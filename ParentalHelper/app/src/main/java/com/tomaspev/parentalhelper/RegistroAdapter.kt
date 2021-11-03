package com.tomaspev.parentalhelper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*
import kotlin.math.absoluteValue

class RegistroAdapter(var context: Context): RecyclerView.Adapter<RegistroAdapter.ViewHolder>() {

    var dataList = emptyList<Registro>()

    internal fun setDataList(dataList: List<Registro>) {
        this.dataList = dataList
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.tv_nombre)
        val edad: TextView = itemView.findViewById(R.id.tv_edad)
        //val cumple: TextView = itemView.findViewById(R.id.tv_cumple)
        val capacidad: ImageButton = itemView.findViewById(R.id.btn_capacidad)

        val cardView: CardView = itemView.findViewById(R.id.registro_cardView) // para hacer click
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegistroAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.registros_recyclerview_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RegistroAdapter.ViewHolder, position: Int) {
        val data = dataList[position]

        holder.nombre.text = data.nombre
        holder.edad.text = daysBetween(data.cumple, todaysDate()).toString()
        //holder.cumple.text = formatoFecha(data.cumple, "dd/MM")
        //holder.capacidad.setImageResource()

        holder.cardView.setOnClickListener {
            Toast.makeText(context, "Registro", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount() = dataList.size

    // Para el formato de fecha que se necesita
    private fun formatoFecha(fecha: String, output: String): String {
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
    private fun todaysDate():String {
        val cal = Calendar.getInstance()
        val dd = cal.get(Calendar.DAY_OF_MONTH)
        val mm = cal.get(Calendar.MONTH) + 1
        val yy = cal.get(Calendar.YEAR)
        return "$dd/$mm/$yy"
    }
    // Devuelve la cantidad de dias entre dos fechas
    private fun daysBetween(fecha1: String, fecha2: String): Int {
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
}