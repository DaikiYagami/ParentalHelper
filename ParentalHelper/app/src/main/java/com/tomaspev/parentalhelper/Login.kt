package com.tomaspev.parentalhelper

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registro_usuario.*

class Login : AppCompatActivity() {

    private val GOOGLE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()


        setup()
        session()
    }

    private fun session(){
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        val provider = prefs.getString("provider", null)

        if (email != null && provider != null) {
            showHome(email, ProviderType.valueOf(provider))
        }
    }

    fun registrarse(){
        val intent = Intent(this, RegistroUsuario::class.java)
        startActivity(intent)
    }

    fun recuperar(view: View){
        val intent = Intent(this, RecuperarPass::class.java)
        startActivity(intent)
    }

    private fun setup(){

        LogInButton.setOnClickListener {
            if (EmailTxT.editText?.text?.isEmpty() == true) {
                EmailTxT.helperText="E-mail no puede estar vació"
                Handler(Looper.getMainLooper()).postDelayed({
                   EmailTxT.helperText=" "
                }, 5000)
            } else if (!Patterns.EMAIL_ADDRESS.matcher(EmailTxT.editText!!.text).matches()){
                EmailTxT.helperText="Introduzca un e-mail valido"
                Handler(Looper.getMainLooper()).postDelayed({
                    EmailTxT.helperText=" "
                }, 5000)
            } else if (PasswordTxT.editText?.text?.isEmpty() == true) {
                PasswordTxT.helperText="La contraseña no puede estar vacia"
                Handler(Looper.getMainLooper()).postDelayed({
                    PasswordTxT.helperText=" "
                }, 5000)
            } else if (PasswordTxT.editText?.text?.length!! <8) {
                PasswordTxT.helperText="La contraseña debe poseer al menos 8 caracteres"
                Handler(Looper.getMainLooper()).postDelayed({
                    PasswordTxT.helperText=" "
                }, 5000)
            } else if (EmailTxT.editText?.text?.isNotEmpty() == true && Patterns.EMAIL_ADDRESS.matcher(EmailTxT.editText!!.text).matches() &&
                PasswordTxT.editText?.text?.isNotEmpty() == true){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    EmailTxT.editText!!.text.toString(),
                    PasswordTxT.editText!!.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        PasswordTxT.helperText="E-mail o contraseña incorrecta."
                    }
                }
            }
        }

        /*LogInButton.setOnClickListener {
            if (emailEditText.text.isEmpty()) {
                Toast.makeText(this, "E-Mail no puede estar vacio", Toast.LENGTH_SHORT).show()
            } else if (!Patterns.EMAIL_ADDRESS.matcher(emailEditText.text).matches()){
                Toast.makeText(this, "Introduzca un E-mail valido", Toast.LENGTH_SHORT).show()
            } else if (passwordEditText.text.isEmpty()) {
                Toast.makeText(this, "La Contraseña no puede estar vacia", Toast.LENGTH_SHORT).show()
            } else if (passwordEditText.text.length <6) {
                Toast.makeText(this, "La contraseña debe poseer más de 6 caracteres", Toast.LENGTH_SHORT).show()
            } else if (emailEditText.text.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailEditText.text).matches() &&
                passwordEditText.text.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(emailEditText.text.toString(),
                    passwordEditText.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        showAlert()
                    }
                }
            }
        }*/

        googleButton.setOnClickListener {

            val googleConf =
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken("165836093897-t8d5a94ia2sghbe3mbcqs10s7nd6011q.apps.googleusercontent.com")
                    .requestEmail()
                    .build()

            val googleClient = GoogleSignIn.getClient(this, googleConf)
            googleClient.signOut()

            startActivityForResult(googleClient.signInIntent, GOOGLE)
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error. El usuario no esta registrado o las credenciales son incorrectas.")
        builder.setPositiveButton("Ok",null)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == GOOGLE) {

            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account = task.getResult(ApiException::class.java)

                if (account != null) {

                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)

                    FirebaseAuth.getInstance()
                        .signInWithCredential(credential).addOnCompleteListener {
                        if (it.isSuccessful) {
                            showHome(account.email ?: "", ProviderType.GOOGLE)
                        } else {
                            showAlert()
                        }
                    }
                }
            } catch (e: ApiException) {
                showAlert()
            }
        }
    }
}