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
import com.example.marcascelulares.Modelos.BaseDatosFirestore
import com.example.marcascelulares.Modelos.Marca
import com.example.marcascelulares.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MarcasActivity : AppCompatActivity() {

    private var marcaSeleccionada = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marcas)

        val btn_CrearMarca = findViewById<Button>(R.id.btn_crear_marca)

        btn_CrearMarca.setOnClickListener {
            irActividad(CrearActualizarMarcas::class.java)
        }

        // Cargar las marcas al iniciar la actividad
        cargarMarcas()


    }

    override fun onStart() {
        super.onStart()
        cargarMarcas()
    }

    private fun cargarMarcas() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val marcasCelulares = BaseDatosFirestore.obtenerTodasLasMarcas()


                val adapter = ArrayAdapter(
                    this@MarcasActivity,
                    android.R.layout.simple_list_item_1,
                    marcasCelulares
                )

                val listaVista = findViewById<ListView>(R.id.lv_marcas)
                listaVista.adapter = adapter
                adapter.notifyDataSetChanged()
                registerForContextMenu(listaVista)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    private fun irActividad(activity: Class<*>, params: Bundle? = null) {
        val intent = Intent(this, activity)
        if (params != null) {
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
        val listaVista = findViewById<ListView>(R.id.lv_marcas)
        marcaSeleccionada = (listaVista.adapter.getItem(posicion) as Marca).nombre

        println("marca seleccionada" + marcaSeleccionada)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.mi_modelos-> {
                val params = Bundle()
                val marca = marcaSeleccionada
                params.putString("marca", marca)
                irActividad(CelularesActivity::class.java, params)
                return true
            }
            R.id.mi_editar -> {
                val params = Bundle()
                val marca = marcaSeleccionada
                params.putString("marca", marca)
                params.putString("modo","ACTUALIZAR")

                irActividad(CrearActualizarMarcas::class.java, params)
                return true
            }
            R.id.mi_eliminar -> {
                showConfirmDeleteDialog(marcaSeleccionada)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

        private fun showConfirmDeleteDialog(marcaSeleccionada: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Eliminar Marca")
        //${marca.nombre.toString()}
        builder.setMessage("¿Estás seguro de eliminar ?")
        builder.setPositiveButton("Si") { _, _ ->
            BaseDatosFirestore.eliminarMarca(marcaSeleccionada)
            cargarMarcas()
        }
        builder.setNegativeButton("No", null)
        builder.show()
    }
}

