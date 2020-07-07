package com.appsbyasfar.covid19.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.appsbyasfar.covid19.R
import com.appsbyasfar.covid19.models.CountryStatsModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener

class HomeCountriesRecyclerViewAdapter(private val countries:List<CountryStatsModel>):
    RecyclerView.Adapter<HomeCountriesRecyclerViewAdapter.ViewHolder>() {

    private lateinit var context:Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context=parent.context
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview_country_stats_home, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val countryFlagUrl="https://www.countryflags.io/${countries[position].getCountryCode()}/flat/64.png"
        Glide.with(context).load(countryFlagUrl).listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(p0: GlideException?, p1: Any?, p2: com.bumptech.glide.request.target.Target<Drawable>?, p3: Boolean): Boolean {
                return false
            }
            override fun onResourceReady(p0: Drawable?, p1: Any?, p2: com.bumptech.glide.request.target.Target<Drawable>?, p3: DataSource?, p4: Boolean): Boolean {
                Palette.Builder(p0?.toBitmap()!!).generate { it?.let { palette ->
                    val dominantColor = palette.getDominantColor(ContextCompat.getColor(context, R.color.colorBackground))
                    holder.tvCountry.setTextColor(dominantColor)
                } }
                return false
            }
        }).into(holder.ivFlag)

        holder.tvCountry.text=countries[position].getCountry()
        holder.tvConfirmCount.text=countries[position].getTotalConfirmed().toString()
        holder.tvRecoveredCount.text=countries[position].getTotalRecovered().toString()
        holder.tvDeathsCount.text=countries[position].getTotalDeaths().toString()
    }

    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val ivFlag: ImageView =itemView.findViewById(R.id.iv_flag)
        val tvCountry: TextView =itemView.findViewById(R.id.tv_country_name)
        val tvConfirmCount: TextView =itemView.findViewById(R.id.tv_country_confirmed_count)
        val tvRecoveredCount: TextView =itemView.findViewById(R.id.tv_country_recovered_count)
        val tvDeathsCount: TextView =itemView.findViewById(R.id.tv_country_deaths_count)
    }

}