package com.example.roomdatabase

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Insert
    fun insert(user: List<User>)

    @Query("DELETE FROM user")
    fun deleteAll()
}