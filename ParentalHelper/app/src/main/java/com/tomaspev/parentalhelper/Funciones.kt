package com.tomaspev.parentalhelper

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*

// Toma los datos solicitados de la DB
fun getData(recyclerView: RecyclerView, dataList: ArrayList<Registro>, context: Context, img: ImageView?, adapter: String, ref: String, bundle: Bundle?) {
    dataList.clear()
    val uid = FirebaseAuth.getInstance().currentUser?.uid.toString()
    val database = FirebaseDatabase.getInstance().getReference("Usuario").child(uid).child(ref)
    database.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            if (snapshot.exists()) {
                for (reg in snapshot.children) {
                    val registro = reg.getValue(Registro::class.java)
                    dataList.add(registro!!)
                }

                if (adapter == "ListadoRegistro") {
                    recyclerView.adapter = ListadoRegistrosAdapter(context, dataList)
                }
                else if (adapter == "Main") {
                    dataList.add(Registro())
                    recyclerView.adapter = RegistroAdapter(context, dataList, bundle)
                }
                // Imagen de fondo en caso de no haber registros
                if (img != null) {
                    if (dataList.size != 0 ) {
                        img.setImageResource(0)
                    }
                    else {
                        img.setImageResource(R.drawable.puzzle)
                    }
                }
            }
        }
        override fun onCancelled(error: DatabaseError) {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
        }
    })
}
// Para el formato de fecha que se necesita
fun formatoFecha(fecha: String, output: String): String {
    val formattedDate: String
    when (output) {
        "dd/MM" -> {
            val parser = SimpleDateFormat("dd/MM/yyyy")
            val formatter = SimpleDateFormat("dd/MM")
            formattedDate = formatter.format(parser.parse(fecha))
        }
        "dd" -> {
            val parser = SimpleDateFormat("dd/MM/yyyy")
            val formatter = SimpleDateFormat("dd")
            formattedDate = formatter.format(parser.parse(fecha))
        }
        "MM" -> {
            val parser = SimpleDateFormat("dd/MM/yyyy")
            val formatter = SimpleDateFormat("MM")
            formattedDate = formatter.format(parser.parse(fecha))
        }
        "yyyy" -> {
            val parser = SimpleDateFormat("dd/MM/yyyy")
            val formatter = SimpleDateFormat("yyyy")
            formattedDate = formatter.format(parser.parse(fecha))
        }
        else -> {
            formattedDate = fecha
        }
    }
    return formattedDate
}
// Devuelve la fecha de hoy
fun fechaHoy():String {
    val cal = Calendar.getInstance()
    val dd = cal.get(Calendar.DAY_OF_MONTH)
    val mm = cal.get(Calendar.MONTH) + 1
    val yy = cal.get(Calendar.YEAR)
    return "$dd/$mm/$yy"
}
// Devuelve la cantidad de dias entre dos fechas
fun edad(f1: String?, fecha2: String): Int {
    val fecha1 = f1.toString()
    val dd1 = formatoFecha(fecha1, "dd")
    val dd2 = formatoFecha(fecha2, "dd")
    val mm1 = formatoFecha(fecha1, "MM")
    val mm2 = formatoFecha(fecha2, "MM")
    val yy1 = formatoFecha(fecha1,"yyyy")
    val yy2 = formatoFecha(fecha2,"yyyy")

    var diff = yy2.toInt() - yy1.toInt()

    if (mm2.toInt() < mm1.toInt() || (mm2.toInt() == mm1.toInt() && dd2.toInt() < dd1.toInt())) {
        diff--
    }
    return diff
}
// Cerrar sesion =====
fun cerrarSesion(email: String?, provider: String?, act: Boolean, prefs: SharedPreferences.Editor) {
    if (act) {
        prefs.clear()
        prefs.apply()

        if (provider == ProviderType.FACEBOOK.name) {
            LoginManager.getInstance().logOut()
        }

        FirebaseAuth.getInstance().signOut()
    }
}