const getTasks = async () => {
    const url = 'http://127.0.0.1:8080/tasks'
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
        // setting the content with all task attributes.
        div.innerHTML = `
            <h4><a href="task-description.html?id=${task.id}">${task.title}</a></h4>
             <button class="btn btn-edit" onclick="editTask(${task.id})">Edit</button>
             <button class="btn btn-delete" onclick="deleteTask(${task.id})">Delete</button>
        `;
        console.log('task id: ' + task.id);
        console.log('task title: ' + task.title);
        console.log('task description: ' + task.description);
        
        const hr = document.createElement('hr');
        taskList.appendChild(hr);
        taskList.appendChild(div);
    })
}

const editTask = (taskId) => {
    // Redirect to edit page or show a form for editing
    window.location.href = `edit-task.html?id=${taskId}`;
}

const deleteTask = async (taskId) => {
    try {
        const response = await fetch(`http://127.0.0.1:8080/tasks/delete/${taskId}`, {
            method: 'DELETE',
        });
        if (!response.ok) {
            throw new Error('Network response was not ok.');
        }
        alert('Task deleted successfully!');
        getTasks();
    } catch (error) {
        console.log('There was a problem with the task deletion', error);
    }
}

// Automatically load tasks when the page loads
window.onload = getTasks;