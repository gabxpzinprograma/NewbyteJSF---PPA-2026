anime({
  targets: '.nav__ul li',
  translateY: [-50, 0],
  opacity: [0, 1],
  delay: anime.stagger(100),
  duration: 1000,
  easing: 'easeOutQuad',
});

anime({
  targets: '.cta__container h1',
  translateX: [-300, 0],
  opacity: [0, 1],
  duration: 1200,
  easing: 'easeOutQuad',
});

anime({
  targets: '.cta__container p',
  translateX: [300, 0],
  opacity: [0, 1],
  delay: 500,
  duration: 1200,
  easing: 'easeOutQuad',
});

anime({
  targets: '.logo__container img',
  translateY: [-100, 0],
  opacity: [0, 1],
  duration: 1500,
  easing: 'easeOutBounce',
});

anime({
  targets: '.footer',
  translateY: [100, 0],
  opacity: [0, 1],
  duration: 1500,
  easing: 'easeOutQuad',
});

document.querySelectorAll('.nav__li img').forEach((item) => {
  item.addEventListener('mouseover', () => {
    anime({
      targets: item,
      scale: 1.2,
      duration: 300,
      easing: 'easeInOutQuad',
    });
  });

  item.addEventListener('mouseout', () => {
    anime({
      targets: item,
      scale: 1.0,
      duration: 300,
      easing: 'easeInOutQuad',
    });
  });
});

const logo = document.querySelector('.logo__container img');

logo.addEventListener('mouseover', () => {
  anime({
    targets: logo,
    scale: 1.1,
    duration: 300,
    easing: 'easeInOutQuad',
  });
});

logo.addEventListener('mouseout', () => {
  anime({
    targets: logo,
    scale: 1.0,
    duration: 300,
    easing: 'easeInOutQuad',
  });
});

const menuItem = document.querySelector('.menu__item');
const menuItemImage = menuItem.querySelector('img');
const dropdownMenu = document.querySelector('.dropdown-menu');
const dropdownItems = dropdownMenu.querySelectorAll('ul li');

menuItem.addEventListener('mouseenter', () => {
  anime({
    targets: menuItemImage,
    rotate: '1turn',
    duration: 500,
    easing: 'easeInOutQuad',
  });

  anime({
    targets: dropdownMenu,
    opacity: [0, 1],
    scaleY: [0, 1],
    duration: 400,
    easing: 'easeOutQuad',
    begin: () => {
      dropdownMenu.style.display = 'block';
    },
  });

  anime({
    targets: dropdownItems,
    opacity: [0, 1],
    translateX: [-50, 0],
    delay: anime.stagger(100),
    duration: 400,
    easing: 'easeOutQuad',
  });
});

menuItem.addEventListener('mouseleave', () => {
  anime({
    targets: menuItemImage,
    rotate: '0turn',
    duration: 500,
    easing: 'easeInOutQuad',
  });

  anime({
    targets: dropdownItems,
    opacity: [1, 0],
    translateX: [0, -50],
    duration: 300,
    easing: 'easeInQuad',
  });

  anime({
    targets: dropdownMenu,
    opacity: [1, 0],
    scaleY: [1, 0],
    duration: 400,
    easing: 'easeInQuad',
    complete: () => {
      dropdownMenu.style.display = 'none';
    },
  });
});


