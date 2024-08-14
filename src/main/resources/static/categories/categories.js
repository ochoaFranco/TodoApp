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