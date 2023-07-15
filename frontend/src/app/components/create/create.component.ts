import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { Todo } from "src/app/models/todo";
import { TodoService } from "src/app/services/todo.service";

@Component({
  selector: "app-create",
  templateUrl: "./create.component.html",
  styleUrls: ["./create.component.css"],
})
export class CreateComponent implements OnInit {
  todo: Todo = {
    titulo: "",
    descricao: "",
    dataParaFinalizar: new Date(),
    finalizado: false,
  };

  constructor(private router: Router, private service: TodoService) {}

  ngOnInit(): void {}

  create(): void {
    this.formataData();

    this.service.create(this.todo).subscribe({
      complete: () => {
        this.service.message("To-do criado com sucesso!");
        this.router.navigate([""]);
      },
      error: () => {
        this.service.message("Falha ao criar To-do");
        this.router.navigate([""]);
      },
    });
  }

  cancel(): void {
    this.router.navigate([""]);
  }

  formataData(): void {
    let data = new Date(this.todo.dataParaFinalizar).toLocaleDateString();
    this.todo.dataParaFinalizar = data;
  }
}
