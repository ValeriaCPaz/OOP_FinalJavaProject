package com.assessment;

import java.util.ArrayList;

public class Admin extends Person {

    private Student student;
    private Lecturer lecturer;
    private CollegeBranch collegeBranch;
    private Module module;
    private Course course;
    private ArrayList<CollegeBranch> listOfBranches = new ArrayList<>();


    public Admin(String first_name, String last_name, String gender, String phone, String dob, String emailAddress, String id, String password) {
        super(first_name, last_name, gender, phone, dob, emailAddress, id, password);
    }

    public Admin(String first_name, String last_name) {
        super(first_name, last_name);
    }




    // CREATING BRANCHES, LECTURERS AND STUDENTS

    public CollegeBranch createBranch(String unit, String address) {

        collegeBranch = new CollegeBranch(unit, address);

        getListOfBranches().add(collegeBranch);

        return collegeBranch;

    }

    public Lecturer createLecturer(String first_name, String last_name, String gender, String phone, String dob, String emailAddress,
                                   String id, String password, String courseName, String module, String collegeBranch) throws Exception {

        lecturer = new Lecturer(first_name, last_name, gender, phone, dob, emailAddress, id, password, module,courseName, collegeBranch);


        // add lecturer to list of students in the college branch
        for(CollegeBranch cb: getListOfBranches()){
            if(cb.getUnit().equalsIgnoreCase(student.getCollegeBranchName())){
                lecturer.setCollegeBranch(cb);
                cb.getListOfLecturers().add(lecturer);
                break;
            } else {
                throw new Exception("The branch you're trying to add to the lecturer does not exist!!!");
            }
        }

        // Add lecturer to the course

        for (Course c : lecturer.getCollegeBranch().getListOfCourses()){
            if(c.getName().equalsIgnoreCase(lecturer.getCourseName())){
                c.getListOfLecturersCourse().add(lecturer);
                break;
            }
            else{
                throw new Exception("The course You're trying to add the lecturer to does not exist!!!");
            }
        }

        // add lecturer to the module

        for(Module m: lecturer.getCourse().getListOfModulesCourse()){
            if(m.getSubject().equalsIgnoreCase(lecturer.getModuleName())){
                m.setLecture(lecturer);
                break;
            } else {
                throw new Exception("The module you're trying to add the lecturer does not exist!!!");
            }
        }


        return lecturer;
    }


    public Student createStudents(String first_name, String last_name, String gender, String phone, String dob,
                                  String emailAddress, String id, String password, String collegeBranch, String course, boolean isPaidFull, int installments) throws Exception {

        student = new Student(first_name, last_name, gender, phone, dob, emailAddress, id, password, collegeBranch, course, isPaidFull);

        // add student to list of students in the college branch
        for(CollegeBranch cb: getListOfBranches()){
            if(cb.getUnit().equalsIgnoreCase(student.getCollegeBranchName())){
                student.setCollegeBranch(cb);
                cb.getListOfStudents().add(student);
                break;
            } else {
                throw new Exception("The branch you're trying to add to the student does not exist!!!");
            }
        }

        // add student to list of students in course

        for(Course c : student.getCollegeBranch().getListOfCourses()){
            if(c.getName().equalsIgnoreCase(student.getCourseName())){
                student.setCourse(c);
                c.getListOfStudentsCourse().add(student);
                break;
            } else{
                throw new Exception("The course you're trying to add the student to does not exist!!!");
            }
        }

        // set the installments to be paid
        setInstallments(installments, student);
        return student;

    }


    // DELETING COURSES LECTURERS AND STUDENTS

    public void deleteStudent(Student student, CollegeBranch collegeBranch){

        collegeBranch.getListOfStudents().remove(student);
        student.getCourse().getListOfStudentsCourse().remove(student);

        for (Module m: student.getCourse().getListOfModulesCourse()){
            m.getListOfStudentsModule().remove(student);
        }

    }

    public void deleteCourse(CollegeBranch collegeBranch, Course course){


        for (Student s: course.getListOfStudentsCourse()){
            s.setCourse(null);
        }

        for (Module m: course.getListOfModulesCourse()){
            deleteModule(course, m);
        }

        collegeBranch.getListOfCourses().remove(course);



    }

    public void deleteModule(Course c, Module m){

        c.getListOfModulesCourse().remove(m);

    }

    public void deleteLecturer(Lecturer l, Course c){

        l.getModule().setLecture(null);
        l.getModule().getCourse().getListOfLecturersCourse().remove(l);
        l.getCollegeBranch().getListOfLecturers().remove(l);

    }

    // CREATING MODULES AND COURSES - ADDING STUDENTS TO MODULES
    public Module createModule(String subject, Course course, CollegeBranch collegeBranch, String weekDay, String classHour) {

        module = new Module(subject, course, collegeBranch, weekDay, classHour);

        course.getListOfModulesCourse().add(module);


        return module;
    }

    public Course createCourse(CollegeBranch collegeBranch, String name, Double price) {

        course = new Course(collegeBranch, name, price);
        collegeBranch.getListOfCourses().add(course);

        return course;
    }

    public void addStudentToModule(Module module, Student s) throws Exception {


        if (module.getCourse().getCollegeBranch().getUnit().equalsIgnoreCase(s.getCollegeBranch().getUnit())) {
            module.getListOfStudentsModule().add(s);
            s.getTimetable().add(module);

        } else {
            throw new Exception("This module doesn't exist in this Branch" + s.getFirstName() + " " + module.getSubject());
        }


    }


    // GETTING AND PRINTING LIST OF BRANCHES

    public ArrayList<CollegeBranch> getListOfBranches() {
        return listOfBranches;
    }

    public void printListOfBranches(){
        for (CollegeBranch cb : getListOfBranches()){
            System.out.println("Branch " + cb.getUnit() + "\nLocated at: " + cb.getAddress());
        }
    }


    // DELETING STUDENTS, LECTURERES AND BRANCHES

    public void deleteStudent(String studentID, CollegeBranch cb){

        int counting = 0;

        for (int i = 0; i < collegeBranch.getListOfStudents().size(); i++){
            counting++;
            if (collegeBranch.getListOfStudents().get(i).getId().equalsIgnoreCase(studentID)){
                collegeBranch.getListOfStudents().remove(i);
                System.out.println("Student removed");
                break;
            }
        }

        if (counting == collegeBranch.getListOfStudents().size()){
            System.out.println("This student does not exist in this branch!!!");
        }

    }




    // Add payment to student
    public void addPayment(Student s){

        int count = 0;

        for (int i = 0; i< s.getFees().size(); i++){
            count++;
            if (s.getFees().get(i) !=0.0){
                s.getFees().set(i, 0.0);
                break;
            }

        }
        if (count == s.getFees().size()){
            System.out.println("THE FEES ARE ALL PAID. NO NEED TO ADD EXTRA PAYMENT!!!");
        }

    }



    // Set fees installments

    private void setInstallments(int installments, Student s){

        if(s.isPaidFull()){
            for (int i = 0; i < installments; i++) {
                s.getFees().add(0.0);
            }
        } else {
            Double installment = s.getCourse().getPrice() / installments;

            for (int i = 0; i < installments; i++) {
                s.getFees().add(installment);
            }
        }

    }

}


