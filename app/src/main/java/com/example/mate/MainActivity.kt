package com.example.mate


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var timeanddate: TextView
    lateinit var check1: CheckBox
    lateinit var check2: CheckBox
    lateinit var check3: CheckBox
   // lateinit var addButton: FloatingActionButton
    var todayDate=""

    @RequiresApi(Build.VERSION_CODES.N)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbHelper = SqliteHelper(this, "todo.db", null, 1)

        timeanddate = findViewById(R.id.timeanddate)
        check1 = findViewById(R.id.check1)
        check2 = findViewById(R.id.check2)
        check3 = findViewById(R.id.check3)
        //addButton = findViewById(R.id.addButton)


        //상단 오늘 날짜 가져오기
        val now_time : Long = System.currentTimeMillis()
        val date_time = Date(now_time) //Date 타입으로 변경
        val todaydate = SimpleDateFormat("yyyy-MM-dd") //원하는 포맷으로 지정
        val str_todaydate = todaydate.format(date_time)
        timeanddate.text = str_todaydate.toString()


        //checkbox 체크 여부 확인

        check1.setOnCheckedChangeListener { buttonView, isChecked ->

            if(isChecked){
                //정렬 알고리즘 사용, 맨 아래로 정렬
                Toast.makeText(this,"complete task1", Toast.LENGTH_SHORT).show()
            }
            else{
                //정렬 알고리즘 사용
                Toast.makeText(this,"Do not complete task1", Toast.LENGTH_SHORT).show()
            }
        }

        check2.setOnCheckedChangeListener { buttonView, isChecked ->

            if(isChecked){
                //정렬 알고리즘 사용, 맨 아래로 정렬
                Toast.makeText(this,"complete task1", Toast.LENGTH_SHORT).show()
            }
            else{
                //정렬 알고리즘 사용
                Toast.makeText(this,"Do not complete task1", Toast.LENGTH_SHORT).show()
            }
        }

        check3.setOnCheckedChangeListener { buttonView, isChecked ->

            if(isChecked){
                //정렬 알고리즘 사용, 맨 아래로 정렬
                Toast.makeText(this,"complete task1", Toast.LENGTH_SHORT).show()
            }
            else{
                //정렬 알고리즘 사용
                Toast.makeText(this,"Do not complete task1", Toast.LENGTH_SHORT).show()
            }
        }

        //추가 버튼 클릭시 화면 전환
//        addButton.setOnClickListener {
//            startActivity(Intent(this@MainActivity, Popup_Todo_Add::class.java))
//        }

        
    }




}
