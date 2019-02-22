$(document).ready(function(){
	accyear=$("#hiddenaccyear").val();
      $("#back").click(function()
			{
			window.location.href="examTimetablePath.html?method=setGradeDependency&accyear="+accyear+"&hschoolLocation="+$("#hiddenlocid").val();
			});
      
      $('.depend').click(function(){
    	  var sectionId = $(this).closest("tr").find(".sectionId").attr("id");
    	  var classId = $(this).closest("tr").find(".classId").attr("id");
    	  $("#hclass").val(classId);
    	  $("#hsection").val(sectionId);
    	  $("#dialog1").dialog('open');
    	  $("#selectall").click(function(){
    		  $(".select").prop('checked', $(this).prop("checked"));
    	  });
      });
      
      $(".select").change(function(){
    	  if($(".select").length==$(".select:checked").length){
    		  $("#selectall").attr("checked",true);
    	  }
    	  else{
    		  $("#selectall").attr("checked",false);
    	  }

      });
     
     
      $("#dialog1").dialog({

    	  autoOpen  : false,
    	  width  : 232,
    	  modal     : true,
    	  title     :"Select Grade Dependency",
    	  buttons   : {
    		  "Move": {
    			  text: 'Move',
    			  click: function() {
    				  myArray = [];
    				  var cnt=0;
    				  $("#dialog1").dialog("close");
    				  $('input[type="checkbox"]:checked').each(function(){
    					  myArray.push($(this).val()); 
    					  cnt++;
    				  });
    				 
    				  if(cnt==0){
    					  $("#dialog1").dialog("open");
    					  $(".errormessagediv").show();
    					  $(".validateTips").text("Select Any One Field");
    					  return false;
    				  }
    				  $("#dialog2").dialog("open");
    				  for(var i=0; i<myArray.length; i++){
    					  $(".dialog2 ."+myArray[i]).show();
    				  }
    				 
    				  
    			  }
    		  },
    		  'Close' : function() {
    			  $("#selectall").attr('checked',false);
    			  $(".select").attr('checked',false);
    			  $(this).dialog('close');
    			 
    		  }
    	  }
      });
      
      $("#dialog2").dialog({

  		autoOpen  : false,
  		modal     : true,
  		width     : 480,
  		title     :"Grade Dependency",
  		buttons   : {
  			"Save": {
  				text: 'Save',
  				click: function() {
  					var accyear=$("#hiddenaccyear").val();
  					var sectionId=$("#hsection").val();
  			    	var classId=$("#hclass").val();
  			        var exam=$("#hiddenexamid").val();
  			        var location=$("#hiddenlocid").val();
  			        var project=$("#projectmark").val();
  					var assignment=$("#assignmentmark").val();
  					var practical=$("#practicalmark").val();
  					var attendance=$("#attendancemark").val();
  					
  					
  					datalist = {
  							"project":project,
  							"assignment":assignment,
  							"practical":practical,
  							"attendance":attendance,
  							"sectionId":sectionId,
  							"classId":classId,
  							"exam":exam,
  							"location":location,
  							"accyear":accyear,
  					},
  						
  					$.ajax({
  						type : "POST",
  						url : "examTimetablePath.html?method=insertGradeDependent",
  						data : datalist,
  						async : false,
  						success : function(data) {
  							var result = $.parseJSON(data);
  							if(result.status == "success"){
  								$('.errormessagediv').hide();
  								$(".successmessagediv").show();
  								$(".validateTips").text("Dependency Added Succesfully");
  								$("#dialog2").dialog('close');
  								location.reload();
  							}else{
  								$('.errormessagediv').show();
  								$(".successmessagediv").hide();
  								$(".errormessage").text(result.status);
  								$("#dialog2").dialog('close');
  								location.reload();
  							}
  							
  						}
  					});
  					
  				  $("#selectall").attr('checked',false);
    			  $(".select").attr('checked',false);
    			  location.reload();
			  }
  			},
  			'Close' : function() {
  				$("#selectall").attr('checked',false);
  				$(".select").attr('checked',false);
  				for(var i=0; i<myArray.length; i++){
  					$(".dialog2 ."+myArray[i]).hide();
  				}
  				$(this).dialog('close');
  				location.reload();
  			},
  		}
  	});
      
      
});

