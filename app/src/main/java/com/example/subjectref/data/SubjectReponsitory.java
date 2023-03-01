package com.example.subjectref.data;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubjectReponsitory {
    FirebaseFirestore db;
    public SubjectReponsitory(){
        db = FirebaseFirestore.getInstance();
    }
    public MutableLiveData<List<Subject>> getAllSubject(){
        MutableLiveData<List<Subject>> listLiveData = new MutableLiveData<>(new ArrayList<>());
        db.collection("subject")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        List<Subject> subjects = new ArrayList<>();
                        for (DocumentSnapshot document : value.getDocuments()) {
                            String name = document.get("name",String.class);
                            String subtitle = document.get("intro",String.class);
                            Subject subject = new Subject(name,subtitle);
                            document.getReference().collection("lesson").addSnapshotListener(new EventListener<QuerySnapshot>() {
                                @Override
                                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                                    List<Lesson> lessons = new ArrayList<>();
                                    for (DocumentSnapshot valueDocument : value.getDocuments()) {
                                        String name = valueDocument.get("name",String.class);
                                        String intro = valueDocument.get("intro",String.class);
                                        String content = valueDocument.get("content",String.class);
                                        Lesson lesson = new Lesson(name,intro,content);
                                        lessons.add(lesson);
                                    }
                                    subject.setLessons(lessons);
                                    subjects.add(subject);
                                    listLiveData.setValue(subjects);

                                }
                            });
                        }
                    }
                });

        return listLiveData;
    }
    public void createSubject(String name,String subtitle){
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("name" ,name);
        dataMap.put("sublitle",subtitle);
        db.collection("subject")
                .add(dataMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
    public void deleteSubject(String id){
        db.collection("subject").document(id)
                .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
}
