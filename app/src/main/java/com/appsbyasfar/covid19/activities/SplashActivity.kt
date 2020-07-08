package com.appsbyasfar.covid19.activities

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import com.appsbyasfar.covid19.R
import com.appsbyasfar.covid19.api.Api
import com.appsbyasfar.covid19.models.SummeryModel
import com.appsbyasfar.covid19.utils.WORLD_SUMMERY
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splash)


        val imageView=findViewById<ImageView>(R.id.image_view)

        Glide.with(this)
            .load(R.drawable.splash_bg)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)

        val retrofit = Retrofit.Builder().baseUrl("https://api.covid19api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val countriesApi = retrofit.create(
            Api::class.java
        )

        val listCall: Call<SummeryModel> = countriesApi.getCompleteSummery()

        listCall.enqueue(object : Callback<SummeryModel> {
            override fun onResponse(
                call: Call<SummeryModel>,
                response: Response<SummeryModel>
            ) {
                if (response.isSuccessful) {
                    val homeIntent= Intent(this@SplashActivity,
                        MainActivity::class.java)
                    val countries: SummeryModel = response.body() as SummeryModel
                    homeIntent.putExtra(WORLD_SUMMERY,countries)
                    startActivity(homeIntent)
                    finish()
                    return
                }
            }

            override fun onFailure(
                call: Call<SummeryModel>,
                t: Throwable
            ) {
                Toast.makeText(this@SplashActivity,t.message,Toast.LENGTH_LONG).show()
            }
        })
    }
}