package com.example.myapplication

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter

import kotlinx.android.synthetic.main.activity_list_view.*
import kotlinx.android.synthetic.main.content_list_view.*

class ListViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        var listaNombres = arrayListOf<String>()
        listaNombres.add("Sofia")
        listaNombres.add("Andres")
        listaNombres.add("Carolina")
        listaNombres.add("Ana")
        listaNombres.add("Johana")
        listaNombres.add("Cesar")

        val adapter= ArrayAdapter(this,android.R.layout.simple_list_item_1, listaNombres)
        lv_ejemplo.adapter= adapter
        lv_ejemplo.onItemClickListener=AdapterView.OnItemClickListener { parent, view, position, id ->
            Log.i("list-view", "Posicion $position")
            Snackbar
                .make(view, "posicion $position", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }






    }


}
