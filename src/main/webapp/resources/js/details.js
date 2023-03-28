 $(document).ready(function () {
    console.log("rara")
    const container = $('.img');
    const button = $('#add-button');

    container.hover(

        function () {
            button.show();
        },
        function () {
            button.hide();
        }
    );
})

let slideIndex = 1;
showSlides(slideIndex);


// Thumbnail image controls
function currentSlide(n) {
    showSlides(slideIndex = n);
}

function showSlides(n) {
    let i;
    let slides = document.getElementsByClassName("section");
    let buttons = document.getElementsByClassName("section-btn");
    if (n > slides.length) { slideIndex = 1 }
    if (n < 1) { slideIndex = slides.length }
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";

    }

    slides[slideIndex - 1].style.display = "block";

}