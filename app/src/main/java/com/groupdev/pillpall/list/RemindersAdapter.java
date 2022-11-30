package com.groupdev.pillpall.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.groupdev.pillpall.R;
import com.groupdev.pillpall.model.Reminder;

import java.util.List;

public class RemindersAdapter extends RecyclerView.Adapter<RemindersAdapter.RemindersViewHolder> {

    private List<Reminder> reminders;
    private OnReminderClickListener listener;
    private OnRemoveReminderClickListener removeListener;

    public RemindersAdapter() {
        this.reminders = reminders;
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
        holder.reminderName.setText(reminder.getName());
        holder.reminderTime.setText(reminder.getDescription());

        holder.reminderTaken.setChecked(reminders.get(position).isTaken());

        if(reminders.get(position).isActive()){
            holder.reminderImage.setImageResource(R.drawable.ic_notifications_active);
        }
        else{
            holder.reminderImage.setImageResource(R.drawable.ic_notifications);
        }

    }

    @Override
    public int getItemCount() {
        if(reminders != null){
            return reminders.size();
        }
        else {
            return 0;
        }
    }

    public void setOnClickListener(OnReminderClickListener<Reminder> listener) {
        this.reminders = reminders;
        notifyDataSetChanged();
    }


    public class RemindersViewHolder extends RecyclerView.ViewHolder {
        private TextView reminderName;
        private TextView reminderTime;
        private CheckBox reminderTaken;
        private ImageView reminderImage;

        public RemindersViewHolder(View itemView) {
            super(itemView);
            reminderName = itemView.findViewById(R.id.name_medicationName_reminder);
            reminderTime = itemView.findViewById(R.id.item_time_reminder);
            reminderTaken = itemView.findViewById(R.id.checkBox_item_reminder);
            reminderImage = itemView.findViewById(R.id.image_item_reminder);
        }
    }

    public interface OnReminderClickListener<T> {
        void onReminderClick(Reminder reminder);
    }

    public interface OnRemoveReminderClickListener {
        void onRemoveReminderClick(Reminder reminder);
    }
}
