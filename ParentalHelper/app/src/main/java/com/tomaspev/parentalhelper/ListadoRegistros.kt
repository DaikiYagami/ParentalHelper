package com.tomaspev.parentalhelper

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_listado_registros.*
import java.util.HashMap

class ListadoRegistros : AppCompatActivity() {
    private lateinit var dataList: ArrayList<Registro>
    private lateinit var recyclerView: RecyclerView

    private lateinit var prefs: SharedPreferences.Editor
    private var bundle: Bundle? = null
    private var email: String? = null
    private var provider: String? = null

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_basico, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_registros)

        setSupportActionBar(toolbar_listado_registros)

        val img = empty_listado_registros
        img.setImageResource(R.drawable.puzzle)

        recyclerView = findViewById(R.id.rv_lista_registros)
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 1)

        dataList = arrayListOf()
        dataList.clear()
        getData(recyclerView, dataList, applicationContext, img, "ListadoRegistro", "registros", null)
        // Resto del código =======================================================================

        bundle = intent.extras                               // Variable que rescata los extras que trae el Intent
        email = bundle?.getString("email")              // Variable que rescata el correo
        provider = bundle?.getString("provider")        // Variable que rescata el provider

        prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()

        fab.setOnClickListener {
            /* // Esto es para agregar datos en progreso contenido nomas, solo temporal
            val uid = FirebaseAuth.getInstance().currentUser?.uid.toString()
            database = FirebaseDatabase.getInstance().getReference("Usuario")

            val map: MutableMap<String, Any> = HashMap()
            map.put("id", 1)
            map.put("progreso", 70)

            database.child(uid).child("registros").child("Javiera").child("progreso").child("1").setValue(map)*/

            val intent = Intent(this, IngresoRegistro::class.java)
            intent.putExtras(bundle!!)
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {
        intent = Intent(this, MainActivity::class.java)
        intent.putExtras(bundle!!)
        startActivity(intent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.menu_logout -> {
                cerrarSesion(email, provider, true, prefs)
                val home = Intent(this, Login::class.java)
                startActivity(home)
                finish()
            }

        }
        return super.onOptionsItemSelected(item)
    }
}