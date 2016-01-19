var telefonos = [];
var wazes = [];
var traficos = [];
var radios = [];
var camaras = [];

$('.menu--link').click(function (){
	var me = this ;
	if($(me).attr('class').indexOf('menu-link-activo')>=0){
		$(me).removeClass('menu-link-activo');
	}else{
		$(me).addClass('menu-link-activo');
	}
});

$('.menu--link').addClass('menu-link-activo');

//Render Mapa

$('#map-canvas').css('height',($(window).height()-50)+'px')


var center = new google.maps.LatLng(-12.121446, -77.030175);
var uniqueId = 1;

var markers = [];
var iterator = 0;

var map;



function initialize(options) {
	options = options || {};
	var center = new google.maps.LatLng(-12.121446, -77.030175);
	var mapOptions = {
		zoom: 15,
		center: center,
        mapTypeId: google.maps.MapTypeId.ROADMAP
	};

	map = new google.maps.Map(document.getElementById('map-canvas'),
			mapOptions);
			google.maps.event.trigger(map, 'resize');
	if (!options.noDrop) {
		drop();
	}
	
	
	jsontelefono();
	jsonwaze();
	jsonradio();
	jsontrafico();
	jsoncam();

	
}

function drop(options) {
	if (options) {
		addMarker(options);
	} /*else {
		var contador = 0;
		for (var i = 0; i < puntos_sedes.length; i++) {
			
			setTimeout(function () {
				
				addMarker(null,contador);
//                            console.log(puntos_sedes[contador]);
//                            contador++;
contador++;
			}, i * 200);
		}
	}*/

}

function setAllMap(m) {
	for (var i = 0; i < markers.length; i++) {
		markers[i].setMap(m);
	}
}

function clearMarkers() {
	setAllMap(null);
}

function clearSpecificMarker(id){

	for (var i = 0; i < markers.length; i++) {
            if (markers[i].id == id) {
                //Remove the marker from Map                  
                markers[i].setMap(null);
 
                //Remove the marker from array.
                markers.splice(i, 1);
                return;
            }
        }

}

function addMarker(options,ite, arr, name_arr) {
	iterator=ite;
	var image = arr[iterator].icon;
	if (options) {
		iterator = options.position;
	}
	
	var infowindow = new google.maps.InfoWindow({
		index: iterator,
		content: arr[iterator].html + '<img id="close-info-window-x-' + iterator + '" src="imgs/ico-salir.png" style="position:absolute;top:0px;right:0px;margin:0px 0px 0px 0px ;z-index:999999;" />'
	});

	//close-info-window-x

	var marker = new google.maps.Marker({
		position: arr[iterator].punto,
		map: map,
		draggable: false,
		animation: google.maps.Animation.DROP,
        mapTypeId: google.maps.MapTypeId.ROADMAP,
		icon: image
	});
	
	 marker.id = uniqueId;
     uniqueId++;
	

	
	if(name_arr+""=="puntos_trafico"){
		traficos.push(uniqueId);
	}else if(name_arr+""=="puntos_tlf"){
		telefonos.push(uniqueId);
		console.log("telefonos");
		console.log(telefonos);
	}else if(name_arr+""=="puntos_waze"){
		wazes.push(uniqueId);
	}else if(name_arr+""=="puntos_cam"){
		camaras.push(uniqueId);
	}else if(name_arr+""=="puntos_radio"){
		radios.push(uniqueId);
	}
	
	
	markers.push(marker);

	google.maps.event.addListener(marker, 'click', function () {

		google.maps.event.addListener(infowindow, 'domready', function () {
			$('#close-info-window-x-' + this.index).click(function () {
				infowindow.close();
			});
		});

		infowindow.open(map, marker);
	});

	iterator++;

}

function jsontelefono(){

	//TELEFONO
	var puntos_tlf = [{
		punto: new google.maps.LatLng(-12.120260, -77.032572),
		icon:'imgs/icon_telefono_32.png',
		html: '<div class="detalle-acordion" style="padding: 5px 0px 5px 0px;width:250px;" >' +
				'<div style="width:100%;float:left;font-family:Verdana;color:#d90016;font-size:14px;margin-bottom:5px;"><b>Título</b></div>' +
				'<div>Texto</div>' +
				
				'</div>'
	}]
	
	var contador = 0;
	for (var i = 0; i < puntos_tlf.length; i++) {
		setTimeout(function () {
			addMarker(null,contador,puntos_tlf, "puntos_tlf");
			contador++;
		}, i * 200);
	}
	
	console.log("arr_telefonos");
	console.log(telefonos);
	$("#htelefono").val(telefonos);
	
}

