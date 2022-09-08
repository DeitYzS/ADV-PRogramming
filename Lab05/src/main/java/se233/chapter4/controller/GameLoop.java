package se233.chapter4.controller;

import se233.chapter4.model.Character;
import se233.chapter4.model.Character_P2;
import se233.chapter4.view.Platform;

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

    private void update_P2(Character_P2 character_p2) {
        if(platform.getKeys().isPressed(character_p2.getLeftKey())){
            character_p2.setScaleX(-1);
            character_p2.moveLeft();
            platform.getCharacter_P2().trace();
        }
        if(platform.getKeys().isPressed(character_p2.getRightKey())){
            character_p2.setScaleX(1);
            character_p2.moveRight();
            platform.getCharacter_P2().trace();
        }
        if(!platform.getKeys().isPressed(character_p2.getLeftKey()) && !platform.getKeys().isPressed(character_p2.getRightKey()) ) {
            character_p2.stop();
        }
        if(platform.getKeys().isPressed(character_p2.getLeftKey() ) || platform.getKeys().isPressed(character_p2.getRightKey())) {
            character_p2.getImageView().tick();
        }
        if(platform.getKeys().isPressed(character_p2.getUpKey()) ) {
            character_p2.jump();
        }
    }

    @Override
    public void run() {
        while (ruuning) {
            float time = System.currentTimeMillis();
            update(platform.getCharacter());
            update_P2(platform.getCharacter_P2());
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
