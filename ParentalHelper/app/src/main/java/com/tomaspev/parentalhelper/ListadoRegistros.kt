package com.tomaspev.parentalhelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_listado_registros.*

class ListadoRegistros : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var dataList: ArrayList<Registro>
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_registros)

        recyclerView = findViewById<RecyclerView>(R.id.rv_lista_registros)
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
            val intent = Intent(this, IngresoRegistro::class.java)
            startActivity(intent)
        }
    }

    private fun getData() {
        val uid = FirebaseAuth.getInstance().currentUser?.uid.toString()
        database = FirebaseDatabase.getInstance().getReference("Usuario").child(uid).child("registros")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (usr in snapshot.children) {
                        val user = usr.getValue(Registro::class.java)
                        dataList.add(user!!)
                    }
                    recyclerView.adapter = ListadoRegistrosAdapter(applicationContext, dataList)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
    // Temporal, solo para tener algunas cosas de referencia
    private fun readData(uid: String) {
        database = FirebaseDatabase.getInstance().getReference("Usuario")
        database.child(uid).child("nombresReg").get().addOnSuccessListener {
            if (it.exists()) {
                val registros = it.value
                /*for (name in list) {

                }*/
                //val correo = it.child("email").value
                //val contra = it.child("password").value
                //tv_uid.text = registros.toString()
            }
            else {
                Toast.makeText(this, "User doesn't exists", Toast.LENGTH_SHORT).show()
            }
        }
    }
}