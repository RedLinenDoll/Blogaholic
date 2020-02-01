const uriStart = '/team-java_blogaholic/';

async function loadAllBlogList() {
    const blogListContainer = document.querySelector("#all-blog-container");
    let response = await fetch(`${uriStart}load-blog-list`);
    let blogUserList = await response.json();
    blogListContainer.innerHTML = "";
    blogUserList.forEach(blogUser => {
            const blogDiv = renderBlogDiv(blogUser);
            if (blogDiv !== null)
                blogListContainer.appendChild(blogDiv);
        }
    );
}

function renderBlogDiv(blogUser) {
    if (blogUser.blogName === null) return null;
    const blogDiv = document.createElement("div");
    blogDiv.classList.add("blog-div", "page-item-div");

    const avatarDiv = document.createElement("div");
    avatarDiv.classList.add("block-avatar-div");
    avatarDiv.innerHTML = `<a href="${uriStart}user-profile?user-id=${blogUser.userID}"><img src="${uriStart}images/avatar/${blogUser.avatarPath}" class="block-avatar blog-avatar" alt=""></a>`;
    avatarDiv.innerHTML += `<p class="blog-tiny-username">${blogUser.username}</p>`;

    const blogLink = document.createElement("a");
    blogLink.classList.add("full-article-link");
    blogLink.href = `${uriStart}blog-view?authorID=${blogUser.userID}`;

    const blogTitleDiv = document.createElement("div");
    blogTitleDiv.classList.add("blog-title-div", "page-item-title-div");
    const blogTitle = document.createElement("h2");
    blogTitleDiv.classList.add("blog-title", "page-item-title");
    blogTitle.innerText = blogUser.blogName;
    blogTitleDiv.appendChild(blogTitle);

    const blogBriefDiv = document.createElement("div");
    const blogBrief = document.createElement("p");
    blogBrief.classList.add("blog-brief", "page-item-brief");
    blogBrief.innerText = blogUser.blogDescription;
    blogBriefDiv.appendChild(blogBrief);

    blogLink.appendChild(blogTitleDiv);
    blogLink.appendChild(blogBriefDiv);
    blogDiv.appendChild(avatarDiv);
    blogDiv.appendChild(blogLink);

    return blogDiv;

}