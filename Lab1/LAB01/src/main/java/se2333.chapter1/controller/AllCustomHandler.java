package se2333.chapter1.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;
import se2333.chapter1.Launcher;
import se2333.chapter1.model.character.BasedCharater;
import se2333.chapter1.model.item.Armor;
import se2333.chapter1.model.item.BasedEquipment;
import se2333.chapter1.model.item.Weapon;

import java.util.ArrayList;

public class AllCustomHandler {
    public static class GenCharacterHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Launcher.setMainCharacter(GenCharacter.setUpCharater());
            if (Launcher.getEquippedArmor() != null) {
                Launcher.getAllEquipments().add(Launcher.getEquippedArmor());
                Launcher.setEquippedArmor(null);
                Launcher.getMainCharacter().unequipArmor();
            }
            if (Launcher.getEquippedWeapon() != null) {
                Launcher.getAllEquipments().add(Launcher.getEquippedWeapon());
                Launcher.setEquippedWeapon(null);
                Launcher.getMainCharacter().unequipWeapon();
            }
            Launcher.refreshPane();
        }
    }

    public static void onDragDetected(MouseEvent event, BasedEquipment equipment, ImageView imageView) {
        Dragboard db = imageView.startDragAndDrop(TransferMode.ANY);
        db.setDragView(imageView.getImage());
        ClipboardContent content = new ClipboardContent();
        content.put(equipment.DATA_FORMAT, equipment);
        db.setContent(content);
        event.consume();
    }

    public static void onDragOver(DragEvent event, String type) {
        Dragboard dragboard = event.getDragboard();
        BasedEquipment retrievedEquipment = (BasedEquipment) dragboard.getContent(BasedEquipment.DATA_FORMAT);
        if (dragboard.hasContent(BasedEquipment.DATA_FORMAT) && retrievedEquipment.getClass().getSimpleName().equals(type))
            ;
        event.acceptTransferModes(TransferMode.MOVE);
    }

    public static void onDragDropped(DragEvent event, Label lbl, StackPane imgGroup) {
        boolean dragCompleted = false;
        Dragboard dragboard = event.getDragboard();
        ArrayList<BasedEquipment> allEquipments = Launcher.getAllEquipments();

        if (dragboard.hasContent(BasedEquipment.DATA_FORMAT)) {
            BasedEquipment retrievedEquipment = (BasedEquipment) dragboard.getContent(BasedEquipment.DATA_FORMAT);
            BasedCharater character = Launcher.getMainCharacter();

            if (retrievedEquipment.getClass().getSimpleName().equals("Weapon")) {
                if (character.getType().equals(((Weapon) retrievedEquipment).getDamageType()) || character.getClass().getSimpleName().equals("BattleMageCharacter")) {
                    if (Launcher.getEquippedWeapon() != null) {
                        allEquipments.add((Launcher.getEquippedWeapon()));
                    }
                    Launcher.setEquippedWeapon((Weapon) retrievedEquipment);
                    character.equipWeapon((Weapon) retrievedEquipment);
                } else {
                    AllCustomHandler.onDragFail(event);
                }
            } else {
             if(!character.getClass().getSimpleName().equals("BattleMageCharacter")){
                if (Launcher.getEquippedArmor() != null) {
                    allEquipments.add(Launcher.getEquippedArmor());
                }
                Launcher.setEquippedArmor((Armor) retrievedEquipment);
                character.equipArmor((Armor) retrievedEquipment);
             } else {
                    AllCustomHandler.onDragFail(event);
             }
            }
            Launcher.setMainCharacter(character);
            Launcher.setAllEquipments(allEquipments);
            Launcher.refreshPane();

            ImageView imgView = new ImageView();
            if (imgGroup.getChildren().size() != 1) {
                imgGroup.getChildren().remove(1);
                Launcher.refreshPane();
            }
            lbl.setText(retrievedEquipment.getClass().getSimpleName() + ":\n" + retrievedEquipment.getName());
            imgView.setImage(new Image(Launcher.class.getResource(retrievedEquipment.getImgpath()).toString()));
            imgGroup.getChildren().add(imgView);
            dragCompleted = true;
        }
        event.setDropCompleted(dragCompleted);
    }

    public static void onEquipDone(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        ArrayList<BasedEquipment> allEquipments = Launcher.getAllEquipments();
        BasedEquipment retrievedEquipment = (BasedEquipment) dragboard.getContent(BasedEquipment.DATA_FORMAT);

        int pos = -1;
        for (int i = 0; i < allEquipments.size(); i++) {
            if (allEquipments.get(i).getName().equals(retrievedEquipment.getName())) {
                pos = i;
            }
        }
        if (pos != -1) {
            allEquipments.remove(pos);
        }
        Launcher.setAllEquipments(allEquipments);
        Launcher.refreshPane();
    }

    public static class unequip implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if (Launcher.getEquippedArmor() != null) {
                Launcher.getAllEquipments().add(Launcher.getEquippedArmor());
                Launcher.setEquippedArmor(null);
                Launcher.getMainCharacter().unequipArmor();
            }
            if (Launcher.getEquippedWeapon() != null) {
                Launcher.getAllEquipments().add(Launcher.getEquippedWeapon());
                Launcher.setEquippedWeapon(null);
                Launcher.getMainCharacter().unequipWeapon();
            }

            Launcher.refreshPane();
        }
    }
    public  static void onDragFail(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        BasedEquipment retrievedEquipment = (BasedEquipment) dragboard.getContent(BasedEquipment.DATA_FORMAT);
        Launcher.getAllEquipments().add(retrievedEquipment);

        Launcher.refreshPane();
    }
}
