package com.example.appnews.Common

import com.example.appnews.Interface.NewsService
import com.example.appnews.Remote.RetrofitClient
import java.lang.StringBuilder

object  Common{
    val BASE_URL = "https://newsapi.org/"
    val API_KEY = "60d43f4420014ca98d82cfcd3624a0c7"

val newsService:NewsService
    get()=RetrofitClient.getClient(BASE_URL).create(NewsService::class.java)

    fun getNewsAPI(source:String):String{
        val apiUrl = StringBuilder("https://newsapi.org/v2/top-headlines?sources=bbc-news&apiKey=60d43f4420014ca98d82cfcd3624a0c7")
            .append(source)
            .append("&apiKey")
            .append(API_KEY)
            .toString()
return apiUrl
    }

}


