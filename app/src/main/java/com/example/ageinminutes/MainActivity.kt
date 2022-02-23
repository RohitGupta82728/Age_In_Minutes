package com.example.ageinminutes

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener {view ->
            ClickDatePicker(view)
        }
    }

    fun ClickDatePicker(view: View) {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd=DatePickerDialog(this,DatePickerDialog.OnDateSetListener {
                view, selYear, selMonth, selDayOfMonth ->
            Toast.makeText(this,"The chosen year $selYear , the chosen month $selMonth" +
                    " , the chosen day $selDayOfMonth",Toast.LENGTH_LONG).show()
            val selectedDate = "$selDayOfMonth/${selMonth+1}/$selYear"
            tvSelectedDate.setText(selectedDate)
            val simpleDateFormat =SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
            val theDate = simpleDateFormat.parse(selectedDate)
            val selectedDateInMin = theDate!!.time/60000

            val currentDate = simpleDateFormat.parse(simpleDateFormat.format(System.currentTimeMillis()))
            val currentDateInMin = currentDate!!.time/60000

            val diffInMin = currentDateInMin-selectedDateInMin
            tvDateInMinutes.setText(diffInMin.toString())
        },
            year,
            month,
            day)
        dpd.datePicker.maxDate = Date().time-86400000
        dpd.show()
        }

}
