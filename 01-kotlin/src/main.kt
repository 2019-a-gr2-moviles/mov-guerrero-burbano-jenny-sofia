import java.util.*

fun main(args:Array<String>){

    println("hellow world")
    var nombre="adrian"
    nombre=""
    val nombreI="çadrian"
    //nombreI = "jsjs"
    val apellido: String="Eguez"
    val edad: Int =29
    val sueldo: Double=1.21
    val casadi: Boolean=false
    val profesor:  Boolean=true
    val hijos=null
    //Condicionales
    if (apellido== "Eguez"){
        println("Verdadero")
    }else{
        println("Falso");
    }
    val tieneNombreApellido= if(apellido!=null && nombre!=null) "ok" else "bad"
    println(tieneNombreApellido)
    println(nombre + apellido)
    estaJalado(5.9)
    estaJalado(7.0)
    estaJalado(10.0)
    estaJalado(0.0)
    holaMundo("holaaaa")
    holaMundoAvanzado(2345678)
    println(sumardosnumero(1,4))
    val arregloCumpleanos= intArrayOf(1,2,3,4)
    val arregloTodo= arrayOf(1,"asd", 10.2, true)
    arregloCumpleanos[0]=4
    println(arregloCumpleanos[0])
    arregloCumpleanos.set(1,5)
    val date= Date()
    var notas= arrayListOf(1,2,3,4,5)
    val notasDos=notas.map { nota->


        val modulo = nota % 2
        val esPar = 0
        when (modulo) {
            esPar -> {
                nota + 1
            }
            else -> {
                nota + 2
            }
        }
    }
    notasDos.forEach{
        println(it)
    }
    notas.forEachIndexed() {
        index, nota:Int->

        println("$index. $nota")
    }

}
fun estaJalado(nota:Double){
    when (nota){
        7.0 ->{
         println("Pasaste con las justas")
        }
        10.0 ->{
            println("Genial")
        }
        0.0->{
            println("Dios mio")
        }
        else->{
            println("Tu nota es ${nota}:")
            println("Tu nota es $nota:")
        }
    }
}
fun holaMundo(mensaje:String){
    println("Mensaje: $mensaje")
}
fun holaMundoAvanzado(mensaje:Any):Unit{
    println("Mensaje: $mensaje")
}
fun sumardosnumero(numUno:Int, numDos:Int):Int{
    return numUno + numDos

}

