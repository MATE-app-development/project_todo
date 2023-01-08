package com.example.mate

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_date_pick_spinner.*


class DatePick_Spinner : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_pick_spinner)

        save_button_date.setOnClickListener {
            var date = datePicker.year.toString()+"/"+datePicker.month.toString()+"/"+datePicker.dayOfMonth.toString()
            finish()
        }
    }
}