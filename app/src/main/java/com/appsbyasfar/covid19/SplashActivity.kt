package com.appsbyasfar.covid19

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import com.appsbyasfar.covid19.api.Api
import com.appsbyasfar.covid19.models.SummeryModel
import com.appsbyasfar.covid19.utils.WORLD_SUMMERY
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val imageView=findViewById<ImageView>(R.id.image_view)
        val background=findViewById<ConstraintLayout>(R.id.constraint)
        val textView = findViewById<TextView>(R.id.text_view_result)

        Glide.with(this).load("https://www.countryflags.io/pk/flat/64.png").listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(p0: GlideException?, p1: Any?, p2: com.bumptech.glide.request.target.Target<Drawable>?, p3: Boolean): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onResourceReady(p0: Drawable?, p1: Any?, p2: com.bumptech.glide.request.target.Target<Drawable>?, p3: DataSource?, p4: Boolean): Boolean {
                Log.d("TAG", "OnResourceReady")

                Palette.Builder(p0?.toBitmap()!!).generate { it?.let { palette ->
                    val dominantColor = palette.getDominantColor(ContextCompat.getColor(this@SplashActivity, R.color.colorPrimary))
                    background.setBackgroundColor(palette.getLightVibrantColor(Color.WHITE))
                    textView.setTextColor(palette.getMutedColor(Color.BLACK))
                    // TODO: use dominant color

                } }
                //do something when picture already loaded
                return false
            }
        }).into(imageView)


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
                    val homeIntent= Intent(this@SplashActivity,MainActivity::class.java)
                    val countries: SummeryModel = response.body() as SummeryModel
                    homeIntent.putExtra(WORLD_SUMMERY,countries)
                    startActivity(homeIntent)
                    finish()
                    return
                }
                val countries: SummeryModel = response.body() as SummeryModel
                for (country in countries.getCountries()!!) {
                    var content = ""
                    content += """
                        Country: ${country?.getCountry()}

                        """.trimIndent()
                    content += """
                        Confirmed: ${country?.getNewConfirmed()}

                        """.trimIndent()
                    textView.append(content)
                }
            }

            override fun onFailure(
                call: Call<SummeryModel>,
                t: Throwable
            ) {
                textView.text = t.message
            }
        })
    }
}