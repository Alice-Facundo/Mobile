package com.example.taskliteapp.viewmodel

import com.example.taskliteapp.model.Task
import com.example.taskliteapp.model.TaskFilter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope // Import viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
// Remove these if using viewModelScope:
// import kotlinx.coroutines.CoroutineScope
// import kotlinx.coroutines.Dispatchers

class DesafioTaskViewModel : ViewModel() {
    private val _tasks =
        MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow <List<Task>> = _tasks
    private val _filter = MutableStateFlow(TaskFilter .ALL)
    val filteredTasks : StateFlow <List<Task>> =
        combine(_tasks, _filter) { tasks, filter ->
            when (filter) {
                TaskFilter .ALL -> tasks
                TaskFilter .COMPLETED -> tasks.filter { it.isDone
                }
                TaskFilter .PENDING -> tasks.filter { !it.isDone }
            }
        }.stateIn(
            scope = viewModelScope, // Using viewModelScope for lifecycle awareness
            started = SharingStarted .WhileSubscribed(5000), // Better for active UI, keeps flow active for 5s after no collectors
            initialValue = emptyList()
        )
    private var nextId = 1
    fun onAddTask(title: String) {
        if (title.isNotBlank()) {
            val newTask = Task(id = nextId++, title = title, isDone =
                false)
            _tasks.value = _tasks.value + newTask
        }
    }
    fun onTaskChecked(id: Int) {
        _tasks.value = _tasks.value.map {
            if (it.id == id) it.copy(isDone = !it.isDone) else it
        }
    }
    fun onDeleteTask(id: Int) {
        _tasks.value = _tasks.value.filterNot { it.id == id }
    }
    fun onFilterSelected(filter: TaskFilter) {
        _filter.value = filter
    }
}