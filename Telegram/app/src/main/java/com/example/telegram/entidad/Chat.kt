package com.example.telegram.entidad

class Chat(
    var nombre: String,
    var mensaje: String,
    var hora: String,
    var nMensajes: Int,
    var src: String
){
    override fun toString(): String {
        return "Chat(nombre='$nombre', mensaje='$mensaje', hora='$hora', nMensajes=$nMensajes, src=$src)"
    }
}