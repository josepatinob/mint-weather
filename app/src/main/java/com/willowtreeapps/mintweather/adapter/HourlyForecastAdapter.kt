package com.willowtreeapps.mintweather.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.willowtreeapps.mintweather.databinding.HourlyForecastItemBinding
import com.willowtreeapps.mintweather.model.DarkSkyForecast
import com.willowtreeapps.mintweather.utils.getConditionsDrawable
import com.willowtreeapps.mintweather.utils.getHourFormattedWithZone
import kotlin.math.floor

class HourlyForecastAdapter : RecyclerView.Adapter<HourlyForecastAdapter.ItemViewHolder>() {

    class ItemViewHolder(val binding: HourlyForecastItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallBack = object : DiffUtil.ItemCallback<DarkSkyForecast>() {
        override fun areItemsTheSame(oldItem: DarkSkyForecast, newItem: DarkSkyForecast) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: DarkSkyForecast, newItem: DarkSkyForecast) =
            oldItem.time == newItem.time
    }

    private val differ = AsyncListDiffer(this, diffCallBack)

    fun submitList(list: List<DarkSkyForecast>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            HourlyForecastItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = differ.currentList[position]

        with(holder) {
            binding.hour.text =
                if (position == 0) "Now" else getHourFormattedWithZone(item.time, item.timezone)
            binding.condition.setImageResource(getConditionsDrawable(item.icon))
            binding.temperature.text = "${floor(item.temperature).toInt()}\u00B0"
        }
    }

    override fun getItemCount() = differ.currentList.size
}