package com.example.tikiapp.screens.home.flashdeal

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tikiapp.common.domain.entity.FlashDeal
import com.example.tikiapp.utils.BaseViewItem

class FlashDealAdapter(private var flashDeals: List<FlashDeal> = ArrayList()) :
    RecyclerView.Adapter<FlashDealVH>() {

    fun setFlashDeals(newFlashDeal: List<FlashDeal>) {
        Log.e("ANNX", "FlashDealAdapter bind $newFlashDeal")
        flashDeals = newFlashDeal
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashDealVH {
        return FlashDealVH(FlashDealItemView(LayoutInflater.from(parent.context), parent))
    }

    override fun getItemCount(): Int {
        return flashDeals.size
    }

    override fun onBindViewHolder(holder: FlashDealVH, position: Int) {
        holder.bind(flashDeals[position])
    }

}


class FlashDealVH(private val baseViewItem: BaseViewItem<FlashDeal>) :
    RecyclerView.ViewHolder(baseViewItem.view) {

    fun bind(flashDeal: FlashDeal) {
        baseViewItem.bind(flashDeal)
    }
}