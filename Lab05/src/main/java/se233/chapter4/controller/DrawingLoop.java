package se233.chapter4.controller;

import se233.chapter4.model.Character;
import se233.chapter4.model.Character_P2;
import se233.chapter4.view.Platform;

public class DrawingLoop implements Runnable{
    private Platform platform;
    private int frameRate;
    private float interval;
    private boolean running;

    public DrawingLoop(Platform platform) {
        this.platform = platform;
        frameRate = 30;
        interval = 1000.0f / frameRate; // 1000 ms = 1 second
        running = true;
    }

    private void checkDrawCollisions(Character character, Character_P2 character_p2) {
        character.checkReachGameWall();
        character.checkReachHighest();
        character.checkReachFloor();

        character_p2.checkReachGameWall();
        character_p2.checkReachHighest();
        character_p2.checkReachFloor();
    }

    private void paint(Character character,Character_P2 character_p2) {
        character.repaint();
        character_p2.repaint();
    }

    @Override
    public void run() {
        while(running) {
            float time = System.currentTimeMillis();
            checkDrawCollisions(platform.getCharacter(),platform.getCharacter_P2());
            paint(platform.getCharacter(),platform.getCharacter_P2());
            time = System.currentTimeMillis() - time;
            if(time < interval) {
                try {
                    Thread.sleep((long) (interval - time));
                } catch (InterruptedException e) {
                    running = false;
                }
            }
        }
    }
}
