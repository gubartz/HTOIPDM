package br.edu.ifsp.hto.operacoesbasicas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.ifsp.hto.operacoesbasicas.ui.theme.OperacoesBasicasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OperacoesBasicasTheme {
                OperacoesBasicas(
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
fun OperacoesBasicas(modifier: Modifier = Modifier) {
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = num1,
            onValueChange = { novoTexto ->
                if (novoTexto.all { it.isDigit() }) {
                    num1 = novoTexto
                }
            },
            label = { Text("Número 1") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = num2,
            onValueChange = { novoTexto ->
                if (novoTexto.all { it.isDigit() }) {
                    num2 = novoTexto
                }
            },
            label = { Text("Número 2") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Button(onClick = {
                val dNum1 = num1.toDoubleOrNull() ?: 0.0
                val dNum2 = num2.toDoubleOrNull() ?: 0.0
                result = (dNum1 + dNum2).toString()
            }) {
                Text(text = "+")
            }
            Button(onClick = {
                val dNum1 = num1.toDoubleOrNull() ?: 0.0
                val dNum2 = num2.toDoubleOrNull() ?: 0.0
                result = (dNum1 - dNum2).toString()
            }) {
                Text(text = "-")
            }
            Button(onClick = {
                val dNum1 = num1.toDoubleOrNull() ?: 0.0
                val dNum2 = num2.toDoubleOrNull() ?: 0.0
                result = (dNum1 * dNum2).toString()
            }) {
                Text(text = "*")
            }
            Button(onClick = {
                val dNum1 = num1.toDoubleOrNull() ?: 0.0
                val dNum2 = num2.toDoubleOrNull() ?: 0.0
                result = (dNum1 / dNum2).toString()
            }) {
                Text(text = "/")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Resultado: $result"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OperacoesBasicasPreview() {
    OperacoesBasicasTheme {
        OperacoesBasicas()
    }
}