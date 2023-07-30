package com.example.telegram.entidad

class BaseDatosChats {
    companion object{
        val arregloChats = arrayListOf<Chat>()
        val arregloMensajes = arrayListOf<Mensaje>()
        init{
            arregloChats.add(
                Chat("Omar","Vas ir a comprar ?", "15:00",3,"@drawable/perfil_h1")
            )
            arregloChats.add(
                Chat("Kevin Paredes","Ya llenaste la encuesta?", "16:00",4,"@drawable/perfil_h2")
            )
            arregloChats.add(
                Chat("Dennis","Vamos a clases", "17:00",2,"@drawable/perfil_h4")
            )
            arregloChats.add(
                Chat("Tefa","Me avisas porfa", "18:00",16,"@drawable/perfil_m1")
            )
            arregloChats.add(
                Chat("Erika","Me puedes ayudar con un deber", "12:00",1,"@drawable/perfil_m2")
            )
            arregloChats.add(
                Chat("Cris Lopez","Asoma para ir al partido", "14:00 pm",2,"@drawable/perfil_h1")
            )
            arregloChats.add(
                Chat("Daniela Solano","Hola veci", "17:00",1,"@drawable/perfil_m3")
            )
            arregloChats.add(
                Chat("Samantha","Hola :)", "18:00",1,"@drawable/perfil_m1")
            )
            arregloChats.add(
                Chat("Samuel Acosta","Ya leiste el libro que mandó la Inge?", "12:00",2,"@drawable/perfil_h4")
            )
            arregloChats.add(
                Chat("Juan","Asoma", "14:00",1,"@drawable/perfil_h2")
            )
            arregloChats.add(
                Chat("Carlos Lombeida","Como va la familia?", "15:00",3,"@drawable/perfil_h4")
            )
            arregloChats.add(
                Chat("Fernanda","Me acompañas el sábado?", "16:00",9,"@drawable/perfil_m1")
            )
            arregloChats.add(
                Chat("Karol","Aún tienes la foto?", "21:00",3,"@drawable/perfil_m2")
            )
            arregloChats.add(
                Chat("Ibeth","Cuando me vienes a visitar?", "18:00",2,"@drawable/perfil_m3")
            )
            arregloChats.add(
                Chat("Liz","Si vas asomar en el parque?", "16:00",9,"@drawable/perfil_m1")
            )
            arregloChats.add(
                Chat("Nicole","Vamos por una papitas :)", "22:00",3,"@drawable/perfil_m2")
            )
            arregloChats.add(
                Chat("Elizabeth","Que tal tu día ?", "18:00",2,"@drawable/perfil_m3")
            )
            arregloChats.add(
                Chat("Leonela","Osea por mi está bien, no se que pienses?", "18:00",7,"@drawable/perfil_m2")
            )


        }
        init{
            arregloMensajes.add(
                Mensaje("Hola cómo estás","10:12")
            )
            arregloMensajes.add(
                Mensaje(":)","10:12")
            )
            arregloMensajes.add(
                Mensaje("Creo que a esta hora estás en la U","10:12")
            )
            arregloMensajes.add(
                Mensaje("Quería hacerte una invitación","10:13")
            )
            arregloMensajes.add(
                Mensaje("El fin de semana es mi cumple","10:13")
            )
            arregloMensajes.add(
                Mensaje("Por si te olvidaste :|","10:13")
            )
            arregloMensajes.add(
                Mensaje("jaja","10:13")
            )
            arregloMensajes.add(
                Mensaje("Quisiera que estés conmigo en mi fiesta","10:14")
            )
            arregloMensajes.add(
                Mensaje(":)","10:14")
            )
            arregloMensajes.add(
                Mensaje("<3","10:14")
            )
            arregloMensajes.add(
                Mensaje("La fiesta es en mi casa","10:15")
            )
            arregloMensajes.add(
                Mensaje("A las 7 pm","10:15")
            )
            arregloMensajes.add(
                Mensaje("Obviamente puedes venir más pronto si gustas","10:15")
            )
            arregloMensajes.add(
                Mensaje("Me confirmas","10:15")
            )
            arregloMensajes.add(
                Mensaje("Bye","10:16")
            )
            arregloMensajes.add(
                Mensaje("Que tengas un lindo día","10:16")
            )
            arregloMensajes.add(
                Mensaje("Otra cosa que me estaba olvidando, quería invitarle a tu prima, me cayó super bien," +
                        "pero no tengo el número de ella, tal ves me podrías pasar, o tal ves le puedes decir de la fiesta :)","17:59")
            )
            arregloMensajes.add(
                Mensaje("Me avisas porfa","18:00")
            )
        }

    }
}