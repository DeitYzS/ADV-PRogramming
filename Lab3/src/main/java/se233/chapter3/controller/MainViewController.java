package se233.chapter3.controller;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import se233.chapter3.Launcher;
import se233.chapter3.model.FileFreq;
import se233.chapter3.model.PDFdocument;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class MainViewController {
    LinkedHashMap<String, ArrayList<FileFreq>> uniqueSets;

    LinkedHashMap<String, ArrayList<FileFreq>> newMap = new LinkedHashMap<>();
    LinkedHashMap<String, String> fileMap = new LinkedHashMap<>();
    @FXML private ListView<String> inputListView;
    @FXML private Button startButton;
    @FXML ListView listView;
    @FXML private MenuItem Close;

    @FXML
        public void initialize(){
        inputListView.setOnDragOver(event -> {
            Dragboard db = event.getDragboard();
            final boolean  isAccepted = db.getFiles().get(0).getName().toLowerCase().endsWith(".pdf");
            if(db.hasFiles() && isAccepted){
                event.acceptTransferModes(TransferMode.COPY);
            } else {
                event.consume();
            }
        });

        inputListView.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if(db.hasFiles()){
                success = true;
                String filePath;
                int total_files = db.getFiles().size();

                for(int i = 0;i< total_files;i++){
                        File file = db.getFiles().get(i);
                        filePath = file.getAbsolutePath();
                    inputListView.getItems().add(file.getName());
                    fileMap.put(file.getName(),filePath) ;
                }


            event.setDropCompleted(success);
            event.consume();
        }
    });
        startButton.setOnAction(event1 -> {
            Parent bgRoot = Launcher.stage.getScene().getRoot();
            Task<Void> processTask = new Task<Void>() {
                @Override
                protected Void call() throws IOException {
                    ProgressIndicator pi = new ProgressIndicator();
                    VBox box = new VBox(pi);
                    box.setAlignment(Pos.CENTER);
                    Launcher.stage.getScene().setRoot(box);
                    ExecutorService executor = Executors.newFixedThreadPool(4);
                    final ExecutorCompletionService<Map<String, FileFreq>> completionService = new ExecutorCompletionService<>(executor);
                    List<String> inputListViewItems = inputListView.getItems();
                    int total_files = inputListViewItems.size();
                    Map<String, FileFreq>[] wordMap = new Map[total_files];

                    for(int i = 0; i < total_files;i++) {
                        try {
                            String fileName = inputListViewItems.get(i);
                            String filePath = fileMap.get(fileName);
                            PDFdocument p = new PDFdocument(filePath);
                            completionService.submit(new WordMapPageTask(p));
                        }catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    for(int i = 0; i<total_files;i++) {
                        try {
                            Future<Map<String, FileFreq>> future = completionService.take();
                            wordMap[i] = future.get();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    try {
                        WordMapMergeTask merger = new WordMapMergeTask(wordMap);
                        Future<LinkedHashMap<String, ArrayList<FileFreq>>> future = executor.submit(merger);
                        uniqueSets = future.get();
                        //Exercise 2
                        uniqueSets.forEach((Key,Value) -> {
                            String newKey = "";
                            newKey = Key + " ( ";
                            for(int i=0; i<Value.size();i++){
                                if(i == Value.size()-1){
                                    newKey += Value.get(i).getFreq() + " ) ";
                                } else {
                                    newKey += Value.get(i).getFreq() + " , ";
                                }
                            }
                            newMap.put(newKey,Value);
                        });
                        listView.getItems().addAll(newMap.keySet());
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        executor.shutdown();
                    }
                    return null;
                }
            };
            processTask.setOnSucceeded(e -> {
                Launcher.stage.getScene().setRoot(bgRoot);
            });
            Thread thread = new Thread(processTask);
            thread.setDaemon(true);
            thread.start();
        });

        listView.setOnMouseClicked(event -> {
            ArrayList<FileFreq> listOfLinks = newMap.get(listView.getSelectionModel().getSelectedItem());
            ListView<FileFreq> popupListView = new ListView<>();
            LinkedHashMap<FileFreq, String> lookupTable = new LinkedHashMap<>();

            for(int i = 0; i<listOfLinks.size(); i++) {
                lookupTable.put(listOfLinks.get(i), listOfLinks.get(i).getPath());
                popupListView.getItems().add(listOfLinks.get(i));
            }
            popupListView.setPrefHeight(popupListView.getItems().size() * 28);
            popupListView.setOnMouseClicked(innerEvent -> {
                Launcher.hs.showDocument("file:///"+lookupTable.get(popupListView.getSelectionModel().getSelectedItem()));
                popupListView.getScene().getWindow().hide();
            });
            Popup popup = new Popup();
            //Exercise 5
            popupListView.setOnKeyPressed(keyEvent -> {
                if(keyEvent.getCode() == KeyCode.ESCAPE){ popup.hide();}
            });
            popup.getContent().add(popupListView);
            popup.show(Launcher.stage);
        });
        //Exercise 4
        Close.setOnAction(event -> {
            System.exit(0);
        });
    }
}
