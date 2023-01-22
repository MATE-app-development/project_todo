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
                "trpt bool," +
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

    fun selectalltodo(DB_todo: SQLiteDatabase, condition:String?):MutableList<todo>{
        //condition만 바꾸어 인수로 전해주면 그 조건에 일치하는 결과값을 List로 반환한다.

        var columns: Array<String> = arrayOf<String>("tID", "tName", "tdate","tdline","trpt","talarm")
        var cursor: Cursor = DB_todo.query("Todo", columns, condition, null, null, null, "tID DESC")
        var todolistAll = mutableListOf<todo>()

        while (cursor.moveToNext()) {
            var tID = cursor.getInt(0)
            var tName = cursor.getString(1)
            var tdate = cursor.getString(2)
            var tdline = cursor.getString(3)
            var trpt = cursor.getString(4).toBoolean()
            var talarm = cursor.getString(5)

            todolistAll.add(todo(tID, tName, tdate, tdline, null, trpt, talarm, null, null))
        }

        cursor.close()
        DB_todo.close()

        return todolistAll
    }

    fun selectoneTodo(DB_todo: SQLiteDatabase, condition:String?):todo{
        var todolistAll = mutableListOf<todo>()

        var columns: Array<String> = arrayOf<String>("tID", "tName", "tdate","tdline","trpt","talarm")
        var cursor: Cursor = DB_todo.query("Todo", columns, condition, null, null, null, null)

        while (cursor.moveToNext()) {
            var tID = cursor.getInt(0)
            var tName = cursor.getString(1)
            var tdate = cursor.getString(2)
            var tdline = cursor.getString(3)
            var trpt = cursor.getString(4).toBoolean()
            var talarm = cursor.getString(5)

            todolistAll.add(todo(tID, tName, tdate, tdline, null, trpt, talarm, null, null))
        }
        return todolistAll[0]
    }

    fun updateTodo(DB_todo: SQLiteDatabase, thetodo: todo):Int?{ //지금 이거 오류가 남. 전체 앱이 종료됨.
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

        try {
            DB_todo.update("Todo",values,"tID=${thetodo.tID}",null)
            return 1
        } catch (e: NumberFormatException) { return null}
        finally {
            DB_todo.close() //메모리 누수 방지를 위해 close 필수
        }
    }

    fun deleteTodo(DB_todo: SQLiteDatabase,tID:Int){
        val delete = "delete from Todo where tID = ${tID}"
        DB_todo.execSQL(delete)
        DB_todo.close()
    }
}