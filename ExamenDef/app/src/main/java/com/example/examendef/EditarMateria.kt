package com.example.examendef

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_editar_materia.*
import kotlinx.android.synthetic.main.content_crear_materia.*
import java.util.*

class EditarMateria : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_materia)

        val idMateria= intent.getIntExtra("id",-1)
        cargarMateria(idMateria)
    }
    fun cargarMateria(id:Int){
        var materiaaux= Materia(
            id = 0,
            nombre = "",
            codigo = "",
            descripcion = "",
            activo = true,
            fechaCreacion = Date(),
            numeroHoras = 0,
            estudianteId = 0
        )

        MainActivity.dbMateria.forEach{
            if(it.id==id){
                materiaaux=it
            }
        }

        inputNameMateria.setText("holaaaaaaaaa")
        inputCodigoMateria.setText(materiaaux.codigo)
       //.setText(materiaaux.fechaCreacion.toString())
        inputDescripcion.setText(materiaaux.descripcion)
        inputEstaActivo.isChecked=materiaaux.activo
        inputHorasSemana.setText(materiaaux.numeroHoras.toString())


    }
    fun actualizar(){

    }

}
