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

import com.groupdev.pillpall.viewModel.AddReminderViewModel;
import com.groupdev.pillpall.R;

public class AddReminderFragment extends Fragment {

    private AddReminderViewModel viewModel;
    private EditText reminderName;
    private EditText reminderTime;
    private Button saveReminder;
    private NavController navController;


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

    }

    private void initViews(View view) {
        navController = Navigation.findNavController(view);
        reminderName = view.findViewById(R.id.edit1);
        reminderTime = view.findViewById(R.id.edit2);
        saveReminder = view.findViewById(R.id.button_addNewReminder);
        saveReminder.setOnClickListener(v -> {
            viewModel.AddReminder(reminderName.getText().toString(),
                    reminderTime.getText().toString());
            navController.navigate(R.id.navigation_home);
        });


    }
}