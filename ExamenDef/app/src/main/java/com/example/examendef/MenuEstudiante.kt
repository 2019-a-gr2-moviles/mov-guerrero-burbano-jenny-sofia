package com.example.examendef

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_menu_estudiante.*
import kotlinx.android.synthetic.main.content_menu_estudiante.*

class MenuEstudiante : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_estudiante)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }


        btn_crearEstudiante.setOnClickListener {
            crearEstudiante()
        }
        btn_gestionEstudiante.setOnClickListener {
            gestionarEstudiantes()
        }

    }
    fun crearEstudiante(){
        val intent= Intent(
            this, CrearEstudiante::class.java
        )
        startActivity(intent);
    }

    fun gestionarEstudiantes(){
        val intent= Intent(
            this, GestionEstudiantes::class.java
        )
        startActivity(intent);
    }

}
