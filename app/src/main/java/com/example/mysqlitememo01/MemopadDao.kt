package com.example.mysqlitememo01

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MemopadDao {
    @Query("SELECT * FROM memopad")
    fun getAll(): List<Memopad>

    @Query("SELECT * FROM memopad WHERE `key` = :key")
    fun findKey(key: String?): List<Memopad>

//    @Insert
//    fun insert(vararg memopad: Memopad)

    @Query("INSERT INTO memopad(`key`, memo) VALUES(:key, :memo)")
    fun insert(key: String?, memo: String?)

    @Query("DELETE FROM memopad WHERE `key` = :key")
    fun delete(key: String?)

    @Query("DELETE FROM memopad")
    fun deleteAll()
}