function jsoncam(){	
	//CAMARA
	var puntos_cam = [
	{
		punto: new google.maps.LatLng(-12.122574, -77.031980),
		icon:'imgs/video_camara_32.png',
		html: '<div class="detalle-acordion" style="padding: 5px 0px 5px 0px;width:250px;" >' +
				'<div style="width:100%;float:left;font-family:Verdana;color:#d90016;font-size:14px;margin-bottom:5px;"><b>Título</b></div>' +
				'<div>Texto</div>' +
				 
				'</div>'
	}
	]
	
	var contador = 0;
	for (var i = 0; i < puntos_cam.length; i++) {
		setTimeout(function () {
			addMarker(null,contador,puntos_cam, "puntos_cam");
			contador++;
		}, i * 200);
	}
	
}

function jsonwaze(){
	//WAZE
	var puntos_waze = [
	
	{
		punto: new google.maps.LatLng(-12.119310, -77.029021),
		icon:'imgs/icon_waze_32.png',
		html: '<div class="detalle-acordion" style="padding: 5px 0px 5px 0px;width:250px;" >' +
				'<div style="width:100%;float:left;font-family:Verdana;color:#d90016;font-size:14px;margin-bottom:5px;"><b>Título</b></div>' +
				'<div>Texto</div>' +
				
				'</div>'
	}
	
	]
	
	
	var contador = 0;
	for (var i = 0; i < puntos_waze.length; i++) {
		setTimeout(function () {
			addMarker(null,contador,puntos_waze, "puntos_waze");
			contador++;
		}, i * 200);
	}
}

function jsonradio(){
	//RADIO
	var puntos_radio = [
	{
		punto: new google.maps.LatLng(-12.123538, -77.030690),
		icon:'imgs/icon_radio_tetra_32.png',
		html: '<div class="detalle-acordion" style="padding: 5px 0px 5px 0px;width:250px;" >' +
				'<div style="width:100%;float:left;font-family:Verdana;color:#d90016;font-size:14px;margin-bottom:5px;"><b>Título</b></div>' +
				'<div>Texto</div>' +
				
				'</div>'
	}
	]
	
	var contador = 0;
	for (var i = 0; i < puntos_radio.length; i++) {
		setTimeout(function () {
			addMarker(null,contador,puntos_radio, "puntos_radio");
			contador++;
		}, i * 200);
	}
}

function jsontrafico(){
	//TRAFICO
	var puntos_trafico = [
	{
		punto: new google.maps.LatLng(-12.123805, -77.032708),
		icon:'imgs/icon_trafico_32.png',
		html: '<div class="detalle-acordion" style="padding: 5px 0px 5px 0px;width:250px;" >' +
				'<div style="width:100%;float:left;font-family:Verdana;color:#d90016;font-size:14px;margin-bottom:5px;"><b>Título</b></div>' +
				'<div>Texto</div>' +
				
				'</div>'
	}
	]
	
	var contador = 0;
	for (var i = 0; i < puntos_trafico.length; i++) {
		setTimeout(function () {
			addMarker(null,contador,puntos_trafico, "puntos_trafico");
			contador++;
		}, i * 200);
	}
}


function fillPolylines(data) {
		var arrPoligono = data;
		var arrPoints = new Array();
		var arrTemp = null;
		var point = null;
		var myPolygon = null;
		for (var i = 0; i < arrPoligono.length; i++) {
			point = null;
			arrTemp = null;
			arrTemp = arrPoligono[i].split(' ');
			point = new google.maps.LatLng(arrTemp[1], arrTemp[0]);
			arrPoints.push(point);
		}
		myPolygon = new google.maps.Polyline({
					path : arrPoints,
					strokeColor : "#118833",
					strokeOpacity : 1.0,
					strokeWeight : 1,
					fillOpacity : 0.0
				});
		myPolygon.setMap(map);
	};

google.maps.event.addDomListener(window, 'load', initialize);

$(window).resize(function(){
	
	setTimeout(function(){
clearMarkers();
	initialize();
		
	},200);

	
});


