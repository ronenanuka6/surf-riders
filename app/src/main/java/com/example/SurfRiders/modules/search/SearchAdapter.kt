package com.example.SurfRiders.modules.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.SurfRiders.R
import com.example.SurfRiders.data.cocktail.Cocktail

class SearchAdapter(var cocktails: MutableList<Cocktail>?) :
    RecyclerView.Adapter<SearchHolder>() {

    override fun getItemCount(): Int {
        return cocktails?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cocktail_search_card, parent, false)
        return SearchHolder(itemView)
    }

    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        val cocktail = cocktails?.get(position)
        holder.bind(cocktail)
    }
}
