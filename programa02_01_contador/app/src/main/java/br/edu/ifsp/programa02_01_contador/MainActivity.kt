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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.ifsp.programa02_01_contador.ui.theme.Programa02_01_contadorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Programa02_01_contadorTheme {
                ContadorApp()
            }
        }
    }
}

@Composable
fun ContadorApp() {
    /*
    TODO(1). Declaramos a variável contador como um estado mutável e observável ao utilizar o
     mutableStateOf. Com iss, qualquer alteração no conteúdo da variável, seja por meio de
     programação ou na interface, atualiza seu valor na tela.
     */
    var contador by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        /*
         TODO(2) Criar uma coluna interna e usamos o Arrangement.spacedBy(12.dp) para que todos os
          elementos internos tenham um espaço entre eles de 12.dp. Essa coluna, por estar dentro da
          coluna externa (acima) será centralizada verticalmente e horizontalmente.
         */
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                /*
                TODO(3) A variável contador é um inteiro. Para mostrá-la na tela é necessário
                 converter seu conteúdo para um número. Assim ele pode ser mostrado na tela. Podemos
                 usar substituição de variável entre as áspas. Isso significa que o toString() da
                 varíavel contador será invocado. Desta maneria, não precisamos fazer concatenção de
                 um string com uma variável.
                 */
                text = "O valor atual é $contador",
                /*
                TODO(4) Definimos um estilo para o texto. O sp indica scale-independent pixels. O sp
                  é igual ao dp, mas ele respeita o tamanho de fonte que o usuário configurou
                  no celular.
                 */
                style = TextStyle(
                    fontSize = 20.sp
                )
            )
            Button(
                onClick = {
                    /*
                     TODO(5) Realiza o incremento de uma unidade na variável contador. Como essa
                      variável é observável o reflexo é imediato na tela, ocorrendo uma recomposição.
                     */
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
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContadorAppPreview() {
    Programa02_01_contadorTheme {
        ContadorApp()
    }
}