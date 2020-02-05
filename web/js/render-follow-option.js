let followSpan;
let checkCircle;
let authorName;
let currentVisitorID;

async function renderFollowSpan(authorID, visitorID, authorUsername) {
    currentVisitorID = visitorID;
    authorName = authorUsername;
    followSpan = document.querySelector("#follow-span");
    if (authorID === visitorID) {
        followSpan.innerHTML = `You have <a href="${uriStart}user-profile?user-id=${authorID}#followers"><span id="follower-number-span">${await getFollowerNumber()}</span></a> followers.`
    }
    if (visitorID === -1) {
        followSpan.innerHTML = `${authorName} has <a href="${uriStart}user-profile?user-id=${authorID}#followers"><span id="follower-number-span">${await getFollowerNumber()}</span></a> followers.`
    }
    else {
        await renderFollowOption(authorID, visitorID);
    }

}

async function getFollowerNumber() {
    let response = await fetch(`${uriStart}follow-relationship?request-option=get-follower-number&target-user-id=${currentAuthorID}`);
    return await response.json();
}

function changeFollowingRelationship(adding) {
    const request = new XMLHttpRequest();
    request.open("POST", `${uriStart}follow-relationship`, true);
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.send(`adding=${adding}&follower-id=${currentVisitorID}&publisher-id=${currentAuthorID}`);
    request.onreadystatechange = function () {
        console.log(request.onreadystatechange);
        if (request.readyState === 4) {
            window.location.reload();
        }
    }
}

async function renderFollowOption() {
    let response = await fetch(`${uriStart}follow-relationship?request-option=check-if-following&target-user-id=${currentVisitorID}&publisher-id=${currentAuthorID}`);
    let isFollowing = await response.json();

    checkCircle = document.createElement("i");
    checkCircle.classList.add("far", "follow-check-circle");
    checkCircle.id = "follow-check-circle";


    if (isFollowing) {
        checkCircle.classList.add("fa-check-circle");
        followSpan.appendChild(checkCircle);
        followSpan.innerHTML += ` You are following ${authorName}`;
        addFollowOptionListener( false);
    } else {
        checkCircle.classList.add("fa-circle");
        followSpan.appendChild(checkCircle);
        followSpan.innerHTML += ` Click to follow ${authorName}`;
        addFollowOptionListener(true);
    }

}

function addFollowOptionListener(clickToFollow) {
    followSpan.addEventListener("click", function () {
        changeFollowingRelationship(clickToFollow);
    })

}