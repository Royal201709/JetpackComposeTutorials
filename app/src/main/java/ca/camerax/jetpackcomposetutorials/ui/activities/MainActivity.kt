package ca.camerax.jetpackcomposetutorials.ui.activities

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import ca.camerax.jetpackcomposetutorials.model.DataProvider
import ca.camerax.jetpackcomposetutorials.model.User
import ca.camerax.jetpackcomposetutorials.ui.theme.JetpackComposeTutorialsTheme
import ca.camerax.jetpackcomposetutorials.util.CommonComposeFunctions.startTargetActivity
import java.util.*
import ca.camerax.jetpackcomposetutorials.R

@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhiteSurface {
                val textState = remember { mutableStateOf(TextFieldValue("")) }
                Column(modifier = Modifier.fillMaxWidth()) {
                    UniversalSimpleTopAppBar("All Activities")
                    Spacer(modifier = Modifier.width(10.dp))
                    UniversalSearchView(state = textState)
                    Spacer(modifier = Modifier.width(10.dp))
                    FillUserList(
                        getUserList(state = textState),
                        /*content = {
                            UserListItem(user = User())
                        }*/
                    )
                }
            }
        }
    }
}

@Composable
fun WhiteSurface(content: @Composable () -> Unit) {
    JetpackComposeTutorialsTheme(darkTheme = false) {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            color = Color.White,
            contentColor = contentColorFor(Color.White)
        ) {
            content()
        }
    }
}

@Composable
fun UniversalSimpleTopAppBar(topAppBarTitle: String) {

    val context = (LocalContext.current as Activity)

    TopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        modifier = Modifier.fillMaxWidth()
    ) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {

            val (backIcon, title) = createRefs()

            //TopAppBar Content
            IconButton(
                onClick = {
                    context.onBackPressed()
                    Toast
                        .makeText(context, "Back Button Clicked", Toast.LENGTH_SHORT)
                        .show()
                },
                enabled = true,
                modifier = Modifier
                    .constrainAs(backIcon) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                    contentDescription = "Back",
                )
            }

            //topAppBarTitle
            Text(
                modifier = Modifier
                    .constrainAs(title) {
                        top.linkTo(backIcon.top)
                        bottom.linkTo(backIcon.bottom)
                        start.linkTo(backIcon.end)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5,
                maxLines = 1,
                text = topAppBarTitle,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
fun UniversalSearchView(state: MutableState<TextFieldValue>) {
    OutlinedTextField(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        //materialIcon(),
        value = state.value,
        textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
        onValueChange = { newValue ->
            state.value = newValue
        },
        leadingIcon = {
            Icon(
                Icons.Default.Search,//painter = painterResource(R.drawable.ic_baseline_search_24),
                contentDescription = "Search view icon",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        state.value =
                            TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        label = { Text("Search") },
        //shape = RectangleShape, // The TextFiled has rounded corners top left and right by default
        shape = RoundedCornerShape(6.dp),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.Black,
            cursorColor = Color.Black,
            leadingIconColor = Color.Black,
            trailingIconColor = Color.Black,
            backgroundColor = colorResource(id = R.color.white),
            focusedBorderColor = Color.DarkGray,
            unfocusedBorderColor = Color.Gray
            /*focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent*/
        )
    )
}

@ExperimentalMaterialApi
@Composable
fun FillUserList(
    userList: MutableList<User>,
    //content: @Composable (user:User) -> Unit
) {
    val context = LocalContext.current
    // We save the scrolling position with this state that can also be used to programmatically scroll the list
    val scrollState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        state = scrollState,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        itemsIndexed(userList) { index, userItem ->
            //content(userItem)
            UserListItem(user = userItem, painter = painterResource(R.drawable.img), index) {
                onItemClick(selectedItemIndex = index, context = context)
            }

        }
    }
}

@Composable
fun getUserList(state: MutableState<TextFieldValue>): MutableList<User> {
    val transactionList = remember { DataProvider.userList }

    val searchedText = state.value.text
    return if (searchedText.isEmpty()) {
        transactionList
    } else {
        val resultList = ArrayList<User>()
        for (transaction in transactionList) {
            if (transaction.userFullName.lowercase(Locale.getDefault())
                    .contains(searchedText.lowercase(Locale.getDefault()))
            ) {
                resultList.add(transaction)
            }
        }
        resultList
    }
}

@ExperimentalMaterialApi
fun onItemClick(selectedItemIndex: Int = 0, context: Context) {

    when (selectedItemIndex) {
        1 -> {
            context.startTargetActivity(VerticalAndHorizontalScrollingActivity())
        }
        2 -> {
            context.startTargetActivity(StateInComposeActivity())
        }
        3 -> {
            context.startTargetActivity(MaterialThemeScaffold())
        }
        4 -> {
            context.startTargetActivity(CustomLayoutActivity())
        }
        5 -> {
            context.startTargetActivity(BottomNavigationActivity())
        } else -> {
        context.startTargetActivity(BottomNavigationActivity())
        }
    }
}


@Composable
fun UserListItem(
    user: User,
    painter: Painter,
    selectedItemIndex: Int = 0,
    OnItemClick: (Int) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 4.dp),
        elevation = 4.dp,
        backgroundColor = Color.LightGray,
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
    ) {
        Surface(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .clickable(onClick = {
                    OnItemClick(selectedItemIndex)
                }),
            shape = MaterialTheme.shapes.medium,
            elevation = 1.dp
        ) {
            Row(
                modifier = Modifier
                    .padding(all = 10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                /**
                 * In order to show place holder while the picture is loading I use a Surface where we specify a circle shape and the placeholder color
                 * */
                /*Surface(
                    modifier = Modifier
                        .size(56.dp)
                        .padding(1.dp)// margin
                        .background(color = Color.Black)
                        .clip(CircleShape)
                        .padding(10.dp),// real padding
                    shape = CircleShape,
                    color = Color.Black//MaterialTheme.colors.surface//onSurface.copy(alpha = 0.2f)
                ) {
                    CircularImageView(size = 56.dp, painter = painter)
                }*/
                CircularImageView(size = 56.dp, painter = painter)
                //Add a horizontal space between the image and the column
                Spacer(modifier = Modifier.width(8.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                ) {

                    Text(text = user.userFullName)
                    //Add a vertical space between the author and message texts
                    Row {

                        /**
                         * Modifier.alignByBaseline() added to below Texts for aligning them in the same line(parallel)
                         * */
                        Text(modifier = Modifier.alignByBaseline(), text = user.transactionStatus)
                        Spacer(modifier = Modifier.width(8.dp))
                        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                            Text(
                                modifier = Modifier.alignByBaseline(),
                                text = user.transactionDate,
                                style = MaterialTheme.typography.body2
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CircularImageView(size: Dp = 0.dp, painter: Painter) {
    Image(
        painter = painter,
        contentDescription = "Contact profile picture",
        modifier = Modifier
            //Set image size to 40 dp
            .size(size = size)
            //Clip image to be shaped as a circle
            .clip(CircleShape)
    )
}


@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    val context = LocalContext.current
    JetpackComposeTutorialsTheme {
        Column(modifier = Modifier.fillMaxWidth()) {
            UniversalSimpleTopAppBar("All Activities")
            Spacer(modifier = Modifier.width(10.dp))
            UniversalSearchView(state = textState)
            Spacer(modifier = Modifier.width(10.dp))
            UserListItem(
                User(
                    userFullName = "Royal Lachinov",
                    transactionStatus = "processing",
                    transactionDate = "22/08/2021"
                ),
                painter = painterResource(R.drawable.img)
            ) {
                onItemClick(0, context)
            }
        }
    }
}