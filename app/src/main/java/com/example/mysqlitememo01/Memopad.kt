package com.example.mysqlitememo01

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memopad")
data class Memopad(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val key: String?,
    val memo: String?,
    @ColumnInfo(name = "write_date", defaultValue = "CURRENT_TIMESTAMP")
    val writeDate: String
)
//{
//    companion object {
//        fun createInsert(key: String?, memo: String?): Memopad {
//            return Memopad(0, key, memo, "")
//        }
//    }
//}