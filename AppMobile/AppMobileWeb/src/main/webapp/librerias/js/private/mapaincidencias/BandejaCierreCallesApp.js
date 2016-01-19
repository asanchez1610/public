Ext.Loader.setConfig({
			enabled : true
		});

Ext.application({
	name : 'MOBILEAPP',
	autoCreateViewport : false,
	appFolder : '/mobileApps/js/private/mapaincidencias/',
	controllers : ['CierreCalleController'],
	launch : function() {
		
		console.log('app start controller!');
		
		var height=($(window).height()-110);
		
        Ext.widget('cierreCalleGrid', {
											id:'grid-cierre-calles',
								   		 	height: height,
										    width:$('.bandeja-container-sesion').width(),
										    style:'border:1px #e1e1e1 solid;',
										    renderTo: 'content-grid',
											    extraParams:{
											    	tipoIncidencia:8766
											    }
											}
									);     
        
        $(window).resize(function(){
        	if(Ext.getCmp('grid-auditoria')){
        		Ext.getCmp('grid-auditoria').setWidth($('.bandeja-container-sesion').width());
        	}
        });
				
		
	}
});