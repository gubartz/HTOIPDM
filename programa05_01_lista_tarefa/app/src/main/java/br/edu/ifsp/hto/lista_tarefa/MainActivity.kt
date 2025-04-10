package br.edu.ifsp.hto.lista_tarefa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.ifsp.hto.lista_tarefa.ui.theme.ListaTarefaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListaTarefaTheme {
                ListaTarefaApp()
            }
        }
    }
}

@Composable
fun ListaTarefaApp() {
    // TODO(1) Declaramos uma variável observável para manter o texto da tarefa digitada.
    var tarefa by remember { mutableStateOf("") }

    /*
     TODO(2) Declaramos uma variável observável de uma lista de strings. Isso significa que qualquer
      inclusão, alteraçaõ ou remoção da lista terá reflexo na Interface. O <String> é um generics e
      indica que a lista só pode manter elementos do tipo String.
     */
    val tarefas = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Lista de Tarefas",
                style = TextStyle(
                    fontSize = 32.sp
                )
            )
            OutlinedTextField(
                value = tarefa,
                onValueChange = {
                    tarefa = it
                },
                label = {
                    Text("Descrição")
                }
            )
            Button(
                onClick = {
                    // TODO(3) Ao clicar neste botão o texto que se encontra no OutlinedTextField é
                    //  inserido na lista. A inserção só ocorre se o campo não estiver vazio.
                    if (tarefa.isNotBlank()) {
                        tarefas.add(tarefa)
                        // TODO(4) Após inserir a tarefa limpamos o campo.
                        tarefa = ""
                    }
                }
            ) {
                Text("Adicionar")
            }
            // TODO(5) Colocamos uma barra horizontal para divisão.
            HorizontalDivider(
                modifier = Modifier
                    // TODO(6) Essa barra ocupará 90% do espaço horizontal disponível.
                    .fillMaxWidth(0.9f),
                // TODO(7) A grossura dessa barra é de 16.dp
                thickness = 16.dp,
            )
            // TODO(8) Utilizamos um LazyColumn para iterar sobre a lista e mostrar seus valores.
            //  Como a lista é observável, quando há inclusão de um item a interface é
            //  automaticamente atualizada.
            LazyColumn {
                items(tarefas) { tarefa ->
                    // TODO(9) Cada item que será mostrado na lista é um composable, neste caso
                    //  um Text.
                    Text(
                        tarefa,
                        style = TextStyle(
                            fontSize = 40.sp
                        )
                    )
                    HorizontalDivider(
                        modifier = Modifier
                            // TODO(10) Essa barra ocupará 90% do espaço horizontal disponível.
                            .fillMaxWidth(0.9f)
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ListaTarefaAppPreview() {
    ListaTarefaTheme {
        ListaTarefaApp()
    }
}