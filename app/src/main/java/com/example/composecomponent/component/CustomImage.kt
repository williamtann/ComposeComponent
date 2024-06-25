@file:Suppress("KDocUnresolvedReference")

package com.example.composecomponent.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.composecomponent.R

@Composable
fun CustomImage() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Icon(
            modifier = Modifier.size(60.dp),
            /**
             * we can use [imageVector] as well and use the icons selection from Google Material
             */
//            imageVector = Icons.Outlined.Email,
            painter = painterResource(id = R.drawable.baseline_shopping_cart_24),
            tint = Color.Red,
            contentDescription = null
        )

        Image(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape),
            /**
             * we can use [imageVector] for [Image] composable as well
             */
//            imageVector = Icons.Default.Lock,
            painter = painterResource(id = R.drawable.puppy),
            contentDescription = null,
            /**
             * [colorFilter] is used to tint the image, but rarely used as [Image] composable
             * usually contains colorful image instead of a single color image
             */
//            colorFilter = ColorFilter.tint(Color.Red),
            colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) }),
            contentScale = ContentScale.Crop
        )

        val context = LocalContext.current
        AsyncImage(
            modifier = Modifier.size(200.dp),
            /**
             * we can directly set the value of [model] with string URL of the image
             */
//            model = "https://picsum.photos/id/1/1000/1000",
            model = ImageRequest.Builder(context).data("https://picsum.photos/id/1/1000/1000")
                /**
                 * we can change the caching policy to be disabled, but best just leave it alone
                 * as caching provides better user experience and improve efficiency our app
                 */
//                .diskCachePolicy(CachePolicy.DISABLED)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.placeholder),
            error = painterResource(id = R.drawable.error),
            contentDescription = null,
            onLoading = {
                Log.d("CustomImage", "Image is loading")
            },
            onError = {
                Log.d("CustomImage", "Failed to load image")
            },
            onSuccess = {
                Log.d("CustomImage", "Image loaded successfully")
            }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun CustomImagePreview() {
    CustomImage()
}