const uriStart = '/team-java_blogaholic/';
let usernameValidated = true;
let passwordValidated = true;

window.addEventListener("load", function () {
    const usernameWarn = document.querySelector("#duplicate-warning");
    const usernameInput = document.querySelector("#username");
    usernameInput.addEventListener("input", checkIfUserExists);

    async function checkIfUserExists(e) {
        let inputUsername = e.target.value;
        let response = await fetch(`${uriStart}signup?tested-username=${inputUsername}`);
        let existingUsernameCount = await response.text();
        if (existingUsernameCount === 1 + "") {
            usernameWarn.classList.remove("invisible");
            usernameValidated = false;
        } else {
            usernameWarn.classList.add("invisible");
            usernameValidated = true;

        }
    }

    const confirmPasswordInput = document.querySelector("#confirm-password");
    confirmPasswordInput.addEventListener("input", checkIfPasswordMatches);
    const passwordInput = document.querySelector("#password");
    const passwordWarn = document.querySelector("#not-matching-warning");

    async function checkIfPasswordMatches() {
        passwordInput.addEventListener("input", checkIfPasswordMatches);
        let firstPassword = passwordInput.value;
        let confirmedPassword = confirmPasswordInput.value;
        if (firstPassword !== confirmedPassword) {
            passwordWarn.classList.remove("invisible");
            passwordValidated = false;
        } else {
            passwordWarn.classList.add("invisible");
            passwordValidated = true;
        }
    }


});

function validateForm(form) {
    if (!usernameValidated) {
        alert("Please choose another username!");
        return false;
    }

    if (!passwordValidated) {
        alert("Please confirm your password!");
        return false;
    }
}


