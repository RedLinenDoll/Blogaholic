const uriStart = '/team-java_blogaholic/';
let keywordPattern;
let currentKeyword;

function loadCurrentArticle(articleID, articleTitle, articleContent, authorID,
                            authorUsername, avatarPath, timeCreated, keyword) {
    const processedKeyword = keyword.replace(`\.`, `[.]`);
    keywordPattern = new RegExp(processedKeyword, 'gi');

    currentKeyword = keyword;
    const resultDiv = document.querySelector("#article-container-" + articleID);

    const articleLink = document.createElement("a");
    articleLink.classList.add("full-article-link");
    articleLink.href = `${uriStart}article-view?articleID=${articleID}`;

    const articleTitleDiv = document.createElement("div");
    articleTitleDiv.classList.add("article-title-div", "page-item-title-div");
    const articleTitleH2 = document.createElement("h2");
    articleTitleH2.classList.add("article-title", "page-item-title");
    articleTitleH2.innerHTML = getProcessedTitleHTML(articleTitle, keyword);
    articleTitleDiv.appendChild(articleTitleH2);

    const articleContentSegmentDiv = document.createElement("div");
    const articleContentSegment = document.createElement("p");
    articleContentSegment.classList.add("article-segment", "page-item-brief");
    articleContentSegment.innerHTML = getProcessedContentHTML(articleContent, keyword);
    articleContentSegmentDiv.appendChild(articleContentSegment);

    const articleInformationDiv = document.createElement("div");
    articleInformationDiv.classList.add("article-information-div");
    const articleInformation = document.createElement("span");
    articleInformation.innerHTML = `created by <img class="inline-avatar" src=${uriStart}images/avatar/${avatarPath} alt=" "> ${authorUsername} on ${javaTimestampToLocaleString(timeCreated)}`;
    articleInformationDiv.appendChild(articleInformation);

    articleLink.appendChild(articleTitleDiv);
    articleLink.appendChild(articleContentSegmentDiv);

    resultDiv.appendChild(articleLink);
    resultDiv.appendChild(articleInformationDiv);

}

function javaTimestampToLocaleString(timestamp) {
    const databaseTime = new Date(timestamp + ' UTC');
    return databaseTime.toLocaleString('en-NZ', {timeZone: 'Pacific/Auckland'});
}


function getProcessedTitleHTML(title) {
    return title.replace(keywordPattern, `<span class="keyword-span">${currentKeyword}</span>`)
}

function getProcessedContentHTML(content) {
    const firstAppear = content.indexOf(`${currentKeyword}`);
    if (firstAppear === -1)
        return content.substr(0, 96);
    const startCut = Math.max(firstAppear - 48, 0);
    const endCut = Math.min(content.length, startCut + 96);
    return '<q>... ' + content.substr(startCut, endCut - startCut).replace(keywordPattern, `<span class="keyword-span">${currentKeyword}</span>`) + ' ...</q>';

}
