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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.groupdev.pillpall.R;
import com.groupdev.pillpall.list.RemindersAdapter;
import com.groupdev.pillpall.model.Reminder;
import com.groupdev.pillpall.viewModel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private HomeViewModel homeViewModel;
    private NavController navController;
    private RemindersAdapter remindersAdapter;
    FloatingActionButton fab;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeViewModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class);
        initializeViews(view);
        setupViews();
    }

    private void initializeViews(View view) {
        navController = Navigation.findNavController(view);
        fab = view.findViewById(R.id.add_reminder_button);
        recyclerView = view.findViewById(R.id.home_recyclerView);
    }

    private void setupViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.hasFixedSize();

        List<Reminder> reminders = new ArrayList<>();
        reminders.add(new Reminder(" Reminder name uno", "10:00"));
        reminders.add(new Reminder(" Reminder name dos ", "12:00"));
        reminders.add(new Reminder(" Reminder name tres", "15:00"));
        reminders.add(new Reminder(" Reminder name 4", "16:00" ));

        remindersAdapter = new RemindersAdapter(reminders);
        recyclerView.setAdapter(remindersAdapter);

        fab.setOnClickListener(v -> {
            navController.navigate(R.id.addReminderFragment);
        });
    }
}