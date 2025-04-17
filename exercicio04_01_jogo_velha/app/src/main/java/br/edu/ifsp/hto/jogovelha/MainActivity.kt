package br.edu.ifsp.hto.jogovelha

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.ifsp.hto.jogovelha.ui.theme.JogoVelhaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JogoVelhaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    JogoVelha(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun JogoVelha(modifier: Modifier = Modifier) {
    var jogadorAtual by remember { mutableStateOf("X") }
    var tabuleiro = remember {
        mutableStateListOf(
            mutableStateListOf("", "", ""),
            mutableStateListOf("", "", ""),
            mutableStateListOf("", "", "")
        )
    }
    var ganhador by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Jogo da Velha",
            fontSize = 52.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.size(32.dp))
        for (i in 0..2) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                for (j in 0..2) {
                    Button(
                        modifier = Modifier
                            .size(100.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.LightGray,
                            contentColor = Color.Black
                        ),
                        shape = RectangleShape,
                        onClick = {
                            if (tabuleiro[i][j].isBlank()) {
                                tabuleiro[i][j] = jogadorAtual
                                jogadorAtual = if (jogadorAtual == "X") "O" else "X"
                                ganhador = verificarGanhador(tabuleiro)
                            }
                        },
                    ) {
                        Text(
                            tabuleiro[i][j],
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                }
            }
            Spacer(modifier = Modifier.size(8.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            if (ganhador.isNotBlank()) "Ganhador foi o $ganhador" else " ",
            style = TextStyle(
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Red
            )
        )
        Button(
            onClick = {
                jogadorAtual = "X"
                for (i in tabuleiro.indices) {
                    for (j in tabuleiro[i].indices) {
                        tabuleiro[i][j] = ""
                    }
                }
            }) {
            Text("Reiniciar",
                style = TextStyle(
                    fontSize = 32.sp
                ))
        }
    }
}

fun verificarGanhador(tabuleiro: List<List<String>>): String {
    val linhas = tabuleiro.size // quantidade de linhas

    for (i in 0 until linhas) {
        // linhas
        if (tabuleiro[i][0].isNotBlank()) {
            if (tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2]) {
                return tabuleiro[i][0]
            }
        }

        // colunas
        if (tabuleiro[0][i].isNotBlank()) {
            if (tabuleiro[0][i] == tabuleiro[1][i] && tabuleiro[1][i] == tabuleiro[2][i]) {
                return tabuleiro[0][i]
            }
        }
    }

    // Diagonal principal
    if (tabuleiro[0][0].isNotBlank() && tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2]) {
        return tabuleiro[0][0]
    }

    // Diagonal
    if (tabuleiro[0][2].isNotBlank() && tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][0]) {
        return tabuleiro[0][2]
    }

    return ""
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JogoVelha()
}