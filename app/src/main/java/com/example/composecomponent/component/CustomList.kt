@file:Suppress("KDocUnresolvedReference")

package com.example.composecomponent.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

data class Item(val id: Int, val text: String)

private const val CONTENT_TYPE_STICKY = "CONTENT_TYPE_STICKY"
private const val CONTENT_TYPE_TEXT = "CONTENT_TYPE_TEXT"

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomList() {
    val itemList = List(30) {
        Item(id = it + 1001, text = "Item no: ${it + 1}")
    }

    /**
     * we can make a [Column] or [Row] to be scrollable by adding a scroll state
     */
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .verticalScroll(rememberScrollState())
//            .padding(16.dp),
//        verticalArrangement = Arrangement.spacedBy(12.dp)
//    ) {
//        for (item in itemList) {
//            Text(text = item.text)
//        }
//    }

    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(itemList) {
            Text(text = it.text)
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        state = listState
    ) {
        /**
         * we can create a sticky list item with [stickyHeader]
         */
        stickyHeader(
            key = "first sticky",
            contentType = CONTENT_TYPE_STICKY
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Gray)
                    .clickable {
                        scope.launch {
                            /**
                             * different ways to programmatically scroll the list
                             */
//                            listState.scrollBy(-1000f)
//                            listState.animateScrollBy(1000f)
//                            listState.scrollToItem(10)
                            listState.animateScrollToItem(10)
                        }
                    }
                    .padding(12.dp),
                text = "This is a sticky header"
            )
        }

        /**
         * although we only add a single item, we still have to wrap it with [item] first
         */
        item(
            key = "single item",
            contentType = CONTENT_TYPE_TEXT
        ) {
            Text(text = "This is an item")
        }

        /**
         * we can create a list of items by passing the list of object or variables
         */
        items(
            itemList,
            key = { item -> "items with list ${item.id}" },
            contentType = { CONTENT_TYPE_TEXT }
        ) { item ->
            Text(text = item.text)
        }

        /**
         * similar with above, but [itemsIndexed] will also provide us with the index value
         * of each list item
         */
        itemsIndexed(itemList, key = { _, item ->
            "itemsIndexed ${item.id}"
        }, contentType = { _, _ ->
            CONTENT_TYPE_TEXT
        }) { index, item ->
            Text(text = "Index: $index, value: ${item.text}")
        }

        stickyHeader(
            contentType = CONTENT_TYPE_STICKY
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Gray)
                    .padding(12.dp),
                text = "This is another sticky header"
            )
        }

        /**
         * we can also create a list of items by passing the size of the list
         */
        items(
            100,
            key = { index -> "items with count $index" },
            contentType = { CONTENT_TYPE_TEXT }
        ) { index ->
            Text(text = "Items with index: $index")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CustomListPreview() {
    CustomList()
}