package com.groupdev.pillpall.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.groupdev.pillpall.R;
import com.groupdev.pillpall.list.VitalsAdapter;
import com.groupdev.pillpall.util.ShowList;
import com.groupdev.pillpall.viewModel.VitalsViewModel;

import java.util.ArrayList;
import java.util.List;

public class VitalsFragment extends Fragment {

    private VitalsViewModel vitalsViewModel;
    private NavController navController;
    private FloatingActionButton fab;
    private Button bloodPressure, heartRate, temperature, respiratoryRate, oxygenSaturation, weight, height, bmi, bloodGlucose;
    private RecyclerView recyclerView;
    private VitalsAdapter vitalsAdapter;
    private List<ShowList> showLists;
    private String vitalType;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vitals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vitalsViewModel = new ViewModelProvider(getActivity()).get(VitalsViewModel.class);
        initializeViews(view);
        setupViews();
    }

    private void initializeViews(View view) {
        navController = Navigation.findNavController(view);
        fab = view.findViewById(R.id.button_add_vitals);
        bloodPressure = view.findViewById(R.id.image_icon_vitals_item);
        heartRate = view.findViewById(R.id.button_heartRate);
        temperature = view.findViewById(R.id.button_temperature);
        respiratoryRate = view.findViewById(R.id.button_respiratoryRate);
        oxygenSaturation = view.findViewById(R.id.button_oxygenSaturation);
        weight = view.findViewById(R.id.button_weight);
        height = view.findViewById(R.id.button_height);
        bmi = view.findViewById(R.id.button_bmi);
        bloodGlucose = view.findViewById(R.id.button_bloodGlucose);
        recyclerView = view.findViewById(R.id.recycler_view_vitals);

    }

    private void setupViews() {
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Not bind to the database
        //TODO: Bind to the database
        ArrayList<ShowList> showLists = new ArrayList<>();
        showLists.add(new ShowList("2021-04-20 12:00", 120.80, "Blood Pressure"));
        showLists.add(new ShowList("2021-04-20 13:00", 130.80, "Blood Pressure"));
        showLists.add(new ShowList("2021-04-20 14:00", 140.80, "Blood Pressure"));
        showLists.add(new ShowList("2021-05-20 15:00", 95, "Heart Rate"));
        vitalsAdapter = new VitalsAdapter(showLists);

        recyclerView.setAdapter(vitalsAdapter);

        fab.setOnClickListener(v -> {
            navController.navigate(R.id.addVitalFragment);
        });

    }
}