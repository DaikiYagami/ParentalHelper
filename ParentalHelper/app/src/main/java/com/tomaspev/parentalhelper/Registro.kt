package com.tomaspev.parentalhelper

import java.io.Serializable

data class Registro (
    val nombre: String? = "empty",
    val cumple: String? = "empty",
    val capacidad: Boolean? = false,
    val progreso: ProgresoContenido? = ProgresoContenido(Contenido("empty","","","","",""),50)
        ) : Serializable