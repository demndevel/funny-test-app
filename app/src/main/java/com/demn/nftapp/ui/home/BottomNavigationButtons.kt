package com.demn.nftapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.demn.nftapp.R
import com.demn.nftapp.ui.theme.Black3
import com.demn.nftapp.ui.theme.Black40
import com.demn.nftapp.ui.theme.NFTAppTheme
import com.demn.nftapp.ui.theme.Purple

data class BottomNavigationButtonsState(
    val activeBottomNavigationItem: BottomNavigationItems = BottomNavigationItems.Home
)
enum class BottomNavigationItems {
    Home,
    Activity,
    Notification,
    Profile
}

typealias BottomNavigationButtonListener = () -> Unit

@Composable
fun BottomNavigationButtons(
    bottomNavigationButtonsState: BottomNavigationButtonsState,
    modifier: Modifier = Modifier,
    onHomeButtonClick: BottomNavigationButtonListener,
    onActivityButtonClick: BottomNavigationButtonListener,
    onNotificationButtonClick: BottomNavigationButtonListener,
    onProfileButtonClick: BottomNavigationButtonListener,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        BottomNavigationButton(
            R.drawable.icon_home,
            onClick = onHomeButtonClick,
            bottomNavigationButtonsState.activeBottomNavigationItem == BottomNavigationItems.Home
        )

        BottomNavigationButton(
            R.drawable.icon_activity,
            onClick = onActivityButtonClick,
            isActive = bottomNavigationButtonsState.activeBottomNavigationItem == BottomNavigationItems.Activity
        )

        BottomNavigationButton(
            R.drawable.icon_notification,
            onClick = onNotificationButtonClick,
            bottomNavigationButtonsState.activeBottomNavigationItem == BottomNavigationItems.Notification
        )
        BottomNavigationButton(
            R.drawable.icon_profile,
            onClick = onProfileButtonClick,
            isActive = bottomNavigationButtonsState.activeBottomNavigationItem == BottomNavigationItems.Profile
        )
    }
}

@Composable
private fun BottomNavigationButton(
    iconId: Int,
    onClick: () -> Unit,
    isActive: Boolean = false,
) {
    val color = when (isActive) {
        true -> Purple
        false -> Black40
    }

    IconButton(onClick = { onClick() }) {
        Icon(
            painterResource(iconId),
            contentDescription = null,
            tint = color
        )
    }
}

@Preview
@Composable
private fun BottomNavigationButtonsPreview() {
    NFTAppTheme {
        Box(
            Modifier
                .fillMaxSize()
                .background(Black3),
            contentAlignment = Alignment.BottomCenter
        ) {
            var bottomNavigationButtonsState by remember {
                mutableStateOf(
                    BottomNavigationButtonsState(BottomNavigationItems.Home)
                )
            }

            BottomNavigationButtons(
                bottomNavigationButtonsState,
                Modifier.fillMaxWidth(),
                onHomeButtonClick = {
                    bottomNavigationButtonsState =
                        BottomNavigationButtonsState(BottomNavigationItems.Home)
                },
                onActivityButtonClick = {
                    bottomNavigationButtonsState =
                        BottomNavigationButtonsState(BottomNavigationItems.Activity)
                },
                onNotificationButtonClick = {
                    bottomNavigationButtonsState =
                        BottomNavigationButtonsState(BottomNavigationItems.Notification)
                },
                onProfileButtonClick = {
                    bottomNavigationButtonsState =
                        BottomNavigationButtonsState(BottomNavigationItems.Profile)
                }
            )
        }
    }
}