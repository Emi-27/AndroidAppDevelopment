package com.groupdev.pillpall.view;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.groupdev.pillpall.R;
import com.groupdev.pillpall.viewModel.AddVitalViewModel;

public class AddVitalFragment extends Fragment {

    private AddVitalViewModel ViewModel;
    private NavController navController;

    private Button addVitalButton;

    private TextView dateTime;
    private EditText bloodPressure;
    private EditText heartRate;
    private EditText temperature;
    private EditText respiratoryRate;
    private EditText oxygenSaturation;
    private EditText weight;
    private EditText height;
    private EditText bmi;
    private EditText bloodGlucose;
    private EditText notes;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_vital, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewModel = new ViewModelProvider(this).get(AddVitalViewModel.class);
        initViews(view);

    }

    private void initViews(View view) {
        navController = Navigation.findNavController(view);
        addVitalButton = view.findViewById(R.id.button_addNewVitals);

        dateTime = view.findViewById(R.id.textView_addVitals_dateTime);
        bloodPressure = view.findViewById(R.id.editTextBloodPressure);
        heartRate = view.findViewById(R.id.editTextHeartRate);
        temperature = view.findViewById(R.id.editTextTemperature);
        respiratoryRate = view.findViewById(R.id.editTextRespRate);
        oxygenSaturation = view.findViewById(R.id.editTextOxSat);
        weight = view.findViewById(R.id.editTextWeight);
        height = view.findViewById(R.id.editTextHeight);
        bmi = view.findViewById(R.id.editTextBmi);
        bloodGlucose = view.findViewById(R.id.editTextBloodGluc);
        notes = view.findViewById(R.id.editTextNotes);

        addVitalButton.setOnClickListener(v -> {
            ViewModel.addVital(dateTime.getText().toString(),
                    bloodPressure.getText().toString(),
                    heartRate.getText().toString(),
                    temperature.getText().toString(),
                    respiratoryRate.getText().toString(),
                    oxygenSaturation.getText().toString(),
                    weight.getText().toString(),
                    height.getText().toString(),
                    bmi.getText().toString(),
                    bloodGlucose.getText().toString(),
                    notes.getText().toString());
            navController.navigate(R.id.navigation_vitals);
        });



    }
}