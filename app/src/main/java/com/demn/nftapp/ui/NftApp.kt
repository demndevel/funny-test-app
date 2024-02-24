package com.demn.nftapp.ui

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import com.demn.nftapp.ui.welcome.WelcomeScreen

@Composable
fun NftApp() {
    Navigator(WelcomeScreen()) { navigator ->
        FadeTransition(navigator)
    }
}