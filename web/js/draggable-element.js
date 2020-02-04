
window.addEventListener("load", function () {
    const draggableContainers = document.querySelectorAll(".draggable-container");
    draggableContainers.forEach(container => {dragElement(container)});

});

function dragElement(draggableContainer) {
    let pos1 = 0, pos2 = 0, pos3 = 0, pos4 = 0;
    if (document.getElementById(draggableContainer.id + "-head")) {
        // if present, the header is where you move the DIV from:
        document.getElementById(draggableContainer.id + "-head").onmousedown = dragMouseDown;
    } else {
        // otherwise, move the DIV from anywhere inside the DIV:
        draggableContainer.onmousedown = dragMouseDown;
    }

    function dragMouseDown(e) {
        e = e || window.event;
        e.preventDefault();
        // get the mouse cursor position at startup:
        pos3 = e.clientX;
        pos4 = e.clientY;
        document.onmouseup = closeDragElement;
        // call a function whenever the cursor moves:
        document.onmousemove = elementDrag;
    }

    function elementDrag(e) {
        e = e || window.event;
        e.preventDefault();
        // calculate the new cursor position:
        pos1 = pos3 - e.clientX;
        pos2 = pos4 - e.clientY;
        pos3 = e.clientX;
        pos4 = e.clientY;
        // set the element's new position:
        draggableContainer.style.top = (draggableContainer.offsetTop - pos2) + "px";
        draggableContainer.style.left = (draggableContainer.offsetLeft - pos1) + "px";
    }

    function closeDragElement() {
        // stop moving when mouse button is released:
        document.onmouseup = null;
        document.onmousemove = null;
    }
}
