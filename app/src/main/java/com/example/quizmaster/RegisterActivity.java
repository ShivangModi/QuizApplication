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
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailTextView, passwordTextView, username, phoneNumber;
    private Button Btn;
    private ProgressBar progressbar;
    private FirebaseAuth fAuth;
    private TextView tapMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // taking FirebaseAuth instance
        fAuth = FirebaseAuth.getInstance();

        // initialising all views through id defined above
        emailTextView = findViewById(R.id.email_r);
        passwordTextView = findViewById(R.id.password_r);
        Btn = findViewById(R.id.register);
        progressbar = findViewById(R.id.progressBar);
        username = findViewById(R.id.username_r);
        phoneNumber = findViewById(R.id.phone_r);
        tapMe = findViewById(R.id.tap_hear_to_login);

        tapMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

       /* if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }*/

        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailTextView.getText().toString().trim();
                String password = passwordTextView.getText().toString().trim();
                String user = username.getText().toString().trim();
                String phone = phoneNumber.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    emailTextView.setError("Email required");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    passwordTextView.setError("Password required");
                    return;
                }

                if (TextUtils.isEmpty(user)) {
                    username.setError("Username required");
                    return;
                }

                if (TextUtils.isEmpty(phone)) {
                    phoneNumber.setError("Phone number required");
                    return;
                }

                progressbar.setVisibility(View.VISIBLE);

                // registration

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),
                                    "Registration successful!",
                                    Toast.LENGTH_LONG)
                                    .show();
                            // if the user created intent to login activity
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else{

                            Toast.makeText(getApplicationContext(),
                                    "Registration failed",
                                    Toast.LENGTH_LONG)
                                    .show();

                        }

                    }
                });


            }
        });

    }

}