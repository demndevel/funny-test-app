package com.demn.nftapp.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.demn.nftapp.ui.theme.Black40
import com.demn.nftapp.ui.theme.NFTAppTheme
import com.demn.nftapp.ui.theme.Purple
import com.demn.nftapp.ui.theme.sfCompactDisplayFamily
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

data class CategoryTabSwitcherTab(
    val id: Int,
    val text: String
)

@Composable
fun CategoryTabSwitcherItem(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    val textColor = when (selected) {
        true -> Purple
        false -> Black40
    }
    Text(
        text,
        fontFamily = sfCompactDisplayFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 25.sp,
        color = textColor,
        modifier = modifier
            .padding(vertical = 14.dp)
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryTabSwitcher(
    coroutineScope: CoroutineScope,
    tabs: List<CategoryTabSwitcherTab>,
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        indicator = @Composable { tabPositions ->
            TabRowDefaults.PrimaryIndicator(
                Modifier
                    .tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                height = 3.dp,
                color = Purple,
                width = 90.dp
            )
        },
        containerColor = Color.Unspecified,
        modifier = modifier,
        edgePadding = 0.dp
    ) {
        tabs.forEach { tab ->
            val selected = pagerState.currentPage == tab.id
            Tab(
                selected = selected,
                onClick = { coroutineScope.launch { pagerState.animateScrollToPage(tab.id) } },
                modifier = Modifier.padding(horizontal = 30.dp)
            ) {
                CategoryTabSwitcherItem(
                    text = tab.text,
                    selected = selected
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun CategoryTabSwitcherPreview() {
    NFTAppTheme {
        Box(Modifier.height(500.dp)) {
            val coroutineScope = rememberCoroutineScope()
            val pagerState = rememberPagerState(initialPage = 0) { 3 }

            CategoryTabSwitcher(
                coroutineScope,
                tabs = listOf(
                    CategoryTabSwitcherTab(0, "Trending"),
                    CategoryTabSwitcherTab(1, "Collectibles"),
                    CategoryTabSwitcherTab(2, "Premium Dubai")
                ),
                pagerState,
                Modifier.align(Alignment.TopCenter)
            )

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.align(Alignment.BottomCenter)
            ) { page ->
                Text(
                    text = "Page: $page",
                    color = Purple,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}