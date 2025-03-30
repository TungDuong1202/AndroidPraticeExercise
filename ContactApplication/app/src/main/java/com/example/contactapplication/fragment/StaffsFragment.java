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
import com.example.contactapplication.adapter.StaffsAdapter;
import com.example.contactapplication.model.Staff;
import com.example.contactapplication.utils.DatabaseHelper;

import java.util.List;

public class StaffsFragment extends Fragment {

    private RecyclerView rcvStaffs;
    private SearchView svStaff;
    private Spinner spStaff;
    private DatabaseHelper databaseHelper;
    private StaffsAdapter staffsAdapter;
    private List<Staff> staffList;

    // Tạo ActivityResultLauncher để xử lý kết quả từ DetailActivity
    private final ActivityResultLauncher<Intent> detailLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == getActivity().RESULT_OK) {
                    refreshStaffList();
                }
            });

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_staffs, container, false);

        rcvStaffs = view.findViewById(R.id.rcv_staffs);
        svStaff = view.findViewById(R.id.sv_staff);
        spStaff = view.findViewById(R.id.sp_staff);
        databaseHelper = new DatabaseHelper(getContext());

        rcvStaffs.setLayoutManager(new LinearLayoutManager(getContext()));

        if (databaseHelper.getAllStaffs().isEmpty()) {
            insertData();
        }
        // Lấy danh sách nhân viên từ database
        staffList = databaseHelper.getAllStaffs();
        staffsAdapter = new StaffsAdapter(getContext(), staffList, detailLauncher);
        rcvStaffs.setAdapter(staffsAdapter);

        setupSpinner();
        setupSearchView();

        return view;
    }

    public void refreshStaffList() {
        staffList = databaseHelper.getAllStaffs();
        staffsAdapter.updateData(staffList);
    }

    private void setupSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.sort_options,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spStaff.setAdapter(adapter);

        spStaff.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    staffList = databaseHelper.getStaffsSortedAZ();
                } else {
                    staffList = databaseHelper.getStaffsSortedZA();
                }
                staffsAdapter.updateData(staffList);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void setupSearchView() {
        svStaff.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Staff> filteredList = databaseHelper.searchStaffsByName(newText);
                staffsAdapter.updateData(filteredList);
                return true;
            }
        });
    }
    private void insertData(){
        databaseHelper.addStaff(new Staff("Nguyễn Văn A",  "0123456789","Hà Nội", "Quản lý", "a@example.com"));
        databaseHelper.addStaff(new Staff("Trần Thị B",  "0987654321","TP HCM", "Nhân viên", "b@example.com"));
    }
}
