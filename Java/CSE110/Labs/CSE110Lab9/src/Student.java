public class Student {


    private String fullName;
    private String id;
    private double grade;

    private int numUpdated;
    private int numAccessed;



    public String getFullName(){
       this.numAccessed++;
       return this.fullName;

    }

    public String getId(){
        this.numAccessed++;
        return this.id;
    }

    public double getGrade(){
        this.numAccessed++;
        return this.grade;
    }

    public void setFullName(String updatedName){
        this.fullName = updatedName;
        this.numUpdated++;
    }

    public void setId(String updatedID){
        this.id = updatedID;
        this.numUpdated++;
    }

    public void setGrade(double updatedGrade){
        this.grade = updatedGrade;
        this.numUpdated++;
    }

    public int getNumUpdated(){
        this.numAccessed++;
        return this.numUpdated;
    }

    public int getNumAccessed(){
        this.numAccessed++;
        return this.numAccessed;
    }
}
