package com.tomaspev.parentalhelper

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.absoluteValue

class RegistroAdapter(var context: Context, var dataList: ArrayList<Registro>): RecyclerView.Adapter<RegistroAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.tv_nombre)
        val img: ImageView = itemView.findViewById(R.id.iv_registro_main)
        val cardView: CardView = itemView.findViewById(R.id.registro_cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.registros_recyclerview_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]

        if (position != dataList.size - 1) {
            holder.nombre.text = data.nombre
            holder.cardView.setOnClickListener {
                context = holder.itemView.context
                val intent = Intent(context, DetalleRegistro::class.java)
                intent.putExtra("registro", data)
                context.startActivity(intent)
            }
        }
        // Boton agregar nuevo registro
        else {
            holder.nombre.text = "Nuevo"
            holder.img.setBackgroundColor(ContextCompat.getColor(context, R.color.text))
            holder.img.setImageResource(R.drawable.add)
            holder.cardView.setOnClickListener {
                context = holder.itemView.context
                val intent = Intent(context, IngresoRegistro::class.java)
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount() = dataList.size
}