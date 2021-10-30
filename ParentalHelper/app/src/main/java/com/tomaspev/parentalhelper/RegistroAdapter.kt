package com.tomaspev.parentalhelper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class RegistroAdapter(var context: Context): RecyclerView.Adapter<RegistroAdapter.ViewHolder>() {

    var dataList = emptyList<Registro>()

    internal fun setDataList(dataList: List<Registro>) {
        this.dataList = dataList
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var nombre: TextView = itemView.findViewById(R.id.tv_nombre)
        // var cardView: CardView = itemView.findViewById(R.id.registro_cardView) // para hacer click
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegistroAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.registros_recyclerview_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RegistroAdapter.ViewHolder, position: Int) {
        val data = dataList[position]

        holder.nombre.text = data.nombre
    }

    override fun getItemCount() = dataList.size
}