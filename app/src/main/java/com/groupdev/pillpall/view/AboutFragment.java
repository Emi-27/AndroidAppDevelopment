package com.groupdev.pillpall.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.groupdev.pillpall.R;
import com.groupdev.pillpall.BuildConfig;

public class AboutFragment extends Fragment {
    private FirebaseAuth auth;
    TextView aboutName, aboutEmail, aboutVersion;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        initializeViews(view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    private void initializeViews(View view){
        aboutName = view.findViewById(R.id.about_name);
        aboutEmail = view.findViewById(R.id.about_email);
        aboutVersion = view.findViewById(R.id.about_version);
        String version = BuildConfig.VERSION_NAME;

        aboutName.setText(auth.getCurrentUser().getDisplayName());
        aboutEmail.setText(auth.getCurrentUser().getEmail());
        aboutVersion.setText(version);
    }
}