package com.example.contactapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.contactapplication.model.Staff;
import com.example.contactapplication.model.Unit;
import com.example.contactapplication.model.Student;
import com.example.contactapplication.utils.DatabaseHelper;

public class DetailActivity extends AppCompatActivity {
    private Button btnUpdate, btnDelete;
    private boolean isUnit, isStudent;
    private TextView txtContentId, txtContentName, txtContentAddress, txtContentPhone, txtContentPosition, txtContentEmail;
    private DatabaseHelper databaseHelper;

    private final ActivityResultLauncher<Intent> updateLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK) {
                            loadDataFromDatabase();
                        }
                    });

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtContentId = findViewById(R.id.txt_content_id);
        txtContentName = findViewById(R.id.txt_content_name);
        txtContentAddress = findViewById(R.id.txt_content_address);
        txtContentPhone = findViewById(R.id.txt_content_phone);
        txtContentPosition = findViewById(R.id.txt_content_position);
        txtContentEmail = findViewById(R.id.txt_content_email);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);
        databaseHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        if (intent != null) {
            txtContentId.setText(intent.getStringExtra("id"));
            txtContentName.setText(intent.getStringExtra("name"));
            txtContentPhone.setText(intent.getStringExtra("phone"));
            txtContentAddress.setText(intent.getStringExtra("address"));
            txtContentPosition.setText(intent.getStringExtra("position"));
            txtContentEmail.setText(intent.getStringExtra("email"));
            String type = intent.getStringExtra("type");
            isUnit = "unit".equals(type);
            isStudent = "student".equals(type);
        }

        btnUpdate.setOnClickListener(v -> editItem());

        btnDelete.setOnClickListener(v -> {
            if (isUnit) {
                deleteUnit();
            } else if (isStudent) {
                deleteStudent();
            } else {
                deleteStaff();
            }
        });

        txtContentPhone.setOnClickListener(v -> {
            Intent myIntent = new Intent(Intent.ACTION_DIAL);
            myIntent.setData(Uri.parse("tel:" + txtContentPhone.getText().toString()));
            startActivity(myIntent);
        });

        txtContentEmail.setOnClickListener(v -> {
            Intent myIntent = new Intent(Intent.ACTION_SENDTO);
            myIntent.setData(Uri.parse("mailto:" + txtContentEmail.getText().toString()));
            startActivity(myIntent);
        });
    }

    private void deleteUnit() {
        new AlertDialog.Builder(this)
                .setTitle("Xóa đơn vị")
                .setMessage("Bạn có chắc chắn muốn xóa đơn vị này không?")
                .setPositiveButton("Xóa", (dialog, which) -> {
                    databaseHelper.deleteUnit(txtContentId.getText().toString());
                    Toast.makeText(DetailActivity.this, "Đã xóa đơn vị", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                })
                .setNegativeButton("Hủy", null)
                .show();
    }

    private void deleteStaff() {
        new AlertDialog.Builder(this)
                .setTitle("Xóa nhân viên")
                .setMessage("Bạn có chắc chắn muốn xóa cán bộ này không?")
                .setPositiveButton("Xóa", (dialog, which) -> {
                    databaseHelper.deleteStaff(txtContentId.getText().toString());
                    Toast.makeText(DetailActivity.this, "Đã xóa cán bộ", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                })
                .setNegativeButton("Hủy", null)
                .show();
    }

    private void deleteStudent() {
        new AlertDialog.Builder(this)
                .setTitle("Xóa sinh viên")
                .setMessage("Bạn có chắc chắn muốn xóa sinh viên này không?")
                .setPositiveButton("Xóa", (dialog, which) -> {
                    databaseHelper.deleteStudent(txtContentId.getText().toString());
                    Toast.makeText(DetailActivity.this, "Đã xóa sinh viên", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                })
                .setNegativeButton("Hủy", null)
                .show();
    }

    private void editItem() {
        Intent intent = new Intent(this, UpdateActivity.class);
        intent.putExtra("id", txtContentId.getText().toString());
        intent.putExtra("name", txtContentName.getText().toString());
        intent.putExtra("phone", txtContentPhone.getText().toString());
        intent.putExtra("address", txtContentAddress.getText().toString());
        intent.putExtra("position", txtContentPosition.getText().toString());
        intent.putExtra("email", txtContentEmail.getText().toString());
        intent.putExtra("type", isUnit ? "unit" : isStudent ? "student" : "staff");
        setResult(RESULT_OK, intent);
        updateLauncher.launch(intent);
    }

    private void loadDataFromDatabase() {
        String id = txtContentId.getText().toString();
        if (isUnit) {
            Unit unit = databaseHelper.getUnitById(id);
            if (unit != null) {
                txtContentName.setText(unit.getuName());
                txtContentPhone.setText(unit.getuPhoneNumber());
                txtContentAddress.setText(unit.getuAddress());
                txtContentPosition.setText(unit.getPosition());
                txtContentEmail.setText(unit.getEmail());
            }
        } else if (isStudent) {
            Student student = databaseHelper.getStudentById(id);
            if (student != null) {
                txtContentName.setText(student.getsName());
                txtContentPhone.setText(student.getsPhoneNumber());
                txtContentAddress.setText(student.getsAddress());
                txtContentPosition.setText(student.getPosition());
                txtContentEmail.setText(student.getEmail());
            }
        } else {
            Staff staff = databaseHelper.getStaffById(id);
            if (staff != null) {
                txtContentName.setText(staff.getsName());
                txtContentPhone.setText(staff.getsPhoneNumber());
                txtContentAddress.setText(staff.getsAddress());
                txtContentPosition.setText(staff.getPosition());
                txtContentEmail.setText(staff.getEmail());
            }
        }
    }

    public void backAtivity(View view) {
        finish();
    }
}
