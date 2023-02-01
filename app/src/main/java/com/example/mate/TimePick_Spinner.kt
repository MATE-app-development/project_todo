package com.example.mate

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_date_pick_spinner.*
import kotlinx.android.synthetic.main.activity_time_pick_spinner.*

class TimePick_Spinner : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_pick_spinner)
    }

    override fun onStart() {
        super.onStart()

        fun change_to_twodig(thetime:Int): String {
            if (thetime in 0..9) {
                var thetimes = thetime.toString()
                return StringBuilder("0").append(thetimes).toString();
            }
            else { return thetime.toString()}
        }

        save_button_time.setOnClickListener {
            val time = change_to_twodig(timePicker.hour)+":"+change_to_twodig(timePicker.minute)
            intent.putExtra("chosentime", time)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}