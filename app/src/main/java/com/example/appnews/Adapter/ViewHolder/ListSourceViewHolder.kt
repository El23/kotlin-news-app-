package com.example.appnews.Adapter.ViewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.appnews.Interface.ItemClickListener
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.source_news.view.*

class ListSourceViewHolder(itemView: View):RecyclerView.ViewHolder(itemView),
        View.OnClickListener{

    private lateinit var itemClickListener : ItemClickListener
    var source_title = itemView.news_name

    init {
        itemView.setOnClickListener(this)
    }

    fun setItemClickListener(
        itemClickListener:ItemClickListener
    ){
        this.itemClickListener =itemClickListener
    }

    override fun onClick(v: View?) {

        itemClickListener.onClick(v!!,adapterPosition)
    }
}
