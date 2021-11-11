package com.tomaspev.parentalhelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_new_content.*
import kotlinx.android.synthetic.main.elemento_newcontent.view.*

class NewContent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_content)


        //valores lista
        val contenido1 = Contenido(
            "Números del 2 al 10",
            "Nucleo Logico Matematico",
            "www.patito.cl",
            "Aca esta la descripcion corta",
            "Aca esta la descripcion larga jaksdjaksjdkajskdajskdjaksdjaksjdkajsdkajsdkaskdjaksdaksdkasjdkajskdjaksdjaksjdkajsdkajsdkjaskdjaksd")
        val contenido2 = Contenido("Números del 1 al 10", "1","www.patito.cl","Aca esta la descripcion corta","Aca esta la descripcion larga jaksdjaksjdkajskdajskdjaksdjaksjdkajsdkajsdkaskdjaksdaksdkasjdkajskdjaksdjaksjdkajsdkajsdkjaskdjaksd")
        val contenido3 = Contenido("Números del 2 al 10", "2","www.patito.cl","Aca esta la descripcion corta","Aca esta la descripcion larga jaksdjaksjdkajskdajskdjaksdjaksjdkajsdkajsdkaskdjaksdaksdkasjdkajskdjaksdjaksjdkajsdkajsdkjaskdjaksd")
        val contenido4 = Contenido("Números del 1 al 10", "3","www.patito.cl","Aca esta la descripcion corta","Aca esta la descripcion larga jaksdjaksjdkajskdajskdjaksdjaksjdkajsdkajsdkaskdjaksdaksdkasjdkajskdjaksdjaksjdkajsdkajsdkjaskdjaksd")
        val contenido5 = Contenido("Números del 2 al 10", "1","www.patito.cl","Aca esta la descripcion corta","Aca esta la descripcion larga jaksdjaksjdkajskdajskdjaksdjaksjdkajsdkajsdkaskdjaksdaksdkasjdkajskdjaksdjaksjdkajsdkajsdkjaskdjaksd")
        val contenido6 = Contenido("Números del 1 al 10", "2","www.patito.cl","Aca esta la descripcion corta","Aca esta la descripcion larga jaksdjaksjdkajskdajskdjaksdjaksjdkajsdkajsdkaskdjaksdaksdkasjdkajskdjaksdjaksjdkajsdkajsdkjaskdjaksd")
        val contenido7 = Contenido("Números del 2 al 10", "3","www.patito.cl","Aca esta la descripcion corta","Aca esta la descripcion larga jaksdjaksjdkajskdajskdjaksdjaksjdkajsdkajsdkaskdjaksdaksdkasjdkajskdjaksdjaksjdkajsdkajsdkjaskdjaksd")
        val contenido8 = Contenido("Números del 1 al 10", "1","www.patito.cl","Aca esta la descripcion corta","Aca esta la descripcion larga jaksdjaksjdkajskdajskdjaksdjaksjdkajsdkajsdkaskdjaksdaksdkasjdkajskdjaksdjaksjdkajsdkajsdkjaskdjaksd")



        val listaNContent = listOf(contenido1 , contenido3,contenido5,contenido7)
        val listaNContent2 = listOf(contenido2 , contenido4,contenido6,contenido8)

        val adapter1 = NewContentAdapter(this, listaNContent)
        val adapter2 = NewContentAdapter(this, listaNContent2)

        listado1.adapter = adapter1
        listado2.adapter = adapter2


        //HACER CLIKEABLE LOS ITEM DE LA LISTA
        listado1.setOnItemClickListener{ parent, view, position, id->
            val intent = Intent(this, DetalleContenido::class.java)
            intent.putExtra("id", listaNContent[position].idContenido)
            startActivity(intent)
        }
        listado2.setOnItemClickListener{ parent, view, position, id->
            val intent = Intent(this, DetalleContenido::class.java)
            intent.putExtra("id", listaNContent2[position].idContenido)
            startActivity(intent)
        }


    }
}