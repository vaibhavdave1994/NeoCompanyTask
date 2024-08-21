package com.example.neocompany_task.ui.domain.view.mainscreen

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.magnifier
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.neocompany_task.util.Utility.Companion.countListChar
import com.example.neocompany_task.util.Utility.Companion.fruitList
import java.time.format.TextStyle


@Composable
fun bottomSheetDialog() {
    Surface(
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colors.surface,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp)
    ) {
        Box(Modifier.fillMaxWidth()
        ) {
            Text(
                text = countListChar(fruitList),
                modifier = Modifier.offset(x = 0.dp, y = 5.dp),
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 16.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                ),
            )
        }
    }
}

/*@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun bottomSheetDialog(onDismiss: () -> Unit) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        Box(Modifier.fillMaxWidth()
            ) {
            Text(
                text = countListChar(fruitList),
                modifier = Modifier.offset(x = 0.dp, y = 5.dp),
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 16.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                ),
            )
        }


    }

}*/
