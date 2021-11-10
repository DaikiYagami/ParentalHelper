package com.tomaspev.parentalhelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

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
            Contenido("Contenido 1", "Destacado"),
            Contenido("Contenido 2", "Nuevo"),
            Contenido("Contenido 3", "Destacado"),
            Contenido("Contenido 4", "Nuevo"),
            Contenido("Contenido 5", "Destacado"),
            Contenido("Contenido 6", "Nuevo")
        )

        // Lista Contenidos Destacados
        val dataListD = dataListC.filter { it.tipo == "Nuevo" }
        destacadoAdapter.setDataList(dataListD)

        // Lista Contenidos Nuevos
        val dataListNC = dataListC.filter { it.tipo == "Destacado" }
        contenidoNuevoAdapter.setDataList(dataListNC)

        // Resto del código =====================================================================================================

        tv_todos_registros.setOnClickListener {
            val intent = Intent(this, ListadoRegistros::class.java)
            startActivity(intent)
        }
    }
}