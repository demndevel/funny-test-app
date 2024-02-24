package com.demn.nftapp.data

import com.demn.nftapp.models.NftCategory
import kotlinx.coroutines.delay

class FakeCategoryRepository: CategoryRepository {
    private val nftCategoryList = listOf(
        NftCategory(
            0,
            "Trending"
        ),
        NftCategory(
            1,
            "Collectibles"
        ),
        NftCategory(
            2,
            "Premium"
        ),
        NftCategory(
            3,
            "Dubai"
        ),
        NftCategory(
            4,
            "Gaming"
        )
    )

    override suspend fun getAllCategories(): List<NftCategory> {
        delay(400)
        return nftCategoryList
    }
}