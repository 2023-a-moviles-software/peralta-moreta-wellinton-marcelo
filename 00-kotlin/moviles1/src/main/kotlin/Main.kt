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
    val sumaUno = Suma(1,1)
    val sumaDos = Suma(null,1)
    val sumaTres = Suma(1,null)
    val sumaCuatro = Suma(null,null)
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()
    println(Suma.pi)
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)
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
            protected val numeroDos:Int //Propiedad de la clase protected numeros.numeroDos
        ){
            //var cedula: String =""(public es por defecto)
            //private valorCalculado: Int =0(private)
            init {
                this.numeroUno; this.numeroDos; //this es opcional
                numeroUno; numeroDos; //sin el "this", es lo mismo
                println("Inicializando")
            }
        }

//Clase suma
class Suma(//Constructor Primario Suma
    unoParametro : Int, //parametro
    dosParametro : Int,// parametro
): Numeros(unoParametro, dosParametro){//Extendiendo y mandando los parametros (super)
    init{ //Bloque codigo constructos primario
        this.numeroUno
        this.numeroDos
    }

    constructor(//  Segundo constructor
        uno: Int?, //param
        dos: Int //parm
    ):this(
        if(uno ==null) 0 else uno,
        dos
    )

    constructor(//  Tercer constructor
        uno: Int, //param
        dos: Int? //parm
    ):this(
        uno,
        if(dos ==null) 0 else dos
    )

    constructor(//  Cuarto constructor
        uno: Int?, //param
        dos: Int? //parm
    ):this(
        if(uno ==null) 0 else uno,
        if(dos ==null) 0 else dos,
    )

    //metodo
    public fun sumar(): Int{
        val total = numeroUno + numeroDos
        agregarHistorial(total) //this.agregarHistorial(total)
        return total
    }

    companion object{//Atributos y metodos "Compartidos" Singletons o Static de esta clase
        //Todas las instancias de esta clase comparten estos atributos y metodos
        //dentro del companion object
        val pi=3.14

        fun elevarAlCuadrado(num:Int): Int{
            return num*num
        }

        val historialSumas = ArrayList<Int>()

        fun agregarHistorial(valorNuevaSuma: Int){
            historialSumas.add(valorNuevaSuma)
        }
    }


}


