import java.io.File
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class Marca(
    var id: Int,
    var nombre: String,
    var pais: String,
    var anioCreacion: Int,
    var ventaMillones: Double
)

class Celular(
    var idMarca: Int,
    var modelo: String,
    var almacenamiento: Int,
    var es5G: Boolean,
    var precio: Double,
    var fechaLanzamiento: Date
)

fun main(){
    val fileMarcas = File("marcas.txt")
    val fileCelulares = File("celulares.txt");
    var marcasList = mutableListOf<Marca>();
    var celularList = mutableListOf<Celular>();
    marcasList= cargarDatosMarcas(fileMarcas);
    celularList= cargarDatosCelulares(fileCelulares);
    var opcion : Int;

    do {
        println("")
        println("*********************************")
        println("    Menú CRUD");
        println("1 - Registrar");
        println("2 - Leer datos desde archivos");
        println("3 - Actualizar datos");
        println("4 - Borrar registros");
        println("0 - Salir del programa");
        print("Ingrese una opción: ")
        opcion = readLine()?.toInt()?:0;
        println("")

        when(opcion){
            1 -> {
                println("--------------------------")
                println("1. Registrar una Marca")
                println("2. Registrar un Celular")
                var aux = readLine()?.toInt()?:0;
                when(aux){
                    1->{
                        val marca = ingresarMarca();
                        marcasList.add(marca);
                        println("*** Marca de Celular Registrada ***");
                    }
                    2->{
                        val celular = ingresarCelular();
                        celularList.add(celular);
                        println("*** Celular registrado ***");
                    }
                    else -> {
                        println("Opción Inválida!")
                        println("")
                    }
                }
                guardarDatos(fileMarcas,fileCelulares,marcasList,celularList);
            }
            2 -> {
                marcasList= cargarDatosMarcas(fileMarcas);
                celularList= cargarDatosCelulares(fileCelulares);
                if(marcasList.isEmpty() && celularList.isEmpty()){
                    println("No existen registros")
                }else{
                    mostrarDatos(marcasList,celularList);
                }
            }
            3 -> {
                println("--------------------------")
                println("1. Modificar una Marca")
                println("2. Modificar un Celular")
                var aux1 = readLine()?.toInt()?:0;
                when(aux1) {
                    1 -> {
                        if(marcasList.isEmpty()){
                            println("No existe registros de marcas");
                        }else{
                            guardarDatos(fileMarcas,fileCelulares,modificarMarca(marcasList),celularList);
                            println("*** Datos de Marca Actualizada ***");
                        }
                    }
                    2 -> {
                        if(celularList.isEmpty()){
                            println("No existe registros de celulares");
                        }else {
                            guardarDatos(fileMarcas, fileCelulares, marcasList, modificarCelular(celularList));
                            println("*** Datos de Celular Actualizados ***");
                        }
                    }
                    else -> {
                        println("Opción Inválida")
                        println("")
                    }
                }
            }
            4 -> {
                println("--------------------------")
                println("1. Elimiar una Marca")
                println("2. Eliminar un Celular")
                var aux2 = readLine()?.toInt()?:0;
                when(aux2) {
                    1 -> {
                        if(marcasList.isEmpty()){
                            println("No existe registros de Marcas")
                        }else{
                            guardarDatos(fileMarcas, fileCelulares, eliminarMarca(marcasList), celularList);
                        }
                    }
                    2 -> {
                        if(celularList.isEmpty()){
                            println("No existe registros de celulares")
                        }else{
                            guardarDatos(fileMarcas, fileCelulares, marcasList, eliminarCelular(celularList));
                        }
                    }
                    else -> {
                        println("Opción Inválida")
                        println("")
                    }
                }
            }
            0 -> {
                println("Saliendo del programa :)")
            }
            else -> {
                println("Opción Inválida")
                println("")
            }
        }
    } while (opcion != 0)
}

//Guardar Datos en archivo de texto
fun guardarDatos(fileMarcas: File, fileCelulares: File, marcasList: MutableList<Marca>, celularList:MutableList<Celular> ){
    // Guardar datos de celulares en el archivo txt
    fileCelulares.printWriter().use { out ->
        celularList.forEach { celular ->
            out.println("${celular.idMarca},${celular.modelo},${celular.almacenamiento},${celular.es5G}," +
                    "${celular.precio},${celular.fechaLanzamiento}")
        }
    }
    // Guardar datos de marcas en el archivo txt
    fileMarcas.printWriter().use { out ->
        marcasList.forEach { marca ->
            out.println("${marca.id},${marca.nombre},${marca.pais},${marca.anioCreacion},${marca.ventaMillones}")
        }
    }
}

