package com.demn.nftapp.ui.home

import com.demn.nftapp.data.CategoryRepository
import com.demn.nftapp.data.NftRepository
import com.demn.nftapp.models.Nft
import com.demn.nftapp.models.NftCategory
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface HomeUiState {
    val isLoading: Boolean
    val searchBarValue: String

    data class NoCategoriesAndNfts(
        override val isLoading: Boolean,
        override val searchBarValue: String
    ) : HomeUiState

    data class HasCategoriesAndNfts(
        val categories: List<NftCategory>,
        val nfts: List<Nft>,
        override val isLoading: Boolean,
        override val searchBarValue: String
    ) : HomeUiState
}

private data class HomeViewModelState(
    val isLoading: Boolean,
    val searchBarValue: String,
    val categories: List<NftCategory>? = null,
    val nfts: List<Nft>? = null,
) {
    fun toUiState(): HomeUiState {
        return if (categories == null || nfts == null) {
            HomeUiState.NoCategoriesAndNfts(
                isLoading,
                searchBarValue
            )
        } else {
            HomeUiState.HasCategoriesAndNfts(
                categories,
                nfts,
                isLoading,
                searchBarValue
            )
        }
    }
}

class HomeScreenViewModel(
    private val nftRepository: NftRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {
    private val viewModelState = MutableStateFlow(
        HomeViewModelState(
            true,
            "",
            null,
            null
        )
    )

    val uiState = viewModelState
        .map(HomeViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    init {
        refreshNftsAndCategories()
    }

    fun refreshNftsAndCategories() {
        viewModelState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val categories = categoryRepository.getAllCategories()
            val nfts = nftRepository.getAll()

            viewModelState.update {
                it.copy(
                    categories = categories,
                    nfts = nfts,
                    isLoading = false
                )
            }
        }
    }

    fun updateSearchBarValue(newValue: String) {
        viewModelState.update {
            it.copy(
                searchBarValue = newValue
            )
        }
    }
}