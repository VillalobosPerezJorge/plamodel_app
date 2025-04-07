package com.example.plamodel_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import org.w3c.dom.Text

class Registro_usuario : AppCompatActivity() {
    var fecha_anio = 0
    var fecha_mes = 0
    var fecha_dia = 0
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_usuario)
        val btn_register = findViewById<Button>(R.id.btn_register)
        val edt_date = findViewById<EditText>(R.id.edt_date)



        //Accionar Registrar
        btn_register.setOnClickListener{
            if (validarCampos()==0){
                val intent = Intent(this@Registro_usuario, lista_maquetas::class.java )
                startActivity(intent)
            }
        }


        edt_date.setOnClickListener { showDatePickerDialog() }
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    //FECHA
    @SuppressLint("WrongViewCast")
    private fun onDateSelected(day: Int, month: Int, year: Int) {
        val edt_date = findViewById<EditText>(R.id.edt_date)
        edt_date.setText("Fecha de nacimiento: $day-$month-$year")
        fecha_anio = year
        fecha_mes = month
        fecha_dia = day
    }



    //Funciones de validación
    fun validarCampos(): Int {

        var contadorErrores: Int = 0
        val validate = Validator()
        val nomApe = findViewById<TextInputLayout>(R.id.nom_ape)
        val til_email = findViewById<TextInputLayout>(R.id.email_reg)
        val til_email_conf = findViewById<TextInputLayout>(R.id.email_confirm)
        val password = findViewById<TextInputLayout>(R.id.password_reg)
        val password_confirm = findViewById<TextInputLayout>(R.id.password_confirm)
        var str_pass_confirm = password_confirm.editText?.text.toString()
        var str_password = password.editText?.text.toString()
        var nombre = nomApe.editText?.text.toString()
        val username = findViewById<TextInputLayout>(R.id.til_username_reg)
        var username_reg = username.editText?.text.toString()
        var email = til_email.editText?.text.toString()
        var email_conf = til_email_conf.editText?.text.toString()
        val edt_date = findViewById<EditText>(R.id.edt_date)
        val spinner_gender = findViewById<Spinner>(R.id.sp_gender)
        val spn_selected_gender = spinner_gender.selectedItem.toString()
        val txt_error_message = findViewById<TextView>(R.id.gender_select_error)
        val error_terminos = findViewById<TextView>(R.id.terms_error_txt)
        val checkBox = findViewById<CheckBox>(R.id.chkbx_terms)

        val isChecked = checkBox.isChecked
        val checkboxChecked = isChecked






        // Validación nombre y apellido
        if (validate.validarCampoNulo(nombre)) {
            nomApe.error = getString(R.string.error_campo_vacio)
            contadorErrores++
        } else if (validate.validarNombreCompleto(nombre)) {
            nomApe.error = getString(R.string.error_formato_nombre)
            contadorErrores++
        } else if (validate.noContieneNumeros(nombre)) {
            nomApe.error = getString(R.string.error_num_nombre)
            contadorErrores++
            //Validar nombre de usuario
        }else if(validate.validarCampoNulo(username_reg)){
            username.error = getString(R.string.error_campo_vacio)
            contadorErrores++
        } else if (validate.validarNombreDeUsuario(username_reg)){
            username.error = getString(R.string.error_formato_username)
            contadorErrores++
        } else if (validate.validarLongitudNombreDeUsuario(username_reg)) {
            username.error = getString(R.string.error_long_username)
            contadorErrores++
        }//Validación de correo vacío
        else if(validate.validarCampoNulo(email)){
            til_email.error = getString(R.string.error_campo_vacio)
            contadorErrores++
            //Validación de formato correo
        }else if(validate.validarFormatoCorreo(email)){
                til_email.error = getString(R.string.error_formato_correo)
                contadorErrores++
            //Validar si el correo coincide
        }else if(validate.validarCamposIguales(email, email_conf )) {
            til_email.error = getString(R.string.error_coincidencia_correo)
            contadorErrores++
            //Validación de contraseña vacía
        }else if (validate.validarCampoNulo(str_password)){
                password.error = getString(R.string.error_campo_vacio)
                contadorErrores++
            //Validar formato de la contraseña
        } else if(validate.validarFormatoPassword(str_password)){
                    password.error = getString(R.string.error_formato_pass)
                    contadorErrores++
            //Validar si contraseña coincide
        }else if(validate.validarCamposIguales(str_password, str_pass_confirm )) {
            til_email.error = getString(R.string.error_coincidencia_correo)
            contadorErrores++
        }else if (validate.esFechaFutura(fecha_anio, fecha_mes, fecha_dia)) {
            edt_date.error = getString(R.string.error_fecha_futura)
            contadorErrores++
            //Validar opción Spinner
        }else if (validate.validarOpcionSeleccionada(spn_selected_gender)) {
            txt_error_message.text = getString(R.string.error_select_spinner)
            txt_error_message.visibility = View.VISIBLE
            contadorErrores++
            //Validar checkbox términos
        }else if (validate.validarCheckBoxSeleccionado(checkboxChecked)){
            checkBox.text = getString(R.string.error_terminos)
            error_terminos.visibility = View.VISIBLE
            contadorErrores++
        //Mover al final
        } else {
            txt_error_message.visibility = View.GONE
            error_terminos.visibility = View.GONE
            nomApe.error = ""
        }
        return contadorErrores

    }





}



