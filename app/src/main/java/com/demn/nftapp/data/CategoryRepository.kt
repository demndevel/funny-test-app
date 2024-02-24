package com.demn.nftapp.data

import com.demn.nftapp.models.NftCategory

interface CategoryRepository {
    suspend fun getAllCategories(): List<NftCategory>
}