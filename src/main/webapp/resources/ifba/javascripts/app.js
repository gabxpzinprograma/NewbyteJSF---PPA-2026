
// estou perdendo o controle de mim mesmo
document.addEventListener("DOMContentLoaded", function () {
    anime({ // 04:50, dia 12-12-2024
        targets: '.search-container',
        opacity: [0, 1],
        translateY: [20, 0],
        easing: 'easeOutQuad',
        duration: 1000,
        delay: 200
    });

    anime({
        targets: '.search-input',
        opacity: [0, 1],
        translateY: [20, 0],
        easing: 'easeOutQuad',
        duration: 1000,
        delay: 400
    });

    anime({
        targets: '.cards-section',
        opacity: [0, 1],
        translateY: [20, 0],
        easing: 'easeOutQuad',
        duration: 1000,
        delay: 800
    });

    anime({
        targets: '.card-container',
        opacity: [0, 1],
        translateY: [20, 0],
        easing: 'easeOutQuad',
        duration: 1000,
        delay: 1000
    });

    anime({
        targets: '.card-header',
        opacity: [0, 1],
        translateY: [20, 0],
        easing: 'easeOutQuad',
        duration: 1000,
        delay: 1200
    });

    anime({
        targets: '.card-footer',
        opacity: [0, 1],
        translateY: [20, 0],
        easing: 'easeOutQuad',
        duration: 1000,
        delay: 1400
    });

    anime({
        targets: '.live-sessions h2',
        opacity: [0, 1],
        translateY: [20, 0],
        easing: 'easeOutQuad',
        duration: 1000,
        delay: 1600
    });

    anime({
        targets: '.live-thumbnail',
        opacity: [0, 1],
        translateY: [20, 0],
        easing: 'easeOutQuad',
        duration: 1000,
        delay: 1800
    });

    anime({
        targets: '.user-info',
        opacity: [0, 1],
        translateY: [20, 0],
        easing: 'easeOutQuad',
        duration: 1000,
        delay: 2000
    });

    anime({
        targets: '.notification-box',
        opacity: [0, 1],
        translateY: [20, 0],
        easing: 'easeOutQuad',
        duration: 1000,
        delay: 2200
    });

    anime({
        targets: '.nav ul li a',
        opacity: [0, 1],
        translateY: [20, 0],
        easing: 'easeOutQuad',
        duration: 1000,
        delay: 2400
    });
});

document.addEventListener("DOMContentLoaded", function () {
    // CLARO, eles sempre vão dizer que sou doido, mas quem quer fazer isso as 03:30 da manhã? claro, sempre o gab, sempre o gab
    // isso é normal, eu gosto disso, eu mereço isso, correto? kkkkkk, claro que mereço, eu que iniciei isso, porquê não mereceria? kkkkkk
    // você ai está analizando o codigo? claro que está, ou sou somente eu, em algum ano, vendo algo, que eu simplismente não aguento mais
    // tudo está bem, eu ficarei bem, eu sou um bom programador, eu sou um bom programador, eu sou um bom programador, eu sou um bom programador
    // porque isso continua dando erro? porque? meus pais já vieram aqui três vezes, mas eu preciso terminar, o q eles pensariam de mim se nao terminasse ?
    // porque eu me importo com tudo isso? porque? por favor, só quero dormir, ou ser notado, ou qualquer coisa, menos linhas de codigo, por favor
    const cards = document.querySelectorAll(".card-container");
    const prevBtn = document.getElementById("prev");
    const nextBtn = document.getElementById("next");

    let currentIndex = 0;
    const visibleCards = 2;

    function updateVisibility() {
        cards.forEach((card, index) => {
            card.style.display = (index >= currentIndex && index < currentIndex + visibleCards) ? "block" : "none";
        });

        prevBtn.disabled = currentIndex === 0;
        nextBtn.disabled = currentIndex + visibleCards >= cards.length;
    }

    prevBtn.addEventListener("click", () => {
        if (currentIndex > 0) {
            currentIndex--;
            updateVisibility();
        }
    });



    nextBtn.addEventListener("click", () => {
        if (currentIndex + visibleCards < cards.length) {
            currentIndex++;
            updateVisibility();
        }
    });

    updateVisibility();

});