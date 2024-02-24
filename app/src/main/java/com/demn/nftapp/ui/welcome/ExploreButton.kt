package com.demn.nftapp.ui.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.demn.nftapp.R
import com.demn.nftapp.ui.theme.Black100
import com.demn.nftapp.ui.theme.Black27
import com.demn.nftapp.ui.theme.Black3
import com.demn.nftapp.ui.theme.museoModernFamily

@Composable
fun ExploreNftsButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier
            .height(80.dp),
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = Black100,
            contentColor = Black3,
            disabledContainerColor = Black100,
            disabledContentColor = Black3
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(
            text = stringResource(R.string.explore_nfts_button_text),
            fontFamily = museoModernFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 24.sp
        )
    }
}
@Preview(showBackground = true)
@Composable
private fun ExploreNftsButtonPreview() {
    Box(
        modifier = Modifier
            .height(400.dp)
            .width(800.dp)
            .background(Black27),
        contentAlignment = Alignment.Center
    ) {
        ExploreNftsButton(
            onClick = {}
        )
    }
}