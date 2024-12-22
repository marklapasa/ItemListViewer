package ca.appfactoryto.itemlistviewer.ui.app

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ca.appfactoryto.itemlistviewer.R
import ca.appfactoryto.itemlistviewer.ui.theme.ItemListViewerTheme

/**
 * Navigation bar for the Item List Viewer app located at the bottom of the screen.
 */
@Composable
fun ItemListViewerNavigation(
    modifier: Modifier = Modifier,
    selectedTabIndex: Int,
    onFlatListSelected: () -> Unit,
    onGroupedListSelected: () -> Unit
) {
    NavigationBar(
        modifier = modifier
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    Icons.Filled.Menu,
                    contentDescription = stringResource(R.string.flat_list)
                )
            },
            label = { Text(stringResource(R.string.flat)) },
            selected = selectedTabIndex == 0,
            onClick = onFlatListSelected
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.columns_icon_24),
                    contentDescription = stringResource(R.string.grouped_list)
                )
            },
            label = { Text(stringResource(R.string.grouped)) },
            selected = selectedTabIndex == 1,
            onClick = onGroupedListSelected
        )
    }
}

@Preview
@Composable
fun ItemListViewerNavigationPreview() {
    ItemListViewerTheme {
        ItemListViewerNavigation(Modifier, 0, {}, {})
    }
}
