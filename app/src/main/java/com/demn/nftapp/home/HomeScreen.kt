package com.demn.nftapp.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.demn.nftapp.R
import com.demn.nftapp.shared.ui.theme.Black100
import com.demn.nftapp.shared.ui.theme.Black40
import com.demn.nftapp.shared.ui.theme.museoModernFamily
import eu.bambooapps.material3.pullrefresh.PullRefreshIndicator
import eu.bambooapps.material3.pullrefresh.pullRefresh
import eu.bambooapps.material3.pullrefresh.rememberPullRefreshState
import kotlinx.coroutines.CoroutineScope
import org.koin.compose.koinInject

class HomeScreen : Screen {
    @OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val vm = koinInject<HomeScreenViewModel>()
        val state by vm.uiState.collectAsState()
        val coroutineScope = rememberCoroutineScope()
        val pagerState = rememberPagerState(initialPage = 0) { 5 }
        val bottomNavigationButtonsState by remember { mutableStateOf(BottomNavigationButtonsState()) }
        val pullRefreshState = rememberPullRefreshState(refreshing = state.isLoading, onRefresh = {
            vm.refreshNftsAndCategories()
        })

        val homeScreenCategoryTabs = (state as? HomeUiState.HasCategoriesAndNfts)?.categories?.map {
            CategoryTabSwitcherTab(it.id, it.name)
        } ?: listOf()

        Box {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(
                        start = 25.dp,
                        top = 50.dp
                    )
                    .pullRefresh(pullRefreshState)
            ) {
                ScreenContent(
                    state,
                    vm,
                    coroutineScope,
                    homeScreenCategoryTabs,
                    pagerState,
                    bottomNavigationButtonsState
                )
            }

            PullRefreshIndicator(
                refreshing = state.isLoading,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun ColumnScope.ScreenContent(
        state: HomeUiState,
        vm: HomeScreenViewModel,
        coroutineScope: CoroutineScope,
        homeScreenCategoryTabs: List<CategoryTabSwitcherTab>,
        pagerState: PagerState,
        bottomNavigationButtonsState: BottomNavigationButtonsState
    ) {
        Header()

        Spacer(Modifier.height(25.dp))

        SearchBar(
            value = state.searchBarValue,
            onValueChange = { vm.updateSearchBarValue(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 25.dp)
        )

        Spacer(Modifier.height(25.dp))

        if (state.isLoading || state is HomeUiState.NoCategoriesAndNfts) {
            NoNftsBox()
        } else {
            CategoriesAndNfts(coroutineScope, homeScreenCategoryTabs, pagerState, state)
        }

        Spacer(Modifier.height(25.dp))

        BottomNavigationButtons(
            bottomNavigationButtonsState = bottomNavigationButtonsState,
            onActivityButtonClick = {}, // TODO
            onHomeButtonClick = {}, // TODO
            onNotificationButtonClick = {}, // TODO
            onProfileButtonClick = {}, // TODO
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .padding(end = 25.dp)
        )

        Spacer(Modifier.height(25.dp))
    }

    @Composable
    private fun ColumnScope.NoNftsBox() {
        Box(
            Modifier.Companion
                .weight(1f)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.no_nfts_to_show_right_now),
                color = Black40,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun ColumnScope.CategoriesAndNfts(
        coroutineScope: CoroutineScope,
        homeScreenCategoryTabs: List<CategoryTabSwitcherTab>,
        pagerState: PagerState,
        state: HomeUiState
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            CategoryTabSwitcher(
                coroutineScope = coroutineScope,
                tabs = homeScreenCategoryTabs,
                pagerState = pagerState,
                modifier = Modifier
            )

            Spacer(Modifier.height(25.dp))

            if (state is HomeUiState.HasCategoriesAndNfts) {
                HorizontalNftPager(pagerState, state)
            }
        }
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun HorizontalNftPager(
    pagerState: PagerState,
    state: HomeUiState.HasCategoriesAndNfts
) {
    HorizontalPager(
        pagerState,
        userScrollEnabled = false,
        modifier = Modifier
    ) { pageNumber ->
        LazyRow(
            Modifier.fillMaxWidth()
        ) {
            val nfts = state.nfts.filter {
                it.categoryId == pageNumber
            }

            if (nfts.isEmpty()) {
                item {
                    Text(
                        text = stringResource(R.string.no_nfts_in_this_category),
                        color = Black40
                    )
                }
            }

            items(nfts) { nft ->
                NftCard(
                    imageUrl = nft.imageUrl,
                    nftName = nft.name,
                    nftCostInEth = nft.cost.toString(),
                    "${nft.cost * 2978}",
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .width(220.dp)
                        .height(340.dp)
                )
            }
        }
    }
}

@Composable
private fun Header() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 25.dp)
    ) {
        Text(
            stringResource(R.string.discover_nft),
            fontFamily = museoModernFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 43.sp,
            lineHeight = 65.sp
        )

        IconButton(onClick = { TODO() }) {
            Icon(
                painter = painterResource(R.drawable.icon_activity),
                contentDescription = null,
                tint = Black100
            )
        }
    }
}