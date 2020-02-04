window.addEventListener("load", function () {
   let small = window.matchMedia("(max-width: 700px)").matches;
      updateResponsive(small);
});
window.addEventListener("resize", function () {
   let small = window.matchMedia("(max-width: 700px)").matches;
   updateResponsive(small);
});

function updateResponsive(small) {
   if (small) {
      document.querySelector("#home-name").innerText="";
   }
   else {
      document.querySelector("#home-name").innerText="Blogaholic!";
   }
}