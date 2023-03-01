package com.example.subjectref;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.subjectref.data.HomeViewModel;
import com.example.subjectref.data.Subject;
import com.example.subjectref.databinding.FragmentHomeBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    Button btnAdd;
    Button btnRemove;
    NavController mController;
    SubjectAdapter adapter;
    FirebaseFirestore db;
    HomeViewModel mViewmodel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        mController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        db = FirebaseFirestore.getInstance();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnAdd = binding.btnAdd;
        btnRemove = binding.btnDelete;
        RecyclerView recyclerView = view.findViewById(R.id.recycle_view);
        adapter = new SubjectAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mViewmodel = new ViewModelProvider(this).get(HomeViewModel.class);
        if(mViewmodel.getAllSubject() != null){
            mViewmodel.getAllSubject().observe(getViewLifecycleOwner(), new Observer<List<Subject>>() {
                @Override
                public void onChanged(List<Subject> subjects) {
                    for (Subject subject : subjects) {
                        adapter.setData(subjects);
                    }
                }
            });
        }

        btnAdd.setOnClickListener(v->{
//            createSubject();
        });
        btnRemove.setOnClickListener(v->{
//            deleteSubject();
        });
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