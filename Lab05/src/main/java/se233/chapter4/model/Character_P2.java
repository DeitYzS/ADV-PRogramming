package se233.chapter4.model;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se233.chapter4.Launcher;
import se233.chapter4.view.Platform;

public class Character_P2 extends Pane{
        Logger logger = LoggerFactory.getLogger(se233.chapter4.model.Character.class);
        public static final int CHARACTER_WIDTH = 32;
        public static final int CHARACTER_HEIGHT = 64;
        private Image characterImg;
        private AnimatedSprite imageView;
        private int x;
        private int y;
        private KeyCode leftKey;
        private KeyCode rightKey;
        private KeyCode upKey;
        int xVelocity = 0;
        int yVelocity = 0;
        boolean isMoveLeft = false;
        boolean isMoveRight = false;
        boolean isFalling = true;
        boolean canJump = false;
        boolean isJumping = false;
        int xAcceleration = 1;

        int yAccelration = 1;
        int xMaxVelocity = 10;
        int yMaxVelocity = 18;

        public Character_P2(int x, int y, int offsetX, int offsetY, KeyCode leftKey, KeyCode rightKey, KeyCode upKey){
            this.x = x;
            this.y = y;
            this.setTranslateX(x);
            this.setTranslateY(y);
            this.characterImg = new Image(Launcher.class.getResourceAsStream("assets/MarioSheet.png"));
            this.imageView = new AnimatedSprite(characterImg,4,4,1,offsetX,offsetY,16,32);
            this.imageView.setFitWidth(CHARACTER_WIDTH);
            this.imageView.setFitHeight(CHARACTER_HEIGHT);
            this.leftKey = leftKey;
            this.rightKey = rightKey;
            this.upKey = upKey;
            this.getChildren().addAll(this.imageView);
        }

        public void moveY() {
            setTranslateY(y);
            if(isFalling) {
                yVelocity = yVelocity >= yMaxVelocity? yMaxVelocity : yVelocity+yAccelration;
                y = y + yVelocity;
            }
            else if (isJumping){
                yVelocity = yVelocity <= 0 ? 0 : yVelocity-yAccelration;
                y =  y - yVelocity;
            }
        }

        public void moveX(){
            setTranslateX(x);
            if(isMoveLeft) {
                xVelocity = xVelocity >= xMaxVelocity? xMaxVelocity : xVelocity+xAcceleration;
                x = x - xVelocity;
            }
            if(isMoveRight) {
                xVelocity = xVelocity >= xMaxVelocity? xMaxVelocity : xVelocity+xAcceleration;
                x = x + xVelocity;
            }
        }

        public void checkReachGameWall() {
            if(x <= 0) {
                x = 0;
            } else if(x+getWidth() >= Platform.WIDTH) {
                x = Platform.WIDTH-(int)getWidth();
            }
        }

        public void jump() {
            if(canJump) {
                yVelocity = yMaxVelocity;
                canJump = false;
                isJumping = true;
                isFalling = false;
            }
        }

        public void checkReachHighest() {
            if(isJumping && yVelocity <= 0){
                isJumping = false;
                isFalling = true;
                yVelocity = 0;
            }
        }

        public void checkReachFloor() {
            if(isFalling && y >= Platform.GROUND - CHARACTER_HEIGHT){
                isFalling = false;
                canJump = true;
                yVelocity = 0;
            }
        }

        public void repaint() {
            moveX();
            moveY();
        }

        public void moveLeft() {
            isMoveLeft = true;
            isMoveRight = false;
        }

        public void moveRight() {
            isMoveRight = true;
            isMoveLeft = false;
        }

        public void stop() {
            isMoveRight = false;
            isMoveLeft = false;
        }

    public Image getCharacterImg() {
        return characterImg;
    }

    public AnimatedSprite getImageView() {
        return imageView;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public KeyCode getLeftKey() {
        return leftKey;
    }

    public KeyCode getRightKey() {
        return rightKey;
    }

    public KeyCode getUpKey() {
        return upKey;
    }

    public void trace() {
            logger.info("x:{} y:{} vx:{} vy:{}",x,y,xVelocity,yVelocity);
        }
}
