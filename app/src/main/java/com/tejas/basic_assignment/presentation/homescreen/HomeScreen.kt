package com.tejas.basic_assignment.presentation.homescreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navController: NavController
){
    val state = viewModel.state
    val refreshState = rememberSwipeRefreshState(isRefreshing = viewModel.state.isLoading)
    viewModel.getVideoDetails()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ){
        OutlinedTextField(
            value = viewModel.searchText,
            onValueChange = {
                            viewModel.search(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(50.dp),
            placeholder = {
                Text(
                    text = "Search for creator/title",
                    color = Color.White
                )
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.White),
            shape = RoundedCornerShape(24.dp),
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.White,
                focusedContainerColor = Color.DarkGray,
                unfocusedContainerColor = Color.DarkGray,
                cursorColor = Color.White
            )
        )
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
                    VideoItem(
                        item = item,
                        onClick = {
                            viewModel.videoDetails = it
                            navController.navigate("video")
                        }
                    )
                }
            }
        }
    }

}
@Composable
@Preview
fun onlyPreview(){
    OutlinedTextField(
        value = "Kuch search karo",
        onValueChange = {
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        placeholder = {
            Text(text = "Search....")
        },
        maxLines = 1,
        singleLine = true,
        shape = RoundedCornerShape(24.dp)
    )
}