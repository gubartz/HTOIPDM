package br.edu.ifsp.hto.intencao_voto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
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
import br.edu.ifsp.hto.intencao_voto.ui.theme.IntencaoVotoTheme
import kotlin.math.pow
import kotlin.math.round

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IntencaoVotoTheme {
                IntencaoVoto(
                )
            }
        }
    }
}

@Composable
fun IntencaoVoto() {
    var votos1 by remember { mutableStateOf(0) }
    var votos2 by remember { mutableStateOf(0) }
    var votos3 by remember { mutableStateOf(0) }

    var per1 by remember { mutableStateOf(0.0) }
    var per2 by remember { mutableStateOf(0.0) }
    var per3 by remember { mutableStateOf(0.0) }

    var mostrarResultados by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeContentPadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Intenção de Votos",
                style = TextStyle(
                    fontSize = 32.sp
                )
            )
            Button(onClick = {
                votos1 += 1
            }) { Text("Candidato 1") }
            Button(onClick = {
                votos2 += 1
            }) { Text("Candidato 2") }

            Button(onClick = {
                votos3 += 1
            }) { Text("Candidato 3") }

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth()
            )

            Button(onClick = {
                val total = votos1 + votos2 + votos3

                if (total > 0) {
                    per1 = roundToDecimals(((votos1 / total.toDouble()) * 100), 2)
                    per2 = roundToDecimals(((votos2 / total.toDouble()) * 100), 2)
                    per3 = roundToDecimals(((votos3 / total.toDouble()) * 100), 2)
                }

                mostrarResultados = true
            }) {
                Text("Totalizar")
            }

            Button(onClick = {
                votos1 = 0
                votos2 = 0
                votos3 = 0
                per1 = 0.0
                per2 = 0.0
                per3 = 0.0

                mostrarResultados = false
            }) {
                Text("Reiniciar")
            }

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
            )

            Text(
                text = "Totais",
                style = TextStyle(
                    fontSize = 32.sp
                )
            )

            if (mostrarResultados) {
                Text("Candidato 1: $votos1 - ${per1}%")
                Text("Candidato 2: $votos2 - ${per2}%")
                Text("Candidato 3: $votos3 - ${per3}%")
            } else{
                Text("Candidato 1: 0 - 0%")
                Text("Candidato 2: 0 - 0%")
                Text("Candidato 3: 0 - 0%")
            }
        }
    }
}

fun roundToDecimals(value: Double, decimals: Int): Double {
    val factor = 10.0.pow(decimals)
    return round(value * factor) / factor
}

@Preview(showBackground = true)
@Composable
fun IntencaoVotoPreview() {
    IntencaoVotoTheme {
        IntencaoVoto()
    }
}