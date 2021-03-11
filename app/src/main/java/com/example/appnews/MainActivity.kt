package com.example.appnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.AlertDialog
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.example.appnews.Adapter.ViewHolder.ListSourceAdapter
import com.example.appnews.Common.Common
import com.example.appnews.Interface.NewsService
import com.example.appnews.Model.WebSite
import com.google.gson.Gson
import dmax.dialog.SpotsDialog

import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {

    lateinit var layoutManager: LinearLayoutManager
    lateinit var mService: NewsService
    lateinit var adapter: ListSourceAdapter
    lateinit var dialog:AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Paper.init(this)

        mService = Common.newsService
        swipe_to_refresh.setOnRefreshListener {
            loadWebSiteSource(true)
        }

        recycler_view_news.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recycler_view_news.layoutManager = layoutManager


        loadWebSiteSource(false)
//        dialog = SpotsDialog(this)

    }
    private fun loadWebSiteSource(isRefresh:Boolean){

        if (!isRefresh){
            val cashe = Paper.book().read<String>("cache")
            if (cashe!=null && !cashe.isBlank()&&cashe!="null"){
                //read
                val  webSite = Gson().fromJson<WebSite>(cashe,WebSite::class.java)
                adapter.notifyDataSetChanged()
                recycler_view_news.adapter = adapter
            }else{
                //load and write
                dialog.show()

                mService.sources.enqueue(object :retrofit2.Callback<WebSite>{
                    override fun onResponse(call: Call<WebSite>, response: Response<WebSite>) {

                        adapter= ListSourceAdapter(baseContext,response.body()!!)
                        adapter.notifyDataSetChanged()
                        recycler_view_news.adapter = adapter



                    }

                    override fun onFailure(call: Call<WebSite>, t: Throwable) {

                        Toast.makeText(baseContext,"failed",Toast.LENGTH_SHORT).show()


                    }
                })
            }
        }
    }
}
