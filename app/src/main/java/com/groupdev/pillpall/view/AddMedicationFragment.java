package com.groupdev.pillpall.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.groupdev.pillpall.R;
import com.groupdev.pillpall.model.Medication;
import com.groupdev.pillpall.viewModel.MedicationsViewModel;


public class AddMedicationFragment extends Fragment {

    private MedicationsViewModel medicationsViewModel;
    private EditText medicationName, medicationStrength, medicationNotes;
    private Button addMedicationButton;
    private Spinner unitSpinner, formSpinner;
    private Medication medicationToBeAdded;
    private NavController navController;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        medicationsViewModel = new ViewModelProvider(getActivity()).get(MedicationsViewModel.class);
        initializeViews(view);
        initializeFormSpinner(view);
        initializeUnitSpinner(view);
        medicationToBeAdded = new Medication();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_medication, container, false);
    }

    private void initializeViews(View view){
        navController = Navigation.findNavController(view);
        medicationName = view.findViewById(R.id.add_medication_name);
        medicationStrength = view.findViewById(R.id.add_medication_strength);
        medicationNotes = view.findViewById(R.id.add_medication_notes);
        addMedicationButton = view.findViewById(R.id.confirm_medication_button);
        addMedicationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameText, dosageText;
                nameText = medicationName.getText().toString();
                dosageText = medicationStrength.getText().toString();
                if(!(nameText.isEmpty() || dosageText.isEmpty())){
                    medicationToBeAdded.setName(nameText);
                    medicationToBeAdded.setDosage(Double.parseDouble(dosageText));
                    medicationToBeAdded.setNotes(medicationNotes.getText().toString());
                    medicationsViewModel.addMedication(medicationToBeAdded);
                    navController.navigate(R.id.navigation_medications);
                }
                else {
                    Toast.makeText(getContext(), "Please fill in name and dosage.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initializeUnitSpinner(View view){
        unitSpinner = (Spinner) view.findViewById(R.id.medication_unit_spinner);
//        THIS POPULATES THE SPINNER WITH VALUES FROM STRING RESOURCES
//        ArrayAdapter<CharSequence> unitAdapter = ArrayAdapter.createFromResource(
//                getContext(), R.array.units_array, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
//        unitSpinner.setAdapter(unitAdapter);
        unitSpinner.setAdapter(new ArrayAdapter<Medication.UnitOfDosage>(
                getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, Medication.UnitOfDosage.values()));
        unitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                medicationToBeAdded.setUnitOfDosage((Medication.UnitOfDosage) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //TODO
            }
        });
    }

    private void initializeFormSpinner(View view){
        formSpinner = (Spinner) view.findViewById(R.id.medication_form_spinner);
//        THIS POPULATES THE SPINNER WITH VALUES FROM STRING RESOURCES
//        ArrayAdapter<CharSequence> formAdapter = ArrayAdapter.createFromResource(
//                getContext(), R.array.form_array, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
//        formSpinner.setAdapter(formAdapter);

        formSpinner.setAdapter(new ArrayAdapter<Medication.Form>(
                getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, Medication.Form.values()));
        formSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                medicationToBeAdded.setForm((Medication.Form) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //TODO
            }
        });
    }
}