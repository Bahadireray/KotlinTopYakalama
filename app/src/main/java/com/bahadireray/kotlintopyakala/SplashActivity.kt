package com.bahadireray.kotlintopyakala

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var logoDondur = AnimationUtils.loadAnimation(this, R.anim.logodondur)
        imgLogo.animation = logoDondur

        /*Slash ekran yapıyor olsaydık buranın içerisinde işlemleri yapmamız gerecekti
        Animasyonumuz bitince Activity neler yapsın diye karar verirken kullanabiliriz.
        Ama biz Başla diye buton tanımlayıp diğer aktiviye öyle geçiyoruz.
         */
        object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }
            override fun onFinish() {
            }
        }.start()
        
        btnlogin.setOnClickListener(View.OnClickListener {
            var intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        })

    }




}