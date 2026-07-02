package com.example.movies.ui.main.tabs.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movies.domain.repositories.home.HomeRepository
import com.example.movies.ui.main.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
private val sections = listOf(
    TvSectionUiState("Recommendations", null, Resources.idle),
    TvSectionUiState("Comedy", 35, Resources.idle),
    TvSectionUiState("Drama", 18, Resources.idle),
    TvSectionUiState("Animation", 16, Resources.idle),
    TvSectionUiState("Crime", 80, Resources.idle),
    TvSectionUiState("Action & Adventure", 10759, Resources.idle),
    TvSectionUiState("Family ", 10751, Resources.idle),
    TvSectionUiState("Mystery  ", 9648, Resources.idle),
    TvSectionUiState("Kids ", 10762, Resources.idle),
    TvSectionUiState("War & Politics", 10768, Resources.idle)

)
private val movieSections = listOf(
    MovieSectionUiState("Recommendations", null, Resources.idle),
    MovieSectionUiState("Action", 28, Resources.idle),
    MovieSectionUiState("Adventure", 12, Resources.idle),
    MovieSectionUiState("Animation", 16, Resources.idle),
    MovieSectionUiState("Comedy", 35, Resources.idle),
    MovieSectionUiState("Crime", 80, Resources.idle),
    MovieSectionUiState("Drama", 18, Resources.idle),
    MovieSectionUiState("Family", 10751, Resources.idle),
    MovieSectionUiState("Fantasy", 14, Resources.idle),
    MovieSectionUiState("History", 36, Resources.idle),
    MovieSectionUiState("Horror", 27, Resources.idle),
    MovieSectionUiState("Music", 10402, Resources.idle),
    MovieSectionUiState("Mystery", 9648, Resources.idle),
    MovieSectionUiState("Romance", 10749, Resources.idle),
    MovieSectionUiState("Science Fiction", 878, Resources.idle),
    MovieSectionUiState("Thriller", 53, Resources.idle),
    MovieSectionUiState("War", 10752, Resources.idle),
    MovieSectionUiState("Western", 37, Resources.idle)
)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    val state : MutableStateFlow<HomeStates> = MutableStateFlow(HomeStates(  sections = sections , sectionsMovies = movieSections))

    fun doAction(events: HomeEvents){
        when(events){
         is    HomeEvents.getDiscoverTv -> getDiscoverTv(events.page , events.genre)
            HomeEvents.LoadHomeSections -> loadHomeSections()
            HomeEvents.LoadMovies -> loadMovies()
        }

    }

    private fun loadMovies() {
        movieSections.forEach { section ->
            getDiscoverMovies(
                page = 1,
                genre = section.genreId
            )
        }
    }

    private fun loadHomeSections() {
        sections.forEach { section ->
            getDiscoverTv(
                page = 1,
                genre = section.genreId
            )
        }
    }
    private fun getDiscoverMovies(page: Int?, genre: Int? = null) {
        viewModelScope.launch {
            val previousResults = state.value.sectionsMovies
                .firstOrNull { it.genreId == genre }
                ?.state
                ?.let { it as? Resources.Success }
                ?.data
                ?.results
                .orEmpty()

            state.value = state.value.copy(
                sectionsMovies = state.value.sectionsMovies.map { section ->
                    if (section.genreId == genre) {
                        section.copy(state = Resources.Loading)
                    } else {
                        section
                    }
                }
            )

            val response = repository.getDiscoveryMovies(page, genre)
            if (response.isSuccess) {
                val data = response.getOrNull()
                val newResults = data?.results.orEmpty()

                val mergedData = if (page != null && page > 1) {
                    data?.copy(results = previousResults + newResults)
                } else {
                    data
                }

                state.value = state.value.copy(
                    sectionsMovies = state.value.sectionsMovies.map { section ->
                        if (section.genreId == genre) {
                            section.copy(state = Resources.Success(mergedData))
                        } else {
                            section
                        }
                    }
                )
            } else {
                state.value = state.value.copy(
                    sectionsMovies = state.value.sectionsMovies.map { section ->
                        if (section.genreId == genre) {
                            section.copy(state = Resources.Error(response.exceptionOrNull()!!))
                        } else {
                            section
                        }
                    }
                )
            }
        }
    }

    private fun getDiscoverTv(page: Int?, genre: Int? = null) {
        viewModelScope.launch {
            val previousResults = state.value.sections
                .firstOrNull { it.genreId == genre }
                ?.state
                ?.let { it as? Resources.Success }
                ?.data
                ?.results
                .orEmpty()

            state.value = state.value.copy(
                sections = state.value.sections.map { section ->
                    if (section.genreId == genre) {
                        section.copy(state = Resources.Loading)
                    } else {
                        section
                    }
                }
            )

            val response = repository.getDiscoveryTv(page, genre)
            if (response.isSuccess) {
                val data = response.getOrNull()
                val newResults = data?.results.orEmpty()

                val mergedData = if (page != null && page > 1) {
                    data?.copy(results = previousResults + newResults)
                } else {
                    data
                }

                state.value = state.value.copy(
                    sections = state.value.sections.map { section ->
                        if (section.genreId == genre) {
                            section.copy(state = Resources.Success(mergedData))
                        } else {
                            section
                        }
                    }
                )
            } else {
                state.value = state.value.copy(
                    sections = state.value.sections.map { section ->
                        if (section.genreId == genre) {
                            section.copy(state = Resources.Error(response.exceptionOrNull()!!))
                        } else {
                            section
                        }
                    }
                )
            }
        }
    }
}