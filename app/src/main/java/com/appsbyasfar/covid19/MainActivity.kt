package com.appsbyasfar.covid19

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.appsbyasfar.covid19.adapters.HomeCountriesRecyclerViewAdapter
import com.appsbyasfar.covid19.models.CountryStatsModel
import com.appsbyasfar.covid19.models.SummeryModel
import com.appsbyasfar.covid19.utils.WORLD_SUMMERY
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    private var chart: PieChart? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val extras=intent.extras
        var countries:SummeryModel?=null
        if(extras?.get(WORLD_SUMMERY) != null)
            countries=extras.get(WORLD_SUMMERY) as SummeryModel

        val countriesAdapter=HomeCountriesRecyclerViewAdapter(countries = (countries?.getCountries() as List<CountryStatsModel>?)!!)
        rv_countries_stats.adapter=countriesAdapter
        countriesAdapter.notifyDataSetChanged()

        
//        Log.d("TAG", "onCreate: ${countries?.getDate()}")

//        title = "PieChartActivity"
//
//
//        chart = findViewById(R.id.chart1)
//        chart?.setUsePercentValues(true)
//        chart?.description?.isEnabled = false
//        chart?.setExtraOffsets(5f, 10f, 5f, 5f)
//
//        chart?.setDragDecelerationFrictionCoef(0.95f)
//
////        chart?.setCenterTextTypeface(tfLight)
//        chart?.setCenterText(generateCenterSpannableText())
//
//        chart?.isDrawHoleEnabled = true
//        chart?.setHoleColor(Color.WHITE)
//
//        chart?.setTransparentCircleColor(Color.WHITE)
//        chart?.setTransparentCircleAlpha(110)
//
//        chart?.setHoleRadius(70f)
//        chart?.setTransparentCircleRadius(61f)
//
//        chart?.setDrawCenterText(true)
//
//        chart?.setRotationAngle(-75f)
//        // enable rotation of the chart by touch
//        // enable rotation of the chart by touch
//        chart?.isRotationEnabled = true
//        chart?.isHighlightPerTapEnabled = true
//
//        // chart.setUnit(" €");
//        // chart.setDrawUnitsInChart(true);
//
//        // add a selection listener
//
//        // chart.setUnit(" €");
//        // chart.setDrawUnitsInChart(true);
//
//
//        chart?.animateY(1400, Easing.EaseInOutQuad)
//        // chart.spin(2000, 0, 360);
//
//        // chart.spin(2000, 0, 360);
//        val l: Legend? = chart?.legend
//        l?.verticalAlignment = Legend.LegendVerticalAlignment.TOP
//        l?.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
//        l?.orientation = Legend.LegendOrientation.VERTICAL
//        l?.setDrawInside(false)
//        l?.xEntrySpace = 7f
//        l?.yEntrySpace = 0f
//        l?.yOffset = 0f
//
//        // entry label styling
//
//        // entry label styling
//        chart?.setEntryLabelColor(Color.WHITE)
////        chart?.setEntryLabelTypeface(tfRegular)
//        chart?.setEntryLabelTextSize(12f)

//
//
//        val textView = findViewById<TextView>(R.id.text_view_result)
//
//        val retrofit = Retrofit.Builder().baseUrl("https://api.covid19api.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        val countriesApi = retrofit.create(
//            Api::class.java
//        )
//
//        val listCall: Call<List<CountryModel>> = countriesApi.getCountries()
//
//        listCall.enqueue(object : Callback<List<CountryModel>> {
//            override fun onResponse(
//                call: Call<List<CountryModel>>,
//                response: Response<List<CountryModel>>
//            ) {
//                if (!response.isSuccessful) {
//                    textView.text = "Code " + response.code()
//                    return
//                }
//                val countries: List<CountryModel> = response.body() as List<CountryModel>
//                for (country in countries) {
//                    var content = ""
//                    content += """
//                        Country: ${country.country}
//
//                        """.trimIndent()
//                    content += """
//                        Slug: ${country.slug}
//
//                        """.trimIndent()
//                    content += """
//                        ISO2: ${country.iSO2}
//
//                        """.trimIndent()
//                    textView.append(content)
//                }
//            }
//
//            override fun onFailure(
//                call: Call<List<CountryModel>>,
//                t: Throwable
//            ) {
//                textView.text = t.message
//            }
//        })

 //       setData(3,3)
    }

    private fun getCountries(): List<CountryStatsModel> {
        TODO("Not yet implemented")
    }

    private val listOfPercentage=listOf(0.5f,0.3f,0.3f,0.2f)
    private val listOfTypes=listOf("Confirmed","Recovered","Deaths")

    private fun setData(count: Int, range: Int) {
        val entries: ArrayList<PieEntry> = ArrayList()

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (i in 0 until count) {
            entries.add(
                PieEntry(
                    listOfPercentage[i],
                    listOfTypes[i],
                    resources.getDrawable(R.drawable.ic_launcher_background)
                )
            )
        }
        val dataSet = PieDataSet(entries, "Covid 19 World Summery")
        dataSet.setDrawIcons(false)
        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f

        // add a lot of colors
        val colors: ArrayList<Int> = ArrayList()
        colors.add(Color.BLUE)
        colors.add(Color.GREEN)
        colors.add(Color.RED)
//        for (c in ColorTemplate.VORDIPLOM_COLORS) colors.add(c)
//        for (c in ColorTemplate.JOYFUL_COLORS) colors.add(c)
//        for (c in ColorTemplate.COLORFUL_COLORS) colors.add(c)
//        for (c in ColorTemplate.LIBERTY_COLORS) colors.add(c)
//        for (c in ColorTemplate.PASTEL_COLORS) colors.add(c)
//        colors.add(ColorTemplate.getHoloBlue())

        dataSet.colors = colors
        //dataSet.setSelectionShift(0f);
        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter(chart))
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.WHITE)
//        data.setValueTypeface(tfLight)
        chart!!.data = data

        // undo all highlights
        chart!!.highlightValues(null)
        chart!!.invalidate()
    }


    private fun generateCenterSpannableText(): SpannableString? {
        val s = SpannableString("Covid 19 World Stats\ndeveloped by Asfar Ali")
        s.setSpan(RelativeSizeSpan(1.7f), 0, 20, 0)
        s.setSpan(StyleSpan(Typeface.NORMAL), 20, 34, 0)
        s.setSpan(ForegroundColorSpan(Color.GRAY), 20, 34, 0)
        s.setSpan(RelativeSizeSpan(.8f), 20, 34, 0)
        s.setSpan(StyleSpan(Typeface.ITALIC), 34, s.length, 0)
        s.setSpan(ForegroundColorSpan(ColorTemplate.getHoloBlue()), 34, s.length, 0)
        return s
    }

    fun onClickAllCountries(view: View) {}

}