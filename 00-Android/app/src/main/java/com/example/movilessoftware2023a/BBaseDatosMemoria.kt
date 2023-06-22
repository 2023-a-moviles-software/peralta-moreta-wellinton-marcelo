package com.example.movilessoftware2023a
//Base de datos Memoria
class BBaseDatosMemoria {
    companion object{
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init{
            arregloBEntrenador
                .add(
                    BEntrenador(1,"Wellinton","wp@com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(2,"Dennis","dc@com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(3,"Json","js@com")
                )
        }
    }

}