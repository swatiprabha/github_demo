package com.example.game.presentation.game.components

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.game.domain.model.Games

@Composable
fun GameItem(modifier: Modifier, games: Games) {

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .testTag("gameItem")
    ) {
        Card(elevation = CardDefaults.cardElevation(4.dp), shape = RoundedCornerShape(16.dp)) {
            Column(modifier = modifier
                .padding(8.dp)
                .clickable {

                }
                .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                AsyncImage(
                    model = games.thumbnail,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                        .testTag("thumbnail"),
                    contentScale = ContentScale.Crop,
                    )
                Text(
                    text = games.title,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.testTag("title")
                )
                Text(text = games.shortDescription, modifier = Modifier.testTag("shortDescription"))
            }
        }
    }
}