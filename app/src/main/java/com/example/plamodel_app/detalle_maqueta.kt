package com.example.plamodel_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class detalle_maqueta : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_maqueta)



        //Título maqueta
        var til_nombre_maqueta_detalle = findViewById<TextView>(R.id.til_nom_maqueta_detalle)
        val maquetaSeleccionada = intent.getStringExtra("nomMaq").toString()
        til_nombre_maqueta_detalle.text = maquetaSeleccionada


        //Botones eliminar y editar
        val flt_eliminar = findViewById<FloatingActionButton>(R.id.flt_eliminar)
        val flt_editar = findViewById<FloatingActionButton>(R.id.flt_editar)

        //Accionar Eliminar maqueta
        flt_eliminar.setOnClickListener{
            confirmationDelete()
        }

        //Accionar Editar maqueta
        flt_editar.setOnClickListener{
            val intent = Intent(this@detalle_maqueta, editar_maqueta::class.java )
            intent.putExtra("key_maqueta", maquetaSeleccionada)
            startActivity(intent)
        }

    }



    private fun confirmationDelete() {
        val builder = AlertDialog.Builder(this)
        val maquetaSeleccionada = intent.getStringExtra("nomMaq").toString()
        //Título y el mensaje del AlertDialog
        builder.setTitle("¿Quieres eliminar la maqueta $maquetaSeleccionada?")
        builder.setMessage("Eliminarás definitivamente esta maqueta de tu cuenta")

        // Botón positivo
        builder.setPositiveButton("Eliminar") { dialog, which ->
            Toast.makeText(this, "Se ha eliminado la maqueta $maquetaSeleccionada", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@detalle_maqueta, dashboard_maquetas::class.java )
            startActivity(intent)
        }

        // Botón negativo
        builder.setNegativeButton("Cancelar") { dialog, which ->
            dialog.dismiss()
        }


        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }
    }
