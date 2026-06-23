package com.example.movies.network

import android.R.attr.level
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun createHttpClient() : HttpClient {
    return HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
        install(Logging){
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    println("Ktor: $message")
                }
            }

        }
        defaultRequest {
            url(  "https://api.themoviedb.org/3/")
            contentType(ContentType.Application.Json)
            headers{
                header("accept", "application/json")
               header("content-type", "application/json")
               header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlNmZhYmY2ZTYzNDI2MzYzOTNlNTM1NDFjY2E1M2IyYSIsIm5iZiI6MTc0OTkxNTYzMy4zNTYsInN1YiI6IjY4NGQ5N2YxM2E0YTY0M2Q3ZTNkZjc2YSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.xAAsuBAoIevhJ2Fwg5Fgmf2ieYXkN77LNSZMniszvyo")
            }

        }
    }
}