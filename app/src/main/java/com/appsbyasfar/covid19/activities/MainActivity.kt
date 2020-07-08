package com.appsbyasfar.covid19.activities


import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.appsbyasfar.covid19.R
import com.appsbyasfar.covid19.adapters.HomeCountriesRecyclerViewAdapter
import com.appsbyasfar.covid19.models.CountryStatsModel
import com.appsbyasfar.covid19.models.SummeryModel
import com.appsbyasfar.covid19.utils.WORLD_SUMMERY
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val extras=intent.extras
        var summery:SummeryModel?=null
        if(extras?.get(WORLD_SUMMERY) != null)
            summery=extras.get(WORLD_SUMMERY) as SummeryModel

        tv_date.text=summery?.getDate()?.subSequence(0,10)

        val globalStats=summery?.getGlobal()!!

        tv_world_confirm_total.text=globalStats.getTotalConfirmed().toString()
        tv_world_confirm_new.text=globalStats.getNewConfirmed().toString()

        tv_world_recover_total.text=globalStats.getTotalRecovered().toString()
        tv_world_recover_new.text=globalStats.getNewRecovered().toString()

        tv_world_deaths_total.text=globalStats.getTotalDeaths().toString()
        tv_world_deaths_new.text=globalStats.getNewDeaths().toString()


        val countriesAdapter=HomeCountriesRecyclerViewAdapter(countries = (summery.getCountries() as List<CountryStatsModel>?)!!)
        rv_countries_stats.adapter=countriesAdapter
        countriesAdapter.notifyDataSetChanged()


    }

    fun onClickAllCountries(view: View) {}

}