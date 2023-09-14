package com.example.marcascelulares.Activities

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.example.marcascelulares.Modelos.BaseDatosFirestore
import com.example.marcascelulares.Modelos.Celular
import com.example.marcascelulares.Modelos.Marca
import com.example.marcascelulares.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CelularesActivity : AppCompatActivity() {

    var marca: Marca? = null
    var seletedMarca = ""
    var modeloSeleccionado = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_celulares)

        seletedMarca = intent.getStringExtra("marca").toString()

        cargarCelulares()

        val crearCelularesButton = findViewById<Button>(R.id.btn_crear_celular)

        crearCelularesButton.setOnClickListener {
            val params = Bundle()
            params.putString("marcaCelular", seletedMarca)
            irActividad(CrearActualizarCelulares::class.java, params)

        }

    }


    override fun onStart() {
        super.onStart()

        cargarCelulares()
    }
    private fun obtenerMarca(seletedMarca:String) : Marca?{
        GlobalScope.launch(Dispatchers.Main) {
            marca = BaseDatosFirestore.obtenerMarcaPorNombre(seletedMarca)
         }
        println(marca)
        println("obtenere marca 1 "+marca?.nombre)
        return marca
    }

    private fun irActividad(activity: Class<*>, params: Bundle? = null) {
        val intent = Intent(this, activity)
        if (params != null) {
            intent.putExtras(params)
        }
        startActivity(intent)
    }


    private fun cargarCelulares() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                println("comienza cargar cell con"+seletedMarca)

                val celulares = BaseDatosFirestore.obtenerTodosLosCelulares(seletedMarca)
                println("Aqui van loss celulares de "+seletedMarca)
                println(celulares)
                val tvTitle = findViewById<TextView>(R.id.tt_title_celular_marca)
                tvTitle.text = seletedMarca

                val celularesList = findViewById<ListView>(R.id.lv_celulares)

                val adapter = ArrayAdapter(
                    this@CelularesActivity,
                    android.R.layout.simple_list_item_1,
                    celulares
                )

                celularesList.adapter = adapter
                adapter.notifyDataSetChanged()
                registerForContextMenu(celularesList)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_celulares, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position

        val listaVista = findViewById<ListView>(R.id.lv_celulares)
        modeloSeleccionado = (listaVista.adapter.getItem(posicion) as Celular).modelo

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.mi_editar -> {
                val params = Bundle()
                params.putString("marcaCelular", seletedMarca)
                params.putString("modeloCelular", modeloSeleccionado)
                params.putString("modo","ACTUALIZAR")
                irActividad(CrearActualizarCelulares::class.java, params)
                return true
            }

            R.id.mi_eliminar -> {
                showConfirmDeleteDialog(modeloSeleccionado)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
        return true
    }

    private fun showConfirmDeleteDialog(modeloSeleccionado: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Eliminar Celular")
        builder.setMessage("¿Estás seguro de eliminar: ${modeloSeleccionado} ?")
        builder.setPositiveButton("Si") { _, _ ->
            BaseDatosFirestore.eliminarCelular(seletedMarca, modeloSeleccionado.toString())
            cargarCelulares()
        }
        builder.setNegativeButton("No", null)
        builder.show()
    }

}