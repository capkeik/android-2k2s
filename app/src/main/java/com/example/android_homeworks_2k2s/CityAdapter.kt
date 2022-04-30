package com.example.android_homeworks_2k2s

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_homeworks_2k2s.data.response.ListModel
import com.example.android_homeworks_2k2s.databinding.ItemCityBinding

class CityAdapter(
    private var listModel: ListModel,
    private val onItemSelected: (String) -> (Unit)
) : RecyclerView.Adapter<CityHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder =
        CityHolder(
            ItemCityBinding.inflate(
                LayoutInflater.from(parent.context), parent, false),
            onItemSelected
        )
    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        holder.bind(listModel.list[position])
    }

    override fun getItemCount(): Int = listModel.list.size

}