import { showToast } from "../utils/utils.js";

const getCompletedTasks = async () => {
    const url = 'http://127.0.0.1:8080/tasks/completed';
    try {
        const response = await fetch(url);
        if (!response.ok) {
            throw new Error('Network response was not ok.');
        }
        const data = await response.json();
        console.log(data);

        displayCompletedTasks(data);
    } catch (error) {
        console.log('There has been a problem with your fetch operation', error);
    }
}

const displayCompletedTasks = (tasks) => {
    const taskList = document.getElementById("completed-tasks");
    taskList.innerHTML = '';
    tasks.forEach(task => {
        const div = document.createElement('div');
        div.className = 'task-item';
        const completedStatus = task.completed ? 'Finished' : 'Not finished yet';
        div.innerHTML = `
        <div class="task-header">
            <h4><a href="task-description.html?id=${task.id}">${task.title}</a></h4>
            <span class="task-category">Category: ${task.categoryName}</span>
        </div>
        <button class="btn btn-toDo" data-task-id="${task.id}">Not Finished</button>
        <p class="task-status ${task.completed ? 'status-finished' : 'status-unfinished'}">Status: ${completedStatus}</p>
    `;
        
        const hr = document.createElement('hr');
        taskList.appendChild(hr);
        taskList.appendChild(div);
    });

    // Add event listeners to edit button
    document.querySelectorAll('.btn-toDo').forEach(button => {
        button.addEventListener('click', () => {
            const taskId = button.getAttribute('data-task-id');
            toDo(taskId);
        });
    });
}


// Mark a task as not completed.
const toDo = async (taskId) => {
    try {
        const response = await fetch(`http://127.0.0.1:8080/tasks/${taskId}/uncompleted`,{
            method: 'PATCH'
        });
        
        if (!response.ok) { 
            throw new Error('Network response was not ok.');
        }
        showToast('Task mark as uncompleted successfully!')
        getCompletedTasks();
    } catch(error) {
        console.log('There was a problem with the task', error);
    }
}

// Automatically load completed tasks when the page loads
window.onload = getCompletedTasks;
