function renderFollowOption(authorID, visitorID) {

    const followSpan = document.querySelector("#follow-option");
    if (authorID === visitorID) {
        followSpan.innerHTML="You Have TODO followers."
    }
    else {
        followSpan.innerHTML =`<i class="far fa-circle"></i> follow him`;
    }

    // TODO write UserDAO methods and Servlet to get the follower number of a user,
     // TODO and write UserDAO method to see if a user is following another user,
    // TODO and write method to make a user follow another user.

}