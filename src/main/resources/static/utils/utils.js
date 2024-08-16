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
