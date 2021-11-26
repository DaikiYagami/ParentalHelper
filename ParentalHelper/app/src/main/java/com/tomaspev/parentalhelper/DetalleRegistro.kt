package com.tomaspev.parentalhelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DetalleRegistro : AppCompatActivity() {
    private lateinit var progresoContenidoAdapter: ProgresoContenidoAdapter
    private lateinit var dataList: List<ProgresoContenido>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_registro)

        // Código para mostrar los datos del registro seleccionado ==============================================================>>>

        // Se recibe el objeto registro pasado por el intent
        val registro = intent.getSerializableExtra("registro") as Registro

        // Se identifican los campos mostrados en el layout
        val nombre: TextView = findViewById(R.id.tv_detalle_registro_nombre)
        val edad: TextView = findViewById(R.id.tv_detalle_registro_edad)
        val cumple: TextView = findViewById(R.id.tv_detalle_registro_cumple)
        val genero: TextView = findViewById(R.id.tv_detalle_registro_genero)

        // Se le asignan los valores guardados en el objeto registro a los campos del layout
        nombre.text = registro.nombre
        edad.text = edad(registro.cumple, fechaHoy()).toString()
        cumple.text = registro.cumple

        // Esto muestra el progreso en los contenidos asociados a este registro =================================================>>>
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