//Registrar marca
fun ingresarMarca(): Marca{
    println("----------------------------------------")
    println("Ingrese los datos de la Marca");
    print("Id Marca: ");
    var id = readLine()?.toInt()?:0;
    print("Nombre de la Marca: ")
    var marca = readLine()?:"";
    print("País de origen: ")
    var pais = readLine()?:"";
    print("Anio de creación: ")
    var anioCreacion = readLine()?.toInt()?:0;
    print("Venta unidades (Mill) del último anio: ")
    var ventas = readLine()?.toDouble()?:0.0

    return Marca(id,marca,pais,anioCreacion,ventas);
}

//Registrar Celular
fun ingresarCelular():Celular{
    println("----------------------------------------")
    println("Ingrese los datos del celular")
    print("ID Marca: ")
    val idMarca = readLine()?.toInt()?:0;
    print("Modelo: ")
    val modelo = readLine()?:""
    print("Almacenamiento: ")
    val almacenamiento = readLine()?.toInt()?:0
    print("¿Es 5G?(true/false): ")
    val es5g = readLine()?.toBoolean()?:false
    print("Precio: ")
    val precio = readLine()?.toDouble()?:0.0
    print("Fecha de lanzamiento: ")
    val fechalanzamiento = readLine()?:""
    val formatoFecha = SimpleDateFormat("dd/MM/yyyy")
    val fecha = formatoFecha.parse(fechalanzamiento)

    return Celular(idMarca,modelo,almacenamiento,es5g,precio,fecha);
}

//Cargamos datos de marcas desde el archivo de texto
fun cargarDatosCelulares(file:File): MutableList<Celular> {
    val datosCelulares = mutableListOf<Celular>();
    if(file.exists()){
        file.readLines().forEach(){
                line->
            val datos = line.split(",");
            val aux = datos[5]
            val formatoFecha = SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH)
            val fecha = formatoFecha.parse(aux)

            val celular =
                Celular(datos[0].toInt(),datos[1], datos[2].toInt(),datos[3].toBoolean(),datos[4].toDouble(),fecha)
            datosCelulares.add(celular);
        }
    }
    return datosCelulares;
}

//Cargamos datos de celulares desde archivo de texto
fun cargarDatosMarcas(file:File):MutableList<Marca> {
    val datosMarcas = mutableListOf<Marca>();
    if(file.exists()){
        file.readLines().forEach(){
                line->
            val datos=line.split(",");
            val marca =
                Marca(datos[0].toInt(), datos[1], datos[2], datos[3].toInt(), datos[4].toDouble())
            datosMarcas.add(marca);
        }
    }
    return datosMarcas;
}

//Mostramos en pantalla los datos de las marcas con sus respectivos celulares
fun mostrarDatos(listMarcas: MutableList<Marca>, listCelulares: MutableList<Celular>) {
    val formatoSalida = SimpleDateFormat("dd/MM/yyyy")
    var indice: Int = 0
    listMarcas.forEachIndexed { index, marca ->
        val aux1 = marca.id;
        println("-------------------------------------------------------------")
        println("ID:           " + marca.id)
        println("Nombre Marca: " + marca.nombre)
        println("País Origen:  " + marca.pais)
        println("Anio cracion: " + marca.anioCreacion)
        println("Ventas:       " + marca.ventaMillones+" Millones de unidades")
        listCelulares.forEachIndexed { index, celular ->
            val aux2 = celular.idMarca
            if (aux1 == aux2) {
                println("---------")
                println(""+ (indice + 1) + ".       Modelo:           " + celular.modelo)
                println("         Almacenamiento:   " + celular.almacenamiento+" GB")
                println("         ¿Es 5G?:          " + celular.es5G)
                println("         Precio:           $" + celular.precio)
                println("         Fecha lanzamiento:" + formatoSalida.format(celular.fechaLanzamiento))
                indice++
            }
        }
        indice = 0
    }
}

