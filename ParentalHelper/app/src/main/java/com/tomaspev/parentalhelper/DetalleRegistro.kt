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
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 1, GridLayoutManager.VERTICAL, false)
        progresoContenidoAdapter = ProgresoContenidoAdapter(applicationContext)
        recyclerView.adapter = progresoContenidoAdapter

        dataList = listOf(
            ProgresoContenido(Contenido("Contenido de evaluacion 1","2","www.patito.cl","","", "Destacado"), 60),
            ProgresoContenido(Contenido("Contenido de evaluacion 1","2","www.patito.cl","","", "Destacado"), 20),
            ProgresoContenido(Contenido("Contenido de evaluacion 1","2","www.patito.cl","","", "Destacado"), 40),
            ProgresoContenido(Contenido("Contenido de evaluacion 1","2","www.patito.cl","","", "Destacado"), 60),
            ProgresoContenido(Contenido("Contenido de evaluacion 1","2","www.patito.cl","","", "Destacado"), 80),
            ProgresoContenido(Contenido("Contenido de evaluacion 1","2","www.patito.cl","","", "Destacado"), 10),
            ProgresoContenido(Contenido("Contenido de evaluacion 1","2","www.patito.cl","","", "Destacado"), 90),
            ProgresoContenido(Contenido("Contenido de evaluacion 1","2","www.patito.cl","","", "Destacado"), 50)
        )

        progresoContenidoAdapter.setDataList(dataList)
    }
}