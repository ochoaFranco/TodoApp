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
            <p><strong>Description:</strong> ${task.description}</p>
            <p><strong>Due Date:</strong> ${task.due_date}</p>
            <p><strong>Status:</strong> ${completedStatus}</p>
            <p><strong>Category:</strong> ${task.categoryName}</p
        `;
        const hr = document.createElement('hr');
        taskList.appendChild(hr);
        taskList.appendChild(div);
    })
}



const getCategories = async () => {
    const url = 'http://127.0.0.1:8080/categories';
    try {
        const response = await fetch(url);
        if (!response.ok){
            throw new Error('Network response was not ok.');
        }
        const data = await response.json();
        displayCategories(data);
    } catch(error) {
        console.log('There has been a problem with your fetch operation', error);
    }
};

const displayCategories = (categories) => {
    const categoryList = document.getElementById("categoryList");
    categoryList.innerHTML = ''; // clear previous content.

    categories.forEach(category => {
        const div = document.createElement('div');
        div.textContent = category.name;
        categoryList.appendChild(div);

    });
}