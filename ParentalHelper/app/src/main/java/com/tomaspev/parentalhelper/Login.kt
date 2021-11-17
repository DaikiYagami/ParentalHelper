package com.tomaspev.parentalhelper

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*



class Login : AppCompatActivity() {

    private val GOOGLE_SIGN_IN = 100

    private val callBackManager = CallbackManager.Factory.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()    // Oculta la barra superior de la aplicación
        setup()                     // Carga la función setup al iniciar la activity
        session()                   // Carga la función session al iniciar la activity
    }

    // FUNCION QUE PERMITE MANTENER LA SESION INICIADA
    private fun session(){
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        val provider = prefs.getString("provider", null)

        if (email != null && provider != null) {
            showHome(email, ProviderType.valueOf(provider))
        }
    }

    // FUNCION ONCLICK QUE CAMBIA DE ACTIVITY
    fun registrarse(view: View) {
        val intent = Intent(this, RegistroUsuario::class.java)
        startActivity(intent)
    }

    // FUNCION ONCLICK QUE CAMBIA DE ACTIVITY
    fun recuperar(view: View) {
        val intent = Intent(this, RecuperarPass::class.java)
        startActivity(intent)
    }

    // LOGICA DEL BOTON LOGIN PARA INICIAR SESION Y VALIDACIONES DE SUS CAMPOS
    private fun setup(){
        LogInButton.setOnClickListener {
            if (EmailTxT.editText?.text?.isEmpty() == true) {
                EmailTxT.helperText="Este campo es obligatorio"
            } else if (!Patterns.EMAIL_ADDRESS.matcher(EmailTxT.editText!!.text).matches()){
                EmailTxT.helperText="Introduzca un e-mail valido"
            } else if (PasswordTxT.editText?.text?.isEmpty() == true) {
                PasswordTxT.helperText="Este campo es obligatorio"
            } else if (PasswordTxT.editText?.text?.length!! <8) {
                PasswordTxT.helperText="La contraseña debe poseer al menos 8 caracteres"
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
        // ========== SECCION LOGIN CON GOOGLE ========== //
        googleButton.setOnClickListener {
            val googleConf =
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken("165836093897-t8d5a94ia2sghbe3mbcqs10s7nd6011q.apps.googleusercontent.com")
                    .requestEmail()
                    .build()

            val googleClient = GoogleSignIn.getClient(this, googleConf)
            startActivityForResult(googleClient.signInIntent, GOOGLE_SIGN_IN)
        }
        // ========== SECCION LOGIN CON FACEBOOK ========== //
        facebookButton.setOnClickListener {

            LoginManager.getInstance().logInWithReadPermissions(this, listOf("email"))

            LoginManager.getInstance().registerCallback(callBackManager,
                object : FacebookCallback<LoginResult> {

                    override fun onSuccess(result: LoginResult?) {

                        result?.let {

                            val token = it.accessToken

                            val credential :AuthCredential = FacebookAuthProvider.getCredential(token.token)
                            FirebaseAuth.getInstance()
                                .signInWithCredential(credential).addOnCompleteListener {
                                    if (it.isSuccessful) {
                                        showHome(it.result?.user?.email ?: "", ProviderType.FACEBOOK)
                                    } else {
                                        showAlert()
                                    }
                                }
                        }
                    }

                    override fun onCancel() {

                    }

                    override fun onError(error: FacebookException?) {
                        showAlert()
                    }
                })
        }

    }

    // CREAR Y MOSTRAR ALERTA //
    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("ERROR")
        builder.setMessage("Lo sentimos, ha ocurrido un error.")
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

    // INICIO DE SESION CON GOOGLE REQUIERE DE ESTA FUNCION OVERRIDE PARA VALIDAR LA CUENTA DE GOOGLE
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        callBackManager.onActivityResult(requestCode, resultCode, data)

        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    val credential :AuthCredential = GoogleAuthProvider.getCredential(account.idToken, null)
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