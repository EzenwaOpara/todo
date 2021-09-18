package com.benjie.rest;

import com.benjie.entity.Todo;
import com.benjie.service.TodoService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/*
 * Created by OPARA BENJAMIN
 * On 9/6/2021 - 12:02 PM
 */

@Path("todo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TodoRest {

    @Inject
    TodoService todoService;

    @POST
    @Path("new")
    public Response createTodo(Todo todo) {
        todoService.createTodo(todo);
        return Response.ok(todo).build();
    }

    @PUT
    @Path("update")
    public Response updateTodo(Todo todo) {
        todoService.updateTodo(todo);
        return Response.ok(todo).build();
    }

    @GET
    @Path("{id}")
    public Todo getTodo(@PathParam("id") Long id) {
        return todoService.findTodoById(id);
    }

    @GET
    @Path("list")
    public List<Todo> getTodos() {
        return todoService.getTodos();
    }

    @POST
    @Path("status")
    public Response markAsComplete(@QueryParam("id") Long id) {
        Todo todo = todoService.findTodoById(id);
        todo.setCompleted(true);
        todoService.updateTodo(todo);
        return Response.ok(todo).build();
    }
}
