package com.tomaspev.parentalhelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_recuperar_pass.*

class RecuperarPass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar_pass)

        supportActionBar?.hide()
        submitBtn.setOnClickListener {
            val email: String = EmailResetTxT.editText?.text?.toString()!!.trim { it <= ' ' }
            if (email.isEmpty()) {
                EmailResetTxT.helperText="Introduzca un email"
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                EmailResetTxT.helperText="Introduzca un e-mail valido"
                Handler(Looper.getMainLooper()).postDelayed({
                    EmailResetTxT.helperText=" "
                }, 5000)
            } else if (email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener {task ->
                        if (task.isSuccessful){
                            Toast.makeText(
                                this,
                                "Correo de recuperación enviado",
                                Toast.LENGTH_SHORT
                            ).show()

                            finish()
                        }else{
                            Toast.makeText(
                                this,
                                "Error",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }

    }
}