import java.io.*;
import java.util.Scanner;

public class CollegeStudentsData {
    static CollegeStudents[] collegeStudents = new CollegeStudents[6000];
    static int studentCount = 0;
    static int baseId = 2024001;
    static final String FILE_NAME = "College.txt";
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        loadCollegeData();

        int choice;

        do {
            System.out.println("\nMENU:");
            System.out.println("1. Create Student Data.");
            System.out.println("2. School Tuition Pay Details.");
            System.out.println("3. Read Student Records/Data.");
            System.out.println("4. Update Student Records/Data.");
            System.out.println("5. Delete Student Records/Data.");
            System.out.println("6. Exit.");

            System.out.println("\nEnter Your Choice from 1-6:");
            choice = scan.nextInt();

            switch (choice) {
                case 1:
                    createStudent();
                    break;
                case 2:
                    tuitionPay();
                    break;
                case 3:
                    readStudentRecords();
                    break;
                case 4:
                    updateStudentData();
                    break;
                case 5:
                    deleteStudentRecords();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    saveCollegeData();
                    break;
                default:
                    System.out.println("Invalid Choice. 1-6 ra gani...BOGO..!!");
            }
        } while (choice != 6);
    }

    static void createStudent() {
        System.out.println("Name: ");
        scan.nextLine();
        String name = scan.nextLine();
        System.out.println("Gender: ");
        String gender = scan.nextLine();
        System.out.println("Nationality: ");
        String nationality = scan.nextLine();
        System.out.println("Course: ");
        String course = scan.nextLine();
        System.out.println("Age: ");
        int age = scan.nextInt();

        CollegeStudents students = new CollegeStudents(baseId + studentCount, name, gender, nationality, course, age);
        collegeStudents[studentCount] = students;
        studentCount++;
    }

    static void tuitionPay() {
        System.out.println("\nEnter your ID Number: ");
        int id = scan.nextInt();

        CollegeStudents studentID = validateID(id);

        if (studentID != null) {
            System.out.println("\nWell hello student, this is where you see the details of your school tuitions.");
            System.out.println("School Tuition: ₱16,000.00");

            System.out.println("\nDo you want to pay your tuition? Type 'Yes' to proceed. Type 'No' to exit: ");
            scan.nextLine();
            String decide = scan.nextLine();

            if (decide.equalsIgnoreCase("Yes")) {
                System.out.println("How much: ");
                double pay = scan.nextDouble();

                if (pay > studentID.getLeftBalance()) {
                    System.out.println("\nYour payment exceeds the remaining balance of ₱" + studentID.getLeftBalance() + ". Please try again.");
                } else {
                    studentID.payTuition(pay);

                    if (studentID.getLeftBalance() == 0) {
                        System.out.println("\nYour school tuition is now paid. You are now cleared.");
                    } else {
                        System.out.println("\nYou paid your tuition with an amount of ₱" + studentID.getPaidTuition());
                        System.out.println("The balance left that you need to pay for you to be cleared for your tuition is ₱" + studentID.getLeftBalance());
                    }
                }

            } else if (decide.equalsIgnoreCase("No")) {
                System.out.println("\nExiting");
            } else {
                System.out.println("\nInvalid input. Please try again.");
            }

        } else {
            System.out.println("\nInvalid ID number or there are no student records that has been created.");
        }
    }

    static void readStudentRecords() {
        if (studentCount == 0) {
            System.out.println("\nThere's no students listed in here or there's no student that has been created or made.");
            return;
        }
        System.out.println("\nSTUDENT'S RECORDS/DETAILS:");

        for (int i = 0; i < studentCount; i++) {
            System.out.println(collegeStudents[i]);
        }
    }

    static void updateStudentData() {
        if (studentCount == 0) {
            System.out.println("\nThere's no student records that has been created or made.");
            return;
        }

        System.out.println("Enter Student's ID to update:");
        int id = scan.nextInt();

        if (id < baseId || id >= baseId + studentCount) {
            System.out.println("\nInvalid ID. Please try again.");
            return;
        }

        CollegeStudents colleges = collegeStudents[id - baseId];

        System.out.println("Enter New Name: ");
        scan.nextLine();
        String newName = scan.nextLine();
        System.out.println("New Gender: ");
        String newGen = scan.nextLine();
        System.out.println("New Nationality: ");
        String newNL = scan.nextLine();
        System.out.println("New Course: ");
        String newCourse = scan.nextLine();
        System.out.println("Age: ");
        int newAge = scan.nextInt();

        colleges.setName(newName);
        colleges.setGender(newGen);
        colleges.setNationality(newNL);
        colleges.setCourse(newCourse);
        colleges.setAge(newAge);

        System.out.println("\nStudent's Record has been updated successfully...");
    }

    static void deleteStudentRecords() {
        if (studentCount == 0) {
            System.out.println("\nThere's no student that has been made or created.");
            return;
        }

        System.out.println("Enter Student's ID to delete: ");
        int id = scan.nextInt();

        if (id < baseId || id >= baseId + studentCount) {
            System.out.println("\nInvalid ID. Please try again.");
            return;
        }

        for (int i = id - baseId; i < studentCount - 1; i++) {
            collegeStudents[i] = collegeStudents[i + 1];
        }
        studentCount--;

        System.out.println("\nStudent's Record Deleted Successfully...");
    }

    static CollegeStudents validateID(int id) {
        for (int i = 0; i < studentCount; i++) {
            if (collegeStudents[i].getId() == id) {
                return collegeStudents[i];
            }
        }
        return null;
    }

    static void loadCollegeData() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine){
                    isFirstLine = false;
                    continue;
                }

                String[] data = line.split(" \\| ");
                int id = Integer.parseInt(data[0].trim());
                String name = data[1].trim();
                String gender = data[2].trim();
                String nationality = data[3].trim();
                String course = data[4].trim();
                int age = Integer.parseInt(data[5].trim());
                double paidTuition = Double.parseDouble(data[7].trim());
                double leftBalance = Double.parseDouble(data[8].trim());

                CollegeStudents student = new CollegeStudents(id, name, gender, nationality, course, age);
                student.setPaidTuition(paidTuition);
                student.setLeftBalance(leftBalance);

                collegeStudents[studentCount] = student;
                studentCount++;
            }
        } catch (IOException e) {
            System.out.println("Error loading College Student Records/Data. " + e.getMessage());
        }
    }

    static void saveCollegeData() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {

            bw.write("   ID   |     NAME     |  GENDER  |  NATIONALITY  | COURSE | AGE | TUITION | PAID TUITION | LEFT BALANCE TO PAY");
            bw.newLine();

            for (int i = 0; i < studentCount; i++) {
                CollegeStudents student = collegeStudents[i];
                bw.write(student.getId() + " | " + student.getName() + " | " + student.getGender() + " | " + student.getNationality() + " | " + student.getCourse() + " | " + student.getAge()
                        + " | " + student.getTuition() + " | " + student.getPaidTuition() + " | " + student.getLeftBalance());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving College Student Data. " + e.getMessage());
        }
    }
}
