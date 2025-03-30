package com.example.contactapplication.adapter;

import android.app.Activity;
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
import com.example.contactapplication.model.Staff;

import java.util.List;

public class StaffsAdapter extends RecyclerView.Adapter<StaffsAdapter.StaffsViewHolder> {
    private List<Staff> staff;
    private final ActivityResultLauncher<Intent> detailLauncher;
    private final Context context;

    public StaffsAdapter(Context context, List<Staff> staff, ActivityResultLauncher<Intent> detailLauncher) {
        this.context = context;
        this.staff = staff;
        this.detailLauncher = detailLauncher;
    }

    @NonNull
    @Override
    public StaffsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_staffs, parent, false);
        return new StaffsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StaffsViewHolder holder, int position) {
        Staff staff = this.staff.get(position);
        holder.bind(staff);

        holder.itemView.setOnClickListener(v -> {
            Intent detailIntent = new Intent(v.getContext(), DetailActivity.class);
            detailIntent.putExtra("id", staff.getsId());
            detailIntent.putExtra("name", staff.getsName());
            detailIntent.putExtra("address", staff.getsAddress());
            detailIntent.putExtra("phone", staff.getsPhoneNumber());
            detailIntent.putExtra("position", staff.getPosition());
            detailIntent.putExtra("email", staff.getEmail());
            detailIntent.putExtra("type", "staff");

            // Sử dụng detailLauncher để lắng nghe kết quả từ DetailActivity
            detailLauncher.launch(detailIntent);
        });
    }

    @Override
    public int getItemCount() {
        return staff.size();
    }

    public void updateData(List<Staff> newStaff) {
        this.staff = newStaff;
        notifyDataSetChanged();
    }

    public static class StaffsViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtEmail;
        private final TextView txtsname;
        private final TextView txtPhone;

        public StaffsViewHolder(@NonNull View itemView) {
            super(itemView);
            txtEmail = itemView.findViewById(R.id.txt_semail);
            txtsname = itemView.findViewById(R.id.txt_sname);
            txtPhone = itemView.findViewById(R.id.txt_sphone);
        }

        public void bind(Staff staff) {
            txtEmail.setText(staff.getEmail());
            txtsname.setText(staff.getsName());
            txtPhone.setText(staff.getsPhoneNumber());
        }
    }
}
