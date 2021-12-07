package com.tomaspev.parentalhelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_volver, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_registros)

        recyclerView = findViewById(R.id.rv_lista_registros)
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 1)

        /*
        dataList = arrayListOf(
            Registro("Manuel", "12/04/2019", false),
            Registro("Jose", "15/07/2018", true),
            Registro("Martina", "23/09/2017", false)
        )*/
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
                TODO("Not yet implemented")
            }

        })
    }
}