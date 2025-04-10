package br.edu.ifsp.hto.lista_tarefa

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// TODO(1) Separamos e montamos um layout específico que representa um item da nossa LazyColumn.
@Composable
fun TarefaCard(tarefa: String) {
    // TODO(2) Utilizamos o Card para o layout de cada item.
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                tarefa,
                style = TextStyle(
                    fontSize = 40.sp
                )
            )
            // TODO(3) O Spacer adiciona um espaço vazio. O 1f significa 1 unit of weight
            //  1 unidade de peso. Ao indicar o valor 1 estamos indicando que o Spacer deve usar
            //  todo o espaço que sobrar, empurrando a Row que contém os botões para o final da
            //   linha pai.
            Spacer(modifier = Modifier.weight(1f))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                // TODO(4) Adicionamos um botão para editar o item.
                Button(
                    onClick = {},
                ) {
                    // TODO(5) O botão é um ícone com um lápis para editar.
                    Icon(
                        Icons.Default.Edit,
                        contentDescription = null
                    )
                }
                // TODO(6) Adicionamos um botão para excluir o item.
                Button(
                    onClick = {},
                ) {
                    // TODO(7) O botão é um ícone com uma lixeira para excluir.
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TarefaCardPreview() {
    TarefaCard("Tarefa")
}
