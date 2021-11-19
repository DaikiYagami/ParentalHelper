package com.tomaspev.parentalhelper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProgresoContenidoAdapter(var context: Context): RecyclerView.Adapter<ProgresoContenidoAdapter.ViewHolder>() {

    private var dataList = emptyList<ProgresoContenido>()

    internal fun setDataList(dataList: List<ProgresoContenido>) {
        this.dataList = dataList
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val contenido: TextView = itemView.findViewById(R.id.tv_detalle_registro_contenido)
        val titulo: TextView = itemView.findViewById(R.id.tv_detalle_registro_contenido_titulo)
        val progresoTV: TextView = itemView.findViewById(R.id.tv_detalle_registro_progreso)
        val progresoPB: ProgressBar = itemView.findViewById(R.id.pb_detalle_registro_progreso)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgresoContenidoAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.progreso_contenido_recyclerview_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]

        holder.contenido.text = data.contenido.titulo
        holder.titulo.text = data.contenido.titulo
        holder.progresoTV.text = data.progreso.toString() + "%"
        holder.progresoPB.progress = data.progreso
    }

    override fun getItemCount() = dataList.size
}