// Create this as navbar.js
document.addEventListener('DOMContentLoaded', function() {
    const searchInput = document.querySelector('.nav-search input');
    const restaurantsGrid = document.querySelector('.restaurants-grid');
    const originalRestaurants = Array.from(document.querySelectorAll('.restaurant-card'));
    
    // Create a "No results" message element
    const noResultsMessage = document.createElement('div');
    noResultsMessage.className = 'no-results-message';
    noResultsMessage.innerHTML = `
        <i class="fas fa-search"></i>
        <p>No restaurants found matching your search.</p>
    `;
    noResultsMessage.style.display = 'none';
    restaurantsGrid.parentNode.insertBefore(noResultsMessage, restaurantsGrid.nextSibling);

    searchInput.addEventListener('input', function(e) {
        const searchTerm = e.target.value.toLowerCase().trim();
        
        // Filter restaurants based on search term
        const filteredRestaurants = originalRestaurants.filter(card => {
            const restaurantName = card.querySelector('.restaurant-name').textContent.toLowerCase();
            return restaurantName.includes(searchTerm);
        });

        // Hide all restaurants first
        originalRestaurants.forEach(card => {
            card.style.display = 'none';
        });

        if (searchTerm === '') {
            // If search is empty, show all restaurants
            originalRestaurants.forEach(card => {
                card.style.display = 'block';
            });
            noResultsMessage.style.display = 'none';
        } else if (filteredRestaurants.length > 0) {
            // Show only matching restaurants
            filteredRestaurants.forEach(card => {
                card.style.display = 'block';
            });
            noResultsMessage.style.display = 'none';
        } else {
            // Show no results message
            noResultsMessage.style.display = 'flex';
        }
    });
});