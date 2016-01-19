var Util = new Object();
Util = {
		UiAlerta:function(config){
			
			var idWinAlerta=config.idWindowAlerta || Ext.id();
			
			var buttons=config.buttons || new Array();
			
			if(!config.buttons){
			
				buttons.push({
								text : 'Aceptar',
								scale : 'medium',
								iconCls : config.iconCls || 'btnAprobedMedium',
								handler : function() {
									Ext.getCmp(idWinAlerta).close();
									
									if (config.fn_execute) {
										config.fn_execute();
									}
	
								}
	
							});
							
				if(config.isConfirm){
				
					buttons.push({
								text : 'Cancelar',
								scale : 'medium',
								iconCls : config.iconClsCancelar || 'btnCancelMedium',
								handler : function() {
									Ext.getCmp(idWinAlerta).close();
									if(config.fn_cancel){
									config.fn_cancel();
									}
								}
	
							})
				
				}
			
			}
			
		Ext.create('Ext.window.Window', {
			id:idWinAlerta,
			closable : false,
			modal:true,
			width : config.width || 400,
			y : (config.y?config.y:undefined),
			draggable : false,
			resizable : false,
			listeners : {

				afterrender : function(thisWin) {
				
				$(window).scroll(function() {
					
					try {
						var arrPos = thisWin.getPosition();
						thisWin.setPosition(arrPos[0], (config.marginTop?$(window).scrollTop()+config.marginTop:$(window).scrollTop()+ thisWin.getHeight()+100)  );
					} catch (e) {

					}					

				});
				
				}
				,
				show:function( this_win ){}

			},
			border : false,
			style : 'background:#fff;',
			bodyStyle : 'background:#fff;padding:5px 10px 5px 10px;',
			layout : 'fit',
			items : {
				xtype : 'panel',
				border : false,
				style : 'background:#fff;',
				bodyStyle : 'background:#fff;padding:5px 3px 2px 3px;',
				html : '<div style="color:#333;text-align:center;margin-bottom:0px;padding:10px 5px 5px 5px;">' +
							'<span style="font-size:13px;color:#666;"><b>'
							+ config.mensaje + '' +
							'</b></span>' +
						'</div> ' + '',
				buttons : buttons
			}
		}).show();
			
		}
		,
		runAjax : function(config) {
		
			var ret;
			if (config.msg) {
				Ext.MessageBox.wait('Espere un momento porfavor...');
			}
			Ext.Ajax.request({
						url : config.url,
						async : config.async,
						timeout:config.timeout || 500000000,
						headers : {
							Accept : 'application/json, text/javascript, */*; q=0.01'
						},
						method : config.method,
						callback : config.callback
								|| function(options, success, response) {
									if (config.msg) {
										Ext.MessageBox.updateProgress(1);
										Ext.MessageBox.hide();
									}
									ret = Ext.decode(response.responseText);
								},
						listeners : {
							beforerequest : config.beforerequest
						},
						success : config.success || function(response) {
							ret = Ext.decode(response.responseText);
		
							if (ret.success
									&& (ret.message != '' && ret.message != null)) {
		
								if (config.showAlert) {
									alert(ret.message);
								}
							} else if (!ret.success) {
		
								if (ret.message) {
									if (config.showAlert) {
										alert(ret.message);
									}
								}
							}
						},
						failure : config.failure || function(response) {
							//alert(Util.Ajax.getAlerta(MSG_REGISTRE_CORRECTAMENTE));
						},
						params : config.params,
						jsonData : config.jsonParam
					});
			return ret;
		
		}
}
