public class CollegeStudents {
    private int id;
    private String name;
    private String gender;
    private String nationality;
    private String course;
    private int age;
    private final int tuition = 16000;
    private double paidTuition;
    private double leftBalance;

    public CollegeStudents(int id, String name, String gender, String nationality, String course, int age) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.nationality = nationality;
        this.course = course;
        this.age = age;
        this.paidTuition = 0;
        this.leftBalance = 16000;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public int getTuition() {
        return tuition;
    }

    public double getPaidTuition() {
        return paidTuition;
    }
    public void setPaidTuition(double paidTuition) {
        this.paidTuition = paidTuition;
    }

    public double getLeftBalance() {
        return leftBalance;
    }
    public void setLeftBalance(double leftBalance) {
        this.leftBalance = leftBalance;
    }

    public void payTuition(double amount) {
        if (amount > 0) {
            this.paidTuition += amount;
            this.leftBalance -= amount;
        }
    }

    @Override
    public String toString() {
        return "\nID: " + id +
                "\nName: " + name +
                "\nGender: " + gender +
                "\nNationality: " + nationality +
                "\nCourse: " + course +
                "\nAge: " + age +
                "\nSchool Tuition: " + tuition +
                "\nPaid Tuition: " + paidTuition +
                "\nLeft Tuition Balance to pay: " + leftBalance;
    }
}
