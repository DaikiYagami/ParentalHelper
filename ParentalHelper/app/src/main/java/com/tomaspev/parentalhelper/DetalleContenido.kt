package com.tomaspev.parentalhelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_detalle_contenido.*
import android.content.Intent
import kotlinx.android.synthetic.main.activity_main.*


class DetalleContenido : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_contenido)

        //crear listado de item seleccionado con solo un elemento

        val contenido1 = Contenido(
            "Contenido de evaluacion 1",
            "Nucleo Logico Matematico",
            "www.patito.cl",
            "Aca esta la descripcion corta",
            "Aca esta la descripcion larga jaksdjaksjdkajskdajskdjaksdjaksjdkajsdkajsdkaskdjaksdaksdkasjdkajskdjaksdjaksjdkajsdkajsdkjaskdjaksd")
        val contenido2 = Contenido("Contenido de evaluacion 2", "1","www.patito.cl","Aca esta la descripcion corta","Aca esta la descripcion larga jaksdjaksjdkajskdajskdjaksdjaksjdkajsdkajsdkaskdjaksdaksdkasjdkajskdjaksdjaksjdkajsdkajsdkjaskdjaksd")
        val contenido3 = Contenido("Números del 2 al 10", "2","www.patito.cl","Aca esta la descripcion corta","Aca esta la descripcion larga jaksdjaksjdkajskdajskdjaksdjaksjdkajsdkajsdkaskdjaksdaksdkasjdkajskdjaksdjaksjdkajsdkajsdkjaskdjaksd")
        val contenido4 = Contenido("Números del 1 al 10", "3","www.patito.cl","Aca esta la descripcion corta","Aca esta la descripcion larga jaksdjaksjdkajskdajskdjaksdjaksjdkajsdkajsdkaskdjaksdaksdkasjdkajskdjaksdjaksjdkajsdkajsdkjaskdjaksd")
        val contenido5 = Contenido("Números del 2 al 10", "1","www.patito.cl","Aca esta la descripcion corta","Aca esta la descripcion larga jaksdjaksjdkajskdajskdjaksdjaksjdkajsdkajsdkaskdjaksdaksdkasjdkajskdjaksdjaksjdkajsdkajsdkjaskdjaksd")
        val contenido6 = Contenido("Números del 1 al 10", "2","www.patito.cl","Aca esta la descripcion corta","Aca esta la descripcion larga jaksdjaksjdkajskdajskdjaksdjaksjdkajsdkajsdkaskdjaksdaksdkasjdkajskdjaksdjaksjdkajsdkajsdkjaskdjaksd")
        val contenido7 = Contenido("Números del 2 al 10", "3","www.patito.cl","Aca esta la descripcion corta","Aca esta la descripcion larga jaksdjaksjdkajskdajskdjaksdjaksjdkajsdkajsdkaskdjaksdaksdkasjdkajskdjaksdjaksjdkajsdkajsdkjaskdjaksd")
        val contenido8 = Contenido("Números del 1 al 10", "1","www.patito.cl","Aca esta la descripcion corta","Aca esta la descripcion larga jaksdjaksjdkajskdajskdjaksdjaksjdkajsdkajsdkaskdjaksdaksdkasjdkajskdjaksdjaksjdkajsdkajsdkjaskdjaksd")

        val listaContent = listOf(contenido1 , contenido3,contenido5,contenido7, contenido2,contenido4,contenido6,contenido8)

        //Prueba de filtro

        /*val objetoIntent: Intent= intent
        var numero = objetoIntent.getStringExtra("num")
        var con = "contenido"*/

        //----------------
        var bundle = intent.extras
        textView.text=bundle.getString("dt")


        //-----------------------
        var listaContent2 = listaContent.filter{ it ==contenido2}//aparece solo el que acompaña al it   it == contenido2

        //-------------------------------------------------------
        val adapter = ContentAdapter(this, listaContent2)
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


