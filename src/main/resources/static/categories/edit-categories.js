import { showToast, populateCategories } from "../utils/utils.js";
let categoryId;
document.addEventListener('DOMContentLoaded', () => {
    const urlParams = new URLSearchParams(window.location.search);
    categoryId = urlParams.get('id');
    // fetch category details from backend.
    fetch(`http://127.0.0.1:8080/categories/${categoryId}`)
        .then(response => response.json())
        .then(category => {
            //populate form fields with category data.
            document.getElementById('categoryName').value = category.name;
        })
        .catch(error => console.error('error fetching category data', error))
});

populateCategories();

document.getElementById('edit-category-form').addEventListener('submit', (event) => {
    event.preventDefault();
    
    const updatedCategory = {
        name: document.getElementById('categoryName').value
    };
    // Send updated task data to backend.
    fetch(`http://127.0.0.1:8080/categories/edit/${categoryId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(updatedCategory)
    })
    .then(response => {
        if (!response.ok) {
            console.log('Error updating category: ', response.statusText);
        } else {
            showToast('Category updated successfully!')
            window.location.href = 'categories.html'; // redirect to categories list.
        }
    })
    .catch(error => console.error('Error: ', error));
});