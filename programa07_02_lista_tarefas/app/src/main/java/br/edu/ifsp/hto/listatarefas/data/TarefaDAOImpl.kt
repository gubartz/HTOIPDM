package br.edu.ifsp.hto.dataclass.data

import br.edu.ifsp.hto.listatarefas.domain.Tarefa

class TarefaDAOImpl : TarefaDAO {
    private val tarefaList = mutableListOf<Tarefa>()
    private var identificadorAtual: Long = 1

    override fun insert(tarefa: Tarefa) {
        val novaTarefa = tarefa.copy(id = identificadorAtual++)
        tarefaList.add(novaTarefa)
    }

    override fun insertMulti(tarefas: MutableList<Tarefa>) {
        for (tarefa in tarefas) {
            insert(tarefa)
        }
    }

    override fun update(tarefa: Tarefa) {
        val index = tarefaList.indexOfFirst { it.id == tarefa.id }

        if (index != -1) {
            tarefaList[index] = tarefa
        }
    }

    override fun delete(tarefa: Tarefa) {
        tarefaList.removeIf { it.id == tarefa.id }
    }

    override fun getAllTarefas(): List<Tarefa> {
        return tarefaList
    }

    override fun getTarefaById(tarefaId: Long): Tarefa? {
        return tarefaList.find { it.id == tarefaId }
    }


}