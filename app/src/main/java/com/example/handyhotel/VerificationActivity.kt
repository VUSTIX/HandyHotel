package com.example.handyhotel

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

@Suppress("NAME_SHADOWING")
class VerificationActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    private lateinit var editText3: EditText
    private lateinit var editText4: EditText
    private lateinit var editText5: EditText
    private lateinit var editText6: EditText
    private lateinit var buttonNext: Button
    private lateinit var vibrator: Vibrator
    private lateinit var firebaseExample: FirebaseExample
    private lateinit var verification: String

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)

        val sharedPreferences: SharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        val intent = intent
        val idUser = intent.getStringExtra("idUser")
        val firstName = intent.getStringExtra("firstName")
        val lastName = intent.getStringExtra("lastName")
        val phone = intent.getStringExtra("phone")
        val login = intent.getStringExtra("login")
        val hashedPassword = intent.getStringExtra("hashedPassword")

        textView = findViewById(R.id.textView11)
        textView.text = "В течение 2 минут на номер\n$phone придет\nкод подтверждения"

        vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator

        editText1 = findViewById(R.id.editTextNumber1)
        editText2 = findViewById(R.id.editTextNumber2)
        editText3 = findViewById(R.id.editTextNumber3)
        editText4 = findViewById(R.id.editTextNumber4)
        editText5 = findViewById(R.id.editTextNumber5)
        editText6 = findViewById(R.id.editTextNumber6)

        setupTextWatchers()
        setupBackspaceListener()

        val options = PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
            .setPhoneNumber(phone.toString())
            .setTimeout(60L, TimeUnit.SECONDS) // Время ожидания SMS-кода (60 секунд)
            .setActivity(this@VerificationActivity)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    // Аутентификация успешно завершена
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    Toast.makeText(this@VerificationActivity, "Ошибка отправки SMS: ${e.message}", Toast.LENGTH_SHORT).show()
                }

                @SuppressLint("CommitPrefEdits")
                override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                    // sms с кодом отправлено
                    verification = verificationId
                }
            })
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)

        buttonNext = findViewById(R.id.buttonNext)
        buttonNext.setOnClickListener {
            val text1 = editText1.text.toString().trim()
            val text2 = editText2.text.toString().trim()
            val text3 = editText3.text.toString().trim()
            val text4 = editText4.text.toString().trim()
            val text5 = editText5.text.toString().trim()
            val text6 = editText6.text.toString().trim()
            val code = "$text1$text2$text3$text4$text5$text6"

            val credential = PhoneAuthProvider.getCredential(verification, code)
            FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val editor = sharedPreferences.edit()
                        editor.putString("verificationId", verification)
                        editor.apply()

                        firebaseExample = FirebaseExample()

                        firebaseExample.addUser(
                            idUser.toString(),
                            firstName.toString(),
                            lastName.toString(),
                            phone.toString(),
                            login.toString(),
                            hashedPassword.toString(),
                        )
                        Toast.makeText(this@VerificationActivity, "Успешно", Toast.LENGTH_SHORT).show()
                    } else {
                        editText1.setText("")
                        editText2.setText("")
                        editText3.setText("")
                        editText4.setText("")
                        editText5.setText("")
                        editText6.setText("")
                        editText1.requestFocus()
                        vibrate()
                    }
                }

        }

        val backButton: Button = findViewById(R.id.backRegButton)
        backButton.setOnClickListener {
            onBackPressed()
        }

    }

    private fun setupTextWatchers() {
        editText1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length == 1) {
                    editText2.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if ((s?.length ?: 0) > 1) {
                    s?.delete(1, s.length)
                }
            }
        })

        editText2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length == 1) {
                    editText3.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if ((s?.length ?: 0) > 1) {
                    s?.delete(1, s.length)
                }
            }
        })

        editText3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length == 1) {
                    editText4.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if ((s?.length ?: 0) > 1) {
                    s?.delete(1, s.length)
                }
            }
        })

        editText4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length == 1) {
                    editText5.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if ((s?.length ?: 0) > 1) {
                    s?.delete(1, s.length)
                }
            }
        })

        editText5.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length == 1) {
                    editText6.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if ((s?.length ?: 0) > 1) {
                    s?.delete(1, s.length)
                }
            }
        })

        editText6.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No-op since it's the last EditText
            }

            override fun afterTextChanged(s: Editable?) {
                if ((s?.length ?: 0) > 1) {
                    s?.delete(1, s.length)
                }
            }
        })

        editText1.requestFocus()
    }

    private fun setupBackspaceListener() {

        editText2.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN) {
                if (editText2.text.isNullOrEmpty()) {
                    editText1.requestFocus()
                    editText1.setSelection(editText1.text.length)
                }
            }
            false
        }

        editText3.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN) {
                if (editText3.text.isNullOrEmpty()) {
                    editText2.requestFocus()
                    editText2.setSelection(editText2.text.length)
                }
            }
            false
        }

        editText4.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN) {
                if (editText4.text.isNullOrEmpty()) {
                    editText3.requestFocus()
                    editText3.setSelection(editText3.text.length)
                }
            }
            false
        }

        editText5.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN) {
                if (editText5.text.isNullOrEmpty()) {
                    editText4.requestFocus()
                    editText4.setSelection(editText4.text.length)
                }
            }
            false
        }

        editText6.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN) {
                if (editText6.text.isNullOrEmpty()) {
                    editText5.requestFocus()
                    editText5.setSelection(editText5.text.length)
                }
            }
            false
        }
    }

    private fun vibrate() {
        val vibrationEffect = VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE)
        vibrator.vibrate(vibrationEffect)
    }

}