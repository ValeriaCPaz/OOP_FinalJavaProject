package com.assessment;

import java.util.ArrayList;

public class CollegeBranch {

    private String unit;
    private String address;
    private ArrayList<Course> listOfCourses = new ArrayList<>();
    private ArrayList<Lecturer> listOfLecturers = new ArrayList<>();
    private ArrayList<Student> listOfStudents = new ArrayList<>();
    private ArrayList<CollegeBranch> listOfBranches = new ArrayList<>();


    public CollegeBranch(String unit, String address) {
        this.unit = unit;
        this.address = address;
        listOfBranches.add(this);
    }

    public String getUnit() {
        return unit;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<CollegeBranch> getListOfBranches() {
        return listOfBranches;
    }

    public ArrayList<Course> getListOfCourses() {
        return listOfCourses;
    }

    public void setListOfCourses(ArrayList<Course> listOfCourses) {
        this.listOfCourses = listOfCourses;
    }

    public ArrayList<Lecturer> getListOfLecturers() {
        return listOfLecturers;
    }

    public void setListOfLecturers(ArrayList<Lecturer> listOfLecturers) {
        this.listOfLecturers = listOfLecturers;
    }

    public ArrayList<Student> getListOfStudents() {
        return listOfStudents;
    }

    @Override
    public String toString() {
        return "CollegeBranch{" +
                "unit='" + unit + '\'' +
                ", address='" + address + '\'' +
                ", listOfCourses=" + listOfCourses +
                ", listOfLecturers=" + listOfLecturers +
                ", listOfStudents=" + listOfStudents +
                '}';
    }

    public void setListOfStudents(ArrayList<Student> listOfStudents) {
        this.listOfStudents = listOfStudents;
    }
}
