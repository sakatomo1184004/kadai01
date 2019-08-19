package com.sakatomo.schedule

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

data class dataman(val no: String, val name: String)

class DB_Adapter(private val context: Context, private val texts : List<dataman>) : BaseAdapter() {

    private val db : SQLiteDatabase
    private val usDB : DataBaseOpenHelper
    init {
        usDB = DataBaseOpenHelper(context)
        db = usDB.writableDatabase
    }

    private val inflater = LayoutInflater.from(context)

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = p1 ?: createView(p2)

        val user = getItem(p0)

        val viewHolder = view.tag as ViewHolder

        viewHolder.no.text = user.no
        viewHolder.name.text = user.name

        return view
    }

    override fun getItem(p0: Int) = texts[p0]
    override fun getItemId(p0: Int) = p0.toLong()
    override fun getCount() = texts.size

    private class ViewHolder(view: View){
        val no = view.findViewById(R.id.No) as TextView
        val name = view.findViewById(R.id.Name) as TextView
    }

    private fun createView(parent : ViewGroup?):View{
        val view = inflater.inflate(R.layout.list_text_row,parent,false)
        view.tag = ViewHolder(view)
        return view
    }



}