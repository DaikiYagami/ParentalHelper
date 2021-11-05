package com.tomaspev.parentalhelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_recuperar_pass.*

class RecuperarPass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar_pass)

        supportActionBar?.hide()
        submitBtn.setOnClickListener {
            val email: String = emailResetTxT.text.toString().trim { it <= ' ' }
            if (email.isEmpty()) {
                Toast.makeText(
                    this,
                    "Por favor, introduzca un correo electronico.",
                    Toast.LENGTH_SHORT
                ).show()
            }else{
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener {task ->
                        if (task.isSuccessful){
                            Toast.makeText(
                                this,
                                "Correo de recuperación enviado.",
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