package com.example.examendef

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import com.example.examendef.R.layout.*

import kotlinx.android.synthetic.main.activity_crear_estudiante.*
import kotlinx.android.synthetic.main.content_crear_estudiante.*
import java.util.*

class CrearEstudiante : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_crear_estudiante)
        setSupportActionBar(toolbar)



        btn_actualizar.setOnClickListener {

            crearEstudiante()
        }
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
    fun crearEstudiante(){
        val estudiante=Estudiante(
            graduado = true,
            fechaNacimiento = Date(),
            nombres=inputNombre.text.toString(),
            apellidos = inputApellidos.text.toString(),
            id = MainActivity.contadorEstudiateId,
            semestreActual = inputSemestre.text.toString().toInt()


            )
        MainActivity.dbEstudiante.add(estudiante)
        MainActivity.contadorEstudiateId++
        gestionarEstudiantes()



    }
    fun gestionarEstudiantes(){
        val intent= Intent(
          this,MenuEstudiante::class.java
        )
       startActivity(intent);
  }

}
