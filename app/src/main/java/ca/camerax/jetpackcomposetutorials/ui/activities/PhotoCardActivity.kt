package ca.camerax.jetpackcomposetutorials.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.camerax.jetpackcomposetutorials.R


class PhotoCardActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SetupScaffold(
                topBar = universalScaffoldTopAppBar(topAppBarTitle = "Remember Coroutine Scope")
            ) {
                ShowPhotoCardAndTextView()
            }
        }
    }
}
    @Composable
    fun ShowPhotoCardAndTextView() {
        Column {
            PhotoCard()
            Spacer(modifier = Modifier.padding(top = 20.dp))

            TwoTextView(text1 = "First Textview", text2 = "Second Textview")
        }
    }

    @Composable
    fun PhotoCard(modifier: Modifier = Modifier) {
        Row(modifier
            .padding(8.dp)//margin
            .clip(RoundedCornerShape(4.dp))
            .background(color = MaterialTheme.colors.surface)
            .clickable { }
            .padding(16.dp)//padding
        ) {
            Surface(
                modifier = Modifier
                    .padding(0.dp)
                    .size(50.dp)
                    .padding(5.dp),
                shape = CircleShape,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
            ) {
                CircularImageView(painter = painterResource(R.drawable.img))
            }

            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = "Creating Photo Card", fontWeight = FontWeight.Bold)
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(text = "3 minutes ago", style = MaterialTheme.typography.body2)
                }
            }
        }
    }

    @Composable
    fun TwoTextView(modifier: Modifier = Modifier, text1: String, text2: String) {
        Row(modifier = modifier.height(IntrinsicSize.Min)) {
            Text(
                text = text1,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 4.dp)
                    .wrapContentWidth(Alignment.Start)
            )

            Divider(
                color = Color.LightGray, modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
            )

            Text(
                text = text2,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 4.dp)
                    .wrapContentWidth(Alignment.End)
            )
        }
    }


    @Preview
    @Composable
    fun PreviewPhotoCard() {
        PhotoCard()
    }

    @Preview
    @Composable
    fun PreviewTextViews() {
        TwoTextView(text1 = "First Textview", text2 = "Second Textview")
    }
