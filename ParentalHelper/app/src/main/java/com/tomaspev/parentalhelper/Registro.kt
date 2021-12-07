package com.tomaspev.parentalhelper

import java.io.Serializable

data class Registro (
    var nombre: String? = "empty",
    var cumple: String? = "empty",
    var capacidad: Boolean? = false,
    var progreso: ArrayList<ProgresoContenido?> = arrayListOf()
        ) : Serializable