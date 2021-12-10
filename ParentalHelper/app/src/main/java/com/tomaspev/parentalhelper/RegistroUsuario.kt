package com.tomaspev.parentalhelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_registro_usuario.*
import java.util.regex.Pattern

class RegistroUsuario : AppCompatActivity() {

    // CREA Y COMPILA UN PATRÓN PARA VALIDAR CONTRASEÑA
    val PASSWORD_PATTERN = Pattern.compile("^" +
            "(?=.*[a-zA-Z])" +  //any letter
            "(?=.*[!#()*+,-./:;=?@^_`{|}~])" +  //at least 1 special character
            "(?=\\S+$)" +  //no white spaces
            ".{8,20}" + //at least 8 characters
            "$"
    )

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_usuario)

        supportActionBar?.hide()    // Oculta la barra superior de la aplicación
        database = FirebaseDatabase.getInstance().reference
        setup()                     // Carga la función al iniciar la activity
    }

    // BOTON REGISTRAR Y VALIDACIONES //
    private fun setup() {
        signUpButton.setOnClickListener {
            if (emailEditText.editText?.text?.isEmpty() == true) {
                emailEditText.helperText="E-mail no puede estar vació"
            } else if (!Patterns.EMAIL_ADDRESS.matcher(emailEditText.editText!!.text).matches()){
                emailEditText.helperText="Introduzca un e-mail valido"
            } else if (passwordEditText.editText?.text?.isEmpty() == true) {
                passwordEditText.helperText="Este campo es obligatorio"
            } else if (!PASSWORD_PATTERN.matcher(passwordEditText.editText!!.text).matches()) {
                passwordEditText.helperText="Contraseña débil"
            } else if (emailEditText.editText?.text?.isNotEmpty() == true && Patterns.EMAIL_ADDRESS.matcher(emailEditText.editText!!.text).matches() &&
                passwordEditText.editText?.text?.isNotEmpty() == true){
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailEditText.editText?.text.toString(),
                        passwordEditText.editText?.text.toString()).addOnCompleteListener {
                        if (it.isSuccessful) {
                            // Se agregan el email y la contraseña en una variable de tipo Map
                            val map: MutableMap<String, Any> = HashMap()
                            map.put("email", iet_email.text.toString())
                            map.put("password", iet_password.text.toString())
                            // Para conseguir el uid
                            val user = Firebase.auth.currentUser
                            var uid = ""
                            user?.let{
                                uid = user.uid
                            }
                            // Se agrega la variable Map al usuario
                            database.child("Usuario").child(uid).setValue(map)

                            showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        showAlert()
                    }
                }
            }
        }
    }

    // CREAR Y MOSTRAR ALERTA //
    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Este E-Mail ya esta en uso")
        builder.setPositiveButton("Ok",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    // CAMBIAR DE ACTIVITY Y PASAR DATOS "email" Y "provider" A MAIN
    private fun showHome(email: String, provider: ProviderType) {
        val home = Intent(this, MainActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(home)
    }
}