package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.util.Log

import kotlinx.android.synthetic.main.activity_parselable.*
import kotlinx.android.synthetic.main.content_parselable.*

class parselable : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val sofia: Usuario= this.intent.getParcelableExtra<Usuario>("usuario")
        Log.i("parselable", "Nombre${sofia?.nombre}")
        val x=sofia.nombre;
        Log.i("prueba", x)
        Log.i("parselable", "Nombre${sofia?.edad}")
        Log.i("parselable", "Nombrellll${sofia?.fechaNacimiento}")
        Log.i("parselable", "Nombre${sofia?.sueldo}")
        val micky: Mascta?= this.intent.getParcelableExtra<Mascta>("mascota")
        Log.i("parselable", "Nombre${micky?.nombre}")
        Log.i("parselable", "Nombre${micky?.duenio?.nombre}")

        setContentView(R.layout.activity_parselable)
        setSupportActionBar(toolbar)
        btn_menu.setOnClickListener {
            this.regresarMenu();
        }
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

    }
    fun regresarMenu(){
        val intenExplicito= Intent(
            this, MainActivity::class.java
        )
        startActivity(intenExplicito)
    }

}
