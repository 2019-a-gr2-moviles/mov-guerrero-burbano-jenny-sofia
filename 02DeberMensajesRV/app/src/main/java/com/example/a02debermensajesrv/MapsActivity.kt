package com.example.a02debermensajesrv

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnCameraIdleListener,
    GoogleMap.OnCameraMoveListener, GoogleMap.OnCameraMoveStartedListener,
    GoogleMap.OnPolylineClickListener,GoogleMap.OnPolygonClickListener{
    override fun onPolylineClick(p0: Polyline?) {
        Log.i("maps", "CLICK POLILINE")
    }

    override fun onPolygonClick(p0: Polygon?) {
        Log.i("maps", "CLIC POLIGONO")
    }

    override fun onCameraIdle() {
        Log.i("maps", "esto")
    }

    override fun onCameraMove() {
        Log.i("maps", "move")
    }

    override fun onCameraMoveStarted(p0: Int) {
        Log.i("maps", "starter") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var mMap: GoogleMap
    private var tienePermisosLocalizacion= false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        solicitarPermisosLocalizacion()

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
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

        // Add a marker in Sydney and move the camera
        val foch = LatLng(-0.202760, -78.490813)
        establecerListener(mMap)
      //  mMap.addMarker(MarkerOptions().position(fo).title("Marker in Sydney"))
//        mMap.addMarker(MarkerOptions()
//            .position(foch)
//            .title("Marker in Sydney"))
        val titulo= "foch"
        anadirMarcado(foch,titulo)
        moverCamara(foch, 17f)
        establecerconfiguracionMapa(mMap)
        val poliLineaUno= googleMap.addPolyline(
            PolylineOptions()
                .clickable(true).add(
                    LatLng(-0.210462, -78.493948),
                    LatLng(-0.208218, -78.490163),
                    LatLng(-0.208583, -78.488940),
                    LatLng(-0.209377, -78.490303)
                )
        )
        val poliOligini=googleMap
            .addPolygon(
                PolygonOptions()
                    .clickable(true)
                    .add(
                        LatLng(-0.209431, -78.490078),
                        LatLng(-0.208734, -78.488951),
                        LatLng(-0.209431, -78.488286),
                        LatLng(-0.210085, -78.489745)
                    )

            )
        poliOligini.fillColor= -0xc771c4

    }
    fun anadirMarcado(latLng: LatLng, titulo:String ){
        mMap.addMarker(
            MarkerOptions()
                .position(latLng)
                .title("Marker in poli")
        )
    }
    fun moverCamara(latLng: LatLng, zoom: Float = 10f){

        mMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(latLng, zoom)
        )

    }
    fun establecerconfiguracionMapa(mapa: GoogleMap){
        val contexto= this.applicationContext
        with(mapa){
            val permisFineLocation = ContextCompat.checkSelfPermission(
                contexto,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            val tienePermission = permisFineLocation== PackageManager.PERMISSION_GRANTED
            if(tienePermission) {
                mapa.isMyLocationEnabled = true
            }

            this.uiSettings.isZoomControlsEnabled=true
            uiSettings.isMyLocationButtonEnabled=true
        }
    }
    fun solicitarPermisosLocalizacion(){
        val permisFineLocation = ContextCompat.checkSelfPermission(
            this.applicationContext,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
        val tienePermission = permisFineLocation== PackageManager.PERMISSION_GRANTED
        if(tienePermission){
            Log.i("mapa", "TIENE PERMISO")
            this.tienePermisosLocalizacion=true
        }else{
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ),
                1 //CÓDIGO QUE VAMOS A ESPERAR
            )
        }
    }
    fun establecerListener(map:GoogleMap){
        with(map){
            setOnCameraIdleListener(this@MapsActivity)
            setOnCameraMoveStartedListener(this@MapsActivity)
            setOnCameraMoveListener(this@MapsActivity)
            setOnPolygonClickListener(this@MapsActivity)
            setOnPolylineClickListener(this@MapsActivity)
        }
    }
}
