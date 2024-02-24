package com.demn.nftapp.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.demn.nftapp.ui.theme.Black100
import com.demn.nftapp.ui.theme.Black27
import com.demn.nftapp.ui.theme.Black3
import com.demn.nftapp.ui.theme.Black40
import com.demn.nftapp.ui.theme.Purple
import com.demn.nftapp.ui.theme.sfCompactDisplayFamily
@Composable
fun NftCard(
    imageUrl: String,
    nftName: String,
    nftCostInEth: String,
    nftCostInUsd: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Black3)
            .border(
                BorderStroke(
                    width = 2.dp,
                    color = Black27
                ),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(
                start = 18.dp,
                end = 18.dp,
                bottom = 22.dp,
                top = 16.dp
            )
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .fillMaxWidth()
                .height(160.dp)
                .background(Color.Unspecified)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(Modifier.height(23.dp))

        Text(
            nftName,
            color = Black100,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            fontFamily = sfCompactDisplayFamily,
            fontWeight = FontWeight.SemiBold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(Modifier.height(32.dp))

        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "$nftCostInEth ETH",
                color = Purple,
                fontFamily = sfCompactDisplayFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                lineHeight = 28.sp
            )

            Spacer(Modifier.weight(1f))

            Text(
                text = "≈ $ $nftCostInUsd",
                color = Black40,
                fontFamily = sfCompactDisplayFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                lineHeight = 22.sp
            )
        }
    }
}

@Preview
@Composable
private fun NftCardPreview() {
    MaterialTheme {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .width(250.dp)
                .height(850.dp)
                .background(Black3)
        ) {
            NftCard(
                modifier = Modifier
                    .padding(horizontal = 20.dp),
                nftCostInUsd = "251.59",
                imageUrl = "",
                nftCostInEth = "0.1",
                nftName = "1/4 generic engineering"
            )
        }
    }
}