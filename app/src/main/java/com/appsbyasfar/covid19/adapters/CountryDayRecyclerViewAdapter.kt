package com.appsbyasfar.covid19.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.graphics.ColorUtils
import androidx.recyclerview.widget.RecyclerView
import com.appsbyasfar.covid19.R
import com.appsbyasfar.covid19.activities.StatusFilter
import com.appsbyasfar.covid19.models.CountryDayModel
import com.appsbyasfar.covid19.utils.changeColorTo
import com.appsbyasfar.covid19.utils.startDrawable

const val REMOVE=-1

class CountryDayRecyclerViewAdapter(
    var countryDays: List<CountryDayModel>,
    private val dominantColor: Int
) :RecyclerView.Adapter<CountryDayRecyclerViewAdapter.ViewHolder>(){
    private lateinit var context: Context
    private var filter:StatusFilter=StatusFilter.ALL

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context=parent.context
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview_country_day_stats, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return countryDays.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dayStats=countryDays[position]

        when(filter){
            StatusFilter.ALL->{
                holder.completeDetails()
                holder.tvSelectedStatus.text = "Active: ${dayStats.active}"
                holder.tvSelectedStatus.setTextColor(dominantColor)
                holder.tvConfirmCount.text = dayStats.confirmed.toString()
                holder.tvRecoverCount.text = dayStats.recovered.toString()
                holder.tvDeathsCount.text = dayStats.deaths.toString()
                holder.tvSelectedStatus.startDrawable(REMOVE)
            }
            StatusFilter.CONFIRMED->{
                holder.lessDetails()
                holder.tvSelectedStatus.setTextColor(context.resources.getColor(R.color.colorConfirmedStatus))
                holder.tvSelectedStatus.text = "  ${dayStats.confirmed}"
                holder.tvSelectedStatus.startDrawable(R.drawable.ic_confirm)
            }
            StatusFilter.RECOVERED->{
                holder.lessDetails()
                holder.tvSelectedStatus.setTextColor(context.resources.getColor(R.color.colorRecoveredStatus))
                holder.tvSelectedStatus.text = "  ${dayStats.recovered}"
                holder.tvSelectedStatus.startDrawable(R.drawable.ic_recovered)
            }
            StatusFilter.DEATHS->{
                holder.lessDetails()
                holder.tvSelectedStatus.setTextColor(context.resources.getColor(R.color.colorDeathStatus))
                holder.tvSelectedStatus.text = "  ${dayStats.deaths}"
                holder.tvSelectedStatus.startDrawable(R.drawable.ic_dead)
            }
        }

        holder.card.changeColorTo(ColorUtils.setAlphaComponent(dominantColor,30))
        holder.tvDate.text = dayStats.date?.subSequence(0,10)
        holder.tvDate.changeColorTo(dominantColor)
    }


    fun changeFilterTo(filter: StatusFilter){
        this.filter=filter
        notifyDataSetChanged()
//        when(filter){
//            StatusFilter.ALL->{
//
//            }
//            StatusFilter.CONFIRMED->{
//
//            }
//            StatusFilter.RECOVERED->{
//
//            }
//            StatusFilter.DEATHS->{
//
//            }
//        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card:CardView=itemView as CardView
        val tvDate:TextView = itemView.findViewById(R.id.tv_date)
        val tvSelectedStatus:TextView = itemView.findViewById(R.id.tv_selected_status)
        val tvConfirmCount:TextView = itemView.findViewById(R.id.tv_day_confirmed)
        val tvRecoverCount:TextView = itemView.findViewById(R.id.tv_day_recovered)
        val tvDeathsCount:TextView = itemView.findViewById(R.id.tv_day_deaths)
        private val ivConfirm:ImageView = itemView.findViewById(R.id.iv_confirm_icon)
        private val ivRecovered:ImageView = itemView.findViewById(R.id.iv_recover_icon)
        private val ivDeaths:ImageView = itemView.findViewById(R.id.iv_death_icon)

        fun lessDetails(){
            tvConfirmCount.visibility = View.GONE
            tvRecoverCount.visibility = View.GONE
            tvDeathsCount.visibility = View.GONE
            ivConfirm.visibility = View.GONE
            ivRecovered.visibility = View.GONE
            ivDeaths.visibility = View.GONE
        }

        fun completeDetails(){

            tvConfirmCount.visibility = View.VISIBLE
            tvRecoverCount.visibility = View.VISIBLE
            tvDeathsCount.visibility = View.VISIBLE
            ivConfirm.visibility = View.VISIBLE
            ivRecovered.visibility = View.VISIBLE
            ivDeaths.visibility = View.VISIBLE
        }
    }

}