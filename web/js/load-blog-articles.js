const uriStart = '/team-java_blogaholic/';
let currentAuthorID;
let postContainer;

async function fetchArticles(authorID) {
    let response = await fetch(`${uriStart}load-articles?authorID=${authorID}`);
    return await response.json();
}

async function loadArticleList(authorID) {
    currentAuthorID = authorID;
    postContainer = document.querySelector("#article-list-container");
    const articleList = await fetchArticles(authorID);
    postContainer.innerHTML = "";
    renderArticlesDisplay(articleList, postContainer);

}

async function resortArticles(e) {
    const newSortRule = e.target.value;
    const articleList = sortArticlesByRules(newSortRule, await fetchArticles(currentAuthorID));
    postContainer.innerHTML = "";
    renderArticlesDisplay(articleList, postContainer);
}

function renderArticlesDisplay(articleList, postContainer) {
    articleList.forEach(article => {
            postContainer.appendChild(renderArticleDiv(article));
        }
    );
    listenForLikeDislike();
}

function renderArticleDiv(article) {
    const articleDiv = document.createElement("div");
    articleDiv.classList.add("article-div", "page-item-div");

    const fullArticleLink = document.createElement("a");
    fullArticleLink.classList.add("full-article-link");
    fullArticleLink.href = `${uriStart}article-view?articleID=${article.articleID}`;

    const articleTitleDiv = document.createElement("div");
    articleTitleDiv.classList.add("article-title-div");
    const articleTitle = document.createElement("h2");
    articleTitleDiv.classList.add("article-title");
    articleTitle.innerText = article.articleTitle;
    articleTitleDiv.appendChild(articleTitle);

    const articleBriefDiv = document.createElement("div");
    articleBriefDiv.classList.add("article-brief-div", "page-item-brief-div");
    const articleBrief = document.createElement("p");
    articleBrief.classList.add("article-brief", "page-item-brief");
    articleBrief.innerText = article.articleBrief;
    articleBriefDiv.appendChild(articleBrief);

    const articleInfoDiv = document.createElement("div");
    articleInfoDiv.classList.add("article-info-div", "page-item-info-div");
    const articleInfo = document.createElement("span");
    articleInfo.classList.add("article-info", "page-item-info");
    articleInfo.innerHTML = `Created on ${timestampToLocaleString(article.timeCreated)} · 
<span id="like-article-${article.articleID}-count" class="count-span">${article.likesCount}</span>
<i class="far fa-thumbs-up like-empty-button like-article" id="like-article-${article.articleID}"></i>·
<span id="dislike-article-${article.articleID}-count" class="count-span">${article.dislikesCount}</span>
<i class="far fa-thumbs-down dislike-empty-button dislike-comment" id="dislike-article-${article.articleID}"></i>`;
    articleInfoDiv.appendChild(articleInfo);

    fullArticleLink.appendChild(articleTitleDiv);
    fullArticleLink.appendChild(articleBriefDiv);
    articleDiv.appendChild(fullArticleLink);
    articleDiv.appendChild(articleInfoDiv);
    articleDiv.innerHTML += '<hr class="line-between-articles">';
    return articleDiv;

}

function timestampToLocaleString(timestamp) {
    const databaseTime = new Date(timestamp);
    return databaseTime.toLocaleString('en-NZ', {timeZone: 'Pacific/Auckland'});
}
