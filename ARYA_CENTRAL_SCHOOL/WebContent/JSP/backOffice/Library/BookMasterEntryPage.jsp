<!DOCTYPE html>
<html lang="en">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<link rel="stylesheet"	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/timepicker.js"></script>
<script type="text/javascript" src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script> 

<script type="text/javascript"
	src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet" />
<link href="CSS/newUI/modern-business.css" rel="stylesheet" />
<link href="CSS/newUI/custome.css" rel="stylesheet" />
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />

<script type="text/javascript" src="JS/backOffice/Library/BookEntryPage.js"></script> 

<!-- <script >
	$('.carousel').carousel({
		interval : 5000
	//changes the speed
	})
	$(document).scroll(function() {
		var y = $(this).scrollTop();
		if (y > 100) {
			$('.topimg').fadeIn();
		} else {
			$('.topimg').fadeOut();
		}
	});
</script>
<script>
	$('.carousel').carousel({
		interval : 5000
	//changes the speed
	})
</script> -->


<style>
.buttons{

vertical-align: 0px;

}
</style>
</head>

<body>
	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span>Book Details</span>
		</p>
		
		
				<div class="successmessagediv" align="center" style="display: none;">
						<div class="message-item">
							<!-- Warning -->
							<a href="#" class="msg-success bg-msg-succes"><span
								class="validateTips"></span></a>
						</div>
					</div>	
					

					
				<div class="errormessagediv" align="center" style="display: none;">
					<div class="message-item">
					<!-- Warning -->
				    <a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
					</div>
					</div>	
				

			<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>
		
					<logic:present name="errormessagediv" scope="request">
		
						<div class="errormessagediv" align="center" style="display: none;">
					<div class="message-item">
					<!-- Warning -->
				    <a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span>
					<bean:write	name="errormessagediv" scope="request" /></a>
					</div>
					</div>	
							</logic:present>
					
				

		<form action="driverDetailsPath.html?method=savedriverval" id="driverformid" enctype="multipart/form-data"  method="post">
			<div class="panel-group"  role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #767676;vertical-align: text-top;"><h4 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Book Details
							</h4></a>
						

							<div class="navbar-right" >
							
							<a id="savedriver" class="buttons"data-toggle="tooltip" data-placement="bottom" title="Save"> Save
							</a>	&nbsp;
								
							<a href="adminMenu.html?method=driverList">
								<span class="buttons" id="back" >Back</span></a>
							</div>
						
					</div>
					


			<input	type="hidden" name="drivercode" id="drivercode" value="<logic:present name="driverlist" ><bean:write name="driverlist" property="driverCode"/></logic:present>"/>
		    <input  type="hidden" name="vehiclecode" id="vehiclecode" value="<logic:present name="fuellist" ><bean:write name="fuellist" property="vehicleCode"/></logic:present>"/>
            <input type="hidden" name="fuelcode" id="fuelcode"  value="<logic:present name="fuellist" ><bean:write name="fuellist" property="fuelCode"/></logic:present>"/>
		    <input type="hidden" name="license" id="hlicensetodrive" value='<logic:present name="driverlist"><bean:write name="driverlist" property="license" /></logic:present>'></input>
		    <input type="hidden" name="licenseupload" id="hlicenseupload" value='<logic:present name="driverlist"><bean:write name="driverlist" property="licensedrive" /></logic:present>'></input>




			<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
						
						
					
						
				
					<div class="col-md-12" style="padding:0;height:300px; !important;overflow-y:scroll !important; ">				
	<table  class="table" id="allstudent" style="font-family: Open Sans Light;color: #897676;" >
	<tr>
	 <th  width="20px !important;">S.No</th>
	<!--  <th >Sub Module</th> -->
	 <th style="text-align: center;width:130px !important;">Book Name</th>  
	 <th   style="text-align: center;width:130px !important;">Author Name</th>
	 <th  style="text-align: center;width:130px !important;">Edition</th> 
	 <th style="text-align: center;width:130px !important;">BarCode Number</th>
	  <th style="text-align: center;width:130px !important;">Delete</th>
	  
	
	</tr>
		<tr>
		<td > 
		<span  class="snoClass" id="sno">1</span>
		</td>
		
		<td align="center"> 
		<input type="text" name="bookname" id="bookname1" class="bookclass">
		</td>
		
		<td align="center"> 
		<input type="text" name="authorname" id="authorname1" class="authorclass">
		</td>
		
		<td align="center"> 
		<input type="text" name="edition" id="edition1" class="editionclass">
		</td>
		
		<td align="center"> 
		<input type="text" name="barcode"  id="barcode1" class="barcodeclass">
		</td>
		
		
		<td align="center"> 
		<input type="text" name="barcode"  id="barcode1" class="barcodeclass">
		</td>
		
		</tr>	
		
		<tr>
		<td> 
		<span  class="snoClass" id="sno">2</span>
		</td>
		
		<td align="center"> 
		<input type="text" name="bookname" id="bookname2" class="bookclass">
		</td>
		
		<td align="center"> 
		<input type="text" name="authorname" id="authorname2" class="authorclass">
		</td>
		
		<td align="center"> 
		<input type="text" name="edition"  id="edition2" class="editionclass">
		</td>
		
		<td align="center"> 
		<input type="text" name="barcode" id="barcode2" class="barcodeclass">
		</td>
		</tr>	
		
		<tr>
		<td> 
		<span  class="snoClass" id="sno">3</span>
		</td>
		
		<td align="center"> 
		<input type="text" name="bookname" id="bookname3" class="bookclass">
		</td>
		
		<td align="center"> 
		<input type="text"  name="authorname" id="authorname3" class="authorclass">
		</td>
		
		<td align="center"> 
		<input type="text" name="edition"  id="edition3" class="editionclass">
		</td>
		
		<td align="center"> 
		<input type="text" name="barcode" id="barcode3" class="barcodeclass">
		</td>
		</tr>	
		
		<tr>
		<td> 
		<span  class="snoClass" id="sno">4</span>
		</td>
		
		<td align="center"> 
		<input type="text" name="bookname" id="bookname4" class="bookclass">
		</td>
		
		<td align="center"> 
		<input type="text" name="authorname" id="authorname4" class="authorclass">
		</td>
		
		<td align="center"> 
		<input type="text" name="edition"  id="edition4" class="editionclass">
		</td>
		
		<td align="center"> 
		<input type="text" name="barcode" id="barcode4" class="barcodeclass">
		</td>
		</tr>			
		
		</table>
		
		
			<!-- <div align="center">
						<input type="button" value="Add Row" id="Add" class="btn btn-success" />
					</div>	 -->
					<div align="center">
					<button type="button" id="addrow" class="btn btn-info">Add Row</button>
					</div>
		</div>
			
	</div>	
		
		
						
			
						
						
						
						
				
							
</div>							
</div>					
	</div>				
</form>
				
			
				
</div>			
	
</body>

</html>
