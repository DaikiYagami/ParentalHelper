package com.tomaspev.parentalhelper

import java.text.SimpleDateFormat
import java.util.*

// Para el formato de fecha que se necesita
fun formatoFecha(fecha: String, output: String): String {
    val formattedDate: String
    when (output) {
        "dd/MM" -> {
            val parser = SimpleDateFormat("dd/MM/yyyy")
            val formatter = SimpleDateFormat("dd/MM")
            formattedDate = formatter.format(parser.parse(fecha))
        }
        "dd" -> {
            val parser = SimpleDateFormat("dd/MM/yyyy")
            val formatter = SimpleDateFormat("dd")
            formattedDate = formatter.format(parser.parse(fecha))
        }
        "MM" -> {
            val parser = SimpleDateFormat("dd/MM/yyyy")
            val formatter = SimpleDateFormat("MM")
            formattedDate = formatter.format(parser.parse(fecha))
        }
        "yyyy" -> {
            val parser = SimpleDateFormat("dd/MM/yyyy")
            val formatter = SimpleDateFormat("yyyy")
            formattedDate = formatter.format(parser.parse(fecha))
        }
        else -> {
            formattedDate = fecha
        }
    }
    return formattedDate
}
// Devuelve la fecha de hoy
fun fechaHoy():String {
    val cal = Calendar.getInstance()
    val dd = cal.get(Calendar.DAY_OF_MONTH)
    val mm = cal.get(Calendar.MONTH) + 1
    val yy = cal.get(Calendar.YEAR)
    return "$dd/$mm/$yy"
}
// Devuelve la cantidad de dias entre dos fechas
fun edad(f1: String?, fecha2: String): Int {
    val fecha1 = f1.toString()
    val dd1 = formatoFecha(fecha1, "dd")
    val dd2 = formatoFecha(fecha2, "dd")
    val mm1 = formatoFecha(fecha1, "MM")
    val mm2 = formatoFecha(fecha2, "MM")
    val yy1 = formatoFecha(fecha1,"yyyy")
    val yy2 = formatoFecha(fecha2,"yyyy")

    var diff = yy2.toInt() - yy1.toInt()

    if (mm2.toInt() < mm1.toInt() || (mm2.toInt() == mm1.toInt() && dd2.toInt() < dd1.toInt())) {
        diff--
    }
    return diff
}