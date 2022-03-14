package ca.camerax.jetpackcomposetutorials.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import ca.camerax.jetpackcomposetutorials.util.CommonComposeFunctions.firstBaselineToTop

class ConstraintLayoutActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SetupScaffold(topBar = universalScaffoldTopAppBar(
                topAppBarTitle = "Constraint Layout",
                //actions = topAppbarActions()
            )) {
                Column {
                    ConstraintLayoutContent()
                    Divider(color = Color.LightGray, modifier = Modifier.padding(all = 10.dp))
                    DecoupledConstraintLayout()
                    Divider(color = Color.LightGray, modifier = Modifier.padding(all = 10.dp))
                    ConstraintLayoutContent2()
                    Divider(color = Color.LightGray, modifier = Modifier.padding(all = 10.dp))
                    CreateLargeConstraintLayout()
                }
            }
        }
    }
}

@Composable
fun ConstraintLayoutContent() {
    ConstraintLayout(modifier = Modifier
        .wrapContentHeight()
        .fillMaxWidth()
        .background(color = Color.Blue)) {
        val (button, text1, text2) = createRefs()

        Button(onClick = { /*TODO*/ },
            modifier = Modifier.constrainAs(button) {
                top.linkTo(parent.top, margin = 16.dp)
            }) {
            Text(text = "Button")
        }

        Text(text = "Text", modifier = Modifier.constrainAs(text1) {
            top.linkTo(button.bottom, margin = 16.dp)
        })

        Text(text = "Text with firstBaselineToTop", modifier = Modifier.firstBaselineToTop(52.dp)
            .constrainAs(text2) {
            top.linkTo(text1.bottom)
        })
    }
}

@Composable
fun DecoupledConstraintLayout() {
    BoxWithConstraints {
        val constraints = if (maxWidth < maxHeight) {
            decoupledConstraintLayout(margin = 16.dp)
        } else {
            decoupledConstraintLayout(margin = 32.dp)
        }

        ConstraintLayout(
            constraintSet = constraints,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .background(color = Color.Green)
        ) {
            Button(onClick = { /*TODO*/ }, modifier = Modifier.layoutId("button")) {
                Text(text = "Button decoupled 1")
            }

            Text(text = "Text decoupled 1", Modifier.layoutId("text"))
        }
    }
}


private fun decoupledConstraintLayout(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val button = createRefFor("button")
        val text = createRefFor("text")

        constrain(button) {
            top.linkTo(parent.top, margin = margin)
        }

        constrain(text) {
            top.linkTo(button.bottom, margin = margin)
        }
    }
}

@Composable
fun ConstraintLayoutContent2() {
    ConstraintLayout(modifier = Modifier
        .wrapContentHeight()
        .fillMaxWidth()
        .background(color = Color.Cyan)) {
        val (button1, button2, text) = createRefs()

        Button(onClick = { /*TODO*/ }, modifier = Modifier.constrainAs(button1) {
            top.linkTo(parent.top, margin = 16.dp)
        }) {
            Text(text = "Button 1")
        }

        Text(text = "Text", modifier = Modifier.constrainAs(text) {
            top.linkTo(button1.bottom)
            centerAround(button1.end)
        })

        val barrier = createEndBarrier(button1, text)
        Button(onClick = { /*TODO*/ }, modifier = Modifier.constrainAs(button2) {
            top.linkTo(parent.top, margin = 16.dp)
            start.linkTo(barrier)
        }) {
            Text(text = "Button 2")
        }
    }
}

@Composable
fun CreateLargeConstraintLayout() {
    ConstraintLayout(modifier = Modifier
        .wrapContentHeight()
        .fillMaxWidth()
        .background(color = Color.LightGray)) {
        val text = createRef()
        val guideline = createGuidelineFromStart(fraction = 0.25f)

        Text(
            text = "Very Loooooooooooon loooooon looooon loooong text",
            modifier = Modifier.constrainAs(text) {
                linkTo(start = guideline, end = parent.end)
                width = Dimension.preferredWrapContent
            })
    }
}