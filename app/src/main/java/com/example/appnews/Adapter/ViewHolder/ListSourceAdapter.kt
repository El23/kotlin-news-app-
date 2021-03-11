package com.example.appnews.Adapter.ViewHolder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.appnews.Interface.ItemClickListener
import com.example.appnews.Model.WebSite
import com.example.appnews.R

class ListSourceAdapter (private val context: Context,private val webSite: WebSite)
    :RecyclerView.Adapter<ListSourceViewHolder>() {



    override fun getItemCount(): Int {
      return webSite.articles!!.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSourceViewHolder {
        val inflater = LayoutInflater.from(parent!!.context)
        val itemView = inflater.inflate(R.layout.source_news,parent,false)

        return ListSourceViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListSourceViewHolder, position: Int) {
        holder!!.source_title.text = webSite.articles!![position].title
        holder.setItemClickListener(object :ItemClickListener{
            override fun onClick(view: View, position: Int) {
                Toast.makeText(context, "///", Toast.LENGTH_LONG).show()
            }
        })
    }
}
