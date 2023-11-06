package com.neuralnet.maisfinancas.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.ui.components.core.MaisFinancasBackground
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme

@Composable
fun WelcomeScreen(
    onEntrarClick: () -> Unit,
    onNavigateSignup: (Int) -> Unit,
) {
    MaisFinancasBackground(
        canNavigateBack = false,
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = stringResource(R.string.app_name),
            modifier = Modifier.weight(.75f),
            contentScale = ContentScale.Crop
        )

        Text(
            text = stringResource(R.string.app_slogan),
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier
                .weight(.35f)
                .align(Alignment.Start)
        )

        Button(
            onClick = onEntrarClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.comece_aqui)
            )
        }

        ClickableText(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onBackground)) {
                    append(stringResource(R.string.nao_possui_conta))
                }
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                    append(stringResource(R.string.registrar))
                }
            },
            onClick = onNavigateSignup,
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun WelcomeScreenPreview() {
    MaisFinancasTheme {
        WelcomeScreen(
            onEntrarClick = {},
            onNavigateSignup = {},
        )
    }
}
