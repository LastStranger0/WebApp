package com.example.webapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PostDao {
    @Query("SELECT * FROM Post")
    fun getAllPosts(): List<Post>
    @Query("SELECT * FROM POST WHERE id = :id")
    fun getPostById(id: Int): Post
    @Insert
    fun insertPost(post: Post)
    @Update
    fun updatePost(post: Post)
    @Delete
    fun deletePost(post: Post)
}