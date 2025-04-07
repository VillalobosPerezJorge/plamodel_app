package com.example.plamodel_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputLayout

class editar_maqueta : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_maqueta)

        val btn_aceptar = findViewById<Button>(R.id.btn_aceptar)

        //Nombre maqueta
        val str_k_maqueta = intent.getStringExtra("key_maqueta")
        val til_edit_nmaqueta = findViewById<TextInputLayout>(R.id.til_edit_nmaqueta)

        til_edit_nmaqueta.hint = str_k_maqueta

        //Accionar Aceptar
        btn_aceptar.setOnClickListener{
            confirmationEdit()
        }

    }

    private fun confirmationEdit() {
        val builder = AlertDialog.Builder(this)

        //Título y el mensaje del AlertDialog
        builder.setTitle("¿Quieres aceptar los cambios")
        builder.setMessage("Puedes volver más tarde a editar la maqueta")

        // Botón positivo
        builder.setPositiveButton("Aceptar cambios") { dialog, which ->
            Toast.makeText(this, "Se ha editado la maqueta", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@editar_maqueta, dashboard_maquetas::class.java )
            startActivity(intent)
        }

        // Botón negativo
        builder.setNegativeButton("Aún me faltan cambios") { dialog, which ->
            dialog.dismiss()
        }


        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }
}