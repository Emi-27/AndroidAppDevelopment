package com.groupdev.pillpall.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.groupdev.pillpall.R;
import com.groupdev.pillpall.util.ShowList;

import java.util.List;


public class VitalsAdapter extends RecyclerView.Adapter<VitalsAdapter.ViewHolder> {

    private List<ShowList> vitals;
    private OnClickListener onClickListener;

    public VitalsAdapter(List<ShowList> vitals) {
        this.vitals = vitals;
    }

    public void setOnClickListener(OnClickListener listener) {
        this.onClickListener = listener;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.vitals_item, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.time.setText(vitals.get(position).getDateTime());
        viewHolder.measurement.setText(String.valueOf(vitals.get(position).getMeasurement()));
    }

    public int getItemCount() {
        if (vitals != null) {
            return vitals.size();
        } else {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView time, measurement;
        ImageView icon;
        ImageButton editM;

        ViewHolder(View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.textView_timeVital);
            measurement = itemView.findViewById(R.id.textView_measureVital);
            icon = itemView.findViewById(R.id.image_icon_vitals_item);
            editM = itemView.findViewById(R.id.imageButtonVitals_edit);

        }
    }

    public interface OnClickListener {
        void onClick(ShowList vitals);
    }
}