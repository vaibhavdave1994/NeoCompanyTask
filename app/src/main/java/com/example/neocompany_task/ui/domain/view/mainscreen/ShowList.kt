package com.example.neocompany_task.ui.domain.view.mainscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.neocompany_task.R

@Composable
fun showList(fName:String,whatIs:String) {
    Card(
        modifier = Modifier
            .padding(start = 0.dp, top = 6.dp, end = 0.dp, bottom = 6.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.lightblue)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.fruits),
                contentDescription = null,
                modifier = Modifier
                    .size(90.dp)
                    .padding(start = 5.dp, top = 5.dp, end = 10.dp, bottom = 5.dp)
                    .clip(RoundedCornerShape(5.dp)),
                contentScale = ContentScale.Crop
            )

            Column {
                Text(
                    text = fName,
                    style = TextStyle(
                        fontSize = 16.sp, // Set the text size to 24sp
                        color = Color.Black // Set the text color
                    )
                )
                Text(
                    text = "Fruit",
                    modifier = Modifier.offset(x = 0.dp, y = 5.dp),
                    style = TextStyle(
                        fontSize = 16.sp, // Set the text size to 24sp
                        color = Color.Black // Set the text color
                    ),
                )
            }
        }
    }
}