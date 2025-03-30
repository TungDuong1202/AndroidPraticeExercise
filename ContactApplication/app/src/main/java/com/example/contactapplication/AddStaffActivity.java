package com.example.contactapplication;

import static java.security.AccessController.getContext;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.contactapplication.model.Staff;
import com.example.contactapplication.model.Unit;
import com.example.contactapplication.utils.DatabaseHelper;

public class AddStaffActivity extends AppCompatActivity {
    private EditText edtName, edtPhone, edtAddress, edtPosition, edtEmail;
    private Button btnSave;
    private DatabaseHelper databaseHelper;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_staff);
        // Ánh xạ các thành phần
        edtName = findViewById(R.id.edt_staff_name);
        edtPhone = findViewById(R.id.edt_staff_phone);
        edtAddress = findViewById(R.id.edt_staff_address);
        edtPosition = findViewById(R.id.edt_staff_position);
        edtEmail = findViewById(R.id.edt_staff_email);
        btnSave = findViewById(R.id.btn_save_staff);

        databaseHelper = new DatabaseHelper(this);

        // Xử lý khi bấm nút Lưu
        btnSave.setOnClickListener(v -> saveStaff());
    }
    private void saveStaff() {
        String name = edtName.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();
        String position = edtPosition.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();

        if (name.isEmpty() || phone.isEmpty() || address.isEmpty() || position.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        Staff staff = new Staff(name, phone, address, position, email);
        databaseHelper.addStaff(staff);
        Toast.makeText(this, "Thêm Cán bộ thành công!", Toast.LENGTH_SHORT).show();

        // Xóa dữ liệu sau khi thêm
        edtName.setText("");
        edtPhone.setText("");
        edtAddress.setText("");
        edtPosition.setText("");
        edtEmail.setText("");
    }
}