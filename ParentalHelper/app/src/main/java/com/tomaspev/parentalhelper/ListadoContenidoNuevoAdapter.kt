package com.tomaspev.parentalhelper

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ListadoContenidoNuevoAdapter(var context: Context): RecyclerView.Adapter<ListadoContenidoNuevoAdapter.ViewHolder>() {

    private var dataList = emptyList<Contenido>()

    internal fun setDataList(dataList: List<Contenido>) {
        this.dataList = dataList
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val titulo: TextView = itemView.findViewById(R.id.tv_titulo)
        /*val descripcionShort: TextView = itemView.findViewById(R.id.tv_descripcionShort)*/
        val cardView: CardView = itemView.findViewById(R.id.contenidon_cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contenido_nuevo2_recyclerview_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]

        holder.titulo.text = data.titulo
        /*holder.descripcionShort.text = data.descripcionShort*/

        holder.cardView.setOnClickListener {
            context = holder.itemView.context
            val intent = Intent(context, DetalleContenido::class.java)
            intent.putExtra("activity", "NewContent")
            intent.putExtra("contenido", data)
            context.startActivity(intent)
            (context as NewContent).finish()
        }
    }

    override fun getItemCount() = dataList.size
}