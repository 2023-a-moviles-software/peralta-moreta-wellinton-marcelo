package com.example.marcascelulares.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.marcascelulares.Modelos.BaseDatos
import com.example.marcascelulares.Modelos.Celular
import com.example.marcascelulares.Modelos.Marca
import com.example.marcascelulares.R

class CrearActualizarCelulares : AppCompatActivity() {

    var modo = ""
    var idMarca = 0
    var idCelular = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_actualizar_celulares)

        idMarca = intent.getIntExtra("idMarca",-1)
        modo = intent.getStringExtra("modo")?:"CREAR"
        val botonGuardar = findViewById<Button>(R.id.btn_guardar_celular)
        val modoTexto =findViewById<TextView>(R.id.tv_modo_celular.toInt())

        modoTexto.text = modo

        if(modo == "ACTUALIZAR"){
            idCelular = intent.getIntExtra("idCelular",-1)
            var marca = BaseDatos.buscarMarca(idMarca)
            marca?.let {
                cargarDatosCelular(marca!!,idCelular)
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
                BaseDatos.crearCelular(
                    idMarca = idMarca,
                    idCelular = id.text.toString().toInt(),
                    modelo = modelo.text.toString(),
                    almacenamiento = almacenamiento.text.toString().toInt(),
                    es5g = es5g.isChecked,
                    precio = precio.text.toString().toDouble(),
                    fechaLanzamiento = fechaLanzamiento.text.toString()
                )
                finish()

            }else if (modo == "ACTUALIZAR") {
                //var identificador: Int = if (marca?.id != null) marca?.id!! else -1

                BaseDatos.actualizarCelular(
                    idMarca = idMarca,
                    idCelular = id.text.toString().toInt(),
                    modelo = modelo.text.toString(),
                    almacenamiento = almacenamiento.text.toString().toInt(),
                    es5g = es5g.isChecked,
                    precio = precio.text.toString().toDouble(),
                    fechaLanzamiento = fechaLanzamiento.text.toString()
                )
                finish()
            }

        }
    }
    fun cargarDatosCelular(marca: Marca, idCelular:Int) {
        val idCel = findViewById<EditText>(R.id.tv_id_celular)
        val modelo = findViewById<EditText>(R.id.tv_modelo_celular)
        val almacenamiento = findViewById<EditText>(R.id.tv_almacenamiento)
        val es5g = findViewById<Switch>(R.id.sw_5g)
        val precio = findViewById<EditText>(R.id.tv_precio)
        val fecha = findViewById<EditText>(R.id.tv_fecha)
        idCel.setText(marca.celulares[idCelular].idCelular.toString())
        modelo.setText(marca.celulares[idCelular].modelo)
        almacenamiento.setText(marca.celulares[idCelular].almacenamiento.toString())
        es5g.isChecked = marca.celulares[idCelular].es5g
        precio.setText(marca.celulares[idCelular].precio.toString())
        fecha.setText(marca.celulares[idCelular].fechaLanzamiento)
    }
}