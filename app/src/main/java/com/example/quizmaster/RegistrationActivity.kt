package com.example.quizmaster

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register.*

class RegistrationActivity :  AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        alrady_account.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        auth = Firebase.auth

       // val email = email.text.toString()
       // val password = password.text.toString()

        registration_done.setOnClickListener{
            signUpUser()
        }

    }

    private fun signUpUser() {

        if(username.text.toString().isEmpty()){
            username.error = "please enter a username"
            username.requestFocus()
            return
        }

        if(phoneNumber.text.toString().isEmpty()){
            phoneNumber.error = "please enter a phone number"
            phoneNumber.requestFocus()
            return
        }

        if(email.text.toString().isEmpty()){
            email.error = "please enter a email"
            email.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()){
            email.error = "enter valid email"
            email.requestFocus()
            return
        }

        if(password.text.toString().isEmpty()){
            password.error = "please enter a password"
            password.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(baseContext, "Registration failed, Please try again",
                        Toast.LENGTH_SHORT).show()

                }


            }

    }

}


