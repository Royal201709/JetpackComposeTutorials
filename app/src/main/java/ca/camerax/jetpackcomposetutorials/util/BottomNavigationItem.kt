package ca.camerax.jetpackcomposetutorials.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Created by Royal Lachinov on 2022-03-12.
 */
sealed class BottomNavigationItem(var route: String, var icon: ImageVector, var title: String) {
    object Home : BottomNavigationItem("home", Icons.Filled.Home, "Home")
    object Learn : BottomNavigationItem("learn", Icons.Filled.ModelTraining, "Learn")
    object Resources : BottomNavigationItem("resources", Icons.Filled.LocalPostOffice, "Resource")
    object Present : BottomNavigationItem("present", Icons.Filled.FilePresent, "Present")
    object More : BottomNavigationItem("more", Icons.Filled.More, "More")

}
