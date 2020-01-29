const uriStart = '/team-java_blogaholic/';
// TODO once we decided on our blog website name, the /blog/ part need to be updated.

async function loadCommentList(articleID) {
    const commentContainer = document.querySelector("#all-comments-container");
    let response = await fetch(`${uriStart}load-comments?articleID=${articleID}`);
    let commentList = await response.json();
    commentContainer.innerHTML = "";

    commentList.forEach(comment => {
            let rootCommentDiv = getCommentDiv(comment);
            rootCommentDiv.classList.add("root-comment-div");
            commentContainer.appendChild(rootCommentDiv);
        }
    );
}

function getCommentDiv(comment) {
    const commentDiv = document.createElement("div");
    commentDiv.classList.add("comment-div", "page-item-div");

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

function timestampToLocaleString(timestamp) {
    return new Date(timestamp).toLocaleString()
}

