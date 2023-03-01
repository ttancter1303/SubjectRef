package com.example.subjectref.data;

import java.util.List;

public class Subject {
    private String name;
    private String subName;
    private List<Lesson> lessons;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public Subject(String name, String subName, List<Lesson> lessons) {
        this.name = name;
        this.subName = subName;
        this.lessons = lessons;
    }
    public Subject(String name, String subName) {
        this.name = name;
        this.subName = subName;
    }
}
