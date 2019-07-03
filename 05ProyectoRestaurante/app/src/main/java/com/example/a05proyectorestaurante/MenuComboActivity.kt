package com.example.a05proyectorestaurante

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.activity_menu_combo.*

class MenuComboActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_combo)
        cargar(0)

    }
    fun irGestionCombo(indice:Int){
        val intent= Intent(
            this, GestionComboActivity::class.java
        )
        intent.putExtra("indice", indice )
        startActivity(intent);
    }
    fun cargar(opcion: Int){
        val url = "${MenuActivity.objetoCompartido.url}/combo"
        var lista= listOf<Combo>()

        url
            .httpGet()
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http", "Error: ${ex.message}")
                    }
                    is Result.Success -> {
                        val data = result.get()
                        Log.i("http", "Data: ${data}")
                        var platoParseada = Klaxon().parseArray<Combo>(data)
                        lista=platoParseada!!
                        runOnUiThread {
                            iniciarRecycleView(lista, this, rv_combo, opcion)
                        }

                    }
                }
            }
    }
    fun iniciarRecycleView(lista: List<Combo>, actividad:MenuComboActivity, recycler_view: RecyclerView, opcion: Int){
        val adaptadorPlato= AdaptadorCombo(lista, actividad, recycler_view, opcion)
        rv_combo.adapter=adaptadorPlato
        rv_combo.itemAnimator = DefaultItemAnimator()
        //Nos falta el layout manager
        rv_combo.layoutManager= LinearLayoutManager(this)
        adaptadorPlato.notifyDataSetChanged()
    }
//    fun withEditText(plato: Plato, opcion: Int) {
//
//        val builder = AlertDialog.Builder(this)
//        val inflater = layoutInflater
//        builder.setTitle("With EditText")
//        val dialogLayout = inflater.inflate(R.layout.dialog, null)
//
//        val nombre = dialogLayout.findViewById<EditText>(R.id.input_nombre)
//        val descripcion = dialogLayout.findViewById<EditText>(R.id.input_descripcion)
//        val precio = dialogLayout.findViewById<EditText>(R.id.inpt_precio)
//        nombre.setText(plato.nombre.toString())
//        descripcion.setText(plato.descripcion.toString())
//        precio.setText(plato.precio.toString())
//
//
//        builder.setView(dialogLayout)
//
//        builder.setPositiveButton("Guardar") { dialogInterface, i ->
//            if (opcion == 1) {
//
//                plato.nombre = nombre.text.toString()
//                plato.descripcion = descripcion.text.toString()
//                plato.precio = precio.text.toString().toDouble()
//                guardarPlato(plato)
//            } else {
//
//                plato.nombre = nombre.text.toString()
//                plato.descripcion = descripcion.text.toString()
//                plato.precio = precio.text.toString().toDouble()
//                editarPlato(plato)
//            }
//            Toast.makeText(applicationContext, "EditText is " + nombre.text.toString(), Toast.LENGTH_SHORT).show()
//
//        }
//        builder.show()
//    }
}
