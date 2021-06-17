/*Ventana emergente para crear rutina*/
const open = document.getElementById('open');
const modal_container = document.getElementById('modal_container');
const close = document.getElementById('close');

open.addEventListener('click', () => {
    modal_container.classList.add('show');
});

close.addEventListener('click', () => {
    modal_container.classList.remove('show');
});


/*Ventana emergente para eliminar rutina*/
const open2 = document.getElementById('open2');
const open2b = document.getElementById('open2b');
const open2c = document.getElementById('open2c');
const modal_container2 = document.getElementById('modal_container2');
const close2 = document.getElementById('close2');

open2.addEventListener('click', () => {
    modal_container2.classList.add('show');
});
open2b.addEventListener('click', () => {
    modal_container2.classList.add('show');
});
open2c.addEventListener('click', () => {
    modal_container2.classList.add('show');
});

close2.addEventListener('click', () => {
    modal_container2.classList.remove('show');
});


/*Ventana emergente para agregar ejercicio a la rutina*/
const open3 = document.getElementById('open3');
const open3b = document.getElementById('open3b');
const open3c = document.getElementById('open3c');
const modal_container3 = document.getElementById('modal_container3');
const close3 = document.getElementById('close3');

open3.addEventListener('click', () => {
    modal_container3.classList.add('show');
});
open3b.addEventListener('click', () => {
    modal_container3.classList.add('show');
});
open3c.addEventListener('click', () => {
    modal_container3.classList.add('show');
});

close3.addEventListener('click', () => {
    modal_container3.classList.remove('show');
});
