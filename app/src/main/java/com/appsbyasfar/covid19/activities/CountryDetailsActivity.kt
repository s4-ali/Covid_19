package com.appsbyasfar.covid19.activities

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import com.appsbyasfar.covid19.R
import com.appsbyasfar.covid19.adapters.CountryDayRecyclerViewAdapter
import com.appsbyasfar.covid19.api.Api
import com.appsbyasfar.covid19.models.CountryDayModel
import com.appsbyasfar.covid19.models.CountryStatsModel
import com.appsbyasfar.covid19.utils.COUNTRY
import com.appsbyasfar.covid19.utils.changeColorTo
import com.appsbyasfar.covid19.utils.withAlpha
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import kotlinx.android.synthetic.main.activity_country_details.*
import kotlinx.android.synthetic.main.activity_country_details.tv_country_confirmed_count
import kotlinx.android.synthetic.main.activity_country_details.tv_country_recovered_count
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

enum class StatusFilter{
    ALL,
    CONFIRMED,
    RECOVERED,
    DEATHS,
}

class CountryDetailsActivity : AppCompatActivity() {

    var adapter: CountryDayRecyclerViewAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_details)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val extras=intent.extras
        var country: CountryStatsModel?=null

        if(extras?.get(COUNTRY) != null){
            country=extras.get(COUNTRY) as CountryStatsModel
        }

        tv_title_country.text=country?.getCountry()

        val countryFlagUrl="https://www.countryflags.io/${country?.getCountryCode()}/flat/64.png"
        Glide.with(this).load(countryFlagUrl).listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(p0: GlideException?, p1: Any?, p2: com.bumptech.glide.request.target.Target<Drawable>?, p3: Boolean): Boolean {
                return false
            }
            override fun onResourceReady(p0: Drawable?, p1: Any?, p2: com.bumptech.glide.request.target.Target<Drawable>?, p3: DataSource?, p4: Boolean): Boolean {
                Palette.Builder(p0?.toBitmap()!!).generate { it?.let { palette ->
                    val dominantColor = palette.getDominantColor(ContextCompat.getColor(this@CountryDetailsActivity, R.color.colorBackground))
                    changeThemeTo(dominantColor)
                    loadCountryDetails(country?.getSlug()!!,dominantColor)
                } }
                return false
            }
        }).into(iv_flag)

        tv_country_confirmed_count.text=country?.getTotalConfirmed().toString()
        tv_country_recovered_count.text=country?.getTotalRecovered().toString()
        tv_day_deaths.text=country?.getTotalDeaths().toString()


    }

    private fun loadCountryDetails(countrySlug: String, dominantColor: Int) {

        val retrofit = Retrofit.Builder().baseUrl("https://api.covid19api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val countriesApi = retrofit.create(
            Api::class.java
        )

        val listCall: Call<List<CountryDayModel>> = countriesApi.getCountryDetailsFromFirstDay(countrySlug)

        listCall.enqueue(object : Callback<List<CountryDayModel>> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<List<CountryDayModel>>,
                response: Response<List<CountryDayModel>>
            ) {
                if (response.isSuccessful) {
                    val countryDays: List<CountryDayModel> = response.body() as List<CountryDayModel>
                    val startDate=countryDays[0].date?.subSequence(0,10)
                    val lastDate=countryDays[countryDays.size-1].date?.subSequence(0,10)
                    tv_date_filter.text="$startDate to $lastDate"
                    initRecyclerView(countryDays,dominantColor)
                    progress_indicator.visibility=View.INVISIBLE
                }
            }

            override fun onFailure(
                call: Call<List<CountryDayModel>>,
                t: Throwable
            ) {
                Toast.makeText(this@CountryDetailsActivity,t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun initRecyclerView(countryDays: List<CountryDayModel>, dominantColor: Int) {
        adapter=CountryDayRecyclerViewAdapter(countryDays,dominantColor)
        rv_country_day_stats.adapter=adapter
        adapter?.notifyDataSetChanged()
    }

    private fun changeThemeTo(dominantColor: Int) {
        tv_title_country.changeColorTo(dominantColor)
        tv_date_filter.changeColorTo(dominantColor)
    }

    fun onClickFilter(view: View) {
        val card=view as CardView
        when(card.id){
            R.id.cv_filter_all->{
                card.changeColorTo(Color.BLACK.withAlpha(30))
                adapter?.changeFilterTo(StatusFilter.ALL)

                cv_filter_confirmed.setCardBackgroundColor(Color.WHITE)
                cv_filter_recover.setCardBackgroundColor(Color.WHITE)
                cv_filter_death.setCardBackgroundColor(Color.WHITE)

            }
            R.id.cv_filter_confirmed->{
                card.changeColorTo(resources.getColor(R.color.colorConfirmedStatus).withAlpha(30))
                adapter?.changeFilterTo(StatusFilter.CONFIRMED)

                cv_filter_all.setCardBackgroundColor(Color.WHITE)
                cv_filter_recover.setCardBackgroundColor(Color.WHITE)
                cv_filter_death.setCardBackgroundColor(Color.WHITE)

            }
            R.id.cv_filter_recover->{
                card.changeColorTo(resources.getColor(R.color.colorRecoveredStatus).withAlpha(30))
                adapter?.changeFilterTo(StatusFilter.RECOVERED)

                cv_filter_all.setCardBackgroundColor(Color.WHITE)
                cv_filter_confirmed.setCardBackgroundColor(Color.WHITE)
                cv_filter_death.setCardBackgroundColor(Color.WHITE)

            }
            R.id.cv_filter_death->{
                card.changeColorTo(resources.getColor(R.color.colorDeathStatus).withAlpha(30))
                adapter?.changeFilterTo(StatusFilter.DEATHS)

                cv_filter_all.setCardBackgroundColor(Color.WHITE)
                cv_filter_confirmed.setCardBackgroundColor(Color.WHITE)
                cv_filter_recover.setCardBackgroundColor(Color.WHITE)

            }

        }
    }

    fun onBackPressed(view: View) {
        onBackPressed()
    }
}