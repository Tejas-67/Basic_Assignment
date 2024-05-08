package com.tejas.basic_assignment.presentation.homescreen

import com.tejas.basic_assignment.model.VideoDetails

data class HomeScreenState(
    val videoInfos: List<VideoDetails> = listOf(),
    val isLoading: Boolean = false,
    val error: String? = null
)
