package com.example.android_homeworks_2k2s

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.android_homeworks_2k2s.data.response.DetailModel
import com.example.android_homeworks_2k2s.databinding.ItemCityBinding

class CityHolder(
    private val binding: ItemCityBinding,
    private val onItemSelected: (String) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(cityDetailModel: DetailModel) {
        val name = cityDetailModel.name
        val temp = cityDetailModel.main.temp

        with(binding) {
            tvName.text = name
            tvTemp.text = temp.toString()
            tvTemp.setTextColor(ContextCompat.getColor(itemView.context, chooseColor(temp)))
        }

        itemView.setOnClickListener {
            onItemSelected(name)
        }
    }
    private fun chooseColor(temp: Double) : Int{
        var result = when(temp.toInt()) {
            in -280..-35 ->  R.color.blue_700
            in -35..-25 -> R.color.blue_500
            in -25..-10 -> R.color.blue_300
            in -10..11 -> R.color.neutral
            in 11..26 -> R.color.yellow_300
            in 26..36 -> R.color.yellow_500
            else -> R.color.yellow_700
        }
        return result
    }
}

object colorChooser {

}
