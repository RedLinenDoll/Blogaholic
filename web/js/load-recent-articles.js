const uriStart = '/blog/';
// TODO once we decided on our blog website name, the /blog/ part need to be updated.


async function loadRecentArticleList() {
    const postContainer = document.querySelector("#recent-article-list-container");
    let response = await fetch(`${uriStart}load-articles`);
    let articleList = await response.json();
    postContainer.innerHTML="";
    articleList.forEach(article => {
            postContainer.appendChild(renderRecentArticleDiv(article));
        }
    );
}

function renderRecentArticleDiv(article) {
    const articleDiv = document.createElement("div");
    articleDiv.classList.add("article-div", "page-item-div");

    const fullArticleLink = document.createElement("a");
    fullArticleLink.classList.add("full-article-link");
    fullArticleLink.href=`${uriStart}article-view?articleID=${article.articleID}`;

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
    articleInfo.innerHTML = `Created on ${timestampToLocaleString(article.timeCreated)} · ${article.likesCount} <i class="far fa-thumbs-up like-empty-button like-article" id="like-article-${article.articleID}"></i>· ${article.dislikesCount} <i class="far fa-thumbs-down dislike-empty-button dislike-article-${article.articleID}"></i>`;
    articleInfoDiv.appendChild(articleInfo);

    fullArticleLink.appendChild(articleTitleDiv);
    fullArticleLink.appendChild(articleBriefDiv);
    articleDiv.appendChild(fullArticleLink);
    articleDiv.appendChild(articleInfoDiv);
    articleDiv.innerHTML+='<hr class="line-between-articles">';
    return articleDiv;

}

function timestampToLocaleString(timestamp) {
    return new Date(timestamp).toLocaleString()
}
