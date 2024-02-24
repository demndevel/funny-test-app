package com.demn.nftapp.models

data class Nft(
    val id: Int,
    val categoryId: Int,
    val name: String,
    val cost: Float,
    val imageUrl: String,
)