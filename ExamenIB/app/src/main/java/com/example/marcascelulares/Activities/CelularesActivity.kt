package com.example.marcascelulares.Activities

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
import androidx.appcompat.app.AlertDialog
import com.example.marcascelulares.Modelos.BaseDatos
import com.example.marcascelulares.Modelos.Celular
import com.example.marcascelulares.Modelos.Marca
import com.example.marcascelulares.R

class CelularesActivity : AppCompatActivity() {

    val marcasCelulares = BaseDatos.arrayMarcas
    var seletedMarcaId = 0
    var itemSeleccionado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_celulares)

        seletedMarcaId = intent.getIntExtra("id",-1)
        cargarCelulares(seletedMarcaId)

        val crearCelularesButton = findViewById<Button>(
            R.id.btn_crear_celular
        )

        crearCelularesButton.setOnClickListener {
            irActividad(CrearActualizarCelulares::class.java, Bundle().apply {
                val marca = marcasCelulares.find {
                    it.id == seletedMarcaId
                }
                if (marca != null) {
                    putInt("idMarca", marca.id)
                    putString("marcaNombre", marca.nombre.toString())
                }

            })
        }

    }
    override fun onStart() {
        super.onStart()

        cargarCelulares(seletedMarcaId)
    }

    private fun irActividad(activity: Class<*>, params: Bundle? = null) {
        val intent = Intent(this, activity)
        params?.let{
            intent.putExtras(it)
        }
        startActivity(intent)
    }

    private fun cargarCelulares(marcaId: Int) {
        if (marcaId != null) {
            val marca = marcasCelulares.find {
                it.id == marcaId
            }

            if (marca != null) {
                val celulares = marca.celulares

                val tv_tittle= findViewById<TextView>(R.id.tt_title_celular_marca)
                tv_tittle.text = marca.nombre

                val celularesList = findViewById<ListView>(R.id.lv_celulares)

                val adapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    celulares
                )

                celularesList.adapter = adapter
                adapter.notifyDataSetChanged()
                registerForContextMenu(celularesList)
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

        itemSeleccionado = posicion
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.mi_editar -> {
                val params = Bundle()
                params.putInt("idMarca", seletedMarcaId)
                params.putInt("idCelular", itemSeleccionado)
                params.putString("modo","ACTUALIZAR")
                irActividad(CrearActualizarCelulares::class.java, params)
                return true
            }
            R.id.mi_eliminar -> {
                val celular = marcasCelulares[seletedMarcaId-1].celulares[itemSeleccionado]
                showConfirmDeleteDialog(celular)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun showConfirmDeleteDialog(celular: Celular) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Eliminar Celular")
        builder.setMessage("¿Estás seguro de eliminar: ${celular.modelo.toString()}?")
        builder.setPositiveButton("Si") { _, _ ->
            marcasCelulares[seletedMarcaId-1].celulares.remove(celular)
            cargarCelulares(seletedMarcaId)
        }
        builder.setNegativeButton("No", null)
        builder.show()
    }

}