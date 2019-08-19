package com.sakatomo.schedule

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        val list = mutableListOf<dataman>()
        var data:dataman
        val dbhelper = DataBaseOpenHelper(this)
        val db:SQLiteDatabase? = dbhelper.readableDatabase
        val cur = db?.query("schememo", arrayOf("_no","_name"),null, null,null,null,null,null)
        val listView = findViewById<ListView>(R.id.List)

        //Main3にintentを送り付ける
        listView.onItemClickListener = AdapterView.OnItemClickListener{_, _, position, _ ->

            val n = DB_Adapter(this,list).getItem(position).no

            val intent = Intent(this, Main3Activity::class.java)

            intent.putExtra("_no",n)

            startActivity(intent)

        }

        cur.use{
            while(cur!!.moveToNext()){
                val no = cur.getString(cur.getColumnIndex("_no"))
                val name = cur.getString(cur.getColumnIndex("_name"))
                list.add(dataman(no,name))

                listView.adapter = DB_Adapter(this,list)
            }
            db?.close()
            }

        val button = findViewById<Button>(R.id.button4)
        button.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

}
