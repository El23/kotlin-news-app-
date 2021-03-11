package com.example.appnews.Adapter.ViewHolder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appnews.Common.ISO8601Parser
import com.example.appnews.Interface.ItemClickListener
import com.example.appnews.Model.Articles
import com.example.appnews.R
import com.squareup.picasso.Picasso
import java.text.ParseException
import java.util.*

class ListNewsAdapter (val articleList:List<Articles>,private val context: Context)
    :RecyclerView.Adapter<ListNewsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListNewsViewHolder {
        var inflater = LayoutInflater.from(parent!!.context)
        var itemView = inflater.inflate(R.layout.news_layout,parent,false)
    return ListNewsViewHolder(itemView)
    }

    override fun getItemCount(): Int {

       return articleList.size
    }

    override fun onBindViewHolder(holder: ListNewsViewHolder, position: Int) {

        Picasso.with(context)
            .load(articleList[position].urlToImage)
            .into(holder.article_image)



        if(articleList[position].title!!.length>65){
            holder.article_title.text = articleList[position].title!!.substring(0,65)+"..."
        }
        else{
            holder.article_title.text = articleList[position].title!!

        }
        if(articleList[position].publishedAt!=null){
            var date:Date?=null
            try {
                date= ISO8601Parser.parse(articleList[position].publishedAt!!)

            }catch (e:ParseException){
                e.printStackTrace()
            }
            holder.article_time.setReferenceTime(date!!.time)
        }


        holder.setItemClickListener(object :ItemClickListener{
            override fun onClick(view: View, position: Int) {
                //
            }
        })
    }


}