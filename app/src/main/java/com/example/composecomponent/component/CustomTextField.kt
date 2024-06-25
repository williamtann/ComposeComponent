@file:Suppress("KDocUnresolvedReference")

package com.example.composecomponent.component

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val context = LocalContext.current
        var textValue by remember {
            mutableStateOf("")
        }
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = textValue,
            onValueChange = { newValue ->
                textValue = newValue
            },
            /**
             * we can control whether if the text field is only readable
             * or even be disabled altogether
             */
//            readOnly = true,
//            enabled = false,
            singleLine = true,
            /**
             * remember to put [minLines] value to be <= the value of [maxLines]
             */
//            maxLines = 1,
//            minLines = 2,
            textStyle = MyTextStyle.Label,
            placeholder = {
                Text(text = "Type something..")
            },
            label = {
                Text(text = "Email")
            },
            /**
             * more configurations regarding UI decorations we can set to the [TextField]
             */
//            leadingIcon = {
//                Icon(imageVector = Icons.Default.Email,
//                contentDescription = null)
//            },
//            trailingIcon = {
//                Icon(imageVector = Icons.Default.Send, contentDescription = null)
//            },
//            prefix = {
//                Text(text = "I'm")
//            },
//            suffix = {
//                Text(text = "@gmail.com")
//            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                autoCorrect = false,
                imeAction = ImeAction.Send
            ),
            keyboardActions = KeyboardActions {
                Toast.makeText(context, textValue, Toast.LENGTH_SHORT).show()
            },
            /**
             * we can set a boolean or validation logic on when
             * should it show an error indication
             */
//            isError = textValue.length > 5,
            /**
             * we can configure the [TextField]'s shape and colors
             */
//            shape = RoundedCornerShape(12.dp),
//            colors = TextFieldDefaults.colors(
//                unfocusedIndicatorColor = Color.Transparent,
//                focusedIndicatorColor = Color.Transparent,
//                unfocusedContainerColor = Color(0xFFEEEEEE),
//                focusedContainerColor = Color(0xFFDDDDDD)
//            )
        )

        NormalTextField(textValue = textValue, enabled = false) { newValue ->
            textValue = newValue
        }

        OutlinedTextField(textValue = textValue) { newValue ->
            textValue = newValue
        }
    }
}

@Composable
fun NormalTextField(
    textValue: String,
    enabled: Boolean = true,
    onValueChange: (String) -> Unit
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val backgroundColor by remember {
        derivedStateOf {
            when {
                !enabled -> {
                    Color(0xFFAAAAAA)
                }
                isFocused -> {
                    Color(0xFFDDDDDD)
                }
                else -> {
                    Color(0xFFEEEEEE)
                }
            }
        }
    }
    BasicTextField(
        textStyle = MyTextStyle.Label,
        value = textValue,
        onValueChange = onValueChange,
        interactionSource = interactionSource,
        enabled = enabled
    ) { content ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(backgroundColor)
                .padding(12.dp)
        ) {
            Box(modifier = Modifier.weight(1f)) {
                if (textValue.isEmpty()) {
                    Text(
                        text = "Type something..",
                        style = MyTextStyle.Label.copy(color = Color.Gray)
                    )
                }
                content()
            }
            Icon(imageVector = Icons.Default.Send, contentDescription = null)
        }
    }
}

@Composable
fun OutlinedTextField(
    textValue: String,
    onValueChange: (String) -> Unit
) {
    BasicTextField(
        textStyle = MyTextStyle.Label,
        value = textValue,
        onValueChange = onValueChange
    ) { content ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            Box(modifier = Modifier.weight(1f)) {
                if (textValue.isEmpty()) {
                    Text(
                        text = "Type something..",
                        style = MyTextStyle.Label.copy(color = Color.Gray)
                    )
                }
                content()
            }
            Icon(imageVector = Icons.Default.Send, contentDescription = null)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CustomTextFieldPreview() {
    CustomTextField()
}