const getCategoryDetails = async () => {
    const urlParams = new URLSearchParams(window.location.search);
    const categoryId = urlParams.get('id');
    
    if (!categoryId) {
        console.error('No category ID provided');
        return;
    }

    try {
        const response = await fetch(`http://127.0.0.1:8080/categories/${categoryId}`);
        if (!response.ok) {
            throw new Error('Network response was not ok.');
        }
        const category = await response.json();
        displayCategoryDetails(category);
    } catch (error) {
        console.error('There was a problem with fetching category details:', error);
    }
};

const displayCategoryDetails = (category) => {
    const categoryDetails = document.getElementById('category-details');
    categoryDetails.innerHTML = `
        <h2>${category.name}</h2>
    `;
};

// Fetch and display task details on page load
window.onload = getCategoryDetails;
