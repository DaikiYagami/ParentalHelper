package com.tomaspev.parentalhelper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class RegistroAdapter(var context: Context): RecyclerView.Adapter<RegistroAdapter.ViewHolder>() {

    var dataList = emptyList<Registro>()

    internal fun setDataList(dataList: List<Registro>) {
        this.dataList = dataList
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.tv_nombre)
        val cardView: CardView = itemView.findViewById(R.id.registro_cardView) // para hacer click
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegistroAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.registros_recyclerview_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RegistroAdapter.ViewHolder, position: Int) {
        val data = dataList[position]

        holder.nombre.text = data.nombre

        holder.cardView.setOnClickListener {
            Toast.makeText(context, "click", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount() = dataList.size
}