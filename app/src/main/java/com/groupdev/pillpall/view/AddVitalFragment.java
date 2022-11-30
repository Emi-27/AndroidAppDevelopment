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
import android.widget.TextView;

import com.groupdev.pillpall.R;
import com.groupdev.pillpall.viewModel.AddVitalViewModel;

public class AddVitalFragment extends Fragment {

    private AddVitalViewModel ViewModel;
    private NavController navController;
    private Button addVitalButton;
    private TextView vitalName;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_vital, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewModel = new ViewModelProvider(this).get(AddVitalViewModel.class);

        /* addVitalButton = view.findViewById(R.id.add_vitals_button);

        addVitalButton.setOnClickListener (view1 -> {

        });
        */
    }

    private void initViews(View view) {
        navController = Navigation.findNavController(view);
        addVitalButton = view.findViewById(R.id.button_addNewVitals);
        vitalName = view.findViewById(R.id.edit1);

    }
}