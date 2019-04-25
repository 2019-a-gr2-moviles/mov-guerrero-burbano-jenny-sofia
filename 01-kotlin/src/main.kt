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
    val respuestaFilter=notas.filter {
        it in 2..4

    }.map {
        it * 2
    }
    respuestaFilter.forEach{
        println(it)
    }
    val novias = arrayListOf(1,2,2,3,4,5)
    val respuestaNovioia: Boolean=novias.any{
        it==7
    }
    val tazos= arrayListOf(1,2,3,4,5,6,7)
    val RespuestaTazos=tazos.all {
        it>1
    }
    val totalTazos=tazos.reduce{
        valorAcumulador, tazo->
            valorAcumulador+ tazo
    }
    println(totalTazos)
    val n= Numero("2")

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


class Usuario(val cedula:String){
    public var nombre: String = ""
    public var apellido:String="";

    constructor(cedula:String,apellido:String):this(cedula){

        this.apellido=apellido
    }

}
class UsuarioKT(public var nombre: String, public val apellido: String, protected var id: Int){
    fun hola():String{
        return this.apellido
    }
    private fun hola2(){

    }
    private fun hola3(){

    }
    companion object {
        val gravedad= 10.5
        fun correr(){
            println("Estoy corriendo en $gravedad")

        }
    }
}
fun aa(){
    UsuarioKT.gravedad
    UsuarioKT.correr()

}
fun a() {

    var adrian = UsuarioKT("a", "b", 2);
    adrian.nombre= "sofi";
}
class Numero(var numero:Int){
    constructor(numeroString:String):this(numeroString.toInt()){ // la clase recibe tanto enteros como strings
        println("contructuor")
    }
    init{
        println("Init")
    }
}
abstract class Numeros (var numeroUno:Int, var numeroDos:Int){

}
class Suma( num1:Int,  num2:Int):Numeros(num1, num2){

}
fun cc(){
    val a= Suma(1,2)
   // val b= Numeros(1,3)


}
fun presley(requerido: Int, opcional:Int= 1, nulo: Int?){

}
fun cddd(){
    presley(1,3,4) //Name parameters
    presley(7,2,3)
}
