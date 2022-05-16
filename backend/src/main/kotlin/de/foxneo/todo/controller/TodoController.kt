package de.foxneo.todo.controller

import de.foxneo.todo.entities.Todo
import de.foxneo.todo.entities.TodoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*
import java.util.Optional
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@RestController
@RequestMapping("/todos")
class TodoController (val todoRepository: TodoRepository) : Filter  {

    @GetMapping
    fun getTodos() = todoRepository.findAll();

    @GetMapping("/{todoId}")
    fun getTodo(@PathVariable("todoId") todoId: Long): Optional<Todo> {
        return todoRepository.findById(todoId)
    }

    @PostMapping
    fun newTodo(@RequestBody todo: Todo): Todo {
        return todoRepository.save(todo)
    }

    @PutMapping("/{todoId}")
    fun updateTodo(@PathVariable("todoId") todoId: Long, @RequestBody updatedTodo: Todo): Todo? {
        val oldTodo = todoRepository.findByIdOrNull(todoId)
        if(oldTodo == null){
            return oldTodo
        }
        return todoRepository.save(updatedTodo)
    }
    
    @DeleteMapping("/{todoId}")
    fun deleteTodo(@PathVariable("todoId") todoId: Long){
        todoRepository.deleteById(todoId)
    }

    override fun doFilter(req: ServletRequest?, res: ServletResponse?, chain: FilterChain?) {
        val request = req as HttpServletRequest
        val response = res as HttpServletResponse

        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"))
        response.setHeader("Access-Control-Allow-Credentials", "true")
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE")
        response.setHeader("Access-Control-Max-Age", "3600")
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me")

        chain?.doFilter(req, res)
    }

}