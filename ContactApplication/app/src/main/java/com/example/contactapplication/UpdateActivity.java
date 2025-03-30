package com.example.contactapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.contactapplication.model.Staff;
import com.example.contactapplication.model.Unit;
import com.example.contactapplication.model.Student;
import com.example.contactapplication.utils.DatabaseHelper;

public class UpdateActivity extends AppCompatActivity {
    private String id;
    private String type;
    private EditText edtName, edtPhone, edtAddress, edtPosition, edtEmail;
    private Button btnUpdate;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update);

        edtName = findViewById(R.id.edt_staff_name);
        edtPhone = findViewById(R.id.edt_staff_phone);
        edtAddress = findViewById(R.id.edt_staff_address);
        edtPosition = findViewById(R.id.edt_staff_position);
        edtEmail = findViewById(R.id.edt_staff_email);
        btnUpdate = findViewById(R.id.btn_save_staff);

        databaseHelper = new DatabaseHelper(this);

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getStringExtra("id");
            edtName.setText(intent.getStringExtra("name"));
            edtPhone.setText(intent.getStringExtra("phone"));
            edtAddress.setText(intent.getStringExtra("address"));
            edtPosition.setText(intent.getStringExtra("position"));
            edtEmail.setText(intent.getStringExtra("email"));
            type = intent.getStringExtra("type");
        }

        btnUpdate.setOnClickListener(v -> updateItem());
    }

    private void updateItem() {
        String name = edtName.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();
        String position = edtPosition.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();

        if (name.isEmpty() || phone.isEmpty() || address.isEmpty() || position.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        if ("unit".equals(type)) {
            Unit unit = new Unit(id, name, phone, address, position, email);
            databaseHelper.updateUnit(unit);
            Toast.makeText(this, "Sửa Đơn vị thành công!", Toast.LENGTH_SHORT).show();
        } else if ("staff".equals(type)) {
            Staff staff = new Staff(id, name, phone, address, position, email);
            databaseHelper.updateStaff(staff);
            Toast.makeText(this, "Sửa Cán bộ thành công!", Toast.LENGTH_SHORT).show();
        } else if ("student".equals(type)) {
            Student student = new Student(id, name, phone, address, position, email);
            databaseHelper.updateStudent(student);
            Toast.makeText(this, "Sửa Sinh viên thành công!", Toast.LENGTH_SHORT).show();
        }
        setResult(RESULT_OK);
        finish();
    }
}
