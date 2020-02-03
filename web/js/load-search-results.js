function loadCurrentArticle(article) {
    const articleContainer = document.querySelector("#article-container-${article.articleID}");
    articleContainer.innerHTML = article.articleContent + article.author.avatarPath;

}