package br.edu.ifsp.hto.gasolinaoualcool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.text.KeyboardOptions
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
            .safeDrawingPadding(),
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

            /*
             TODO(1) Removemos o botão e como as variáveis são observáveis, a medida que os valores
              são digitados na interface gráfica o cálculo é realizado.
             */
            if (precoAlcool.isNotBlank() && precoGasolina.isNotBlank()) {
                melhorCombustivel = if (precoAlcool.toDouble() / precoGasolina.toDouble() < 0.7) {
                    "Álcool"
                } else {
                    "Gasolina"
                }
            }

            Text(
                text = melhorCombustivel,
                style = TextStyle(
                    color = Color.Red,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            TextField(
                value = precoGasolina,
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