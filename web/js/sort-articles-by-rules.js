function sortArticlesByLike(articles) {
    articles.sort((articleA, articleB) => (articleB.likesCount - articleA.likesCount));
    return articles;
}

function sortArticlesByCreationDate(articles) {
    articles.sort((articleA, articleB) => (articleB.timeCreated - articleA.timeCreated));
    return articles;
}

function sortArticlesByTitleZtoA(articles) {
    articles.sort((articleA, articleB) => (articleB.articleTitle.localeCompare(articleA.articleTitle)));
    return articles;
}
function sortArticlesByTitleAtoZ(articles) {
    articles.sort((articleA, articleB) => (articleA.articleTitle.localeCompare(articleB.articleTitle)));
    return articles;
}

function sortArticlesByRules(rule, articles) {
    switch (rule) {
        case "latest-first":
            return sortArticlesByCreationDate(articles);
        case "most-liked-first":
            return sortArticlesByLike(articles);
        case "article-title-z-a" :
            return sortArticlesByTitleZtoA(articles);
        case "article-title-a-z" :
            return sortArticlesByTitleAtoZ(articles);
        default:
            return articles;

    }
}