package com.example.movies.ui.main.tv

import androidx.lifecycle.ViewModel
import com.example.movies.domain.repositories.home.HomeRepository
import jakarta.inject.Inject

class TvViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel()  {

}