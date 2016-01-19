
var center = new google.maps.LatLng(-12.121446, -77.030175);


var arrPuntosGlobales = [];

var markers = [];
var iterator = 0;

var map;

var markerClusterer = null;
var wazeReady = false;
var dolphinReady = false;

var wazeVal = false;
var dolphinVal = false;

var arrJams = [];

var arrWazeExistentes = [];

var arrTempUUID = [];

function existArraWaze(key){
	var existe = false;
	for(var i = 0 ; i < arrWazeExistentes.length ; i++){
		if(arrWazeExistentes[i].key == key){
			existe = true;
			break;
		}
	}
	return existe;
}


function deleteUUID(key){
	for(var i = 0 ; i < arrWazeExistentes.length ; i++){
		if(arrWazeExistentes[i].key == key){
			arrWazeExistentes.splice(key,1);
			break;
		}
	}
}

var procesandoWaze = false;

var procesandoTetra = false;

var procesandoVoxiva = false;

//$('.menu--link').addClass('menu-link-activo');

$('.menu--link').click(function (){
	var me = this ;
	var activo;
	if($(me).attr('class').indexOf('menu-link-activo')>=0){
		$(me).removeClass('menu-link-activo');
		activo = false;
	}else{
		activo = true;
		$(me).addClass('menu-link-activo');
	}
	
	var tipos = $('.menu-link-activo');
	var arrSelecteds = [];
	
	$.each(tipos,function(i,t){
		arrSelecteds.push($(t).attr('data-tipo'));
	});
	
	$.each(MapaIncidencias.markers,function(i,m){

		if(arrSelecteds.indexOf(m.tipo)>=0){
			m.marker.setVisible(true);
			
			if(m.tipo == 'waze'){
				
				$.each(MapaIncidencias.polylines,function(a,b){
					b.setVisible(true);
				});
				
			}
		}else{
			m.marker.setVisible(false);

			if(m.tipo == 'waze'){
				
				$.each(MapaIncidencias.polylines,function(a,b){
					b.setVisible(false);
				});
				
			}
			
		}
		
	});
	
});


$('#map-canvas').css('height',($(window).height()-50)+'px')

