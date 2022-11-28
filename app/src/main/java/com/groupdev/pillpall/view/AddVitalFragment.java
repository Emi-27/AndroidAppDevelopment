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

import com.groupdev.pillpall.R;
import com.groupdev.pillpall.viewModel.AddVitalViewModel;

public class AddVitalFragment extends Fragment {

    private Button addVitalButton;
    private AddVitalViewModel mViewModel;

    public static AddVitalFragment newInstance() {
        return new AddVitalFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_vital, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);
        /* addVitalButton = view.findViewById(R.id.add_vitals_button);

        addVitalButton.setOnClickListener (view1 -> {

        });
        */
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AddVitalViewModel.class);
    }

}