package com.example.telegram

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.telegram.entidad.Mensaje

class Message_Rview (
    private val contexto: ActivityChat,
    private val mensajes: ArrayList<Mensaje>,
    private val recyclerView: RecyclerView
    ): RecyclerView.Adapter<Message_Rview.MyViewHolder>() {

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val contenido: TextView
        val hora: TextView

        init {
            contenido = view.findViewById(R.id.tv_contenido)
            hora = view.findViewById(R.id.tv_hora_mensaje)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_message_rview, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val mensajeActual = mensajes[position]
        holder.contenido.text = mensajeActual.contenido
        holder.hora.text = mensajeActual.hora
    }

    override fun getItemCount(): Int {
        return mensajes.size
    }


}