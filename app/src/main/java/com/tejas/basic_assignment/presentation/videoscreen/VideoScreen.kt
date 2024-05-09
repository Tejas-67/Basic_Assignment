package com.tejas.basic_assignment.presentation.videoscreen

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tejas.basic_assignment.presentation.homescreen.HomeScreenViewModel

@Composable
fun VideoScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navController: NavController
){

    val videoDetails = viewModel.videoDetails
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ){
        VideoPlayer(videoUri = Uri.parse(videoDetails!!.video_url))
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text =  videoDetails.title,
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier.padding(start = 12.dp, end = 12.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "${videoDetails.views} views • ${videoDetails.upload_time}",
            color = Color.Gray,
            modifier = Modifier.padding(start = 12.dp, end = 12.dp),
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(12.dp))

        val upcomingList = viewModel.allVideos.filter {
            it.id!=videoDetails.id
        }
        LazyColumn {
            items(upcomingList.size){index ->
                val item = upcomingList[index]
                UpcomingVideoItem(
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

