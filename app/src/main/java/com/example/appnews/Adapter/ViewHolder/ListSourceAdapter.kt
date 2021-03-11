package com.example.appnews.Adapter.ViewHolder

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appnews.Model.WebSite
import com.example.appnews.R

class ListSourceAdapter (private val context: Context,private val webSite: WebSite)
    :RecyclerView.Adapter<ListSourceViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSourceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.source_news,parent,false)

        return ListSourceViewHolder(itemView)

    }

    override fun getItemCount(): Int {
      return webSite.articles!!.size
    }

    override fun onBindViewHolder(holder: ListSourceViewHolder, position: Int) {

    }
}
