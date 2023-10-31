package com.neuralnet.maisfinancas.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme
import com.neuralnet.maisfinancas.util.FieldValidationError

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InserirRegistroBottomSheet(
    onDismissRequest: () -> Unit,
    valor: String,
    onValueChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    valorErrorField: FieldValidationError? = null,
    sheetState: SheetState = rememberModalBottomSheetState(),
    calendarState: DatePickerState = rememberDatePickerState(),
) {
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismissRequest,
    ) {
        NumberTextField(
            valor = valor,
            onValueChange = onValueChange,
            errorMessage = valorErrorField,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        DatePicker(
            state = calendarState,
            showModeToggle = false,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Button(
            onClick = onSaveClick,
            modifier = Modifier
                .align(Alignment.End)
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp,
                )
        ) {
            Text(text = stringResource(R.string.salvar))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun InserirRegistroBottomSheetPreview() {
    MaisFinancasTheme {
        InserirRegistroBottomSheet(
            onDismissRequest = {},
            valor = "",
            onValueChange = {},
            onSaveClick = {}
        )
    }
}
