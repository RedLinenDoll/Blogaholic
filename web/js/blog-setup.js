const uriStart = '/team-java_blogaholic/';

function loadLayoutPreview (containerList) {
    for (let i = 0; i < containerList.length; i++) {
        const previewImage = document.createElement("img");
        previewImage.src=`${uriStart}images/layout-preview/layout${i+1}.gif`;
        previewImage.width = 360;
        containerList[i].appendChild(previewImage);
    }
}

const colorLabel = `<label for="theme-color">Please pick your color</label><br>`;
const freeColorInput = `<input type="color" value="#fc5a03" name="theme-color" id="theme-color" required><br>`;

const limitedColorInput =
    `<br><input type="radio" id="color1" name="theme-color" value="#3f99ae"> <label for="color1" style="color:#3f99ae">calming blue</label>
 | <input type="radio" id="color2" value="#9e7865" name="theme-color"> <label for="color2" style="color: #937865">coffee break</label>
<br><input type="radio" id="color3" value="#78914d" name="theme-color"> <label for="color3" style="color: #78914d">green breath</label>
 | <input type="radio" id="color4" value="#ffd500" name="theme-color"> <label for="color4" style="color: #ffd500">sun bath</label>
<br><input type="radio" id="color5" value="#f29494" name="theme-color"> <label for="color5" style="color: #f29494">rose blossom</label><br>
`;

window.addEventListener("load", function () {
    const layout1ColorSelectionDiv = document.querySelector("#layout1-color-selection-container");
    const layout2ColorSelectionDiv = document.querySelector("#layout2-color-selection-container");
    const layout1Radio = document.querySelector("#layout-radio-1");
    const layout2Radio = document.querySelector("#layout-radio-2");

    setTimeout(function () {
        document.body.style.backgroundImage = "none";
        document.querySelector("#clean-message").style.color = "white";
    }, 1000);
    loadLayoutPreview([document.querySelector("#layout-preview-1"), document.querySelector("#layout-preview-2")]);

    layout1Radio.addEventListener("click", function () {showLayoutColorPick(1)});
    layout2Radio.addEventListener("click", function () {showLayoutColorPick(2)});

    const submitButton = document.createElement("button");
    submitButton.type="submit";
    submitButton.classList.add("submit-setup-button");
    submitButton.innerText = "Set up my blog!";
    submitButton.style.height = "3em";
    submitButton.style.margin = "10px";
    submitButton.style.fontSize = "1.5em";
    submitButton.style.backgroundColor = "#fc5a03";

    function showLayoutColorPick(id) {
        if (id === 1) {
            layout2ColorSelectionDiv.innerHTML = "";
            layout1ColorSelectionDiv.innerHTML = colorLabel +  freeColorInput;
            layout1ColorSelectionDiv.appendChild(submitButton);
            const colorSelector = document.querySelector("#theme-color");
            colorSelector.addEventListener("change", function () {
                submitButton.style.backgroundColor = colorSelector.value;
            })

        }
        if (id===2) {
            layout1ColorSelectionDiv.innerHTML = "";
            layout2ColorSelectionDiv.innerHTML = colorLabel + limitedColorInput;

            layout2ColorSelectionDiv.appendChild(submitButton);
        }

    }



});

