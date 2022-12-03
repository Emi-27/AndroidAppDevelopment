package com.groupdev.pillpall.view;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.groupdev.pillpall.viewModel.AddReminderViewModel;
import com.groupdev.pillpall.R;
import com.takisoft.datetimepicker.DatePickerDialog;
import com.takisoft.datetimepicker.TimePickerDialog;

import java.util.Calendar;

public class AddReminderFragment extends Fragment {

    private AddReminderViewModel viewModel;
    private NavController navController;

    private EditText reminderName;
    private EditText reminderTime;
    private Button saveReminder;
    private Button addTime;
    private Button addDate;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_reminder, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AddReminderViewModel.class);
        initViews(view);

        Calendar cal = Calendar.getInstance();

        addTime.setOnClickListener(newView -> {
                            DatePickerDialog dpd = new DatePickerDialog(getContext(), (view1, year, month, dayOfMonth) -> {
                                Toast.makeText(getContext(), String.format("%d", year) + "-" + String.format("%02d", month + 1) + "-"
                                        + String.format("%02d", dayOfMonth), Toast.LENGTH_SHORT).show();
                            }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
                            dpd.show();
                        }
                );

        addDate.setOnClickListener(newView -> {
                            TimePickerDialog tpd = new TimePickerDialog(getContext(), (view1, hourOfDay, minute) -> {
                                Toast.makeText(getContext(), String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute), Toast.LENGTH_SHORT).show();
                            }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), DateFormat.is24HourFormat(getContext()));
                            tpd.show();
                        }
                );

    }

    private void initViews(View view) {
        navController = Navigation.findNavController(view);
        addTime = view.findViewById(R.id.imageButton_AddTime);
        addDate = view.findViewById(R.id.imageButton_AddDate);
        reminderName = view.findViewById(R.id.editText_AddReminder_notes);
        reminderTime = view.findViewById(R.id.editText_AddReminder_quantity);
        saveReminder = view.findViewById(R.id.button_addNewReminder);
        saveReminder.setOnClickListener(v -> {
            viewModel.AddReminder(reminderName.getText().toString(),
                    reminderTime.getText().toString());
            navController.navigate(R.id.navigation_home);
        });


    }
}