package br.edu.ifsp.hto.lista_tarefa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
    // TODO(8) Alteramos o identificador da varíavel para tfTarefa, para não ter problema com a
    //  variável tarefa do itemsIndexed do LazyColumn.
    var tfTarefa by remember { mutableStateOf("") }
    val tarefas = remember { mutableStateListOf<String>() }

    // TODO(9) Precisamos manter um estado que representa qual índice está em edição. Iniciamos com
    //  -1, pois nenhum item está sendo editado. Também vamos convencionar que -1 sempre significa
    //  que um item não está sendo editado.
    var indiceEdicao by remember { mutableStateOf(-1) }

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
                value = tfTarefa,
                onValueChange = {
                    tfTarefa = it
                },
                label = {
                    Text("Descrição")
                }
            )
            Button(
                onClick = {
                    if (tfTarefa.isNotBlank()) {
                        // TODO(14) Se existir um índice em edição, precisamos substituir o valor ao
                        //  invés de adiconar um novo.
                        if (indiceEdicao == -1) {
                            tarefas.add(tfTarefa)
                        } else {
                            // TODO(15) Atualizamos um elemento da lista com base no índice clicado
                            //  pelo usuário e com o conteúdo do OutlinedTextField.
                            tarefas[indiceEdicao] = tfTarefa
                            // TODO(16) Após a edição indicamos que nenhum item está sendo editado
                            //  atribuindo -1, conforme convencionado no começo do código (passo 9).
                            indiceEdicao = -1
                        }
                        tfTarefa = ""
                    }
                }
            ) {
                Text(
                    // TODO(10) Se algum item estiver sendo editado o texto do botão será Adicionar,
                    //  caso contrário será Editar.
                    text = if (indiceEdicao == -1) "Adicionar" else "Editar"
                )
            }
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth(0.9f),
                thickness = 16.dp,
            )
            LazyColumn {
                // TODO(6) Alteramos para utilizar o itemsIndexed, que permite acesso tanto ao
                //  conteúdo da lista quando ao índice.
                itemsIndexed(tarefas) { index, tarefa ->
                    TarefaCard(
                        tarefa,
                        // TODO(7) Ao clicar em remoção, removemos o item da lista de acordo com
                        //  seu índice.
                        onExcluir = {
                            tarefas.removeAt(index)
                        },
                        // TODO(11) Definimos a ação da edição.
                        onEditar = {
                            // TODO(12) Atribuímos para o campo de texto o texto correspondente a
                            //  tarefa clicada.
                            tfTarefa = tarefa
                            // TODO(13) Atribuímos o índice clicado para o indiceEdicao. Agora
                            //  sabemos qual item está sendo editado.
                            indiceEdicao = index
                        },
                    )
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