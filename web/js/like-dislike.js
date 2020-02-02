function listenForLikeDislike() {
    const likeEmptyButtons = document.querySelectorAll(".like-empty-button");
    const dislikeEmptyButtons = document.querySelectorAll(".dislike-empty-button");

    likeEmptyButtons.forEach(
        button => {
            button.addEventListener("click", emptyButtonClicked);
        });
    dislikeEmptyButtons.forEach(
        button => {
            button.addEventListener("click", emptyButtonClicked)
        });


    function emptyButtonClicked(e) {
        const buttonNode = e.target;
        const targetInfo = buttonNode.id.split("-");
        const isLike = (targetInfo[0] === "like");
        const targetType = targetInfo[1];
        const targetID = targetInfo[2];
        sendDoRequest(targetType, targetID, isLike);
        changeDoState(buttonNode, targetID, isLike);
    }

    function filledButtonClicked(e) {
        const buttonNode = e.target;
        const targetInfo = buttonNode.id.split("-");
        const isLike = (targetInfo[0] === "like");
        const targetType = targetInfo[1];
        const targetID = targetInfo[2];
        sendUndoRequest(targetType, targetID, isLike);
        changeUndoState(buttonNode, targetID, isLike);
    }

    function changeDoState(buttonNode, targetID, isLike) {
        changePairState(buttonNode, false);

        buttonNode.classList.remove(isLike ? "like-empty-button" : "dislike-empty-button",
            "far");
        buttonNode.classList.add(isLike ? "like-full-button" : "dislike-full-button", "fas");
        const recordSpan = document.querySelector(`#${buttonNode.id}-count`);
        recordSpan.innerText = Number(recordSpan.innerText) + 1;
        buttonNode.removeEventListener("click", emptyButtonClicked);
        buttonNode.addEventListener("click", filledButtonClicked);

    }

    function changeUndoState(buttonNode, targetID, isLike) {
        changePairState(buttonNode, true);
        buttonNode.classList.remove(isLike ? "like-full-button" : "dislike-full-button",
            "fas");
        buttonNode.classList.add(isLike ? "like-empty-button" : "dislike-empty-button",
            "far");
        const recordSpan = document.querySelector(`#${buttonNode.id}-count`);
        recordSpan.innerText = Number(recordSpan.innerText) - 1;
        buttonNode.removeEventListener("click", filledButtonClicked);
        buttonNode.addEventListener("click", emptyButtonClicked);
    }

    function changePairState(buttonNode, enabled) {
        const buttonIDInfo = buttonNode.id.split("-");
        const pairType = buttonIDInfo[0] === "like" ? "dislike" : "like";
        const pairID = [pairType, buttonIDInfo[1], buttonIDInfo[2]].join("-");
        const pairNode = document.querySelector("#" + pairID);
        if (enabled) {
            pairNode.addEventListener("click", emptyButtonClicked);
            pairNode.classList.remove("disabled-icon");
        } else {
            pairNode.removeEventListener("click", emptyButtonClicked);
            pairNode.classList.add("disabled-icon");

        }
    }

    function sendDoRequest(targetType, targetID, isLike) {
        const request = new XMLHttpRequest();
        request.open("POST", `${uriStart}like-dislike`, true);
        request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        console.log(`target-id=${targetID}&target-type=${targetType}&is-like=${isLike}&is-plus=true`);
        request.send(`target-id=${targetID}&target-type=${targetType}&is-like=${isLike}&is-plus=true`);
    }

    function sendUndoRequest(targetType, targetID, isLike) {
        const request = new XMLHttpRequest();
        request.open("POST", `${uriStart}like-dislike`, true);
        request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        request.send(`target-id=${targetID}&target-type=${targetType}&is-like=${isLike}&is-plus=false`);
    }

}