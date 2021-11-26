package com.tomaspev.parentalhelper

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_ingreso_registro.*
import java.util.*

class IngresoRegistro : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    private lateinit var database: DatabaseReference
    var dd = 0
    var mm = 0
    var yy = 0
    // s -> save
    var sdd = 0
    var smm = 0
    var syy = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingreso_registro)

        val uid = FirebaseAuth.getInstance().currentUser?.uid.toString()
        database = FirebaseDatabase.getInstance().getReference("Usuario")

        btn_ingresar_registro.setOnClickListener {
            val map: MutableMap<String, Any> = HashMap()
            val nombre = iet_nombre.text.toString()
            val cumple = tv_registro_cumple.text
            val capacidad:Boolean = switch_capacidad.isChecked

            map.put("nombre", nombre)
            map.put("cumple", cumple)
            map.put("capacidad", capacidad)

            database.child(uid).child("registros").child(nombre).setValue(map)

            // Volver al activity anterior
            val intent = Intent(this, ListadoRegistros::class.java)
            startActivity(intent)
        }

        btn_registro_cumple.setOnClickListener {
            pickDate()
        }
    }
    // Date Picker ================================================================================
    private fun getDateTimeCalendar() {
        val cal = Calendar.getInstance()
        dd = cal.get(Calendar.DAY_OF_MONTH)
        mm = cal.get(Calendar.MONTH)
        yy = cal.get(Calendar.YEAR)
    }
    private fun pickDate() {
        getDateTimeCalendar()
        DatePickerDialog(this, this, yy, mm, dd).show()
    }
    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        sdd = dayOfMonth
        smm = month + 1
        syy = year
        getDateTimeCalendar()
        tv_registro_cumple.text = "$sdd/$smm/$syy"
    }
}