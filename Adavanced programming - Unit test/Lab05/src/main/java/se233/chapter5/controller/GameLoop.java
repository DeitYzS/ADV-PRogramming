package se233.chapter5.controller;

import se233.chapter5.model.Character;
import se233.chapter5.view.Platform;
import se233.chapter5.view.Score;
import java.util.ArrayList;

public class GameLoop implements Runnable{
    private Platform platform;
    private int frameRate;
    private float interval;
    private boolean ruuning;

    public GameLoop(Platform platform) {
        this.platform = platform;
        frameRate = 10;
        interval = 1000.0f / frameRate;
        ruuning = true;
    }
    private void update(Character character) {
        if(platform.getKeys().isPressed(character.getLeftKey())){
            character.setScaleX(-1);
            character.moveLeft();
            platform.getCharacter().trace();
        }
        if(platform.getKeys().isPressed(character.getRightKey())){
            character.setScaleX(1);
            character.moveRight();
            platform.getCharacter().trace();
        }
        if(!platform.getKeys().isPressed(character.getLeftKey()) && !platform.getKeys().isPressed(character.getRightKey()) ) {
          character.stop();
        }
        if(platform.getKeys().isPressed(character.getLeftKey() ) || platform.getKeys().isPressed(character.getRightKey())) {
            character.getImageView().tick();
        }
        if(platform.getKeys().isPressed(character.getUpKey()) ) {
            character.jump();
        }
    }
    private void updateScore(ArrayList<Score> scoreList, ArrayList<Character> characterList) {
        javafx.application.Platform.runLater(() -> {
            for (int i=0; i<scoreList.size(); i++) {
                scoreList.get(i).setPoint(characterList.get(i).getScore());
            }
        });
    }

    @Override
    public void run() {
        while (ruuning) {
            float time = System.currentTimeMillis();
            update(platform.getCharacter());
            update(platform.getCharacter_P2());
            updateScore(platform.getScoreList(),platform.getCharacterList());
            time = System.currentTimeMillis() - time;

            if (time < interval) {
                try {
                    Thread.sleep((long) (interval - time));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep((long) (interval - (interval % time)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
