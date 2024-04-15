package com.example.bowlarama.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.bowlarama.R
import com.example.bowlarama.Team
import kotlinx.coroutines.launch

@Composable
fun TeamsScreen()
{
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Magenta),
        contentAlignment = Alignment.Center
    ){
        createScroller()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun createScroller(){
    val pinPals = Team(
        R.drawable.pin_pals,
        "Pin Pals",
        "Homer, Barney, Apu and Clancy make up the Pin Pals: a formidable team with a strong record and a large bar tab"
    )

    val holyRollers = Team(
        R.drawable.holy_rollers,
        "Holy Rollers",
        "Reverend Lovejoy, Helen Lovejoy, Ned Flanders and Maud Flanders make up the Holy Rollers."
    )

    val homeWreckers = Team(
        R.drawable.homewreckers,
        "Home Wreckers",
        "Jacques, Lurleen, Mindy and Princess Kashmir make up the Homewreckers."
    )

    val teams = listOf(
        pinPals,
        holyRollers,
        homeWreckers
    )

    val pagerState = rememberPagerState(pageCount = { teams.size })
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()){

        HorizontalPager(
            state = pagerState,
            key = { teams[it] },
            pageSize = PageSize.Fill
        ) {index ->
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colors.surface
                ),
                modifier = Modifier.size(width = 300.dp, height = 600.dp)
            ){
                Column {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = teams[index].name
                    )

                    Image(
                        modifier = Modifier.fillMaxWidth(),
                        painter = painterResource(id = teams[index].picture),
                        contentDescription = null,
                        contentScale = ContentScale.Fit
                    )

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = teams[index].description
                    )

                }
            }
        }

        Box(
            modifier = Modifier
                .offset(y = -(35).dp)
                .fillMaxWidth(0.5f)
                .clip(RoundedCornerShape(100))
                .background(MaterialTheme.colors.background)
                .padding(8.dp)
                .align(Alignment.BottomCenter)
        ){
            IconButton(
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(
                            pagerState.currentPage - 1
                        )
                    }
                },
                modifier = Modifier.align(Alignment.CenterStart)
            ){
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Go back"
                )
            }

            IconButton(
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(
                            pagerState.currentPage + 1
                        )
                    }
                },
                modifier = Modifier.align(Alignment.CenterEnd)
            ){
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "Go forth"
                )
            }
        }



    }

}