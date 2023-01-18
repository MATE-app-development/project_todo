package com.example.todolist

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var timeanddate: TextView
    lateinit var check1:CheckBox
    lateinit var check2:CheckBox
    lateinit var check3:CheckBox
    lateinit var addButton: FloatingActionButton
    var todayDate=""
    @RequiresApi(Build.VERSION_CODES.N)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timeanddate = findViewById(R.id.timeanddate)
        check1 = findViewById(R.id.check1)
        check2 = findViewById(R.id.check2)
        check3 = findViewById(R.id.check3)
        addButton = findViewById(R.id.addButton)


        //상단 오늘 날짜 가져오기 -> 클릭 시 달력 picker 나오도록 추가해야 함(23.01.11기준)
        val now_time : Long = System.currentTimeMillis()
        val date_time = Date(now_time) //Date 타입으로 변경
        val todaydate =SimpleDateFormat("yyyy-MM-dd") //원하는 포맷으로 지정
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
        addButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, AddActivity::class.java))
        }

        //달력 선택 시 DatePickerDialog 띄우기
        val cal = Calendar.getInstance() //캘린더 뷰 만들기
        timeanddate.setOnClickListener(View.OnClickListener {

             val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, day ->
                todayDate = "${year}년 ${month+1}월 ${day+1}일"
            }

            DatePickerDialog(this, dateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        })










    }
}