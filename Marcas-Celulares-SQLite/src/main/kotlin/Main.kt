import java.io.File
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun main(){

    val sqlite = SQLite()
    sqlite.crearTablas()

    var opcion : Int;

    do {
        println("")
        println("*********************************")
        println("    Menú CRUD");
        println("1 - Registrar");
        println("2 - Mostrar Datos");
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
                        sqlite.insertarMarca(marca)
                        println("*** Marca de Celular Registrada ***");
                    }
                    2->{
                        val celular = ingresarCelular();
                        sqlite.insertarCelular(celular)
                        println("*** Celular registrado ***");
                    }
                    else -> {
                        println("Opción Inválida!")
                        println("")
                    }
                }
            }
            2 -> {
                val marcasList = sqlite.getMarcas()
                val celularList = sqlite.getCelulares()
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
                        val marcasList = sqlite.getMarcas()
                        if(marcasList.isEmpty()){
                            println("No existe registros de marcas");
                        }else{
                            modificarMarca(marcasList, sqlite)
                        }
                    }
                    2 -> {
                        val celularList = sqlite.getCelulares()
                        if(celularList.isEmpty()){
                            println("No existe registros de celulares");
                        }else {
                            modificarCelular(celularList, sqlite)
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
                        val marcasList = sqlite.getMarcas()
                        if(marcasList.isEmpty()){
                            println("No existe registros de Marcas")
                        }else{
                            eliminarMarca(marcasList,sqlite)
                        }
                    }
                    2 -> {
                        val celularList = sqlite.getCelulares()
                        if(celularList.isEmpty()){
                            println("No existe registros de celulares")
                        }else{
                            eliminarCelular(celularList,sqlite);
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
    //cerramos la conexion
    sqlite.closeConnection()
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



//Mostramos en pantalla los datos de las marcas con sus respectivos celulares
fun mostrarDatos(listMarcas: List<Marca>, listCelulares: List<Celular>) {
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
fun modificarCelular(celularList: List<Celular>, sqLite: SQLite) {
    println("---------------------------------------------------")
    print("Ingrese el modelo de celular que desea Actualizar: ")
    val modelo = readLine()?.toString()?:""
    val celular = celularList.find { it.modelo == modelo}

    if(celular != null){
        val celularActualizado = ingresarCelular()
        celularActualizado.modelo = celular.modelo
        sqLite.updateCelular(celularActualizado)
        println("*** Datos de Celular Actualizados ***");
    }else{
        println("Celular incorrecta")
    }
}

//Modificamos una marca
fun modificarMarca(marcasList: List<Marca>, sqLite: SQLite) {
    println("---------------------------------------------------")
    print("Ingrese el ID de la Marca que desea Actualizar: ")
    val marcaId = readLine()?.toInt()?:0
    val marca = marcasList.find { it.id==marcaId }

    if(marca != null){
        val marcaActualizada = ingresarMarca()
        marcaActualizada.id = marca.id
        sqLite.updateMarca(marcaActualizada)
        println("*** Datos de Marca Actualizados ***");
    }else{
        println("Marca de celular incorrecta")
    }
}

//Eliminar una marca
fun eliminarMarca(marcasList: List<Marca>, sqLite: SQLite)  {
    println("--------------------------------------")
    println("Marcas Disponibles")
    marcasList.forEach{ marca ->
        println("ID: ${marca.id}, Nombre: ${marca.nombre}")

    }
    println("")
    print("Ingrese el ID de la marca que desea Eliminar: ")
    val marcaIdBorrar = readLine()?.toInt()?:0
    val marcaBorrar = marcasList.find { it.id == marcaIdBorrar }

    if(marcaBorrar!= null){
        sqLite.deleteMarca(marcaIdBorrar)
        println("Marca de celular eliminada correctamente");
    }else{
        println("Marca de celular incorrecta")
    }
}

//Eliminar un celular
fun eliminarCelular(celularList: List<Celular>, sqLite: SQLite)  {
    println("-----------------------------------------------")
    println("Celulares Disponibles: ")
    celularList.forEach{celular ->
        println("ID marca: ${celular.idMarca}, Modelo: ${celular.modelo}")
    }
    println("")
    print("Ingrese el modelo del celular que desea Eliminar: ")
    val modeloBorrar = readLine()?.toString()?:""
    val celularBorrar = celularList.find { it.modelo == modeloBorrar }

    if(celularBorrar!= null){
        sqLite.deleteCelular(celularBorrar.modelo)
        println("Celular eliminado correctamente");
    }else{
        println("Modelo de celular incorrecto")
    }
}