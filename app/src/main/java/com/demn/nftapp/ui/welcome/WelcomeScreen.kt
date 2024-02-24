package com.demn.nftapp.ui.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.demn.nftapp.R
import com.demn.nftapp.ui.home.HomeScreen
import com.demn.nftapp.ui.theme.Black100
import com.demn.nftapp.ui.theme.Black3
import com.demn.nftapp.ui.theme.NFTAppTheme
import com.demn.nftapp.ui.theme.museoModernFamily

class WelcomeScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        Column(
            modifier = Modifier
                .background(color = Black3)
                .fillMaxSize()
                .padding(
                    horizontal = 30.dp
                ),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(Modifier.height(144.dp))

            WelcomeText()

            Spacer(Modifier.weight(1f))

            ExploreNftsButton(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    navigator.push(HomeScreen())
                }
            )

            Spacer(Modifier.height(112.dp))
        }
    }

    @Composable
    private fun WelcomeText() {
        val starIconInlineContentId = "starIconInlineContentId"
        val text = buildAnnotatedString {
            append(stringResource(R.string.market_place_welcome_screen))
            appendInlineContent(starIconInlineContentId, "[star]")
        }
        val inlineContent = mapOf(
            Pair(
                starIconInlineContentId,
                InlineTextContent(
                    Placeholder(
                        width = 56.sp,
                        height = 55.sp,
                        placeholderVerticalAlign = PlaceholderVerticalAlign.Center
                    )
                ) {
                    Icon(
                        painterResource(R.drawable.star_img),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
            )
        )

        Icon(
            painterResource(id = R.drawable.nft_welcome_img),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
        )
        Text(
            text = text,
            color = Black100,
            fontSize = 76.sp,
            lineHeight = 91.sp,
            fontFamily = museoModernFamily,
            fontWeight = FontWeight.Black,
            inlineContent = inlineContent
        )
    }

    @Preview
    @Composable
    private fun ContentPreview() {
        NFTAppTheme {
            Content()
        }
    }
}