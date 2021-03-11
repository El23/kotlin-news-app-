package com.example.appnews.Interface

import com.example.appnews.Model.WebSite
import retrofit2.Call
import retrofit2.http.GET

interface NewsService {

    @get: GET("v2/everything?q=tesla&from=2021-02-11&sortBy=publishedAt&apiKey=60d43f4420014ca98d82cfcd3624a0c7")
    val sources:Call<WebSite>
}