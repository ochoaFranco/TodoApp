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
            <h4>${task.title}</h4>
        `;
        const hr = document.createElement('hr');
        taskList.appendChild(hr);
        taskList.appendChild(div);
    })
}