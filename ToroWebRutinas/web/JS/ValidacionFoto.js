function validarExt(){ 
	var fotoP = document.getElementById('fotoP');
	var archivoRuta = fotoP.value;
	var extPermitidas = /(.png|.PNG|.jpeg|.JPEG|.jpg|.JPG)$/i;
	if(!extPermitidas.exec(archivoRuta)){ 
		alert('Asegurese de haber seleccionado una imagen tipo .png, .jpeg o .jpg');
		fotoP.value = '';
		return false;
	} 
	else {
		 //PRevio del PDF 
		if (fotoP.files && fotoP.files[0])
		{ 
			var visor = new FileReader();
			visor.onload = function(e) {
				document.getElementById('foto_seleccionada').innerHTML = 
				'<embed src="'+e.target.result+'" width="120" height="120"/>';
			}; visor.readAsDataURL(fotoP.files[0]);
		} 
	}
}