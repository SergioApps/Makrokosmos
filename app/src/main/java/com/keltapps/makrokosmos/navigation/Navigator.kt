package com.keltapps.makrokosmos.navigation

interface Navigator {
    companion object {
        const val ID_ABOUT = 1
        const val ID_AUTHOR = 2
        const val ID_INTERPRETER = 3
    }

    fun openMakrokosmos()
    fun openInfo(id: Int)
}