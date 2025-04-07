package com.example.plamodel_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

class dashboard_maquetas : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_maquetas)

        //Referencias a elementos
        val txt_bienvenida = findViewById<TextView>(R.id.txt_bienvenida)
        val spn_categoria = findViewById<Spinner>(R.id.spn_categoria)
        val lv_datos = findViewById<ListView>(R.id.lv_datos)
        val flt_agregar = findViewById<FloatingActionButton>(R.id.flt_agregar)


        //Valor del intent
        val mail:String = intent.getStringExtra("mail").toString()
        txt_bienvenida.setText("Bienvenido ${mail}")

        //Generación de Spinner
        val arrayAdapterSpinner: ArrayAdapter<*>
        val marcas = arrayOf("Seleccionar marca", "Bandai", "Kotobukiya", "Tamiya")
        arrayAdapterSpinner = ArrayAdapter(this, android.R.layout.simple_list_item_1,marcas)
        spn_categoria.adapter= arrayAdapterSpinner

        //Floatactionbutton agregar maqueta
        flt_agregar.setOnClickListener{
            val intent = Intent(this@dashboard_maquetas, agregar_maqueta::class.java )
            startActivity(intent)
        }


        //Generación de Listview
        //Adaptador que siempre acepta un array o una lista como entrada
        val arrayAdapter: ArrayAdapter<*>
        val maquetas = arrayOf("Blade Liger AB",
            "Blue Eyes White Dragon Figure Rise",
            "Vegeta New Spec",
            "Gundam Aerial Full Mechanics",
            "Angemon Figure Rise",
            "ZOZOTOWN Madoka Yuki Touou HS",
            "Magikarp Pokepla",
            "Dibison Marking Plus Ver."
        )
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,maquetas)
        lv_datos.adapter= arrayAdapter

        lv_datos.onItemClickListener = object : AdapterView.OnItemClickListener{
            override fun onItemClick(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {

                if (position >= 0 && position < maquetas.size && spn_categoria.selectedItem != "Seleccionar marca" ) {
                    val maquetaSeleccionada = maquetas[position]
                    Toast.makeText(this@dashboard_maquetas, "Seleccionaste $maquetaSeleccionada", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@dashboard_maquetas, detalle_maqueta::class.java )
                    intent.putExtra("nomMaq", maquetaSeleccionada)
                    startActivity(intent)
                } else if (spn_categoria.selectedItem == "Seleccionar marca"){
                    Toast.makeText(this@dashboard_maquetas, "¡Debes seleccionar una marca!", Toast.LENGTH_SHORT).show()
                }
            }

        }

    }
}