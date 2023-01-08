package com.example.mate

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_time_pick_spinner.*

class TimePick_Spinner : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_pick_spinner)

        save_button_time.setOnClickListener {
            val time = timePicker.hour.toString()+":"+timePicker.minute.toString()
            finish()
        }
    }
}