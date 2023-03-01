package com.example.subjectref.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class HomeViewModel extends ViewModel {
    SubjectReponsitory mSubjectReponsitory;
    LiveData<List<Subject>> mAllSubject;

    public HomeViewModel() {
        mSubjectReponsitory = new SubjectReponsitory();
        mAllSubject =mSubjectReponsitory.getAllSubject();
    }

    public LiveData<List<Subject>> getAllSubject() {
        return mAllSubject;
    }
    public void createSubject(String name, String intro){
        mSubjectReponsitory.createSubject(name,intro);
    }
}