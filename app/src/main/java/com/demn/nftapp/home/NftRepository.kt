package com.demn.nftapp.home

import com.demn.nftapp.models.Nft

interface NftRepository {
    suspend fun getAll(): List<Nft>

    suspend fun getById(): Nft
}