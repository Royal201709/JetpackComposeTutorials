package ca.camerax.jetpackcomposetutorials.util


import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp
import ca.camerax.jetpackcomposetutorials.VerticalAndHorizontalScrollingActivity

/**
 * Created by Royal Lachinov on 2022-03-04.
 */
object CommonComposeFunctions {

        fun Modifier.firstBaselineToTop(firstBaselineToTop: Dp) = this.then(layout { measurable, constraints ->
            val placeable = measurable.measure(constraints)
            check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
            val firstBaseline = placeable[FirstBaseline]

            val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
            val height = placeable.height.plus(placeableY)
            layout(placeable.width, height){
                placeable.placeRelative(0, placeableY)
            }
        })

    fun Context.startTargetActivity(activity:ComponentActivity){
        this.startActivity(
            Intent(
                this,
                activity::class.java
            )
        )
    }
}