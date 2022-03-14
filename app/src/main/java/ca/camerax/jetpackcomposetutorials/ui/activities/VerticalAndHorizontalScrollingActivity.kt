package ca.camerax.jetpackcomposetutorials.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.camerax.jetpackcomposetutorials.ui.theme.JetpackComposeTutorialsTheme
import ca.camerax.jetpackcomposetutorials.util.CommonComposeFunctions.startTargetActivity


@ExperimentalMaterialApi
class VerticalAndHorizontalScrollingActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhiteSurface {
                LeftAndRightScrollingGridList(
                    rows = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13),
                    columns = mutableListOf(1, 2, 3, 4, 5, 6, 7)
                )
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun LeftAndRightScrollingGridList(rows: MutableList<Int>, columns: MutableList<Int>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        UniversalSimpleTopAppBar("Vertical And Horizontal Scrolling Grid View")
        //Spacer(modifier = Modifier.padding(top = 10.dp))
        Divider(color = Color.Gray, modifier = Modifier.padding(all = 10.dp))
        LazyColumn {
            itemsIndexed(rows) { rowIndex: Int, row ->
                LazyRow {
                    itemsIndexed(columns) { columnIndex: Int, column ->
                        GridViewItem(
                            row = row, rowIndex = rowIndex,
                            column = column, columnIndex = columnIndex
                        )
                    }
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun GridViewItem(row: Int, rowIndex: Int, column: Int, columnIndex: Int) {
    val context = LocalContext.current
    Text(
        modifier = Modifier
            .padding(8.dp)
            .background(if (row % 2 == 0 && column % 2 == 0) Color.Red else Color.Green)
            .selectable(selected = true,
                onClick = {
                    Toast
                        .makeText(
                            context,
                            "Row = $row,  RowIndex = $rowIndex\n" +
                                    "Column = $column, ColumnIndex = $columnIndex",
                            Toast.LENGTH_SHORT
                        )
                        .show()
                    context.startTargetActivity(StateInComposeActivity())
                })
            .width(100.dp)
            .height(100.dp),
        textAlign = TextAlign.Center,
        color = Color.White,
        text = "Row $row,  RowIndex $rowIndex\n" +
                "Column $column, ColumnIndex $columnIndex",
    )
}


@ExperimentalMaterialApi
@Preview(showBackground = true, name = "Grid preview")
@Composable
fun DefaultPreview2() {
    JetpackComposeTutorialsTheme {
        LeftAndRightScrollingGridList(
            rows = mutableListOf(1, 2, 3, 4),
            columns = mutableListOf(1, 2, 3, 4, 5)
        )
    }
}