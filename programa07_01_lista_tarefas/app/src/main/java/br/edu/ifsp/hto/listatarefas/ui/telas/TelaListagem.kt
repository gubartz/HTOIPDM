package br.edu.ifsp.hto.listatarefas.ui.telas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.edu.ifsp.hto.dataclass.data.TarefaDAOImpl
import br.edu.ifsp.hto.listatarefas.domain.Tarefa
import br.edu.ifsp.hto.listatarefas.domain.tarefa1
import br.edu.ifsp.hto.listatarefas.domain.tarefa2
import br.edu.ifsp.hto.listatarefas.domain.tarefa3
import br.edu.ifsp.hto.listatarefas.ui.theme.ListaTarefasTheme

@Composable
fun TelaListagem(
    tarefaDAOImpl: TarefaDAOImpl,
    navegarParaTelaAdicionarEditar: (id: Long?) -> Unit,
) {

    var listaTarefas = tarefaDAOImpl.getAllTarefas()

    ListagemConteudo(
        listaTarefas = listaTarefas,
        onAdicionarClick = navegarParaTelaAdicionarEditar,
        onRemover = { tarefa ->
            tarefaDAOImpl.delete(tarefa)
        }
    )
}

@Composable
fun ListagemConteudo(
    listaTarefas: List<Tarefa>,
    onAdicionarClick: (id: Long?) -> Unit,
    onRemover: (tarefa: Tarefa) -> Unit
) {
    Scaffold(
        topBar = {
            Text(
                "Lista de Tarefas",
                style = TextStyle(
                    fontSize = 32.sp
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onAdicionarClick(null)
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.consumeWindowInsets(paddingValues),
            contentPadding = paddingValues
        ) {
            items(listaTarefas) { tarefa ->
                TarefaCard(
                    tarefa = tarefa,
                    onFinalizadaChange = { },
                    onEditarClick = {
                        onAdicionarClick(tarefa.id)
                    },
                    onRemoverClick = {
                        onRemover(tarefa)
                    }
                )
            }
        }
    }
}

@Composable
fun TarefaCard(
    tarefa: Tarefa,
    onFinalizadaChange: (Boolean) -> Unit,
    onEditarClick: () -> Unit,
    onRemoverClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Checkbox(
                checked = tarefa.ehFinalizada,
                onCheckedChange = onFinalizadaChange
            )
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = tarefa.titulo,
                    style = MaterialTheme.typography.titleLarge
                )

                tarefa.descricao?.let {
                    Text(
                        text = tarefa.descricao,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            Button(
                onClick = onEditarClick
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Editar",
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = onRemoverClick,
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Remover",
                )
            }
        }
    }
}

@Preview
@Composable
private fun ListagemConteudoPreview() {
    ListaTarefasTheme {
        ListagemConteudo(
            listaTarefas = listOf(
                tarefa1,
                tarefa2,
                tarefa3,
            ),
            onAdicionarClick = {},
            onRemover = {}
        )
    }
}