var MapaIncidencias = {

		markersWaze:[],
		
		polylines:[],
		
		markersTetra:[],
		
		markers:[],
		
		markersVoxiva:[],
	
		init:function(){
			
			MapaIncidencias.map = new google.maps.Map(document.getElementById('map-canvas'), {
				zoom: 15,
				center: new google.maps.LatLng(-12.121446, -77.030175),
		        mapTypeId: google.maps.MapTypeId.ROADMAP
			});
			
			
//			var markerVillena = new google.maps.Marker({
//				position: new google.maps.LatLng(-12.127392, -77.035141),
//				map: MapaIncidencias.map,
//				draggable: false,
//		        mapTypeId: google.maps.MapTypeId.ROADMAP,
//		        icon: new google.maps.MarkerImage( 
//								        		'/mobileApps/images/camara_rojo.png', 
//								    		    null,
//								    		    null,
//								    		    null, 
//								    		    new google.maps.Size(32,33)
//								        		)
//			});
//			
//			//markerVillena.setAnimation(google.maps.Animation.BOUNCE);
//			
//			var infowindow = new google.maps.InfoWindow({
//			    		content: '<b style="font-size:18px;">Puente Villena</b><br><iframe frameBorder="0" scrolling="no" src="http://172.16.10.150/testCamara/demo.html" width="500px" height="500px"></iframe>'
//			    	});
//		    		
//			 			    	
//			    	google.maps.event.addListener(markerVillena, 'click', function () {
//			    		
//			    		//markerVillena.setAnimation(null);
//			    		
//			    		infowindow.open(MapaIncidencias.map, markerVillena);
//			    		
//			    	});

			var kmzLayer = new google.maps.KmlLayer('http://digital.miraflores.gob.pe:8080/a/sm/camaras_miraflores.kmz');
			kmzLayer.setMap(MapaIncidencias.map);
			
			MapaIncidencias.buildWazeService();
			MapaIncidencias.buildTetraService();
			MapaIncidencias.buildServiceVoxiva();
			
			MapaIncidencias.refreshWaze();
			MapaIncidencias.refreshTetra();
			MapaIncidencias.refreshVoxiva();

			
		}
		,getVisible:function(tipo){
		
			var tipos = $('.menu-link-activo');
			var arrSelecteds = [];
			var visible = true;
			$.each(tipos,function(i,t){
				arrSelecteds.push($(t).attr('data-tipo'));
			});
			
			if(arrSelecteds.indexOf(tipo)>=0){
					visible = true;
			}else{
					visible = false;
			}
			
			return visible;
			
		}
		,addMarker:function(options){
						
			
			var image = options.icon;
			 
			var infowindow = new google.maps.InfoWindow({
				index: options.index,
				content: options.html
			});
		
			var visible = true;
			
			
			
			switch(options.tipo){
			
				case 'waze':
				
					visible = MapaIncidencias.getVisible('waze');
				
				break;
				
				case 'tetra1':
				
					visible = MapaIncidencias.getVisible('tetra1');
				
				break;
				
				case 'tetra2':
				
					visible = MapaIncidencias.getVisible('tetra2');
				
				break;
				case 'tetra3':
				
					visible = MapaIncidencias.getVisible('tetra3');
				
				break;
				
				case 'tetra4':
					
					visible = MapaIncidencias.getVisible('tetra4');
				
				break;
				
			}
			
			
			var marker = new google.maps.Marker({
				
				position: options.punto,
				map: MapaIncidencias.map,
				draggable: false,
				visible:visible,
		        mapTypeId: google.maps.MapTypeId.ROADMAP,
		        icon: new google.maps.MarkerImage( 
								        		image, 
								    		    null,
								    		    null,
								    		    null, 
								    		    new google.maps.Size(32,36)
								        		)
			});
			
			var isAnimation = false;
			if(options.tipo == 'waze' ){
				
				if(options.isFirstCall){
					arrWazeExistentes.push({key:options.uuid});
				}else{
					if(!existArraWaze(options.uuid)){
						marker.setAnimation(google.maps.Animation.BOUNCE);
						
						isAnimation = true;
						
						arrWazeExistentes.push({key:options.uuid});
					}
				}
							
			}
			
			 
		
			var objMarker = {
					marker : marker
					,tipo:options.tipo
					,punto:options.punto
					,uuid:options.uuid
				
			}
			
			MapaIncidencias.markers.push(objMarker);
			
			if(options.tipo == 'waze'){
				MapaIncidencias.markersWaze.push(objMarker);
			}
			
			if(options.tipo.indexOf('tetra')>=0){
				MapaIncidencias.markersTetra.push(objMarker);
			}
			
		
			if(options.isRadioTetra){
			google.maps.event.addListener(marker, 'click', function () {
		
				var content = $.parseHTML(infowindow.content);
				var issi = $(content).find(".issi");
			
				$.ajax({
				    url: "/mobileApps/cierre-calles/obtener-detalle-incidencia",
				    dataType: "json",
				    data : {issi : $(issi).html()},
				    success: function( response ) {
				    	var data = response.data;
		
				    	var varhtml = $($.parseHTML(infowindow.content));
				    	
				    	var afterHtml = '<p><strong>Modelo Radio: </strong>'+data.modeloRadio+'<br /> <strong>Tipo Radio: </strong> '+data.tipoRadio+'<br /><strong>Marca Vehiculo: </strong>'+data.marcaVehiculo+'<br /> </p>'+
							    		'<p><strong>Modelo Vehiculo: </strong>'+data.modeloVehiculo+'<br /> <strong>Tipo Vehiculo: </strong>'+data.tipoVehiculo+'<br /><strong>Placa: </strong>'+data.placa+'<br /> </p>'+
							    		'<p><strong>Zona: </strong>'+data.zona+'</p>';
				    		
				    	
				    	setTimeout(function () {
				    		
							google.maps.event.addListener(infowindow, 'domready', function () {
								$('#close-info-window-x-' + this.index).click(function () {
									infowindow.close();
								});
							});
							
							infowindow['content'] = $(varhtml).html()+afterHtml;
							
							infowindow.open(MapaIncidencias.map, marker);
						
						}, 200);
				    	
				    }
				});
			});
		
		}else{
			google.maps.event.addListener(marker, 'click', function () {
		
				if(isAnimation){
					marker.setAnimation(null);
				}
				
				infowindow.open(MapaIncidencias.map, marker);
				
			});
		}
			
			
		}
		,
		buildServiceVoxiva:function(){
			
			$.ajax({
		    url: "/mobileApps/mapa-incidencia/voxiva-list",
		    dataType: "json",
		    success: function( response ) {
		    	
		    	$.each(response.data,function(i,o){

		    		var infowindow = new google.maps.InfoWindow({
			    		content: '<div class="detalle-acordion" style="padding: 5px 0px 5px 0px;width:300px;" >' +
						'<div style="width:100%;float:left;font-family:Verdana;color:#d90016;font-size:14px;margin-bottom:5px;"><b>VOXIVA</b></div>' +
						'<div>'+o.descripcion+'</div></div>'
			    	});
		    		
			    	var marker = new google.maps.Marker({
			    		position: new google.maps.LatLng(parseFloat(o.longitud), parseFloat(o.latitud)),
			    		map: MapaIncidencias.map,
			    		draggable: false,
			            mapTypeId: google.maps.MapTypeId.ROADMAP,
			            icon: new google.maps.MarkerImage( '/mobileApps/images/phone_azul.png' , undefined, undefined, undefined, new google.maps.Size(32,36))
			    	});
			    	
			    	google.maps.event.addListener(marker, 'click', function () {
			    		infowindow.open(MapaIncidencias.map, marker);
			    	});
			    	
			    	var objMarker = {
			    			marker : marker
			    			,tipo:'voxiva'
			    	}
			    	
			    	MapaIncidencias.markers.push(objMarker);
			    	MapaIncidencias.markersVoxiva.push(objMarker);
			    	
		    	});
		    	
		    	
		    }});
			
		}
		,
		fillPolylines:function(data,jams) {
			
			var arrPoligono = data;
			var point = null;
			var visible = MapaIncidencias.getVisible('waze');
			
			var arrPoints = [];
			
				var obj;
			
				for (var i = 0; i < arrPoligono.length; i++) {
					point = arrPoligono[i];
					arrPoints.push({lat: point.y, lng: point.x});
				}
			
				
		       var flightPath = new google.maps.Polyline({
		         path: arrPoints,
		         geodesic: true,
		         strokeColor: '#0C6BB5',
		         strokeOpacity: 0.6,
		         strokeWeight: 6,
		         visible:visible
		       });
		
		       MapaIncidencias.polylines.push(flightPath);
		       
		       flightPath.setMap(MapaIncidencias.map);
		       
		       
//		       	   var infowindow = new google.maps.InfoWindow({
//					    content: item.desCalle
//					  });
//			  
//			   google.maps.event.addListener(flightPath, 'click', function(event) {
//				    //infowindow.position = event.latLng;
//				    
////				    infowindow.setContent(toolTip);
//					infowindow.setPosition(event.latLng);
//					infowindow.open(map);
//				 }); 
		       
			
			}
		,getArrayTetra:function(arr){
			
			var tipo,obj;
			var arrTetra = [];
	        for (var i = 0; i < arr.length; i++) {
	        	
	        	obj = new Object();
	        	obj['punto'] = new google.maps.LatLng(arr[i].longitud, arr[i].latitude);
	        	
	        	
	        	//obj['icon'] ='/mobileApps/images/icon_radio_tetra_32.png';
	        	obj['isRadioTetra'] = true;
	        	
	        	var estadoDesc;
	        	
	        	if(arr[i].issi+''=='85324' || arr[i].issi+''=='85344' || arr[i].issi+''=='85319'
	        		|| arr[i].issi+''=='85337' || arr[i].issi+''=='85312' || arr[i].issi+''=='85243' || arr[i].issi+''=='85253' || arr[i].issi+''=='85284'
	        			|| arr[i].issi+''=='85255' || arr[i].issi+''=='85394' || arr[i].issi+''=='85383' || arr[i].issi+''=='85277' || arr[i].issi+''=='85302'
	        			|| arr[i].issi+''=='85310' || arr[i].issi+''=='85308' || arr[i].issi+''=='85268' || arr[i].issi+''=='85201' || arr[i].issi+''=='85169'
	        			|| arr[i].issi+''=='85254' || arr[i].issi+''=='85298' || arr[i].issi+''=='85248' || arr[i].issi+''=='85244' || arr[i].issi+''=='85245'
	        			|| arr[i].issi+''=='85257' || arr[i].issi+''=='85261' || arr[i].issi+''=='85250' || arr[i].issi+''=='85247' || arr[i].issi+''=='85297'
	        			|| arr[i].issi+''=='85274' || arr[i].issi+''=='85258' || arr[i].issi+''=='85246' || arr[i].issi+''=='85249' || arr[i].issi+''=='85251'
	        			|| arr[i].issi+''=='85271' || arr[i].issi+''=='85259' || arr[i].issi+''=='85074' || arr[i].issi+''=='85209' || arr[i].issi+''=='85073'
	        			|| arr[i].issi+''=='85288' || arr[i].issi+''=='85096' || arr[i].issi+''=='85286' || arr[i].issi+''=='85098' || arr[i].issi+''=='85256'
	        			|| arr[i].issi+''=='85174' || arr[i].issi+''=='85104' || arr[i].issi+''=='85180' || arr[i].issi+''=='85252' || arr[i].issi+''=='85093'
	        			|| arr[i].issi+''=='85097' || arr[i].issi+''=='85085' || arr[i].issi+''=='85230' || arr[i].issi+''=='85105' || arr[i].issi+''=='85428'
	        			|| arr[i].issi+''=='85429' || arr[i].issi+''=='85430' || arr[i].issi+''=='85431' || arr[i].issi+''=='85432' || arr[i].issi+''=='85433'
	        			|| arr[i].issi+''=='85434' || arr[i].issi+''=='85435' || arr[i].issi+''=='85436' || arr[i].issi+''=='85437' || arr[i].issi+''=='85438'
	        			|| arr[i].issi+''=='85441' || arr[i].issi+''=='85442' || arr[i].issi+''=='85403' || arr[i].issi+''=='85404'){
	        		
	        		estadoDesc = "TRANSITO";
	        		obj['icon'] ='/mobileApps/images/tetra_amarillo.png';
	        		tipo = 'tetra4';
	        		
	        	}else
	        	
	        	if(arr[i].estado+''=='001' || arr[i].estado+''=='010'){
	        		estadoDesc = "APAGADO";
	        		obj['icon'] ='/mobileApps/images/tetra_negro.png';
	        		tipo = 'tetra2';
	        	}else if(arr[i].estado+''=='00C' || arr[i].estado+''=='0C0'){
	        		estadoDesc = "BATERIA BAJA";
	        		obj['icon'] ='/mobileApps/images/tetra_rojo.png';
	        		tipo = 'tetra3';
	        	}else if(arr[i].estado+''=='00E' || arr[i].estado+''=='0E0'){
	        		estadoDesc = "DESCONECTADO";
	        		obj['icon'] ='/mobileApps/images/tetra_rojo.png';
	        		tipo = 'tetra3';
	        	}else if(arr[i].estado+''=='00F' || arr[i].estado+''=='0F0'){
	        		estadoDesc = "DESCONOCIDO";
	        		obj['icon'] ='/mobileApps/images/tetra_rojo.png';
	        		tipo = 'tetra3';
	        	}else if(arr[i].estado+''=='002' || arr[i].estado+''=='020'){
	        		estadoDesc = "EMERGENCIA";
	        		obj['icon'] ='/mobileApps/images/tetra_rojo.png';
	        		tipo = 'tetra3';
	        	}else if(arr[i].estado+''=='901'){
	        		estadoDesc = "MALA PRESICION";
	        		obj['icon'] ='/mobileApps/images/tetra_rojo.png';
	        		tipo = 'tetra3';
	        	}else if(arr[i].estado+''=='902'){
	        		estadoDesc = "NO REPORTA - MAL APAGADO";
	        		obj['icon'] ='/mobileApps/images/tetra_rojo.png';
	        		tipo = 'tetra3';
	        	}else if(arr[i].estado+''=='000' || arr[i].estado+''=='003' || arr[i].estado+''=='004' || arr[i].estado+''=='005'
	        		|| arr[i].estado+''=='006' || arr[i].estado+''=='007' || arr[i].estado+''=='008' || arr[i].estado+''=='009'
	        		|| arr[i].estado+''=='00B' || arr[i].estado+''=='00D' || arr[i].estado+''=='030' || arr[i].estado+''=='040'
	        		|| arr[i].estado+''=='050' || arr[i].estado+''=='060' || arr[i].estado+''=='070' || arr[i].estado+''=='080' 
	        		|| arr[i].estado+''=='090' || arr[i].estado+''=='0B0' || arr[i].estado+''=='0D0' || arr[i].estado+''=='100' 
	        		|| arr[i].estado+''=='106' || arr[i].estado+''=='110' || arr[i].estado+''=='120' || arr[i].estado+''=='130'
	        		|| arr[i].estado+''=='140' || arr[i].estado+''=='160' || arr[i].estado+''=='200' || arr[i].estado+''=='801'
	        		|| arr[i].estado+''=='802' || arr[i].estado+''=='810' || arr[i].estado+''=='820'){
	        		estadoDesc = "NORMAL";
	        		obj['icon'] ='/mobileApps/images/tetra_verde.png';
	        		tipo = 'tetra1';
	        	}else if(arr[i].estado+''=='105' || arr[i].estado+''=='150' || arr[i].estado+''=='905'){
	        		estadoDesc = "SIN COBERTURA DE GPS";
	        		obj['icon'] ='/mobileApps/images/tetra_rojo.png';
	        		tipo = 'tetra3';
	        	}else if(arr[i].estado+''=='00A' || arr[i].estado+''=='0A0'){
	        		estadoDesc = "SIN SERVICIO";
	        		obj['icon'] ='/mobileApps/images/tetra_rojo.png';
	        		tipo = 'tetra3';
	        	}
	        	
	        	
	        	
	        	
	        	
				obj['html'] = '<div id="detalle'+arr[i].issi+'" style="padding: 5px 0px 5px 0px;width:300px;" >' +
				'<p style="width:100%;float:left;font-family:Verdana;color:#d90016;font-size:14px;margin-bottom:5px;"><b>'+estadoDesc+'</b></p>' +
				'<p><strong>issi: </strong><span class="issi">'+arr[i].issi+'</span><br /> <strong>Serie: </strong>'+arr[i].serie+'<br /><strong>TEI: </strong>'+arr[i].tei+'<br /> </p>'+
				'</div>';
				
				obj['tipoService'] = tipo;
				obj['isRadioTetra'] = true;
				
				arrTetra.push(obj);
				
			}
			
	        return arrTetra;
		}
		,buildTetraService:function(){
			
			var arrTetra = [];
			
			$.ajax({
		    url: "/mobileApps/cierre-calles/dolphin-service",
		    dataType: "json",
		    success: function( response ) {
		    	
		    	$(".mask-loading-main").hide();
		    	
		    	if(response.status){
		    		
			        var arr = response.items;

			        arrTetra = MapaIncidencias.getArrayTetra(arr)
		    
					$.each(arrTetra,function(i,tetra){
						
						MapaIncidencias.addMarker({
							
							icon:tetra.icon,
							index:i+'_tetra',
							html:tetra.html,
							punto:tetra.punto,
							tipo:tetra.tipoService,
							isRadioTetra:true
							
						});
						
					});
		    		
		    	}else{
		    		
		    		console.log("No hay datos que mostrar de -> /mobileApps/cierre-calles/dolphin-service");
		    		
		    	}
		    	
	
		    }
		});
			
		}
		,getArrayWaze:function(arr){
		
			var arrWaze = [];
			
			
			
			for (var i = 0; i < arr.length; i++) {
				
				obj = new Object();
				obj['uuid'] = arr[i].uuid;
				obj['punto'] = new google.maps.LatLng(arr[i].location.y, arr[i].location.x);
	
	
				if(arr[i].subtype == 'ACCIDENT_MINOR'){
						obj['icon'] ='/mobileApps/images/accidente_waze.png';
						obj['html'] = '<div class="detalle-acordion" style="padding: 5px 0px 5px 0px;width:300px;" >' +
						'<div style="width:100%;float:left;font-family:Verdana;color:#d90016;font-size:14px;margin-bottom:5px;"><b>ACCIDENTE LEVE</b></div>' +
						'<div>'+(arr[i].reportDescription?'<p>'+arr[i].reportDescription+'</p>':'')+'<b>Calle:</b> '+(arr[i].street?arr[i].street:'')+'<br /> <b>Distrito:</b> '+(arr[i].city?arr[i].city:'')+'<br /><b>Valoración:</b> '+arr[i].reportRating+'</div></div>';
				}else{
					if(arr[i].subtype == 'ACCIDENT_MINOR'){
						obj['icon'] ='/mobileApps/images/accidente_waze.png';
						obj['html'] = '<div class="detalle-acordion" style="padding: 5px 0px 5px 0px;width:300px;" >' +
						'<div style="width:100%;float:left;font-family:Verdana;color:#d90016;font-size:14px;margin-bottom:5px;"><b>ACCIDENTE GRAVE</b></div>' +
						'<div>'+(arr[i].reportDescription?'<p>'+arr[i].reportDescription+'</p>':'')+'<b>Calle:</b> '+(arr[i].street?arr[i].street:'')+'<br /> <b>Distrito:</b> '+(arr[i].city?arr[i].city:'')+'<br /><b>Valoración:</b> '+arr[i].reportRating+'</div></div>';
					}else{
						if(arr[i].subtype == "JAM_MODERATE_TRAFFIC"){
							obj['icon'] ='/mobileApps/images/moderado_waze.png';
							obj['html'] = '<div class="detalle-acordion" style="padding: 5px 0px 5px 0px;width:300px;" >' +
							'<div style="width:100%;float:left;font-family:Verdana;color:#d90016;font-size:14px;margin-bottom:5px;"><b>EMBOTELLAMIENTO MODERADO</b></div>' +
							'<div>'+(arr[i].reportDescription?'<p>'+arr[i].reportDescription+'</p>':'')+'<b>Calle:</b> '+(arr[i].street?arr[i].street:'')+'<br /> <b>Distrito:</b> '+(arr[i].city?arr[i].city:'')+'<br /><b>Valoración:</b> '+arr[i].reportRating+'</div></div>';
						}else{
						
							if(arr[i].subtype == "JAM_HEAVY_TRAFFIC"){
								obj['icon'] ='/mobileApps/images/grave_waze.png';
								obj['html'] = '<div class="detalle-acordion" style="padding: 5px 0px 5px 0px;width:300px;" >' +
								'<div style="width:100%;float:left;font-family:Verdana;color:#d90016;font-size:14px;margin-bottom:5px;"><b>EMBOTELLAMIENTO GRAVE</b></div>' +
								'<div>'+(arr[i].reportDescription?'<p>'+arr[i].reportDescription+'</p>':'')+'<b>Calle:</b> '+(arr[i].street?arr[i].street:'')+'<br /> <b>Distrito:</b> '+(arr[i].city?arr[i].city:'')+'<br /><b>Valoración:</b> '+arr[i].reportRating+'</div></div>';
							}else{
								if(arr[i].subtype+""=="JAM_STAND_STILL_TRAFFIC"){
									obj['icon'] ='/mobileApps/images/paro_total.png';
									obj['html'] = '<div class="detalle-acordion" style="padding: 5px 0px 5px 0px;width:300px;" >' +
									'<div style="width:100%;float:left;font-family:Verdana;color:#d90016;font-size:14px;margin-bottom:5px;"><b>EMBOTELLAMIENTO ALTO TOTAL</b></div>' +
									'<div>'+(arr[i].reportDescription?'<p>'+arr[i].reportDescription+'</p>':'')+'<b>Calle:</b> '+(arr[i].street?arr[i].street:'')+'<br /> <b>Distrito:</b> '+(arr[i].city?arr[i].city:'')+'<br /><b>Valoración:</b> '+arr[i].reportRating+'</div></div>';
								}else{
									if(arr[i].subtype+""=="JAM_LIGHT_TRAFFIC"){
										obj['icon'] ='/mobileApps/images/paro_total.png';
										obj['html'] = '<div class="detalle-acordion" style="padding: 5px 0px 5px 0px;width:300px;" >' +
										'<div style="width:100%;float:left;font-family:Verdana;color:#d90016;font-size:14px;margin-bottom:5px;"><b>EMBOTELLAMIENTO</b></div>' +
										'<div>'+(arr[i].reportDescription?'<p>'+arr[i].reportDescription+'</p>':'')+'<b>Calle:</b> '+(arr[i].street?arr[i].street:'')+'<br /> <b>Distrito:</b> '+(arr[i].city?arr[i].city:'')+'<br /><b>Valoración:</b> '+arr[i].reportRating+'</div></div>';
									}else{
										
										if(arr[i].type+""=="CONSTRUCTION"){
												obj['icon'] ='/mobileApps/images/construccion_waze.png';
												obj['html'] = '<div class="detalle-acordion" style="padding: 5px 0px 5px 0px;width:300px;" >' +
												'<div style="width:100%;float:left;font-family:Verdana;color:#d90016;font-size:14px;margin-bottom:5px;"><b>CONSTRUCCIÓN</b></div>' +
												'<div>'+(arr[i].reportDescription?'<p>'+arr[i].reportDescription+'</p>':'')+'<b>Calle:</b> '+(arr[i].street?arr[i].street:'')+'<br /> <b>Distrito:</b> '+(arr[i].city?arr[i].city:'')+'<br /><b>Valoración:</b> '+arr[i].reportRating+'</div></div>';
											}
											else
											{
												if(arr[i].type+""=="ROAD_CLOSED"){
					
														var titulo = '';

														switch(arr[i].subtype){
															
															case 'ROAD_CLOSED_HAZARD':
																titulo = 'Via Cerrada: Peligro';
															break;
															
															case 'ROAD_CLOSED_CONSTRUCTION':
																titulo = 'Via Cerrada: Construcción';
															break;
															
															case 'ROAD_CLOSED_EVENT':
																titulo = 'Via Cerrada: Evento';
															break;
															
															default:
																titulo = 'Via Cerrada';
															break;
															
														}
														
														titulo = titulo.toUpperCase();
													
														obj['icon'] ='/mobileApps/images/calle_cerrada_waze.png';
														obj['html'] = '<div class="detalle-acordion" style="padding: 5px 0px 5px 0px;width:300px;" >' +
														'<div style="width:100%;float:left;font-family:Verdana;color:#d90016;font-size:14px;margin-bottom:5px;"><b>'+titulo+'</b></div>' +
														'<div>'+(arr[i].reportDescription?'<p>'+arr[i].reportDescription+'</p>':'')+'<b>Calle:</b> '+(arr[i].street?arr[i].street:'')+'<br /> <b>Distrito:</b> '+(arr[i].city?arr[i].city:'')+'<br /><b>Valoración:</b> '+arr[i].reportRating+'</div></div>';
														
													}else{
													
														if(arr[i].type+""=="HAZARD" ||  arr[i].type+""=="WEATHERHAZARD" ){
					
															var titulo = '';

															obj['icon'] ='/mobileApps/images/peligro_waze.png';
															
															switch(arr[i].subtype){
																
																case 'HAZARD_ON_ROAD':
																	titulo = 'Peligro vial';
																break;
																
																case 'HAZARD_ON_SHOULDER':
																	titulo = 'Peligro en la berma';
																break;
																
																case 'HAZARD_WEATHER':
																	titulo = 'Peligro de clima';
																break;
																
																case 'HAZARD_ON_ROAD_OBJECT':
																	titulo = 'Objetos en la vía';
																break;
																
																case 'HAZARD_ON_ROAD_POT_HOLE':
																	titulo = 'Bache';
																break;
																	
																case 'HAZARD_ON_ROAD_ROAD_KILL':
																	titulo = 'Peligro de atropellamientos';
																break;
																
																case 'HAZARD_ON_SHOULDER_CAR_STOPPED':
																	titulo = 'Vehículo detenido a un lado de la vía';
																break;
																
																case 'HAZARD_ON_SHOULDER_ANIMALS':
																	titulo = 'Peligro de animales en la berma';
																break;
																
																case 'HAZARD_ON_SHOULDER_MISSING_SIGN':
																	titulo = 'Peligro de falta de señal en la berma';
																break;
																
																case 'HAZARD_WEATHER_FOG':
																	titulo = 'Peligro de niebla';
																break;
																
																case 'HAZARD WEATHER_HAIL':
																	titulo = 'Peligro de granizo';
																break;
																
																case 'HAZARD_WEATHER_HEAVY_RAIN':
																	titulo = 'Peligro de  fuertes lluvias';
																break;
																
																case 'HAZARD_WEATHER_HEAVY_SNOW':
																	titulo = 'Peligro de fuertes nevadas';
																break;
																
																case 'HAZARD_WEATHER_FLOOD':
																	titulo = 'Peligro de inundaciones';
																break;
																
																case 'HAZARD_WEATHER_MONSOON':
																	titulo = 'Peligro de vientos fuertes';
																break;
//															
																case 'HAZARD_WEATHER_TORNADO':
																	titulo = 'Peligro de tornado ';
																break;
																
																case 'HAZARD_WEATHER_HEAT_WAVE':
																	titulo = 'Peligro de ola de calor';
																break;
																
																case 'HAZARD_WEATHER_HURRICANE':
																	titulo = 'Peligro de huracán';
																break;
																
																case 'HAZARD_WEATHER_FREESING_RAIN':
																	titulo = 'Peligro de lluvias';
																break;
																
																case 'HAZARD_ON_ROAD_LANE_CLOSED':
																	titulo = 'Peligro en la vía: carriles cerrados';
																break;
																
																case 'HAZARD_ON_ROAD_OIL':
																	titulo = 'Peligro de aceite en la vía';
																break;
																
																case 'HAZARD_ON_ROAD_ICE':
																	titulo = 'Peligro de hielo en la vía';
																break;
																
																case 'HAZARD_ON_ROAD_CONSTRUCTION':
																obj['icon'] ='/mobileApps/images/construccion_waze.png';
																	titulo = 'construcción';
																break;
																
																case 'HAZARD_ON_ROAD_CAR_STOPPED':
																	titulo = 'Automóvil detenido sobre el camino';
																break;
																
																default:
																	titulo = 'Peligro vial';
																break;
																
															}
														
															titulo = titulo.toUpperCase();
															
															
															obj['html'] = '<div class="detalle-acordion" style="padding: 5px 0px 5px 0px;width:300px;" >' +
															'<div style="width:100%;float:left;font-family:Verdana;color:#d90016;font-size:14px;margin-bottom:5px;"><b>'+titulo+'</b></div>' +
															'<div>'+(arr[i].reportDescription?'<p>'+arr[i].reportDescription+'</p>':'')+'<b>Calle:</b> '+(arr[i].street?arr[i].street:'')+'<br /> <b>Distrito:</b> '+(arr[i].city?arr[i].city:'')+'<br /><b>Valoración:</b> '+arr[i].reportRating+'</div></div>';
															
														}else{
														
															if(arr[i].type == 'JAM'){
															obj['icon'] ='/mobileApps/images/moderado_waze.png';
															obj['html'] = '<div class="detalle-acordion" style="padding: 5px 0px 5px 0px;width:300px;" >' +
															'<div style="width:100%;float:left;font-family:Verdana;color:#d90016;font-size:14px;margin-bottom:5px;"><b>EMBOTELLAMIENTO</b></div>' +
															'<div>'+(arr[i].reportDescription?'<p>'+arr[i].reportDescription+'</p>':'')+'<b>Calle:</b> '+(arr[i].street?arr[i].street:'')+'<br /> <b>Distrito:</b> '+(arr[i].city?arr[i].city:'')+'<br /><b>Valoración:</b> '+arr[i].reportRating+'</div></div>';
															}else{
																
																if(arr[i].type.indexOf('ACCIDENT')>=0){
																	
																	obj['icon'] ='/mobileApps/images/accidente_waze.png';
																	obj['html'] = '<div class="detalle-acordion" style="padding: 5px 0px 5px 0px;width:300px;" >' +
																	'<div style="width:100%;float:left;font-family:Verdana;color:#d90016;font-size:14px;margin-bottom:5px;"><b>ACCIDENTE</b></div>' +
																	'<div>'+(arr[i].reportDescription?'<p>'+arr[i].reportDescription+'</p>':'')+'<b>Calle:</b> '+(arr[i].street?arr[i].street:'')+'<br /> <b>Distrito:</b> '+(arr[i].city?arr[i].city:'')+'<br /><b>Valoración:</b> '+arr[i].reportRating+'</div></div>';
																	
																}else{
																	
																	console.log('Tipo no mapeado: '+arr[i].type)
																	console.log('Sub tipo no mapeado: '+arr[i].subtype)
																	
																	console.log(arr[i])
																	
																    obj['icon'] ='/mobileApps/images/defecto_waze.png';
																    obj['html'] = '<div class="detalle-acordion" style="padding: 5px 0px 5px 0px;width:300px;" >' +
																   '<div style="width:100%;float:left;font-family:Verdana;color:#d90016;font-size:14px;margin-bottom:5px;"><b>INFORMACIÓN DE USUARIOS WAZE</b></div>' +
																   '<div>'+(arr[i].reportDescription?'<p>'+arr[i].reportDescription+'</p>':'')+'<b>Calle:</b> '+(arr[i].street?arr[i].street:'')+'<br /> <b>Distrito:</b> '+(arr[i].city?arr[i].city:'')+'<br /><b>Valoración:</b> '+arr[i].reportRating+'</div></div>';
																	
																}
																
															}
															
															
														}
														
													}
												
											}
										
									}
							}
							
						}
					
					}
				}
				
				}
				
				obj['tipoService'] = 'waze';
				obj['isRadioTetra'] = false;
				
				arrWaze.push(obj);
				
			}
			
			return arrWaze;
		}
		,buildWazeService:function(){
			
			var arrWaze = [];
			
			$.ajax({
		    url: "/mobileApps/cierre-calles/waze-service",
		    dataType: "json",
		    success: function( response ) {
		    	$(".mask-loading-main").hide();
		        
		    	var obj;
		        
		        var arr = response.alerts;

		        arrWaze = MapaIncidencias.getArrayWaze(arr);
				
				$.each(arrWaze,function(i,waze){
					
					MapaIncidencias.addMarker({
						
						icon:waze.icon,
						index:i+'_waze',
						html:waze.html,
						punto:waze.punto,
						tipo:'waze',
						isRadioTetra:false,
						isFirstCall:true,
						uuid:waze.uuid
						
					})
					
				});
				
				if(response.jams){
					for (var i = 0; i < response.jams.length; i++) {
						 
						MapaIncidencias.fillPolylines(response.jams[i].line,response.jams[i]);
						 
					 }
				}
				 
		    }
		});
			
		}
		,
		refreshWaze:function(){
			
			setInterval(function(){
				if(!procesandoWaze){
					
					procesandoWaze = true;
					
					var arrWaze = [];
					
					$.ajax({
				    url: "/mobileApps/cierre-calles/waze-service",
				    dataType: "json",
				    success: function( response ) {
				    	console.log('otra vez->'+(new Date()))
				    	var obj;
				        var c =0;
				        var arr = response.alerts;
				        
				      // arrTempUUID = [];
				        
				        arrWaze = MapaIncidencias.getArrayWaze(arr);
				        
//				        $.each(arrWaze,function(i,waze){
//				        	arrTempUUID.push(waze.uuid);
//				        });
//				        
//				        function isPresetTemp(v){
//				        	//arrTempUUID	
//				        	var e = false;
//				        	for(var i = 0 ; i < arrTempUUID.length ; i++){
//					    		if(v==arrTempUUID[i]){
//					    			e = true;
//					    			break;
//					    		}
//					    	}
//				        	return e;
//				        }
//				        
//				        for(var j = 0 ; j < arrWazeExistentes.length ; j++){
//				    		if(!isPresetTemp(arrWazeExistentes[j].key)){
//				    			console.log('key a eleiminar->'+arrWazeExistentes[j].key)
//				    			deleteUUID(arrWazeExistentes[j].key);
//				    		}
//				    	}
				       
				        
						$.each(MapaIncidencias.markersWaze,function(a,b){
							if(b){
								b.marker.setMap(null);
							}
						});

						MapaIncidencias.markersWaze = [];
						
						$.each(MapaIncidencias.markers,function(r,q){
							
							if(q && q.tipo == 'waze'){
								MapaIncidencias.markers.splice(q,1);
							}
							
						});
				      
						
				        $.each(arrWaze,function(i,waze){
								        	
							MapaIncidencias.addMarker({
								
								icon:waze.icon,
								index:i+'_waze',
								html:waze.html,
								punto:waze.punto,
								tipo:'waze',
								isRadioTetra:false,
								uuid:waze.uuid
								
							})
							
						});
				        
				        $.each(MapaIncidencias.polylines,function(s,t){
				        	t.setMap(null);
				        });
				        
				        MapaIncidencias.polylines = [];
						
						if(response.jams){
							for (var i = 0; i < response.jams.length; i++) {
								 
								MapaIncidencias.fillPolylines(response.jams[i].line,response.jams[i]);
								 
							 }
						}
						
						procesandoWaze = false;
						 
				    }
				});
					

					
				}
		
				
			}, 30000)
			
			
		}
		,
		refreshTetra:function(){
			
			setInterval(function(){
				
				if(!procesandoTetra){
					
					procesandoTetra = true;
					
					$.ajax({
				    url: "/mobileApps/cierre-calles/dolphin-service",
				    dataType: "json",
				    success: function( response ) {
				    	
				        var arr = response.items;
				        
				        var arrTetra = [];
				        
						$.each(MapaIncidencias.markersTetra,function(a,b){
							if(b){
								b.marker.setMap(null);
							}
						});
				        
						MapaIncidencias.markersTetra = [];
						
						$.each(MapaIncidencias.markers,function(r,q){
							
							if(q && q.tipo.indexOf('tetra')){
								MapaIncidencias.markers.splice(q,1);
							}
							
						});
												
				        arrTetra = MapaIncidencias.getArrayTetra(arr);
					    
								$.each(arrTetra,function(i,tetra){
									
									MapaIncidencias.addMarker({
										
										icon:tetra.icon,
										index:i+'_tetra',
										html:tetra.html,
										punto:tetra.punto,
										tipo:tetra.tipoService,
										isRadioTetra:true
										
									});
									
								});
				        
				        procesandoTetra = false;
						 
				    }
				});
									
				}
				
			}, 30000)
			
		}
		,refreshVoxiva:function(){
			
			setInterval(function(){
			
						if(!procesandoVoxiva){
				procesandoVoxiva = true;
				
				$.ajax({
			    url: "/mobileApps/mapa-incidencia/voxiva-list",
			    dataType: "json",
			    success: function( response ) {
			    	
			    	
			    	$.each(MapaIncidencias.markersVoxiva,function(i,v){
			    		try{
			    			v.marker.setMap(null);
			    		}catch(e){
			    			
			    		}
			    	});
			    	
			    	MapaIncidencias.markersVoxiva = [];
			    	
			    	$.each(MapaIncidencias.markers,function(a,b){
			    	
			    		if(b && b.marker.tipo == 'voxiva'){
			    			MapaIncidencias.markers.splice(b,1);
			    		}
			    		
			    	});
			    	
			    	$.each(response.data,function(i,o){
			    		
			    		var infowindow = new google.maps.InfoWindow({
				    		content: '<div class="detalle-acordion" style="padding: 5px 0px 5px 0px;width:300px;" >' +
							'<div style="width:100%;float:left;font-family:Verdana;color:#d90016;font-size:14px;margin-bottom:5px;"><b>VOXIVA</b></div>' +
							'<div>'+o.descripcion+'</div></div>'
				    	});
			    		
				    	var marker = new google.maps.Marker({
				    		position: new google.maps.LatLng(parseFloat(o.longitud), parseFloat(o.latitud)),
				    		map: MapaIncidencias.map,
				    		draggable: false,
				    		visible:MapaIncidencias.getVisible('voxiva'),
				            mapTypeId: google.maps.MapTypeId.ROADMAP,
				            icon: new google.maps.MarkerImage( '/mobileApps/images/phone_azul.png' , undefined, undefined, undefined, new google.maps.Size(32,36))
				    	});
				    	
				    	google.maps.event.addListener(marker, 'click', function () {
				    		infowindow.open(MapaIncidencias.map, marker);
				    	});
				    	
				    	var objMarker = {
				    			marker : marker
				    			,tipo:'voxiva'
				    	}
				    	
				    	MapaIncidencias.markers.push(objMarker);
				    	MapaIncidencias.markersVoxiva.push(objMarker);
				    	
			    	});
			    	
			    	procesandoVoxiva = false;
			    }});
				
			}
				
			}, 30000)	
			
		
		}
		
		
		
}

window.onload = function(){
	MapaIncidencias.init();
}



