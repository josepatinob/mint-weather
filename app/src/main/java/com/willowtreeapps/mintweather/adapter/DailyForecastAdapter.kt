package com.willowtreeapps.mintweather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.willowtreeapps.mintweather.databinding.DailyForecastItemBinding
import com.willowtreeapps.mintweather.model.DailyForecast
import com.willowtreeapps.mintweather.utils.getConditionsDrawable

class DailyForecastAdapter : RecyclerView.Adapter<DailyForecastAdapter.ItemViewHolder>() {

    class ItemViewHolder(val binding: DailyForecastItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    private val diffCallBack = object : DiffUtil.ItemCallback<DailyForecast>() {
        override fun areItemsTheSame(oldItem: DailyForecast, newItem: DailyForecast) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: DailyForecast, newItem: DailyForecast) =
            oldItem.date == newItem.date
    }

    private val differ = AsyncListDiffer(this, diffCallBack)

    fun submitList(list: List<DailyForecast>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            DailyForecastItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = differ.currentList[position]

        with(holder) {
            binding.conditionsImage.setImageResource(getConditionsDrawable(item.code))
            binding.day.text = if (position == 0) "Today" else item.day
            binding.lowTemp.text = "${item.low}\u00B0"
            binding.highTemp.text = "${item.high}\u00B0"
        }
    }

    override fun getItemCount() = differ.currentList.size
}