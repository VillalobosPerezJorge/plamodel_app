package com.example.plamodel_app

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Telephony.Mms.Rate
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputLayout
import android.Manifest
import android.annotation.SuppressLint
import com.google.android.material.floatingactionbutton.FloatingActionButton

class agregar_maqueta : AppCompatActivity() {
    companion object {
        const val REQUEST_IMAGE_CAPTURE = 1
        private val CAMERA_PERMISSION_REQUEST_CODE = 2
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_maqueta)

        val btn_takephoto = findViewById<Button>(R.id.btn_takephoto)
        val btn_agregar_maq = findViewById<Button>(R.id.btn_add)
        val flt_tomarfoto = findViewById<FloatingActionButton>(R.id.flt_tomarfoto)

        //Accionar Agregar maqueta
        btn_agregar_maq.setOnClickListener{
            if (validarCampos()==0){
                val intent = Intent(this@agregar_maqueta, lista_maquetas::class.java )
                startActivity(intent)
            }
        }

        flt_tomarfoto.setOnClickListener {
            checkCameraPermission()
            Log.d("MiApp", "Intent de cámara lanzado")
        }




    }

    //FUNCIÓN PARA VALIDAR
    fun validarCampos(): Int {
        var contadorErrores: Int = 0
        val validate = Validator()

        val til_nombre_maqueta = findViewById<TextInputLayout>(R.id.til_nom_maqueta)
        val til_serie = findViewById<TextInputLayout>(R.id.til_serie)
        val til_dificultad = findViewById<TextInputLayout>(R.id.til_dificultad)
        val til_precio = findViewById<TextInputLayout>(R.id.til_precio)
        val spn_marca = findViewById<Spinner>(R.id.spn_marca)
        val spn_escala = findViewById<Spinner>(R.id.spn_escala)
        val rate_stars = findViewById<RatingBar>(R.id.rate_stars)
        val btn_uploadphoto = findViewById<Button>(R.id.btn_uploadphoto)
        val btn_takephoto = findViewById<Button>(R.id.btn_takephoto)
        val txtv_error_marca = findViewById<TextView>(R.id.textv_error_marca)
        val textv_error_escala = findViewById<TextView>(R.id.textv_error_escala)
        val str_marca = spn_marca.selectedItem.toString()
        val str_escala = spn_escala.selectedItem.toString()



        var str_nom_maqueta = til_nombre_maqueta.editText?.text.toString()
        var str_serie = til_serie.editText?.text.toString()
        var str_dificultad = til_dificultad.editText?.text.toString()
        var str_precio = til_precio.editText?.text.toString()
        var flt_stars = rate_stars.rating




        // Validación nombre maqueta vacío
        if (validate.validarCampoNulo(str_nom_maqueta)) {
            til_nombre_maqueta.error = getString(R.string.error_campo_vacio)
            contadorErrores++
            //Validar nombre corto
        } else if (validate.validarLongitud(str_nom_maqueta)) {
            til_nombre_maqueta.error = getString(R.string.error_formato_nombre)
            contadorErrores++
            //Validar marca
        } else if (validate.validarOpcionSeleccionada(str_marca)) {
            contadorErrores++
            txtv_error_marca.error = getString(R.string.error_select_spinner)
            txtv_error_marca.visibility = View.VISIBLE
            //Validar serie vacío
        } // Validación nombre maqueta vacío
        if (validate.validarCampoNulo(str_serie)) {
            til_serie.error = getString(R.string.error_campo_vacio)
            contadorErrores++
            //Validar serie corto
        } else if (validate.validarLongitud(str_serie)) {
            til_serie.error = getString(R.string.error_formato_nombre)
            contadorErrores++
            //Validar escala
        } else if (validate.validarOpcionSeleccionada(str_escala)) {
            contadorErrores++
            txtv_error_marca.error = getString(R.string.error_select_spinner)
            Toast.makeText(getApplicationContext(),"Por favor selecciona una escala",Toast.LENGTH_SHORT).show();
            // Validación dificultad vacío
            if (validate.validarCampoNulo(str_dificultad)) {
                til_dificultad.error = getString(R.string.error_campo_vacio)
                contadorErrores++
                //Validar dificultad corto
            } else if (validate.validarLongitud(str_dificultad)) {
                til_dificultad.error = getString(R.string.error_formato_nombre)
                contadorErrores++
                // Validación precio vacío
            } else if (validate.validarCampoNulo(str_precio)) {
                til_dificultad.error = getString(R.string.error_campo_vacio)
                contadorErrores++

            }
        }else {
            til_nombre_maqueta.error = ""
            txtv_error_marca.error = ""
        }
        return contadorErrores
    }

    // METODO QUE VALIDA EL PERMISO DE LA CAMARA EN CASO DE TENER PERMISO EJECUTARA EL INTENT DE LA FOTO
    fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            // Solicitar permiso
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_REQUEST_CODE)
            Log.d("MiApp", "Permiso solicitado")
        } else {
            // Si ya tienes el permiso, inicia la cámara
            dispatchTakePictureIntent()
            Log.d("MiApp", "TakePicture Iniciado")
        }
    }

    // METODO QUE GATILLARA LA CAPTURA DE LA IMAGEN
    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                Log.d("MiApp", "dispatch Iniciado")
            }
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            val imgv_foto_maqueta = findViewById<ImageView>(R.id.imgv_foto_maqueta)
            imgv_foto_maqueta.setImageBitmap(imageBitmap)
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            CAMERA_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permiso otorgado, inicia la cámara
                    dispatchTakePictureIntent()
                } else {
                    // Permiso denegado, muestra un mensaje o maneja según tus necesidades
                    Toast.makeText(this, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show()
                }
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }







    }


}