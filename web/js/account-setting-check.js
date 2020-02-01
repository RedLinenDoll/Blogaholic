const uriStart = '/team-java_blogaholic/';
let usernameValidated = true;
let passwordValidated = true;

async function checkIfNewUsernameExists(newUsernameInput, oldUsername, usernameWarn) {
    const inputUsername = newUsernameInput.value;
    let response = await fetch(`${uriStart}signup?tested-username=${inputUsername}`);
    let existingUsernameCount = await response.text();
    if (existingUsernameCount > 0 + "" && oldUsername !== inputUsername) {
        usernameWarn.classList.remove("invisible");
        usernameValidated = false;
    } else {
        usernameWarn.classList.add("invisible");
        usernameValidated = true;
    }
}

async function checkIfNewPasswordMatches(confirmNewPasswordInput, newPasswordInput, passwordWarn) {
    newPasswordInput.addEventListener("input", checkIfNewPasswordMatches);
    let firstPassword = newPasswordInput.value;
    let confirmedPassword = confirmNewPasswordInput.value;
    if (firstPassword !== confirmedPassword) {
        passwordWarn.classList.remove("invisible");
        passwordValidated = false;
    } else {
        passwordWarn.classList.add("invisible");
        passwordValidated = true;
    }
}


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

function loadConfirmDeleteAccountForm(bodyContainer, targetUsername, targetUserID) {
    const confirmDeleteForm = document.createElement("form");
    confirmDeleteForm.action = `${uriStart}delete-account?userID=${targetUserID}`;
    confirmDeleteForm.method = "post";
    confirmDeleteForm.style.width = "90%";
    confirmDeleteForm.id = "confirm-delete-form";

    const confirmLogInDiv = document.createElement("div");
    confirmLogInDiv.innerHTML = `<hr class="warning-hr"> 
        <p class="delete-warning-message bold">Warning! </p>
        <p class="delete-warning-message">You are trying to delete your account. </p>
        <p class="delete-warning-message">All your creation in blogaholic wiill be deleted. </p>
        <p class="delete-warning-message bold">This operation cannot be undone.</p>
        
        <div><label for="username">Confirm Username:</label>
         <input type="text" id="username" name="username"></div>
         <div>
        <label for="password">Confirm Password:</label>
            <input type="password" id="password" name="password" required>
            </div>`;
   const  confirmTyping = document.createElement("div");
   const confirmLabel = document.createElement("label");
   confirmLabel.for="confirm-input";
   confirmLabel.innerText = `Please confirm by typing in "delete account ${targetUsername}" below:`;
   const confirmInput =document.createElement("input");
   confirmInput.id="confirm-input";
   confirmInput.type="text";
   confirmTyping.appendChild(confirmLabel);
   confirmTyping.appendChild(confirmInput);

    confirmDeleteForm.appendChild(confirmLogInDiv);
    confirmDeleteForm.appendChild(confirmTyping);
   confirmDeleteForm.style.paddingBottom = "50px";
    bodyContainer.appendChild(confirmDeleteForm);
    confirmDeleteForm.scrollIntoView({behavior: "smooth", block: "start"});

    confirmInput.addEventListener("input", function () {
        checkDeleteConfirmed(confirmInput, targetUsername, confirmDeleteForm);
    });

}

function checkDeleteConfirmed(confirmInput, targetUsername, confirmDeleteForm) {
    if (`delete account ${targetUsername}` === confirmInput.value) {
       confirmDeleteForm.innerHTML += `<button type="submit" id="delete-account-button" style="background-color: red">
                    Confirm Delete
                </button>`
    }

}