package ca.camerax.jetpackcomposetutorials.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import ca.camerax.jetpackcomposetutorials.R

class MPresentationDetailsScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SetPresentationLayout()
        }
    }
}

@Composable
fun SetPresentationLayout() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.LightGray)
    ) {
        val (profileImage, playPresentation, presentationTitle, date, location, dateIcon, locationIcon, resource, topSpace, bottomSpace, listIcon, sortIcon, addResource, markAsDone) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = "Profile Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .constrainAs(profileImage) {
                    linkTo(start = parent.start, end = parent.end)
                    top.linkTo(parent.top)
                    width = Dimension.fillToConstraints
                }
                .aspectRatio(2.5f / 1f)
        )
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = "Play",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
                .constrainAs(playPresentation) {
                    top.linkTo(profileImage.bottom)
                    bottom.linkTo(profileImage.bottom)
                    start.linkTo(parent.start, margin = 16.dp)
                }
        )
        Text(
            text = "Test Presentation", maxLines = 1,
            modifier = Modifier.constrainAs(presentationTitle) {
                top.linkTo(profileImage.bottom, 16.dp)
                start.linkTo(playPresentation.end, 16.dp)
            }, style = TextStyle(Color.Black, fontSize = 16.sp)
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_favorite_24),
            contentDescription = "Date",
            modifier = Modifier
                .size(16.dp)
                .constrainAs(dateIcon) {
                    top.linkTo(presentationTitle.bottom, 8.dp)
                    start.linkTo(presentationTitle.start)
                }
        )
        Text(
            text = "Date : 04-04-2022", modifier = Modifier.constrainAs(date) {
                top.linkTo(presentationTitle.bottom, 6.dp)
                start.linkTo(dateIcon.end, 4.dp)
            }, style = TextStyle(Color.Black, fontSize = 14.sp)
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_favorite_24),
            contentDescription = "Date",
            modifier = Modifier
                .size(16.dp)
                .constrainAs(locationIcon) {
                    top.linkTo(dateIcon.bottom, 8.dp)
                    start.linkTo(dateIcon.start)
                }
        )
        Text(
            text = "nagpur,hyderabad", maxLines = 1,
            modifier = Modifier.constrainAs(location) {
                top.linkTo(dateIcon.bottom, 4.dp)
                start.linkTo(locationIcon.end, 4.dp)
            }, style = TextStyle(Color.Black, fontSize = 14.sp)
        )
        Spacer(
            modifier = Modifier
                .constrainAs(topSpace) {
                    top.linkTo(locationIcon.bottom, 16.dp)
                    start.linkTo(parent.start)
                }
                .fillMaxWidth()
                .height(0.5.dp)
                .background(Color.DarkGray)
        )
        Text(
            text = "Resource", maxLines = 1,
            modifier = Modifier
                .padding(start = 8.dp, top = 16.dp, end = 8.dp, bottom = 16.dp)
                .constrainAs(resource) {
                    top.linkTo(topSpace.bottom)
                    start.linkTo(parent.start)
                    width = Dimension.preferredWrapContent
                }, style = TextStyle(Color.Black, fontSize = 16.sp)
        )
        Spacer(
            modifier = Modifier
                .constrainAs(bottomSpace) {
                    top.linkTo(resource.bottom)
                    start.linkTo(parent.start)
                }
                .fillMaxWidth()
                .height(0.5.dp)
                .background(Color.DarkGray)
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_favorite_24),
            contentDescription = "Date",
            modifier = Modifier
                .size(16.dp)
                .constrainAs(listIcon) {
                    top.linkTo(resource.top)
                    bottom.linkTo(resource.bottom)
                    end.linkTo(parent.end, 16.dp)
                }
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_favorite_24),
            contentDescription = "Date",
            modifier = Modifier
                .size(16.dp)
                .constrainAs(sortIcon) {
                    top.linkTo(resource.top)
                    bottom.linkTo(resource.bottom)
                    end.linkTo(listIcon.start, 16.dp)
                }
        )
        OutlinedButton(
            onClick = { },
            border = BorderStroke(1.dp, Color.Blue),

            modifier = Modifier
                .padding(8.dp)
                .constrainAs(addResource) {
                    top.linkTo(bottomSpace.bottom, 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.preferredWrapContent
                }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_favorite_24),
                contentDescription = "Add Resource",
                modifier = Modifier.padding(4.dp)
            )
            Text(text = "Add Resource", modifier = Modifier.offset(4.dp, 0.dp))
        }
        Button(
            onClick = { },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .background(Color.Magenta, shape = RoundedCornerShape(corner = CornerSize(10.dp)))
                .constrainAs(markAsDone) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                }
        ) {
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                    contentDescription = "Mark As Done",
                    modifier = Modifier.padding(4.dp)
                )
                Text(text = "Mark as Done", modifier = Modifier.offset(4.dp, 0.dp))
            }
        }
    }
}

@Preview
@Composable
fun DisplayPresentation() {
    SetPresentationLayout()
}