package com.example.game.game_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.game.presentation.game.MainActivity
import org.junit.Rule
import org.junit.Test

class GameList {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun validateProgressBarVisible(){
        composeRule.apply{
            onNodeWithTag("progress").assertIsDisplayed()
        }
    }

    @Test
    fun verifyIfViewsExists() {
        composeRule.onNodeWithTag("game_list").assertExists()
    }
}