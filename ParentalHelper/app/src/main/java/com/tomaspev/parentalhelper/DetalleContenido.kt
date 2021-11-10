package com.tomaspev.parentalhelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetalleContenido : AppCompatActivity() {

    private lateinit var database : AppDatabase
    private lateinit var contenido : Contenido
    private lateinit var contenidoLiveData :LiveData<Contenido>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_contenido)

        database = AppDatabase.getDatabase(this)
        val idcontenido = intent.getIntExtra("id", 0)

        contenidoLiveData = database.contenido().get(idcontenido)
        contenidoLiveData.observe(this, {contenido = it

            nombre_cafe_d.text = contenido.nombre
        })


    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.editar -> {
                val intent = Intent(this, ingresar_cafe::class.java)
                intent.putExtra("cafe", cafe)
                startActivity(intent)
            }
            R.id.eliminar -> {
                cafeLiveData.removeObservers(this)
                CoroutineScope(Dispatchers.IO).launch {
                    database.cafes().delete(cafe)
                    this@CafeDescripcion.finish()
                }
            }
        }


        return super.onOptionsItemSelected(item)
    }

    fun volverListado(view: View){
        Toast.makeText(this, "Volviendo a Main", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, ListadoCafes::class.java)
        startActivity(intent)
        finish()
    }
}