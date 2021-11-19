package com.tomaspev.parentalhelper

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class ListadoRegistrosAdapter(var context: Context): RecyclerView.Adapter<ListadoRegistrosAdapter.ViewHolder>() {

    private var dataList = emptyList<Registro>()

    internal fun setDataList(dataList: List<Registro>) {
        this.dataList = dataList
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.tv_listado_registros_nombre)
        val edad: TextView = itemView.findViewById(R.id.tv_edad)
        val genero: TextView = itemView.findViewById(R.id.tv_listado_registros_genero)
        val progresoPB: ProgressBar = itemView.findViewById(R.id.pb_listado_registros_progreso)
        val progresoTV: TextView = itemView.findViewById(R.id.tv_listado_registros_progreso)
        val cardView: CardView = itemView.findViewById(R.id.listado_registros_cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListadoRegistrosAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.listado_registros_recyclerview_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]

        holder.nombre.text = data.nombre
        holder.edad.text = edad(data.cumple, fechaHoy()).toString()
        holder.genero.text = data.genero
        holder.progresoTV.text = data.progreso.toString() + "%"
        holder.progresoPB.progress = data.progreso

        holder.cardView.setOnClickListener {
            context = holder.itemView.context
            val intent = Intent(context, DetalleRegistro::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = dataList.size
}