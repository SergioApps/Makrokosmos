package com.keltapps.musicalzodiacpiano.navigation

import androidx.navigation.NavController

interface Navigator {
    fun openMakrokosmos(navController: NavController)
    fun openAboutInfo(navController: NavController)
    fun openAuthorInfo(navController: NavController)
    fun openInterpreterInfo(navController: NavController)
    fun openSongDetail(navController: NavController, songId: String)
}