package com.example.plamodel_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton

class lista_maquetas : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_maquetas)

        val btn_add = findViewById<FloatingActionButton>(R.id.btn_agregarmaqueta)
        val btn_del_1 = findViewById<Button>(R.id.btn_del_1)
        val btn_ed_1 = findViewById<Button>(R.id.btn_ed_1)

        //Accionar Eliminar maqueta
        btn_del_1.setOnClickListener{
            val intent = Intent(this@lista_maquetas, eliminar_maqueta::class.java )
            startActivity(intent)
        }


        //Accionar editar maqueta
        btn_ed_1.setOnClickListener{
            val intent = Intent(this@lista_maquetas, editar_maqueta::class.java )
            startActivity(intent)
        }

        //Accionar Sumar maqueta
        btn_add.setOnClickListener{
                val intent = Intent(this@lista_maquetas, agregar_maqueta::class.java )
                startActivity(intent)
            }
        }


    }
