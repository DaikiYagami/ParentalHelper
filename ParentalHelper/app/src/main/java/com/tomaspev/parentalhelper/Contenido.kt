package com.tomaspev.parentalhelper

import android.text.Html
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "contenido")
class Contenido(
    val titulo:String,
    val clase:String,
    val enlaceWeb:String,
    val descripcionShort:String,
    val descripcionLong:String,
    val tipo: String,
    @PrimaryKey(autoGenerate = true)
    var idContenido: Int = 0

) : Serializable
