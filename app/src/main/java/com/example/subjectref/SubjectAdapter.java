package com.example.subjectref;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.subjectref.data.Subject;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolder> {
    List<Subject> mData =new ArrayList<>();
    FirebaseStorage storage;

    public void setData(List<Subject> data){
        mData = data;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public SubjectAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        storage = FirebaseStorage.getInstance();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectAdapter.ViewHolder holder, int position) {
        holder.bindView(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mHeader;
        TextView mSubHeader;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mHeader = itemView.findViewById(R.id.txt_header);
            mSubHeader = itemView.findViewById(R.id.txt_sub_header);
            itemView.setOnClickListener(v -> {
                if(mOnItemClickListener != null){
                    mOnItemClickListener.onClick(itemView, mData.get(getLayoutPosition()));
                }
            });
        }

        public void bindView(Subject subject) {
            mHeader.setText(subject.getName());
            mSubHeader.setText(subject.getSubName());
        }
    }
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        public void onClick(View view, Subject subject);
    }
}
