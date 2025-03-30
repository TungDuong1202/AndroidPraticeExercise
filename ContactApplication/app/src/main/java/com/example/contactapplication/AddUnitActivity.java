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

import com.example.contactapplication.model.Unit;
import com.example.contactapplication.utils.DatabaseHelper;

public class AddUnitActivity extends AppCompatActivity {
    private EditText edtName, edtPhone, edtAddress, edtPosition, edtEmail;
    private Button btnSave;
    private DatabaseHelper databaseHelper;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_unit);
        // Ánh xạ các thành phần
        edtName = findViewById(R.id.edt_unit_name);
        edtPhone = findViewById(R.id.edt_unit_phone);
        edtAddress = findViewById(R.id.edt_unit_address);
        edtPosition = findViewById(R.id.edt_unit_position);
        edtEmail = findViewById(R.id.edt_unit_email);
        btnSave = findViewById(R.id.btn_save_unit);

        databaseHelper = new DatabaseHelper(this);

        // Xử lý khi bấm nút Lưu
        btnSave.setOnClickListener(v -> saveUnit());
    }
    private void saveUnit() {
        String name = edtName.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();
        String position = edtPosition.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();

        if (name.isEmpty() || phone.isEmpty() || address.isEmpty() || position.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        Unit unit = new Unit(name, phone, address, position, email);
        databaseHelper.addUnit(unit);
        Toast.makeText(this, "Thêm đơn vị thành công!", Toast.LENGTH_SHORT).show();

        // Xóa dữ liệu sau khi thêm
        edtName.setText("");
        edtPhone.setText("");
        edtAddress.setText("");
        edtPosition.setText("");
        edtEmail.setText("");
    }
}