package com.groupdev.pillpall.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.groupdev.pillpall.R;
import com.groupdev.pillpall.model.Medication;

import java.util.List;

public class MedicationAdapter extends RecyclerView.Adapter<MedicationAdapter.MedicationsViewHolder> {

    private List<Medication> medications;
    private OnClickListener listener;

    public MedicationAdapter(List<Medication> medications){
        this.medications = medications;
    }

    public void setOnClickListener(OnClickListener listener){
        this.listener = listener;
    }


    @NonNull
    @Override
    public MedicationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.medication_item, parent, false);
        return new MedicationsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicationsViewHolder holder, int position) {
        holder.name.setText(medications.get(position).getName());
        holder.strength.setText(String.valueOf(medications.get(position).getDosage()));
        holder.units.setText(String.valueOf(medications.get(position).getUnitOfDosage()));

    }

    @Override
    public int getItemCount() {
        return medications.size();
    }

    class MedicationsViewHolder extends RecyclerView.ViewHolder{
        TextView name, strength, units;

        MedicationsViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.medication_name);
            strength = itemView.findViewById(R.id.medication_units);
            units = itemView.findViewById(R.id.medication_strength);

            itemView.setOnClickListener(l -> {
                listener.onClick(medications.get(getBindingAdapterPosition()));
            });
        }
    }

    public interface OnClickListener{
        void onClick(Medication medication);
    }

    public void setMedications(List<Medication> medications){
        this.medications = medications;
        notifyDataSetChanged();
    }

}
