package ca.camerax.jetpackcomposetutorials.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import ca.camerax.jetpackcomposetutorials.R
import ca.camerax.jetpackcomposetutorials.model.DataProvider
import ca.camerax.jetpackcomposetutorials.model.Message

class MConstraintLayoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestConstraintLayoutContent()
            //LargeTextConstraintLayoutContent()
        }
    }

    @Preview
    @Composable
    fun PreviewTestConstraintLayoutContent() {
        //LargeTextConstraintLayoutContent()
        TestConstraintLayoutContent()
    }
}

@Composable
fun TestConstraintLayoutContent() {
        var state by remember { mutableStateOf(0) }
        val titles = listOf("Field Coaching", "My Team", "My Field Coaching Report")
        Column {
            //TabRow used for fixed tab
            ScrollableTabRow(
                selectedTabIndex = state,
                backgroundColor = Color.White,
                contentColor = Color.Magenta
            ) {
                titles.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title) },
                        selected = state == index,
                        onClick = { state = index },
                        selectedContentColor = Color.Magenta,
                        unselectedContentColor = Color.Gray
                    )
                }
            }
            if (titles[state] == "Field Coaching") {
                Conversation(DataProvider.messageList)
            } else {
                Conversation(DataProvider.messageList)
            }
            /* Text(
                 modifier = Modifier.align(Alignment.CenterHorizontally),
                 text = "Text tab ${titles[state]} selected",
                 style = MaterialTheme.typography.body1
             )*/
        }
        /* val (button1, button2, text) = createRefs()
         Button(onClick = { }, modifier = Modifier.constrainAs(button1) {
             top.linkTo(parent.top, 16.dp)
         }) {
             Text(text = "Button 1")
         }
         Text("Text", modifier = Modifier.constrainAs(text) {
             top.linkTo(button1.bottom, 16.dp)
             centerAround(button1.end)
         })
         val barrier = createEndBarrier(button1, text)
         Button(onClick = { }, modifier = Modifier.constrainAs(button2) {
             top.linkTo(parent.top, 16.dp)
             start.linkTo(barrier)
         }) {
             Text(text = "Button 2")
         }*/

}

@Composable
fun LargeTextConstraintLayoutContent() {
    ConstraintLayout {
        val text = createRef()
        val guideline = createGuidelineFromStart(0.5f)
        Text(
            text = "This is a very very very very very very very long text",
            modifier = Modifier.constrainAs(text) {
                linkTo(guideline, parent.end)
                width = Dimension.preferredWrapContent
            })
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        itemsIndexed(messages) { index, message ->
            DisplayName(index, message = message)
        }
    }
}

@Composable
fun DisplayName(pos: Int, message: Message) {
    Surface(
        shape = MaterialTheme.shapes.small,
        elevation = 1.dp,
        modifier = Modifier
            .padding(all = 8.dp)
            .clickable { /*onItemClicked(pos, message.auther)*/ },
        color = MaterialTheme.colors.secondaryVariant
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            var isExpanded by remember { mutableStateOf(false) }

            Column {
                Text(
                    text = message.auther,
                    color = MaterialTheme.colors.secondary,
                    style = MaterialTheme.typography.subtitle1
                )
                Spacer(modifier = Modifier.height(2.dp))

                Surface(
                    shape = MaterialTheme.shapes.medium,
                    elevation = 1.dp,
                    modifier = Modifier.clickable { isExpanded = !isExpanded }) {
                    Text(
                        text = message.body,
                        modifier = Modifier.padding(all = 4.dp),
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        style = MaterialTheme.typography.subtitle2
                    )
                }
            }
        }
    }
}