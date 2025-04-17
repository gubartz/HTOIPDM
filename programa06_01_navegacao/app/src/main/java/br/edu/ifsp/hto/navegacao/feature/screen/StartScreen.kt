package br.edu.ifsp.hto.navegacao.feature.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.ifsp.hto.navegacao.ui.theme.NavegacaoTheme


@Composable
fun StartScreen(
    navigateToSecondScreen: (message: String?) -> Unit
) {
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Primeira Tela",
                style = TextStyle(
                    fontSize = 32.sp
                )
            )

            OutlinedTextField(
                value = message,
                onValueChange = { message = it },
                label = { Text("Entre com um texto.") }
            )

            Button(
                onClick = {
                    navigateToSecondScreen(if (message == "") null else message)
                }
            ) {
                Text("Ir para Segunda tela")
            }
        }
    }
}

@Preview
@Composable
private fun StartScreenPreview() {
    StartScreen(
        navigateToSecondScreen = { }
    )
}
