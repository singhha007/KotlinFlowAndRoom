package com.servicetitan.android.platform.kotlinflowroom.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.servicetitan.android.platform.kotlinflowroom.R
import com.servicetitan.android.platform.kotlinflowroom.data.model.Show
import kotlinx.android.synthetic.main.item_show.view.*

class ShowAdapter: RecyclerView.Adapter<ShowAdapter.ShowViewHolder>() {

    private var data = listOf<Show>()

    fun updateData(shows: List<Show>) {
        this.data = shows
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder =
        ShowViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_show, parent, false))

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        holder.update(data.get(position))
    }

    inner class ShowViewHolder(private var item: View): RecyclerView.ViewHolder(item) {

        fun update(show: Show) {
            item.title.text = show.name
            item.description.text = show.overview
            item.rating.text = show.voteAverage.toString()
            item.genre.text = show.genreList.map { it.name }.joinToString()
            item.date.text = show.firstAirDate.toString()
        }
    }
}