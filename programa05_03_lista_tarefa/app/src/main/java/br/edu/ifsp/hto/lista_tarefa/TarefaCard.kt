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

@Composable
// TODO(1) A lista com os elementos é mantida no composable ListaTarefaApp, que está em
//  MainActivity. Não passamos a lista para ser manipulada pelo TarefaCard, pois isso o vincularia
//  a essa lista e ele não poderia mais ser reutilizado com outros composables. Além disso, isso
//  faria com que o estado da lista fosse manipulado em dois lugares, tornando difícil verificar,
//  no código, os locais em que ocorre alterações na lista.
fun TarefaCard(
    tarefa: String,
    // TODO(2) Definimos um parâmetro que recebe uma função para edição do item.
    onEditar: () -> Unit,
    // TODO(3) Definimos um parâmetro que recebe uma função para exclusão do item.
    onExcluir: () -> Unit
) {
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
            Spacer(modifier = Modifier.weight(1f))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    // TODO(4) Por padrão o onClick espera uma função sem parâmetros e sem retorno.
                    //  Foi exatamente o que definimos no parâmetro onEditar deste composable em.
                    //  Por isso podemos atribuí-lo direto sem necessidade de um lambda.
                    onClick = onEditar,
                ) {
                    Icon(
                        Icons.Default.Edit,
                        contentDescription = null
                    )
                }
                Button(
                    // TODO(5) Atribuímos o parâmetro onExcluir, que é uma função, ao onClick.
                    onClick = onExcluir,
                ) {
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
    TarefaCard(
        tarefa = "Tarefa 1",
        onEditar = {},
        onExcluir = {}
    )
}
