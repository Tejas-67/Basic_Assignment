package com.tejas.basic_assignment

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tejas.basic_assignment.presentation.homescreen.HomeScreen

@Composable
fun VideoApp(
    navController: NavHostController = rememberNavController()
){
    Scaffold {
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ){
            composable(route = "home"){
                HomeScreen()
            }
        }
    }
}