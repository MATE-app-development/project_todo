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

        fun month_to_twodig(themonth:String): String {
            if ("1" <= themonth && themonth < "10") {
                return StringBuilder("0").append(themonth).toString();
            }
            else { return themonth}
        }

        save_button_date.setOnClickListener {
            var date = datePicker.year.toString()+"-"+month_to_twodig((datePicker.month+1).toString())+"-"+datePicker.dayOfMonth.toString()
            intent.putExtra("chosendate", date)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}