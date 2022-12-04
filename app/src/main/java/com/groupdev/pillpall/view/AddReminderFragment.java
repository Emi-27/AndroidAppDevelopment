package com.groupdev.pillpall.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.groupdev.pillpall.model.Medication;
import com.groupdev.pillpall.model.Reminder;
import com.groupdev.pillpall.viewModel.AddReminderViewModel;
import com.groupdev.pillpall.R;
import com.takisoft.datetimepicker.DatePickerDialog;
import com.takisoft.datetimepicker.TimePickerDialog;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddReminderFragment extends Fragment {

    private AddReminderViewModel viewModel;
    private NavController navController;
    private List<Medication> medications;
    private Reminder reminderToBeAdded;

    private Spinner spinnerMedication, spinnerFrequency;
    private EditText editTextQuantity, editTextNotes;
    private int time, date, frequency;
    private int dateTime;
    private Button saveReminder, addTime, addDate, removeReminder;
    private AlertDialog.Builder alertBuilder;
    Calendar cal;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_reminder, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AddReminderViewModel.class);

        if(getArguments() != null) {
            Bundle bundle = getArguments();
            reminderToBeAdded = (Reminder) bundle.getSerializable("reminder");
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Edit Reminder " + reminderToBeAdded.getMedicationName());
        } else {
            reminderToBeAdded = new Reminder();
        }

        initViews(view);
        initializeMedicationSpinner(view);
        initializeFrequencySpinner(view);
        initializeDateTimePicker(view);

    }

    private void initViews(View view) {
        navController = Navigation.findNavController(view);
        editTextNotes = view.findViewById(R.id.editText_AddReminder_notes);
        editTextQuantity = view.findViewById(R.id.editText_AddReminder_quantity);
        saveReminder = view.findViewById(R.id.button_addNewReminder);
        removeReminder = view.findViewById(R.id.button_removeReminder);

        if(getArguments() != null) {
            editTextQuantity.setText(String.valueOf(reminderToBeAdded.getQuantity()));
            removeReminder.setVisibility(View.VISIBLE);
        }

        saveReminder.setOnClickListener(v -> {
            if(checkFields()){
            viewModel.AddReminder(reminderToBeAdded.getName(),editTextNotes.getText().toString(),
                    Integer.parseInt(editTextQuantity.getText().toString()),time,frequency,date);
            navController.navigate(R.id.navigation_home);}
        });

        removeReminder.setOnClickListener(v -> {
            if(reminderToBeAdded.getId() == 0){
                Toast.makeText(getContext(), "Reminder not saved yet", Toast.LENGTH_SHORT).show();
            }else{
                alertBuilder.setMessage("Do you really want to delete this reminder?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                viewModel.removeReminder(reminderToBeAdded);
                                navController.navigate(R.id.navigation_home);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = alertBuilder.create();
                alert.setTitle("Confirmation");
                alert.show();
            }
        });
    }

    private void initializeMedicationSpinner(View view){
        spinnerMedication = view.findViewById(R.id.spinner);
        medications = new ArrayList<>();
        medications = viewModel.getAllMedications().getValue();

        List<String> medicationNames = new ArrayList<>();

        if(medications != null){
            for(Medication medication : medications){
                medicationNames.add(medication.getName());
            }
        }

        spinnerMedication.setAdapter(new ArrayAdapter<>(
                getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, medicationNames));
        spinnerMedication.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                reminderToBeAdded.setMedicationName(medications.get(position).getName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //TODO
            }
        });
    }

    private void initializeFrequencySpinner(View view){
        spinnerFrequency = view.findViewById(R.id.spinner2);
        List<String> frequencyList = new ArrayList<>();
        frequencyList.add("Once a day");
        frequencyList.add("Each 12 hours");
        frequencyList.add("Each 8 hours");
        frequencyList.add("Each 6 hours");
        spinnerFrequency.setAdapter(new ArrayAdapter<>(
                getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, frequencyList));
        spinnerFrequency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                frequency = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //TODO
            }
        });
    }

    private void initializeDateTimePicker(View view){
        addTime = view.findViewById(R.id.imageButton_AddTime);
        addDate = view.findViewById(R.id.imageButton_AddDate);
        cal = Calendar.getInstance();
        addTime.setOnClickListener(day -> {
                    DatePickerDialog dpd = new DatePickerDialog(getContext(), (view1, year, month, dayOfMonth) -> {
                        String sDate = year+ ""+ String.format("%02d",month+1) +""+ String.format("%02d",dayOfMonth);
                        date = Integer.parseInt(sDate);
                    }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
                    dpd.show();
                }
        );

        addDate.setOnClickListener(newView -> {
                    TimePickerDialog tpd = new TimePickerDialog(getContext(), (view1, hourOfDay, minute) -> {
                        String sTime = String.format("%02d", hourOfDay) +String.format("%02d", minute);
                        time = Integer.parseInt(sTime);
                    }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), DateFormat.is24HourFormat(getContext()));
                    tpd.show();
                }
        );
    }

    private boolean checkFields() {
        if (editTextQuantity.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Please enter a quantity", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (time == 0) {
            time = Integer.parseInt(cal.get(Calendar.HOUR_OF_DAY)+""+ cal.get(Calendar.MINUTE));
        }
        if (date == 0) {
            date = Integer.parseInt(LocalDate.now().getYear()+""+ String.format("%02d",LocalDate.now().getMonthValue())+""+ String.format("%02d",LocalDate.now().getDayOfMonth()));
        }return true;
    }
}