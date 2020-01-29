const uriStart = '/team-java_blogaholic/';

window.addEventListener("load", function () {
    const submitButton = document.querySelector("#submit-button");
    const usernameWarn = document.querySelector("#duplicate-warning");
    const usernameInput = document.querySelector("#username");
    usernameInput.addEventListener("input", checkIfUserExists);

    async function checkIfUserExists() {
        let inputUsername = usernameInput.value;
        let response = await fetch(`${uriStart}signup?tested-username=${inputUsername}`);
        let existingUsernameCount = await response.text();
        if (existingUsernameCount === 1 + "") {
            usernameWarn.classList.remove("invisible");
            submitButton.disabled = true;
        } else {
            usernameWarn.classList.add("invisible");
            submitButton.disabled = false;
        }
    }

    const confirmPasswordInput = document.querySelector("#confirm-password");
    confirmPasswordInput.addEventListener("input", checkIfPasswordMatches);
    const passwordInput = document.querySelector("#password");
    const passwordWarn = document.querySelector("#not-matching-warning");

    async function checkIfPasswordMatches() {
        let firstPassword = passwordInput.value;
        let confirmedPassword = confirmPasswordInput.value;
        if (firstPassword !== confirmedPassword) {
            passwordWarn.classList.remove("invisible");
            submitButton.disabled = true;
        } else {
            passwordWarn.classList.add("invisible");
            submitButton.disabled = false;
        }
    }


});

