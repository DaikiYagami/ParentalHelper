package com.tomaspev.parentalhelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DetalleRegistro : AppCompatActivity() {
    private lateinit var progresoContenidoAdapter: ProgresoContenidoAdapter
    private lateinit var dataList: List<ProgresoContenido>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_registro)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_detalle_registro_contenidos)
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 2, GridLayoutManager.HORIZONTAL, false)
        progresoContenidoAdapter = ProgresoContenidoAdapter(applicationContext)
        recyclerView.adapter = progresoContenidoAdapter

        dataList = listOf(
            ProgresoContenido(Contenido("Contenido 1", "Destacado"), 60),
            ProgresoContenido(Contenido("Contenido 2", "Nuevo"), 20),
            ProgresoContenido(Contenido("Contenido 3", "Destacado"), 40),
            ProgresoContenido(Contenido("Contenido 4", "Nuevo"), 60),
            ProgresoContenido(Contenido("Contenido 5", "Destacado"), 80),
            ProgresoContenido(Contenido("Contenido 6", "Nuevo"), 10),
            ProgresoContenido(Contenido("Contenido 7", "Destacado"), 90),
            ProgresoContenido(Contenido("Contenido 8", "Nuevo"), 50)
        )

        progresoContenidoAdapter.setDataList(dataList)
    }
}