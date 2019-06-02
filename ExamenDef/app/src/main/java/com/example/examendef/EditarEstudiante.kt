package com.example.examendef

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_editar_estudiante.*
import kotlinx.android.synthetic.main.content_editar_estudiante.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class EditarEstudiante : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_estudiante)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val indiceSeleccionado= intent.getIntExtra("indice",-1)
        if(indiceSeleccionado!=-1){
            CargarEstudiante(indiceSeleccionado)
        }
        btn_eliminar.setOnClickListener {
            eliminarEstudiante(indiceSeleccionado)
        }
        btn_actualizar.setOnClickListener {
            ActualizarEstudiante(indiceSeleccionado)
        }
    }
    fun ActualizarEstudiante(id:Int){
        MainActivity.dbEstudiante[id].nombres=inputNombre.text.toString()
        MainActivity.dbEstudiante[id].apellidos=inputNombre.text.toString()
       val fechaTemp= inputFechaNacimiento.text.toString()
        val fechaSplit= fechaTemp.split('-')

        MainActivity.dbEstudiante[id].fechaNacimiento=LocalDate.of(fechaSplit[0].toInt(), fechaSplit[1].toInt(), 3)
        irGestionEstudiantes()
    }
    fun eliminarEstudiante(id:Int){
        if(id!=-1){
            val tempEst:Estudiante= MainActivity.dbEstudiante[id];
            MainActivity.dbEstudiante.remove(tempEst)
        }
        irGestionEstudiantes()
    }
    fun irGestionEstudiantes(){
        val intent= Intent(
            this, GestionEstudiantes::class.java
        )
        startActivity(intent);
    }

    fun CargarEstudiante(id: Int){
        inputNombre.setText(MainActivity.dbEstudiante[id].nombres)
        inputApellidos.setText(MainActivity.dbEstudiante[id].apellidos)
        inputFechaNacimiento.setText(MainActivity.dbEstudiante[id].fechaNacimiento.toString())
        inputSemestre.setText(MainActivity.dbEstudiante[id].semestreActual.toString())

        if(MainActivity.dbEstudiante[id].graduado){
            inputGraduado.isChecked=true
        }else{
            inputGraduado.isChecked=false;
        }
    }

}
