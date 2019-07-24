package com.example.examendef

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result

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

            eliminarEstudiante(MainActivity.dbEstudiante[indiceSeleccionado].id)


        }
        btn_actualizar.setOnClickListener {
            ActualizarEstudiante(MainActivity.dbEstudiante[indiceSeleccionado].id)
        }
        btn_gestion_hijos.setOnClickListener {
            irMaterias(MainActivity.dbEstudiante[indiceSeleccionado].id)
        }
        btn_crearHijos.setOnClickListener {
            irCrearMateria(MainActivity.dbEstudiante[indiceSeleccionado].id)
        }
        btn_irMapaMaterias.setOnClickListener {
            mapas(MainActivity.dbEstudiante[indiceSeleccionado].id)
        }
    }
    fun irMaterias(id:Int){
        val intent= Intent(
        this, GestionMateria::class.java
        )
        intent.putExtra("id", id )
        startActivity(intent);
    }
    fun mapas(idEstudiante:Int){


        val intent= Intent(
            this, MapsActivity::class.java
        )
        intent.putExtra("idEstudiante", idEstudiante )
        startActivity(intent);
    }
    fun irCrearMateria(id:Int){
        val intent=Intent(
            this, CrearMateria::class.java
        )
        intent.putExtra("id", id )
        startActivity(intent)
        finish()

    }

    fun ActualizarEstudiante(id:Int){

        var semestre=false
        if(inputGraduado.isChecked){

            semestre=true
        }else{

            semestre=false
          //  MainActivity.dbEstudiante[id].graduado=false
        }
        val url = "${MainActivity.objetoCompartido.url}/estudiante/${id}"
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
            .httpPut().body(bodyJson)
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http", "rEQUEST: ${request}")
                    }
                    is Result.Success -> {
                        runOnUiThread {
                            Toast.makeText(this, "Estimado: ${MainActivity.nombre},Estudiante editado exitosamente", Toast.LENGTH_SHORT).show()
                            irGestionEstudiantes(2)
                        }


                        Log.i("http", "TODO BIIIEN")

                      // irGestionEstudiantes(1)
                    }
                }
            }


    }
    fun eliminarEstudiante(id:Int){
        if(id!=-1){
            val url = "${MainActivity.url}/estudiante/${id}"
            var lista = listOf<Estudiante>()
            var listaLibros = ArrayList<Estudiante>()
            url
                .httpDelete()
                .responseString { request, response, result ->
                    when (result) {
                        is Result.Failure -> {
                            val ex = result.getException()
                            Log.i("http", "Error: ${ex.message}")
                        }
                        is Result.Success -> {
                            runOnUiThread {
                                Toast.makeText(this, "Estimado: ${MainActivity.nombre},Estudiante eliminado exitosamente", Toast.LENGTH_SHORT).show()
                                irGestionEstudiantes(2)
                            }


                        }
                    }
                }
        }
        Toast.makeText(this, "Estimado: ${MainActivity.nombre}, estudiante eliminado correctamente", Toast.LENGTH_SHORT).show()





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
