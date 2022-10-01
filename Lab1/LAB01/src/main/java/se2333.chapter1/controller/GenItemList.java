package se2333.chapter1.controller;

import se2333.chapter1.model.DamageType;
import se2333.chapter1.model.item.Armor;
import se2333.chapter1.model.item.BasedEquipment;
import se2333.chapter1.model.item.Weapon;

import java.util.ArrayList;

public class GenItemList {
    public static ArrayList<BasedEquipment> setUpItemList() {
        ArrayList<BasedEquipment> itemLists = new ArrayList<BasedEquipment>(6);
        itemLists.add(new Weapon("Sword",10, DamageType.physical,"assets/sword1.png"));
        itemLists.add(new Weapon("Gun",20,DamageType.physical,"assets/gun1.png"));
        itemLists.add(new Weapon("Staff",30,DamageType.magical,"assets/staff1.png"));
        itemLists.add(new Armor("shirt",0,50,"assets/shirt1.png"));
        itemLists.add(new Armor("armor",50,0,"assets/armor1.png"));
        itemLists.add(new Weapon("spear",12,DamageType.physical,"assets/spear.png"));
        return itemLists;
    }
}
