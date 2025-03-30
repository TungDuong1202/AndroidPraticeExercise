package com.example.contactapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.contactapplication.model.Student;
import com.example.contactapplication.utils.DatabaseHelper;

public class AddStudentActivity extends AppCompatActivity {
    private EditText edtName, edtPhone, edtAddress, edtEmail, edtClass;
    private Button btnSave;
    private DatabaseHelper databaseHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_student);

        // Ánh xạ các thành phần
        edtName = findViewById(R.id.edt_student_name);
        edtPhone = findViewById(R.id.edt_student_phone);
        edtAddress = findViewById(R.id.edt_student_address);
        edtEmail = findViewById(R.id.edt_student_email);
        edtClass = findViewById(R.id.edt_student_class);
        btnSave = findViewById(R.id.btn_save_student);

        databaseHelper = new DatabaseHelper(this);

        // Xử lý khi bấm nút Lưu
        btnSave.setOnClickListener(v -> saveStudent());
    }

    private void saveStudent() {
        String name = edtName.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String studentClass = edtClass.getText().toString().trim();

        if (name.isEmpty() || phone.isEmpty() || address.isEmpty() || email.isEmpty() || studentClass.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        Student student = new Student(name, phone, address, studentClass,email);
        databaseHelper.addStudent(student);
        Toast.makeText(this, "Thêm Sinh viên thành công!", Toast.LENGTH_SHORT).show();

        // Xóa dữ liệu sau khi thêm
        edtName.setText("");
        edtPhone.setText("");
        edtAddress.setText("");
        edtEmail.setText("");
        edtClass.setText("");
    }
}