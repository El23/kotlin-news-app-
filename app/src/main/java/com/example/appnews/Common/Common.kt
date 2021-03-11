package com.example.appnews.Common

import com.example.appnews.Interface.NewsService
import com.example.appnews.Remote.RetrofitClient

object  Common{
    val BASE_URL = "https://newsapi.org/"
    val API_KEY = "60d43f4420014ca98d82cfcd3624a0c7"

val newsService:NewsService
    get()=RetrofitClient.getClient(BASE_URL).create(NewsService::class.java)


}


