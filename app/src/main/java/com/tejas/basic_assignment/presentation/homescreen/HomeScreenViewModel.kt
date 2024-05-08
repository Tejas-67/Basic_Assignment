package com.tejas.basic_assignment.presentation.homescreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tejas.basic_assignment.model.VideoDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration

@HiltViewModel
class HomeScreenViewModel @Inject constructor(): ViewModel() {

    var state by mutableStateOf(
        HomeScreenState()
    )

    private var supabase: SupabaseClient = createSupabaseClient(
        supabaseUrl =  "https://evtqpwlufaaskddbjypw.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImV2dHFwd2x1ZmFhc2tkZGJqeXB3Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTUxNjU3NTEsImV4cCI6MjAzMDc0MTc1MX0.hEYUdWhzQNHGzoScx0aOgOffPhtXEwwNhgRsZGwfhmQ"
    ){
        install(Postgrest)
        this.requestTimeout = Duration.parse("PT1M")
    }

    fun getVideoDetails(){
        _getVideoDetails()
    }

    private fun _getVideoDetails(){
        state = state.copy(isLoading = false)
        viewModelScope.launch{
            val result = supabase.from("videos_basicAssignment")
                .select()
            Log.w("supabase", "data: ${result.data}")
            val data = result.decodeList<VideoDetails>()
            Log.w("supabase", data.toString())
            state = state.copy(videoInfos = data, isLoading = false)
        }
    }
}