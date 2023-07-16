package com.example.marcascelulares.Modelos

class BaseDatos {

    companion object{

        val arrayMarcas = ArrayList<Marca>()

        init {
            arrayMarcas.add(
                Marca(
                    id= 1,
                    nombre= "Samsung",
                    pais= "Corea del Sur",
                    anioCreacion= 1969,
                    ventaMillones= 58.2,
                    celulares = mutableListOf(
                        Celular(
                            idCelular = 1,
                            modelo="S9",
                            almacenamiento = 64,
                            es5g = false,
                            precio= 954.5,
                            fechaLanzamiento = "14-09-2004"
                        ),
                        Celular(
                            idCelular = 2,
                            modelo="S10",
                            almacenamiento = 128,
                            es5g = false,
                            precio= 455.4,
                            fechaLanzamiento = "14-09-2019"
                        )
                        ,Celular(
                            idCelular = 3,
                            modelo="S20",
                            almacenamiento = 128,
                            es5g = true,
                            precio= 1254.5,
                            fechaLanzamiento = "14-09-2020"
                    )
                )
            )
        )
            arrayMarcas.add(
                Marca(
                    id= 2,
                    nombre= "Apple",
                    pais= "EEUU",
                    anioCreacion= 1976,
                    ventaMillones= 73.2,
                    celulares = mutableListOf(
                        Celular(
                            idCelular = 1,
                            modelo="Iphone 8",
                            almacenamiento = 64,
                            es5g = false,
                            precio= 654.5,
                            fechaLanzamiento = "14-09-2004"
                        ),
                        Celular(
                            idCelular = 2,
                            modelo="Iphone X",
                            almacenamiento = 128,
                            es5g = true,
                            precio= 1055.4,
                            fechaLanzamiento = "14-09-2019"
                        )
                        ,Celular(
                            idCelular = 3,
                            modelo="Iphone 12",
                            almacenamiento = 128,
                            es5g = true,
                            precio= 1554.5,
                            fechaLanzamiento = "14-09-2020"
                        )
                    )
                )
            )
            arrayMarcas.add(
                Marca(
                    id= 3,
                    nombre= "Xiaomi",
                    pais= "China",
                    anioCreacion= 2010,
                    ventaMillones= 300.5,
                    celulares = mutableListOf(
                        Celular(
                            idCelular = 1,
                            modelo="Note 8",
                            almacenamiento = 64,
                            es5g = false,
                            precio= 254.5,
                            fechaLanzamiento = "14-09-2004"
                        ),
                        Celular(
                            idCelular = 2,
                            modelo="Note 9",
                            almacenamiento = 128,
                            es5g = true,
                            precio= 355.4,
                            fechaLanzamiento = "14-09-2019"
                        )
                    )
                )
            )
        }

        //FUNCIONES
        fun crearMarca(
            id: Int,
            nombre: String,
            pais: String,
            anioCreacion: Int,
            ventaMillones: Double
        ) {
            arrayMarcas.add(
                Marca(
                    id = id,
                    nombre = nombre,
                    pais = pais,
                    anioCreacion = anioCreacion,
                    ventaMillones = ventaMillones,
                    celulares = mutableListOf()
                )
            )
        }

        fun actualizarMarca(
            id: Int,
            nombre: String,
            pais: String,
            anioCreacion: Int,
            ventaMillones: Double
        ) {
            val marca = buscarMarca(id)
            marca?.id = id
            marca?.nombre = nombre
            marca?.pais = pais
            marca?.anioCreacion = anioCreacion
            marca?.ventaMillones = ventaMillones
        }

        fun buscarMarca(id: Int): Marca? {
            return arrayMarcas.find { it.id == id }
        }

        fun crearCelular(
            idMarca: Int,
            idCelular: Int,
            modelo: String,
            almacenamiento: Int,
            es5g: Boolean,
            precio: Double,
            fechaLanzamiento : String
        ) {
            arrayMarcas[idMarca-1].celulares.add(
                Celular(
                    idCelular = idCelular,
                    modelo = modelo,
                    almacenamiento = almacenamiento,
                    es5g = es5g,
                    precio = precio,
                    fechaLanzamiento = fechaLanzamiento
                )
            )
        }

        fun actualizarCelular(
            idMarca: Int,
            idCelular: Int,
            modelo: String,
            almacenamiento: Int,
            es5g: Boolean,
            precio: Double,
            fechaLanzamiento : String
        ) {
            val marca = buscarMarca(idMarca)
            val celular = marca?.celulares?.find { it.idCelular==idCelular }
            celular?.modelo = modelo
            celular?.almacenamiento = almacenamiento
            celular?.es5g = es5g
            celular?.precio = precio
            celular?.fechaLanzamiento = fechaLanzamiento
        }

    }
}