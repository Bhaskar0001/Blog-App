package com.example.blogreading

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var blogAdapter: BlogAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://blog.vrid.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(APIService::class.java)

        // Fetch blog posts
        apiService.getBlogPosts(10, 1).enqueue(object : Callback<List<BlogPost>> {
            override fun onResponse(call: Call<List<BlogPost>>, response: Response<List<BlogPost>>) {
                if (response.isSuccessful) {
                    val blogList = response.body() ?: emptyList()
                    blogAdapter = BlogAdapter(blogList) { blogPost ->
                        val intent = Intent(this@MainActivity, DetailActivity::class.java)
                        intent.putExtra("blog_url", blogPost.link)
                        startActivity(intent)
                    }
                    recyclerView.adapter = blogAdapter
                }
            }

            override fun onFailure(call: Call<List<BlogPost>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed to load blogs", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
