package com.tomaspev.parentalhelper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ContenidoNuevoAdapter(var context: Context): RecyclerView.Adapter<ContenidoNuevoAdapter.ViewHolder>() {

    private var dataList = emptyList<ContenidoNuevo>()

    internal fun setDataList(dataList: List<ContenidoNuevo>) {
        this.dataList = dataList
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val titulo: TextView = itemView.findViewById(R.id.tv_titulo)
        val cardView: CardView = itemView.findViewById(R.id.destacado_cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContenidoNuevoAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contenido_nuevo_recyclerview_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]

        holder.titulo.text = data.titulo

        holder.cardView.setOnClickListener {
            Toast.makeText(context, "Contenido Nuevo", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount() = dataList.size
}