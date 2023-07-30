package com.example.telegram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.telegram.entidad.BaseDatosChats
import com.example.telegram.entidad.Chat

class MainActivity : AppCompatActivity(), Chat_Rview.OnChatItemClickListener {
    private val arregloDeChats = BaseDatosChats.arregloChats

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inicializarRecyclerView()
    }

    private fun inicializarRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rv_chats)

        val adaptador = Chat_Rview(this, arregloDeChats, recyclerView)
        adaptador.setOnChatItemClickListener(this)

        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onChatItemClick(chat: Chat) {
        val intent = Intent(this, ActivityChat::class.java)
        startActivity(intent)
    }
}