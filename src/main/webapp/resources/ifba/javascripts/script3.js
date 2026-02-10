document.addEventListener('DOMContentLoaded', () => {
    const section = document.querySelector('#secao3');
    let hasAnimated = false;

    function isElementInViewport(el) {
        const rect = el.getBoundingClientRect();
        return (
            rect.top <= window.innerHeight / 1 &&
            rect.bottom >= window.innerHeight / 1
        );
    }

    function animateSection() {
        if (isElementInViewport(section) && !hasAnimated) {
            hasAnimated = true;
            anime({
                targets: '.retangulo',
                translateY: [100, 0],
                opacity: [0, 1],
                duration: 1000,
                easing: 'easeOutQuad',
            });

            anime({
                targets: '.lado-esquerdo',
                translateX: [-50, 0],
                opacity: [0, 1],
                duration: 800,
                delay: 200,
                easing: 'easeOutQuad',
            });

            anime({
                targets: '.lado-direito',
                translateX: [50, 0],
                opacity: [0, 1],
                duration: 800,
                delay: 400,
                easing: 'easeOutQuad',
            });

            anime({
                targets: '.linha-vertical',
                scaleY: [0, 1],
                opacity: [0, 1],
                duration: 600,
                delay: 600,
                easing: 'easeOutQuad',
            });

            anime({
                targets: '.meio-circulo',
                opacity: [0, 1],
                duration: 600,
                delay: 800,
                easing: 'easeOutBack',
            });
        }
    }

    window.addEventListener('scroll', animateSection);
});
