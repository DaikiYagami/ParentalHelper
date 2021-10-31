package com.tomaspev.parentalhelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_new_content.*

class NewContent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_content)

        val contenido1 = Contenido("Números del 1 al 10", "Nucleo Logico Matematico","www.patito.cl","Aca esta la descripcion corta","Aca esta la descripcion larga jaksdjaksjdkajskdajskdjaksdjaksjdkajsdkajsdkaskdjaksdaksdkasjdkajskdjaksdjaksjdkajsdkajsdkjaskdjaksd")
        val contenido2 = Contenido("Números del 1 al 10", "Nucleo Logico Matematico","www.patito.cl","Aca esta la descripcion corta","Aca esta la descripcion larga jaksdjaksjdkajskdajskdjaksdjaksjdkajsdkajsdkaskdjaksdaksdkasjdkajskdjaksdjaksjdkajsdkajsdkjaskdjaksd")
        val contenido3 = Contenido("Números del 1 al 10", "Nucleo Logico Matematico","www.patito.cl","Aca esta la descripcion corta","Aca esta la descripcion larga jaksdjaksjdkajskdajskdjaksdjaksjdkajsdkajsdkaskdjaksdaksdkasjdkajskdjaksdjaksjdkajsdkajsdkjaskdjaksd")
        val contenido4 = Contenido("Números del 1 al 10", "Nucleo Logico Matematico","www.patito.cl","Aca esta la descripcion corta","Aca esta la descripcion larga jaksdjaksjdkajskdajskdjaksdjaksjdkajsdkajsdkaskdjaksdaksdkasjdkajskdjaksdjaksjdkajsdkajsdkjaskdjaksd")



        val listaNContent = listOf(contenido1 , contenido3)
        val listaNContent2 = listOf(contenido2 , contenido4)

        val adapter1 = NewContentAdapter(this, listaNContent)
        val adapter2 = NewContentAdapter(this, listaNContent2)



    }
}