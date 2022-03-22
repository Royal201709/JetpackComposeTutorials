package ca.camerax.jetpackcomposetutorials.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalContext

class CustomColumnActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SetupScaffold(
                topBar = universalScaffoldTopAppBar(topAppBarTitle = "Custom Column", actions = SetToolBarMenu())
            ) {
                FillCustomColumn()
            }
        }
    }
}

@Composable
fun SetToolBarMenu(): @Composable (RowScope.() -> Unit) {
    return {
        val context = LocalContext.current
        IconButton(onClick = {
            context.startActivity(Intent(context, PhotoCardActivity::class.java))
        }) {
            Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Favourite Icon")
        }
    }
}


@Composable
fun FillCustomColumn() {
    MyOwnColumn {
        Text("MyOwnColumn")
        Text("places items")
        Text("vertically.")
        Text("We've done it by hand!")
    }
}

@Composable
fun MyOwnColumn(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Layout(modifier = modifier, content = content) { measurables, constraints ->
        val placeables = measurables.map {
            it.measure(constraints)
        }

        var yPosition = 0

        layout(constraints.maxWidth, constraints.maxHeight) {
            placeables.forEach {
                it.placeRelative(x = 0, y = yPosition)
                yPosition += it.height
            }
        }
    }
}