package com.example.a05proyectorestaurante

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.dialog.*
import kotlinx.android.synthetic.main.layout_menu.*


class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val opcion= intent.getIntExtra("opcion",-1)
        var listaPlatos = arrayListOf<Plato>()
        if(opcion==1){
            btn_nuevoPlato.visibility= View.INVISIBLE
        }else{
            btn_nuevoPlato.setOnClickListener {
                withEditText(Plato("", "",0.0), 1)
            }
        }
        listaPlatos.add(Plato("Oja", "rico", 1.2))
        listaPlatos.add(Plato("Oja22", "ricoa", 3.2))

        var lista= listOf<Plato>()


        val url = "http://192.168.200.5:1337/plato"
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

                        var platoParseada = Klaxon().parseArray<Plato>(data)
                        lista=platoParseada!!
                        //iniciarRecycleView(platoParseada!!, this, rv_plato)
                        runOnUiThread {
                            iniciarRecycleView(lista, this, rv_plato, opcion)
                        }


                        platoParseada?.forEach{
                            Log.i("http", it.nombre)
                        }
                    }
                }
            }



    }
    fun guardarPlato(url:String){

    }
    fun iniciarRecycleView(lista: List<Plato>, actividad:MenuActivity,recycler_view: RecyclerView, opcion: Int){
        val adaptadorPlato= AdaptadorMenu(lista, actividad, recycler_view, opcion)
        rv_plato.adapter=adaptadorPlato
        rv_plato.itemAnimator = DefaultItemAnimator()
        //Nos falta el layout manager
        rv_plato.layoutManager= LinearLayoutManager(this)
        adaptadorPlato.notifyDataSetChanged()
    }
    fun withEditText(plato:Plato, opcion: Int) {

        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        builder.setTitle("With EditText")
        val dialogLayout = inflater.inflate(R.layout.dialog, null)

        val nombre  = dialogLayout.findViewById<EditText>(R.id.input_nombre)
        val descripcion  = dialogLayout.findViewById<EditText>(R.id.input_descripcion)
        val precio  = dialogLayout.findViewById<EditText>(R.id.inpt_precio)
        nombre.setText(plato.nombre.toString())
        descripcion.setText(plato.descripcion.toString())
        precio.setText(plato.precio.toString())

        builder.setView(dialogLayout)

        builder.setPositiveButton("Guardar") { dialogInterface, i ->
            if(opcion==1){
                val (request, response, result) = Fuel.get("http://192.168.200.5:1337/plato")
                    .response()
            }
            Toast.makeText(applicationContext, "EditText is " + nombre.text.toString(), Toast.LENGTH_SHORT).show()

        }
        builder.show()
    }
}
