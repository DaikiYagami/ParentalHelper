package com.tomaspev.parentalhelper

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_detalle_registro.*

class DetalleRegistro : AppCompatActivity() {
    private lateinit var dataList: ArrayList<ProgresoContenido?>

    private var email: String? = null
    private var provider: String? = null
    private lateinit var prefs: SharedPreferences.Editor

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_basico, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_registro)

        setSupportActionBar(toolbar_detalle_registro)

        // Código para mostrar los datos del registro seleccionado ==============================================================>>>

        // Se recibe el objeto registro pasado por el intent
        val registro = intent.getSerializableExtra("registro") as Registro

        // Se identifican los campos mostrados en el layout
        val nombre: TextView = findViewById(R.id.tv_detalle_registro_nombre)
        val edad: TextView = findViewById(R.id.tv_detalle_registro_edad)
        val cumple: TextView = findViewById(R.id.tv_detalle_registro_cumple)

        // Se le asignan los valores guardados en el objeto registro a los campos del layout
        nombre.text = registro.nombre
        edad.text = edad(registro.cumple, fechaHoy()).toString()
        cumple.text = registro.cumple

        // Esto muestra el progreso en los contenidos asociados a este registro =================================================>>>
        val recyclerView = findViewById<RecyclerView>(R.id.rv_detalle_registro_contenidos)
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 1, GridLayoutManager.VERTICAL, false)

        dataList = registro.progreso
        if (dataList.size != 0) {
            empty_detalle_registro.setImageResource(0)
        }
        recyclerView.adapter = ProgresoContenidoAdapter(applicationContext, dataList)

        // Valores necesarios para cerrar sesion
        val bundle = intent.extras                           // Variable que rescata los extras que trae el Intent
        email = bundle?.getString("email")              // Variable que rescata el correo
        provider = bundle?.getString("provider")        // Variable que rescata el provider

        prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
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