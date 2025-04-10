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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.ifsp.programa02_02_contador.ui.theme.Programa02_02_contadorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Programa02_02_contadorTheme {
                ContadorApp()
            }
        }
    }
}

@Composable
fun ContadorApp() {
    var contador by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(
                text = "O valor atual é $contador",
                style = TextStyle(
                    fontSize = 20.sp
                )
            )
            Button(
                onClick = {
                    contador++
                }
            ) {
                Text(
                    text = "Incrementar",
                    style = TextStyle(
                        fontSize = 20.sp
                    )
                )
            }
            // TODO(1) Copiamos e colamos o código do botão acima de incremento.
            Button(
                onClick = {
                    /*
                     TODO(2) Trocamos o código para realizar um decremento e adicionamos um condicional para
                      não permitir valores negativos
                     */
                    if (contador > 0) {
                        contador--
                    }
                }
            ) {
                Text(
                    text = "Decrementar",
                    style = TextStyle(
                        fontSize = 20.sp
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContadorAppPreview() {
    Programa02_02_contadorTheme {
        ContadorApp()
    }
}