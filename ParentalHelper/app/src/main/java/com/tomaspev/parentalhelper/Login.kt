package com.tomaspev.parentalhelper

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registro_usuario.*
import kotlinx.android.synthetic.main.activity_registro_usuario.emailEditText
import kotlinx.android.synthetic.main.activity_registro_usuario.passwordEditText

class Login : AppCompatActivity() {

    private val GOOGLE_SIGN_IN = 1

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

    fun registrarse(view: View){
        val intent = Intent(this, RegistroUsuario::class.java)
        startActivity(intent)
    }
    private fun setup(){
        LogInButton.setOnClickListener  {
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()){

                FirebaseAuth.getInstance().signInWithEmailAndPassword(emailEditText.text.toString(),
                    passwordEditText.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        showAlert()
                    }
                }
            }
        }
        googleButton.setOnClickListener {

            val googleConf =
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken("165836093897-t8d5a94ia2sghbe3mbcqs10s7nd6011q.apps.googleusercontent.com")
                    .requestEmail()
                    .build()

            val googleClient = GoogleSignIn.getClient(this, googleConf)
            googleClient.signOut()

            startActivityForResult(googleClient.signInIntent, GOOGLE_SIGN_IN)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == GOOGLE_SIGN_IN) {

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