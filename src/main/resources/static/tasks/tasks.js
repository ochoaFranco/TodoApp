import { showToast } from "../utils/utils.js";

const getTasks = async () => {
    const url = 'http://127.0.0.1:8080/tasks/uncompleted'
    try {
        const response = await fetch(url);
        if (!response.ok) {
            throw new Error('Network response was not ok.');
        }
        const data = await response.json();
        console.log(data);
        
        displayTasks(data);
    } catch(error) {
        console.log('There has been a problem with your fetch operation', error);
    }
}

const displayTasks = (tasks) => {
    const taskList = document.getElementById("all-tasks")
    taskList.innerHTML = '';
    tasks.forEach(task => {
        const div = document.createElement('div');
        div.className = 'task-item';
        // formatting completed status
        const completedStatus = task.completed ? 'Finished' : 'Not finished yet';   
        div.innerHTML = `
        <div class="task-header">
            <h4><a href="/task-description.html?id=${task.id}">${task.title}</a></h4>
            <span class="task-category">Category: ${task.categoryName}</span>
        </div>
        <button class="btn btn-edit" data-task-id="${task.id}">Edit</button>
        <button class="btn btn-delete" data-task-id="${task.id}">Delete</button>
        <button class="btn btn-finish" data-task-id="${task.id}">finish</button>
        <p class="task-status ${task.completed ? 'status-finished' : 'status-unfinished'}">Status: ${completedStatus}</p>
    `;
        
        const hr = document.createElement('hr');
        taskList.appendChild(hr);
        taskList.appendChild(div);
    })

    // Add event listeners to edit button
    document.querySelectorAll('.btn-edit').forEach(button => {
        button.addEventListener('click', () => {
            const taskId = button.getAttribute('data-task-id');
            editTask(taskId);
        });
    });
    // Add event listeners to delete button
    document.querySelectorAll('.btn-delete').forEach(button => {
        button.addEventListener('click', () => {
            const taskId = button.getAttribute('data-task-id');
            deleteTask(taskId);
        });
    });
    // Add event listeners to finish button.
    document.querySelectorAll('.btn-finish').forEach(button => {
        button.addEventListener('click', () => {
            const taskId = button.getAttribute('data-task-id');
            finishTask(taskId);
        });
    });

}

// Mark a task as completed.
const finishTask = async (taskId) => {
    try {
        const response = await fetch(`http://127.0.0.1:8080/tasks/${taskId}/completed`,{
            method: 'PATCH'
        });
        
        if (!response.ok) {
            throw new Error('Network response was not ok.');
        }
        showToast('Task completed successfully!')
        getTasks();
    } catch(error) {
        console.log('There was a problem with the task', error);
    }
}

// Edit a task.
const editTask = (taskId) => {
    // Redirect to edit page or show a form for editing
    window.location.href = `edit-task.html?id=${taskId}`;
}

// Delete a task.
const deleteTask = async (taskId) => {
    try {
        const response = await fetch(`http://127.0.0.1:8080/tasks/delete/${taskId}`, {
            method: 'DELETE',
        });
        if (!response.ok) {
            throw new Error('Network response was not ok.');
        }
        showToast('Task deleted successfully!')
        getTasks();
    } catch (error) {
        console.log('There was a problem with the task deletion', error);
    }
}

// Automatically load tasks when the page loads
window.onload = getTasks;