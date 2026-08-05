package com.example.movies.ui.main.tabs.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.R
import com.example.movies.domain.repositories.home.HomeRepository
import com.example.movies.ui.main.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
 val sections = listOf(
    TvSectionUiState(R.string.recommendations, null, Resources.idle),
    TvSectionUiState(R.string.comedy, 35, Resources.idle),
    TvSectionUiState(R.string.drama, 18, Resources.idle),
    TvSectionUiState(R.string.animation, 16, Resources.idle),
    TvSectionUiState(R.string.crime, 80, Resources.idle),
    TvSectionUiState(R.string.action_adventure, 10759, Resources.idle),
    TvSectionUiState(R.string.family, 10751, Resources.idle),
    TvSectionUiState(R.string.mystery, 9648, Resources.idle),
    TvSectionUiState(R.string.kids, 10762, Resources.idle),
    TvSectionUiState(R.string.war_politics, 10768, Resources.idle)


)
 val movieSections = listOf(
    MovieSectionUiState(R.string.recommendations, null, Resources.idle),
    MovieSectionUiState(R.string.action, 28, Resources.idle),
    MovieSectionUiState(R.string.adventure, 12, Resources.idle),
    MovieSectionUiState(R.string.animation, 16, Resources.idle),
    MovieSectionUiState(R.string.comedy, 35, Resources.idle),
    MovieSectionUiState(R.string.crime, 80, Resources.idle),
    MovieSectionUiState(R.string.drama, 18, Resources.idle),
    MovieSectionUiState(R.string.family, 10751, Resources.idle),
    MovieSectionUiState(R.string.fantasy, 14, Resources.idle),
    MovieSectionUiState(R.string.history, 36, Resources.idle),
    MovieSectionUiState(R.string.horror, 27, Resources.idle),
    MovieSectionUiState(R.string.music, 10402, Resources.idle),
    MovieSectionUiState(R.string.mystery, 9648, Resources.idle),
    MovieSectionUiState(R.string.romance, 10749, Resources.idle),
    MovieSectionUiState(R.string.sciencefiction, 878, Resources.idle),
    MovieSectionUiState(R.string.thriller, 53, Resources.idle),
    MovieSectionUiState(R.string.war, 10752, Resources.idle),
    MovieSectionUiState(R.string.western, 37, Resources.idle)
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
            is HomeEvents.getDiscoverMovies -> getDiscoverMovies(events.page , events.genre)
            is HomeEvents.OnMoreTvClick -> {
                val section = state.value.sections.first { it.genreId == events.genre }
                getDiscoverTv(section.page + 1, events.genre)
            }

            is HomeEvents.OnMoreMovieClick -> {
                val section = state.value.sectionsMovies.first { it.genreId == events.genre }
                getDiscoverMovies(section.page + 1, events.genre)
            }
        }

    }




    init {
        doAction(HomeEvents.LoadHomeSections)
        doAction(HomeEvents.LoadMovies)

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
                        if (page == 1) {
                            section.copy(
                                state = Resources.Loading
                            )
                        } else {
                            section.copy(
                                isLoadingMore = true
                            )
                        }
                    } else {
                        section
                    }
                }
            )

            val response = repository.getDiscoveryMovies(page, genre)
            if (response.isSuccess) {
                delay(2000)
                val data = response.getOrNull()
                val newResults = data?.results.orEmpty()
                val mergedData = if (page != null && page > 1) {
                    data?.copy(results = (previousResults + newResults).distinctBy { it?.id })
                } else {
                    data
                }

                state.value = state.value.copy(
                    sectionsMovies = state.value.sectionsMovies.map { section ->
                        if (section.genreId == genre) {
                            section.copy(
                                state = Resources.Success(mergedData),
                                page = page ?: 1,
                                isLoadingMore = false
                            )
                        } else {
                            section
                        }
                    }
                )
            } else {
                delay(2000)
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
                        if (page == null || page == 1) {
                            section.copy(
                                state = Resources.Loading,
                                isLoadingMore = false
                            )
                        } else {
                            section.copy(
                                isLoadingMore = true
                            )
                        }
                    } else {
                        section
                    }
                }
            )

            val response = repository.getDiscoveryTv(page, genre)

            if (response.isSuccess) {
                delay(2000)

                val data = response.getOrNull()
                val newResults = data?.results.orEmpty()

                val mergedData =
                    if (page != null && page > 1) {
                        data?.copy(
                            results = previousResults + newResults
                        )
                    } else {
                        data
                    }

                state.value = state.value.copy(
                    sections = state.value.sections.map { section ->
                        if (section.genreId == genre) {
                            section.copy(
                                state = Resources.Success(mergedData),
                                page = page ?: 1,
                                isLoadingMore = false
                            )
                        } else {
                            section
                        }
                    }
                )
            } else {
                delay(2000)

                state.value = state.value.copy(
                    sections = state.value.sections.map { section ->
                        if (section.genreId == genre) {
                            section.copy(
                                state = Resources.Error(response.exceptionOrNull()!!),
                                isLoadingMore = false
                            )
                        } else {
                            section
                        }
                    }
                )
            }
        }
    }
}