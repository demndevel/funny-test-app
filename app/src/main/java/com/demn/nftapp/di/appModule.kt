package com.demn.nftapp.di

import com.demn.nftapp.data.CategoryRepository
import com.demn.nftapp.data.FakeCategoryRepository
import com.demn.nftapp.data.FakeNftRepository
import com.demn.nftapp.data.NftRepository
import com.demn.nftapp.ui.home.HomeScreenViewModel
import org.koin.dsl.module

val appModule = module {
    factory { HomeScreenViewModel(get(), get()) }
    factory<NftRepository> { FakeNftRepository() }
    factory<CategoryRepository> { FakeCategoryRepository() }
}