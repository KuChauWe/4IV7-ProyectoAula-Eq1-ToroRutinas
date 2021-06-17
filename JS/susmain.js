const btnMenu = document.querySelector("#btnMenu");
const menu = document.querySelector("#menu")
btnMenu.addEventListener("click", function(){
    menu.classList.toggle("mostrar")
});
//Lo de arriba es la función para mostrar las categorias principales
const Btn = document.querySelectorAll(".menu__link");
for(let i=0; i < Btn.length; i++){
    Btn[i].addEventListener("click", function(){
        const Btn = this.nextElementSibling;
        const height = Btn.scrollHeight;
        if(Btn.classList.contains("desplegar")){
            Btn.classList.remove("desplegar")
            Btn.removeAttribute("style");
        } else{
            Btn.classList.add("desplegar");
            Btn.style.height = height + "px";
        }
    });
}

const normal = document.querySelector(".sub-submenu-btn")
const subNtp = document.querySelector(".sub-ntp")
normal.addEventListener("click", function(){
    subNtp.classList.toggle("normal")
});

const menuN = document.querySelector("#botonSuperior")
const pulsado = document.querySelector(".menu")
menuN.addEventListener("click", function(){
    pulsado.classList.toggle("menuN")
    pulsado.classList.toggle("submenuN")

});

const pulsadoIn = document.querySelector(".submenu")
menuN.addEventListener("click", function(){
    pulsadoIn.classList.toggle("submenuN")

});



window.addEventListener('load', function(e){
	new Glider(document.querySelector('.carousel__lista'), {
		slidesToShow: 1,
		slidesToScroll: 1,
		dots: '.carousel__indicadores',
		arrows: {
			prev: '.carousel__anterior',
			next: '.carousel__siguiente'
		},
		responsive: [
			{
			  // screens greater than >= 775px
			  breakpoint: 550,
			  settings: {
				// Set to `auto` and provide item width to adjust to viewport
				slidesToShow: 1,
				slidesToScroll: 1
			  }
			},{
			  // screens greater than >= 1024px
			  breakpoint: 800,
			  settings: {
				slidesToShow: 3,
				slidesToScroll: 3,
				itemWidth: 150,
				duration: 2
			  }
			}
		  ]
		})});