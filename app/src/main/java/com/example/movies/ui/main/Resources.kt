package com.example.movies.ui.main

sealed class Resources< out  T>{
    data object idle : Resources<Nothing>()
    data object Loading  : Resources<Nothing>()
    data class Success<out  T >(val data : T? = null) : Resources<T> ()
    data class Error(val exception :  Throwable , val message  : String? = exception.message) : Resources<Nothing> ()



}
