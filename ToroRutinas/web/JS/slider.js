const slider = document.querySelector("#slider");
let sliderSection = document.querySelectorAll(".slider-section");
let sliderSectionLast = sliderSection[sliderSection.length -1];
    
const btnLeft = document.querySelector("#boton-izq");
const btnRight = document.querySelector("#boton-der");
    
slider.insertAdjacentElement('afterbegin', sliderSectionLast);


function siguiente(){
    
    let sliderSectionFirst = document.querySelectorAll(".slider-selection")[0];
    slider.style.marginLeft = "-200%";
    slider.style.transition = "all 0.5s";
    setTimeout(function(){
        slider.style.transition = "none";
        slider.insertAdjacentElement('beforeend', sliderSectionFirst);
        slider.style.marginLeft = "-100%";
    }, 500); /*Aquí es 500 porque es el equivalente de los 0.5 s de arriba*/
             /*1000 es un segundo*/
}

function anterior(){
    
    let sliderSection = document.querySelectorAll(".slider-selection");
    let sliderSectionLast = sliderSection[sliderSection.length -1];
    slider.style.marginLeft = "0";
    slider.style.transition = "all 0.5s";
    setTimeout(function(){
        slider.style.transition = "none";
        slider.insertAdjacentElement('afterbegin', sliderSectionLast);
        slider.style.marginLeft = "-100%";
    }, 500); /*Aquí es 500 porque es el equivalente de los 0.5 s de arriba*/
             /*1000 es un segundo*/
}

btnRight.addEventListener('click', function(){
    siguiente();
});

btnLeft.addEventListener('click', function(){
    anterior();
});

/*AUTOMATIZAR*/
function automatizar(){
    setInterval(function(){
        siguiente();
    }, 4000); /*Cada 5 segundos cambia*/
}