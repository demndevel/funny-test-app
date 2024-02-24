package com.demn.nftapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.demn.nftapp.R
import com.demn.nftapp.ui.theme.Black11
import com.demn.nftapp.ui.theme.Black3
import com.demn.nftapp.ui.theme.Black40
import com.demn.nftapp.ui.theme.sfCompactDisplayFamily
@Composable
fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        leadingIcon = {
            Icon(
                painterResource(R.drawable.icon_search),
                contentDescription = null,
                tint = Black40,
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp)
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Black11,
            focusedContainerColor = Black11,
            unfocusedTextColor = Black40,
            focusedTextColor = Black40,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(
                text = stringResource(R.string.search_bar_placeholder),
                fontFamily = sfCompactDisplayFamily,
                fontWeight = FontWeight.SemiBold,
                color = Black40,
                fontSize = 18.sp
            )
        },
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
    )
}

@Preview
@Composable
private fun SearchBarPreview() {
    var searchBarValue by remember { mutableStateOf("") }

    MaterialTheme {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .width(328.dp)
                .height(350.dp)
                .background(Black3)
        ) {
            SearchBar(
                value = searchBarValue,
                onValueChange = { searchBarValue = it },
                Modifier
                    .fillMaxWidth()
            )
        }
    }
}