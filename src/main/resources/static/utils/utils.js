export function showToast(message) {
    const toastElement = document.getElementById('toast');
    if (toastElement) {
        toastElement.textContent = message;
        toastElement.style.display = 'block'; // Ensure the toast is visible
        setTimeout(() => {
            toastElement.style.display = 'none'; // Hide after some time
        }, 3000); 
    } else {
        console.error('Toast element not found');
    }
}

export const populateCategories = async() => {
    const categorySelect = document.getElementById('category');
    try {
        const response = await fetch('http://127.0.0.1:8080/categories')
        const categories = await response.json();
        
        categories.forEach(category => {
            const option = document.createElement('option');
            option.value = category.id;
            option.textContent = category.name;
            categorySelect.appendChild(option);
        });
    } catch (error) {
        console.log('Error fetching categories:', error);
    }
}