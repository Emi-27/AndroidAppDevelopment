package com.groupdev.pillpall.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import in.akshit.horizontalcalendar.HorizontalCalendarView;
import in.akshit.horizontalcalendar.Tools;


public class HomeFragment extends Fragment {

    private HomeViewModel viewModel;
    private RemindersAdapter remindersAdapter;
    private FloatingActionButton fab;
    private NavController navController;
    private HorizontalCalendarView calendarView;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class);

        initializeViews(view);
        InitializeCalendar();
        setUpViews();
    }

    private void initializeViews(View view) {
        navController = Navigation.findNavController(view);
        calendarView = view.findViewById(R.id.calendar);
        recyclerView = view.findViewById(R.id.home_recyclerView);
        fab = view.findViewById(R.id.add_reminder_button);
        fab.setOnClickListener(v -> {
            navController.navigate(R.id.addReminderFragment);
        });
    }

    private void setUpViews() {
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        remindersAdapter = new RemindersAdapter(viewModel.getAllReminders().getValue(), new RemindersAdapter.OnClickListener() {
            @Override
            public void onClick(Reminder reminder) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("reminder", reminder);
                navController.navigate(R.id.addReminderFragment, bundle);
            }

            @Override
            public void onTake(Reminder reminder) {
                reminder.setTaken(!reminder.isTaken());
                viewModel.updateReminder(reminder);
            }

            @Override
            public void onActive(Reminder reminder) {
                reminder.setActive(!reminder.isActive());
                viewModel.updateReminder(reminder);
            }
        });
        recyclerView.setAdapter(remindersAdapter);

        viewModel.getAllReminders().observe(getViewLifecycleOwner(), reminders -> {
            remindersAdapter.setReminders(reminders);
        });
    }

    private void InitializeCalendar() {
        Calendar startTime = Calendar.getInstance();
        startTime.add(Calendar.WEEK_OF_MONTH,-3);

        Calendar endTime = Calendar.getInstance();
        endTime.add(Calendar.WEEK_OF_MONTH,3);

        ArrayList<String> datesToBeColored = new ArrayList<>();
        datesToBeColored.add(Tools.getFormattedDateToday());

        calendarView.setUpCalendar(startTime.getTimeInMillis(), endTime.getTimeInMillis(), datesToBeColored, date -> {
            int intDateTest = newFormatDate(date);
            viewModel.getRemindersByDate(intDateTest).observe(getViewLifecycleOwner(), reminders -> {
                remindersAdapter.setReminders(reminders);
                datesToBeColored.add(date);
            });


        });
    }

    private int newFormatDate (String date){
        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date newDate = null;
        try {
            newDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String dayN = new SimpleDateFormat("yyyyMMdd").format(newDate);
        return Integer.parseInt(dayN);
    }
}