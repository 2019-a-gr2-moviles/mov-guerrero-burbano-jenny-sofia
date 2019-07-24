package com.example.examendef

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_gestion_materia.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {


    private lateinit var mMap: GoogleMap
    private var idEstudiante: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        idEstudiante = intent.getIntExtra("idEstudiante", -1)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        cargarMaterias(idEstudiante)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        mMap.setOnMarkerClickListener {
            Log.i("maps", it!!.title.toString())
            true
        }
    }

    fun cargarMaterias(idEstudiante: Int) {
        val url = "${MainActivity.url}/materia?estudianteId=${idEstudiante}"
        var lista = listOf<Estudiante>()
        var listaLibros = ArrayList<Estudiante>()


        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        url
            .httpGet()
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http", "Errorssss: ${ex.message} --- ${request}")
                    }
                    is Result.Success -> {

                        val data = result.get()

                        Log.i("http", "TODO BIEN: ${data}")
                        var libroParseada = Klaxon().parseArray<Materia>(data)

                        MainActivity.dbMateria = libroParseada!!
                        runOnUiThread {
                            libroParseada.forEach {

                                val sydney = LatLng(it.latitud.toDouble(), it.longitud.toDouble())
                                mMap.addMarker(MarkerOptions().position(sydney).title("${it.nombre},${it.id}"))
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
                                mMap.setOnMarkerClickListener {
                                    Log.i("maps", it!!.title.toString())
                                    var stringNombreMarker = it!!.title.toString()
                                    val listaSeparado = stringNombreMarker.split(",")
                                    seleccionarMateria(listaSeparado[1].toInt(), idEstudiante)

                                    true
                                }

                            }
                            moverCamara(LatLng(libroParseada!!.get(0).latitud.toDouble(),libroParseada!!.get(0).longitud.toDouble()))

                        }


                    }
                }
            }
    }
    fun moverCamara(latLng: LatLng, zoom: Float = 10f){

        mMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(latLng, zoom)
        )

    }

    fun seleccionarMateria(posicion: Int, idEstudiante: Int) {
        val intent = Intent(
            this, EditMateriaDef::class.java
        )

        intent.putExtra("id", posicion)
        intent.putExtra("idEstudiante", idEstudiante)
        intent.putExtra("opcion", 1)

        startActivity(intent);
    }


}
