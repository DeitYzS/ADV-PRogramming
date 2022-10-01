package se2333.chapter1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import se2333.chapter1.controller.GenCharacter;
import se2333.chapter1.controller.GenItemList;
import se2333.chapter1.model.item.BasedEquipment;
import se2333.chapter1.model.character.BasedCharater;
import se2333.chapter1.model.item.*;
import se2333.chapter1.view.CharacterPane;
import se2333.chapter1.view.EquipPane;
import se2333.chapter1.view.InventoryPane;

import java.util.ArrayList;

public class Launcher extends Application {
    private static Scene mainScene;
    private static BasedCharater mainCharacter = null;
    private static ArrayList<BasedEquipment> allEquipments = null;
    private static Weapon equippedWeapon = null;
    private static Armor equippedArmor = null;
    private static CharacterPane characterPane = null;
    private static EquipPane equipPane = null;
    private static InventoryPane inventoryPane = null;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Intro to RPG");
        primaryStage.setResizable(false);
        primaryStage.show();
        mainCharacter = GenCharacter.setUpCharater();
        allEquipments = GenItemList.setUpItemList();
        Pane mainPane = getMainPane();
        mainScene = new Scene(mainPane);
        primaryStage.setScene(mainScene);
    }
    public Pane getMainPane() {
        BorderPane mainPane = new BorderPane();
        characterPane = new CharacterPane();
        equipPane = new EquipPane();
        inventoryPane = new InventoryPane();
        refreshPane();
        mainPane.setCenter(characterPane);
        mainPane.setLeft(equipPane);
        mainPane.setBottom(inventoryPane);
        return mainPane;
    }
    public static  void refreshPane() {
        characterPane.drawPane(mainCharacter);
        equipPane.drawPane(equippedWeapon,equippedArmor);
        inventoryPane.drawPane(allEquipments);
    }
    public static  BasedCharater getMainCharacter() { return mainCharacter;}
    public static  void setMainCharacter(BasedCharater mainCharacter) {
        Launcher.mainCharacter = mainCharacter;
    }
    public static BasedEquipment getEquippedWeapon() { return  equippedWeapon;}
    public static void setEquippedWeapon(Weapon retrievedEquipment) { equippedWeapon = retrievedEquipment;}
    public static BasedEquipment getEquippedArmor() { return  equippedArmor;}
    public static void setEquippedArmor(Armor retrievedEquipment) { equippedArmor = retrievedEquipment;}
    public static ArrayList<BasedEquipment> getAllEquipments() { return allEquipments;}
    public static void setAllEquipments(ArrayList<BasedEquipment> allEquipments) { allEquipments = allEquipments;}
    public static void setEquipment(Weapon retrievedEquipment) {}

    public static void main(String[] args) {
        launch(args);
    }
}


