

 function getNoticeID(value){
    	
	var noticeId={"id":value};
	
	$.ajax({
	
	   
		url: "noticeboard.html?method=popupNoticeboard",
		data: noticeId,
		success: function(data){
		
	      var noticeBoardList=$.parseJSON(data);
			
			
			$("#update-dialog").dialog({
				autoOpen: true,
				height: '360',
				width: '400',
				modal: true,
				buttons: { 
				    	"Ok": function() {
				    		
				    		$(this).dialog("close");
				    		}
				         } 
			});
			
        document.forms["noticeBoardpopupform"]["noticeid"].value=noticeBoardList.NoticeBoardlist[0].vnoticeid;
		document.forms["noticeBoardpopupform"]["title"].value=noticeBoardList.NoticeBoardlist[0].vtitle;			
		document.forms["noticeBoardpopupform"]["startdate"].value=noticeBoardList.NoticeBoardlist[0].vstartdate;			
		document.forms["noticeBoardpopupform"]["enddate"].value=noticeBoardList.NoticeBoardlist[0].venddate;
		document.forms["noticeBoardpopupform"]["description"].value=noticeBoardList.NoticeBoardlist[0].vdescription;
		document.forms["noticeBoardpopupform"]["status"].value=noticeBoardList.NoticeBoardlist[0].vstatus;
		
		},
		error:function(data){
			alert("Error:noticeId Has Not Edited Because of Some Internal Problem");
			
		}
		
	});
	
}

