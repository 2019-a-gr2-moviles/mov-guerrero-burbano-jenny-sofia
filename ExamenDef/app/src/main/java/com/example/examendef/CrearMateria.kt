package com.example.examendef

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast
import com.github.kittinunf.fuel.httpPost

import kotlinx.android.synthetic.main.activity_crear_materia.*
import kotlinx.android.synthetic.main.content_crear_materia.*
import kotlinx.android.synthetic.main.content_edit_materia_def.*
import com.github.kittinunf.result.Result
import java.util.*

class CrearMateria : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_materia)
        setSupportActionBar(toolbar)
        val idEstudianteSeleccionado= intent.getIntExtra("id",-1)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        btn_GuardarHijo.setOnClickListener {
            crearMateria(idEstudianteSeleccionado)
        }


    }



    fun crearMateria(id:Int){

        var semestre = false

        val url = "${MainActivity.objetoCompartido.url}/materia"
        var date= Date()
        val bodyJson = """
              {
                "nombre": "${inputNameMateriac.text.toString()}",
                "codigo": "${inputCodigoMateriac.text.toString()}",
                "descripcion" : "${inputDescripcionc.text.toString()}",
                "activo": ${inputEstaActivo.isChecked},
                "fechaCreacion": "${date}",
                "numeroHorasPorSemana": ${inputHorasSemanasCrear.text},
                "latitud": "${inputLatitudc.text}",
                "longitud": "${inputLongitudCrear.text}",
                "estudianteId": ${id}

              }
            """
        url
            .httpPost().body(bodyJson)
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http", "rEQUEST: ${ex}")
                    }
                    is Result.Success -> {


                        Log.i("http", "TODO BIIIEN")
//                        Toast.makeText(
//                            this,
//                            "Estimado: ${MainActivity.nombre}, estudiante editado exitosamente",
//                            Toast.LENGTH_SHORT
//                        ).show()
                        // irGestionEstudiantes(1)
                    }
                }
            }
        gestionarMaterias(id)
        Toast.makeText(this, "Estimado: ${MainActivity.nombre}, materia creada exitosamente", Toast.LENGTH_SHORT).show()
        //irGestionMaterias(2)
    }
    fun gestionarMaterias(id:Int){
        val intent= Intent(
            this,GestionMateria::class.java
        )
        intent.putExtra("id", id)
        startActivity(intent);
    }
    fun irGestionMaterias(opcion:Int){
        val intent= Intent(
            this, GestionMateria::class.java
        )
        intent.putExtra("opcion", opcion )
        startActivity(intent);
        finish()
    }


}
