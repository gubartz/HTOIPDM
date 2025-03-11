package br.edu.ifsp.programa02_01_contador

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
import br.edu.ifsp.programa02_01_contador.ui.theme.Programa02_01_contadorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Programa02_01_contadorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Contador(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Contador(modifier: Modifier = Modifier) {
    // 1. Declaramos contador como um estado mutável e observável.
    // Qualquer alteração no conteúdo da variável, por meio de programação,
    // atualiza seu valor na tela (recomposição).
    var contador by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 2. É necessário converter o número para texto (String)
        Text(contador.toString())
        Button(onClick = {
            // 3. Realiza o incremento
            contador++
        }) {
            Text("Incrementar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContadorPreview() {
    Programa02_01_contadorTheme {
        Contador()
    }
}