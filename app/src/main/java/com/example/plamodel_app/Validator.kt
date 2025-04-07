package com.example.plamodel_app

import android.util.Patterns
import android.widget.RatingBar
import java.util.Calendar
import java.util.regex.Pattern

class Validator {

        fun validarCampoNulo(texto:String):Boolean{
            return texto.trim().equals("") || texto.trim().length==0
        }
        fun validarCamposIguales(texto:String,texto2: String):Boolean{
            return !texto.trim().equals(texto2.trim())
        }
        fun validarNombre(nombre:String):Boolean{
            val pattern = Pattern.compile("^[a-zA-Z ]+\$")
            return !pattern.matcher(nombre).matches()
        }
        fun validarFormatoCorreo(correo:String):Boolean{
            return !Patterns.EMAIL_ADDRESS.matcher(correo).matches()
        }
        fun validarNombreDeUsuario(nombreUsuario: String): Boolean {
        val pattern = Regex("^[a-zA-Z0-9]+\$")
        return !pattern.matches(nombreUsuario)
          }

        fun validarLongitudNombreDeUsuario(nombreusuario: String): Boolean {
        return nombreusuario.trim().length <= 6
        }

        fun validarLongitud(texto: String): Boolean {
            return texto.trim().length <= 2
        }

        fun validarNombreCompleto(nombreCompleto: String): Boolean {
            return nombreCompleto.trim().length <= 2
        }

        fun noContieneNumeros(texto: String): Boolean {
            val contieneNumero = texto.any { it.isDigit() }
            return contieneNumero
        }

        fun esFechaFutura(anio: Int, mes: Int, dia: Int): Boolean {
            val fechaSeleccionada = Calendar.getInstance()
            fechaSeleccionada.set(anio, mes, dia)
            val fechaActual = Calendar.getInstance()
            return fechaSeleccionada.after(fechaActual)
        }

        fun validarOpcionSeleccionada(opcionSeleccionada: String): Boolean {
            return opcionSeleccionada == "Selecciona una opción"
        }

        fun validarRatingBarNoVacio(puntuacion: Float): Boolean {
            return puntuacion > 0.0f
        }

        fun validarCorreoElectronicoIgual(correo1: String, correo2: String): Boolean {
            return correo1 == correo2
        }
        fun validarFormatoPassword(password: String): Boolean {
            val longitudMinima = 8
            val contieneMayuscula = password.any { it.isUpperCase() }
            val contieneMinuscula = password.any { it.isLowerCase() }
            val contieneNumero = password.any { it.isDigit() }
            val contieneCaracterEspecial = password.any { !it.isLetterOrDigit() }

            return password.length >= longitudMinima &&
                    contieneMayuscula &&
                    contieneMinuscula &&
                    contieneNumero &&
                    contieneCaracterEspecial
        }

        fun validarCheckBoxSeleccionado(checkboxChecked: Boolean): Boolean {
            return !checkboxChecked
        }


}

