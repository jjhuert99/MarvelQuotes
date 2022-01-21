package com.example.marvelquotes.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelquotes.databinding.HolderQuoteBinding
import com.example.marvelquotes.network.Quote

class HomeAdapter(val clickListener:Clicked) : ListAdapter<Quote, HomeAdapter.QuoteHolder>(DiffCallback) {
    class QuoteHolder(private var binding: HolderQuoteBinding):
        RecyclerView.ViewHolder(binding.root) {
            fun bind(quoteWhole: Quote, clickListener: Clicked){
                binding.quote.text = quoteWhole.Quote
                binding.character.text = quoteWhole.Speaker
                binding.movie.text = quoteWhole.Title
            }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Quote>() {
        override fun areItemsTheSame(oldItem: Quote, newItem: Quote): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Quote, newItem: Quote): Boolean {
            return oldItem.Quote == newItem.Quote
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.QuoteHolder {
        return QuoteHolder(HolderQuoteBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: HomeAdapter.QuoteHolder, position: Int) {
        val quoteWhole = getItem(position)
        holder.bind(getItem(position), clickListener)
        holder.bind(quoteWhole, clickListener)
    }
}

class Clicked(val clickListener: (quote: String, character: String, movie: String) -> Unit) {
    fun onClick(quoteWhole: Quote) = clickListener(quoteWhole.Quote!!, quoteWhole.Speaker!!, quoteWhole.Title!!)
}
