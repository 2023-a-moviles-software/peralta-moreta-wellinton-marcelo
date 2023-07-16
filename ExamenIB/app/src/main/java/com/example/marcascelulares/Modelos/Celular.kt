package com.example.marcascelulares.Modelos

class Celular {
    var idCelular:Int
    var modelo: String
    var almacenamiento: Int
    var es5g: Boolean
    var precio: Double
    var fechaLanzamiento: String

    constructor(
        idCelular: Int,
        modelo: String,
        almacenamiento: Int,
        es5g: Boolean,
        precio: Double,
        fechaLanzamiento: String
    ) {
        this.idCelular = idCelular
        this.modelo = modelo
        this.almacenamiento = almacenamiento
        this.es5g=es5g
        this.precio=precio
        this.fechaLanzamiento=fechaLanzamiento
    }

    override fun toString(): String {
        return "${this.modelo} - $ ${this.precio}"
    }
}


