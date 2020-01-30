const uriStart = '/team-java_blogaholic/';
let currentArticleID;

async function loadCommentList(articleID, authorID, loggedUserID) {
    currentArticleID = articleID;
    const commentContainer = document.querySelector("#all-comments-container");
    let response = await fetch(`${uriStart}load-comments?articleID=${articleID}`);
    let commentList = await response.json();
    commentContainer.innerHTML = "";

    commentList.forEach(comment => {
            let rootCommentDiv = getCommentDiv(comment, authorID, loggedUserID);
            rootCommentDiv.classList.add("root-comment-div");
            commentContainer.appendChild(rootCommentDiv);
        }
    );
}

function getCommentDiv(comment, authorID, loggedUserID) {
    const commentDiv = document.createElement("div");
    commentDiv.classList.add("comment-div", "page-item-div");
    commentDiv.id = "comment" + comment.commentID;

    const avatarDiv = document.createElement("div");
    avatarDiv.classList.add("block-avatar-div");
    avatarDiv.innerHTML = `<img src="${uriStart}images/avatar/${comment.avatarPath}" class="block-avatar comment-avatar" alt="">`;

    const commentInfoDiv = document.createElement("div");
    commentInfoDiv.classList.add("comment-info-div");
    const commentInfo = document.createElement("p");
    commentInfo.classList.add("comment-info");
    commentInfo.innerText = `${comment.commenterUsername} · ${timestampToLocaleString(comment.timeCreated)}`;
    commentInfoDiv.appendChild(commentInfo);

    const commentBodyDiv = document.createElement("div");
    commentBodyDiv.classList.add("comment-body-div");
    const commentBody = document.createElement("p");
    commentBody.classList.add("comment-body");
    commentBody.innerText = comment.commentBody;
    commentBodyDiv.appendChild(commentBody);

    const commentOptionsDiv = document.createElement("div");
    commentOptionsDiv.classList.add("comment-options-div");
    const commentLikeDislikeSpan = document.createElement("span");
    commentLikeDislikeSpan.classList.add("comment-like-dislike-span");
    commentLikeDislikeSpan.innerHTML = `${comment.likesCount} <i class="far fa-thumbs-up like-empty-button like-comment" id="like-comment-${comment.commentID}"></i>· ${comment.dislikesCount} <i class="far fa-thumbs-down dislike-empty-button dislike-comment-${comment.commentIDD}"></i>`;
    commentOptionsDiv.appendChild(commentLikeDislikeSpan);
    appendEditButton(commentOptionsDiv, authorID, loggedUserID, comment.commentID, comment.commenterID);
    appendDeleteButton(commentOptionsDiv, authorID, loggedUserID, comment.commentID, comment.commenterID);

    commentDiv.appendChild(avatarDiv);
    commentDiv.appendChild(commentInfoDiv);
    commentDiv.appendChild(commentBodyDiv);
    commentDiv.appendChild(commentOptionsDiv);

    comment.childComments.forEach(
        childComment => {
            const childCommentDiv = getCommentDiv(childComment);
            childCommentDiv.classList.add("child-comment-div");
            commentDiv.appendChild(childCommentDiv);
        }
    );

    return commentDiv;
}

function appendEditButton(commentOptionsDiv, authorID, loggedUserID, commentID, commenterID) {
    if (loggedUserID < 0) return;
    if (loggedUserID === commenterID) {
        const editButton = document.createElement("button");
        editButton.innerText = "Edit";
        editButton.addEventListener("click", editComment(commentID));
        commentOptionsDiv.appendChild(editButton);
    }
}

function appendDeleteButton(commentOptionsDiv, authorID, loggedUserID, commentID, commenterID) {
    if (loggedUserID < 0) return;
    if (loggedUserID === commenterID || loggedUserID === authorID) {
        const deleteButton = document.createElement("button");
        deleteButton.innerText = "delete";
        deleteButton.addEventListener("click", async function () {
            await deleteComment(commentID);
            location.reload();
        });
        commentOptionsDiv.appendChild(deleteButton);
    }
}

function editComment(commentID) {

}

async function deleteComment(commentID) {
    const request = new XMLHttpRequest();
    request.open("POST", `${uriStart}delete-comment`, true);
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.send(`commentID=${commentID}&articleID=${currentArticleID}`);
}

function timestampToLocaleString(timestamp) {
    return new Date(timestamp).toLocaleString()
}

