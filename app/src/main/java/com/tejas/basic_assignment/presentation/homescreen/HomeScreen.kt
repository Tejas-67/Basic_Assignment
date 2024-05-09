package com.tejas.basic_assignment.presentation.homescreen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
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
    Column(
        modifier = Modifier.fillMaxSize()
    ){
        OutlinedTextField(
            value = viewModel.searchText,
            onValueChange = {
                            viewModel.search(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = {
                Text(text = "Search....")
            },
            maxLines = 1,
            singleLine = true
        )
        SwipeRefresh(
            state = refreshState,
            onRefresh = {
                viewModel.getVideoDetails()
            }
        ) {
            var count = 0
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