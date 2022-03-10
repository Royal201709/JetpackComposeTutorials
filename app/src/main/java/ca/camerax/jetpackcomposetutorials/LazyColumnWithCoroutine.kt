package ca.camerax.jetpackcomposetutorials

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


class LazyColumnWithCoroutine : ComponentActivity() {

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhiteSurface {
                UniversalScaffoldTopAppBar(topAppBarTitle = "Remember Coroutine Scope"){
                    ScrollingList()
                }
                /*Column {
                    UniversalTopAppBar(topAppBarTitle = "Remember Coroutine Scope")
                    Divider(modifier = Modifier.padding(all = 10.dp), color = Color.LightGray)
                    ScrollingList()
                }*/
            }
        }
    }
}

@Composable
fun ScrollingList() {
    val listSize = 100
    val scrollingState = rememberLazyListState()
    val rememberCoroutine = rememberCoroutineScope()

    Column {
        Row {
            Button(onClick = {
                rememberCoroutine.launch {
                    scrollingState.animateScrollToItem(0)
                }
            }) {
                Text(text = "Scroll to the top")
            }

            Button(onClick = {
                rememberCoroutine.launch {
                    scrollingState.animateScrollToItem(listSize - 1)
                }
            }, modifier = Modifier.padding(start = 20.dp)) {
                Text(text = "Scroll to the end")
            }
        }

        LazyColumn(state = scrollingState) {
            items(listSize) {
                ImageList(item = it)
            }
        }
    }
}