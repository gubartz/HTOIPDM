package br.edu.ifsp.programa02_02_contador

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.edu.ifsp.programa02_02_contador.ui.theme.Programa02_02_contadorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Programa02_02_contadorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Contador(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Contador(name: String, modifier: Modifier = Modifier) {
    var contador by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(contador.toString())
        Button(onClick = {
            contador++
        }) {
            Text("Incrementar")
        }
        // 1. Copiamos e colamos o código do botão acima
        Button(onClick = {
            // 2. Trocamos para decremento
            // 3. Adicionamos um condicional para não permitir valores negativos
            if (contador > 0) {
                contador--
            }
        }) {
            Text("Incrementar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContadorPreview() {
    Programa02_02_contadorTheme {
        Contador("Android")
    }
}