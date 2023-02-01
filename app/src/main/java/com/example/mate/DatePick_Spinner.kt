package com.example.mate

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_date_pick_spinner.*
import kotlinx.android.synthetic.main.activity_date_pick_spinner.datePicker
import kotlinx.android.synthetic.main.activity_date_pick_spinner_before_time.*
import kotlinx.android.synthetic.main.activity_main.*


class DatePick_Spinner : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_pick_spinner)
    }

    override fun onStart() {
        super.onStart()

        fun date_to_twodig(thedate:Int): String {
            if (1 <= thedate && thedate < 10) {
                var date_a_c = thedate.toString()
                return StringBuilder("0").append(date_a_c).toString();
            }
            else { return thedate.toString()}
        }

        save_button_date.setOnClickListener {
            var date = datePicker.year.toString()+"-"+date_to_twodig(datePicker.month+1)+"-"+date_to_twodig(datePicker.dayOfMonth)
            intent.putExtra("chosendate", date)
            setResult(RESULT_OK, intent)
            this.finish()
        }
    }
}