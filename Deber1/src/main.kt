import java.io.*
import java.util.*
import javax.swing.JOptionPane

import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.io.FileNotFoundException
import kotlin.collections.ArrayList

import java.io.FileWriter
import java.io.BufferedWriter
import javax.swing.JLabel
import javax.swing.JTextField
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.*

import java.awt.event.ActionEvent
import javax.swing.table.DefaultTableModel


fun main(args: Array<String>) {


    val ruta = "src/archivo.txt"
    val archivo = File(ruta)
    val bw: BufferedWriter


    var cadena: String = ""
    val f = FileReader(archivo)
    val b = BufferedReader(f)
    val dbPlatos = ArrayList<Plato>()

    try {
        cadena = b.readLine()

        println(cadena)
        val separados = cadena.split(';')

        separados.forEach { element ->
            val temp = element.split(',')
            val platoTemp = Plato(
                idPlato = " ",
                nombre = " ",
                tipo = " ",
                precio = 0.0
            )
            platoTemp.idPlato = temp[0]
            platoTemp.nombre = temp[1]
            platoTemp.tipo = temp[2]
            platoTemp.precio = temp[3].toDouble()

            dbPlatos.add(platoTemp)
        }
        val p = JPanel()
        val dtm =
            DefaultTableModel(arrayOf(arrayOf("1", "2", "3"), arrayOf("Id", "Nombre", "Precio")), arrayOf("Names", "In", "Order"))
        dbPlatos.forEach{ element ->
                var arrayTemp= arrayOf(element.idPlato, element.nombre, element.tipo, element.precio)
                dtm.addRow(arrayTemp)


        }
        dtm.removeRow(0)



        val tabla = JTable(dtm)
        tabla.createDefaultColumnsFromModel()
        p.add(tabla)

        JOptionPane.showConfirmDialog(null, p, "MENÚ : ", 2);
        actualizarElmento(dbPlatos, 0)
        //eliminarElmento(dbPlatos, 0)
        //println(dbPlatos)
        //actualizarDB(dbPlatos)
    } catch (e: IllegalStateException) {
        actualizarElmento(dbPlatos, 0)
        print("No existen datos almacenados")
    }


    // bw = BufferedWriter(FileWriter(archivo));
    // bw.write("holaaaa.");

    //  b.close()

}

fun mostrarMenu(dbPlatos: ArrayList<Plato>) {
    dbPlatos.forEach { item ->


    }
}

fun actualizarDB(dbPlatos: ArrayList<Plato>) {
    val ruta = "src/archivo.txt"
    val archivo = File(ruta)
    val bw: BufferedWriter
    var condicional: String = ""
    var esPrimero: Boolean = true;
    if (archivo.exists()) {
        bw = BufferedWriter(FileWriter(archivo))
        dbPlatos.forEach { item ->
            if (esPrimero) {
                condicional =
                    condicional.plus(item.idPlato).plus(",").plus(item.nombre).plus(",").plus(item.tipo).plus(",")
                        .plus(item.precio)

                esPrimero = false
            } else {
                condicional =
                    condicional.plus(";").plus(item.idPlato).plus(",").plus(item.nombre).plus(",").plus(item.tipo)
                        .plus(",").plus(item.precio)

            }

        }
        bw.write(condicional)

    } else {

        // actualizarElmento(dbPlatos, 0)
        bw = BufferedWriter(FileWriter(archivo))
        bw.write("Acabo de crear el fichero de texto.")
    }
    bw.close()
    /*val bw: BufferedWriter

    bw = BufferedWriter(FileWriter(archivo));

    bw.write(condicional)*/
}

fun eliminarElmento(dbPlatos: ArrayList<Plato>, index: Int) {
    var platoTemp: Plato = dbPlatos[index]
    dbPlatos.remove(platoTemp)
    actualizarDB(dbPlatos)
}

fun actualizarElmento(dbPlatos: ArrayList<Plato>, index: Int) {

    val p = JPanel()
    val nombre = JTextField(10)
    val tipo = JTextField(10)
    val precio = JTextField(10)
    val name = JButton("Name")
    // val texto:String = "salto de linea \n hola"

    //  println(texto)
    //var platoTemp:Plato=dbPlatos[index]
    p.add(JLabel("Nombre :"))

    p.add(nombre)
    p.add(JLabel("Tipo :  "))
    p.add(tipo)
    p.add(JLabel("Precio : "))
    p.add(precio)

    nombre.text = dbPlatos[index].nombre
    tipo.text = dbPlatos[index].tipo
    precio.text = dbPlatos[index].precio.toString()
    JOptionPane.showConfirmDialog(null, p, "ACTUALIZACIÓN DE PLATO : ", JOptionPane.OK_CANCEL_OPTION);
    dbPlatos[index].nombre = nombre.text
    dbPlatos[index].tipo = tipo.text
    dbPlatos[index].precio = precio.text.toDouble()

    actualizarDB(dbPlatos)
//    println(familyName.text)

}


fun añadirElmento(dbPlatos: ArrayList<Plato>, newPlato: Plato) {


}

class Plato(var idPlato: String, var nombre: String, var tipo: String, var precio: Double) {

}

@Throws(FileNotFoundException::class, IOException::class)
fun muestraContenido(archivo: String) {

}

