@file:Suppress("KDocUnresolvedReference")

package com.example.composecomponent.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.composecomponent.R

@Composable
fun CustomText() {
    Text(
        text = buildAnnotatedString {
            append("Hello world, today is such a ")
            withStyle(
                style = MyTextStyle.Title.toSpanStyle()
            ) {
                append("beautiful")
            }
            append(" day!")
        },
        /**
         * more configurations that we can make in [Text] composable, it can also be set using [style] parameter
         */
//        fontSize = 20.sp,
//        fontWeight = FontWeight.Black,
//        fontFamily = FontFamily.Serif,
//        color = Color.Blue,
//        lineHeight = 50.sp,
//        letterSpacing = 4.sp,
//        textAlign = TextAlign.End,
        overflow = TextOverflow.Ellipsis,
        /**
         * remember to put [minLines] value to be <= the value of [maxLines]
         */
        maxLines = 5,
        minLines = 3,
        style = MyTextStyle.Label.copy(
            /**
             * [textDecoration] used to add underline ar/or strikethrough
             */
//            textDecoration = TextDecoration.Underline + TextDecoration.LineThrough,
            shadow = Shadow(
                color = Color.Red,
                offset = Offset(x = 5f, y = 10f),
                blurRadius = 10f
            ),
            color = Color.Blue,
            fontStyle = FontStyle.Italic
        )
    )
}

object MyTextStyle {
    private val baseFontFamily = FontFamily(
        Font(resId = R.font.mali_light, weight = FontWeight.Light),
        Font(resId = R.font.mali_lightitalic, weight = FontWeight.Light, style = FontStyle.Italic),
        Font(resId = R.font.mali_regular, weight = FontWeight.Normal),
        Font(resId = R.font.mali_italic, weight = FontWeight.Normal, style = FontStyle.Italic),
        Font(resId = R.font.mali_semibold, weight = FontWeight.SemiBold),
        Font(
            resId = R.font.mali_semibolditalic,
            weight = FontWeight.SemiBold,
            style = FontStyle.Italic
        ),
    )

    private val BaseText = TextStyle(
        fontFamily = baseFontFamily
    )

    val Title = BaseText.copy(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )
    val Label = BaseText.copy(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    )

}

@Preview(showSystemUi = true)
@Composable
fun CustomTextPreview() {
    CustomText()
}