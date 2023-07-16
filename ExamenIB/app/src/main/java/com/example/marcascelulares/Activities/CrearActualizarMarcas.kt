package com.example.marcascelulares.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.marcascelulares.Modelos.BaseDatos
import com.example.marcascelulares.Modelos.Marca
import com.example.marcascelulares.R

class CrearActualizarMarcas : AppCompatActivity() {
    var modo = ""
    var marca: Marca? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_actualizar_marcas)

        modo = intent.getStringExtra("modo")?:"CREAR"
        val botonGuardar = findViewById<Button>(R.id.btn_guardar_marcas)
        val textoModo =findViewById<TextView>(R.id.tv_modo)

        textoModo.text = modo.toString()

        if(modo == "ACTUALIZAR"){
            var id = intent.getIntExtra("id",-1)
            marca = BaseDatos.buscarMarca(id)
            marca?.let {
                cargarDatosMarca(marca!!)
            }
        }
        botonGuardar.setOnClickListener {
            guardarMarca()
        }

    }

    fun guardarMarca(){
        val id = findViewById<EditText>(R.id.tv_id)
        val nombre = findViewById<EditText>(R.id.tv_nombre_marca)
        val pais = findViewById<EditText>(R.id.tv_pais)
        val anio = findViewById<EditText>(R.id.tv_anio)
        val venta = findViewById<EditText>(R.id.tv_ventas)

        if (
            id.text.isNotEmpty()&&
            nombre.text.isNotEmpty() &&
            pais.text.isNotEmpty() &&
            anio.text.isNotEmpty() &&
            venta.text.isNotEmpty()
        ) {
            if (modo == "CREAR") {
                BaseDatos.crearMarca(
                    id = id.text.toString().toInt(),
                    nombre = nombre.text.toString(),
                    pais = pais.text.toString(),
                    anioCreacion = anio.text.toString().toInt(),
                    ventaMillones = venta.text.toString().toDouble()
                )
                finish()

            } else if (modo == "ACTUALIZAR") {
                var identificador: Int = if (marca?.id != null) marca?.id!! else -1

                BaseDatos.actualizarMarca(
                    id = id.text.toString().toInt(),
                    nombre = nombre.text.toString(),
                    pais = pais.text.toString(),
                    anioCreacion = anio.text.toString().toInt(),
                    ventaMillones = venta.text.toString().toDouble()
                )
                finish()
            }

        }
    }
    fun cargarDatosMarca(marca: Marca) {
        val id = findViewById<EditText>(R.id.tv_id)
        val nombre = findViewById<EditText>(R.id.tv_nombre_marca)
        val pais = findViewById<EditText>(R.id.tv_pais)
        val anio = findViewById<EditText>(R.id.tv_anio)
        val venta = findViewById<EditText>(R.id.tv_ventas)
        id.setText(marca.id.toString())
        nombre.setText(marca.nombre)
        pais.setText(marca.pais)
        anio.setText(marca.anioCreacion.toString())
        venta.setText(marca.ventaMillones.toString())
    }
}