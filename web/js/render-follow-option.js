let followSpan;
let checkCircle;
let authorName;
let currentVisitorID;

async function renderFollowSpan(authorID, visitorID, authorUsername) {
    currentVisitorID = visitorID;
    authorName = authorUsername;
    followSpan = document.querySelector("#follow-span");
    if (authorID === visitorID) {
        followSpan.innerHTML = `You have <a href="${uriStart}user-profile?user-id=${authorID}#followers"><span id="follower-number-span">${await getFollowerNumber()}</span></a> follower(s).`
        return;
    }
    if (visitorID === -1) {
        followSpan.innerHTML = `${authorName} has <a href="${uriStart}user-profile?user-id=${authorID}#followers"><span id="follower-number-span">${await getFollowerNumber()}</span></a> follower(s).`
    } else {
        await renderFollowOption(authorID, visitorID);
    }
}

async function renderProfileFollowParagraph(authorID, visitorID, authorUsername) {
    currentVisitorID = visitorID;
    authorName = authorUsername;
    followSpan = document.createElement("span");
    followSpan.id = "follow-span";
    const followParagraph = document.querySelector("#follow-paragraph");
    if (authorID === visitorID) {
        followParagraph.innerHTML = `You have ${await getFollowerNumber()} follower(s). <br>`
    } else {
        followParagraph.innerHTML = `${authorName} has ${await getFollowerNumber()} follower(s). <br>`
        if (visitorID > 0) {
            await renderFollowOption(authorID, visitorID);
            followParagraph.appendChild(followSpan);
        }
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
        addFollowOptionListener(false);
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

async function getFollowerList() {
    let response = await fetch(`${uriStart}follow-relationship?request-option=get-follower-list&target-user-id=${currentAuthorID}`);
    return await response.json();
}

async function getPublisherList() {
    let response = await fetch(`${uriStart}follow-relationship?request-option=get-publisher-list&target-user-id=${currentAuthorID}`);
    return await response.json();

}

async function loadUserBox() {
    const followerBox = document.querySelector("#follower-box");
    const followerList = await getFollowerList();
    const publisherBox = document.querySelector("#following-box");
    const publisherList = await getPublisherList();

    if (followerList.length > 0)
        followerList.forEach(follower => {
            followerBox.appendChild(renderRelationshipAvatarDiv(follower));
        });

    if (publisherList.length > 0) {
        publisherList.forEach(publisher => {
            publisherBox.appendChild(renderRelationshipAvatarDiv(publisher));
        })
    } else publisherBox.innerHTML = `<p style="font-size:0.7em">(not following anyone yet)</p>`;

}

function renderRelationshipAvatarDiv(user) {
    const relationshipDiv = document.createElement("div");
    relationshipDiv.classList.add("relationship-div");

    const avatarDiv = document.createElement("div");
    avatarDiv.classList.add("relationship-avatar-div");
    const userProfileLink = document.createElement("a");
    userProfileLink.href = `${uriStart}user-profile?user-id=${user.userID}`;

    const avatar = document.createElement("img");
    avatar.src = `${uriStart}images/avatar/${user.avatarPath}`;
    avatar.classList.add("block-avatar", "relationship-avatar");
    userProfileLink.appendChild(avatar);
    avatarDiv.appendChild(userProfileLink);

    const usernameDiv = document.createElement("div");
    usernameDiv.classList.add("relationship-username-div");
    usernameDiv.innerHTML = `<p>${user.username}</p>`;

    relationshipDiv.appendChild(avatarDiv);
    relationshipDiv.appendChild(usernameDiv);
    return relationshipDiv;

}