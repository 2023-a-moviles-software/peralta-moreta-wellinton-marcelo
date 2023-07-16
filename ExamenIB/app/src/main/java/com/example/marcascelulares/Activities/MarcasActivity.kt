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
import androidx.appcompat.app.AlertDialog
import com.example.marcascelulares.Modelos.BaseDatos
import com.example.marcascelulares.Modelos.Marca
import com.example.marcascelulares.R

class MarcasActivity : AppCompatActivity() {

    val marcasCelulares = BaseDatos.arrayMarcas
    var itemSeleccionado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marcas)

        cargarMarcas()

        val btn_CrearMarca = findViewById<Button>(R.id.btn_crear_marca)

        btn_CrearMarca.setOnClickListener{
            irActividad(CrearActualizarMarcas::class.java)
        }
    }

    override fun onStart() {
        super.onStart()
        cargarMarcas()
    }

    private fun cargarMarcas(){
        val listaVista =findViewById<ListView>( R.id.lv_marcas)

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            marcasCelulares
        )

        listaVista.adapter = adapter
        adapter.notifyDataSetChanged()
        registerForContextMenu(listaVista)
    }

    private fun irActividad(activity: Class<*>, params: Bundle?=null){
        val intent = Intent(this, activity)
        if(params != null){
            intent.putExtras(params)
        }
        startActivity(intent)
    }
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_marcas, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        itemSeleccionado = posicion
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.mi_modelos-> {
                val params = Bundle()
                val marca = marcasCelulares[itemSeleccionado]
                params.putInt("id", marca.id.toInt())
                irActividad(CelularesActivity::class.java, params)
                return true
            }
            R.id.mi_editar -> {
                val params = Bundle()
                val marca = marcasCelulares[itemSeleccionado]
                params.putInt("id", marca.id.toInt())
                params.putString("modo","ACTUALIZAR")

                irActividad(CrearActualizarMarcas::class.java, params)
                return true
            }
            R.id.mi_eliminar -> {
                val marca = marcasCelulares[itemSeleccionado]
                showConfirmDeleteDialog(marca)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun showConfirmDeleteDialog(marca: Marca) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Eliminar Marca")
        builder.setMessage("¿Estás seguro de eliminar: ${marca.nombre.toString()}?")
        builder.setPositiveButton("Si") { _, _ ->
            marcasCelulares.removeAt(itemSeleccionado)
            cargarMarcas()
        }
        builder.setNegativeButton("No", null)
        builder.show()
    }
}