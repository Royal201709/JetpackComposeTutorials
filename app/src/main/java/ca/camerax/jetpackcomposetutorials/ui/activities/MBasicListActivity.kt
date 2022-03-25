package ca.camerax.jetpackcomposetutorials.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.camerax.jetpackcomposetutorials.R
import ca.camerax.jetpackcomposetutorials.model.DataProvider
import ca.camerax.jetpackcomposetutorials.model.Message

@ExperimentalFoundationApi
class MBasicListActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConversationGrid(DataProvider.messageList)
            // DisplayName(Message("Android", "JetPack component"))
        }
    }

    @Composable
    fun displayGridItem(message: Message) {
        Card(
            elevation = 5.dp,
            modifier = Modifier
                .padding(8.dp, 8.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(10.dp))
        ) {
            Box(modifier = Modifier.height(200.dp)) {
                Image(
                    painterResource(id = R.drawable.img),
                    contentDescription = "Profile",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black
                                ),
                                startY = 0f,
                            ),
                            alpha = 0.5f
                        )
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = message.auther,
                            style = TextStyle(color = Color.White, fontSize = 16.sp),
                            maxLines = 2
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp, 4.dp, 8.dp, 4.dp),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Image(
                        painterResource(id = R.drawable.ic_baseline_more_horiz_24),
                        contentDescription = "Profile",
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Image(
                        painterResource(id = R.drawable.ic_baseline_favorite_24),
                        contentDescription = "Profile",
                        contentScale = ContentScale.Crop,
                    )
                }
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
                .clickable { onItemClicked(pos, message.auther) },
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

    /* @Preview(name = "Light Mode")
     @Preview(
         name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES,
         showBackground = true
     )
     @Composable
     fun PreviewDisplayName() {
         DisplayName(Message("Team", "you should start jetpack UI component"))
     }*/

    @Composable
    fun ConversationGrid(messages: List<Message>) {
        val listState = rememberLazyListState()
        LazyVerticalGrid(cells = GridCells.Fixed(2), state = listState) {
            items(messages) { message ->
                displayGridItem(message = message)
            }
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


    @Preview
    @Composable
    fun PreviewConversation() {
        ConversationGrid(DataProvider.messageList)
        //Conversation(DataProvider.messageList)
    }

    /* @Preview
     @Composable
     fun PreviewConversation() {
         Conversation(DataProvider.messageList)
     }*/

    private fun onItemClicked(pos: Int, msg: String) {
        Toast.makeText(applicationContext, "Clicked Item position $pos", Toast.LENGTH_LONG).show()
    }

}