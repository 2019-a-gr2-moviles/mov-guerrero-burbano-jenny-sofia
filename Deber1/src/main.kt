import java.awt.Dimension
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

val ruta = "src/archivo.txt"
val archivo = File(ruta)



var cadena: String = ""
val f = FileReader(archivo)
val b = BufferedReader(f)
val dbPlatos = ArrayList<Plato>()
fun main(args: Array<String>) {
    try {
        val bw: BufferedWriter
        cadena = b.readLine()
        var tempIndex=-1
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
        var mostrarMen=0
       do{
            mostrarMen=mostrarMenu(dbPlatos)
           when(mostrarMen){
               1->
                   mostrarPlatos(dbPlatos)
               2->
                   ingresarPlato(dbPlatos)
               3-> {
                   tempIndex=mostrarPlatos(dbPlatos);
                   actualizarElmento(dbPlatos,tempIndex)
               }

               4-> {
                   tempIndex = mostrarPlatos(dbPlatos);
                   eliminarElmento(dbPlatos, tempIndex)
               }
           }
       }while (mostrarMen!= 5)

    } catch (e: IllegalStateException) {
        actualizarElmento(dbPlatos, 0)
        print("No existen datos almacenados")
    }


    // bw = BufferedWriter(FileWriter(archivo));
    // bw.write("holaaaa.");

    //  b.close()

}

fun mostrarPlatos(dbPlatos: ArrayList<Plato>):Int{
    val idPlato = JTextField(10)
    val p = JPanel()
    val dtm =
        DefaultTableModel(arrayOf(arrayOf("Id", "Nombre", "Precio")), arrayOf("Names", "In", "Order"))
    dbPlatos.forEach{ element ->
        var arrayTemp= arrayOf(element.idPlato, element.nombre, element.tipo, element.precio)
        dtm.addRow(arrayTemp)

    }
    dtm.removeRow(0)
    val tabla = JTable(dtm)
    tabla.createDefaultColumnsFromModel()
    tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    p.add(tabla)


    JOptionPane.showConfirmDialog(null, p, "MENÚ : ", 2);
  //  actualizarElmento(dbPlatos, 0)
    //eliminarElmento(dbPlatos, 0)
    //println(dbPlatos)
    //actualizarDB(dbPlatos)
    return tabla.selectedRow
}
fun mostrarMenu(dbPlatos: ArrayList<Plato>):Int {
    val idPlato = JTextField(10)
    val p = JPanel()
    val dtm =
        DefaultTableModel(arrayOf(arrayOf("Índice", "Opcion"), arrayOf("1.-",
            "Ver platos "),arrayOf("2.-", "Ingresar"), arrayOf("3.-", "Editar"),arrayOf("4.-", "Eliminar"),arrayOf("5.-", "Salir")), arrayOf("Names", "In"))

    val tabla = JTable(dtm)
    tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    tabla.createDefaultColumnsFromModel()
    p.add(tabla)





    JOptionPane.showConfirmDialog(null, p, "MENÚ : ", 2);

    //eliminarElmento(dbPlatos, 0)
    //println(dbPlatos)
    //actualizarDB(dbPlatos)
    println( "--"+ tabla.selectedColumn+ "g--"+ tabla.selectedRow);
    return tabla.selectedRow
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
fun ingresarPlato(dbPlatos: ArrayList<Plato>){
    val p = JPanel()
    val id = JTextField(10)
    val nombre = JTextField(10)
    val tipo = JTextField(10)
    val precio = JTextField(10)
    val name = JButton("Name")
    // val texto:String = "salto de linea \n hola"

    //  println(texto)
    //var platoTemp:Plato=dbPlatos[index]
    p.add(JLabel("Identificador :"))
    p.add(id)
    p.add(JLabel("Nombre :"))
    p.add(nombre)
    p.add(JLabel("Tipo :  "))
    p.add(tipo)
    p.add(JLabel("Precio : "))
    p.add(precio)


    JOptionPane.showConfirmDialog(null, p, "ACTUALIZACIÓN DE PLATO : ", JOptionPane.OK_CANCEL_OPTION);
    val platoTemp = Plato(
        idPlato = id.text,
        nombre = nombre.text,
        tipo = tipo.text,
        precio = precio.text.toDouble()
    )
    dbPlatos.add(platoTemp)

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

