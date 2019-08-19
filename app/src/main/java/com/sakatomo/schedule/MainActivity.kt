package com.sakatomo.schedule

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbhelper = DataBaseOpenHelper(this)
        val db = dbhelper.writableDatabase

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val editNo = findViewById<EditText>(R.id.edtNo)
            val editName = findViewById<EditText>(R.id.edtName)

            if(isPrimaryKeyCheck(db,editNo.text.toString())) {
                Toast.makeText(this, "keyはすでに存在します", Toast.LENGTH_SHORT).show()
            }else{
            val values = ContentValues()
            values.put("_no", editNo.text.toString())
            values.put("_name", editName.text.toString())
            db.insertOrThrow("schememo", null, values)
            editNo.setText("")
            editName.setText("")
        }

        val intent = Intent(this, Main2Activity::class.java)
        startActivity(intent)


    }

        val button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener {
            val intent = Intent(this, Main2Activity::class.java)
            startActivity(intent)
        }
}

    fun isPrimaryKeyCheck(db : SQLiteDatabase, key : String): Boolean{
        //存在する場合はtrueを返す
        val sql = "select count(*) as cnt from schememo where _no = ?"
        val cur = db.rawQuery( sql , arrayOf(key))
        cur.moveToFirst()
        val cnt =cur.getInt( cur.getColumnIndex("cnt"))
        cur.close()
        return cnt > 0
    }
}



