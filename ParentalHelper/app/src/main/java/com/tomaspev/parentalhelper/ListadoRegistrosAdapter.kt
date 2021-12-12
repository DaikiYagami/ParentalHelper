package com.tomaspev.parentalhelper

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class ListadoRegistrosAdapter(var context: Context, private var dataList: ArrayList<Registro>): RecyclerView.Adapter<ListadoRegistrosAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.tv_listado_registros_nombre)
        val edad: TextView = itemView.findViewById(R.id.tv_edad)
        //val actividad: ImageView = itemView.findViewById(R.id.iv_actividad_pendiente)
        val progresoPB: ProgressBar = itemView.findViewById(R.id.pb_listado_registros_progreso)
        val cardView: CardView = itemView.findViewById(R.id.listado_registros_cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.listado_registros_recyclerview_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]

        holder.nombre.text = data.nombre
        holder.edad.text = edad(data.cumple, fechaHoy()).toString()
        holder.progresoPB.progress = 60

        holder.cardView.setOnClickListener {
            context = holder.itemView.context
            val intent = Intent(context, DetalleRegistro::class.java)
            intent.putExtra("registro", data)
            context.startActivity(intent)
            (context as ListadoRegistros).finish()
        }
    }

    override fun getItemCount() = dataList.size
}