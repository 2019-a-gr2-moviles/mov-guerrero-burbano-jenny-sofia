package com.example.examendef

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast

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
        btn_gestion_hijos.setOnClickListener {
            irMaterias(MainActivity.dbEstudiante[indiceSeleccionado].id)
        }
        btn_crearHijos.setOnClickListener {
            irCrearMateria(MainActivity.dbEstudiante[indiceSeleccionado].id)
        }
    }
    fun irMaterias(id:Int){
        val intent= Intent(
        this, GestionMateria::class.java
        )
        intent.putExtra("id", id )
        startActivity(intent);
    }
    fun irCrearMateria(id:Int){
        val intent=Intent(
            this, CrearMateria::class.java
        )
        intent.putExtra("id", id )
        startActivity(intent)

    }

    fun ActualizarEstudiante(id:Int){
        MainActivity.dbEstudiante[id].nombres=inputNombre.text.toString()
        MainActivity.dbEstudiante[id].apellidos=inputApellidos.text.toString()
        MainActivity.dbEstudiante[id].semestreActual=inputSemestre.text.toString().toInt()
        if(inputGraduado.isChecked){
            MainActivity.dbEstudiante[id].graduado=true
        }else{
            MainActivity.dbEstudiante[id].graduado=false
          //  MainActivity.dbEstudiante[id].graduado=false
        }

        Toast.makeText(this, "Estimado: ${MainActivity.nombre}, estudiante editado exitosamente", Toast.LENGTH_SHORT).show()
        irGestionEstudiantes(1)
    }
    fun eliminarEstudiante(id:Int){
        if(id!=-1){
            val tempEst:Estudiante= MainActivity.dbEstudiante[id];
            MainActivity.dbEstudiante.remove(tempEst)
        }
        Toast.makeText(this, "Estimado: ${MainActivity.nombre}, estudiante eliminado correctamente", Toast.LENGTH_SHORT).show()




        irGestionEstudiantes(2)
    }
    fun irGestionEstudiantes(opcion:Int){
        val intent= Intent(
            this, GestionEstudiantes::class.java
        )
        intent.putExtra("opcion", opcion )
        startActivity(intent);
        finish()
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
