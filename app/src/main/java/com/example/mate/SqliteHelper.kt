package com.example.mate

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqliteHelper (
    context: Context?, //자료형을 할당할 때 뒤에 ?를 붙임.
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
): SQLiteOpenHelper(context, name, factory, version)//이거 상속받는다는 뜻
{ //상속을 활용하여 DB와 TABLE 생성

    override fun onCreate(db: SQLiteDatabase) {
        val createTodo: String = "CREATE TABLE if not exists Todo(" +
                "tID integer primary key autoincrement," +
                "tName text not null," +
                "tdate date not null," +
                "tdline datetime," +
                "tipt int," +
                "trpt bool default 0," +
                "talarm datetime," +
                "tdone bool default 0," +
                "tdel bool default 0" +
                ");"
        db?.execSQL(createTodo)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        /*val sql1: String = "DROP TABLE if exists todo"
        db.execSQL(sql1)
        val sql2: String = "DROP TABLE if exists calender"
        db.execSQL(sql2)
        onCreate(db)*/
    }
}