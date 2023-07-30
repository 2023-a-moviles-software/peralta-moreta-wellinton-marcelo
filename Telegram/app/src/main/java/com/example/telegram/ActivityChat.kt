package com.example.telegram

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.telegram.entidad.BaseDatosChats

class ActivityChat : AppCompatActivity() {

    private val arregloDeMensajes = BaseDatosChats.arregloMensajes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_vista)

        inicializarRecyclerView()

    }
    private fun inicializarRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rv_mensajes)

        val adaptador = Message_Rview(this, arregloDeMensajes, recyclerView)

        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}

