package app.revanced.manager.ui.screen

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.revanced.manager.R
import app.revanced.manager.ui.viewmodel.DashboardViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(viewModel: DashboardViewModel = getViewModel()) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 14.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        HeadlineWithCard(R.string.updates) {
            Row(
                modifier = Modifier
                    .padding(16.dp, 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column {
                    CommitDate(
                        label = R.string.patcher,
                        date = viewModel.patcherCommitDate
                    )
                    CommitDate(
                        label = R.string.manager,
                        date = viewModel.managerCommitDate
                    )
                }
                Button(onClick = {
                    Toast.makeText(context, "Already up-to-date!", Toast.LENGTH_SHORT).show()
                }) {
                    Text(
                        text = stringResource(R.string.update_manager),
                    )
                }
            }
        }

        HeadlineWithCard(headline = R.string.patched_apps) {
            Row(
                modifier = Modifier
                    .padding(16.dp, 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column {
                    UpdatesAvailable(
                        label = R.string.updates_available,
                        amount = 2 // TODO
                    )
                }
                Button(onClick = {
                    Toast.makeText(context, "Already up-to-date!", Toast.LENGTH_SHORT).show()
                }) {
                    Text(
                        text = stringResource(R.string.update_all),
                    )
                }
            }

        }
    }
}

@Composable
fun CommitDate(@StringRes label: Int, date: String) {
    Row {
        Text(
            text = "${stringResource(label)}: ",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = date,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun UpdatesAvailable(@StringRes label: Int, amount: Int) {
    Row {
        Text(
            text = "${stringResource(label)} ($amount)",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeadlineWithCard(
    @StringRes headline: Int,
    content: @Composable () -> Unit
) {
    Column {
        Text(
            text = stringResource(headline),
            style = MaterialTheme.typography.headlineSmall
        )
        ElevatedCard(
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth(),
        ) { content() }
    }
}