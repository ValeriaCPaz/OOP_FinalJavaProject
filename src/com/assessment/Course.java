package com.assessment;

import java.util.ArrayList;

public class Course {

    CollegeBranch collegeBranch;
    private String name;
    private ArrayList<Lecturer> listOfLecturersCourse = new ArrayList<>();
    private ArrayList<Student> listOfStudentsCourse = new ArrayList<>();
    private ArrayList<Module> listOfModulesCourse = new ArrayList<>();
    private Double price;

    public Course(CollegeBranch collegeBranch, String name, Double price) {
        this.collegeBranch = collegeBranch;
        this.name = name;
        this.price = price;
    }

    public CollegeBranch getCollegeBranch() {
        return collegeBranch;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public ArrayList<Lecturer> getListOfLecturersCourse() {
        return listOfLecturersCourse;
    }

    public void setListOfLecturersCourse(ArrayList<Lecturer> listOfLecturersCourse) {
        this.listOfLecturersCourse = listOfLecturersCourse;
    }

    public ArrayList<Student> getListOfStudentsCourse() {
        return listOfStudentsCourse;
    }

    public void setListOfStudentsCourse(ArrayList<Student> listOfStudentsCourse) {
        this.listOfStudentsCourse = listOfStudentsCourse;
    }

    public ArrayList<Module> getListOfModulesCourse() {
        return listOfModulesCourse;
    }

    public void setListOfModulesCourse(ArrayList<Module> listOfModulesCourse) {
        this.listOfModulesCourse = listOfModulesCourse;
    }
}