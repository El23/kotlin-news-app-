package com.example.appnews

import android.app.AlertDialog
import android.app.Service
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appnews.Adapter.ViewHolder.ListNewsAdapter
import com.example.appnews.Common.Common
import com.example.appnews.Interface.NewsService
import com.example.appnews.Model.News
import com.squareup.picasso.Picasso
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_list_news.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.swipe_to_refresh
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class ListNews : AppCompatActivity() {


    var source =""
    var webHotUrl:String?=""

    lateinit var dialog: AlertDialog
    lateinit var mService: NewsService
    lateinit var adapter: ListNewsAdapter
    lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_news)

        mService = Common.newsService
//        dialog = SpotsDialog(this)
        swipe_to_refresh.setOnRefreshListener { loadNews(source,true) }

        diagonalLayout.setOnClickListener{

        }
        list_news.setHasFixedSize(true)
        list_news.layoutManager = LinearLayoutManager(this)



        if(intent!=null){
            source = intent.getStringExtra("source").toString()
            if(!source.isEmpty())
                loadNews(source,false)
        }

    }

    private fun loadNews(source:String?, isRefreshed:Boolean){

        if(isRefreshed){
            dialog.show()
            mService.getnewsFromSource(Common.getNewsAPI(source!!))
                .enqueue(object : retrofit2.Callback<News> {
                    override fun onFailure(call: Call<News>?, t: Throwable) {

                    }

                    override fun onResponse(call: Call<News>, response: Response<News>) {
                     dialog.dismiss()

                        Picasso.with(baseContext)
                            .load(response!!.body()!!.articles!![0].urlToImage)
                            .into(top_image)
                        top_title.text  =response!!.body()!!.articles!![0].title
                        top_author.text  =response!!.body()!!.articles!![0].author

                        webHotUrl = response!!.body()!!.articles!![0].url
                        val removeFirstItem = response!!.body()!!.articles
                        removeFirstItem!!.removeAt(0)

                        adapter = ListNewsAdapter(removeFirstItem!!,baseContext)
                        adapter.notifyDataSetChanged()

                        list_news.adapter = adapter

                    }


                })
        }
        else{

            swipe_to_refresh.isRefreshing =true

            mService.getnewsFromSource(Common.getNewsAPI(source!!))
                .enqueue(object : retrofit2.Callback<News> {
                    override fun onFailure(call: Call<News>?, t: Throwable) {

                    }

                    override fun onResponse(call: Call<News>, response: Response<News>) {
                       swipe_to_refresh.isRefreshing = false

                        Picasso.with(baseContext)
                            .load(response.body()!!.articles!![0].urlToImage)
                            .into(top_image)
                        top_title.text  =response!!.body()!!.articles!![0].title
                        top_author.text  =response!!.body()!!.articles!![0].author

                        webHotUrl = response.body()!!.articles!![0].url
                        val removeFirstItem = response!!.body()!!.articles
                        removeFirstItem!!.removeAt(0)

                        adapter = ListNewsAdapter(removeFirstItem!!,baseContext)
                        adapter.notifyDataSetChanged()

                        list_news.adapter = adapter

                    }


                })
        }
    }


}
