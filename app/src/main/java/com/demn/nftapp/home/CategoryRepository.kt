package com.demn.nftapp.home

import com.demn.nftapp.models.NftCategory

interface CategoryRepository {
    suspend fun getAllCategories(): List<NftCategory>
}