package com.tejas.basic_assignment.presentation.homescreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel()
){
    val state = viewModel.state
    val refreshState = rememberSwipeRefreshState(isRefreshing = viewModel.state.isLoading)
    viewModel.getVideoDetails()
    Surface(
        modifier = Modifier.fillMaxSize()
    ){
        SwipeRefresh(
            state = refreshState,
            onRefresh = {
                viewModel.getVideoDetails()
            }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ){
                items(state.videoInfos.size){ index->
                    val item = state.videoInfos[index]
                    VideoItem(item = item)
                }
            }
        }
    }
}