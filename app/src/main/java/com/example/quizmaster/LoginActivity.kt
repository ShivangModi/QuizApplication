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
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        register_first.setOnClickListener{
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

        login_done.setOnClickListener {
            this.doLogin()
        }

    }

    private fun doLogin(){
        if(Email.text.toString().isEmpty()){
            Email.error = "please enter a email"
            Email.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(Email.text.toString()).matches()){
            Email.error = "enter valid email"
            Email.requestFocus()
            return
        }

        if(Password.text.toString().isEmpty()){
            Password.error = "please enter a password"
            Password.requestFocus()
            return
        }

        auth.signInWithEmailAndPassword(Email.text.toString(), Password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)

                }

                // ...
            }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null){
            Toast.makeText(baseContext, "Login successful.",
                Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(baseContext, "Login failed.",
                Toast.LENGTH_SHORT).show()
        }
    }
}