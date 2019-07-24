package com.example.examendef

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_editar_materia.*
import kotlinx.android.synthetic.main.content_crear_materia.*
import java.util.*

class EditarMateria : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_materia)

        val idMateria= intent.getIntExtra("id",-1)
        val idEstudiante= intent.getIntExtra("idEstudiante",-1)
        cargarMateria(idMateria)
    }
    fun cargarMateria(id:Int){



    }

    fun actualizar(){

    }

}
