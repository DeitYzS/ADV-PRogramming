package se2333.chapter1.model.character;

import se2333.chapter1.model.DamageType;
import se2333.chapter1.model.item.BasedEquipment;

public class BattleMageCharacter extends BasedCharater {
    public BattleMageCharacter(String name,String imgpath,int basedDef,int basedRes) {
        this.name = name;
        this.type = DamageType.battleMage;
        this.imgpath = imgpath;
        this.fullHp = 40;
        this.basedPow = 40;
        this.basedDef = basedDef;
        this.basedRes = basedRes;
        this.hp = this.fullHp;
        this.power = this.basedPow;
        this.defense = basedDef;
        this.resistance = basedRes;
    }
}
