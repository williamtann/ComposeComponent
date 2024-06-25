package com.example.composecomponent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.composecomponent.component.CustomButton
import com.example.composecomponent.component.CustomImage
import com.example.composecomponent.component.CustomList
import com.example.composecomponent.component.CustomText
import com.example.composecomponent.component.CustomTextField

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            /**
             * Uncomment the component that you want to preview
             */
//            CustomText()
//            CustomImage()
//            CustomButton()
//            CustomTextField()
            CustomList()
        }
    }
}