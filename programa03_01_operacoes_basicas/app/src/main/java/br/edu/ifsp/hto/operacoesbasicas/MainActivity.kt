package br.edu.ifsp.hto.operacoesbasicas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.ifsp.hto.operacoesbasicas.ui.theme.OperacoesBasicasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OperacoesBasicasTheme {
                OperacoesBasicasApp()
            }
        }
    }
}

@Composable
fun OperacoesBasicasApp() {

    // TODO(1) Declaramos uma variável observável para o número 1, número 2 e para o resultado.
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    // TODO(2) Um estilo pode ser declarado em uma variável se vamos utilizá-lo em vários lugares.
    val estiloTexto = TextStyle(
        fontSize = 20.sp
    )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Operações Básicas",
                // TODO(3) Atribuímos uma cor azul ao texto.
                style = TextStyle(
                    fontSize = 32.sp,
                    color = Color(0xFF651FFF)
                )
            )
            TextField(
                /*
                 TODO(4) O TextField é um campo para entrada de valores. No caso o valor do campo
                  está sendo associado à variável observável num1.
                 */
                value = num1,
                /*
                 TODO(5) O onValueChange recebe uma lambda em que o valor que o usuário vai
                  digitando é recebido como um parâmetro. Neste local que atualizamos o conteúdo de
                  nossa variável observável num1 com o que efetivamente é digitado pelo usuário na
                  interface.
                 */
                onValueChange = { valorDigitado ->
                    /*
                     TODO(6) O método all percorre cada caractere da string e aplica a condição
                      da lambda (it.isDigit() no seu exemplo). Se todos os caracteres forem dígitos,
                      ele retorna true; caso contrário, retorna false. Dessa maneira apenas
                      caracteres numéricos são aceitos no campo.
                     */
                    if (valorDigitado.all { it.isDigit() }) {
                        /*
                         TODO(7) O valor digitado pelo usuário é atribuído a variável observável
                          num1.
                         */
                        num1 = valorDigitado
                    }
                },
                label = {
                    Text(
                        "Número 1",
                        // TODO(8) O estilo defino na variável estiloTexto é aplicado no campo.
                        style = estiloTexto
                    )
                },
                /*
                 TODO(9) Ao entrar no campo apenas o teclado número é disponibilizado para o
                  usuário.
                 */
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                // TODO(10) O mesmo estilo é aplicado no contéudo que será digitado pelo usuário.
                textStyle = estiloTexto
            )
            // TODO(11) Aplicamos as mesmas definições para o número 2.
            TextField(
                value = num2,
                onValueChange = { valorDigitado ->
                    if (valorDigitado.all { it.isDigit() }) {
                        num2 = valorDigitado
                    }
                },
                label = {
                    Text(
                        "Número 2",
                        style = estiloTexto
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = estiloTexto
            )
            // TODO(12) Definimos uma linha para manter um botão para cada operação básica.
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Button(
                    onClick = {
                        /*
                        TODO(13) Realizamos a conversão de num1 e num2 com toDoubleOrNull, isso
                         significa que se a conversão falhar um valor nulo será retornado. Já o ?: é
                         chamado de operador elvis e serve para fornecer um valor padrão caso o
                         resultado da conversão seja nulo. Neste caso, se a conversão falhar, será usado
                         o valor 0.0.
                         */
                        val dNum1 = num1.toDoubleOrNull() ?: 0.0
                        val dNum2 = num2.toDoubleOrNull() ?: 0.0
                        resultado = (dNum1 + dNum2).toString()
                    }) {
                    Text(
                        text = "+",
                        style = estiloTexto
                    )
                }
                Button(
                    onClick = {
                        val dNum1 = num1.toDoubleOrNull() ?: 0.0
                        val dNum2 = num2.toDoubleOrNull() ?: 0.0
                        resultado = (dNum1 - dNum2).toString()
                    }) {
                    Text(
                        text = "-",
                        style = estiloTexto
                    )
                }
                Button(
                    onClick = {
                        val dNum1 = num1.toDoubleOrNull() ?: 0.0
                        val dNum2 = num2.toDoubleOrNull() ?: 0.0
                        resultado = (dNum1 * dNum2).toString()
                    }) {
                    Text(
                        text = "X",
                        style = estiloTexto
                    )
                }
                Button(
                    onClick = {
                        val dNum1 = num1.toDoubleOrNull() ?: 0.0
                        val dNum2 = num2.toDoubleOrNull() ?: 0.0
                        /*
                        TODO(14) Para a divisão verificamos se o denominado é igual a zero.
                         */
                        if (dNum2 == 0.0) {
                            resultado = "Divisão Inválida."
                        } else {
                            resultado = (dNum1 / dNum2).toString()
                        }

                    }) {
                    Text(
                        text = "÷",
                        style = estiloTexto
                    )
                }
            }
            Text(
                /*
                TODO(15) Utilizamos substituição de variável para apresentar o resultado. Como a
                 variável resultado é observável seu estado é atualizado automaticamente quando um
                 dos botões é apertado pelo usuário.
                 */
                text = "Resultado: $resultado",
                style = estiloTexto
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OperacoesBasicasPreview() {
    OperacoesBasicasTheme {
        OperacoesBasicasApp()
    }
}