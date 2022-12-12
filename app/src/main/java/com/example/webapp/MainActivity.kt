package com.example.webapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val postsApi = PostRetrofit.getRetrofit()
        val database = Room.
        databaseBuilder(this.applicationContext,
            PostDatabase::class.java,
            "post")
            .build()

        val button = findViewById<Button>(R.id.getPostButton)
        val textView = findViewById<TextView>(R.id.postsText)

        button.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val posts = postsApi.getPosts()
                for (post in posts.body()!!) {
                    database.getPostDao().insertPost(post)
                }
                val databasePosts = database.getPostDao().getAllPosts()
                withContext(Dispatchers.Main){
                    textView.text = databasePosts.toString()
                }
            }
        }
    }
}