package com.groupdev.pillpall.view;

import android.annotation.SuppressLint;
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

import com.github.mikephil.charting.charts.BarChart;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.groupdev.pillpall.MainActivity;
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
import java.util.List;

import in.akshit.horizontalcalendar.HorizontalCalendarView;
import in.akshit.horizontalcalendar.Tools;


public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private HomeViewModel viewModel;
    private RemindersAdapter remindersAdapter;
    private FloatingActionButton fab;
    private NavController navController;
    private HorizontalCalendarView calendarView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class);

        initializeViews(view);

        fab.setOnClickListener(v -> {
            navController.navigate(R.id.addReminderFragment);
        });

        remindersAdapter = new RemindersAdapter();
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        viewModel.getAllReminders().observe(getViewLifecycleOwner(), reminders -> {
            remindersAdapter.setReminders(reminders);
            recyclerView.setAdapter(remindersAdapter);
        });

        recyclerView.setAdapter(remindersAdapter);


        Calendar startTime = Calendar.getInstance();
        startTime.add(Calendar.WEEK_OF_MONTH,-3);

        Calendar endTime = Calendar.getInstance();
        endTime.add(Calendar.WEEK_OF_MONTH,3);

        ArrayList<String> datesToBeColored = new ArrayList<>();
        datesToBeColored.add(Tools.getFormattedDateToday());

        calendarView.setUpCalendar(startTime.getTimeInMillis(), endTime.getTimeInMillis(), datesToBeColored, date -> {
            viewModel.getAllReminders().observe(getViewLifecycleOwner(), reminders -> {
                remindersAdapter.setReminders(reminders);
                recyclerView.setAdapter(remindersAdapter);
            });
            Toast.makeText(getContext(),date+" clicked!",Toast.LENGTH_SHORT).show();
        });

    }

    private void initializeViews(View view) {
        navController = Navigation.findNavController(view);
        fab = view.findViewById(R.id.add_reminder_button);
        recyclerView = view.findViewById(R.id.home_recyclerView);
        calendarView = view.findViewById(R.id.calendar);
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