function onSuccess(googleUser) {
    onSignIn(googleUser);
}

function onFailure(error) {
    console.log(error);
}



function renderButton() {
    gapi.signin2.render('google-log-in-button', {
        'scope': 'profile email',
        'width': 300,
        'height': 50,
        'longtitle': true,
        'theme': 'dark',
        'onsuccess': onSuccess,
        'onfailure': onFailure
    });
}

function onSignIn(googleUser) {
    var profile = googleUser.getBasicProfile();
    var redirectUrl = './google-login';
    //using jquery to post data dynamically
    var form = $('<form action="' + redirectUrl + '" method="post" style="display: none">' +
        '<input type="hidden" name="idtoken" value="' +
        googleUser.getAuthResponse(true).id_token + '" />' +
        '</form>');
    $('body').append(form);
    form.submit();

}














