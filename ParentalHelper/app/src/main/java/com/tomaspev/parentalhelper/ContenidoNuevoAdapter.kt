package com.tomaspev.parentalhelper

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ContenidoNuevoAdapter(var context: Context): RecyclerView.Adapter<ContenidoNuevoAdapter.ViewHolder>() {

    private var dataList = emptyList<Contenido>()

    internal fun setDataList(dataList: List<Contenido>) {
        this.dataList = dataList
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val titulo: TextView = itemView.findViewById(R.id.tv_titulo)
        val cardView: CardView = itemView.findViewById(R.id.contenidon_cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contenido_nuevo_recyclerview_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]

        holder.titulo.text = data.titulo

        holder.cardView.setOnClickListener {
            context = holder.itemView.context
            val intent = Intent(context, DetalleContenido::class.java)
            intent.putExtra("activity", "Main")
            intent.putExtra("contenido", data)
            context.startActivity(intent)
            (context as MainActivity).finish()
        }
    }

    override fun getItemCount() = dataList.size
}