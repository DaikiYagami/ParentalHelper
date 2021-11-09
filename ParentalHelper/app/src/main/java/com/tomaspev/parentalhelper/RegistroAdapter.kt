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
        val genero: TextView = itemView.findViewById(R.id.tv_genero)
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
        holder.edad.text = edad(data.cumple, fechaHoy()).toString()
        holder.genero.text = data.genero
        //holder.cumple.text = formatoFecha(data.cumple, "dd/MM")
        //holder.capacidad.setImageResource()

        holder.cardView.setOnClickListener {
            Toast.makeText(context, "Registro", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount() = dataList.size
}