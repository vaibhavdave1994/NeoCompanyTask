package com.example.neocompany_task.ui.domain.view.mainscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.neocompany_task.ui.data.model.FruitModel

@Composable
fun customSearchView(list: List<FruitModel>, isTextFieldVisible: MutableState<Boolean>) {
    val modifier: Modifier = Modifier
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    val focusRequester = remember { FocusRequester() }


    Column( modifier = modifier
        .fillMaxWidth()
        .padding(start = 15.dp, top = 6.dp, end = 15.dp, bottom = 10.dp)) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp))
                .background(Color.LightGray)

        ) {
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },

                placeholder = { Text("Search...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
                trailingIcon = {
                    IconButton(onClick = {
                        isTextFieldVisible.value = true
                        searchQuery = TextFieldValue("")
                    }) {

                        Icon(imageVector = Icons.Filled.Clear, contentDescription = "Clear")
                    }
                },



                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search
                ),

                keyboardActions = KeyboardActions(onSearch = { }),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    focusedContainerColor = Color.LightGray,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.LightGray,
                    focusedLeadingIconColor = Color.Black,
                    unfocusedIndicatorColor = Color.LightGray,
                    disabledIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
                    .onFocusChanged {
                        if (it.isFocused) {
                            if (searchQuery.text.isEmpty()) {
                                isTextFieldVisible.value = false
                            }
                        } else {
                            isTextFieldVisible.value = true
                            // not focused
                        }
                    }
                    .background(Color.LightGray, RoundedCornerShape(8.dp))

            )





        }
        val filteredFruits = list.filter {
            it.fruitName?.contains(searchQuery.text, ignoreCase = true) == true
        }
        LazyColumn {
            items(filteredFruits) {  fruit ->
                fruit.fruitName?.let { fruitName ->
                    fruit.whatIs?.let { whatIs ->
                        showList(fruitName, whatIs)
                    }
                }
            }
        }


    }


}
