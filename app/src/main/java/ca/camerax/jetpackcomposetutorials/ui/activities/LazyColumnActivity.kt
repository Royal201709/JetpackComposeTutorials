package ca.camerax.jetpackcomposetutorials.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.camerax.jetpackcomposetutorials.model.User
import ca.camerax.jetpackcomposetutorials.ui.theme.JetpackComposeTutorialsTheme

@ExperimentalMaterialApi
class FlexibleLayoutActivity : ComponentActivity() {

    //@ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhiteSurface {
                val textState = remember { mutableStateOf(TextFieldValue("")) }

                Column {
                    UniversalSimpleTopAppBar(topAppBarTitle = "ListItem in LazyColumn")
                    Divider(modifier = Modifier.padding(all = 10.dp), color = Color.LightGray)
                    UniversalSearchView(state = textState)
                    Divider(modifier = Modifier.padding(all = 10.dp), color = Color.LightGray)
                    FillNamesList(userList = getUserList(state = textState))
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun FillNamesList(userList: MutableList<User>) {

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(userList) { user ->
            NamesListItem(user = user)
            Divider(
                modifier = Modifier.padding(all = 10.dp),
                color = Color.LightGray
            )
        }
    }
}


@ExperimentalMaterialApi
@Composable
fun NamesListItem(user: User) {
    var isSelected by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(if (isSelected) Color.Red else Color.Transparent)

    ListItem(
        Modifier
            .background(color = backgroundColor)
            .clickable(onClick = { isSelected = !isSelected }),
        text = { UserName(name = user.userFullName) },
        secondaryText = { UserName(name = user.transactionStatus) },
    )
}

@Composable
fun UserName(name: String) {
    Text(text = "Hello $name!")
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    JetpackComposeTutorialsTheme {
        NamesListItem(User(userFullName = "Android",transactionStatus = "dsdsds",transactionDate = "sdsdsd"))
    }
}