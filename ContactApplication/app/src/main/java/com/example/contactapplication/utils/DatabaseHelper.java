package com.example.contactapplication.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.contactapplication.model.Staff;
import com.example.contactapplication.model.Student;
import com.example.contactapplication.model.Unit;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "TLUContact";
    private static final int DATABASE_VERSION = 1;
    // Table Staffs
    private static final String TABLE_STAFFS = "staffs";
    private static final String COLUMN_STAFF_ID = "id";
    private static final String COLUMN_STAFF_NAME = "name";
    private static final String COLUMN_STAFF_PHONE = "phone";
    private static final String COLUMN_STAFF_ADDRESS = "address";
    private static final String COLUMN_STAFF_POSITION = "position";
    private static final String COLUMN_STAFF_EMAIL = "email";

    // Table Units
    private static final String TABLE_UNITS = "units";
    private static final String COLUMN_UNIT_ID = "id";
    private static final String COLUMN_UNIT_NAME = "name";
    private static final String COLUMN_UNIT_PHONE = "phone";
    private static final String COLUMN_UNIT_ADDRESS = "address";
    private static final String COLUMN_UNIT_POSITION = "position";
    private static final String COLUMN_UNIT_EMAIL = "email";
    // Table Students
    private static final String TABLE_STUDENTS = "students";
    private static final String COLUMN_STUDENT_ID = "id";
    private static final String COLUMN_STUDENT_NAME = "name";
    private static final String COLUMN_STUDENT_PHONE = "phone";
    private static final String COLUMN_STUDENT_POSITION = "position";
    private static final String COLUMN_STUDENT_ADDRESS = "address";
    private static final String COLUMN_STUDENT_EMAIL = "email";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng Staffs
        String CREATE_STAFF_TABLE = "CREATE TABLE " + TABLE_STAFFS + " (" +
                COLUMN_STAFF_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_STAFF_NAME + " TEXT, " +
                COLUMN_STAFF_PHONE + " TEXT, " +
                COLUMN_STAFF_ADDRESS + " TEXT, " +
                COLUMN_STAFF_POSITION + " TEXT," +
                COLUMN_STAFF_EMAIL + " TEXT)";
        db.execSQL(CREATE_STAFF_TABLE);
        // Tạo bảng Units
        String CREATE_UNIT_TABLE = "CREATE TABLE " + TABLE_UNITS + " (" +
                COLUMN_UNIT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_UNIT_NAME + " TEXT, " +
                COLUMN_UNIT_PHONE + " TEXT, " +
                COLUMN_UNIT_ADDRESS + " TEXT, " +
                COLUMN_UNIT_POSITION + " TEXT," +
                COLUMN_UNIT_EMAIL + " TEXT)";
        db.execSQL(CREATE_UNIT_TABLE);
        // Tạo bảng Students
        String CREATE_STUDENT_TABLE = "CREATE TABLE " + TABLE_STUDENTS + " (" +
                COLUMN_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_STUDENT_NAME + " TEXT, " +
                COLUMN_STUDENT_PHONE + " TEXT, " +
                COLUMN_STUDENT_ADDRESS + " TEXT, " +
                COLUMN_STUDENT_POSITION + " TEXT," +
                COLUMN_STUDENT_EMAIL + " TEXT)";
        db.execSQL(CREATE_STUDENT_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STAFFS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UNITS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        onCreate(db);
    }
    // Staff
    public void addStaff(Staff staff) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_STAFF_NAME, staff.getsName());
        values.put(COLUMN_STAFF_PHONE, staff.getsPhoneNumber());
        values.put(COLUMN_STAFF_ADDRESS, staff.getsAddress());
        values.put(COLUMN_STAFF_POSITION, staff.getPosition());
        values.put(COLUMN_STAFF_EMAIL, staff.getEmail());
        db.insert(TABLE_STAFFS, null, values);
        db.close();
    }
    public List<Staff> getAllStaffs() {
        List<Staff> staffList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STAFFS, null);

        if (cursor.moveToFirst()) {
            do {
                Staff staff = new Staff(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)
                );
                staffList.add(staff);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return staffList;
    }
    // STAFF
    public List<Staff> searchStaffsByName(String keyword) {
        List<Staff> staffList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STAFFS + " WHERE " + COLUMN_STAFF_NAME + " LIKE ?", new String[]{"%" + keyword + "%"});

        if (cursor.moveToFirst()) {
            do {
                Staff staff = new Staff(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)
                );
                staffList.add(staff);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return staffList;
    }
    // Lấy danh sách Unit sắp xếp theo A-Z
    public List<Staff> getStaffsSortedAZ() {
        List<Staff> staffList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STAFFS + " ORDER BY " + COLUMN_STAFF_NAME + " ASC", null);
        if (cursor.moveToFirst()) {
            do {
                Staff staff = new Staff(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)
                );
                staffList.add(staff);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return staffList;
    }

    // Lấy danh sách Unit sắp xếp theo Z-A
    public List<Staff> getStaffsSortedZA() {
        List<Staff> staffList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STAFFS + " ORDER BY " + COLUMN_STAFF_NAME + " DESC", null);
        if (cursor.moveToFirst()) {
            do {
                Staff staff = new Staff(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)
                );
                staffList.add(staff);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return staffList;
    }
    // update staff
    public void updateStaff(Staff staff) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", staff.getsName());
        values.put("phone", staff.getsPhoneNumber());
        values.put("address", staff.getsAddress());
        values.put("position", staff.getPosition());
        values.put("email", staff.getEmail());

        db.update("staffs", values, "id = ?", new String[]{String.valueOf(staff.getsId())});
        db.close();
    }

    public void deleteStaff(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("staffs", "id = ?", new String[]{id});
        db.close();
    }
    public Staff getStaffById(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Staff staff = null;

        Cursor cursor = db.query("staffs",
                new String[]{"id", "name", "phone", "address", "position", "email"},
                "id = ?", new String[]{id},
                null, null, null);

        if (cursor.moveToFirst()) {
            staff = new Staff(
                    cursor.getString(0), // id
                    cursor.getString(1), // name
                    cursor.getString(2), // phone
                    cursor.getString(3), // address
                    cursor.getString(4), // position
                    cursor.getString(5)  // email
            );
            cursor.close();
        }

        db.close();
        return staff;
    }


    //UNIT
    public void addUnit(Unit unit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_UNIT_NAME, unit.getuName());
        values.put(COLUMN_UNIT_PHONE, unit.getuPhoneNumber());
        values.put(COLUMN_UNIT_ADDRESS, unit.getuAddress());
        values.put(COLUMN_UNIT_POSITION, unit.getPosition());
        values.put(COLUMN_UNIT_EMAIL, unit.getEmail());

        db.insert(TABLE_UNITS, null, values);
        db.close();
    }

    public List<Unit> getAllUnits() {
        List<Unit> unitList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_UNITS, null);

        if (cursor.moveToFirst()) {
            do {
                Unit unit = new Unit(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)
                );
                unitList.add(unit);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return unitList;
    }
    // Tìm kiếm Unit theo tên
    public List<Unit> searchUnitsByName(String keyword) {
        List<Unit> unitList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_UNITS + " WHERE " + COLUMN_UNIT_NAME + " LIKE ?", new String[]{"%" + keyword + "%"});

        if (cursor.moveToFirst()) {
            do {
                Unit unit = new Unit(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)
                );
                unitList.add(unit);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return unitList;
    }
    // Lấy danh sách Unit sắp xếp theo A-Z
    public List<Unit> getUnitsSortedAZ() {
        List<Unit> unitList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_UNITS + " ORDER BY " + COLUMN_UNIT_NAME + " ASC", null);
        if (cursor.moveToFirst()) {
            do {
                Unit unit = new Unit(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)
                );
                unitList.add(unit);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return unitList;
    }

    // Lấy danh sách Unit sắp xếp theo Z-A
    public List<Unit> getUnitsSortedZA() {
        List<Unit> unitList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_UNITS + " ORDER BY " + COLUMN_UNIT_NAME + " DESC", null);
        if (cursor.moveToFirst()) {
            do {
                Unit unit = new Unit(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)
                );
                unitList.add(unit);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return unitList;
    }
    // Sửa đơn vị theo id
    public void updateUnit(Unit unit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", unit.getuName());
        values.put("phone", unit.getuPhoneNumber());
        values.put("address", unit.getuAddress());
        values.put("position", unit.getPosition());
        values.put("email", unit.getEmail());

        db.update("units", values, "id = ?", new String[]{String.valueOf(unit.getuId())});
        db.close();
    }

    public void deleteUnit(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("units", "id = ?", new String[]{id});
        db.close();
    }
    public Unit getUnitById(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Unit unit = null;

        Cursor cursor = db.query("units",
                new String[]{"id", "name", "phone", "address", "position", "email"},
                "id = ?", new String[]{id},
                null, null, null);

        if (cursor.moveToFirst()) {
            unit = new Unit(
                    cursor.getString(0), // id
                    cursor.getString(1), // name
                    cursor.getString(2), // phone
                    cursor.getString(3), // address
                    cursor.getString(4), // position
                    cursor.getString(5)  // email
            );
            cursor.close();
        }

        db.close();
        return unit;
    }

    //STUDENT

    public void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_STUDENT_NAME, student.getsName());
        values.put(COLUMN_STUDENT_PHONE, student.getsPhoneNumber());
        values.put(COLUMN_STUDENT_ADDRESS, student.getsAddress());
        values.put(COLUMN_STUDENT_POSITION, student.getPosition());
        values.put(COLUMN_STUDENT_EMAIL, student.getEmail());
        db.insert(TABLE_STUDENTS, null, values);
        db.close();
    }

    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STUDENTS, null);

        if (cursor.moveToFirst()) {
            do {
                Student student = new Student(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)
                );
                studentList.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return studentList;
    }

    public List<Student> searchStudentsByName(String keyword) {
        List<Student> studentList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STUDENTS + " WHERE " + COLUMN_STUDENT_NAME + " LIKE ?", new String[]{"%" + keyword + "%"});

        if (cursor.moveToFirst()) {
            do {
                Student student = new Student(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)
                );
                studentList.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return studentList;
    }

    public List<Student> getStudentsSortedAZ() {
        List<Student> studentList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STUDENTS + " ORDER BY " + COLUMN_STUDENT_NAME + " ASC", null);
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)
                );
                studentList.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return studentList;
    }

    public List<Student> getStudentsSortedZA() {
        List<Student> studentList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STUDENTS + " ORDER BY " + COLUMN_STUDENT_NAME + " DESC", null);
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)
                );
                studentList.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return studentList;
    }

    public void updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", student.getsName());
        values.put("phone", student.getsPhoneNumber());
        values.put("address", student.getsAddress());
        values.put("position", student.getPosition());
        values.put("email", student.getEmail());

        db.update("students", values, "id = ?", new String[]{String.valueOf(student.getsId())});
        db.close();
    }

    public void deleteStudent(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("students", "id = ?", new String[]{id});
        db.close();
    }

    public Student getStudentById(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Student student = null;

        Cursor cursor = db.query("students",
                new String[]{"id", "name", "phone", "address", "position", "email"},
                "id = ?", new String[]{id},
                null, null, null);

        if (cursor.moveToFirst()) {
            student = new Student(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5)
            );
            cursor.close();
        }
        db.close();
        return student;
    }
}
