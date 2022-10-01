package se2333.chapter1.model.character;

import se2333.chapter1.model.DamageType;
import se2333.chapter1.model.item.Armor;
import se2333.chapter1.model.item.Weapon;


public class BasedCharater {
    protected  String name,imgpath;
    protected DamageType type;
    protected Integer fullHp, basedPow, basedDef, basedRes;
    protected Integer hp, power, defense, resistance;
    protected Weapon weapon;
    protected Armor armor;

    public String getName(){return name;}
    public String getImgepath(){return imgpath;}
    public Integer getHp(){return hp;}
    public Integer getFullHp(){return fullHp;}
    public Integer getPower(){return power;}
    public Integer getDefense(){return defense;}
    public Integer getResistance(){return resistance;}
    public DamageType getType(){
        return type;
    }

    public void equipWeapon(Weapon weapon) {
        this.weapon = weapon;
        this.power = this.basedPow + weapon.getPower();
    }
    public void equipArmor(Armor armor) {
        this.armor = armor;
        this.defense = this.basedDef + armor.getDefense();
        this.resistance = this.basedRes + armor.getResistance();
    }
    public void unequipWeapon(){
        this.weapon = null;
        this.power = this.basedPow;
    }
    public void unequipArmor(){
        this.armor = null ;
        this.defense = this.basedDef ;
        this.resistance = this.basedRes ;
    }
    @Override
    public String toString(){return name;};

}
