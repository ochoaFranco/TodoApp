const createCategory = () => {
    const form = document.getElementById('create-category-form');
    if (!form) {
        console.error('Form with ID create-category-form not found')
        return;
    }
        
    form.addEventListener('submit', async function(event) {
        event.preventDefault();
        const name = document.getElementById('categoryName').value;
        
        const categoryData = {
            name: name
        };

        try {
            const response = await fetch('http://127.0.0.1:8080/categories/create', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(categoryData)
            });
            // handle response 
            if (!response.ok) {
                let message = 'Failed to create category. Please try again'
                const errorResponse = await response.json();
                message = errorResponse.message || message;
                showAlert('danger', message);
            } else {
                showAlert('Category created successfully!');
                window.location.href = 'index.html'; // Redirect to the homepage
            }
        } catch(error) {
            console.error('There was a problem with the category creation', error);
        }
    });
}

function showAlert(type, message) {
    // Remove existing alerts 
    const existingAlert = document.querySelector('.alert');
    if (existingAlert) {
        existingAlert.remove();
    }
    const alertDiv = document.createElement('div');
    alertDiv.className = `alert alert-${type}`;
    alertDiv.textContent = message;

    // Appending the alert to a fixed container
    const alertContainer = document.getElementById('alert-container');
    
    if (alertContainer) 
        alertContainer.appendChild(alertDiv);
    else 
        console.error('Alert container not found.');

    // Automatically remove the alert after a few seconds
    setTimeout(() => {
        alertDiv.remove();
    }, 5000);
}

window.onload = createCategory;