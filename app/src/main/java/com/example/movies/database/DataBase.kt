package com.example.movies.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movies.network.response.discover.DiscoverResponse

//
//@Database(entities =  [DiscoverResponse::class] , version = 1)
//abstract  class DataBase  : RoomDatabase() {
//    companion object{
//        private  var myDataBase : DataBase? = null
//        fun createDataBase(context: Context){
//            myDataBase =    Room.databaseBuilder(context ,DataBase::class.java,"dataBase")
//                .fallbackToDestructiveMigration()
//                .build()
//
//        }
//        fun getDataBase()  : DataBase{
//            return myDataBase!!
//
//        }
//
//    }
//    abstract fun getDao() : DataBase
//
//}