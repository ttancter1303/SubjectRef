package com.example.subjectref.data;

public class Lesson {
    private String name;
    private String intro;
    private String content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Lesson(String name, String intro, String content) {
        this.name = name;
        this.intro = intro;
        this.content = content;
    }
}