//Modificamos un celular
fun modificarCelular(celularList: MutableList<Celular>) : MutableList<Celular>  {
    println("-------------------------------------------------------")
    print("Ingrese el modelo del celular que desea Actualizar: ")
    val celularModelo = readLine()?:""
    val celularBuscar= celularList.find { it.modelo==celularModelo }
    val celularIndice = celularList.indexOfFirst { it==celularBuscar }
    if(celularIndice in 0..celularList.size){
        println("Elija la variable que desea modificar")
        println("1. ID Marca: ")
        println("2. Modelo: ")
        println("3. Almacenamiento: ")
        println("4. Es 5G? (true/false): ")
        println("5. Precio: ")
        println("6. Fecha de lanzamiento: ")
        print("Ingrese una opción : ")
        var aux = readLine()?.toInt()?:0
        print("")
        when(aux){
            1 -> {
                print("ID Marca: ")
                var id = readLine()?.toInt()?:0
                celularList[celularIndice].idMarca = id
            }
            2 ->{
                print("Modelo: ")
                var modelo = readLine()?:""
                celularList[celularIndice].modelo = modelo
            }
            3 ->{
                print("Almacenamiento: ")
                var almacenamiento = readLine()?.toInt()?:0
                celularList[celularIndice].almacenamiento = almacenamiento
            }
            4 ->{
                print("¿Es 5G?: ")
                var es5g = readLine()?.toBoolean()?:false
                celularList[celularIndice].es5G = es5g
            }
            5 ->{
                print("Precio: ")
                var precio = readLine()?.toDouble()?:0.0
                celularList[celularIndice].precio = precio
            }
            6 ->{
                print("Fecha de lanzamiento ")
                val fechalanzamiento = readLine()?:""
                val formatoFecha = SimpleDateFormat("dd/MM/yyyy")
                val fecha = formatoFecha.parse(fechalanzamiento)
                celularList[celularIndice].fechaLanzamiento= fecha
            }
            else -> {
                println("Opción inválida")
                println("")
            }
        }
    }else{
        println("Modelo de celular inválido")
    }
    return celularList
}

//Modificamos una marca
fun modificarMarca(marcasList: MutableList<Marca>): MutableList<Marca>  {
    println("---------------------------------------------------")
    print("Ingrese el ID de la Marca que desea Actualizar: ")
    val marcaId = readLine()?.toInt()?:0
    val marcaBuscar= marcasList.find { it.id==marcaId }
    val marcaIndice = marcasList.indexOfFirst { it==marcaBuscar }
    if(marcaIndice in 0..marcasList.size){
        println("Elija la variable que desea modificar")
        println("1. ID Marca ")
        println("2. Nombre de la Marca ")
        println("3. País de origen ")
        println("4. Anio de creación ")
        println("5. Venta unidades (Mill) del último anio")
        print("Ingrese una opción : ")
        var aux = readLine()?.toInt()?:0
        print("")
        when(aux){
            1 -> {
                print("ID Marca: ")
                var id = readLine()?.toInt()?:0
                marcasList[marcaIndice].id = id
            }
            2 ->{
                print("Nombre de la Marca: ")
                var nombre = readLine()?:""
                marcasList[marcaIndice].nombre = nombre
            }
            3 ->{
                print("País de origen: ")
                var pais = readLine()?:""
                marcasList[marcaIndice].pais = pais
            }
            4 ->{
                print("Anio de creación: ")
                var anio = readLine()?.toInt()?:0
                marcasList[marcaIndice].anioCreacion = anio
            }
            5 ->{
                print("Venta unidades (Mill) último anio: ")
                var venta = readLine()?.toDouble()?:0.0
                marcasList[marcaIndice].ventaMillones = venta
            }
            else -> {
                println("Opción inválida")
                println("")
            }

        }
    }else{
        println("Marca de celular incorrecta")
    }
    return marcasList
}

//Eliminar una marca
fun eliminarMarca(marcasList: MutableList<Marca>): MutableList<Marca>  {
    print("Ingrese el ID de la marca que desea Eliminar: ")
    val marcaIdBorrar = readLine()?.toInt()?:0
    val marcaBuscar= marcasList.find { it.id==marcaIdBorrar }
    val marcaIndice = marcasList.indexOfFirst { it==marcaBuscar }
    if(marcaIndice in 0..marcasList.size){
        marcasList.removeAt(marcaIndice);
        println("Celular eliminado correctamente");
    }else{
        println("Marca de celular incorrecta")
    }
    return marcasList
}

//Eliminar un celular
fun eliminarCelular(celularList: MutableList<Celular>): MutableList<Celular>  {
    print("Ingrese el modelo del celular a eliminar: ")
    val modeloBorrar = readLine()?:""
    val celularBuscar= celularList.find { it.modelo==modeloBorrar }
    val borrarIndice = celularList.indexOfFirst { it==celularBuscar }
    if(borrarIndice in 0..celularList.size){
        celularList.removeAt(borrarIndice);
        println("Celular eliminado correctamente");
    }else{
        println("Modelo de celular incorrecto")
    }
    return celularList
}