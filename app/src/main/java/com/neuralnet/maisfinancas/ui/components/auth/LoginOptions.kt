package com.neuralnet.maisfinancas.ui.components.auth

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme

@Composable
fun LoginOptions(
    label: String,
    onSigninWithFacebook: () -> Unit,
    onSigninWithGoogle: () -> Unit,
    onSigninWithTwitter: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Divider(
                modifier = Modifier
                    .weight(.3f)
                    .padding(8.dp)
            )

            Text(text = label)

            Divider(
                modifier = Modifier
                    .weight(.3f)
                    .padding(8.dp)
            )
        }

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            LoginOption(
                icon = R.drawable.facebook,
                contentDescription = stringResource(id = R.string.facebook),
                onClick = onSigninWithFacebook,
                modifier = Modifier.weight(1f)
            )
            LoginOption(
                icon = R.drawable.google,
                contentDescription = stringResource(id = R.string.google),
                onClick = onSigninWithGoogle,
                modifier = Modifier.weight(1f)
            )
            LoginOption(
                icon = R.drawable.twitter,
                contentDescription = stringResource(id = R.string.twitter),
                onClick = onSigninWithTwitter,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun LoginOption(
    @DrawableRes icon: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .padding(vertical = 12.dp)
            .border(
                border =
                BorderStroke(
                    width = 1.dp,
                    color = Color.LightGray
                ),
                shape = MaterialTheme.shapes.medium
            )
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = contentDescription,
            tint = Color.Unspecified,
        )

    }
}

@Preview
@Composable
fun LoginOptionsPreview() {
    MaisFinancasTheme {
        LoginOptions(
            "ou continue com",
            onSigninWithFacebook = {},
            onSigninWithGoogle = {},
            onSigninWithTwitter = {}
        )
    }
}
