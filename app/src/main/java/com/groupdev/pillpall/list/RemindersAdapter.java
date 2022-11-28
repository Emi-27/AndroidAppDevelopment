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

    public RemindersAdapter(List<Reminder> reminders) {
        this.reminders = reminders;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RemindersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reminder_item, parent, false);
        return new RemindersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RemindersViewHolder holder, int position) {
        holder.reminderName.setText(reminders.get(position).getName());
        holder.reminderTime.setText(reminders.get(position).getStartTime());
        if(reminders.get(position).isTaken()){
            holder.reminderTaken.setChecked(true);
        }
        else{
            holder.reminderTaken.setChecked(false);
        }

        if(reminders.get(position).isActive()){
            holder.reminderImage.setImageResource(R.drawable.ic_notifications_active_24);
        }
        else{
            holder.reminderImage.setImageResource(R.drawable.ic_baseline_notifications_24);
        }

    }

    @Override
    public int getItemCount() {
        return reminders.size();
    }

    public void setReminders(List<Reminder> reminders) {
        this.reminders = reminders;
        notifyDataSetChanged();
    }

    public Reminder getReminderAt(int position) {
        return reminders.get(position);
    }

    public class RemindersViewHolder extends RecyclerView.ViewHolder {
        TextView reminderName;
        TextView reminderTime;
        CheckBox reminderTaken;
        ImageView reminderImage;

        public RemindersViewHolder(@NonNull View itemView) {
            super(itemView);
            reminderName = itemView.findViewById(R.id.name_item_reminder);
            reminderTime = itemView.findViewById(R.id.time_item_reminder);
            reminderTaken = itemView.findViewById(R.id.checkBox_item_reminder);
            reminderImage = itemView.findViewById(R.id.image_item_reminder);
        }

        public void bind(final Reminder reminder, final OnReminderClickListener listener) {


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onReminderClick(reminder);
                }
            });
        }
    }

    public interface OnReminderClickListener {
        void onReminderClick(Reminder reminder);
    }
}
