package com.example.handyhotel

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.*
import java.util.concurrent.TimeUnit

class AuthorizationActivity : AppCompatActivity() {

    private lateinit var buttonLogIn: Button
    private lateinit var buttonSingUp: Button
    private lateinit var editTextLogin: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var textViewError: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)

        buttonLogIn = findViewById(R.id.buttonLogIn)
        buttonSingUp = findViewById(R.id.buttonSingUp)
        editTextLogin = findViewById(R.id.editTextLogin)
        editTextPassword = findViewById(R.id.editTextPassword)
        textViewError = findViewById(R.id.textViewError)

        buttonLogIn.setOnClickListener {

            textViewError.visibility = View.INVISIBLE;

            val database: FirebaseDatabase = FirebaseDatabase.getInstance()
            val usersRef: DatabaseReference = database.getReference("users")

            val login = editTextLogin.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            val query: Query = usersRef.orderByChild("login").equalTo(login)
            query.addListenerForSingleValueEvent(object : ValueEventListener {
                @Suppress("UNCHECKED_CAST")
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // Получение данных о пользователе
                        val userSnapshot: DataSnapshot = dataSnapshot.children.iterator().next()
                        val userMap: Map<String, Any> = userSnapshot.value as Map<String, Any>

                        val storedPassword = userMap["password"] as String
                        if (storedPassword == password) {
                            val phone = userMap["phone"] as String
                            val intent = Intent(this@AuthorizationActivity, VerificationActivity::class.java)
                            intent.putExtra("phone", phone)
                            startActivity(intent)
                        } else {
                            textViewError.text = "Неправильный логин или пароль"
                            textViewError.visibility = View.VISIBLE;
                        }
                    } else {
                        textViewError.text = "Аккаунта с таким логином не существует"
                        textViewError.visibility = View.VISIBLE;
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })


        }

        buttonSingUp.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

    }

}