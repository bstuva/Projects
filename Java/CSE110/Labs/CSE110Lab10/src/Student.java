public class Student {

    private String fullName;
    private String id;
    private double grade;

    public Student(String fullName, String id, double grade){

        this.fullName = fullName;
        this.id = id;
        this.grade = grade;
    }

    public String toString(){
        return System.out.printf("Read%s   %s", getFullName(), getId()).toString();
    }

    public boolean equals(Object obj){

        if(obj == null){
            return false;
        }

        if(obj.getClass() != this.getClass()){
            return false;
        }

        Student other = (Student) obj;
        return this.id.equals(other.id);
    }

    public String getFullName(){
        return this.fullName;
    }
    public String getId(){
        return this.id;
    }
    public double getGrade(){
        return this.grade;
    }

    public void setFullName(String updatedName){
       this.fullName = updatedName;
    }
    public void setId(String updatedId){
        this.id = updatedId;
    }
    public void setGrade(double updatedGrade){
        this.grade = updatedGrade;
    }
}
