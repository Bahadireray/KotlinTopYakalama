package com.bahadireray.kotlintopyakala

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random as Random

class MainActivity : AppCompatActivity() {
    //Score tutan bir değişkeni farklı yerlerden de çağırabiliriz.
    //Onun için tanımlamayı erişebilir olarak Main Metot içerisinde tanımlıyoruz.
    var score = 0
    var imageArray = ArrayList<ImageView>()
    var handler = Handler()
    var runnable = Runnable { }
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Shared İnitialize
        sharedPreferences = this.getSharedPreferences(
            "com.bahadireray.kotlintopyakala",
            Context.MODE_PRIVATE
        )
        //İmageArray tanımlamaları ayrı fonksiyon içerisinde tanımladık.
        imageList()
        hideImages()

        val saveScore = sharedPreferences.getInt("score", 0)
        scoreText.text = "Skor: $saveScore"


        // Geri sayım ana metot içerisinde olacağı için burada tanımıyorum.
        object : CountDownTimer(15000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeText.text = "Time: " + millisUntilFinished / 1000   //Milisaniyeyi saniye olarak azaltıyoruz
            }

            //Zamanlayıcı duracağı zaman olacaklara karar veriyoruz
            override fun onFinish() {
                timeText.text = "Time: 0"
                handler.removeCallbacks(runnable)
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE   //Tıklanabilir öğelerimizi ortadan kaldırıyoruz
                }

                //Süre bitince karar aşamasında Alert diyalog kullanıyoruz.
                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("OYUN BİTTİ")
                alert.setMessage("Puanınız: $score yeniden denemek ister misin?")
                alert.setPositiveButton("Evet") { dialog, which ->
                    val intent = intent
                    startActivity(intent)
                    finish() // Arka planda açık uygulamalar birikmesin
                }
                alert.setNegativeButton("No") { dialog, which ->
                    Toast.makeText(this@MainActivity, "Oyun Bitti", Toast.LENGTH_LONG).show()
                    val intent = Intent(applicationContext, SplashActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                alert.setCancelable(false) // Diyalog haricinde tıklamaları engelliyoruz.
                alert.show()
            }
        }.start()
    }

    // Görsel öğelerimize tıklanabilir onclik metodu atayıp işlemleri içerisinde yapıyoruz.
    fun increaseScore(view: View) {
        score++
        sharedPreferences.edit().putInt("score", score).apply()
        scoreText.text = "Skor:${score.toString()}"
    }

    fun hideImages() {

        //Arka planda işlemleri yaparken Runnable kullanıyoruz. Uygulamada işlemler sırasıyla gerçekleşmesi için.
        runnable = object : Runnable {
            override fun run() {
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }
                var random = java.util.Random()
                val randomIndex = random.nextInt(20)   //0-20 farklı arasında değerler üretiyor
                imageArray[randomIndex].visibility = View.VISIBLE //Üretilen sayıların Array içerisinde karşılığı tıklanabilir yapıyoruz.
                handler.postDelayed(runnable, 600) //işlemlerin gerçekleşme hızını belirliyoruz.
            }
        }
        handler.post(runnable) //İşlemleri başlatıyoruz.
    }

    fun imageList() {
        imageArray.add(imageView)
        imageArray.add(imageView2)
        imageArray.add(imageView3)
        imageArray.add(imageView4)
        imageArray.add(imageView5)
        imageArray.add(imageView6)
        imageArray.add(imageView7)
        imageArray.add(imageView8)
        imageArray.add(imageView9)
        imageArray.add(imageView10)
        imageArray.add(imageView11)
        imageArray.add(imageView12)
        imageArray.add(imageView13)
        imageArray.add(imageView14)
        imageArray.add(imageView15)
        imageArray.add(imageView16)
        imageArray.add(imageView17)
        imageArray.add(imageView18)
        imageArray.add(imageView19)
        imageArray.add(imageView20)


    }
}