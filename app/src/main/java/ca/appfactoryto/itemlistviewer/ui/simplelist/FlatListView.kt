package ca.appfactoryto.itemlistviewer.ui.simplelist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.appfactoryto.itemlistviewer.R
import ca.appfactoryto.itemlistviewer.data.EmbeddedItemRepository
import ca.appfactoryto.itemlistviewer.ui.app.LoadingScreen
import ca.appfactoryto.itemlistviewer.ui.app.SortRule
import ca.appfactoryto.itemlistviewer.ui.theme.CORNER_RADIUS
import ca.appfactoryto.itemlistviewer.ui.theme.ItemListViewerTheme

/**
 * A simple list view of items. List Ids are allowed to be repeated.
 */
@Composable
fun FlatListView(modifier: Modifier = Modifier, model: FlatListViewModel) =
    if (model.items.isNotEmpty()) {

        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(2.dp),
            state = model.listState,
        ) {
            itemsIndexed(model.items) { index, item ->

                val topPadding = if (index == 0) 16.dp else 8.dp

                Surface(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(top = topPadding, start = 16.dp, end = 16.dp),
                    color = colorResource(id = R.color.white),
                    shape = RoundedCornerShape(CORNER_RADIUS),
                    shadowElevation = 2.dp,
                    onClick = {
                        model.onItemClicked(item)
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
                        Text(text = item.listId.toString())
                        Text(text = item.name ?: stringResource(R.string.unknown))
                    }
                }
            }
        }
    } else {
        LoadingScreen()
    }

@Preview
@Composable
fun ListViewPreview() {
    val snackbarHostState = remember { SnackbarHostState() }
    val sortRule = remember { mutableStateOf(SortRule.NUMERICAL) }
    ItemListViewerTheme {
        FlatListView(
            model = FlatListViewModel(
                repository = EmbeddedItemRepository,
                sortRule = sortRule,
                snackbarHostState = snackbarHostState
            )
        )
    }
}