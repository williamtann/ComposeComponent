@file:Suppress("KDocUnresolvedReference")

package com.example.composecomponent.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Green,
                contentColor = Color.DarkGray,
                disabledContainerColor = Color.DarkGray,
                disabledContentColor = Color.White
            ),
            /**
             * as the name suggest, we can set whether the button is enabled or not
             */
//            enabled = false,
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp, pressedElevation = 3.dp
            ),
            /**
             * for [Button] composable, setting the [border] parameter provides more precise borders
             * for our button rather than setting the border using modifier
             */
//            border = BorderStroke(width = 2.dp, color = Color.Red),
            contentPadding = PaddingValues(horizontal = 40.dp, vertical = 12.dp),
        ) {
            Text(text = "It's a button")
            Spacer(modifier = Modifier.width(8.dp))
            Icon(imageVector = Icons.Default.Send, contentDescription = null)
        }

        OutlinedButton(
            onClick = { /*TODO*/ }, border = BorderStroke(width = 2.dp, color = Color.Black)
        ) {
            Text(text = "This is outlined")
        }

        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.Email, contentDescription = null
            )
        }

        MyIconButton(
            modifier = Modifier.size(80.dp),
            imageVector = Icons.Default.Email,
            onClick = { /*TODO*/ }
        )
    }
}

@Composable
fun MyIconButton(modifier: Modifier = Modifier, imageVector: ImageVector, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .padding(4.dp)
            .clip(CircleShape)
            .clickable(
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = rememberRipple(),
                onClick = onClick
            )
            .padding(8.dp)
    ) {
        Icon(
            modifier = Modifier.fillMaxSize(),
            imageVector = imageVector,
            contentDescription = null
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun CustomButtonPreview() {
    CustomButton()
}