package com.example.a05proyectorestaurante

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.supportActionBar?.hide()
        btn_menu.setOnClickListener {
            irActividadMenu()
        }
        btn_administrador.setOnClickListener {
            withEditText()
        }
    }
    fun irActividadMenu(){
        val intent= Intent(
            this, MenuActivity::class.java
        )
        startActivity(intent);
    }
    fun withEditText() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        builder.setTitle("With EditText")
        val dialogLayout = inflater.inflate(R.layout.dialog, null)
        val editText  = dialogLayout.findViewById<EditText>(R.id.editText)
        builder.setView(dialogLayout)
        val dialogLayout2 = inflater.inflate(R.layout.dialog, null)
        val editText2  = dialogLayout.findViewById<EditText>(R.id.editText)
        builder.setView(dialogLayout2)
     //   builder.setPositiveButton("OK") { dialogInterface, i -> Toast.makeText(applicationContext, "EditText is " + editText.text.toString(), Toast.LENGTH_SHORT).show() }
        builder.show()
    }
}
