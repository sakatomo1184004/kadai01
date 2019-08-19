package com.sakatomo.schedule

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main3.*

class Main3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        //Main2で貰ったintentを取り出す
        val nosecond = findViewById<TextView>(R.id.No2)
        val key =intent.getStringExtra("_no")
        nosecond.text=key

        //_name（右のやつ）を探して取り出す
        val dtb = DataBaseOpenHelper(this).readableDatabase
        val cusor = dtb.query("schememo",arrayOf("_no","_name"),"_no = ?", arrayOf(key),null,null,null)

        var value = String()

        cusor.use {c->

            val namesecond=findViewById<TextView>(R.id.Name2)
            c.moveToNext()
            namesecond.text=c.getString(c.getColumnIndex("_name"))

        }

        val delete_button = findViewById<Button>(R.id.button3)
        delete_button.setOnClickListener {
            val database = DataBaseOpenHelper(this).writableDatabase

            database.delete("schememo","_no = ?", arrayOf(key))

            val intent = Intent(this, Main2Activity::class.java)

            startActivity(intent)
        }
    }
}
