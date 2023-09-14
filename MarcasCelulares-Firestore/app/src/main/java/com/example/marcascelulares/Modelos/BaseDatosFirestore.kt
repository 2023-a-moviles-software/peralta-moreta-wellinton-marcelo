package com.example.marcascelulares.Modelos

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await


class BaseDatosFirestore {


    companion object{

    private const val MARCAS_COLLECTION = "marcas"
    private val db : FirebaseFirestore = FirebaseFirestore.getInstance()
    private val marcasCollection = db.collection("marcas")

    // Métodos para marcas

    fun crearMarca(marca: Marca) {
        db.collection("marcas")
            .document(marca.nombre.toString())
            .set(marca)
            .addOnSuccessListener { documentReference ->
                println("Se agrego una marca")
            }
            .addOnFailureListener { e ->
                println("Error al agregar la marca: $e")
            }
    }

    fun actualizarMarca(marca: Marca) {
        db.collection("marcas")
            .document(marca.nombre.toString())
            .set(marca)
            .addOnSuccessListener {
                println("Marca actualizada correctamente")
            }
            .addOnFailureListener { e ->
                println("Error al actualizar la marca: $e")
            }
    }

    fun eliminarMarca(nombreMarca: String) {
        db.collection("marcas")
            .document(nombreMarca.toString())
            .delete()
            .addOnSuccessListener {
                println("Marca eliminada correctamente :)")

            }
            .addOnFailureListener { e ->
                println("Error al eliminar la marca: $e")
            }
    }

        suspend fun obtenerMarcaPorNombre(nombre: String): Marca? {
            try {
                val document = db.collection(MARCAS_COLLECTION).document(nombre).get().await()

                if (document.exists()) {
                    val marca = Marca(
                        id = document.getLong("id")?.toInt() ?: 0,
                        nombre = document.getString("nombre") ?: "",
                        pais = document.getString("pais") ?: "",
                        anioCreacion = document.getLong("anioCreacion")?.toInt() ?: 0,
                        ventaMillones = document.getDouble("ventaMillones") ?: 0.0,
                        celulares = obtenerTodosLosCelulares(nombre)
                    )
                    return marca
                } else {
                    return null
                }
            } catch (e: Exception) {
                return null
            }
        }


        suspend fun obtenerTodasLasMarcas(): List<Marca> {
            val marcas = mutableListOf<Marca>()
            try {
                val querySnapshot = db.collection(MARCAS_COLLECTION).get().await()

                if (!querySnapshot.isEmpty) {
                    for (document in querySnapshot) {
                        val id = document.getLong("id")?.toInt()?: 0
                        val nombre = document.getString("nombre") ?: ""
                        val pais = document.getString("pais") ?: ""
                        val anioCreacion = document.getLong("anioCreacion")?.toInt() ?: 0
                        val ventaMillones = document.getDouble("ventaMillones") ?: 0.0
                        val celulares = obtenerTodosLosCelulares(nombre)

                        val marca = Marca(id, nombre, pais, anioCreacion, ventaMillones, celulares)
                        marcas.add(marca)
                    }
                } else {
                }
            } catch (e: Exception) {

            }

            return marcas
        }


        fun crearCelular(nombreMarca: String, celular: Celular) {

            db.collection("marcas")
                .document(nombreMarca)
                .collection("Modelos")
                .document(celular.modelo)
                .set(celular)
                .addOnSuccessListener { documentReference ->
                    println("se agrego un modelo")
                }
                .addOnFailureListener { e ->
                    println("Error al agregar el celular: $e")
                }
        }

        fun actualizarCelular(nombreMarca: String, modeloCelular: String, celular: Celular) {
            db.collection("marcas")
                .document(nombreMarca)
                .collection("Modelos")
                .document(modeloCelular)
                .set(celular)
                .addOnSuccessListener {
                    println("Celular actualizado correctamente")
                }
                .addOnFailureListener { e ->
                    println("Error al actualizar el celular: $e")
                }
        }

        fun eliminarCelular(nombreMarca: String, modeloCelular: String) {
            db.collection("marcas")
                .document(nombreMarca)
                .collection("Modelos")
                .document(modeloCelular)
                .delete()
                .addOnSuccessListener {
                    println("Celular eliminado correctamente")
                }
                .addOnFailureListener { e ->
                    println("Error al eliminar el celular: $e")
                }
        }

        suspend fun obtenerCelularporNombre(nombreMarca: String, modeloCelular: String): Celular? {
            try {
                val documentSnapshot = marcasCollection.document(nombreMarca)
                    .collection("Modelos")
                    .document(modeloCelular)
                    .get()
                    .await()
                if(documentSnapshot.exists()){
                    val celular = Celular(
                        id = documentSnapshot.getLong("id")?.toInt()?: 0,
                        modelo = documentSnapshot.getString("modelo") ?: "",
                        almacenamiento = documentSnapshot.getLong("almacenamiento")?.toInt()?: 0,
                        es5g = documentSnapshot.getBoolean("es5g")?:false,
                        precio = documentSnapshot.getDouble("precio") ?: 0.0,
                        fechaLanzamiento = documentSnapshot.getString("fechaLanzamiento") ?: ""
                    )
                    return celular
                } else {
                    return null
                }
            } catch (e: Exception) {
                return null
            }
        }


        suspend fun obtenerTodosLosCelulares(nombreMarca: String): List<Celular> {
            val celularesList = mutableListOf<Celular>()
            return try {
                val querySnapshot = marcasCollection.document(nombreMarca)
                    .collection("Modelos")
                    .get()
                    .await()

                if (!querySnapshot.isEmpty) {
                    for (document in querySnapshot) {
                        val id = document.getLong("id")?.toInt()?: 0
                        val modelo = document.getString("modelo") ?: ""
                        val almacenamiento = document.getLong("id")?.toInt()?: 0
                        val es5g = document.getBoolean("es5g")?:false
                        val precio = document.getDouble("precio") ?: 0.0
                        val fechaLanzamiento = document.getString("fechaLanzamiento") ?: ""

                        val celular = Celular(id,modelo,almacenamiento,es5g,precio,fechaLanzamiento)
                        celularesList.add(celular)
                    }
                } else {
                }

                celularesList

            } catch (e: Exception) {
                emptyList()
            }
        }

    }

}

