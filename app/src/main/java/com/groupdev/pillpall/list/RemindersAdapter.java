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

import java.util.List;

public class RemindersAdapter extends RecyclerView.Adapter<RemindersAdapter.RemindersViewHolder> {

    private List<Reminder> reminders;
    private OnClickListener onClickListener;

    public RemindersAdapter(List<Reminder> reminders, OnClickListener onClickListener){
        this.reminders = reminders;
        this.onClickListener = onClickListener;
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

        String time = String.format("%04d", reminder.getTime());
        String timeFormat = time.charAt(0)+""+time.charAt(1)+":"+time.charAt(2)+""+time.charAt(3);
        holder.reminderTime.setText(timeFormat);
        holder.reminderName.setText(reminder.getMedicationName());

        holder.reminderTaken.setChecked(reminders.get(position).isTaken());

        if (reminders.get(position).isActive()) {
            holder.reminderImageButton.setImageResource(R.drawable.ic_notification_active);
        } else {
            holder.reminderImageButton.setImageResource(R.drawable.ic_notification);
        }

        holder.reminderTaken.setChecked(reminders.get(position).isTaken());
    }

    @Override
    public int getItemCount() {
        if (reminders != null) {
            return reminders.size();
        } else {
            return 0;
        }
    }

    public class RemindersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView reminderName, reminderTime;
        private CheckBox reminderTaken;
        private ImageButton reminderImageButton, editImgBut;


        public RemindersViewHolder(View itemView) {
            super(itemView);
            reminderName = itemView.findViewById(R.id.name_medicationName_reminder);
            reminderTime = itemView.findViewById(R.id.item_time_reminder);
            reminderTaken = itemView.findViewById(R.id.checkBox_item_reminder);
            reminderImageButton = itemView.findViewById(R.id.button_rem_notif);
            editImgBut = itemView.findViewById(R.id.imageButton_edit);

            editImgBut.setOnClickListener(this);
            reminderTaken.setOnClickListener(this);
            reminderImageButton.setOnClickListener(this);

            }

        @Override
        public void onClick(View v) {
            if (v.getId() == editImgBut.getId()) {
                onClickListener.onClick(reminders.get(getBindingAdapterPosition()));
                notifyDataSetChanged();
            } else if(v.getId() == reminderTaken.getId()) {
                onClickListener.onTake(reminders.get(getBindingAdapterPosition()));
                notifyDataSetChanged();
            } else if (v.getId() == reminderImageButton.getId()) {
                onClickListener.onActive(reminders.get(getBindingAdapterPosition()));
                notifyDataSetChanged();
            }
        }
    }

    public interface OnClickListener {
        void onClick(Reminder reminder);

        void onTake(Reminder reminder);

        void onActive(Reminder reminder);

    }

}
