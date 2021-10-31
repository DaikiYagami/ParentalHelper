package com.tomaspev.parentalhelper
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.elemento_newcontent.view.*

class NewContentAdapter            (private val mContext: Context, private val newContent: List<Contenido>) :
    ArrayAdapter<Contenido>(mContext,0,newContent)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.elemento_newcontent, parent, false)
        val Contenido = newContent[position]
        layout.titlenewcontent.text = Contenido.titulo
        layout.descriptionnewcontent.text = Contenido.descripcionShort
        return layout
    }

}