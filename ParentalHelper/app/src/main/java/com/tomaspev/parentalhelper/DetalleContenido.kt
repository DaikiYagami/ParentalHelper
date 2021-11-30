package com.tomaspev.parentalhelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_detalle_contenido.*
import android.content.Intent
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class DetalleContenido : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_contenido)
        // Código para mostrar los datos del contenido seleccionado ==============================================================>>>



        // Se recibe el objeto contenido pasado por el intent
        val contenido = intent.getSerializableExtra("contenido") as Contenido

        // Se identifican los campos mostrados en el layout
        val titulo: TextView = findViewById(R.id.tv_detalle_contenido_titulo)
        val ambito: TextView = findViewById(R.id.tv_detalle_contenido_ambito)
        val descripcionShort: TextView = findViewById(R.id.tv_detalle_contenido_descripcionShort)


        // Se le asignan los valores guardados en el objeto registro a los campos del layout
        titulo.text = contenido.titulo
        ambito.text = contenido.ambito
        descripcionShort.text = contenido.descripcionShort




/*
        //crear listado de item seleccionado con solo un elemento

        val contenido1 = Contenido(
            "Contenido de evaluacion 1",
            "Nucleo Logico Matematico",
            "www.patito.cl",
            "Aca esta la descripcion corta",
            "Aca esta la descripcion larga jaksdjaksjdkajskdajskdjaksdjaksjdkajsdkajsdkaskdjaksdaksdkasjdkajskdjaksdjaksjdkajsdkajsdkjaskdjaksd", "Destacado")
        val contenido2 = Contenido("Contenido de evaluacion 2", "1","www.patito.cl","Aca esta la descripcion corta","Aca esta la descripcion larga jaksdjaksjdkajskdajskdjaksdjaksjdkajsdkajsdkaskdjaksdaksdkasjdkajskdjaksdjaksjdkajsdkajsdkjaskdjaksd", "Destacado")
        val contenido3 = Contenido("Números del 2 al 10", "2","www.patito.cl","Aca esta la descripcion corta","Aca esta la descripcion larga jaksdjaksjdkajskdajskdjaksdjaksjdkajsdkajsdkaskdjaksdaksdkasjdkajskdjaksdjaksjdkajsdkajsdkjaskdjaksd", "Destacado")
        val contenido4 = Contenido("Números del 1 al 10", "3","www.patito.cl","Aca esta la descripcion corta","Aca esta la descripcion larga jaksdjaksjdkajskdajskdjaksdjaksjdkajsdkajsdkaskdjaksdaksdkasjdkajskdjaksdjaksjdkajsdkajsdkjaskdjaksd", "Destacado")
        val contenido5 = Contenido("Números del 2 al 10", "1","www.patito.cl","Aca esta la descripcion corta","Aca esta la descripcion larga jaksdjaksjdkajskdajskdjaksdjaksjdkajsdkajsdkaskdjaksdaksdkasjdkajskdjaksdjaksjdkajsdkajsdkjaskdjaksd", "Destacado")
        val contenido6 = Contenido("Números del 1 al 10", "2","www.patito.cl","Aca esta la descripcion corta","Aca esta la descripcion larga jaksdjaksjdkajskdajskdjaksdjaksjdkajsdkajsdkaskdjaksdaksdkasjdkajskdjaksdjaksjdkajsdkajsdkjaskdjaksd", "Destacado")
        val contenido7 = Contenido("Números del 2 al 10", "3","www.patito.cl","Aca esta la descripcion corta","Aca esta la descripcion larga jaksdjaksjdkajskdajskdjaksdjaksjdkajsdkajsdkaskdjaksdaksdkasjdkajskdjaksdjaksjdkajsdkajsdkjaskdjaksd", "Destacado")
        val contenido8 = Contenido("Números del 1 al 10", "1","www.patito.cl","Aca esta la descripcion corta","Aca esta la descripcion larga jaksdjaksjdkajskdajskdjaksdjaksjdkajsdkajsdkaskdjaksdaksdkasjdkajskdjaksdjaksjdkajsdkajsdkjaskdjaksd", "Destacado")

        val listaContent = listOf(contenido1 , contenido3,contenido5,contenido7, contenido2,contenido4,contenido6,contenido8)

        //Prueba de filtro

        /*val objetoIntent: Intent= intent
        var numero = objetoIntent.getStringExtra("num")
        var con = "contenido"*/

        //----------------
        var bundle = intent.extras
        /*textView.text=bundle.getString("dt")*/


        //-----------------------
        var listaContent2 = listaContent.filter{ it ==contenido2}//aparece solo el que acompaña al it   it == contenido2

        //-------------------------------------------------------
        val adapter = ContentAdapter(this, listaContent2)
        lcontenido.adapter = adapter

*/

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


