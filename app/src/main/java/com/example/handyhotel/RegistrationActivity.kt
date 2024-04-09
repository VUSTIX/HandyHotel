package com.example.handyhotel

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.database.*
import org.mindrot.jbcrypt.BCrypt
import java.util.*
import java.util.concurrent.TimeUnit

class RegistrationActivity : AppCompatActivity() {

    private lateinit var editTextRegPhone: EditText
    private lateinit var editTextRegFirstName: EditText
    private lateinit var editTextRegSecondName: EditText
    private lateinit var editTextRegLogin: EditText
    private lateinit var editTextRegPassword: EditText

    private lateinit var signUpButton: Button

    private lateinit var firebaseExample: FirebaseExample

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        editTextRegPhone = findViewById(R.id.editTextRegPhone)
        editTextRegFirstName = findViewById(R.id.editTextRegFirstName)
        editTextRegSecondName = findViewById(R.id.editTextRegSecondName)
        editTextRegLogin = findViewById(R.id.editTextRegLogin)
        editTextRegPassword = findViewById(R.id.editTextRegPassword)
        signUpButton = findViewById(R.id.signUpButton)
        firebaseExample = FirebaseExample()

        signUpButton.setOnClickListener {
            val phone = editTextRegPhone.text.toString().trim()
            val firstName = editTextRegFirstName.text.toString().trim()
            val lastName = editTextRegSecondName.text.toString().trim()
            val login = editTextRegLogin.text.toString().trim()
            val password = editTextRegPassword.text.toString().trim()
            val idUser = UUID.randomUUID().toString()

            val hashedPassword = hashPassword(password)

            isPhoneUnique(phone) { isPhoneUnique ->
                if (isPhoneUnique) {
                    isLoginUnique(login) { isLoginUnique ->
                        if (isLoginUnique) {
                            val intent = Intent(this@RegistrationActivity, VerificationActivity::class.java)
                            intent.putExtra("idUser", idUser)
                            intent.putExtra("firstName", firstName)
                            intent.putExtra("lastName", lastName)
                            intent.putExtra("phone", phone)
                            intent.putExtra("login", login)
                            intent.putExtra("hashedPassword", hashedPassword)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, "Такой логин уже существует", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                } else {
                    Toast.makeText(
                        this,
                        "На такой номер уже зарегистрирован аккаунт",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        val backButton: Button = findViewById(R.id.backAuthButton)
        backButton.setOnClickListener {
            @Suppress("DEPRECATION")
            onBackPressed()
        }
    }

    private fun isPhoneUnique(phone: String, onComplete: (Boolean) -> Unit) {
        val database = FirebaseDatabase.getInstance()
        val usersRef = database.getReference("users")

        usersRef.runTransaction(object : Transaction.Handler {
            override fun doTransaction(currentData: MutableData): Transaction.Result {
                // Получаем все дочерние узлы
                val usersSnapshot = currentData.children

                for (userSnapshot in usersSnapshot) {
                    val userPhone = userSnapshot.child("phone").getValue(String::class.java)
                    if (userPhone == phone) {
                        return Transaction.abort()
                    }
                }

                return Transaction.success(currentData)
            }

            override fun onComplete(
                databaseError: DatabaseError?,
                committed: Boolean,
                currentData: DataSnapshot?
            ) {
                if (databaseError == null && committed) {
                    onComplete.invoke(true)
                } else {
                    onComplete.invoke(false)
                }
            }
        })
    }

    private fun isLoginUnique(login: String, onComplete: (Boolean) -> Unit) {
        val database = FirebaseDatabase.getInstance()
        val usersRef = database.getReference("users")

        usersRef.runTransaction(object : Transaction.Handler {
            override fun doTransaction(currentData: MutableData): Transaction.Result {
                // Получаем все дочерние узлы
                val usersSnapshot = currentData.children

                for (userSnapshot in usersSnapshot) {
                    val userLogin = userSnapshot.child("login").getValue(String::class.java)
                    if (userLogin == login) {
                        return Transaction.abort()
                    }
                }

                return Transaction.success(currentData)
            }

            override fun onComplete(
                databaseError: DatabaseError?,
                committed: Boolean,
                currentData: DataSnapshot?
            ) {
                if (databaseError == null && committed) {
                    onComplete.invoke(true)
                } else {
                    onComplete.invoke(false)
                }
            }
        })
    }

    private fun hashPassword(password: String): String {
        val saltRounds = 10
        return BCrypt.hashpw(password, BCrypt.gensalt(saltRounds))
    }

    fun checkPassword(password: String, hashedPassword: String): Boolean {
        return BCrypt.checkpw(password, hashedPassword)
    }

}