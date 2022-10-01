package se2333.chapter1.model.character;

import se2333.chapter1.model.DamageType;

public class MagicalCharacter extends BasedCharater {
    public MagicalCharacter(String name,String imgpath,int basedDef,int basedRes) {
         this.name = name;
         this.type = DamageType.magical;
         this.imgpath = imgpath;
         this.fullHp = 30;
         this.basedPow = 50;
         this.basedDef = basedDef;
         this.basedRes = basedRes;
         this.hp = this.fullHp;
         this.power = this.basedPow;
         this.defense = basedDef;
         this.resistance = basedRes;
         }
 }