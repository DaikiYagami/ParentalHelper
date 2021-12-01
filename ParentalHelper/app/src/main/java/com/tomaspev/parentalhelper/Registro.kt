package com.tomaspev.parentalhelper

import java.io.Serializable

data class Registro (
    var nombre: String? = "empty",
    var cumple: String? = "empty",
    var capacidad: Boolean? = false,
    var progreso: ArrayList<ProgresoContenido?> = arrayListOf()
    // "progreso" deberia ser en realidad una lista que reciba los progresos de contenidos, conformados con el id del contenido y su progreso
    // list = { (1, 50), (2, 30), (3, 10), ... }
        ) : Serializable