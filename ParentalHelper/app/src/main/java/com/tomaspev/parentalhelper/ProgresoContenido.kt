package com.tomaspev.parentalhelper

import java.io.Serializable

data class ProgresoContenido (
    val id: Int? = 0,                   // Id del contenido
    val progreso: Int? = 0
        ) : Serializable