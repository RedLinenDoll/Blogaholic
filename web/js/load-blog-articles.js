async function loadArticleList(authorID) {
    const postContainer = document.querySelector("#post-list-container");
    let response = await fetch(`/blog/load-articles?authorID=${authorID}`);
    let articleList = await response.json();

    articleList.forEach(article => {
            postContainer.appendChild(renderArticleDiv(article));
        }
    );
}

function renderArticleDiv(article) {
    console.log(article);
    const articleDiv = document.createElement("div");
    articleDiv.classList.add("articleDiv");

    const articleTitleDiv = document.createElement("div");
    articleTitleDiv.classList.add("articleTitleDiv");
    const articleTitle = document.createElement("h2");
    articleTitleDiv.classList.add("articleTitle");
    articleTitle.innerText = article.articleTitle;
    articleTitleDiv.appendChild(articleTitle);

    const articleBriefDiv = document.createElement("div");
    articleBriefDiv.classList.add("articleBriefDiv");
    const articleBrief = document.createElement("span");
    articleBrief.classList.add("articleBrief");
    articleBrief.innerText = article.articleBrief;
    articleBriefDiv.appendChild(articleBrief);

    const articleInfoDiv = document.createElement("div");
    articleInfoDiv.classList.add("articleInfoDiv");
    const articleInfo = document.createElement("span");
    articleInfo.classList.add("articleInfo");
    articleInfo.innerText = `Created on ${timestampToLocaleString(article.timeCreated)} · ${article.likesCount} likes · ${article.dislikesCount} dislikes`;
    articleInfoDiv.appendChild(articleInfo);

    articleDiv.appendChild(articleTitleDiv);
    articleDiv.appendChild(articleBriefDiv);
    articleDiv.appendChild(articleInfoDiv);
    return articleDiv;

}

function timestampToLocaleString(timestamp) {
    return new Date(timestamp).toLocaleString()
}
