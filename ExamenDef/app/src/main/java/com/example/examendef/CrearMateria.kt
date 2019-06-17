package com.example.examendef

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_crear_materia.*
import kotlinx.android.synthetic.main.content_crear_materia.*
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

        val materia= Materia(

            id = MainActivity.contadorMateriaId,
            codigo=inputCodigoMateria.text.toString(),
            nombre=inputNameMateria.text.toString(),
            descripcion = inputDescripcion.text.toString(),
            fechaCreacion = Date(),
            numeroHoras = inputHorasSemana.text.toString().toInt(),
            estudianteId = id,
            activo = true

        )
        if(inputEstaActivo.isChecked){
            materia.activo=true
        }else{
            materia.activo=false
        }

        MainActivity.dbMateria.add(materia)
        MainActivity.contadorMateriaId++
        gestionarMaterias(id)
        Toast.makeText(this, "Estimado: ${MainActivity.nombre}, materia creada exitosamente", Toast.LENGTH_SHORT).show()
        irGestionMaterias(2)
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
