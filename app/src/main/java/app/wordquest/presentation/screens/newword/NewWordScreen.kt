package app.wordquest.presentation.screens.newword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.wordquest.R
import app.wordquest.presentation.navigation.Screen
import app.wordquest.presentation.screens.shared.MaxWidthImage
import app.wordquest.presentation.screens.shared.RoundedCornerButton
import app.wordquest.ui.theme.DarkPurple

@Composable
fun NewWordScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkPurple),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.weight(1f))
            MaxWidthImage(R.drawable.word_quest_logo)
            Spacer(modifier = Modifier.weight(2f))

            RoundedCornerButton(
                "Изучить новое слово", onClick = { navController.navigate(Screen.NewWord.route) },
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            RoundedCornerButton(
                "История изученных слов",
                onClick = { navController.navigate(Screen.WordList.route) },
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}