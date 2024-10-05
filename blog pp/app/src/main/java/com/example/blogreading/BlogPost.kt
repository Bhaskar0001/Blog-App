package com.example.blogreading

data class BlogPost(
    val id: Int,
    val title: Title,
    val excerpt: Excerpt,
    val link: String
)

data class Title(val rendered: String)
data class Excerpt(val rendered: String)
