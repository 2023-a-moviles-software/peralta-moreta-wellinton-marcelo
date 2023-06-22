package com.example.movilessoftware2023a

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    val callbackContenidoIntentExplicito =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            result ->
            if(result.resultCode == Activity.RESULT_OK){
                if(result.data != null){
                    //logica de negocio
                    val data = result.data
                    "${data?.getStringExtra("nombreModificado")}"

                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val botonACicloVida = findViewById<Button>(
            R.id.btn_ciclo_vida
        )
        botonACicloVida.setOnClickListener{
            irActividad(AACicloVida::class.java)
        }
        val botonListView = findViewById<Button>(
            R.id.btn_ir_list_view
        )
        botonListView.setOnClickListener{
            irActividad(BListView::class.java)
        }
    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        //No recibe respuesta
        startActivity(intent)
        //this.startActivity()
    }

    fun abrirActividadConParametros(
        clase: Class<*>
    ){
        val intentExplicito = Intent(this, clase)
        // Enviar parametros
        // (aceptamos primitivas)
        intentExplicito.putExtra("nombre", "Wellinton")
        intentExplicito.putExtra("apellido", "Peralta")
        intentExplicito.putExtra("edad", 22)
        //Enviamos el intennt con respuesta
        //Recibimos Respuesta
        callbackContenidoIntentExplicito.launch(intentExplicito)
    }
}






