package com.demn.nftapp.data

import com.demn.nftapp.models.Nft
import kotlinx.coroutines.delay

private val nftList = listOf(
    Nft(
        id = 0,
        categoryId = 0,
        name = "1/4 genetic engineering",
        cost = 0.1f,
        imageUrl = "https://i.ibb.co/SKFfPdg/image.png"
    ),
    Nft(
        id = 1,
        categoryId = 1,
        name = "Ethereum meta flower",
        cost = 3f,
        imageUrl = "https://i.ibb.co/FJYRBLq/image.png"
    ),
    Nft(
        id = 0,
        categoryId = 0,
        name = "1/4 genetic engineering",
        cost = 0.1f,
        imageUrl = "https://i.ibb.co/SKFfPdg/image.png"
    ),
    Nft(
        id = 1,
        categoryId = 1,
        name = "Ethereum meta flower",
        cost = 3f,
        imageUrl = "https://i.ibb.co/FJYRBLq/image.png"
    ),
    Nft(
        id = 0,
        categoryId = 0,
        name = "1/4 genetic engineering",
        cost = 0.1f,
        imageUrl = "https://i.ibb.co/SKFfPdg/image.png"
    ),
    Nft(
        id = 1,
        categoryId = 1,
        name = "Ethereum meta flower",
        cost = 3f,
        imageUrl = "https://i.ibb.co/FJYRBLq/image.png"
    ),
    Nft(
        id = 0,
        categoryId = 0,
        name = "1/4 genetic engineering",
        cost = 0.1f,
        imageUrl = "https://i.ibb.co/SKFfPdg/image.png"
    ),
    Nft(
        id = 2,
        categoryId = 1,
        name = "Ethereum meta flower",
        cost = 3f,
        imageUrl = "https://i.ibb.co/FJYRBLq/image.png"
    ),
    Nft(
        id = 3,
        categoryId = 0,
        name = "1/4 genetic engineering",
        cost = 0.1f,
        imageUrl = "https://i.ibb.co/SKFfPdg/image.png"
    ),
    Nft(
        id = 1,
        categoryId = 0,
        name = "Ethereum meta flower",
        cost = 3f,
        imageUrl = "https://i.ibb.co/FJYRBLq/image.png"
    ),
    Nft(
        id = 1,
        categoryId = 3,
        name = "Amogus",
        cost = 3f,
        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQKkFyZnnCbd-97WWRsSz07cdHn7_4yUm1qBg6-aLAQ-g&s"
    ),
    Nft(
        id = 1,
        categoryId = 3,
        name = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        cost = 3f,
        imageUrl = "https://i.guim.co.uk/img/static/sys-images/Guardian/Pix/pictures/2014/3/21/1395401774125/Lorem-ipsum-011.jpg?width=465&dpr=1&s=none"
    )
)

class FakeNftRepository : NftRepository {
    override suspend fun getAll(): List<Nft> {
        delay(1400)
        return nftList
    }

    override suspend fun getById(): Nft {
        TODO("Not yet implemented")
    }
}