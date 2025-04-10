package br.edu.ifsp.hto.gasolinaoualcool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.windowInsetsPadding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.ifsp.hto.gasolinaoualcool.ui.theme.GasolinaOuAlcoolTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GasolinaOuAlcoolTheme {
                GasolinaOuAlcoolApp()
            }
        }
    }
}

@Composable
fun GasolinaOuAlcoolApp() {
    /*
     TODO(1) Definimos as variáveis observáveis para os preços da gasolina e do álcool, bem como
      para o melhor combustível.
     */
    var melhorCombustivel by remember { mutableStateOf("") }
    var precoGasolina by remember { mutableStateOf("") }
    var precoAlcool by remember { mutableStateOf("") }

    val textFieldStyle = TextStyle(
        fontSize = 18.sp
    )

    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF2196F3))
            // TODO(2) O safeDrawingPadding garante que a coluna vai ser respeitar a barra do topo.
            .safeDrawingPadding(),
        // TODO(3) Colocamos o alinhamento dos objetos a partir do topo.
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Gasolina ou Álcool?",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 32.sp
                )
            )

            Text(
                text = melhorCombustivel,
                /*
                 TODO(4) O melhor combustível será mostrado em vermelho, com tamanho 40 e em
                  negrito (FontWeight.Bold).
                 */
                style = TextStyle(
                    color = Color.Red,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            TextField(
                value = precoGasolina,
                /*
                TODO(5) O it é uma forma abreviada de se referir ao valor que foi passado para a
                 função dentro das chaves {}.
                 */
                onValueChange = {
                    precoGasolina = it
                },
                label = {
                    Text(
                        text = "Preço da Gasolina",
                        style = textFieldStyle
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = textFieldStyle
            )

            TextField(
                value = precoAlcool,
                onValueChange = {
                    precoAlcool = it
                },
                label = {
                    Text(
                        text = "Preço da Álcool",
                        style = textFieldStyle
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = textFieldStyle
            )

            Button(onClick = {
                /*
                TODO(6) O cálculo só deve ser feito se os preços do álcool e da gasolina tenham sido
                 fornecidos.
                 */
                if (precoAlcool.isNotBlank() && precoGasolina.isNotBlank()) {
                    /*
                     TODO(7) Essa é uma construção válida em kotlin, chamada de expressão
                      condicional if. Ou seja, o if não é apenas uma estrutura de controle, mas
                      retorna um valor podendo ser uma expressão.
                     */
                    melhorCombustivel = if (precoAlcool.toDouble() / precoGasolina.toDouble() < 0.7) {
                        "Álcool"
                    } else {
                        "Gasolina"
                    }
                }
            }) {
                Text(
                    text = "Calcular",
                    style = textFieldStyle
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GasolinaOuAlcoolAppPreview() {
    GasolinaOuAlcoolTheme {
        GasolinaOuAlcoolApp()
    }
}