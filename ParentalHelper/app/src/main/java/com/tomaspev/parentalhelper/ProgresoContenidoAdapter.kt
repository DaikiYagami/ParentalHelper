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
    private lateinit var registro: Registro

    internal fun setDataList(dataList: List<ProgresoContenido>, registro: Registro) {
        this.dataList = dataList
        this.registro = registro
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val contenido: TextView = itemView.findViewById(R.id.tv_detalle_registro_contenido)
        val titulo: TextView = itemView.findViewById(R.id.tv_detalle_registro_contenido_titulo)
        val genero: TextView = itemView.findViewById(R.id.tv_detalle_registro_genero)
        val progresoTV: TextView = itemView.findViewById(R.id.tv_detalle_registro_progreso)
        val progresoPB: ProgressBar = itemView.findViewById(R.id.pb_detalle_registro_progreso)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgresoContenidoAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.progreso_contenido_recyclerview_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]

        holder.contenido.text = "titulo" //data.contenido.titulo // Falta ingresar contenidos y acceder a ellos a traves de su id
        holder.titulo.text = "contenido" //data.contenido.titulo
        holder.progresoTV.text = data.progreso.toString() + "%"
        holder.progresoPB.progress = data.progreso!!.toInt()

        //holder.genero.text = registro.progreso[0]
    }

    override fun getItemCount() = dataList.size
}