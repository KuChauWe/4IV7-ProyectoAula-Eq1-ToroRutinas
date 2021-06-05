let indice = 1;
muestraSlides(indice);

function avanzar(n){
    muestraSlides(indice+=n);
}

function posicionSlide(n){
    muestraSlides(indice=n);
}

function muestraSlides(n){
    let i;
    let slides = document.getElementsByClassName('slider-section');
    let barras = document.getElementsByClassName('barra');

    if(n > slides.length){
        indice = 1;
    }
    if(n < 1){
        indice = slide.length();
    }
    for(i = 0; i < slides.length; i++){
        slides[i].style.display = 'none';
    }
    for(i = 0; i < barras.length; i++){
        barras[i].className = barras[i].className.replace(" active", "");
    }

    slides[indice-1].style.display = 'block';
    barras[indice-1].className += ' active';

}

var intervalo;

function ejecutar(n){
    if(n == 1){
        intervalo = setInterval(function tiempo(){
            muestraSlides(indice+=1)
        }, 4000); /*Cada 4 segundos*/
    }
    if(n == 0){
        clearInterval(intervalo);
    }    
}

function detener(){
    timeout = clearTimeout(detener, 4000);
}
