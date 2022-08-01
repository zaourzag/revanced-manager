package app.revanced.manager.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.revanced.manager.R

@Composable
fun ApplicationItem(
    name: String,
    released: String, // TODO: temp
    onClickUpdate: () -> Unit,
    icon: @Composable () -> Unit,
    expandedContent: @Composable () -> Unit
) {
    ExpandableCard(
        content = { arrowButton ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    icon()
                    Column(modifier = Modifier.padding(start = 8.dp)) {
                        Text(name)
                        Text(
                            text = released,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    arrowButton()
                    OutlinedButton(onClick = onClickUpdate) {
                        Text(stringResource(R.string.update))
                    }
                }
            }
        },
        expandedContent = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) { expandedContent() }
        }
    )
}