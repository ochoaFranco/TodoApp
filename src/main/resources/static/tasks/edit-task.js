import { showToast } from "../utils/utils.js";

let taskId;
document.addEventListener('DOMContentLoaded', () => {
    const urlParams = new URLSearchParams(window.location.search);
    taskId = urlParams.get('id');
    // fetch task details from backend.
    fetch(`http://127.0.0.1:8080/tasks/${taskId}`)
        .then(response => response.json())
        .then(task => {
            //populate form fields with task data.
            document.getElementById('title').value = task.title;
            document.getElementById('description').value = task.description;
            document.getElementById('due_date').value = task.due_date;
            document.getElementById('categoryName').value = task.categoryName;
        })
        .catch(error => console.error('error fetching task data', error))
});

document.getElementById('edit-task-form').addEventListener('submit', (event) => {
    event.preventDefault();
    
    const updatedTask = {
        title: document.getElementById('title').value,
        description: document.getElementById('description').value,
        due_date: document.getElementById('due_date').value,
        categoryId: document.getElementById('categoryId').value,
    };
    // Send updated task data to backend.
    fetch(`http://127.0.0.1:8080/tasks/edit/${taskId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(updatedTask)
    })
    .then(response => {
        if (!response.ok) {
            console.log('Error updating task: ', response.statusText);
        } else {
            showToast('Task updated successfully!')
            window.location.href = 'index.html'; // redirect to task list
        }
    })
    .catch(error => console.error('Error: ', error));
});