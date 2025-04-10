package br.edu.ifsp.hto.lista_tarefa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
    var tarefa by remember { mutableStateOf("") }
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
                    if (tarefa.isNotBlank()) {
                        tarefas.add(tarefa)
                    }
                    tarefa = ""
                }
            ) {
                Text("Adicionar")
            }
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth(0.9f),
                thickness = 16.dp,
            )
            LazyColumn {
                items(tarefas) { tarefa ->
                    // TODO(8) Utilizamos o TarefaCard ao invés de definir o layout
                    //  dentro na LazyColumn. O código fica mais organizado dessa maneira e o
                    //  layout independente.
                    TarefaCard(tarefa)
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