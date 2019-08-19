package com.sakatomo.schedule

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseOpenHelper(context: Context?)  : SQLiteOpenHelper(context, "Omendb", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        //データベースがないときに実行される
        db?.execSQL("create table schememo ( " +
                "_no primary key , " +
                "_name text not null)")
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS" + "schememo" + ";")
        onCreate(db)
    }
}
    /*fun queryTexts(context: Context?) : List<String>{
        val database = DataBaseOpenHelper(context).readableDatabase

        val cursor = database.query("texts", null, null, null, null, null,"no DESC")

        val texts = mutableListOf<String>()
        cursor.use{
            while(cursor.moveToNext()){
                val text = cursor.getString(cursor.getColumnIndex("name"))
                texts.add(text)
            }
        }
        database.close()
        return texts
    }

    fun insertText(context: Context?, text: String){
        val database = DataBaseOpenHelper(context).writableDatabase
        database.use { db ->

            val record = ContentValues().apply{
                put("name" , text)
            }
            db.insert("texts", null, record)
        }
    }*/