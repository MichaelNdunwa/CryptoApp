package com.example.cryptoapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptoapp.databinding.ViewholderCryptoBinding
import com.example.cryptoapp.model.Model
import java.text.DecimalFormat

class CryptoAdapter(private val dataList: ArrayList<Model>): RecyclerView.Adapter<CryptoAdapter.ViewHolder>() {
    private val formatter = DecimalFormat("###,###,###,###.##")
    class ViewHolder(val binding: ViewholderCryptoBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int ): CryptoAdapter.ViewHolder {
        val binding = ViewholderCryptoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CryptoAdapter.ViewHolder, position: Int) {
        val item = dataList[position]
        holder.binding.apply {
            cryptoName.text = item.name
            cryptoPrice.text = "$${formatter.format(item.price)}"
            cryptoRate.text = "${item.changePercent}%"
            cryptoSize.text = "${item.propertySize}${item.symbol}"
            cryptoAmount.text = "$${formatter.format(item.propertyAmount)}"
            sparkLineLayout.setData(item.lineData)

            val changeColour = when {
                item.changePercent > 0 -> Color.parseColor("#12c737")
                item.changePercent < 0 -> Color.parseColor("#FA1504")
                else -> Color.WHITE
            }
            cryptoRate.setTextColor(changeColour)
            sparkLineLayout.sparkLineColor = changeColour

            val drawableResourceId = holder.itemView.context.resources.getIdentifier(
                item.name, "drawable", holder.itemView.context.packageName
            )
            Glide.with(holder.itemView.context).load(drawableResourceId).into(cryptoLogo)
        }
    }

    override fun getItemCount(): Int = dataList.size

}