package com.tomaspev.parentalhelper

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_listado_registros.*
import java.util.HashMap

class ListadoRegistros : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var dataList: ArrayList<Registro>
    private lateinit var recyclerView: RecyclerView

    private lateinit var prefs: SharedPreferences.Editor
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

        recyclerView = findViewById(R.id.rv_lista_registros)
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 1)

        dataList = arrayListOf()
        dataList.clear()
        getData()
        // Resto del código =======================================================================

        fab.setOnClickListener {
            /* // Esto es para agregar datos en progreso contenido nomas, solo temporal
            val uid = FirebaseAuth.getInstance().currentUser?.uid.toString()
            database = FirebaseDatabase.getInstance().getReference("Usuario")

            val map: MutableMap<String, Any> = HashMap()
            map.put("id", 1)
            map.put("progreso", 70)

            database.child(uid).child("registros").child("Javiera").child("progreso").child("1").setValue(map)*/

            val intent = Intent(this, IngresoRegistro::class.java)
            startActivity(intent)
        }

        val bundle = intent.extras                           // Variable que rescata los extras que trae el Intent
        email = bundle?.getString("email")              // Variable que rescata el correo
        provider = bundle?.getString("provider")        // Variable que rescata el provider

        prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()

    }
    // Toma los datos solicitados de la DB
    private fun getData() {
        val uid = FirebaseAuth.getInstance().currentUser?.uid.toString()
        database = FirebaseDatabase.getInstance().getReference("Usuario").child(uid).child("registros")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (reg in snapshot.children) {
                        val registro = reg.getValue(Registro::class.java)
                        dataList.add(registro!!)
                    }
                    recyclerView.adapter = ListadoRegistrosAdapter(applicationContext, dataList)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
            }

        })
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.menu_logout -> {
                cerrarSesion(email, provider, true, prefs)
                val home = Intent(this, Login::class.java)
                startActivity(home)
            }

        }
        return super.onOptionsItemSelected(item)
    }
}