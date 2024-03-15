package com.example.game.game_list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.game.domain.model.Games
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.example.game.presentation.game.components.GameItem

class GameItemShould {

    @Rule
    @JvmField
    val composeRule = createComposeRule()
    private lateinit var game: Games

    @Before
    fun setUp() {
        game = Games(
            gameUrl = "https://www.freetogame.com/g/508/thumbnail.jpg",
            id = 508,
            shortDescription = "Get ready to command your own World War II military squad in Gaijin and Darkflow Software\\u2019s MMO squad-based shooter Enlisted.",
            thumbnail = "https://www.freetogame.com/g/508/thumbnail.jpg",
            title = "Enlisted"
        )
        composeRule.setContent {
            GameItem(games = game, modifier = Modifier.fillMaxSize())
        }
    }

    @Test
    fun verifyIfAllViewsExists() {
        composeRule.onNodeWithTag("thumbnail", useUnmergedTree = true).assertExists()
        composeRule.onNodeWithTag("title", useUnmergedTree = true).assertExists()
        composeRule.onNodeWithTag("shortDescription", useUnmergedTree = true).assertExists()
    }

    @Test
    fun itemDisplayed() {
        composeRule.onNodeWithTag("gameItem", useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun containCorrectTitle() {
        composeRule.onNodeWithTag("title", useUnmergedTree = true).assertTextEquals("Enlisted")
    }

    @Test
    fun containCorrectDescription() {
        composeRule.onNodeWithTag("shortDescription", useUnmergedTree = true).assertTextEquals(
            "Get ready to command your own World War II military squad in Gaijin and " + "Darkflow Software\\u2019s MMO squad-based shooter Enlisted."
        )
    }
}