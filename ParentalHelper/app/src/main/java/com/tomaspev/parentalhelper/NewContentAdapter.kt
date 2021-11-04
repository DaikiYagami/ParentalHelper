package com.tomaspev.parentalhelper
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import kotlinx.android.synthetic.main.elemento_newcontent.view.*
import android.R




class NewContentAdapter            (private val mContext: Context, private val detalle: List<Contenido>) :
    ArrayAdapter<Contenido>(mContext,0,detalle)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.elemento_newcontent, parent, false)
        val Contenido = detalle[position]
        layout.titlenewcontent.text = Contenido.titulo
        layout.descriptionnewcontent.text = Contenido.descripcionShort

        val condition = Contenido.clase
        if (condition == "1") {

            /*val view = NewContentAdapter.getLayoutInflater().inflate(android.R.layout.p1, null)
                    val img: ImageView = view.findById(android.R.id.img_newcontent)
                    return layout*/
            return layout
        } else {
            if (condition == "2") {
                return layout

            } else {
                if (condition == "3") {
                    return layout

                } else {
                    return layout

                }
            }

        }
    }
    }

