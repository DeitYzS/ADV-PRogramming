package se2333.chapter1.view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import se2333.chapter1.Launcher;
import se2333.chapter1.controller.AllCustomHandler;
import se2333.chapter1.model.item.BasedEquipment;

import java.util.ArrayList;

public class InventoryPane extends ScrollPane {
    private ArrayList<BasedEquipment> equipmentArray;
    public InventoryPane(){ }
    private Pane getDetailsPane() {
        Pane inventoryInfoPane = new HBox(10);
        inventoryInfoPane.setBorder(null);
        inventoryInfoPane.setPadding(new Insets(25, 25, 25, 25));

        if (equipmentArray != null) {
            ImageView[] imageViewList = new ImageView[equipmentArray.size()];
            for (int i = 0; i < equipmentArray.size(); i++) {
                imageViewList[i] = new ImageView();
                imageViewList[i].setImage(new Image(Launcher.class.getResource(
                        equipmentArray.get(i).getImgpath()).toString()));
                int funalI = i;
                imageViewList[i].setOnDragDetected(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        AllCustomHandler.onDragDetected(event, equipmentArray.get(funalI), imageViewList[funalI]);
                    }
                });

                imageViewList[i].setOnDragDone(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(DragEvent event) {
                        AllCustomHandler.onEquipDone(event);
                        if(event.isAccepted() != true){
                            AllCustomHandler.onDragFail(event);
                        }
                    }
                });

            }

                inventoryInfoPane.getChildren().addAll(imageViewList);
            }
             return inventoryInfoPane;
    }
    public void drawPane(ArrayList<BasedEquipment> equipmentArray){
        this.equipmentArray = equipmentArray;
        Pane inventoryInfo = getDetailsPane();
        this.setStyle("-fx-background-color:white;");
        this.setContent(inventoryInfo);
    }
}
