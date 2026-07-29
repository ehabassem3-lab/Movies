package com.example.movies.network.response

import okhttp3.MediaType

data class RatedResponse
    (
            val id : Int ,
            val rated : Boolean?= false ,
            val mediaType: String
  )