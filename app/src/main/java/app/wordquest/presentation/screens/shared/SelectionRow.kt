package app.wordquest.presentation.screens.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.wordquest.ui.theme.DarkPurple
import app.wordquest.ui.theme.White
import app.wordquest.ui.theme.typography


@Composable
fun SelectionRow(
    labelText: String,
    selectedLanguage: String,
    onLanguageSelected: (String) -> Unit,
    options: List<String>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(DarkPurple),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = labelText,
            style = MaterialTheme.typography.bodyMedium,
            color = White,
            modifier = Modifier
                .weight(1f)
                .padding(5.dp)
        )
        CustomDropdown(
            selectedItem = selectedLanguage,
            onItemSelected = onLanguageSelected,
            options = options,
            modifier = Modifier.width(160.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDropdown(
    selectedItem: String,
    onItemSelected: (String) -> Unit,
    options: List<String>,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        TextField(
            value = selectedItem,
            onValueChange = {},
            readOnly = true,
            textStyle = typography.bodyMedium.copy(color = White),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = White,
                unfocusedBorderColor = White,
                cursorColor = White
            ),
            modifier = Modifier
                .clickable { expanded = !expanded }
                .fillMaxWidth(),
            enabled = false
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = {Text(option)},
                    onClick = {
                        onItemSelected(option)
                        expanded = false
                    })
            }
        }
    }
}