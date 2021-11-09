package com.tomaspev.parentalhelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_registro_usuario.*

class RegistroUsuario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_usuario)

        supportActionBar?.hide()
        setup()
    }

    private fun setup(){
        signUpButton.setOnClickListener {
            if (emailEditText.text.isEmpty()) {
                Toast.makeText(this, "E-Mail no puede estar vacio", Toast.LENGTH_SHORT).show()
            } else if (!Patterns.EMAIL_ADDRESS.matcher(emailEditText.text).matches()){
                Toast.makeText(this, "Introduzca un E-mail valido", Toast.LENGTH_SHORT).show()
            } else if (passwordEditText.text.isEmpty()) {
                Toast.makeText(this, "Contraseña no puede estar vacia", Toast.LENGTH_SHORT).show()
            } else if (emailEditText.text.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailEditText.text).matches() &&
                    passwordEditText.text.isNotEmpty()){
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailEditText.text.toString(),
                        passwordEditText.text.toString()).addOnCompleteListener {
                        if (it.isSuccessful) {
                            showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                    }
                }
            }
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error.")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String, provider: ProviderType) {

        val home = Intent(this, MainActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(home)
    }
}