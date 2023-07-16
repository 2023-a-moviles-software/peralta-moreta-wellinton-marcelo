package com.example.marcascelulares.Modelos

class Marca {
    var id: Int
    var nombre: String
    var pais: String
    var anioCreacion: Int
    var ventaMillones: Double
    var celulares: MutableList<Celular>

    constructor(
        id: Int,
        nombre: String,
        pais: String,
        anioCreacion: Int,
        ventaMillones: Double,
        celulares: MutableList<Celular>
    ){
        this.id = id
        this.nombre = nombre
        this.pais = pais
        this.anioCreacion = anioCreacion
        this.ventaMillones = ventaMillones
        this.celulares = celulares
    }

    override fun toString(): String {
        return "${this.nombre} - ${this.pais}"
    }




}