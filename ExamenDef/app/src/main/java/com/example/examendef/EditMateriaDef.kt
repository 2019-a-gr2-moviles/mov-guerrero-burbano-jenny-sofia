package com.example.examendef

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_edit_materia_def.*
import kotlinx.android.synthetic.main.content_edit_materia_def.*
import java.util.*

class EditMateriaDef : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_materia_def)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val idMateria= intent.getIntExtra("id",-1)
        val materiaTemp=cargarMateria(idMateria)
        btn_Eliminar.setOnClickListener {
            eliminar(idMateria, materiaTemp)
        }
        btn_Actualizar.setOnClickListener {
            actualizar(idMateria, materiaTemp)
        }
    }
    fun cargarMateria(id:Int):Materia{
        var materiaaux= Materia(
            id = id,
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

        inputNombreMateria.setText(materiaaux.nombre)
        inputCodigoMateria.setText(materiaaux.codigo)
        inputFechaCreacion.setText(materiaaux.fechaCreacion.toString())
        inputDescripcionMateria.setText(materiaaux.descripcion)
        inputActivo.isChecked=materiaaux.activo
        inputHorasSemana.setText(materiaaux.numeroHoras.toString())
        return materiaaux;


    }
    fun actualizar(id: Int, materia: Materia){

        var materiaAxu=MainActivity.dbMateria.indexOf(materia)
        if(materiaAxu!=-1){
            MainActivity.dbMateria[materiaAxu].nombre=inputNombreMateria.text.toString();
            MainActivity.dbMateria[materiaAxu].numeroHoras=inputHorasSemana.text.toString().toInt();
            MainActivity.dbMateria[materiaAxu].descripcion=inputDescripcionMateria.text.toString()
            MainActivity.dbMateria[materiaAxu].codigo= inputCodigoMateria.text.toString()
            MainActivity.dbMateria[materiaAxu].activo=inputActivo.isChecked

        }
        Toast.makeText(this, "Estimado: ${MainActivity.nombre}, materia editada exitosamente", Toast.LENGTH_SHORT).show()
        irGestionMaterias(2);
    }
    fun eliminar(id:Int, materia: Materia){
        var materiaAxu=MainActivity.dbMateria.indexOf(materia)
        if(materiaAxu!=-1) {
            var tempMateria= MainActivity.dbMateria[materiaAxu]
            MainActivity.dbMateria.remove(tempMateria)
        }
        Toast.makeText(this, "Estimado: ${MainActivity.nombre}, materia eliminada exitosamente", Toast.LENGTH_SHORT).show()
        irGestionMaterias(id)
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
