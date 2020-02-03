const uriStart = '/team-java_blogaholic/';
const elegantColorNames = ['Modern Gray', 'Coffee Break', 'Green Breath', 'Sun Bath', 'Rose Perfume', 'Red Blossom'];
const elegantColorHex = ["#535353", "#9e7865", "#78914d", "#ffd500", "#f29494", "#ba0810"];
const limitedColorRadios = [];
const limitedColorLabels = [];
let layout1ColorSelectionDiv;
let layout2ColorSelectionDiv;
const submitButton = initializeSubmitButton();
const freeColorInput = initializeFreeColorInput();
const freeColorLabel = initializeColorLabel();


function constructLimitedColorOptions() {
    for (let i = 1; i <= 6; i++) {
        const radio = document.createElement("input");
        const label = document.createElement("label");
        radio.type = "radio";
        radio.id = `color-radio-${i}`;
        radio.name = "theme-color";
        radio.classList.add("color-radio");
        radio.value = elegantColorHex[i - 1];

        label.style.color = elegantColorHex[i - 1];
        label.style.fontWeight = "bolder";
        label.for = `color-radio-${i}`;
        label.innerText = elegantColorNames[i - 1];

        limitedColorRadios.push(radio);
        limitedColorLabels.push(label);
    }
}

function layout1Selected() {
    layout2ColorSelectionDiv.innerHTML = "";

    constructLimitedColorOptions();
    for (let i = 0; i < 6; i++) {
        layout1ColorSelectionDiv.appendChild(limitedColorRadios[i]);
        layout1ColorSelectionDiv.appendChild(limitedColorLabels[i]);
        if (i % 2 === 0) layout1ColorSelectionDiv.innerHTML += " | ";
        else layout1ColorSelectionDiv.innerHTML += "<br>";
    }
    layout1ColorSelectionDiv.appendChild(submitButton);
    addLayout1EventListener();

}

function layout2Selected() {
    layout1ColorSelectionDiv.innerHTML = "";

    layout2ColorSelectionDiv.appendChild(freeColorLabel);
    layout2ColorSelectionDiv.appendChild(freeColorInput);
    submitButton.style.backgroundColor = "#fc5a03";
    layout2ColorSelectionDiv.appendChild(submitButton);
    freeColorInput.addEventListener("change",
        function () {
            onColorSelection(freeColorInput.value);
        });

}

function onColorSelection(color) {
    submitButton.style.backgroundColor = color;
    document.documentElement.style.setProperty("--theme-color", color);
}

function loadLayoutPreview(containerList) {
    for (let i = 0; i < containerList.length; i++) {
        const previewImage = document.createElement("img");
        previewImage.src = `${uriStart}images/layout-preview/layout${i + 1}.gif`;
        previewImage.width = 360;
        containerList[i].appendChild(previewImage);
    }
}


function initializeSubmitButton() {
    const submitButton = document.createElement("button");
    submitButton.type = "submit";
    submitButton.classList.add("submit-setup-button");
    submitButton.innerText = "Set up my blog!";
    submitButton.style.height = "3em";
    submitButton.style.margin = "10px";
    submitButton.style.fontSize = "1.5em";
    submitButton.style.backgroundColor = "#535353";
    submitButton.display = "block";
    submitButton.margin = "5px auto";
    return submitButton;

}

function initializeFreeColorInput() {
    const freeColorInput = document.createElement("input");
    freeColorInput.type = "color";
    freeColorInput.value = "#fc5a03";
    freeColorInput.name = "theme-color";
    freeColorInput.id = "free-color-input";
    return freeColorInput;
}

function initializeColorLabel() {
    const colorLabel = document.createElement("label");
    colorLabel.for = "free-color-input";
    colorLabel.innerText = "Please pick a theme color";
    colorLabel.innerHTML += "<br>";
    return colorLabel;
}

function addLayout1EventListener() {
    const radios = document.querySelectorAll(".color-radio");
    for (let i = 0; i < 6; i++) {
        radios[i].addEventListener("click", function () {
            onColorSelection(elegantColorHex[i]);
        });
    }


}