package se2333.chapter1.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import se2333.chapter1.Launcher;
import se2333.chapter1.controller.AllCustomHandler;
import se2333.chapter1.model.character.BasedCharater;

public class CharacterPane extends ScrollPane {
    private BasedCharater charater;
    public CharacterPane() { }
    private Pane getDetailsPane() {
        Pane characterInfoPane = new VBox(10);
        characterInfoPane.setBorder(null);
        characterInfoPane.setPadding(new Insets(25, 25, 25, 25));
        Label name, type, hp, atk, def, res;
        ImageView mainImage = new ImageView();

        if (this.charater != null) {
            name = new Label("Name: " + charater.getName());
            mainImage.setImage(new Image(Launcher.class.getResource(charater.getImgepath()).toString()));
            hp = new Label("HP: " + charater.getHp().toString() + "/" + charater.getFullHp().toString());
            type = new Label("Type: " + charater.getType().toString());
            atk = new Label("ATK: " + charater.getPower());
            def = new Label("DEF: " + charater.getDefense());
            res = new Label("RES: " + charater.getResistance());
        } else {
            name = new Label("Name: ");
            mainImage.setImage(new Image(Launcher.class.getResource("assets/unknown.png").toString()));
            hp = new Label("HP: ");
            type = new Label("Type: ");
            atk = new Label("ATK: ");
            def = new Label("DEF: ");
            res = new Label("RES: ");
        }
        Button genCharacter = new Button();
        genCharacter.setText("Generate Character");
        genCharacter.setOnAction(new AllCustomHandler.GenCharacterHandler());
        characterInfoPane.getChildren().addAll(name,mainImage,type,hp,atk,def,res,genCharacter);
        return characterInfoPane;
    }
    public void drawPane(BasedCharater charater){
        this.charater = charater;
        Pane characterInfo = getDetailsPane();
        this.setStyle("-fx-background-color:white;");
        this.setContent(characterInfo);
    }
}
