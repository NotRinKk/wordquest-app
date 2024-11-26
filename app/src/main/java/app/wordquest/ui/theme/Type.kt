package app.wordquest.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import app.wordquest.R

val robotoFamily = FontFamily(
    Font(R.font.inter_bold, FontWeight.Bold)
)

val typography = Typography(
        // H1
    headlineLarge = TextStyle(
        fontFamily = robotoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp,
    ), // H2
    headlineMedium = TextStyle(
        fontFamily = robotoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
    ), // H3
    headlineSmall = TextStyle(
        fontFamily = robotoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ), // Text
    bodyMedium = TextStyle(
        fontFamily = robotoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )
)