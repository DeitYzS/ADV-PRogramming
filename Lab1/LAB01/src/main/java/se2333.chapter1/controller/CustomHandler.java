package se2333.chapter1.controller;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import se2333.chapter1.Launcher;
import se2333.chapter1.model.item.Armor;
import se2333.chapter1.model.item.BasedEquipment;
import se2333.chapter1.model.item.Weapon;

public class CustomHandler {
    public static void OnDragOver(DragEvent event, String type){
            Dragboard dragboard = event.getDragboard();
            BasedEquipment retrievedEquipment = (BasedEquipment)dragboard.getContent(BasedEquipment.DATA_FORMAT);
        if(dragboard.hasContent(BasedEquipment.DATA_FORMAT) && retrievedEquipment.getClass().getSimpleName().equals(type)) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
    }

    public static  void onDragDropped(DragEvent event, Label lbl, StackPane imgGroup) {
        boolean dragCompleted = false;
        Dragboard dragboard = event.getDragboard();
        if(dragboard.hasContent(BasedEquipment.DATA_FORMAT)) {
            BasedEquipment retrievedEquipment = (BasedEquipment) dragboard.getContent(BasedEquipment.DATA_FORMAT);
        if(retrievedEquipment.getClass().getSimpleName().equals("Weapon")) {
            Launcher.setEquipment((Weapon) retrievedEquipment);
        } else {
            Launcher.setEquippedArmor((Armor) retrievedEquipment);
        }
            ImageView imgView = new ImageView();
        if(imgGroup.getChildren().size() != 1) {
            imgGroup.getChildren().remove(1);
            Launcher.refreshPane();
        }
            lbl.setText(retrievedEquipment.getClass().getSimpleName() + ":\n" +
                    retrievedEquipment.getName());
             imgView.setImage(new Image(Launcher.class.getResource(retrievedEquipment.
                    getImgpath()).toString()));
             imgGroup.getChildren().add(imgView);
             dragCompleted = true;
        }
            event.setDropCompleted(dragCompleted);
        }
    }

