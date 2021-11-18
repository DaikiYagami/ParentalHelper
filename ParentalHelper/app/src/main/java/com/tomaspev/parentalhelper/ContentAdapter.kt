package com.tomaspev.parentalhelper
import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.elemento_newcontent.*
import kotlinx.android.synthetic.main.elemento_newcontent.view.*
import kotlinx.android.synthetic.main.registros_recyclerview_layout.view.*
import android.graphics.drawable.Drawable
import android.graphics.drawable.*
import kotlinx.android.synthetic.main.elemento_contenido.view.*


class ContentAdapter(private val mContext: Context, private val detalle: List<Contenido>) :
    ArrayAdapter<Contenido>(mContext,0,detalle) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.elemento_contenido, parent, false)
        val contenido = detalle[position]



        layout.titlecontent.text = contenido.titulo
        layout.descriptioncontent.text = contenido.descripcionShort


        var condition = contenido.clase

        if (condition == "1") {
            layout.img_content.setImageResource(R.drawable.p1)
            return layout
        } else {
            if (condition == "2") {
                layout.img_content.setImageResource(R.drawable.p2)
                return layout
            } else {
                layout.img_content.setImageResource(R.drawable.p3)
                return layout
            }

        }

    }

}