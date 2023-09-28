package com.neuralnet.maisfinancas.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.neuralnet.maisfinancas.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaisFinancasTopAppBar(
    @StringRes title: Int,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit = {},
) {
    if (canNavigateBack) {
        CenterAlignedTopAppBar(
            title = { Text(stringResource(title)) },
            modifier = modifier,
            navigationIcon = {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        )
    } else {
        CenterAlignedTopAppBar(title = { Text(stringResource(title)) }, modifier = modifier)
    }
}
