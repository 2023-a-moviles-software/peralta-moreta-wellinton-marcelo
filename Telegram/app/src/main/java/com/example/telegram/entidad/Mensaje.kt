package com.example.telegram.entidad

class Mensaje (

    var contenido: String,
    var hora: String

    ){

    override fun toString(): String {
        return "Mensaje(contenido='$contenido', hora='$hora')"
    }
}