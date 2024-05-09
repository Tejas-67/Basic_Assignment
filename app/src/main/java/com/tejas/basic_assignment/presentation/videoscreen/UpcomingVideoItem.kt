package com.tejas.basic_assignment.presentation.videoscreen

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.skydoves.landscapist.rememberDrawablePainter
import com.tejas.basic_assignment.R
import com.tejas.basic_assignment.model.VideoDetails

@Composable
fun UpcomingVideoItem(
    item: VideoDetails,
    onClick: (VideoDetails) -> Unit
){
    var image by remember { mutableStateOf<Drawable?>(null) }

    val context = LocalContext.current
    Glide.with(context)
        .load(item.creator_avatar_url)
        .into(object: CustomTarget<Drawable>(){
            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                image = resource
            }
            override fun onLoadCleared(placeholder: Drawable?){}

        })

    Row(
        modifier = Modifier.fillMaxWidth()
            .clickable { onClick(item) }
            .padding(16.dp)
    ){

        Image(
            painter = rememberDrawablePainter(drawable = image),
            contentDescription = null,
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth(.4f)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier.padding(start = 12.dp, end = 16.dp)
        ){
            Text(
                text = item.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 16.sp,
                style = TextStyle(fontWeight = FontWeight.Bold),
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${item.views} views • ${item.upload_time}",
                fontSize = 14.sp,
                color = Color.Gray,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}