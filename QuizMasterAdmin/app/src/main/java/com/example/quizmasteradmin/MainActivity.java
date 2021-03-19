package com.example.quizmasteradmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private EditText emailTextView, passwordTextView;
    private Button Btn;
    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // taking instance of FirebaseAuth
        fAuth = FirebaseAuth.getInstance();

        // initialising all views through id defined above
        emailTextView = findViewById(R.id.email);
        passwordTextView = findViewById(R.id.password);
        Btn = findViewById(R.id.login);

        Intent intent = new Intent(this, CategoryActivity.class);

        if (fAuth.getCurrentUser() != null) {
            startActivity(intent);
            finish();
            return;
        }

        // Set on Click Listener on Sign-in button
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                //sign in
                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),
                                    "Login successful!",
                                    Toast.LENGTH_LONG)
                                    .show();

                            // if the user created intent to login activity
                            startActivity(new Intent(getApplicationContext(), CategoryActivity.class));
                            finish();
                        } else {
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