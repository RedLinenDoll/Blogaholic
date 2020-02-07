const uriStart = '/team-java_blogaholic/';
let currentFollowingArticleIndex = 1;
let currentRecentArticleIndex = 1;
let followingArticleIDList = [];
let recentArticleIDList = [];
let isLoadingFollowingArticles = true;
let postContainer;
let currentUserID;
let loadMoreButton;

async function loadFeedArticleList(userID) {
    loadMoreButton = document.querySelector("#feed-more-button");
    loadMoreButton.addEventListener("click", loadMoreFeedArticles);
    currentUserID = userID;
    postContainer = document.querySelector("#feed-article-list-container");
    let response = await fetch(`${uriStart}article-feed?request-option=load-feed-article-ids&user-id=${currentUserID}`);
    followingArticleIDList = await response.json();
    await loadMoreFeedArticles();

}

async function initializeRecentArticleIDList() {
    let response = await fetch(`${uriStart}article-feed?request-option=load-more-article-ids&user-id=${currentUserID}`);
    recentArticleIDList = await response.json();
}

async function loadMoreFeedArticles() {

    if (isLoadingFollowingArticles) {
        if (followingArticleIDList.length === 0) {
            await initializeRecentArticleIDList();
            isLoadingFollowingArticles = false;
            loadMoreFeedArticles();
            return;
        }
        let from = currentFollowingArticleIndex;
        let to = Math.min(currentFollowingArticleIndex + 5, followingArticleIDList.length);
        for (let i = from; i < to; i++) {
            await renderFeedArticleByArticleID(followingArticleIDList[i]);
        }
        if (to === followingArticleIDList.length) {
            await initializeRecentArticleIDList();
            isLoadingFollowingArticles = false;
            renderFinishFollowingFeedDiv(true); // put the div before the button
        } else {
            currentFollowingArticleIndex = to;
        }
    }
   else if (!isLoadingFollowingArticles) {
        let from = currentRecentArticleIndex;
        let to = Math.min(currentRecentArticleIndex + 5, recentArticleIDList.length);
        for (let i = from; i < to; i++) {
            await renderFeedArticleByArticleID(recentArticleIDList[i]);
        }
        if (to === recentArticleIDList.length) {
            renderFinishAllDiv(); // remove the button, and add a link to homepage instead
        } else {
            currentRecentArticleIndex = to;
        }
    }
}

async function renderFeedArticleByArticleID(articleID) {
    let response = await fetch(`${uriStart}article-feed?request-option=load-article-by-id&article-id=${articleID}`);
    const article = await response.json();

    const articleDiv = document.createElement("div");
    articleDiv.classList.add("feed-article-div", "article-div", "page-item-div");

    const fullArticleLink = document.createElement("a");
    fullArticleLink.classList.add("full-article-link");
    fullArticleLink.href = `${uriStart}article-view?articleID=${articleID}`;

    const articleTitleDiv = document.createElement("div");
    articleTitleDiv.classList.add("article-title-div", "page-item-title-div");
    const articleTitleH2 = document.createElement("h2");
    articleTitleH2.classList.add("article-title", "page-item-title");
    articleTitleH2.innerText = article.articleTitle;
    articleTitleDiv.appendChild(articleTitleH2);

    const articleBriefDiv = document.createElement("div");
    const articleBrief = document.createElement("p");
    articleBrief.classList.add("article-brief", "page-item-brief");
    articleBrief.innerText = article.articleBrief;
    articleBriefDiv.appendChild(articleBrief);

    const articleInformationDiv = document.createElement("div");
    articleInformationDiv.classList.add("article-information-div");
    const articleInformation = document.createElement("span");
    articleInformation.innerHTML = `created by <a href="${uriStart}blog-view?authorID=${article.author.userID}"><img class="inline-avatar" src=${uriStart}images/avatar/${article.author.avatarPath} alt=" "></a> ${article.author.username} on ${timestampToLocaleString(article.timeCreated)}`;
    articleInformationDiv.appendChild(articleInformation);

    fullArticleLink.appendChild(articleTitleDiv);
    fullArticleLink.appendChild(articleBriefDiv);

    articleDiv.appendChild(fullArticleLink);
    articleDiv.appendChild(articleInformationDiv);

    postContainer.insertBefore(articleDiv, loadMoreButton);

}


function timestampToLocaleString(timestamp) {
    const databaseTime = new Date(timestamp);
    return databaseTime.toLocaleString('en-NZ', {timeZone: 'Pacific/Auckland'});
}

function renderFinishFollowingFeedDiv(loadedSomeFollowing) {

    const finishFollowingDiv = document.createElement("div");
    finishFollowingDiv.classList.add("finish-following-div");
    if (loadedSomeFollowing) {
        const finishFollowingMessage = document.createElement("p");
        finishFollowingMessage.innerText = "That's all articles written by people you are following.";
        finishFollowingDiv.appendChild(finishFollowingMessage);
    }
    const nextMessage = document.createElement("p");
    nextMessage.innerText ="We can show you more articles by other users if you like.";
    finishFollowingDiv.appendChild(nextMessage);
    postContainer.insertBefore(finishFollowingDiv, loadMoreButton);

}

function renderFinishAllDiv() {
    const finishAllDiv = document.createElement("div");
    const finishAllMessage = document.createElement("p");
    finishAllMessage.innerText ="That's all articles we can feed";

    finishAllDiv.classList.add("finish-all-div");
    finishAllDiv.appendChild(finishAllMessage);
    postContainer.removeChild(loadMoreButton);
    postContainer.insertBefore(finishAllDiv, document.querySelector("#go-top-link"));

}
