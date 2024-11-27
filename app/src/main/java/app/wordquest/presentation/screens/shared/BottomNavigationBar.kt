package app.wordquest.presentation.screens.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import app.wordquest.presentation.navigation.Screen
import app.wordquest.ui.theme.CarrotOrange
import app.wordquest.ui.theme.CloudyGrey

@Composable
fun BottomBar(navController: NavHostController) {
    val selected = remember { mutableStateOf(Icons.Default.Home) }

    BottomAppBar(
        contentColor = Color.White
    ) {
        IconButton(onClick = {
            selected.value = Icons.Default.Lock
            navController.navigate(Screen.Game.route) { popUpTo(0) }
        }, modifier = Modifier.weight(1f)) {
            Icon(Icons.Default.Lock, contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = if (selected.value == Icons.Default.Lock) CloudyGrey else CarrotOrange)
        }

        IconButton(onClick = {
            selected.value = Icons.Default.Add
            navController.navigate(Screen.NewWord.route) { popUpTo(0) }
        }, modifier = Modifier.weight(1f)) {
            Icon(Icons.Default.Add, contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = if (selected.value == Icons.Default.Add) CloudyGrey else CarrotOrange)
        }

        Box(modifier = Modifier
            .weight(1f)
            .padding(16.dp),
            contentAlignment = Alignment.Center) {
            IconButton(onClick = {
                selected.value = Icons.Default.Home
                navController.navigate(Screen.Home.route) { popUpTo(0) }
            }) {
                Icon(Icons.Default.Home, contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = if (selected.value == Icons.Default.Home) CloudyGrey else CarrotOrange)
            }
        }

        IconButton(onClick = {
            selected.value = Icons.Default.Menu
            navController.navigate(Screen.WordList.route) { popUpTo(0) }
        }, modifier = Modifier.weight(1f)) {
            Icon(Icons.Default.Menu, contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = if (selected.value == Icons.Default.Menu) CloudyGrey else CarrotOrange)
        }

        IconButton(onClick = {
            selected.value = Icons.Default.AccountCircle
            navController.navigate(Screen.Account.route) { popUpTo(0) }
        }, modifier = Modifier.weight(1f)) {
            Icon(Icons.Default.AccountCircle, contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = if (selected.value == Icons.Default.AccountCircle) CloudyGrey else CarrotOrange)
        }
    }
}