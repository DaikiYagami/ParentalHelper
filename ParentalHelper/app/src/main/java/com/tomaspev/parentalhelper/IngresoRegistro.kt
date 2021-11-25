package com.tomaspev.parentalhelper

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_ingreso_registro.*
import java.util.*

class IngresoRegistro : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
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