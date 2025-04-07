package com.example.plamodel_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "WrongViewCast", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Hacer referencia a los widgets o UI Controls

        val til_password = findViewById<TextInputLayout>(R.id.til_password)
        val btn_login = findViewById<Button>(R.id.btn_login)
        val btn_register = findViewById<TextView>(R.id.txtv_signup)

        val btn_forgot_pass = findViewById<TextView>(R.id.btn_forgot_pass)




        //Accionar Login
        btn_login.setOnClickListener{
        if (validarCampos()==0){
            val til_user_email = findViewById<TextInputLayout>(R.id.til_user_email)
            var email = til_user_email.editText?.text.toString()
            val intent = Intent(this@MainActivity, dashboard_maquetas::class.java )
            intent.putExtra("mail", email)
            startActivity(intent)
             }


        }
        //Accionar Registro
        btn_register.setOnClickListener {

                val intent = Intent(this@MainActivity, Registro_usuario::class.java)
                startActivity(intent)

        }

        //Accionar Contraseña olvidada
        btn_forgot_pass.setOnClickListener{
            val intent = Intent(this@MainActivity, pass_olvidada::class.java )
            startActivity(intent)
        }



    }

    //Funciones de validación
    fun validarCampos():Int{
        var contador:Int = 0
        val validate = Validator()
        val til_user_email = findViewById<TextInputLayout>(R.id.til_user_email)
        val til_password = findViewById<TextInputLayout>(R.id.til_password)
        var email = til_user_email.editText?.text.toString()
        var password = til_password.editText?.text.toString()


        //Creación de validaciones
        //Validación de correo vacío
        if(validate.validarCampoNulo(email)){
            til_user_email.error = getString(R.string.error_campo_vacio)
            contador++

        }else{
            //Validación de formato correo
            if(validate.validarFormatoCorreo(email)){
                til_user_email.error = getString(R.string.error_formato_correo)
                contador++
            }else{
                til_user_email.error = ""
            }

            //Validación de contraseña
        if(validate.validarCampoNulo(password)){
            til_password.error = getString(R.string.error_campo_vacio)
            contador++
        }
        else{
            til_password.error = ""
        }
    }
        return contador}}
