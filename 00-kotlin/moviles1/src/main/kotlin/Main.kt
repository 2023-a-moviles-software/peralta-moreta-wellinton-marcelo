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



//30 - 05

//Arreglos
//Tipos de Arreglos

//Arreglo Estático
val arregloEstatico: Array<Int> = arrayOf<Int>(1,2,3)
println(arregloEstatico)

//Arreglo dinamico
val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(1,2,3,4,5,6,7,8,9,10)

    //Operadores -> Sirven para los arreglos estaticos y dinamicos

    //For each -> Unit
    //Iterarun arreglo

    val respuestaForEach: Unit = arregloDinamico
        .forEach{valorActual: Int ->
            println("Valor Actual : ${valorActual}")
        }

    arregloDinamico.forEach {println(it)} //it en ingles es eso, significa el elemento iterado

    arregloEstatico
        .forEachIndexed { indice: Int, valorActual: Int ->
            println("Valor ${valorActual} Indice: ${indice}")
        }
    println(respuestaForEach)


//Operador Map -> Muta el arreglo (Cambia el arreglo)
// vAMOS A CREAR UN NUEVO ARREGLO CON LOS DATOS MODIFICADOs
    //1 ) Enviamos el nuevo valor de la iteracion
//    2) deveulve el nuevo arreglo modificado

    val respuestaMap: List<Double> = arregloDinamico
        .map{ valorActual: Int ->
            return@map valorActual.toDouble()+100.00
        }
    println(respuestaMap)

    val respuestaMapDos = arregloDinamico.map{it+15}
    println(respuestaMapDos)

    //oPERADOR Filter -> Filtra el arreglo
    //1 devuelve una expresion true or false
    //2 Nuevo arreglo filtrado

    val respuestaFilter: List<Int> =arregloDinamico
        .filter {valorActual: Int ->
            val mayoresACinco: Boolean = valorActual > 5
            return@filter mayoresACinco
        }

    val respuestaFilterDos = arregloDinamico.filter{it <=5}
    println(respuestaFilter)
    println(respuestaFilterDos)

    // OR AND
    // OR -> aNY (aLGUNO CUMPLE)
    // AND -> All (todos cumplesn?)

    val respuestaAny: Boolean = arregloDinamico
        .any{ valorActual : Int ->
            return@any (valorActual>5)
        }
    println(respuestaAny) //true

    val respuestaAll: Boolean= arregloDinamico
        .all{ valorActual:Int ->
            return@all(valorActual>5)
        }
    println(respuestaAll)//false


    //rEDUCE -> VALOR ACUMULADO
// valor acumulado = 0 ( siempre en 0 en kotlin
    // 1,2,3,4,5 -> sume todos los valores del arreglo
    // valIterac1 = valorempieza  +1 = 0+1 = 1 -> iterac1
    // valIterac2 = iterac1  +2 = 1+2 = 3 -> iterac2
    // valIterac3 = iterac2  +3 = 3+3 = 6 -> iterac3
    // valIterac4 = iterac3  +4 = 6+4 = 10 -> iterac4
    // valIterac5 = iterac4  +5 = 10+5 = 15 -> iterac5
    val respuestaReduce: Int = arregloDinamico
        . reduce{ //acumulado =0 -> siempre empieza en 0
            acumulado: Int, valorActual: Int ->
            return@reduce (acumulado + valorActual) // LOGICA DE NEGOCIO -> sumar precios de carrito de compras
        }
    println(respuestaReduce) //78

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


