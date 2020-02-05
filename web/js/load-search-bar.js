function loadSearchBar() {
    const searchBox = document.querySelector("#search-box");
    searchBox.classList.toggle("invisible");
    const searchIcon = document.querySelector("#search-icon");
    searchIcon.classList.toggle("fa-search");
    searchIcon.classList.toggle("fa-angle-up");
    if (!searchBox.classList.contains("invisible")) {
        document.querySelector("#search-keyword").focus();
    }

}

