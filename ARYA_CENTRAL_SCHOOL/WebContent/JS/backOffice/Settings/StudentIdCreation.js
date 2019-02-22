var tempTemplateName=null;
var imgUrl='noImage';
var laybackground="front";
$(document).ready(function () {
	
	$("#idfront").click(function(){
		$(".main-div.idBack").hide();
		$(".main-div.idFront").show();
	});
	$("#idback").click(function(){
		$(".main-div.idFront").hide();
		$(".main-div.idBack").show();
		
	});
	getStudentIdDesignList();	
	$("#idCardDesignListDropDown").val($("#layoutDetails").val()+".css");
	boderFlag=false;
	$("#boxCorner0").click(function(){
		bordercss="#"+divId+"{border-radius:0px}";
		boderFlag=true;
		
	});
	$("#boxCorner2").click(function(){
		bordercss="#"+divId+"{border-radius:2px}";
		boderFlag=true;
	});
	$("#boxCorner4").click(function(){
		bordercss="#"+divId+"{border-radius:4px}";
		boderFlag=true;
	});
	$("#boxCorner6").click(function(){
		bordercss="#"+divId+"{border-radius:6px}";
		boderFlag=true;
	});
	$("#boxCorner8").click(function(){
		 bordercss="#"+divId+"{border-radius:8px}";
		 boderFlag=true;
	});
	var qrcode = new QRCode("qrdetails");
	var qrcode1=new QRCode("qrdetails-bck");
	qrcode.makeCode("Name:"+$("#nametext").text()+" Class:"+$("#classtext").text());
	qrcode1.makeCode("Name:"+$("#nametext").text()+" Class:"+$("#classtext").text());
	$("#hfontval").append("<option value='0px'>-</option>" +
			"<option value='10px'>10</option>" +
			"<option value='12px'>12</option>" +
			"<option value='14px'>14</option>" +
			"<option value='16px'>16</option>" +
			"<option value='18px'>18</option>" +
			"<option value='20px'>20</option>" +
			"<option value='22px'>22</option>" +
			"<option value='24px'>24</option>" +
			"<option value='26px'>26</option>" +
			"<option value='28px'>28</option>" +
			"<option value='30px'>30</option>");
	$("#lfontval").append("<option value='0px'>-</option>" +
			"<option value='6px'>6</option>" +
			"<option value='7px'>7</option>" +
			"<option value='8px'>8</option>" +
			"<option value='9px'>9</option>" +
			"<option value='10px'>10</option>" +
			"<option value='11px'>11</option>" +
			"<option value='12px'>12</option>" +
			"<option value='13px'>13</option>" +
			"<option value='14px'>14</option>" +
			"<option value='15px'>15</option>" +
			"<option value='16px'>16</option>" +
			"<option value='17px'>17</option>" +
			"<option value='18px'>18</option>");
	
	
	$(".main-div").resizable();
	$(".main-div div[id]").draggable().resizable();

	
	$("#schoolName").dblclick(function(){
		colorChange($(this));
	});
	
	
		$("#saveChanges").click(function(){
			var cssgroup=[];
			$(".section div[id]").each(function(){
				if($(this).attr("style[width]")!=undefined){
					var css="."+$(this).attr("class").split(" ")[0]+"{"
					+$(this).attr("style")+"}";
					cssgroup.push(css);
					var css1="#"+$(this).attr("id")+"{background-color:"+BackgroundColor+"}";
					cssgroup.push(css1);

				}else{
					var carryCss1="";
				
					var carryCss="."+$(this).attr("class").split(" ")[0]+"{"
					+"left:"+$("."+$(this).attr("class").split(" ")[0]).css("left")+";top:"
					+$("."+$(this).attr("class").split(" ")[0]).css("top")+";" +
					"width:"+$("."+$(this).attr("class").split(" ")[0]).css("width")+";" +
					"height:"+$("."+$(this).attr("class").split(" ")[0]).css("height")+";" +
					"}";	
					cssgroup.push(carryCss);
					if($("#"+$(this).attr("id")).css('background-color')=="transparent" || $("#"+$(this).attr("id")).css('background-color')=="rgba(0, 0, 0, 0)"){
						carryCss1="#"+$(this).attr("id")+"{background-color:transparent}";
					}
					else{
						carryCss1="#"+$(this).attr("id")+"{background-color:"+rgb2hex($("#"+$(this).attr("id")).css('background-color'))+"}";
					}
					cssgroup.push(carryCss1);
					
					
					
				
				}
				
				if($(this).attr("style[border-color]")!=undefined){
					var css2="#"+$(this).attr("id")+"{border-color:"+BorderColor+"}";
					cssgroup.push(css2);
				}
				else{
					var carryCss2="";
					if($("#"+$(this).attr("id")).css('border-color')=="transparent" || $("#"+$(this).attr("id")).css('border-color')=="rgba(0, 0, 0, 0)"){
						carryCss2="#"+$(this).attr("id")+"{border-color:transparent}";
					}
					else{
						carryCss2="#"+$(this).attr("id")+"{border-color:"+rgb2hex($("#"+$(this).attr("id")).css('border-color'))+"}";
					} 
					cssgroup.push(carryCss2);
				}
			
			if(boderFlag){
				cssgroup.push(bordercss);
				var borderCarryCss="#"+$(this).not("#"+divId).attr("id")+"{border-radius:"+$("#"+$(this).attr("id")).css('border-radius')+"}";
				cssgroup.push(borderCarryCss);
			}
			else{
				var borderCarryCss="#"+$(this).attr("id")+"{border-radius:"+$("#"+$(this).attr("id")).css('border-radius')+"}";
				cssgroup.push(borderCarryCss);
			}
			});
			$(".section span[id]").each(function(){
				
				if($(this).attr("style[color]")!=undefined){
					
					var css="#"+$(this).attr("id")+"{color:"+routeColor+"}";
				cssgroup.push(css);
				}
				else{
					
				var carryCss="#"+$(this).attr("id")+"{color:"+rgb2hex($(this).css('color'))+"}";
				
				cssgroup.push(carryCss);
				
			
				}
				if($(this).attr("style[font-size]")!=undefined){
					var css5="#"+$(this).attr("id")+"{font-size:"+$('#lfontval').val()+"}";
					cssgroup.push(css5);
			}
			else{
				var carryCss5="#"+$(this).attr("id")+"{font-size:"+$(this).css('font-size')+"}";
				cssgroup.push(carryCss5);
			}
				
				if($(this).attr("style[background-color]")!=undefined){
					
					
					var css1="#"+$(this).attr("id")+"{background-color:"+labelbackgroundColor+"}";
					cssgroup.push(css1);
				
			}
			else{
				var carryCss1="";
				if($("#"+$(this).attr("id")).css('background-color')=="transparent" || $("#"+$(this).attr("id")).css('background-color')=="rgba(0, 0, 0, 0)"){
					carryCss1="#"+$(this).attr("id")+"{background-color:transparent}";
				}
				else{
					carryCss1="#"+$(this).attr("id")+"{background-color:"+rgb2hex($("#"+$(this).attr("id")).css('background-color'))+"}";
				}
				
				cssgroup.push(carryCss1);
			}
				
				});
			
			
			if($('#hfontval').val()!="0px"){
				var css4="#schoolNameChange{font-size:"+$('#hfontval').val()+"}";
				cssgroup.push(css4);
			}
			else{
				var carryCss4="#schoolNameChange{font-size:"+$("#schoolNameChange").css('font-size')+"}";
				cssgroup.push(carryCss4);
			}
			var mainCss=cssgroup.toString().replace(/,/g,"");
			 $.ajax({
				 type: 'POST', 
				  url: "adminMenu.html?method=IdDesign",
		          data:{"mainCss":mainCss,"layout":$("#layoutDetails").val(),"imgUrl":imgUrl},
		         
				  success: function(response) {
					  
					  var result = $.parseJSON(response);
						
				  }
				
			});
			
			$(".successmessagediv").show();
			$(".successmessagediv span").text("Save Position progressing...");
			setTimeout(function(){
			location.reload();
			},2000);
			});
		
		
		
	
		
		
		$(".main-div span").bind("contextmenu",function(e){
			  e.preventDefault();
			  thispointer=$(this);
			  thisId=thispointer.attr("id");
			  $(".cntr").css("right",0);
			  $(".cntr").css("top",0);
			 // $("#cntnr").hide(100);        
			  $(".cntr").fadeIn(200,startFocusOut());      
			});

			function startFocusOut(){
			  $(document).on("click",function(){
			  $(".cntr").hide();        
			  $(document).off("click");
			  });
			}
			
			$("#items > .changeColor").spectrum({
				    color: "#ECC",
				    showInput: true,
				    className: "full-spectrum",
				    showInitial: true,
				    showPalette: true,
				    showSelectionPalette: true,
				    maxSelectionSize: 10,
				    preferredFormat: "hex",
				    localStorageKey: "spectrum.demo",
				    move: function (color) {
				        
				    },
				    show: function () {
				    
				    },
				    beforeShow: function () {
				    
				    },
				    hide: function () {
				    
				    },
				    change: function(color) {
				    	 $("#basic-log").text("change called: " + color.toHexString());
				    	 document.getElementById(thisId).style.color = color.toHexString(); 
				         routeColor=color.toHexString();
				    },
				    palette: [
				        ["rgb(0, 0, 0)", "rgb(67, 67, 67)", "rgb(102, 102, 102)",
				        "rgb(204, 204, 204)", "rgb(217, 217, 217)","rgb(255, 255, 255)"],
				        ["rgb(152, 0, 0)", "rgb(255, 0, 0)", "rgb(255, 153, 0)", "rgb(255, 255, 0)", "rgb(0, 255, 0)",
				        "rgb(0, 255, 255)", "rgb(74, 134, 232)", "rgb(0, 0, 255)", "rgb(153, 0, 255)", "rgb(255, 0, 255)"], 
				        ["rgb(230, 184, 175)", "rgb(244, 204, 204)", "rgb(252, 229, 205)", "rgb(255, 242, 204)", "rgb(217, 234, 211)", 
				        "rgb(208, 224, 227)", "rgb(201, 218, 248)", "rgb(207, 226, 243)", "rgb(217, 210, 233)", "rgb(234, 209, 220)", 
				        "rgb(221, 126, 107)", "rgb(234, 153, 153)", "rgb(249, 203, 156)", "rgb(255, 229, 153)", "rgb(182, 215, 168)", 
				        "rgb(162, 196, 201)", "rgb(164, 194, 244)", "rgb(159, 197, 232)", "rgb(180, 167, 214)", "rgb(213, 166, 189)", 
				        "rgb(204, 65, 37)", "rgb(224, 102, 102)", "rgb(246, 178, 107)", "rgb(255, 217, 102)", "rgb(147, 196, 125)", 
				        "rgb(118, 165, 175)", "rgb(109, 158, 235)", "rgb(111, 168, 220)", "rgb(142, 124, 195)", "rgb(194, 123, 160)",
				        "rgb(166, 28, 0)", "rgb(204, 0, 0)", "rgb(230, 145, 56)", "rgb(241, 194, 50)", "rgb(106, 168, 79)",
				        "rgb(69, 129, 142)", "rgb(60, 120, 216)", "rgb(61, 133, 198)", "rgb(103, 78, 167)", "rgb(166, 77, 121)",
				        "rgb(91, 15, 0)", "rgb(102, 0, 0)", "rgb(120, 63, 4)", "rgb(127, 96, 0)", "rgb(39, 78, 19)", 
				        "rgb(12, 52, 61)", "rgb(28, 69, 135)", "rgb(7, 55, 99)", "rgb(32, 18, 77)", "rgb(76, 17, 48)","rgba(255, 255, 255,0.1)"]
				    ]
				});
			
			
			$("#items > .labelBackgroundColor").spectrum({
			    color: "#ECC",
			    showInput: true,
			    className: "full-spectrum",
			    showInitial: true,
			    showPalette: true,
			    showSelectionPalette: true,
			    maxSelectionSize: 10,
			    preferredFormat: "hex",
			    localStorageKey: "spectrum.demo",
			    move: function (color) {
			        
			    },
			    show: function () {
			    
			    },
			    beforeShow: function () {
			    
			    },
			    hide: function () {
			    
			    },
			    change: function(color) {
			    	 $("#basic-log").text("change called: " + color.toHexString());
			    	$(".label").css({'background-color':color.toHexString()});
			    	$("#addresslebel").css({'background-color':color.toHexString()});
			    	$("#phonelebel").css({'background-color':color.toHexString()});
			    	$("#addresslebel-bck").css({'background-color':color.toHexString()});
			    	$("#phonelebel-bck").css({'background-color':color.toHexString()});
			    	 labelbackgroundColor=color.toHexString();
			    },
			    palette: [
			        ["rgb(0, 0, 0)", "rgb(67, 67, 67)", "rgb(102, 102, 102)",
			        "rgb(204, 204, 204)", "rgb(217, 217, 217)","rgb(255, 255, 255)"],
			        ["rgb(152, 0, 0)", "rgb(255, 0, 0)", "rgb(255, 153, 0)", "rgb(255, 255, 0)", "rgb(0, 255, 0)",
			        "rgb(0, 255, 255)", "rgb(74, 134, 232)", "rgb(0, 0, 255)", "rgb(153, 0, 255)", "rgb(255, 0, 255)"], 
			        ["rgb(230, 184, 175)", "rgb(244, 204, 204)", "rgb(252, 229, 205)", "rgb(255, 242, 204)", "rgb(217, 234, 211)", 
			        "rgb(208, 224, 227)", "rgb(201, 218, 248)", "rgb(207, 226, 243)", "rgb(217, 210, 233)", "rgb(234, 209, 220)", 
			        "rgb(221, 126, 107)", "rgb(234, 153, 153)", "rgb(249, 203, 156)", "rgb(255, 229, 153)", "rgb(182, 215, 168)", 
			        "rgb(162, 196, 201)", "rgb(164, 194, 244)", "rgb(159, 197, 232)", "rgb(180, 167, 214)", "rgb(213, 166, 189)", 
			        "rgb(204, 65, 37)", "rgb(224, 102, 102)", "rgb(246, 178, 107)", "rgb(255, 217, 102)", "rgb(147, 196, 125)", 
			        "rgb(118, 165, 175)", "rgb(109, 158, 235)", "rgb(111, 168, 220)", "rgb(142, 124, 195)", "rgb(194, 123, 160)",
			        "rgb(166, 28, 0)", "rgb(204, 0, 0)", "rgb(230, 145, 56)", "rgb(241, 194, 50)", "rgb(106, 168, 79)",
			        "rgb(69, 129, 142)", "rgb(60, 120, 216)", "rgb(61, 133, 198)", "rgb(103, 78, 167)", "rgb(166, 77, 121)",
			        "rgb(91, 15, 0)", "rgb(102, 0, 0)", "rgb(120, 63, 4)", "rgb(127, 96, 0)", "rgb(39, 78, 19)", 
			        "rgb(12, 52, 61)", "rgb(28, 69, 135)", "rgb(7, 55, 99)", "rgb(32, 18, 77)", "rgb(76, 17, 48)","rgba(255, 255, 255,0.1)"]
			    ]
			});
			
			
			$(".main-div div[id]").bind("contextmenu",function(e){
				  e.preventDefault();
				  divpointer=$(this);
				  divId=divpointer.attr("id");
				  $(".cntr").css("right",0);
				  $(".cntr").css("top",0);
				 // $("#cntnr").hide(100);        
				  $(".cntr").fadeIn(200,startFocusOut());      
				});

				function startFocusOut(){
				  $(document).on("click",function(){
				  $(".cntr").hide();        
				  $(document).off("click");
				  });
				}
				
				$("#items > .changeBackgroundColor").backspectrum({
					backgroundColor: "#ECC",
				    showInput: true,
				    className: "full-spectrum",
				    showInitial: true,
				    showPalette: true,
				    showSelectionPalette: true,
				    maxSelectionSize: 10,
				    preferredFormat: "hex",
				    localStorageKey: "backspectrum.demo",
				    move: function (backgroundColor) {
				        
				    },
				    show: function () {
				    
				    },
				    beforeShow: function () {
				    
				    },
				    hide: function () {
				    
				    },
				    change: function(backgroundColor) {
				    	 $("#basic-log").text("change called: " + backgroundColor.toHexString());
				    	 document.getElementById(divId).style.backgroundColor = backgroundColor; 
				    	 BackgroundColor=backgroundColor.toHex8String();
				    	
				    	 if(BackgroundColor=="#00ffffff"){
				    		 BackgroundColor="transparent";
				    		
				    	 }
				    	 
				    },
				    palette: [
				        ["rgb(0, 0, 0)", "rgb(67, 67, 67)", "rgb(102, 102, 102)",
				        "rgb(204, 204, 204)", "rgb(217, 217, 217)","rgb(255, 255, 255)"],
				        ["rgb(152, 0, 0)", "rgb(255, 0, 0)", "rgb(255, 153, 0)", "rgb(255, 255, 0)", "rgb(0, 255, 0)",
				        "rgb(0, 255, 255)", "rgb(74, 134, 232)", "rgb(0, 0, 255)", "rgb(153, 0, 255)", "rgb(255, 0, 255)"], 
				        ["rgb(230, 184, 175)", "rgb(244, 204, 204)", "rgb(252, 229, 205)", "rgb(255, 242, 204)", "rgb(217, 234, 211)", 
				        "rgb(208, 224, 227)", "rgb(201, 218, 248)", "rgb(207, 226, 243)", "rgb(217, 210, 233)", "rgb(234, 209, 220)", 
				        "rgb(221, 126, 107)", "rgb(234, 153, 153)", "rgb(249, 203, 156)", "rgb(255, 229, 153)", "rgb(182, 215, 168)", 
				        "rgb(162, 196, 201)", "rgb(164, 194, 244)", "rgb(159, 197, 232)", "rgb(180, 167, 214)", "rgb(213, 166, 189)", 
				        "rgb(204, 65, 37)", "rgb(224, 102, 102)", "rgb(246, 178, 107)", "rgb(255, 217, 102)", "rgb(147, 196, 125)", 
				        "rgb(118, 165, 175)", "rgb(109, 158, 235)", "rgb(111, 168, 220)", "rgb(142, 124, 195)", "rgb(194, 123, 160)",
				        "rgb(166, 28, 0)", "rgb(204, 0, 0)", "rgb(230, 145, 56)", "rgb(241, 194, 50)", "rgb(106, 168, 79)",
				        "rgb(69, 129, 142)", "rgb(60, 120, 216)", "rgb(61, 133, 198)", "rgb(103, 78, 167)", "rgb(166, 77, 121)",
				        "rgb(91, 15, 0)", "rgb(102, 0, 0)", "rgb(120, 63, 4)", "rgb(127, 96, 0)", "rgb(39, 78, 19)", 
				        "rgb(12, 52, 61)", "rgb(28, 69, 135)", "rgb(7, 55, 99)", "rgb(32, 18, 77)", "rgb(76, 17, 48)","rgba(255, 255, 255,0.1)"]
				    ]
				});
				
				$("#items > .changeBorderColor").backspectrum({
					backgroundColor: "#ECC",
				    showInput: true,
				    className: "full-spectrum",
				    showInitial: true,
				    showPalette: true,
				    showSelectionPalette: true,
				    maxSelectionSize: 10,
				    preferredFormat: "hex",
				    localStorageKey: "backspectrum.demo",
				    move: function (backgroundColor) {
				        
				    },
				    show: function () {
				    
				    },
				    beforeShow: function () {
				    
				    },
				    hide: function () {
				    
				    },
				    change: function(backgroundColor) {
				    	 $("#basic-log").text("change called: " + backgroundColor.toHexString());
				    	 document.getElementById(divId).style.borderColor = backgroundColor; 
				    	 BorderColor=backgroundColor.toHex8String();
				    	 if(BorderColor=="#1affffff"){
				    		 BorderColor="#00ffffff";
				    		
				    	 }
				    	 
				    },
				    palette: [
				        ["rgb(0, 0, 0)", "rgb(67, 67, 67)", "rgb(102, 102, 102)",
				        "rgb(204, 204, 204)", "rgb(217, 217, 217)","rgb(255, 255, 255)"],
				        ["rgb(152, 0, 0)", "rgb(255, 0, 0)", "rgb(255, 153, 0)", "rgb(255, 255, 0)", "rgb(0, 255, 0)",
				        "rgb(0, 255, 255)", "rgb(74, 134, 232)", "rgb(0, 0, 255)", "rgb(153, 0, 255)", "rgb(255, 0, 255)"], 
				        ["rgb(230, 184, 175)", "rgb(244, 204, 204)", "rgb(252, 229, 205)", "rgb(255, 242, 204)", "rgb(217, 234, 211)", 
				        "rgb(208, 224, 227)", "rgb(201, 218, 248)", "rgb(207, 226, 243)", "rgb(217, 210, 233)", "rgb(234, 209, 220)", 
				        "rgb(221, 126, 107)", "rgb(234, 153, 153)", "rgb(249, 203, 156)", "rgb(255, 229, 153)", "rgb(182, 215, 168)", 
				        "rgb(162, 196, 201)", "rgb(164, 194, 244)", "rgb(159, 197, 232)", "rgb(180, 167, 214)", "rgb(213, 166, 189)", 
				        "rgb(204, 65, 37)", "rgb(224, 102, 102)", "rgb(246, 178, 107)", "rgb(255, 217, 102)", "rgb(147, 196, 125)", 
				        "rgb(118, 165, 175)", "rgb(109, 158, 235)", "rgb(111, 168, 220)", "rgb(142, 124, 195)", "rgb(194, 123, 160)",
				        "rgb(166, 28, 0)", "rgb(204, 0, 0)", "rgb(230, 145, 56)", "rgb(241, 194, 50)", "rgb(106, 168, 79)",
				        "rgb(69, 129, 142)", "rgb(60, 120, 216)", "rgb(61, 133, 198)", "rgb(103, 78, 167)", "rgb(166, 77, 121)",
				        "rgb(91, 15, 0)", "rgb(102, 0, 0)", "rgb(120, 63, 4)", "rgb(127, 96, 0)", "rgb(39, 78, 19)", 
				        "rgb(12, 52, 61)", "rgb(28, 69, 135)", "rgb(7, 55, 99)", "rgb(32, 18, 77)", "rgb(76, 17, 48)","rgba(255, 255, 255,0.1)"]
				    ]
				});
				$("#changeBackgroundImage").click(function(){
					laybackground="front";
					$("#backgroundfile").click();
					
				});
				$("#changeBackgroundImageBack").click(function(){
					laybackground="bck";
					$("#backgroundfile").click();
				});
				
				$("#backgroundfile").change(function(){
					readURL(this);
					saveBackground();
					
				});
				
				$(".main-div").bind("contextmenu",function(e){
					  e.preventDefault();
					 
					  $(".cntr").css("right",0);
					  $(".cntr").css("top",0);
					 // $("#cntnr").hide(100);        
					  $(".cntr").fadeIn(200,startFocusOut());      
					});

					function startFocusOut(){
					  $(document).on("click",function(){
					  $(".cntr").hide();        
					  $(document).off("click");
					  });
					}
					
					$(".labelfontSize").click(function(){
						$("#lfontval").show();
						
					});
					$("#lfontval").blur(function(){
						$("#lfontval").hide();
					});
					
					$("#lfontval").change(function(){
						 if(thisId!=undefined)
						 document.getElementById(thisId).style.fontSize =$(this).val(); 
					});
					
				$(".headerfontSize").click(function(){
					$("#hfontval").show();
					
				});
				$("#hfontval").blur(function(){
					$("#hfontval").hide();
				});
				$("#restoreSize").click(function(){
					$(".main-div").css({
						"width":"203px",
							"height":"318px"
					});
				});
				
				$("#restoreHSize").click(function(){
					$(".main-div").css({
						"width":"318px",
							"height":"203px"
					});
				});
				$("#idCardDesignListDropDown").change(function(){
					if(this.value !=""){
					$('body').append('<link href="CSS/IdCard/'+$(this).val()+'" rel="stylesheet" type="text/css" />');
					
					$("#layoutImage").attr("src","./images/IdCard/"+$(this).val().split(".")[0]+".jpg");
					
					if(tempTemplateName!=null){
						$('body').find("link[href='CSS/IdCard/"+tempTemplateName+"']").remove();
					}
					
					imgUrl="images/IdCard/"+$(this).val().split(".")[0]+".jpg";
					
				
					tempTemplateName=$(this).val();
					
				
					}
				});	
				
				
				});		

