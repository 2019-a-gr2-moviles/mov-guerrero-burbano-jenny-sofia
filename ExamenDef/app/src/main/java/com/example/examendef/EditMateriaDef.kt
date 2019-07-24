package com.example.examendef

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result

import kotlinx.android.synthetic.main.activity_edit_materia_def.*
import kotlinx.android.synthetic.main.content_crear_materia.*
import kotlinx.android.synthetic.main.content_edit_materia_def.*
import java.util.*

class EditMateriaDef : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_materia_def)
        setSupportActionBar(toolbar)


        val idMateria= intent.getIntExtra("id",-1)
        val idEstudiante= intent.getIntExtra("idEstudiante",-1)
        val opcion= intent.getIntExtra("opcion",-1)
        if(opcion==1){
            btn_Actualizar.visibility= View.INVISIBLE
            btn_Eliminar.visibility= View.INVISIBLE
        }
       cargarMateriaInfo(idMateria)
        btn_Eliminar.setOnClickListener {
            eliminar(idMateria, idEstudiante)
        }
        btn_Actualizar.setOnClickListener {
            actualizar(idMateria, idEstudiante)
        }
    }
    fun cargarMateriaInfo(id:Int){
        val url = "${MainActivity.url}/materia?id=${id}"
        var lista = listOf<Estudiante>()
        var listaLibros = ArrayList<Estudiante>()
        url
            .httpGet()
            .responseString { request, response, result ->
                when(result){
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http", "Errorssss: ${ex.message} --- ${request}")
                    }
                    is Result.Success -> {
                        val data = result.get()
                        Log.i("http", "Data: ${data}")

                        var materiaaux = Klaxon().parseArray<Materia>(data)
                        Log.i("http", "Libro PArseada: ${materiaaux}")
                        runOnUiThread {

                            inputNombreMateria.setText(materiaaux?.get(0)!!.nombre!!)
                            inputCodigoMateriaEdit.setText(materiaaux?.get(0)!!.codigo)
                            inputFechaCreacion.setText(materiaaux?.get(0)!!.fechaCreacion.toString())
                            inputDescripcionMateria.setText(materiaaux?.get(0)!!.descripcion)
                            inputActivo.isChecked = materiaaux?.get(0)!!.activo
                            inputHorasPorSemanaEditMateria.setText(materiaaux?.get(0)!!.numeroHorasPorSemana.toString())
                            inputLongitudEditar.setText(materiaaux?.get(0)!!.longitud.toString())
                            inputLatitud.setText(materiaaux?.get(0)!!.latitud.toString())

                        }

                    }
                }
            }
//        var materiaaux= Materia(
//            id = id,
//            nombre = "",
//            codigo = "",
//            descripcion = "",
//            activo = true,
//            fechaCreacion = Date(),
//            numeroHoras = 0,
//            estudianteId = 0
//        )
//
//        MainActivity.dbMateria.forEach{
//            if(it.id==id){
//                materiaaux=it
//            }
//        }
//
//        inputNombreMateria.setText(materiaaux.nombre)
//        inputCodigoMateria.setText(materiaaux.codigo)
//        inputFechaCreacion.setText(materiaaux.fechaCreacion.toString())
//        inputDescripcionMateria.setText(materiaaux.descripcion)
//        inputActivo.isChecked=materiaaux.activo
//        inputHorasSemana.setText(materiaaux.numeroHoras.toString())
//        return materiaaux;


    }
    fun actualizar(id: Int, idEstudiante:Int){

        var semestre = false

        val url = "${MainActivity.objetoCompartido.url}/materia/${id}"
        var date= Date()
        val bodyJson = """
              {
                "nombre": "${inputNombreMateria.text.toString()}",
                "codigo": "${inputCodigoMateriaEdit.text.toString()}",
                "descripcion" : "${inputDescripcionMateria.text.toString()}",
                "activo": ${inputActivo.isChecked},
                "fechaCreacion": "${date}",
                "numeroHorasPorSemana": ${inputHorasPorSemanaEditMateria.text},
                "latitud": "${inputLatitud.text}",
                "longitud": "${inputLongitudEditar.text}",
                "estudianteId": ${idEstudiante}

              }
            """
        url
            .httpPut().body(bodyJson)
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http", "ERROR: ${ex}")
                    }
                    is Result.Success -> {


                        Log.i("httpm", "TODO BIIIEN  ${request}")
//                        Toast.makeText(
//                            this,
//                            "Estimado: ${MainActivity.nombre}, estudiante editado exitosamente",
//                            Toast.LENGTH_SHORT
//                        ).show()
                        // irGestionEstudiantes(1)
                    }
                }
            }



    }
    fun eliminar(id:Int, idEstudiante: Int){

        if(id!=-1) {
            if(id!=-1){
                val url = "${MainActivity.url}/materia/${id}"
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
                                    irGestionMaterias(2, idEstudiante)
                                }


                            }
                        }
                    }
            }

        }

    }
    fun irGestionMaterias(opcion:Int, idEstudiante: Int){
        val intent= Intent(
            this, GestionMateria::class.java
        )
        intent.putExtra("opcion", opcion )
        intent.putExtra("id",  idEstudiante )
        startActivity(intent);
        finish()
    }


}
