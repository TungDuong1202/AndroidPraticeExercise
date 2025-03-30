package com.example.contactapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactapplication.R;
import com.example.contactapplication.adapter.UnitsAdapter;
import com.example.contactapplication.model.Unit;
import com.example.contactapplication.utils.DatabaseHelper;

import java.util.List;

public class UnitsFragment extends Fragment {
    private RecyclerView rcvUnits;
    private SearchView svUnit;
    private DatabaseHelper databaseHelper;
    private UnitsAdapter unitsAdapter;
    private List<Unit> unitList;

    // ActivityResultLauncher để lắng nghe kết quả từ DetailActivity
    private final ActivityResultLauncher<Intent> detailLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == getActivity().RESULT_OK) {
                    refreshUnitList();
                }
            });

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_units, container, false);

        rcvUnits = view.findViewById(R.id.rcv_units);
        svUnit = view.findViewById(R.id.sv_units);
        databaseHelper = new DatabaseHelper(getContext());

        rcvUnits.setLayoutManager(new LinearLayoutManager(getContext()));
        if (databaseHelper.getAllUnits().isEmpty()) {
            addDefaultUnits();
        }

        // Lấy danh sách đơn vị từ database
        unitList = databaseHelper.getAllUnits();
        unitsAdapter = new UnitsAdapter(getContext(), unitList, detailLauncher);
        rcvUnits.setAdapter(unitsAdapter);

        setupSearchView();

        return view;
    }

    public void refreshUnitList() {
        unitList = databaseHelper.getAllUnits();
        unitsAdapter.updateData(unitList);
    }

    private void setupSearchView() {
        svUnit.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Unit> filteredList = databaseHelper.searchUnitsByName(newText);
                unitsAdapter.updateData(filteredList);
                return true;
            }
        });
    }
    private void addDefaultUnits() {
        databaseHelper.addUnit(new Unit("Phòng Kế toán",  "023456789","B5", "Phòng ban", "ketoan@example.com"));
        databaseHelper.addUnit(new Unit("Phòng Nhân sự",  "034567890","C6", "Phòng ban", "nhansu@example.com"));
        databaseHelper.addUnit(new Unit("Công nghệ thông tin",  "03456789024","B6", "Khoa", "CNTT@example.com"));

    }
}
