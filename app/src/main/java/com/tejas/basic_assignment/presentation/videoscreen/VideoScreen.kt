package com.tejas.basic_assignment.presentation.videoscreen

import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.skydoves.landscapist.rememberDrawablePainter
import com.tejas.basic_assignment.presentation.homescreen.HomeScreenViewModel

@Composable
fun VideoScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navController: NavController
){

    val videoDetails = viewModel.videoDetails
    val context = LocalContext.current
    var creatorAvatar by remember { mutableStateOf<Drawable?>(null) }
    Glide.with(context)
        .load(videoDetails!!.creator_avatar_url)
        .into(object: CustomTarget<Drawable>(){
            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                creatorAvatar = resource
            }
            override fun onLoadCleared(placeholder: Drawable?) {}
        })
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ){
        VideoPlayer(videoUri = Uri.parse(videoDetails.video_url))
        Spacer(modifier = Modifier.height(2.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, start = 12.dp, end = 12.dp)
        ){
            Box(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(36.dp)
                    )
            ){
                Image(
                    painter = rememberDrawablePainter(drawable = creatorAvatar),
                    contentDescription = null,
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp)
                )
            }
            Column(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            ) {
                Text(
                    text = videoDetails.title,
                    color = Color.White,
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    fontSize = 18.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = videoDetails.creator_name,
                    color = Color.Gray,
                    fontSize = 12.sp
                )
                Text(
                    text = "${videoDetails.views} views • ${videoDetails.upload_time}",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(2.dp))

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

