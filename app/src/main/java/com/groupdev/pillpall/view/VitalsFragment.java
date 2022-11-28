package com.groupdev.pillpall.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.groupdev.pillpall.R;
import com.groupdev.pillpall.viewModel.VitalsViewModel;

public class VitalsFragment extends Fragment {

    private VitalsViewModel vitalsViewModel;
    private NavController navController;
    private FloatingActionButton fab;


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
        fab = view.findViewById(R.id.add_vitals_button);
    }

    private void setupViews() {
        fab.setOnClickListener(v -> {
            navController.navigate(R.id.addVitalFragment);
        });

    }




}