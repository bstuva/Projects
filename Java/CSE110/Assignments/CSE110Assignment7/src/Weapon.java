// Author      : Brayden Stuva


class Weapon {

    private String name;
    private int maxDamage;

    //a default constructors to initialize
    public Weapon(){
        this.name = "Pointy Stick";
        this.maxDamage = 1;
    }

    //constructor
    public Weapon(String weaponName, int weaponDamage){
        this.name = weaponName;
        this.maxDamage = weaponDamage;
    }

    //Getters and Setters
    public String getName(){
        return this.name;
    }

    public int getMaxDamage(){
        return this.maxDamage;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setMaxDamage(int newDamage){
        this.maxDamage = newDamage;
    }

}
