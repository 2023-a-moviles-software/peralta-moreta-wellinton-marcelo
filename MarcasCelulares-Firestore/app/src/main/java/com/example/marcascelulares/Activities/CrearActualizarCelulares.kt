package com.example.marcascelulares.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import com.example.marcascelulares.Modelos.BaseDatosFirestore
import com.example.marcascelulares.Modelos.Celular
import com.example.marcascelulares.Modelos.Marca
import com.example.marcascelulares.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CrearActualizarCelulares : AppCompatActivity() {

    var modo = ""
    var marcaCelular = ""
    var modeloCelular=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_actualizar_celulares)

        marcaCelular = intent.getStringExtra("marcaCelular").toString()
        modeloCelular = intent.getStringExtra("modeloCelular").toString()
        println("PRINCIPAL"+marcaCelular)
        println("PRINCIPAL 2"+modeloCelular)
        modo = intent.getStringExtra("modo")?:"CREAR"
        val botonGuardar = findViewById<Button>(R.id.btn_guardar_celular)
        val modoTexto =findViewById<TextView>(R.id.tv_modo_celular)

        modoTexto.text = modo

        if(modo == "ACTUALIZAR"){
            GlobalScope.launch(Dispatchers.Main) {
                try {
                    println(" SE COMINEZA A ACTUALIZAR")
                    var celular = BaseDatosFirestore.obtenerCelularporNombre(marcaCelular, modeloCelular)
                    celular?.let {
                        println("hay un celular?")
                        cargarDatosCelular(celular)
                    }

                }catch(e: Exception) {
                    e.printStackTrace()
                }
            }

        }
        botonGuardar.setOnClickListener {
            guardarCelular()
        }
    }

    fun guardarCelular(){
        val id = findViewById<EditText>(R.id.tv_id_celular)
        val modelo = findViewById<EditText>(R.id.tv_modelo_celular)
        val almacenamiento = findViewById<EditText>(R.id.tv_almacenamiento)
        val es5g = findViewById<Switch>(R.id.sw_5g)
        val precio = findViewById<EditText>(R.id.tv_precio)
        val fechaLanzamiento = findViewById<EditText>(R.id.tv_fecha)

        if (
            id.text.isNotEmpty()&&
            modelo.text.isNotEmpty() &&
            almacenamiento.text.isNotEmpty() &&
            precio.text.isNotEmpty()&&
            fechaLanzamiento.text.isNotEmpty()
        ) {
            if(modo == "CREAR"){
                println("Comienza proceso de creacion con" +marcaCelular)
                BaseDatosFirestore.crearCelular(
                    marcaCelular,
                    Celular(
                    id.text.toString().toInt(),
                    modelo.text.toString(),
                    almacenamiento.text.toString().toInt(),
                    es5g.isChecked,
                    precio.text.toString().toDouble(),
                    fechaLanzamiento.text.toString()
                    )
                )
                finish()

            }else if (modo == "ACTUALIZAR") {
                //var identificador: Int = if (marca?.id != null) marca?.id!! else -1

                BaseDatosFirestore.actualizarCelular(
                    marcaCelular,
                    modeloCelular,
                    Celular(
                        id.text.toString().toInt(),
                        modelo.text.toString(),
                        almacenamiento.text.toString().toInt(),
                        es5g.isChecked,
                        precio.text.toString().toDouble(),
                        fechaLanzamiento.text.toString()
                    )
                )
                finish()
            }

        }
    }
    fun cargarDatosCelular(celular: Celular) {
        val idCel = findViewById<EditText>(R.id.tv_id_celular)
        val modelo = findViewById<EditText>(R.id.tv_modelo_celular)
        val almacenamiento = findViewById<EditText>(R.id.tv_almacenamiento)
        val es5g = findViewById<Switch>(R.id.sw_5g)
        val precio = findViewById<EditText>(R.id.tv_precio)
        val fecha = findViewById<EditText>(R.id.tv_fecha)
        println("Supuestamente se deben pasar los datos de")
        println(celular)

        idCel.setText(celular.id.toString())
        modelo.setText(celular.modelo)
        almacenamiento.setText(celular.almacenamiento.toString())
        es5g.isChecked = celular.es5g
        precio.setText(celular.precio.toString())
        fecha.setText(celular.fechaLanzamiento)

    }
}


