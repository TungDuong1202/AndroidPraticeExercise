package com.example.contactapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactapplication.R;
import com.example.contactapplication.adapter.StudentsAdapter;
import com.example.contactapplication.model.Student;
import com.example.contactapplication.utils.DatabaseHelper;

import java.util.List;

public class StudentsFragment extends Fragment {

    private RecyclerView rcvStudents;
    private SearchView svStudent;
    private Spinner spStudent;
    private DatabaseHelper databaseHelper;
    private StudentsAdapter studentsAdapter;
    private List<Student> studentList;

    // ActivityResultLauncher để xử lý kết quả từ DetailActivity
    private final ActivityResultLauncher<Intent> detailLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == getActivity().RESULT_OK) {
                    refreshStudentList();
                }
            });

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_students, container, false);

        rcvStudents = view.findViewById(R.id.rcv_students);
        svStudent = view.findViewById(R.id.sv_student);
        spStudent = view.findViewById(R.id.sp_student);
        databaseHelper = new DatabaseHelper(getContext());

        rcvStudents.setLayoutManager(new LinearLayoutManager(getContext()));

        if (databaseHelper.getAllStudents().isEmpty()) {
            insertData();
        }
        // Lấy danh sách sinh viên từ database
        studentList = databaseHelper.getAllStudents();
        studentsAdapter = new StudentsAdapter(getContext(), studentList, detailLauncher);
        rcvStudents.setAdapter(studentsAdapter);

        setupSpinner();
        setupSearchView();

        return view;
    }

    public void refreshStudentList() {
        studentList = databaseHelper.getAllStudents();
        studentsAdapter.updateData(studentList);
    }

    private void setupSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.sort_options,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spStudent.setAdapter(adapter);

        spStudent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    studentList = databaseHelper.getStudentsSortedAZ();
                } else {
                    studentList = databaseHelper.getStudentsSortedZA();
                }
                studentsAdapter.updateData(studentList);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void setupSearchView() {
        svStudent.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Student> filteredList = databaseHelper.searchStudentsByName(newText);
                studentsAdapter.updateData(filteredList);
                return true;
            }
        });
    }
    private void insertData(){
        databaseHelper.addStudent(new Student("Nguyễn Văn C", "0131232123", "Hà Nội","64CNTT1", "c@gmail.com"));
        databaseHelper.addStudent(new Student("Lê Thị D", "0132131", "TP HCM","64CNTT2", "d@example.com"));
    }
}
