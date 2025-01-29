// CSE 110     : <Class #> / <meeting days and times>
// Assignment  : <assignment #>
// Author      : <name> & <studentID>
// Description : <of the file contents>

import java.util.Random;

class Monster {

    private String name;
    private int healthScore;
    private Weapon weapon;

    public Monster(String string, int num, Weapon wep){
        this.name = string;
        this.healthScore = num;
        this.weapon = wep;
    }



    public String getName(){
        return this.name;
    }

    public int getHealthScore(){
        return this.healthScore;
    }

    public String getWeaponName(){
        return this.weapon.getName();
    }

    public int attack(Monster m){
        Random random = new Random();
        int damage = random.nextInt(this.weapon.getMaxDamage() + 1);
        m.healthScore -= damage;
        if (m.healthScore < 0) {
            m.healthScore = 0;
        }
        return damage;
    }

}
