import java.util.*

fun main(args: Array<String>) {
    println("Hello World!")

    //inmutable
    //Se denota con val
    val inmutable: String = "Wellinton";

    //mutable
    //Se diferencia con var
    var mutable: String = "Quito";
    mutable = "Ambato";

    // val > var
    // de preferencia usaremos el val

    // Duck typing
    // nos reconoce el tipo de variable en caso de no señalar su tipo
    var ejVariable = "Wellinton Peralta";
    val ejEdad: Int = 22;
    ejVariable.trim()
    //ejVariable=ejEdad;

    //Variables primitivas

    val nombreProfe: String = "Adrian Eguez";
    val sueldo: Double = 1.1;
    val estadoCivil: Char = 'C';
    val mayorEdad: Boolean = true;

    //Clases Java
    val FechaNacimiento: Date = Date();

    //SWITCH

    val estadoCivilWhen = "C";
    when (estadoCivilWhen) {
        ("C") -> {
            println("CASADO")
        }
        "S" -> {
            println("SOLTERO")
        }
        else -> {
            println("Nos Sabemos")
        }
    }

    val esSoltero = (estadoCivilWhen == "S")
    val coqueteo = if (esSoltero) "Si" else "No"

    //parametros
    calcularSueldo(10.00)
    calcularSueldo(10.00, 12.00, 20.00)
    calcularSueldo(10.00, bonoEspecial = 20.00) //Name parameters
    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 14.00) //parametros nombrado
}

//void -> unit : para que no retorne nada

fun imprimirNombre(nombre: String): Unit {
    println("Nombre : ${nombre}") //template
}


fun calcularSueldo(
    sueldo: Double, //Requerido
    tasa: Double = 12.00,//Opcional(Defecto)
    bonoEspecial: Double? = null, //Opcion null: nulleable
): Double {
    //Int ->Int? (nullable)
    //String -> String?(nullable)
    //Date -> Date?(nullable)
    if (bonoEspecial == null) {
        return sueldo * (100 / tasa)
    } else {
        return sueldo * (100 / tasa) + bonoEspecial
    }
}
        abstract class NumeroJava{
            protected val numeroUno:Int
            private val numeroDos:Int


            constructor(
                uno: Int,
                dos: Int
            ){
                this.numeroUno = uno
                this.numeroDos = dos
                println("Inicializando")
            }
        }

        abstract class Numeros( //Constructor PRIMARIO
            //Ejemplo;
            //uno: Int, (parametro(Sin modificador de acceso))
            //private var uno: int // Propiedad publica Clase numeros.uno
            //var uno: Int, //Propiedad de la clase (por defecto es PUBLIC)
            //public var uno: Int,
            protected val numeroUno:Int, //Propiedad de la clasew protected numeros.numeroUno
            private val numeroDos:Int, //Propiedad de la clase protected numeros.numeroDos
        ){
            //var cedula: String =""(public es por defecto)
            //private valorCalculado: Int =0(private)
            init {
                this.numeroUno; this.numeroDos; //this es opcional
                numeroUno; numeroDos; //sin el "this", es lo mismo
                println("Inicializando")
            }
        }
