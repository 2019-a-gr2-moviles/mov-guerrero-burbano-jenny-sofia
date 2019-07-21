package com.example.a02debermensajesrv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_fragmentos.*

class FragmentosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragmentos)
        btn_primer.setOnClickListener {
            abrirPrimerFragmento()
        }
        btn_segundo.setOnClickListener {
            abrirSegundoFragmento()
        }
        btn_tercero.setOnClickListener {
            abrirTercerFragmento()
        }
    }
    fun abrirPrimerFragmento(){
        //1.-MANAGER
        val fragmentManagert= supportFragmentManager
        // 2.- EMPEZAR LA TRANSACCIÓN
        val transaccion = fragmentManagert.beginTransaction()
        //3.Definir la instancia del fragmento
        val primerFragmento= BlankFragment()
        //4. reemplazamos el fragmento
        transaccion.replace(R.id.layout_fragment, primerFragmento)
        //5.-Trminar la transaccion
        transaccion.commit()
    }
    fun abrirSegundoFragmento(){
        //1.-MANAGER
        val fragmentManagert= supportFragmentManager
        // 2.- EMPEZAR LA TRANSACCIÓN
        val transaccion = fragmentManagert.beginTransaction()
        //3.Definir la instancia del fragmento
        val primerFragmento= SegundoFragment()
        //4. reemplazamos el fragmento
        var argumentos = Bundle()
        argumentos.putInt("contador", 1)
        primerFragmento.arguments= argumentos

        transaccion.replace(R.id.layout_fragment, primerFragmento)
        //5.-Trminar la transaccion
        transaccion.commit()
    }
    fun abrirTercerFragmento(){
        //1.-MANAGER
        val fragmentManagert= supportFragmentManager
        // 2.- EMPEZAR LA TRANSACCIÓN
        val transaccion = fragmentManagert.beginTransaction()
        //3.Definir la instancia del fragmento
        val primerFragmento= TercerFragment()
        var argumentos = Bundle()
        argumentos.putInt("contador", 1)
        primerFragmento.arguments= argumentos
        //4. reemplazamos el fragmento
        transaccion.replace(R.id.layout_fragment, primerFragmento)
        //5.-Trminar la transaccion
        transaccion.commit()
    }
}
