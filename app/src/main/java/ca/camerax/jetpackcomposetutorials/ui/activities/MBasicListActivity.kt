package ca.camerax.jetpackcomposetutorials.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.camerax.jetpackcomposetutorials.R
import ca.camerax.jetpackcomposetutorials.model.DataProvider
import ca.camerax.jetpackcomposetutorials.model.Message


class MBasicListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Conversation(DataProvider.messageList)
            // DisplayName(Message("Android", "JetPack component"))
        }
    }

    @Composable
    fun DisplayName(pos: Int,message: Message) {
        Surface(
            shape = MaterialTheme.shapes.small,
            elevation = 1.dp,
            modifier = Modifier
                .padding(all = 8.dp)
                .clickable {onItemClicked(pos,message.auther) },
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

                    Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp,modifier = Modifier.clickable { isExpanded = !isExpanded }) {
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
    fun Conversation(messages: List<Message>) {
        LazyColumn {
            itemsIndexed(messages) { index,message ->
                DisplayName(index,message = message)
            }
        }
    }

    @Preview
    @Composable
    fun PreviewConversation() {
        Conversation(DataProvider.messageList)
    }

    private fun onItemClicked(pos:Int ,msg: String) {
        Toast.makeText(applicationContext, "Clicked Item position $pos", Toast.LENGTH_LONG).show()
    }

}