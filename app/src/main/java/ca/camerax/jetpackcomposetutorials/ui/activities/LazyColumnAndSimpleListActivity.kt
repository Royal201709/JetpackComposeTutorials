package ca.camerax.jetpackcomposetutorials.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter

/**
 * Created by Royal Lachinov on 2022-03-05.
 */
class LazyColumnAndSimpleListActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SetupScaffold(
                topBar = universalScaffoldTopAppBar(
                    topAppBarTitle = "Simple list",
                    actions = SimpleListTopAppbarActions()
                )
            ) {
                Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
                    ShowAllLists()
                }
            }
        }
    }
}

@Composable
fun SimpleListTopAppbarActions(): @Composable (RowScope.() -> Unit) {
    return {
        val context = LocalContext.current
        IconButton(onClick = {
            context.startActivity(Intent(context, BasicUIActivity::class.java))
        }) {
            Icon(Icons.Filled.Favorite, contentDescription = "Favorite icon")
        }
    }
}

@Composable
fun ShowAllLists() {
    ConstraintLayout {
        val simpleScrollState = rememberScrollState()
        val lazyScrollState = rememberLazyListState()
        val lazyImageScrollState = rememberLazyListState()


        val (simpleList, lazyList, imageList) = createRefs()

        Column(modifier = Modifier
            .verticalScroll(simpleScrollState)
            .constrainAs(simpleList) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
            }) {
            repeat(100) {
                Text(
                    text = "Simple List with column $it",
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }

        LazyColumn(state = lazyScrollState, modifier = Modifier
            .constrainAs(lazyList) {
                top.linkTo(parent.top)
                start.linkTo(simpleList.end)
            }
            .padding(start = 20.dp)) {
            items(100) {
                Text(
                    text = "Simple list with lazy column $it",
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }

        LazyColumn(state = lazyImageScrollState, modifier = Modifier
            .constrainAs(imageList) {
                start.linkTo(lazyList.end)
                top.linkTo(parent.top)
            }
            .padding(start = 20.dp)) {
            items(100) {
                ImageList(item = it)
            }
        }
    }
}


@Composable
fun SimpleListWithColumn() {
    // We save the scrolling position with this state
    val scrollState = rememberScrollState()

    Column(Modifier.verticalScroll(scrollState)) {
        repeat(100) {
            Text(text = "Simple List with column $it", style = MaterialTheme.typography.subtitle1)
        }
    }
}

@Composable
fun SimpleLazyList() {
    // We save the scrolling position with this state
    val scrollState = rememberLazyListState()

    LazyColumn(state = scrollState) {
        items(100) {
            Text(
                text = "Simple list with lazy column $it",
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}

@Composable
fun ImageList(item: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = rememberImagePainter(data = "https://developer.android.com/images/brand/Android_Robot.png"),
            contentDescription = "Android logo",
            modifier = Modifier.size(50.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(text = "ItemIndex is $item", style = MaterialTheme.typography.subtitle1)
    }
}

@Composable
fun FillImageList() {
    // We save the scrolling position with this state
    val scrollState = rememberLazyListState()

    LazyColumn(state = scrollState) {
        items(100) {
            ImageList(item = it)
        }
    }
}
