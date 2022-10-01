package se2333.chapter1.controller;

import se2333.chapter1.model.character.BasedCharater;
import se2333.chapter1.model.character.BattleMageCharacter;
import se2333.chapter1.model.character.MagicalCharacter;
import se2333.chapter1.model.character.PhysicalCharacter;
import java.util.Random;

public class GenCharacter {
    public static BasedCharater setUpCharater(){
        BasedCharater charater;
        Random rand = new Random();
        int type = rand.nextInt(3)+1;
        int basedDef = rand.nextInt(50)+1;
        int basedRes = rand.nextInt(50)+1;
        if(type == 1) {
            charater = new MagicalCharacter("Yae Miko","assets/yae.png",basedDef,basedRes);
        }else if (type == 2){
            charater = new PhysicalCharacter("Eula", "assets/eula.png", basedDef, basedRes);
        }else {
            charater = new BattleMageCharacter("Paimon","assets/paimon.png",basedDef,basedRes);
  }

        return charater;
        }
    }
