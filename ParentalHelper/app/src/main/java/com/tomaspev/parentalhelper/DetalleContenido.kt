package com.tomaspev.parentalhelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import kotlinx.android.synthetic.main.activity_detalle_contenido.*
import kotlinx.android.synthetic.main.activity_new_content.*

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetalleContenido : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_contenido)

        //crear listado de item seleccionado con solo un elemento


        val listaContent = listOf(contenido)

        val adapter = ContentAdapter(this, listaContent)

        lcontenido.adapter = adapter


    }






    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {

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
    }*/
/*
    fun volverListado(view: View){
        Toast.makeText(this, "Volviendo a Main", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, ListadoCafes::class.java)
        startActivity(intent)
        finish()
    }*/
}