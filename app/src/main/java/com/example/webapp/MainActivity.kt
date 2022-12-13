package com.example.webapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val postsApi = PostRetrofit.getRetrofit()


        val button = findViewById<Button>(R.id.getPostButton)
        val textView = findViewById<TextView>(R.id.postsText)
        viewModel.postsLiveData.observe(this) {
            textView.text = it.toString()
        }
        button.setOnClickListener {
            viewModel.updatePostsFromDatabase(this)
        }
    }
}