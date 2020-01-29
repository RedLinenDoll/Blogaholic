window.addEventListener("load", function () {
    const uriStart = '/blog/';
    const warnSpan = document.querySelector("#duplicate-warning");

    const usernameInput = document.querySelector("#username");
    usernameInput.addEventListener("input", checkIfUserExists);

    async function checkIfUserExists() {
        let inputUsername = usernameInput.value;
        if (inputUsername < 3) return;
        let response = await fetch(`${uriStart}signup?tested-username=${inputUsername}`);
        let existingUsernameCount = await response.text();
        console.log(existingUsernameCount);
        if (existingUsernameCount == 1) {
            warnSpan.classList.remove("invisible");
        }
        else
            warnSpan.classList.add("invisible");
    }

});

