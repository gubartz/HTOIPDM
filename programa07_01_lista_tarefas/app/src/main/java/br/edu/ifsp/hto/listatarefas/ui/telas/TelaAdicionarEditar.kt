package br.edu.ifsp.hto.listatarefas.ui.telas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.ifsp.hto.dataclass.data.TarefaDAOImpl
import br.edu.ifsp.hto.listatarefas.domain.Tarefa
import br.edu.ifsp.hto.listatarefas.ui.theme.ListaTarefasTheme

@Composable
fun TelaAdicionarEditar(
    tarefaDAOImpl: TarefaDAOImpl,
    onBack: () -> Unit,
    id: Long?
) {
    var titulo by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var ehFinalizada by remember { mutableStateOf(false) }

    if (id != null) {
        LaunchedEffect(id) {
            tarefaDAOImpl.getTarefaById(id)?.let { tarefa ->
                titulo = tarefa.titulo
                descricao = tarefa.descricao ?: ""
            }
        }
    }

    AdicionarEditarConteudo(
        id = id,
        titulo = titulo,
        descricao = descricao,
        onTituloChange = { titulo = it },
        onDescricaoChange = { descricao = it },
        onSalvar = {
            if (id == null) {
                var tarefa = Tarefa(
                    id = 0,
                    titulo = titulo,
                    descricao = descricao,
                    ehFinalizada = ehFinalizada
                )
                tarefaDAOImpl.insert(tarefa)
                onBack()
            } else {
                var tarefa = Tarefa(
                    id = id,
                    titulo = titulo,
                    descricao = descricao,
                    ehFinalizada = ehFinalizada
                )
                tarefaDAOImpl.update(tarefa)
                onBack()
            }
        },
    )
}

@Composable
fun AdicionarEditarConteudo(
    id: Long?,
    titulo: String,
    descricao: String?,
    onTituloChange: (String) -> Unit,
    onDescricaoChange: (String) -> Unit,
    onSalvar: () -> Unit,
) {
    Scaffold(
        topBar = {
            Text(
                if (id != null) "Editar Tarefa" else "Adiciona Tarefa",
                style = TextStyle(
                    fontSize = 32.sp
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onSalvar
            ) {
                Icon(Icons.Default.Check, contentDescription = "Salvar")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .consumeWindowInsets(paddingValues)
                .padding(paddingValues)
        ) {

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = titulo,
                onValueChange = onTituloChange,
                label = { Text("Título") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = descricao ?: "",
                onValueChange = onDescricaoChange,
                label = { Text("Descrição") }
            )
        }
    }
}

@Preview
@Composable
private fun AdicionarEditarPreview() {
    ListaTarefasTheme {

    }
}