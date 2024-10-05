package com.example.blogreading
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("wp-json/wp/v2/posts")
    fun getBlogPosts(@Query("per_page") perPage: Int, @Query("page") page: Int): Call<List<BlogPost>>
}

