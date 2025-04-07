package com.example.plamodel_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.graphics.alpha

class Splashactivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        var splashPlamodel = findViewById<ImageView>(R.id.splash_plamodel)
        splashPlamodel.alpha = 0f
        splashPlamodel.animate().setDuration(1500).alpha(1f).withEndAction {
            val i = Intent (this, MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }




    }
}