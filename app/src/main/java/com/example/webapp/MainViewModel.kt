package com.example.webapp

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {
    val postsLiveData: MutableLiveData<List<Post>> = MutableLiveData(emptyList())

    var database: PostDatabase? = null

    fun setDatabase(context: Context): PostDatabase{
        if (database == null) {
            database = Room.
            databaseBuilder(context.applicationContext,
                PostDatabase::class.java,
                "post")
                .build()
        }
        return database!!
    }

    fun updatePostsFromDatabase(context: Context){
        viewModelScope.launch(Dispatchers.IO){
            val database = setDatabase(context)
//            val posts = postsApi.getPosts()
//            for (post in posts.body()!!) {
//                database.getPostDao().insertPost(post)
//            }
            val databasePosts = database.getPostDao().getAllPosts()
            postsLiveData.postValue(databasePosts)
        }
    }
}