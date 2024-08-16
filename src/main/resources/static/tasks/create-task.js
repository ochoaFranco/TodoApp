import { populateCategories } from "./categories.js";
import { showToast } from "../utils/utils.js";
const createTask = () => {
    // populate categories with data.
    populateCategories();
    
    // get HTML elements and then populate them with backend data.
    document.getElementById('new-task-form').addEventListener('submit', async function(event) {
        event.preventDefault();
        const title = document.getElementById('title').value;
        const description = document.getElementById('description').value;
        const dueDate = document.getElementById('due_date').value;
        const category = document.getElementById('category').value;
        
        const taskData = {
            title: title,
            description: description,
            due_date: dueDate,
            completed: false, // default value
            categoryId: category
        };

        try {
            const response = await fetch('http://127.0.0.1:8080/tasks/create', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(taskData)
            });
            if (!response.ok){
                console.log(category);
                
                throw new Error('Network response was not ok');
            }

            // alert('Task created successfully!');
            showToast('Task updated successfully!')
            // Redirect to the homepage
            window.location.href = 'index.html'; 
        } catch(error) {
            console.log('There was a problem with the task creation', error);
            
        }

    })
}

window.onload = createTask;