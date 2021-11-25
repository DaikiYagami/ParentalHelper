package com.tomaspev.parentalhelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_listado_registros.*

class ListadoRegistros : AppCompatActivity() {
    private lateinit var registroAdapter: ListadoRegistrosAdapter
    private lateinit var dataList: List<Registro>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_registros)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_lista_registros)
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 1)
        registroAdapter = ListadoRegistrosAdapter(applicationContext)
        recyclerView.adapter = registroAdapter

        dataList = listOf(
            Registro("Manuel", "12/04/2019", "H", false, 20),
            Registro("Jose", "15/07/2018", "H", true, 40),
            Registro("Martina", "23/09/2017", "M", false, 80)
        )
        registroAdapter.setDataList(dataList)

        // Resto del código =======================================================================

        fab.setOnClickListener {
            val intent = Intent(this, IngresoRegistro::class.java)
            startActivity(intent)
        }
    }
}