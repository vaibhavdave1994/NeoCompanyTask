package com.example.neocompany_task.ui.domain.view.mainscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.neocompany_task.R
import com.example.neocompany_task.ui.data.model.FruitModel
import com.example.neocompany_task.ui.domain.viewmodel.MainViewModel
import com.example.neocompany_task.util.Utility
import com.google.accompanist.pager.HorizontalPagerIndicator

@Composable
fun mainScreen() {
    val mainViewModel: MainViewModel = hiltViewModel()
    val list: List<FruitModel> = mainViewModel.getList
    if (mainViewModel.getList.size == 0) {
        horizontalPagerWithIndicators(Utility.fruitList)
    } else {
        horizontalPagerWithIndicators(list)
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
//@OptIn(ExperimentalFoundationApi::class)
@Composable
fun horizontalPagerWithIndicators(list: List<FruitModel>) {
    val images = ArrayList<Int>()
    for (i in 0 until 5) {
        images.add(
            R.drawable.fruits
        )
    }
    val pagerState = rememberPagerState(pageCount = { images.size })
    var isTextFieldVisible = remember { mutableStateOf(true) }
    var isBottomSheetVisible = remember { mutableStateOf(false) }


    val toolbarHeight = 250.dp
  var showSheet = remember { mutableStateOf(true) }




    Box(
        Modifier
            .fillMaxSize()
    ) {


        Column {
            Column {
                if (isTextFieldVisible.value) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 15.dp, top = 10.dp, end = 15.dp, bottom = 0.dp)
                            .height(220.dp)
                    ) {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp), contentAlignment = Alignment.Center
                        ) {
                            HorizontalPager(beyondBoundsPageCount = 5, state = pagerState) {
                                Image(
                                    painter = painterResource(id = images[it]),
                                    contentScale = ContentScale.Crop,
                                    contentDescription = ""
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(30.dp), contentAlignment = Alignment.Center
                        ) {
                            HorizontalPagerIndicator(
                                pageCount = 5,
                                pagerState = pagerState,
                            )
                        }
                    }
                }
            }
            Box {
                customSearchView(list, isTextFieldVisible)
            }

        }

        FloatingActionButton(
            onClick = {
                isBottomSheetVisible.value = true
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .clip(CircleShape),
            containerColor = colorResource(R.color.white)


        ) {
            Icon(Icons.Filled.MoreVert, contentDescription = "Add")
        }
        if (isBottomSheetVisible.value) {
            Dialog(onDismissRequest = { isBottomSheetVisible.value = false }) {
                bottomSheetDialog()
            }
        }
    }
}







