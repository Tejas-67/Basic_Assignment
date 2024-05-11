package com.tejas.basic_assignment.presentation.homescreen

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.skydoves.landscapist.rememberDrawablePainter
import com.tejas.basic_assignment.R
import com.tejas.basic_assignment.model.VideoDetails

@Composable
fun VideoItem(
    item: VideoDetails,
    onClick: (VideoDetails) -> Unit
){
    var image by remember {mutableStateOf<Drawable?>(null)}
    val context = LocalContext.current
    var creatorAvatar by remember { mutableStateOf<Drawable?>(null) }
    Glide.with(context)
        .load(item.creator_avatar_url)
        .into(object: CustomTarget<Drawable>(){
            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                creatorAvatar = resource
            }
            override fun onLoadCleared(placeholder: Drawable?) {}
        })

    Glide.with(context)
        .load(item.creator_avatar_url)
        .into(object: CustomTarget<Drawable>(){
            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                image = resource
            }
            override fun onLoadCleared(placeholder: Drawable?) {}
        })

    Box(
        modifier = Modifier.fillMaxWidth()
            .background(color = Color.Black)
            .clickable{ onClick(item) }
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 2.dp, bottom = 20.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
            ){
                Image(
                    painter = rememberDrawablePainter(drawable = image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.FillBounds
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
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
                        text = item.title,
                        color = Color.White,
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        fontSize = 18.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = item.creator_name,
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                    Text(
                        text = "${item.views} views • ${item.upload_time}",
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }
            }

        }
    }
}