package com.example.telegram

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.telegram.entidad.Chat

class Chat_Rview(
    private val contexto: MainActivity,
    private val chats: ArrayList<Chat>,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<Chat_Rview.MyViewHolder>() {
    interface OnChatItemClickListener {
        fun onChatItemClick(chat: Chat)
    }

    private var chatItemClickListener: OnChatItemClickListener? = null

    fun setOnChatItemClickListener(listener: OnChatItemClickListener) {
        chatItemClickListener = listener
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreTextView: TextView = view.findViewById(R.id.tv_nombre)
        val mensajeTextView: TextView = view.findViewById(R.id.tv_mensaje)
        val horaTextView: TextView = view.findViewById(R.id.tv_hora)
        val nMensajesTextView: TextView = view.findViewById(R.id.tv_nMensaje)
        val srcImagen: ImageView = view.findViewById(R.id.imgContacto)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val chat = chats[position]
                    chatItemClickListener?.onChatItemClick(chat)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recicler_listachats_vista, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return chats.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val chatActual = chats[position]
        holder.nombreTextView.text = chatActual.nombre
        holder.mensajeTextView.text = chatActual.mensaje
        holder.horaTextView.text = chatActual.hora
        holder.nMensajesTextView.text = chatActual.nMensajes.toString()
        val IDimgperfil = contexto.resources.getIdentifier(chatActual.src, "drawable", contexto.packageName)
        holder.srcImagen.setImageResource(IDimgperfil)
    }
}
