package com.example.plamodel_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

class pass_olvidada : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pass_olvidada)

        val btn_recuperar = findViewById<TextView>(R.id.btn_recuperar)



        //Accionar Recuperar
        btn_recuperar.setOnClickListener{
            if (validarCampos()==0){
                val intent = Intent(this@pass_olvidada, MainActivity::class.java )
                startActivity(intent)
            } }




    }

    //Funciones de validación
    fun validarCampos(): Int {
     val til_recuperar_correo = findViewById<TextInputLayout>(R.id.til_recuperar_correo)
     var str_recuperar_correo = til_recuperar_correo.editText?.text.toString()

        var contadorErrores: Int = 0
        val validate = Validator()


        //Validación de correo vacío
            if(validate.validarCampoNulo(str_recuperar_correo)){
                til_recuperar_correo.error = getString(R.string.error_campo_vacio)
            contadorErrores++
            //Validación de formato correo
        }else if(validate.validarFormatoCorreo(str_recuperar_correo)) {
                til_recuperar_correo.error = getString(R.string.error_formato_correo)
                contadorErrores++
            }else {
                    til_recuperar_correo.error = ""
                Toast.makeText(getApplicationContext(),"Revisa tu correo electrónico", Toast.LENGTH_SHORT).show();
                }
                return contadorErrores
    }



}
