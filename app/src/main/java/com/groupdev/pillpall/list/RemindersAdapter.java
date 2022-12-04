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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RemindersAdapter extends RecyclerView.Adapter<RemindersAdapter.RemindersViewHolder> {

    private List<Reminder> reminders;
    final private OnClickListener onClickListener;

    public RemindersAdapter(OnClickListener listener) {
        reminders = new ArrayList<>();
        onClickListener = listener;
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
        String time = new SimpleDateFormat("HH:MM").format(reminder.getTime());
        holder.reminderName.setText(reminder.getMedicationName());
        holder.reminderTime.setText(time);

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

    public class RemindersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final private TextView reminderName, reminderTime;
        final private CheckBox reminderTaken;
        final private ImageButton reminderImageButton, editImgBut;


        public RemindersViewHolder(View itemView) {
            super(itemView);
            reminderName = itemView.findViewById(R.id.name_medicationName_reminder);
            reminderTime = itemView.findViewById(R.id.item_time_reminder);
            reminderTaken = itemView.findViewById(R.id.checkBox_item_reminder);
            reminderImageButton = itemView.findViewById(R.id.imageButton_item_reminder);
            editImgBut = itemView.findViewById(R.id.imageButton_edit);
        }

        @Override
        public void onClick(View v) {

            if (v == editImgBut) {
                onClickListener.onEditIconClick(reminders.get(getBindingAdapterPosition()));
                notifyDataSetChanged();
            } else if (v == reminderTaken) {
                onClickListener.onActiveIconClick(reminders.get(getBindingAdapterPosition()));
                notifyDataSetChanged();
            } else {
                onClickListener.takenLonCheckClick(reminders.get(getBindingAdapterPosition()));
                notifyDataSetChanged();
            }
        }
    }

    public interface OnClickListener {
        void onEditIconClick(Reminder reminder);

        void onActiveIconClick(Reminder reminder);

        void takenLonCheckClick(Reminder reminder);
    }
}
