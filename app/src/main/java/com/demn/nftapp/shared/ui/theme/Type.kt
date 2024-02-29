package com.demn.nftapp.shared.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.demn.nftapp.R

val museoModernFamily = FontFamily(
    Font(R.font.museo_modern),
    Font(R.font.museo_moderno_bold),
    Font(R.font.museo_moderno_bold),
)

val sfCompactDisplayFamily = FontFamily(
    Font(R.font.sf_compact_display_regular),
    Font(R.font.sf_compact_display_bold),
    Font(R.font.sf_compact_display_bold_italic),
    Font(R.font.sf_compact_display_black),
    Font(R.font.sf_compact_display_semibold),
)

val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = museoModernFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 46.sp,
        lineHeight = 50.sp,
        letterSpacing = 0.sp
    ),
    labelMedium = TextStyle(
        fontFamily = sfCompactDisplayFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 27.sp,
        letterSpacing = 0.sp
    )
)