function readURL(input) {

	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			if(laybackground=="bck"){
				$('#layoutImagebck').attr('src', e.target.result);
			}
			else{
				$('#layoutImage').attr('src', e.target.result);
			}
		
		};

		reader.readAsDataURL(input.files[0]);
		
	}

}

/*function rgb2hex(rgb){
	 rgb = rgb.match(/^rgba?[\s+]?\([\s+]?(\d+)[\s+]?,[\s+]?(\d+)[\s+]?,[\s+]?(\d+)[\s+]?/i);
	 return (rgb && rgb.length === 4) ? "#" +
	  ("0" + parseInt(rgb[1],10).toString(16)).slice(-2) +
	  ("0" + parseInt(rgb[2],10).toString(16)).slice(-2) +
	  ("0" + parseInt(rgb[3],10).toString(16)).slice(-2) : '';
	}*/

function rgb2hex(orig) {
	  var a, isPercent,
	    rgb = orig.replace(/\s/g, '').match(/^rgba?\((\d+),(\d+),(\d+),?([^,\s)]+)?/i),
	    alpha = (rgb && rgb[4] || "").trim(),
	    hex = rgb ? "#" +
	    (rgb[1] | 1 << 8).toString(16).slice(1) +
	    (rgb[2] | 1 << 8).toString(16).slice(1) +
	    (rgb[3] | 1 << 8).toString(16).slice(1) : orig;
	  if (alpha !== "") {
	    isPercent = alpha.indexOf("%") > -1;
	    a = parseFloat(alpha);
	    if (!isPercent && a >= 0 && a <= 1) {
	      a = Math.round(255 * a);
	    } else if (isPercent && a >= 0 && a <= 100) {
	      a = Math.round(255 * a / 100)
	    } else {
	      a = "";
	    }
	  }
	  if (a) {
	    hex += (a | 1 << 8).toString(16).slice(1);
	  }
	  return hex;
	}
function getStudentIdDesignList(){
	$.ajax({
		type:"POST",
		url:"adminMenu.html?method=getStudentIdDesignList",
		async:false,
		success:function(data){
			var result=$.parseJSON(data);
			$("#idCardDesignListDropDown").empty();
			$("#idCardDesignListDropDown").append("<option value=''>-------select-------</option>");
			for(var i=0;i<result.List.length;i++){
				$("#idCardDesignListDropDown").append("<option value='"+result.List[i].idCardtemplateValue+"'>"+result.List[i].idCardtemplateName+"</option>");
				
			}
		}
	});
}
function saveBackground(){
	var layoutdetails=$("#layoutDetails").val();
	if(laybackground=="bck"){
		layoutdetails=$("#layoutDetails").val()+"_bck";
	}
	var formdata;
	formdata = new FormData();
	var file=$("#backgroundfile")[0].files[0];


		formdata.append("inputfile",file); 
		formdata.append("layoutDetails",layoutdetails); 

		$.ajax({
			url:'adminMenu.html?method=saveLayoutImage',
			cache:false,
			type: 'POST',
			data:formdata,
			contentType: false,
			processData: false,

			success:function(data){
				
			} 
		});
}

