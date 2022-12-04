package com.groupdev.pillpall.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.BarChart;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.groupdev.pillpall.R;
import com.groupdev.pillpall.list.MedicationAdapter;
import com.groupdev.pillpall.model.Medication;
import com.groupdev.pillpall.viewModel.MedicationsViewModel;
import com.groupdev.pillpall.databinding.FragmentMedicationsBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToDoubleBiFunction;

public class MedicationsFragment extends Fragment {

    private RecyclerView recyclerView;
    private MedicationAdapter medicationAdapter;
    private List<Medication> medications;
    private MedicationsViewModel medicationsViewModel;
    private NavController navController;
    private AlertDialog.Builder alertBuilder;
    FloatingActionButton fab;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_medications, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        medicationsViewModel = new ViewModelProvider(getActivity()).get(MedicationsViewModel.class);
        initializeViews(view);
        setupViews();
    }

    private void initializeViews(View view){
        navController = Navigation.findNavController(view);
        fab = view.findViewById(R.id.add_medication_button);
        recyclerView = view.findViewById(R.id.medications_recyclerView);
        alertBuilder = new AlertDialog.Builder(getContext());
    }

    private void setupViews(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.hasFixedSize();
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        medications = new ArrayList<>();
        medicationsViewModel.getAllMedications().observe(getViewLifecycleOwner(), meds -> {
            medicationAdapter.setMedications(meds);
        });
        medicationAdapter = new MedicationAdapter(medications);
        recyclerView.setAdapter(medicationAdapter);

        medicationAdapter.setOnClickListener(med -> {
            alertBuilder.setMessage("Do you really want to delete this medication?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            medicationsViewModel.deleteMedication(med);
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
        });

        fab.setOnClickListener(l -> {
            navController.navigate(R.id.addMedicationFragment);
        });
    }

}