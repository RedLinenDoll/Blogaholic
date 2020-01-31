const uriStart = '/team-java_blogaholic/';

function loadLayoutPreview (containerList) {
    for (let i = 0; i < containerList.length; i++) {
        const previewImage = document.createElement("img");
        previewImage.src=`${uriStart}images/layout-preview/layout${i+1}.gif`;
        previewImage.width = 360;
        containerList[i].appendChild(previewImage);
    }

}