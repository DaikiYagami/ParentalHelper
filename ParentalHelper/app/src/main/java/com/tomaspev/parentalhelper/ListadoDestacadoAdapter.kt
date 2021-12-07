package com.tomaspev.parentalhelper

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ListadoDestacadoAdapter(var context: Context): RecyclerView.Adapter<ListadoDestacadoAdapter.ViewHolder>() {

    private var dataList = emptyList<Contenido>()

    internal fun setDataList(dataList: List<Contenido>) {
        this.dataList = dataList
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val titulo: TextView = itemView.findViewById(R.id.tv_titulo)
        val descripcionShort: TextView = itemView.findViewById(R.id.tv_descripcionShort)
        val cardView: CardView = itemView.findViewById(R.id.destacado_cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.destacados2_recyclerview_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]

        holder.titulo.text = data.titulo
        holder.descripcionShort.text = data.descripcionShort

        holder.cardView.setOnClickListener {
            context = holder.itemView.context
            val intent = Intent(context, DetalleContenido::class.java)
            intent.putExtra("contenido", data)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = dataList.size
}