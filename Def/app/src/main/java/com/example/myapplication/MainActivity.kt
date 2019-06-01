package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        btn_actividad_dos.setOnClickListener {
            this.irActividadDos()

        }
        btn_parselable.setOnClickListener {
            this.irAParcelable()
        }
        btn_adapter.setOnClickListener {
            this.irAdapter()
        }
    }
    fun irActividadDos(){
        val intent= Intent(
            this, Actividad_Dos::class.java
        )
        intent.putExtra("nombre", "Sofia")
        intent.putExtra("edad", 21)

        startActivity(intent)
       // finish()

    }
    fun irAParcelable(){
        val intent= Intent(
            this, parselable:: class.java
        )
        val sofia= Usuario("Sofia", 21, Date(), 12.12)

        intent.putExtra("usuario", sofia)
        val micky= Mascta("Micky", sofia)
        intent.putExtra("mascota", micky)
        startActivity(intent)
    }
    fun irAdapter(){
        val intent= Intent(
            this, ListViewActivity:: class.java

        )
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
