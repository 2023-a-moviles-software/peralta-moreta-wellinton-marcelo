package com.example.marcascelulares.Activities

import com.example.marcascelulares.Modelos.BaseDatosFirestore
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import com.example.marcascelulares.Modelos.Celular
import com.example.marcascelulares.Modelos.Marca
import com.example.marcascelulares.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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
            var marca = intent.getStringExtra("marca")
            cargarMarca(marca.toString())
        }
        botonGuardar.setOnClickListener {
            guardarMarca()
        }

    }
    private fun cargarMarca(id: String) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                marca = BaseDatosFirestore.obtenerMarcaPorNombre(id)
                marca?.let {
                    cargarDatosMarca(marca!!)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
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
                BaseDatosFirestore.crearMarca( Marca(
                    id = id.text.toString().toInt(),
                    nombre = nombre.text.toString(),
                    pais = pais.text.toString(),
                    anioCreacion = anio.text.toString().toInt(),
                    ventaMillones = venta.text.toString().toDouble(),
                    celulares = emptyList()
                    )
                )
                finish()

            } else if (modo == "ACTUALIZAR") {
                BaseDatosFirestore.actualizarMarca(
                    Marca(
                    id = id.text.toString().toInt(),
                    nombre = nombre.text.toString(),
                    pais = pais.text.toString(),
                    anioCreacion = anio.text.toString().toInt(),
                    ventaMillones = venta.text.toString().toDouble(),
                    celulares = emptyList()
                    )
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