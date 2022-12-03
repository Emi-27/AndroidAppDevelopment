package com.groupdev.pillpall;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.groupdev.pillpall.R;
import com.groupdev.pillpall.model.User;

public class Register extends AppCompatActivity {
    EditText fullName, registerEmail, registerPassword, repeatPassword;
    Button registerButton;
    TextView loginNow;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fullName = findViewById(R.id.register_full_name);
        registerEmail = findViewById(R.id.register_email);
        registerPassword = findViewById(R.id.register_password);
        repeatPassword = findViewById(R.id.register_repeat_password);
        registerButton = findViewById(R.id.register_button);
        loginNow = findViewById(R.id.login_now);
        auth = FirebaseAuth.getInstance();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullNameText = fullName.getText().toString();
                String registerEmailText = registerEmail.getText().toString();
                String registerPasswordText = registerPassword.getText().toString();
                String repeatPasswordText = repeatPassword.getText().toString();

                if (!(fullNameText.isEmpty() || registerEmailText.isEmpty() || registerPasswordText.isEmpty() || repeatPasswordText.isEmpty())) {
                    if (registerPasswordText.equals(repeatPasswordText)) {
//                        User temp = new User(fullName.getText().toString(), registerEmail.getText().toString(), registerPassword.getText().toString());
//                        System.out.println("USER: " + temp);
                        createAccount(registerEmailText, registerPasswordText, fullNameText);
                    } else {
                        Toast.makeText(Register.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Register.this, "Please fill in all the fields.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        loginNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });
    }

    private void createAccount(String email, String password, String fullName) {

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = auth.getCurrentUser();
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(fullName)
                            .build();

                    user.updateProfile(profileUpdates)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "Registration successful.", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Register.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                }
                            });
                }
                else {
                    try {
                        throw task.getException();
                    }
                    catch(FirebaseAuthWeakPasswordException e) {
                        Toast.makeText(Register.this, "Password too weak!", Toast.LENGTH_LONG).show();
                    }
                    catch(FirebaseAuthUserCollisionException e) {
                        Toast.makeText(Register.this, "User already exists!", Toast.LENGTH_LONG).show();
                    }
                    catch(FirebaseAuthInvalidCredentialsException e) {
                        Toast.makeText(Register.this, "Invalid email format!", Toast.LENGTH_LONG).show();
                    }
                    catch(Exception e) {
                        Toast.makeText(Register.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}