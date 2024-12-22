package ca.appfactoryto.itemlistviewer.ui.app

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import ca.appfactoryto.itemlistviewer.R
import ca.appfactoryto.itemlistviewer.ui.theme.FetchOrange

/**
 * Vertical 3 dots menu button located in the top right corner of the screen.
 */
@Composable
fun MoreMenuButton(
    modifier: Modifier,
    sortRule: SortRule,
    onSortRuleChanged: (SortRule) -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    IconButton(
        modifier = modifier,
        onClick = { expanded = true }) {
        Icon(
            Icons.Filled.MoreVert, contentDescription = stringResource(R.string.more),
        )
    }

    DropdownMenu(
        modifier = modifier.background(color = colorResource(id = R.color.white)),
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        DropdownMenuItem(
            modifier = modifier.background(
                color =
                if (sortRule == SortRule.ALPHABETICAL) {
                    FetchOrange
                } else {
                    colorResource(id = R.color.white)
                }
            ),
            text = {
                Text(text = stringResource(R.string.alphabetical_sort))
            }, onClick = {
                onSortRuleChanged(SortRule.ALPHABETICAL)
                expanded = false
            })

        DropdownMenuItem(
            modifier = modifier.background(
                color =
                if (sortRule == SortRule.NUMERICAL) {
                    FetchOrange
                } else {
                    colorResource(id = R.color.white)
                }
            ),
            text = {
                Text(text = stringResource(R.string.numerical_sort))
            }, onClick = {
                onSortRuleChanged(SortRule.NUMERICAL)
                expanded = false
            })
    }
}