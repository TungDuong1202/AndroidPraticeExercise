package com.example.contactapplication.adapter;

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
import com.example.contactapplication.model.Unit;

import java.util.List;

public class UnitsAdapter extends RecyclerView.Adapter<UnitsAdapter.UnitsViewHolder> {
    private List<Unit> units;
    private final ActivityResultLauncher<Intent> detailLauncher;
    private final Context context;

    public UnitsAdapter(Context context, List<Unit> units, ActivityResultLauncher<Intent> detailLauncher) {
        this.context = context;
        this.units = units;
        this.detailLauncher = detailLauncher;
    }

    @NonNull
    @Override
    public UnitsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_units, parent, false);
        return new UnitsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UnitsViewHolder holder, int position) {
        Unit unit = units.get(position);
        holder.bind(unit);

        holder.itemView.setOnClickListener(v -> {
            Intent detailIntent = new Intent(v.getContext(), DetailActivity.class);
            detailIntent.putExtra("id", unit.getuId());
            detailIntent.putExtra("name", unit.getuName());
            detailIntent.putExtra("address", unit.getuAddress());
            detailIntent.putExtra("phone", unit.getuPhoneNumber());
            detailIntent.putExtra("position", unit.getPosition());
            detailIntent.putExtra("email", unit.getEmail());
            detailIntent.putExtra("type", "unit");

            // Dùng detailLauncher để mở DetailActivity và nhận kết quả
            detailLauncher.launch(detailIntent);
        });
    }

    @Override
    public int getItemCount() {
        return units.size();
    }

    public void updateData(List<Unit> newUnits) {
        this.units = newUnits;
        notifyDataSetChanged();
    }

    public static class UnitsViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtUemail;
        private final TextView txtUname;
        private final TextView txtPhone;

        public UnitsViewHolder(@NonNull View itemView) {
            super(itemView);
            txtUemail = itemView.findViewById(R.id.txt_uemail);
            txtUname = itemView.findViewById(R.id.txt_uname);
            txtPhone = itemView.findViewById(R.id.txt_uphone);
        }

        public void bind(Unit unit) {
            txtUemail.setText(unit.getEmail());
            txtUname.setText(unit.getuName());
            txtPhone.setText(unit.getuPhoneNumber());
        }
    }
}
