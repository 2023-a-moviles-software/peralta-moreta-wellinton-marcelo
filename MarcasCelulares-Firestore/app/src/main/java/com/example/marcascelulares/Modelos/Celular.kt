package com.example.marcascelulares.Modelos


class Celular{
    var id: Int
    var modelo: String
    var almacenamiento: Int
    var es5g: Boolean
    var precio: Double
    var fechaLanzamiento: String

    constructor(
        id: Int,
        modelo: String,
        almacenamiento: Int,
        es5g: Boolean,
        precio: Double,
        fechaLanzamiento: String,
    ){
        this.id = id
        this.modelo = modelo
        this.almacenamiento = almacenamiento
        this.es5g = es5g
        this.precio = precio
        this.fechaLanzamiento = fechaLanzamiento
    }


    override fun toString(): String {
        return "${this.modelo}  -   $ ${this.precio}"
    }
}



