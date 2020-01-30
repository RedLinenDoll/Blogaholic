const uriStart = '/team-java_blogaholic/';
let currentArticleID;
let currentAuthorID;
let currentLoggedUserID;


async function sendDeleteArticleRequest() {
    const request = new XMLHttpRequest();
    request.open("POST", `${uriStart}delete-article`, true);
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.send(`articleID=${currentArticleID}`);
}

async function deleteArticle() {
    await sendDeleteArticleRequest();
    window.location.replace(`${uriStart}blog-view?authorID=${currentAuthorID}`);
}

async function loadCommentList(articleID, authorID, loggedUserID) {
    currentArticleID = articleID;
    currentAuthorID = authorID;
    currentLoggedUserID = loggedUserID;
    const commentContainer = document.querySelector("#all-comments-container");
    let response = await fetch(`${uriStart}load-comments?articleID=${articleID}`);
    let commentList = await response.json();
    commentContainer.innerHTML = "";

    commentList.forEach(comment => {
            let rootCommentDiv = getCommentDiv(comment, authorID);
            rootCommentDiv.classList.add("root-comment-div");
            commentContainer.appendChild(rootCommentDiv);
        }
    );
}

function getCommentDiv(comment, authorID) {
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
    appendEditButton(commentOptionsDiv, authorID, comment);
    appendDeleteButton(commentOptionsDiv, authorID, comment.commentID, comment.commenterID);
    appendCommentButton(commentOptionsDiv, comment.commentID);

    commentDiv.appendChild(avatarDiv);
    commentDiv.appendChild(commentInfoDiv);
    commentDiv.appendChild(commentBodyDiv);
    commentDiv.appendChild(commentOptionsDiv);

    const childCommentsContainer = document.createElement("div");
    childCommentsContainer.classList.add("child-comments-container");

    comment.childComments.forEach(
        childComment => {
            const childCommentDiv = getCommentDiv(childComment);
            childCommentDiv.classList.add("child-comment-div");
            childCommentsContainer.appendChild(childCommentDiv);
        }
    );
    commentDiv.appendChild(childCommentsContainer);

    return commentDiv;
}

function appendEditButton(commentOptionsDiv, authorID, comment) {
    if (currentLoggedUserID < 0) return;
    if (currentLoggedUserID === comment.commenterID) {
        const editButton = document.createElement("button");
        editButton.classList.add("edit-comment-button", "tiny-comment-option-button");
        editButton.innerText = "Edit";
        editButton.addEventListener("click", editComment(comment));
        commentOptionsDiv.appendChild(editButton);
    }
}

function appendDeleteButton(commentOptionsDiv, authorID, commentID, commenterID) {
    if (currentLoggedUserID < 0) return;
    if (currentLoggedUserID === commenterID || currentLoggedUserID === authorID) {
        const deleteButton = document.createElement("button");
        deleteButton.classList.add("delete-comment-button", "tiny-comment-option-button");
        deleteButton.innerText = "Delete";
        deleteButton.addEventListener("click", async function () {
            await deleteComment(commentID);
            location.reload();
        });
        commentOptionsDiv.appendChild(deleteButton);
    }
}

function editComment(comment) {

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

function appendCommentButton(optionDiv, targetCommentID) {
    if (currentLoggedUserID < 0) return;
    const commentButton = document.createElement("button");
    commentButton.innerText = "Comment";
    commentButton.classList.add("tiny-comment-option-button", "add-comment-to-comment-button");
    commentButton.addEventListener("click", function () {
        loadCommentingForm(optionDiv, targetCommentID);
    });
    optionDiv.appendChild(commentButton);
}

function loadCommentingForm(optionDiv, targetCommentID) {
    const oldEditor = document.querySelector("#add-comment-container");
    if (oldEditor !== null)
        oldEditor.parentNode.removeChild(oldEditor);
    const commentFormContainer = document.createElement("div");
    commentFormContainer.id = "add-comment-container";
    commentFormContainer.innerHTML = `
           <form id="add-comment-comment" action="${uriStart}add-comment" method="post">
                <label for="add-comment-to-comment" class="comment-info" style="font-size: 15px;">Add Comments: </label><br>
                <textarea id="add-comment-to-comment" rows="4" maxlength="512" name="article-comment-body" placeholder="Share your thoughts on this comment" style="width: 95%; font-size: 15px; font-family:var(--primary-font)"></textarea>
                <input type="hidden" name="target-id" value="${targetCommentID}">
                <input type="hidden" name="article-id" value="${currentArticleID}">
                <input type="hidden" name="target-type" value="comment">
                <button id="submit-comment-to-comment" class="comment-option-button">Post</button>
            </form>`;
    commentFormContainer.style.gridArea="add-comment";

    optionDiv.parentNode.insertBefore(commentFormContainer, optionDiv.nextSibling);

}

