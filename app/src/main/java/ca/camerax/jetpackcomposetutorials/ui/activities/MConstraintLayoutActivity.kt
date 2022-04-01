package ca.camerax.jetpackcomposetutorials.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

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
    ConstraintLayout {
        val (button1, button2, text) = createRefs()
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
        }
    }
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