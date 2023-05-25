
import java.util.*

fun main(args: Array<String>) {
    println("Hello World!")

    //inmutable
    //Se denota con val
    val inmutable: String="Wellinton";
    inmutable = "Juan"
    //mutable
    //Se diferencia con var
    var mutable: String="Quito";
    mutable="Ambato";

    // val > var
    // de preferencia usaremos el val

    // Duck typing
    // nos reconoce el tipo de variable en caso de no señalar su tipo
    var ejVariable = "Wellinton Peralta";
    val ejEdad: Int= 22;
    ejVariable.trim()
    //ejVariable=ejEdad;

    //Variables primitivas

    val nombreProfe: String ="Adrian Eguez";
    val sueldo: Double = 1.1;
    val estadoCivil: char = 'C';
    val mayorEdad: Boolean = true;

    //Clases Java
    val FechaNacimiento: Date = Date();

    //SWITCH

    val estadoCivilWhen="C";
    when (estadoCivilWhen){
        ("C")->{
            println("CASADO")
        }
        "S"->{
            println("SOLTERO")
        }
        else ->{
            println("Nos Sabemos")
        }
    }

    val esSoltero = (estadoCivilWhen =="S")
    val coqueteo = if(esSoltero)"Si" else "No"

    //parametros
    calcularSueldo(10.00)
    calcularSueldo(10.00, 12.00, 20.00)
    calcularSueldo(10.00, bonoEspecial = 20.00) //Name parameters
    calcularSueldo(bonoEspecial = 20.00, sueldo=10.00, tasa=14.00) //parametros nombrado

    //void -> unit : para que no retorne nada
    new*
    fun imprimirNombre(nombre: String): Unit{
        println("Nombre : ${nombre}") //template
    }

    new*
    fun calculcarSueldo(
        sueldo: Double, //Requerido
        tasa: Double = 12.00,//Opcional(Defecto)
        bonoEspecial:Double? = null, //Opcion null: nulleable
    ): Double{
        //Int ->Int? (nullable)
        //String -> String?(nullable)
        //Date -> Date?(nullable)
        if(bonoEspecial ==null){
            return sueldo*(100/tasa)
        }else{
            return sueldo*(100/tasa)+ bonoEspecial
        }


        abstract class NumeroJava{
            protected val numeroUno:Int
            private val numeroDos:Int

            new*
            constructor(
                uno: Int,
                dos: Int
            ){
                this.numeroUno = uno
                this.numeroDos = dos
                println("Inicializando")
            }
        }

    }






}