//muda os texto
document.addEventListener("DOMContentLoaded", function () {
    const cursosMenu = document.querySelectorAll('.curso-menu');
    cursosMenu.forEach(function (cursoItem) {
        cursoItem.addEventListener('click', function () {
            const cursoId = cursoItem.getAttribute('data-curso-id');
            document.querySelector('#secao2').scrollIntoView({ behavior: 'smooth', block: 'start' });
            const cursosSecao2 = document.querySelectorAll('.curso');
            cursosSecao2.forEach(function (curso) {
                curso.classList.toggle('ativo', curso.getAttribute('data-curso') === cursoId);
            });
        });
    });
});

const toggleButton = document.getElementById('alternar-cursos');
const cursos = document.querySelectorAll('.curso');
let cursosVisiveis = 3;
toggleButton.style.cursor = 'pointer';

toggleButton.addEventListener('click', () => {
    if (cursosVisiveis >= cursos.length) {
        cursosVisiveis = 3;
        cursos.forEach((curso, index) => {
            if (index < 3) {
                curso.style.display = 'flex';
                curso.style.opacity = 0;
                anime({
                    targets: curso,
                    opacity: [0, 1],
                    translateY: [20, 0],
                    easing: 'easeOutExpo',
                    duration: 400,
                    delay: index * 100
                });
            } else {
                anime({
                    targets: curso,
                    opacity: [1, 0],
                    translateY: [0, -20],
                    easing: 'easeOutExpo',
                    duration: 400,
                    complete: () => { curso.style.display = 'none'; }
                });
            }
        });
        toggleButton.innerHTML = '<i class="material-icons">keyboard_arrow_down</i>';
    } else {
        const proximoIndice = cursosVisiveis + 3;
        for (let i = cursosVisiveis; i < proximoIndice && i < cursos.length; i++) {
            cursos[i].style.display = 'flex';
            cursos[i].style.opacity = 0;
            anime({
                targets: cursos[i],
                opacity: [0, 1],
                translateY: [20, 0],
                easing: 'easeOutExpo',
                duration: 400,
                delay: (i - cursosVisiveis) * 100
            });
        }
        for (let i = 0; i < cursosVisiveis; i++) {
            anime({
                targets: cursos[i],
                opacity: [1, 0],
                translateY: [0, -20],
                easing: 'easeOutExpo',
                duration: 400,
                complete: () => { cursos[i].style.display = 'none'; }
            });
        }
        cursosVisiveis = proximoIndice > cursos.length ? cursos.length : proximoIndice;
        toggleButton.innerHTML = cursosVisiveis === cursos.length ? '<i class="material-icons">keyboard_arrow_up</i>' : '<i class="material-icons">keyboard_arrow_down</i>';
    }
});


//popup e imagem
document.addEventListener("DOMContentLoaded", function () {
    let popupOpen = false;

    const createPopup = (content, imageUrl) => {
        if (popupOpen) return;
        popupOpen = true;

        const overlay = document.createElement("div");
        overlay.classList.add("overlay");
        overlay.style.position = "fixed";
        overlay.style.top = "0";
        overlay.style.left = "0";
        overlay.style.width = "100%";
        overlay.style.height = "100%";
        overlay.style.backgroundColor = "rgba(0, 0, 0, 0)";
        overlay.style.zIndex = "999";
        document.body.appendChild(overlay);

        const popup = document.createElement("div");
        popup.classList.add("popup");
        popup.innerHTML = `
            <div class="popup-content">
                <img src="${imageUrl}" alt="Imagem do Curso" style="width: 100%; height: auto;"/>
                <p>${content}</p>
                <button class="close-popup">Fechar</button>
            </div>
        `;
        popup.style.position = "fixed";
        popup.style.top = "50%";
        popup.style.left = "50%";
        popup.style.transform = "translate(-50%, -50%) scale(0.8)";
        popup.style.opacity = "0";
        popup.style.zIndex = "1000";
        document.body.appendChild(popup);

        anime({
            targets: overlay,
            backgroundColor: "rgba(0, 0, 0, 0.7)",
            duration: 100,
            easing: "easeOutQuad",
        });

        anime({
            targets: popup,
            opacity: [0, 1],
            scale: [0.8, 1],
            duration: 300,
            easing: "easeOutQuad",
        });

        const closeButton = popup.querySelector(".close-popup");
        closeButton.addEventListener("click", () => {
            anime({
                targets: popup,
                opacity: [1, 0],
                scale: [1, 0.8],
                duration: 100,
                easing: "easeInQuad",
                complete: () => {
                    document.body.removeChild(popup);
                },
            });

            anime({
                targets: overlay,
                backgroundColor: "rgba(0, 0, 0, 0)",
                duration: 100,
                easing: "easeInQuad",
                complete: () => {
                    document.body.removeChild(overlay);
                    popupOpen = false;
                },
            });
        });

        overlay.addEventListener("click", () => closeButton.click());
    };

    const isSmallScreen = () => window.innerWidth <= 1306;

    document.querySelectorAll(".curso").forEach(button => {
        button.style.cursor = "pointer";
        button.addEventListener("click", function () {
            const cursoId = this.getAttribute("data-curso");

            // Usando a API REST Java (equivalente ao get_curso.php)
            fetch(`${window.location.origin}/api/curso?id_curso=${cursoId}`)
                .then(response => response.json())
                .then(data => {
                    if (data && data.conteudo && data.imagem) {
                        const { conteudo, imagem } = data;

                        document.getElementById("curso-imagem").src = imagem;

                        if (isSmallScreen()) {
                            createPopup(conteudo, imagem);
                        } else {
                            const texto = document.querySelector(".navegacao-direita .texto1");
                            texto.innerText = conteudo;
                        }
                    } else {
                        console.log("Conteudo do curso nao encontrado");
                    }
                })
                .catch(err => {
                    console.error("Erro na requisicao:", err);
                });
        });
    });
});