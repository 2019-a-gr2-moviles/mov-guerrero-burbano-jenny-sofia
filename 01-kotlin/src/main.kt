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
