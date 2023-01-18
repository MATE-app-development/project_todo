package com.example.mate

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.text.DateFormat
import java.time.format.DateTimeFormatter


class SqliteHelper (
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
): SQLiteOpenHelper(context, name, factory, version)
{ //상속을 활용하여 DB와 TABLE 생성

    override fun onCreate(db: SQLiteDatabase) {
        val createTodo: String = "CREATE TABLE if not exists Todo(" +
                "tID integer primary key autoincrement," +
                "tName text not null," +
                "tdate text," + //날짜를 모두 text (문자열)로 통일해두었습니다. 향후 수정 예정
                "tdline text," +
                "tipt int," +
                "trpt bool default 0," +
                "talarm text," +
                "tdone bool default 0," +
                "tdel bool default 0" +
                ");"
        db?.execSQL(createTodo)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) { }

    fun insertTodo(thetodo: todo):Int?{
        val values = ContentValues()

        values.put("tName",thetodo.tName)
        values.put("tdate", thetodo.tdate.toString())
        values.put("tdline", thetodo.tdline.toString())
        values.put("tipt", thetodo.tipt)
        values.put("trpt", thetodo.trpt)
        values.put("talarm", thetodo.talarm.toString()) //datetime을 넣는 기능이 없는듯. 일단 string으로.
        values.put("tdone", thetodo.tdone)
        values.put("tdel", thetodo.tdel)

        val wd = writableDatabase //쓰기나 수정이 가능한 데이터베이스 변수
        try {
            wd.insert("Todo",null,values)
            return 1
        } catch (e: NumberFormatException) { return null}
        finally {
            wd.close() //메모리 누수 방지를 위해 close 필수
        }
    }

    /*
    fun selectTodo():MutableList<todo>{
        val list = mutableListOf<todo>()
        val db = writableDatabase

        // Select문을 실행하여 결과로 Cursor 객체 획득하기
        // Cursor 객체는 SQL 질의문을 통해 반환되는 결과이다(title, content 컬럽 값의 데이터가 들어가있다)
        val cursor: Cursor = db.rawQuery("select tID, tName, tdate from Todo order by _id desc limit 1", null)

        while(cursor.moveToNext()) {
            val tID = cursor.getInt(cursor.getColumnIndex("tID"))
            val tName = cursor.getString(cursor.getColumnIndex("tName"))
            val tdate = cursor.getString(cursor.getColumnIndex("tdate"))
            list.add(todo(tID,tName,tdate, null, null, null, null, null, null))
        }
        cursor.close()
        db.close()

        return list
    }*/

    fun updateTodo(thetodo: todo){
        //이 코드는 하나만 바뀌어도 내용 전체를 업데이트함. 비효율적 but 코드가 훨 간단
        val values = ContentValues()

        values.put("tName",thetodo.tName)
        values.put("tdate", thetodo.tdate.toString())
        values.put("tdline", thetodo.tdline.toString())
        values.put("tipt", thetodo.tipt)
        values.put("trpt", thetodo.trpt)
        values.put("talarm", thetodo.talarm.toString())
        values.put("tdone", thetodo.tdone)
        values.put("tdel", thetodo.tdel)

        val wd = writableDatabase
        wd.update("Todo",values,"id=${thetodo.tID}",null)
        wd.close()
    }

    fun deleteTodo(thetodo:todo){
        val delete = "delete from Todo where tID = ${thetodo.tID}"
        val db = writableDatabase
        db.execSQL(delete)
        db.close()
    }
}