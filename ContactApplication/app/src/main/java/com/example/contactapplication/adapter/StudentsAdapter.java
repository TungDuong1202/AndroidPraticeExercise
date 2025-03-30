package com.example.contactapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactapplication.DetailActivity;
import com.example.contactapplication.R;
import com.example.contactapplication.model.Student;

import java.util.List;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.StudentsViewHolder> {
    private List<Student> students;
    private final ActivityResultLauncher<Intent> detailLauncher;
    private final Context context;

    public StudentsAdapter(Context context, List<Student> students, ActivityResultLauncher<Intent> detailLauncher) {
        this.context = context;
        this.students = students;
        this.detailLauncher = detailLauncher;
    }

    @NonNull
    @Override
    public StudentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_students, parent, false);
        return new StudentsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentsViewHolder holder, int position) {
        Student student = this.students.get(position);
        holder.bind(student);

        holder.itemView.setOnClickListener(v -> {
            Intent detailIntent = new Intent(v.getContext(), DetailActivity.class);
            detailIntent.putExtra("id", student.getsId());
            detailIntent.putExtra("name", student.getsName());
            detailIntent.putExtra("address", student.getsAddress());
            detailIntent.putExtra("phone", student.getsPhoneNumber());
            detailIntent.putExtra("position", student.getPosition());
            detailIntent.putExtra("email", student.getEmail());
            detailIntent.putExtra("type", "student");

            detailLauncher.launch(detailIntent);
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public void updateData(List<Student> newStudents) {
        this.students = newStudents;
        notifyDataSetChanged();
    }

    public static class StudentsViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtEmail;
        private final TextView txtsname;
        private final TextView txtPhone;

        public StudentsViewHolder(@NonNull View itemView) {
            super(itemView);
            txtEmail = itemView.findViewById(R.id.txt_student_email);
            txtsname = itemView.findViewById(R.id.txt_student_name);
            txtPhone = itemView.findViewById(R.id.txt_student_phone);
        }

        public void bind(Student student) {
            txtEmail.setText(student.getEmail());
            txtsname.setText(student.getsName());
            txtPhone.setText(student.getsPhoneNumber());
        }
    }
}
