package br.edu.ifsp.hto.listatarefas.ui.telas

import androidx.lifecycle.ViewModel
import br.edu.ifsp.hto.dataclass.data.TarefaDAOImpl
import br.edu.ifsp.hto.listatarefas.domain.Tarefa
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ListagemViewModel(private val dao: TarefaDAOImpl) : ViewModel() {
    private val _tarefas = MutableStateFlow(dao.getAllTarefas())
    val tarefas: StateFlow<List<Tarefa>> = _tarefas.asStateFlow()

    fun adicionar(titulo: String, descricao: String) {
        dao.insert(
            Tarefa(
                id = 0,
                titulo = titulo,
                descricao = descricao,
                ehFinalizada = false
            )
        )
        _tarefas.value = dao.getAllTarefas()
    }

    fun remover(tarefa: Tarefa) {
        dao.delete(tarefa)
        _tarefas.value = dao.getAllTarefas()
    }

    fun atualizar(tarefa: Tarefa) {
        dao.update(tarefa)
        _tarefas.value = dao.getAllTarefas()
    }
}