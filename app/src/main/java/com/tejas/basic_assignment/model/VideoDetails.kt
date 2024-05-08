package com.tejas.basic_assignment.model

import kotlinx.serialization.Serializable

@Serializable
data class VideoDetails(
    val id: Integer,
    val created_at: String,
    val title: String,
    val duration: Integer,
    val creator_name: String,
    val upload_time: String,
    val views: Int,
    val creator_avatar_url: String,
    val video_url: String,
    val description: String
)
