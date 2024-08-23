import { showToast } from "../utils/utils.js";

const getCategories = async () => {
    const url = 'http://127.0.0.1:8080/categories'
    try {
        const response = await fetch(url);
        if (!response.ok) {
            throw new Error('Network response was not ok.');
        }
        const data = await response.json();
        console.log(data);
        
        displayCategories(data);
    } catch(error) {
        console.log('There has been a problem with your fetch operation', error);
    }
}


const displayCategories = (categories) => {
    const categoryList = document.getElementById("categoryList")
    categoryList.innerHTML = '';
    categories.forEach(category => {
        const div = document.createElement('div');
        div.className = 'category-item';
        div.innerHTML = `
        <div class="category-header">
            <h4><a href="category-description.html?id=${category.id}">${category.name}</a></h4>
        </div>
        <button class="btn btn-edit" data-category-id="${category.id}">Edit</button>
        <button class="btn btn-delete" data-category-id="${category.id}">Delete</button>
    `;
        const hr = document.createElement('hr');
        categoryList.appendChild(hr);
        categoryList.appendChild(div);
    })

    // Add event listeners to edit button
    document.querySelectorAll('.btn-edit').forEach(button => {
        button.addEventListener('click', () => {
            const categoryId = button.getAttribute('data-category-id');
            editCategory(categoryId);
        });
    });

    // Add event listeners to delete button
    document.querySelectorAll('.btn-delete').forEach(button => {
        button.addEventListener('click', () => {
            const categoryId = button.getAttribute('data-category-id');
            deleteCategory(categoryId);
        });
    });
}

// Edit a category.
const editCategory = (categoryId) => {
    // Redirect to edit page or show a form for editing
    window.location.href = `edit-category.html?id=${categoryId}`;
}

// Delete a category.
const deleteCategory = async (categoryId) => {
    try {
        const response = await fetch(`http://127.0.0.1:8080/categories/delete/${categoryId}`, {
            method: 'DELETE',
        });
        if (!response.ok) 
            showAlert('failure', message)
        
        showToast('Category deleted successfully!')
        getCategories();
    } catch (error) {
        showAlert('error', 'There was an unexpected error. Please try again later')
    }
}

function showAlert(type, message) {
    const alertDiv = document.createElement('div');
    alertDiv.className = `alert alert-${type}`;
    alertDiv.textContent = message;
    
    document.body.appendChild(alertDiv);
    
    // Automatically remove the alert after a few seconds
    setTimeout(() => {
        alertDiv.remove();
    }, 5000);
}



window.onload = getCategories;