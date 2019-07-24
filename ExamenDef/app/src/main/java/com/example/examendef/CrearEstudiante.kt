package com.example.examendef

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast
import com.example.examendef.R.layout.*
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result

import kotlinx.android.synthetic.main.activity_crear_estudiante.*
import kotlinx.android.synthetic.main.content_crear_estudiante.*
import kotlinx.android.synthetic.main.content_crear_estudiante.btn_actualizar
import kotlinx.android.synthetic.main.content_crear_estudiante.inputApellidos
import kotlinx.android.synthetic.main.content_crear_estudiante.inputFechaNacimiento
import kotlinx.android.synthetic.main.content_crear_estudiante.inputGraduado
import kotlinx.android.synthetic.main.content_crear_estudiante.inputNombre
import kotlinx.android.synthetic.main.content_crear_estudiante.inputSemestre
import kotlinx.android.synthetic.main.content_editar_estudiante.*
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

    fun CargarEstudiante(id: Int) {
        inputNombre.setText(MainActivity.dbEstudiante[id].nombres)
        inputApellidos.setText(MainActivity.dbEstudiante[id].apellidos)
        inputFechaNacimiento.setText(MainActivity.dbEstudiante[id].fechaNacimiento.toString())
        inputSemestre.setText(MainActivity.dbEstudiante[id].semestreActual.toString())

        if (MainActivity.dbEstudiante[id].graduado) {
            inputGraduado.isChecked = true
        } else {
            inputGraduado.isChecked = false;
        }
    }

    fun crearEstudiante() {

        var semestre = false
        if (inputGraduado.isChecked) {

            semestre = true
        } else {

            semestre = false
            //  MainActivity.dbEstudiante[id].graduado=false
        }
        val url = "${MainActivity.objetoCompartido.url}/estudiante"
        val bodyJson = """
              {
                "nombres": "${inputNombre.text.toString()}",
                "apellidos": "${inputApellidos.text.toString()}",
                "fechaNacimiento" : "${inputFechaNacimiento.text.toString()}",
                "semestreActual": ${inputSemestre.text.toString()},
                "graduado": ${semestre}

              }
            """
        url
            .httpPost().body(bodyJson)
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http", "rEQUEST: ${request}")
                    }
                    is Result.Success -> {


                        Log.i("http", "TODO BIIIEN")
                        Toast.makeText(
                            this,
                            "Estimado: ${MainActivity.nombre}, estudiante editado exitosamente",
                            Toast.LENGTH_SHORT
                        ).show()
                        // irGestionEstudiantes(1)
                    }
                }
            }


        Toast.makeText(this, "Estimado: ${MainActivity.nombre}, estudiante editado exitosamente", Toast.LENGTH_SHORT)
            .show()
        gestionarEstudiantes()


    }

    fun gestionarEstudiantes() {
        val intent = Intent(
            this, MenuEstudiante::class.java
        )
        startActivity(intent);
    }

}
