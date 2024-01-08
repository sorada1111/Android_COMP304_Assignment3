package com.example.soradaprathan_comp304sec001_lab03_ex03


import android.os.Bundle

import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var earthImageView: ImageView
    private lateinit var sunImageView: ImageView
    private lateinit var startAnimBth: Button
    private lateinit var stopAniBtn: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        earthImageView = findViewById(R.id.earthImgView)
        sunImageView = findViewById(R.id.sunImgView)
        startAnimBth = findViewById(R.id.startAnimBtn)
        stopAniBtn = findViewById(R.id.stopAnimBtn)


        startAnimBth.setOnClickListener {
            start()
        }

        stopAniBtn.setOnClickListener {
            stop()
        }


    }

     fun start() {
         Toast.makeText(this, "Earth orbit around the sun", Toast.LENGTH_SHORT).show()
         earthImageView = findViewById(R.id.earthImgView)
         sunImageView = findViewById(R.id.sunImgView)
         // Load the animation
         var sunAnim = AnimationUtils.loadAnimation(this, R.anim.sun_anim)
         var earthAnim = AnimationUtils.loadAnimation(this, R.anim.earth_anim)

         sunAnim.repeatCount = Animation.INFINITE
         earthAnim.repeatMode = Animation.RESTART
         earthAnim.repeatCount = Animation.INFINITE

         earthImageView.startAnimation(earthAnim)
         sunImageView.startAnimation(sunAnim)

    }

     fun stop() {
        sunImageView.clearAnimation()
        earthImageView.clearAnimation()
    }

}