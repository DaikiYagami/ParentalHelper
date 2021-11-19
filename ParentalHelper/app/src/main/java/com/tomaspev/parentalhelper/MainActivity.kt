package com.tomaspev.parentalhelper

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// Clase que permite la diferenciacion de los tipos de proveedor para iniciar sesión
enum class  ProviderType {
    BASIC,
    GOOGLE,
    FACEBOOK
}

class MainActivity : AppCompatActivity() {
    // Registros
    private lateinit var registroAdapter: RegistroAdapter
    private lateinit var dataListR: List<Registro>
    // Contenidos
    private lateinit var dataListC: List<Contenido>
    // Destacados
    private lateinit var destacadoAdapter: DestacadoAdapter
    // Nuevos Contenidos
    private lateinit var contenidoNuevoAdapter: ContenidoNuevoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Registros ============================================================================================================

        val recyclerViewR = findViewById<RecyclerView>(R.id.rv_registros)
        recyclerViewR.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        registroAdapter = RegistroAdapter(applicationContext)
        recyclerViewR.adapter = registroAdapter

        dataListR = listOf(
            Registro("Manuel", "12/04/2019", "H", false, 20),
            Registro("Jose", "15/07/2018", "H", true, 40),
            Registro("Martina", "23/09/2017", "M", false, 80)
        )

        registroAdapter.setDataList(dataListR)

        // Contenidos ===========================================================================================================

        // Adapter Contenidos Destacados
        val recyclerViewD = findViewById<RecyclerView>(R.id.rv_destacados)
        recyclerViewD.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        destacadoAdapter = DestacadoAdapter(applicationContext)
        recyclerViewD.adapter = destacadoAdapter

        // Adapter Contenidos Nuevos
        val recyclerViewC = findViewById<RecyclerView>(R.id.rv_nuevos_contenidos)
        recyclerViewC.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        contenidoNuevoAdapter = ContenidoNuevoAdapter(applicationContext)
        recyclerViewC.adapter = contenidoNuevoAdapter

        // Lista de Contenidos
        dataListC = listOf(
            Contenido("Contenido de evaluacion 1","2","www.patito.cl","Aca esta la descripcion corta","Aca esta la descripcion larga jaksdjaksjdkajskdajskdjaksdjaksjdkajsdkajsdkaskdjaksdaksdkasjdkajskdjaksdjaksjdkajsdkajsdkjaskdjaksd", "Destacado"),
            Contenido("Contenido de evaluacion 2", "1","www.patito.cl","Aca esta la descripcion corta","Aca esta la descripcion larga jaksdjaksjdkajskdajskdjaksdjaksjdkajsdkajsdkaskdjaksdaksdkasjdkajskdjaksdjaksjdkajsdkajsdkjaskdjaksd", "Nuevo"),
            Contenido("Números del 2 al 10", "2","www.patito.cl","Aca esta la descripcion corta","Aca esta la descripcion larga jaksdjaksjdkajskdajskdjaksdjaksjdkajsdkajsdkaskdjaksdaksdkasjdkajskdjaksdjaksjdkajsdkajsdkjaskdjaksd", "Destacado"),
            Contenido("Números del 1 al 10", "3","www.patito.cl","Aca esta la descripcion corta","Aca esta la descripcion larga jaksdjaksjdkajskdajskdjaksdjaksjdkajsdkajsdkaskdjaksdaksdkasjdkajskdjaksdjaksjdkajsdkajsdkjaskdjaksd", "Nuevo"),
            Contenido("Números del 2 al 10", "1","www.patito.cl","Aca esta la descripcion corta","Aca esta la descripcion larga jaksdjaksjdkajskdajskdjaksdjaksjdkajsdkajsdkaskdjaksdaksdkasjdkajskdjaksdjaksjdkajsdkajsdkjaskdjaksd", "Destacado"),
            Contenido("Números del 1 al 10", "2","www.patito.cl","Aca esta la descripcion corta","Aca esta la descripcion larga jaksdjaksjdkajskdajskdjaksdjaksjdkajsdkajsdkaskdjaksdaksdkasjdkajskdjaksdjaksjdkajsdkajsdkjaskdjaksd", "Nuevo"),
            Contenido("Números del 2 al 10", "3","www.patito.cl","Aca esta la descripcion corta","Aca esta la descripcion larga jaksdjaksjdkajskdajskdjaksdjaksjdkajsdkajsdkaskdjaksdaksdkasjdkajskdjaksdjaksjdkajsdkajsdkjaskdjaksd", "Destacado"),
            Contenido("Números del 1 al 10", "1","www.patito.cl","Aca esta la descripcion corta","Aca esta la descripcion larga jaksdjaksjdkajskdajskdjaksdjaksjdkajsdkajsdkaskdjaksdaksdkasjdkajskdjaksdjaksjdkajsdkajsdkjaskdjaksd", "Nuevo")
        )

        // Lista Contenidos Destacados
        val dataListD = dataListC.filter { it.tipo == "Destacado" }
        destacadoAdapter.setDataList(dataListD)

        // Lista Contenidos Nuevos
        val dataListNC = dataListC.filter { it.tipo == "Nuevo" }
        contenidoNuevoAdapter.setDataList(dataListNC)

        // Resto del código =====================================================================================================

        tv_todos_registros.setOnClickListener {
            val intent = Intent(this, ListadoRegistros::class.java)
            startActivity(intent)
        }

        // Parte tomas - verificación y mantener sesión iniciada
        val bundle = intent.extras                               // Variable que rescata los extras que trae el Intent
        val email = bundle?.getString("email")              // Variable que rescata el correo
        val provider = bundle?.getString("provider")        // Variable que rescata el provider

        setup(email ?: "", provider ?: "")         // Carga la funcion y sus datos provenientes de otros activity

        // VARIABLE QUE PERMITE MANTENER LA SESION INICIADA AL UTILIZAR PREFERENCIAS
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("provider", provider)
        prefs.apply()
    }

    // FUNCION QUE PERMITE CERRAR LA SESION EN FIREBASE Y VACIAR PREFS
    private fun setup(email: String, provider: String) {
        /*LogOutButton.setOnClickListener {

            val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()

            if (provider == ProviderType.FACEBOOK.name) {
                LoginManager.getInstance().logOut()
            }

            FirebaseAuth.getInstance().signOut()
            val home = Intent(this, Login::class.java)
            startActivity(home)
        }*/
    }
}