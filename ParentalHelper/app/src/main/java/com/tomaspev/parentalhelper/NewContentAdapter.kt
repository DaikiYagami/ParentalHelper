package com.tomaspev.parentalhelper
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.elemento_newcontent.*
import kotlinx.android.synthetic.main.elemento_newcontent.view.*
import kotlinx.android.synthetic.main.registros_recyclerview_layout.view.*

class NewContentAdapter(private val mContext: Context, private val detalle: List<Contenido>) :
    ArrayAdapter<Contenido>(mContext,0,detalle) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.elemento_newcontent, parent, false)
        val contenido = detalle[position]

        layout.titlenewcontent.text = contenido.titulo
        layout.descriptionnewcontent.text = contenido.descripcionShort

        val condition = contenido.clase
        if (condition == "1") {
            layout.img_newcontent.setImageResource(R.drawable.p1)
            return layout
        } else {
            if (condition == "2") {
                layout.img_newcontent.setImageResource(R.drawable.p2)
                return layout
            } else {
                if (condition == "3") {
                    layout.img_newcontent.setImageResource(R.drawable.p3)
                    return layout
                } else {
                    return layout
                }
            }

        }
    }

}

