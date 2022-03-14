package ca.camerax.jetpackcomposetutorials.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import ca.camerax.jetpackcomposetutorials.model.DataProvider
import coil.compose.rememberImagePainter


@ExperimentalMaterialApi
class MaterialThemeScaffold : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SetupScaffold(
                topBar = universalScaffoldTopAppBar(
                    topAppBarTitle = "Scaffold TopAppBar",
                    actions = topAppbarActions()
                )
            ) {
                BodyContent()
            }
        }
    }
}

@Composable
fun SetupScaffold(
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = topBar,
        bottomBar = bottomBar
    ) {
        content()
    }
}

@ExperimentalMaterialApi
@Composable
fun universalScaffoldTopAppBar(
    topAppBarTitle: String,
    navigationIcon: @Composable (() -> Unit) = topAppbarSimpleBackNavigationAction(),
    actions: @Composable (RowScope.() -> Unit) = {},
): @Composable () -> Unit {

    return {
        TopAppBar(
            /* modifier = Modifier
                 .padding(horizontal = 8.dp, vertical = 8.dp)
                 .fillMaxWidth(),*/
            title = {
                Text(
                    text = topAppBarTitle,
                    style = MaterialTheme.typography.h6,
                    color = Color.Black
                )
            },
            navigationIcon = navigationIcon,
            actions = actions,
            backgroundColor = Color.White,
            elevation = 8.dp,
            )
    }
}

@Composable
fun topAppbarSimpleBackNavigationAction(): @Composable (() -> Unit) {
    return {
        val context = (LocalContext.current as? Activity)
        IconButton(onClick = {
            context?.onBackPressed()
            Toast.makeText(context, "Back button pressed", Toast.LENGTH_SHORT).show()
        }) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Back Button")
        }
    }
}

@Composable
fun topAppbarActions(): @Composable (RowScope.() -> Unit) {
    return {
        val context = LocalContext.current
        IconButton(onClick = {
            context.startActivity(Intent(context, CustomLayoutActivity::class.java))
        }) {
            Icon(Icons.Filled.Favorite, contentDescription = "Favorite icon")
        }
        IconButton(onClick = {
            context.startActivity(Intent(context, ConstraintLayoutActivity::class.java))
        }) {
            Icon(Icons.Filled.Email, contentDescription = "Email Icon")
        }
        IconButton(onClick = {
            context.startActivity(
                Intent(
                    context,
                    LazyColumnWithCoroutine::class.java
                )
            )
        }) {
            Icon(Icons.Filled.Settings, contentDescription = "Settings Icon")
        }
        IconButton(onClick = {
            context.startActivity(Intent(context, CustomColumnActivity::class.java))
        }) {
            Icon(Icons.Filled.Home, contentDescription = "Home Icon")
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun BodyContent() {
    Column {
        Text(text = "Hi there")
        Text(
            text = "We fetch image from remote server to listview. We use just Column compose " +
                    "instead of Lazy Column"
        )
        SimpleList()
    }
}

@ExperimentalMaterialApi
@Composable
fun SimpleList() {
    val scrollState = rememberScrollState()
    val transactionList = remember { DataProvider.userList }
    val context = LocalContext.current
    Column(Modifier.verticalScroll(scrollState)) {
        repeat(transactionList.size) { index ->
            UserListItem(
                user = transactionList[index], painter = rememberImagePainter(
                    data = "https://developer.android.com/images/brand/Android_Robot.png"
                )
            ) {
                onItemClick(index, context)
            }
        }
    }
}