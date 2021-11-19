package com.tomaspev.parentalhelper

import java.io.Serializable

data class Registro (
    val nombre: String,
    val cumple: String,
    val genero: String,
    val capacidad: Boolean,
    val progreso: Int,
        ) : Serializable