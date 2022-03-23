package ca.camerax.jetpackcomposetutorials.model

/**
 * Created by Royal Lachinov on 2021-08-22.
 */
object DataProvider {
    val userList = mutableListOf(
        User(
            userFullName = "Go to Vertical And Horizontal Scrolling Activity",
            transactionStatus = "processing",
            transactionDate = "22/08/2021"
        ),
        User(
            userFullName = "Go to State In Compose Activity",
            transactionStatus = "done",
            transactionDate = "22/08/2021"
        ),
        User(
            userFullName = "Go to MaterialTheme Scaffold TopAppBar Activity",
            transactionStatus = "pending",
            transactionDate = "22/08/2021"
        ),
        User(
            userFullName = "Go to CustomLayout Activity ",
            transactionStatus = "failed",
            transactionDate = "22/08/2021"
        ),
        User(
            userFullName = "Go To BottomNavigationActivity",
            transactionStatus = "processing",
            transactionDate = "22/08/2021"
        ),
        User(userFullName = "Royal", transactionStatus = "pending", transactionDate = "22/08/2021"),
        User(
            userFullName = "James Lachinov7",
            transactionStatus = "failed",
            transactionDate = "22/08/2021"
        ),
        User(
            userFullName = "Royal Lachinov8",
            transactionStatus = "processing",
            transactionDate = "22/08/2021"
        ),
        User(
            userFullName = "Ronald",
            transactionStatus = "processing",
            transactionDate = "22/08/2021"
        ),
        User(
            userFullName = "Royal Lachinov10",
            transactionStatus = "done",
            transactionDate = "22/08/2021"
        ),
        User(
            userFullName = "Royal Lachinov11",
            transactionStatus = "failed",
            transactionDate = "22/08/2021"
        ),
        User(
            userFullName = "Royal Lachinov12",
            transactionStatus = "pending",
            transactionDate = "22/08/2021"
        ),
        User(
            userFullName = "Royal Lachinov13",
            transactionStatus = "processing",
            transactionDate = "22/08/2021"
        ),
        User(
            userFullName = "Royal Lachinov14",
            transactionStatus = "failed",
            transactionDate = "22/08/2021"
        ),
        User(
            userFullName = "Royal Lachinov15",
            transactionStatus = "processing",
            transactionDate = "22/08/2021"
        ),
        User(
            userFullName = "Royal Lachinov16",
            transactionStatus = "pending",
            transactionDate = "22/08/2021"
        )
    )

    val messageList = mutableListOf(
        Message(
            auther = "Add a text element",
            body = "First, we’ll display a “Hello world!” text by adding a text element inside the onCreate method. You do this by defining a content block, and calling the Text() function"
        ),
        Message(
            auther = "Jetpack Compose",
            body = "Jetpack Compose uses a Kotlin compiler plugin to transform these composable functions into the app's UI elements"
        ),
        Message(
            auther = "Define a composable function",
            body = "To make a function composable, add the @Composable annotation. To try this out, define a MessageCard() function which is passed a name, and uses it to configure the text element."
        ),
        Message(
            auther = "Preview your function in Android Studio",
            body = "Android Studio lets you preview your composable functions within the IDE, instead of installing the app to an Android device or emulator."
        ),
        Message(
            auther = "Add multiple texts",
            body = "So far we’ve built our first composable function and preview!"
        ),
        Message(
            auther = "Using a Column",
            body = "The Column function lets you arrange elements vertically. Add Column to the MessageCard() function.\n" +
                    "You can use Row to arrange items horizontally and Box to stack elements."
        ),
        Message(
            auther = "Add an image element",
            body = "Use the Resource Manager to import an image from your photo library "
        ),
    )
}