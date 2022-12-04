package com.groupdev.pillpall.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.groupdev.pillpall.R;
import com.groupdev.pillpall.model.Reminder;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RemindersAdapter extends RecyclerView.Adapter<RemindersAdapter.RemindersViewHolder> {

    private List<Reminder> reminders;
    private OnClickListener onClickListener;

    public RemindersAdapter(List<Reminder> reminders) {
        this.reminders = reminders;
    }

    public void setOnClickListener(OnClickListener listener) {
        this.onClickListener = listener;
    }

    public void setReminders(List<Reminder> reminders) {
        this.reminders = reminders;
        notifyDataSetChanged();
    }

    @Override
    public RemindersAdapter.RemindersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item_reminder, parent, false);
        return new RemindersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RemindersViewHolder holder, int position) {
        Reminder reminder = reminders.get(position);


        holder.reminderName.setText(reminder.getMedicationName());
        holder.reminderTime.setText((String.format("%d", reminder.getTime())));

        holder.reminderTaken.setChecked(reminders.get(position).isTaken());

        if (reminders.get(position).isActive()) {
            holder.reminderImageButton.setImageResource(R.drawable.ic_notification_active);
        } else {
            holder.reminderImageButton.setImageResource(R.drawable.ic_notification);
        }

        holder.reminderTaken.setChecked(reminders.get(position).isTaken());

        holder.reminderImageButton.setOnClickListener(v -> {
            if (reminders.get(position).isActive()) {
                reminders.get(position).setActive(false);
                holder.reminderImageButton.setImageResource(R.drawable.ic_notification);
            } else {
                reminders.get(position).setActive(true);
                holder.reminderImageButton.setImageResource(R.drawable.ic_notification_active);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (reminders != null) {
            return reminders.size();
        } else {
            return 0;
        }
    }

    public class RemindersViewHolder extends RecyclerView.ViewHolder{
        private TextView reminderName, reminderTime;
        private CheckBox reminderTaken;
        private ImageButton reminderImageButton, editImgBut;


        public RemindersViewHolder(View itemView) {
            super(itemView);
            reminderName = itemView.findViewById(R.id.name_medicationName_reminder);
            reminderTime = itemView.findViewById(R.id.item_time_reminder);
            reminderTaken = itemView.findViewById(R.id.checkBox_item_reminder);
            reminderImageButton = itemView.findViewById(R.id.imageButton_item_reminder);
            editImgBut = itemView.findViewById(R.id.imageButton_edit);

            editImgBut.setOnClickListener(v ->
                    onClickListener.onClick(reminders.get(getBindingAdapterPosition())));
        }
    }
    public interface OnClickListener {
        void onClick(Reminder reminder);

    }

    public interface OnActiveClickListener {
        void onClick(Reminder reminder);
    }

    public interface OnTakenClickListener {
        void onClick(Reminder reminder);
    }

}
