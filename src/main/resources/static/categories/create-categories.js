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
            
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }

            alert('Category created successfully!');
            window.location.href = 'index.html'; // Redirect to the homepage
        } catch(error) {
            console.error('There was a problem with the category creation', error);
        }
    });
}

window.onload = createCategory;