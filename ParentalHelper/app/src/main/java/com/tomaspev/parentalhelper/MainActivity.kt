package com.tomaspev.parentalhelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // Registros
    private lateinit var registroAdapter: RegistroAdapter
    private lateinit var dataListR: List<Registro>
    // Destacados
    private lateinit var destacadoAdapter: DestacadoAdapter
    private lateinit var dataListD: List<Destacado>
    // Nuevos Contenidos
    private lateinit var contenidoNuevoAdapter: ContenidoNuevoAdapter
    private lateinit var dataListC: List<ContenidoNuevo>

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

        // Destacados ===========================================================================================================
        val recyclerViewD = findViewById<RecyclerView>(R.id.rv_destacados)
        recyclerViewD.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        destacadoAdapter = DestacadoAdapter(applicationContext)
        recyclerViewD.adapter = destacadoAdapter

        dataListD = listOf(
            Destacado("Destacado 1"),
            Destacado("Destacado 2"),
            Destacado("Destacado 3"),
            Destacado("Destacado 4")
        )
        destacadoAdapter.setDataList((dataListD))

        // Contenido Nuevo ======================================================================================================
        val recyclerViewC = findViewById<RecyclerView>(R.id.rv_nuevos_contenidos)
        recyclerViewC.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        contenidoNuevoAdapter = ContenidoNuevoAdapter(applicationContext)
        recyclerViewC.adapter = contenidoNuevoAdapter

        dataListC = listOf(
            ContenidoNuevo("Contenido 1"),
            ContenidoNuevo("Contenido 2"),
            ContenidoNuevo("Contenido 3"),
            ContenidoNuevo("Contenido 4")
        )
        contenidoNuevoAdapter.setDataList((dataListC))

        // Resto del código =====================================================================================================

        tv_todos_registros.setOnClickListener {
            val intent = Intent(this, DetalleRegistro::class.java)
            startActivity(intent)
        }
    }
}