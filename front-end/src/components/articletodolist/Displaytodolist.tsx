import React from "react";

export default function Displaytodolist({ allTodolist }) {
  return allTodolist.map((todolist, i) => {
    return (
      <div key={i}>
        <h1>todolist </h1>

        <div>
        Title {todolist.title.value}
          Description {todolist.Description.value}
        </div>
      </div>
    );
  });
}
