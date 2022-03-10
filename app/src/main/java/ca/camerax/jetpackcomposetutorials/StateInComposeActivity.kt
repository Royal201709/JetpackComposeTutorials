package ca.camerax.jetpackcomposetutorials

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import ca.camerax.jetpackcomposetutorials.ui.theme.JetpackComposeTutorialsTheme

@ExperimentalMaterialApi
class StateInComposeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhiteSurface {
                FillUI()
            }
        }
    }
}


@ExperimentalMaterialApi
@Composable
fun FillUI() {
    val counterState = remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(Color.Green)
    ) {

        UniversalSimpleTopAppBar(topAppBarTitle = "State in Compose")

        Column(
            modifier = Modifier
                .wrapContentHeight()
                .background(Color.LightGray)
        ) {
            Divider(color = Color.LightGray, modifier = Modifier.padding(all = 10.dp))
            Counter(
                count = counterState.value,
                updateCount = { newCount ->
                    counterState.value = newCount
                })
            Divider(color = Color.LightGray, modifier = Modifier.padding(all = 10.dp))
        }

        Greeting("Android")

        Divider(color = Color.LightGray, modifier = Modifier.padding(all = 10.dp))

        CollectionItem()

        Divider(color = Color.LightGray, modifier = Modifier.padding(all = 10.dp))

        HorizontalUIItems()

    }

}


@Composable
fun CollectionItem() {
    val context = LocalContext.current
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                context.startActivity(Intent(context, LazyColumnAndSimpleListActivity::class.java))
            }
    ) {

        val (
            collectionImgId, favouriteId, collectionTrendingId,
            collectionNameAndDateId, moreOptionsId,
                /*collectionNameId, collectionDateId*/
        ) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = "Collection Item Image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .constrainAs(collectionImgId) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
                .size(width = 150.dp, height = 150.dp)
                .clip(RoundedCornerShape(10.dp)))

        Image(
            painter = painterResource(id = R.drawable.ic_baseline_favorite_24),
            contentDescription = "Favourite Icon",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .constrainAs(favouriteId) {
                    top.linkTo(collectionImgId.top)
                    end.linkTo(collectionImgId.end)
                }
                .size(width = 25.dp, height = 25.dp)
                .padding(top = 10.dp, end = 10.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_baseline_local_fire_department_24),
            contentDescription = "Trending item",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .constrainAs(collectionTrendingId) {
                    top.linkTo(collectionImgId.top)
                    start.linkTo(collectionImgId.start)
                }
                .size(width = 25.dp, height = 25.dp)
                .padding(top = 10.dp, start = 10.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_baseline_more_horiz_24),
            contentDescription = "More Options",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .padding(end = 10.dp)
                .wrapContentHeight()
                .wrapContentWidth()
                .constrainAs(moreOptionsId) {
                    end.linkTo(collectionImgId.end)
                    bottom.linkTo(collectionNameAndDateId.bottom)
                    top.linkTo(collectionNameAndDateId.top)
                }
        )

        Column(modifier = Modifier
            .padding(bottom = 2.dp)
            .constrainAs(collectionNameAndDateId) {
                start.linkTo(collectionTrendingId.start)
                end.linkTo(moreOptionsId.start)
                bottom.linkTo(parent.bottom)
            }) {

            Text(
                text = "Collection name",
                fontSize = 12.sp
            )

            Text(
                text = "Collection date",
                fontSize = 10.sp,
            )
        }
    }
}

@Composable
fun HorizontalUIItems() {

    val stateOfButton = remember{ mutableStateOf(false)}
    val extraPadding = if(stateOfButton.value) 40.dp else 0.dp

    Surface(
        modifier = Modifier.fillMaxWidth().padding(20.dp),
        elevation = 10.dp,
        color = Color.LightGray,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {

        Row(
            modifier = Modifier.padding(20.dp).padding(bottom = extraPadding),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = "First Text",
                    modifier = Modifier.padding(4.dp),
                    color = MaterialTheme.colors.primary
                )
                Text(
                    text = "Second Text",
                    modifier = Modifier.padding(4.dp),
                    color = MaterialTheme.colors.error
                )
            }

            OutlinedButton(onClick = { stateOfButton.value = !stateOfButton.value }, modifier = Modifier.padding(10.dp)) {
                Text(text = if(stateOfButton.value) "Show less" else "Show more", color = Color.Black)
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(
        text = "Hello $name!",
        modifier = Modifier
            .padding(all = 20.dp)
            .wrapContentSize(Alignment.Center),
        textAlign = TextAlign.Center
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {

    val context = LocalContext.current
    Column {
        Button(onClick = { updateCount(count + 1) }, modifier = Modifier.padding(all = 10.dp)) {
            Text("I have clicked $count times")
        }
        Divider(color = Color.LightGray, modifier = Modifier.padding(all = 10.dp))
        Button(onClick = {
            context.startActivity(Intent(context, FlexibleLayoutActivity::class.java))
        }, modifier = Modifier.padding(all = 10.dp)) {
            Text(text = "I have clicked $count times")
        }

        Button(
            onClick = {
                context.startActivity(Intent(context, MaterialThemeScaffold::class.java))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_favorite_24),
                    contentDescription = "Collection Item Image",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .size(width = 24.dp, height = 24.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
                Spacer(
                    modifier = Modifier
                        .padding(all = 4.dp)
                )
                Text("Go to MaterialTheme \"Scaffold\" screen")
            }
        }
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    JetpackComposeTutorialsTheme {
        FillUI()
    }
}