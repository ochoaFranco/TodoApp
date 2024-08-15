const getTaskDetails = async () => {
    const urlParams = new URLSearchParams(window.location.search);
    const taskId = urlParams.get('id');
    
    if (!taskId) {
        console.error('No task ID provided');
        return;
    }

    try {
        const response = await fetch(`http://127.0.0.1:8080/tasks/${taskId}`);
        if (!response.ok) {
            throw new Error('Network response was not ok.');
        }
        const task = await response.json();
        displayTaskDetails(task);
    } catch (error) {
        console.error('There was a problem with fetching task details:', error);
    }
};

const displayTaskDetails = (task) => {
    const taskDetails = document.getElementById('task-details');
    taskDetails.innerHTML = `
        <h2>${task.title}</h2>
        <p><strong>Description:</strong> ${task.description}</p>
        <p><strong>Due Date:</strong> ${task.due_date}</p>
        <p><strong>Status:</strong> ${task.completed ? 'Finished' : 'Not finished yet'}</p>
        <p><strong>Category:</strong> ${task.categoryName}</p>
    `;
};

// Fetch and display task details on page load
window.onload = getTaskDetails;
