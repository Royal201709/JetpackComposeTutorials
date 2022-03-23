package ca.camerax.jetpackcomposetutorials.ui.activities

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.camerax.jetpackcomposetutorials.R
import ca.camerax.jetpackcomposetutorials.model.Message

class BasicUIComponentView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DisplayName(Message("Android", "JetPack component"))
        }
    }

    @Composable
    fun DisplayName(message: Message) {
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
            Column {
                Text(
                    text = message.auther,
                    color = MaterialTheme.colors.secondary,
                    style = MaterialTheme.typography.subtitle1
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = message.body, style = MaterialTheme.typography.subtitle2)
            }
        }

    }

    @Preview(name = "Light Mode")
    @Preview(
        name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true
    )
    @Composable
    fun PreviewDisplayName() {
        DisplayName(Message("Team", "you should start jetpack UI component"))
    }
}