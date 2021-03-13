package com.example.quizmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity {

    private EditText emailTextView, passwordTextView;
    private Button Btn;
    private ProgressBar progressbar;
    private TextView tapHear;

    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // taking instance of FirebaseAuth
        fAuth = FirebaseAuth.getInstance();

        // initialising all views through id defined above
        emailTextView = findViewById(R.id.email_l);
        passwordTextView = findViewById(R.id.password_l);
        Btn = findViewById(R.id.login);
        progressbar = findViewById(R.id.progressBarOfLogin);
        tapHear = findViewById(R.id.tap_hear_to_register);

        tapHear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });

        // Set on Click Listener on Sign-in button
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String email = emailTextView.getText().toString().trim();
                String password = passwordTextView.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    emailTextView.setError("Email required");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    passwordTextView.setError("Password required");
                    return;
                }

                progressbar.setVisibility(View.VISIBLE);

                //sign in

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),
                                    "Login successful!",
                                    Toast.LENGTH_LONG)
                                    .show();
                            // if the user created intent to login activity
                            startActivity(new Intent(getApplicationContext(),CategoriesActivity.class));
                        }
                        else{

                            Toast.makeText(getApplicationContext(),
                                    "Login failed",
                                    Toast.LENGTH_LONG)
                                    .show();

                        }


                    }
                });


            }
        });
    }

}