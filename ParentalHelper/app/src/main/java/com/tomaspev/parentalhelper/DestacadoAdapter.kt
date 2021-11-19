package com.tomaspev.parentalhelper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class DestacadoAdapter(var context: Context): RecyclerView.Adapter<DestacadoAdapter.ViewHolder>() {

    private var dataList = emptyList<Contenido>()

    internal fun setDataList(dataList: List<Contenido>) {
        this.dataList = dataList
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val titulo: TextView = itemView.findViewById(R.id.tv_titulo)
        val cardView: CardView = itemView.findViewById(R.id.destacado_cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestacadoAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.destacados_recyclerview_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]

        holder.titulo.text = data.titulo

        holder.cardView.setOnClickListener {
            Toast.makeText(context, "Destacado", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount() = dataList.size
}