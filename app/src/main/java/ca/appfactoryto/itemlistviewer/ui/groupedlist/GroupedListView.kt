package ca.appfactoryto.itemlistviewer.ui.groupedlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ca.appfactoryto.itemlistviewer.R
import ca.appfactoryto.itemlistviewer.domain.GroupedItem
import ca.appfactoryto.itemlistviewer.ui.theme.CORNER_RADIUS

/**
 * Present the user with a list of group headers. When the user taps on a group header, it will
 * expand a sublist below of items that belong to that group.
 */
@Composable
fun GroupedListView(
    modifier: Modifier = Modifier,
    model: GroupedListViewModel,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        state = model.listState
    ) {
        model.groupedItems.value.forEachIndexed { index, item ->

            val topPadding = if (index == 0) 16.dp else 8.dp

            // Create a header for each list group
            item {
                GroupHeader(topPadding, item)
            }

            // When the group header is tapped on, it will expand the content below the header
            if (item.isExpanded.value) {
                items(item.getChunkedItems(model.sublistColumns)) {

                    // Display a single horizontal sublist that has MAX_COLUMNS as the number
                    // of elements in the row.
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp)
                            .fillMaxWidth(),
                    ) {
                        it.forEach { item ->
                            item.name?.let { name ->
                                Surface(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .weight(1f)
                                        .fillMaxWidth(),
                                    shape = RoundedCornerShape(CORNER_RADIUS),
                                    shadowElevation = 1.dp,
                                    onClick = {
                                        model.onItemClicked(item)
                                    }
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .weight(1f)
                                            .fillMaxWidth(),
                                        textAlign = TextAlign.Center,
                                        text = name
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * A clickable button that uses the listId as the label. Also has an indicator if a child
 * list is currently open.
 */
@Composable
private fun GroupHeader(
    topPadding: Dp,
    item: GroupedItem
) {
    Surface(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(top = topPadding, start = 16.dp, end = 16.dp),
        color = colorResource(id = R.color.white),
        shape = RoundedCornerShape(CORNER_RADIUS),
        shadowElevation = 2.dp,
        onClick = {
            item.isExpanded.value = !item.isExpanded.value
        }
    ) {
        Row(
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
                top = 8.dp,
                bottom = 8.dp
            ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Label
            Text(text = item.groupId.toString())

            // Right Side Icon
            if (item.isExpanded.value) {
                Icon(
                    Icons.Filled.KeyboardArrowUp,
                    contentDescription = stringResource(R.string.close)
                )
            } else {
                Icon(
                    Icons.Filled.KeyboardArrowDown,
                    contentDescription = stringResource(R.string.open)
                )
            }
        }
    }
}