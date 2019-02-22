
$(document).ready(function(){
	
	$(".displayno").text($(".pagebanner").text());
	$(".pagebanner").hide();
	$(".pagelinks").hide();
	
		
	if($(".success").text().length!=7){
	    $("#sucesshover").show();
	}else{
		 $("#sucesshover").hide();
	} 
	
	setTimeout(function() { 
		$('#sucesshover').fadeOut(); 
	    }, 3000);
	
	$("#successmsg").click( function(){
		$("#sucesshover").hide();
	});
	
	
	
	//Student Registration
	
	$('td .permissionStudent').change(function(){
		
		var cnt = 0;
		$('input.permissionStudent:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionStudent:checkbox').length;
		if(cnt==cn){
			$('#StudentAll').prop('checked',true);
		}
		else{
			$('#StudentAll').prop('checked',false);
		}
	});
	
	$('#StudentAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionStudent').prop('checked',true);
			$('input.permissionStudent').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionStudent').prop('checked',false);
			$('input.permissionStudent').parent().css({'color':''});
		}
	});
	

	
	//Student Promotion
	
	$('td .permissionStudentPromotion').change(function(){
		var cnt = 0;
		$('input.permissionStudentPromotion:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionStudentPromotion:checkbox').length;
		if(cnt==cn){
			$('#StudentPromotionAll').prop('checked',true);
		}
		else{
			$('#StudentPromotionAll').prop('checked',false);
		}
	});
	
	$('#StudentPromotionAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionStudentPromotion').prop('checked',true);
			$('input.permissionStudentPromotion').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionStudentPromotion').prop('checked',false);
			$('input.permissionStudentPromotion').parent().css({'color':''});
		}
	});
	
	
	//Student Enquiry
	
	$('td .permissionStudentEnquiry').change(function(){
		var cnt = 0;
		$('input.permissionStudentEnquiry:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionStudentEnquiry:checkbox').length;
		if(cnt==cn){
			$('#StudentEnquiryAll').prop('checked',true);
		}
		else{
			$('#StudentEnquiryAll').prop('checked',false);
		}
	});
	
	$('#StudentEnquiryAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionStudentEnquiry').prop('checked',true);
			$('input.permissionStudentEnquiry').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionStudentEnquiry').prop('checked',false);
			$('input.permissionStudentEnquiry').parent().css({'color':''});
		}
	});
	
	
	//Stream
	
	$('td .permissionStream').change(function(){
		var cnt = 0;
		$('input.permissionStream:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionStream:checkbox').length;
		if(cnt==cn){
			$('#StreamAll').prop('checked',true);
		}
		else{
			$('#StreamAll').prop('checked',false);
		}
	});
	
	
	$('#StreamAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
	
			$('input.permissionStream').prop('checked',true);
			$('input.permissionStream').parent().css({'color':'blue'});
		}
		else{
		
			$('input.permissionStream').prop('checked',false);
			$('input.permissionStream').parent().css({'color':''});
		}
	});

	
	//Role
	
	$('td .permissionRole').change(function(){
		var cnt = 0;
		$('input.permissionRole:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionRole:checkbox').length;
		if(cnt==cn){
			$('#RoleAll').prop('checked',true);
		}
		else{
			$('#RoleAll').prop('checked',false);
		}
	});
	
	$('#RoleAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionRole').prop('checked',true);
			$('input.permissionRole').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionRole').prop('checked',false);
			$('input.permissionRole').parent().css({'color':''});
		}
	});
	

	//Department
	
	$('td .permissionDepartment').change(function(){
		var cnt = 0;
		$('input.permissionDepartment:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionDepartment:checkbox').length;
		if(cnt==cn){
			$('#DepartmentAll').prop('checked',true);
		}
		else{
			$('#DepartmentAll').prop('checked',false);
		}
	});
	
	$('#DepartmentAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionDepartment').prop('checked',true);
			$('input.permissionDepartment').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionDepartment').prop('checked',false);
			$('input.permissionDepartment').parent().css({'color':''});
		}
	});
	
	//Class
	
	$('td .permissionClass').change(function(){
		var cnt = 0;
		$('input.permissionClass:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionClass:checkbox').length;
		if(cnt==cn){
			$('#ClassAll').prop('checked',true);
		}
		else{
			$('#ClassAll').prop('checked',false);
		}
	});
	
	$('#ClassAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionClass').prop('checked',true);
			$('input.permissionClass').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionClass').prop('checked',false);
			$('input.permissionClass').parent().css({'color':''});
		}
	});
	
	
	//Designation
	
	$('td .permissionDesignation').change(function(){
		var cnt = 0;
		$('input.permissionDesignation:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionDesignation:checkbox').length;
		if(cnt==cn){
			$('#DesignationAll').prop('checked',true);
		}
		else{
			$('#DesignationAll').prop('checked',false);
		}
	});
	

	$('#DesignationAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionDesignation').prop('checked',true);
			$('input.permissionDesignation').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionDesignation').prop('checked',false);
			$('input.permissionDesignation').parent().css({'color':''});
		}
	});
	
	// Section
	
	$('td .permissionSection').change(function(){
		var cnt = 0;
		$('input.permissionSection:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionSection:checkbox').length;
		if(cnt==cn){
			$('#SectionAll').prop('checked',true);
		}
		else{
			$('#SectionAll').prop('checked',false);
		}
	});
	
	$('#SectionAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionSection').prop('checked',true);
			$('input.permissionSection').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionSection').prop('checked',false);
			$('input.permissionSection').parent().css({'color':''});
		}
	});
	
	
	//Quata
	
	
	$('td .permissionQuota').change(function(){
		var cnt = 0;
		$('input.permissionQuota:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionQuota:checkbox').length;
		if(cnt==cn){
			$('#QuotaAll').prop('checked',true);
		}
		else{
			$('#QuotaAll').prop('checked',false);
		}
	});
	
	$('#QuotaAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionQuota').prop('checked',true);
			$('input.permissionQuota').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionQuota').prop('checked',false);
			$('input.permissionQuota').parent().css({'color':''});
		}
	});
	
	
	
	//Stage
	
	$('td .permissionStage').change(function(){
		var cnt = 0;
		$('input.permissionStage:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionStage:checkbox').length;
		if(cnt==cn){
			$('#StageAll').prop('checked',true);
		}
		else{
			$('#StageAll').prop('checked',false);
		}
	});
	
	$('#StageAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionStage').prop('checked',true);
			$('input.permissionStage').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionStage').prop('checked',false);
			$('input.permissionStage').parent().css({'color':''});
		}
	});
	
	
	//Driver
	
	$('td .permissionDriver').change(function(){
		var cnt = 0;
		$('input.permissionDriver:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionDriver:checkbox').length;
		if(cnt==cn){
			$('#DriverAll').prop('checked',true);
		}
		else{
			$('#DriverAll').prop('checked',false);
		}
	});
	
	
	$('#DriverAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionDriver').prop('checked',true);
			$('input.permissionDriver').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionDriver').prop('checked',false);
			$('input.permissionDriver').parent().css({'color':''});
		}
	});
	
	
	// Route
	
	
	$('td .permissionRoute').change(function(){
		var cnt = 0;
		$('input.permissionRoute:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionRoute:checkbox').length;
		if(cnt==cn){
			$('#RouteAll').prop('checked',true);
		}
		else{
			$('#RouteAll').prop('checked',false);
		}
	});
	$('#RouteAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionRoute').prop('checked',true);
			$('input.permissionRoute').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionRoute').prop('checked',false);
			$('input.permissionRoute').parent().css({'color':''});
		}
	});
	
	
	//Vehicle
	
	$('td .permissionVehicle').change(function(){
		var cnt = 0;
		$('input.permissionVehicle:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionVehicle:checkbox').length;
		if(cnt==cn){
			$('#VehicleAll').prop('checked',true);
		}
		else{
			$('#VehicleAll').prop('checked',false);
		}
	});
	$('#VehicleAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionVehicle').prop('checked',true);
			$('input.permissionVehicle').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionVehicle').prop('checked',false);
			$('input.permissionVehicle').parent().css({'color':''});
		}
	});
	
	
	//Subject
	
	$('td .permissionSubject').change(function(){
		var cnt = 0;
		$('input.permissionSubject:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionSubject:checkbox').length;
		if(cnt==cn){
			$('#SubjectAll').prop('checked',true);
		}
		else{
			$('#SubjectAll').prop('checked',false);
		}
	});
	$('#SubjectAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionSubject').prop('checked',true);
			$('input.permissionSubject').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionSubject').prop('checked',false);
			$('input.permissionSubject').parent().css({'color':''});
		}
	});
	
	//AcademicYear
	
	$('td .permissionAcademicYear').change(function(){
		var cnt = 0;
		$('input.permissionAcademicYear:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionAcademicYear:checkbox').length;
		if(cnt==cn){
			$('#AcademicYearAll').prop('checked',true);
		}
		else{
			$('#AcademicYearAll').prop('checked',false);
		}
	});
	$('#AcademicYearAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionAcademicYear').prop('checked',true);
			$('input.permissionAcademicYear').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionAcademicYear').prop('checked',false);
			$('input.permissionAcademicYear').parent().css({'color':''});
		}
	});
	
	
	//Staff Registration
	
	$('td .permissionRegistration').change(function(){
		var cnt = 0;
		$('input.permissionRegistration:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionRegistration:checkbox').length;
		if(cnt==cn){
			$('#RegistrationAll').prop('checked',true);
		}
		else{
			$('#RegistrationAll').prop('checked',false);
		}
	});
	$('#RegistrationAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionRegistration').prop('checked',true);
			$('input.permissionRegistration').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionRegistration').prop('checked',false);
			$('input.permissionRegistration').parent().css({'color':''});
		}
	});
	
	
	//Staff Employement
	
	$('td.permissionEmployement').change(function(){
		var cnt = 0;
		$('input.permissionEmployement:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionEmployement:checkbox').length;
		if(cnt==cn){
			$('#EmployementAll').prop('checked',true);
		}
		else{
			$('#EmployementAll').prop('checked',false);
		}
	});
	$('#EmployementAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionEmployement').prop('checked',true);
			$('input.permissionEmployement').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionEmployement').prop('checked',false);
			$('input.permissionEmployement').parent().css({'color':''});
		}
	});
	
	
	//Staff Attendance
	
	$('td .permissionAttendance').change(function(){
		var cnt = 0;
		$('input.permissionAttendance:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionAttendance:checkbox').length;
		if(cnt==cn){
			$('#AttendanceAll').prop('checked',true);
		}
		else{
			$('#AttendanceAll').prop('checked',false);
		}
	});
	$('#AttendanceAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionAttendance').prop('checked',true);
			$('input.permissionAttendance').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionAttendance').prop('checked',false);
			$('input.permissionAttendance').parent().css({'color':''});
		}
	});
	
	
	// PayrollGeneration
	
	$('td .permissionPayrollGeneration').change(function(){
		var cnt = 0;
		$('input.permissionPayrollGeneration:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionPayrollGeneration:checkbox').length;
		if(cnt==cn){
			$('#PayrollGenerationAll').prop('checked',true);
		}
		else{
			$('#PayrollGenerationAll').prop('checked',false);
		}
	});
	$('#PayrollGenerationAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionPayrollGeneration').prop('checked',true);
			$('input.permissionPayrollGeneration').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionPayrollGeneration').prop('checked',false);
			$('input.permissionPayrollGeneration').parent().css({'color':''});
		}
	});
	
	
	// FeeMaster
	
	$('td .permissionFeeMaster').change(function(){
		var cnt = 0;
		$('input.permissionFeeMaster:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionFeeMaster:checkbox').length;
		if(cnt==cn){
			$('#FeeMasterAll').prop('checked',true);
		}
		else{
			$('#FeeMasterAll').prop('checked',false);
		}
	});
	$('#FeeMasterAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionFeeMaster').prop('checked',true);
			$('input.permissionFeeMaster').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionFeeMaster').prop('checked',false);
			$('input.permissionFeeMaster').parent().css({'color':''});
		}
	});
	
	
	// TermMaster
	
	$('td .permissionTermMaster').change(function(){
		var cnt = 0;
		$('input.permissionTermMaster:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionTermMaster:checkbox').length;
		if(cnt==cn){
			$('#TermMasterAll').prop('checked',true);
		}
		else{
			$('#TermMasterAll').prop('checked',false);
		}
	});
	$('#TermMasterAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionTermMaster').prop('checked',true);
			$('input.permissionTermMaster').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionTermMaster').prop('checked',false);
			$('input.permissionTermMaster').parent().css({'color':''});
		}
	});
	
	// ClassFeeSetup
	
	$('td .permissionClassFeeSetup').change(function(){
		var cnt = 0;
		$('input.permissionClassFeeSetup:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionClassFeeSetup:checkbox').length;
		if(cnt==cn){
			$('#ClassFeeSetupAll').prop('checked',true);
		}
		else{
			$('#ClassFeeSetupAll').prop('checked',false);
		}
	});
	$('#ClassFeeSetupAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionClassFeeSetup').prop('checked',true);
			$('input.permissionClassFeeSetup').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionClassFeeSetup').prop('checked',false);
			$('input.permissionClassFeeSetup').parent().css({'color':''});
		}
	});
	
	
	//StageFeeSetup
	
	$('td .permissionStageFeeSetup').change(function(){
		var cnt = 0;
		$('input.permissionStageFeeSetup:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionStageFeeSetup:checkbox').length;
		if(cnt==cn){
			$('#StageFeeSetupAll').prop('checked',true);
		}
		else{
			$('#StageFeeSetupAll').prop('checked',false);
		}
	});
	$('#StageFeeSetupAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionStageFeeSetup').prop('checked',true);
			$('input.permissionStageFeeSetup').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionStageFeeSetup').prop('checked',false);
			$('input.permissionStageFeeSetup').parent().css({'color':''});
		}
	});
	
	// FeeCollection
	
	$('td .permissionFeeCollection').change(function(){
		var cnt = 0;
		$('input.permissionFeeCollection:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionFeeCollection:checkbox').length;
		if(cnt==cn){
			$('#FeeCollectionAll').prop('checked',true);
		}
		else{
			$('#FeeCollectionAll').prop('checked',false);
		}
	});
	$('#FeeCollectionAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionFeeCollection').prop('checked',true);
			$('input.permissionFeeCollection').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionFeeCollection').prop('checked',false);
			$('input.permissionFeeCollection').parent().css({'color':''});
		}
	});
	
	// ExamDetails
	
	$('td .permissionExamDetails').change(function(){
		var cnt = 0;
		$('input.permissionExamDetails:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionExamDetails:checkbox').length;
		if(cnt==cn){
			$('#ExamDetailsAll').prop('checked',true);
		}
		else{
			$('#ExamDetailsAll').prop('checked',false);
		}
	});
	$('#ExamDetailsAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionExamDetails').prop('checked',true);
			$('input.permissionExamDetails').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionExamDetails').prop('checked',false);
			$('input.permissionExamDetails').parent().css({'color':''});
		}
	});
	
	
	// ExamTimeTable
	
	$('td .permissionExamTimeTable').change(function(){
		var cnt = 0;
		$('input.permissionExamTimeTable:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionExamTimeTable:checkbox').length;
		if(cnt==cn){
			$('#ExamTimeTableAll').prop('checked',true);
		}
		else{
			$('#ExamTimeTableAll').prop('checked',false);
		}
	});
	$('#ExamTimeTableAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionExamTimeTable').prop('checked',true);
			$('input.permissionExamTimeTable').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionExamTimeTable').prop('checked',false);
			$('input.permissionExamTimeTable').parent().css({'color':''});
		}
	});
	
	//FuelMaintenance
	
	$('td .permissionFuelMaintenance').change(function(){
		var cnt = 0;
		$('input.permissionFuelMaintenance:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionFuelMaintenance:checkbox').length;
		if(cnt==cn){
			$('#FuelMaintenanceAll').prop('checked',true);
		}
		else{
			$('#FuelMaintenanceAll').prop('checked',false);
		}
	});
	$('#FuelMaintenanceAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionFuelMaintenance').prop('checked',true);
			$('input.permissionFuelMaintenance').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionFuelMaintenance').prop('checked',false);
			$('input.permissionFuelMaintenance').parent().css({'color':''});
		}
	});
	
	
//ViewEaxmDetails
	
	$('td .permissionViewEaxmDetails').change(function(){
		var cnt = 0;
		$('input.permissionViewEaxmDetails:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionViewEaxmDetails:checkbox').length;
		if(cnt==cn){
			$('#ViewEaxmDetailsAll').prop('checked',true);
		}
		else{
			$('#ViewEaxmDetailsAll').prop('checked',false);
		}
	});
	$('#ViewEaxmDetailsAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionViewEaxmDetails').prop('checked',true);
			$('input.permissionViewEaxmDetails').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionViewEaxmDetails').prop('checked',false);
			$('input.permissionViewEaxmDetails').parent().css({'color':''});
		}
	});
	
	
//ExamModule
	
	$('td .permissionExamModule').change(function(){
		var cnt = 0;
		$('input.permissionExamModule:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionExamModule:checkbox').length;
		if(cnt==cn){
			$('#ExamModuleAll').prop('checked',true);
		}
		else{
			$('#ExamModuleAll').prop('checked',false);
		}
	});
	$('#ExamModuleAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionExamModule').prop('checked',true);
			$('input.permissionExamModule').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionExamModule').prop('checked',false);
			$('input.permissionExamModule').parent().css({'color':''});
		}
	});
	
	
//TransportType
	
	$('td .permissionTransportType').change(function(){
		var cnt = 0;
		$('input.permissionTransportType:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionTransportType:checkbox').length;
		if(cnt==cn){
			$('#TransportTypeAll').prop('checked',true);
		}
		else{
			$('#TransportTypeAll').prop('checked',false);
		}
	});
	$('#TransportTypeAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionTransportType').prop('checked',true);
			$('input.permissionTransportType').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionTransportType').prop('checked',false);
			$('input.permissionTransportType').parent().css({'color':''});
		}
	});
	
	
	
	//ConcessionDetails
	
	$('td .permissionConcessionDetails').change(function(){
		var cnt = 0;
		$('input.permissionConcessionDetails:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionConcessionDetails:checkbox').length;
		if(cnt==cn){
			$('#ConcessionDetailsAll').prop('checked',true);
		}
		else{
			$('#ConcessionDetailsAll').prop('checked',false);
		}
	});
	$('#ConcessionDetailsAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionConcessionDetails').prop('checked',true);
			$('input.permissionConcessionDetails').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionConcessionDetails').prop('checked',false);
			$('input.permissionConcessionDetails').parent().css({'color':''});
		}
	});
	
	
	
	//FeeModule
	
	$('td .permissionFeeModule').change(function(){
		var cnt = 0;
		$('input.permissionFeeModule:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionFeeModule:checkbox').length;
		if(cnt==cn){
			$('#FeeModuleAll').prop('checked',true);
		}
		else{
			$('#FeeModuleAll').prop('checked',false);
		}
	});
	$('#FeeModuleAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionFeeModule').prop('checked',true);
			$('input.permissionFeeModule').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionFeeModule').prop('checked',false);
			$('input.permissionFeeModule').parent().css({'color':''});
		}
	});
	
	
	//LeaveRequest
	
	$('td .permissionLeaveRequest').change(function(){
		var cnt = 0;
		$('input.permissionLeaveRequest:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionLeaveRequest:checkbox').length;
		if(cnt==cn){
			$('#LeaveRequestAll').prop('checked',true);
		}
		else{
			$('#LeaveRequestAll').prop('checked',false);
		}
	});
	$('#LeaveRequestAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionLeaveRequest').prop('checked',true);
			$('input.permissionLeaveRequest').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionLeaveRequest').prop('checked',false);
			$('input.permissionLeaveRequest').parent().css({'color':''});
		}
	});
	
//LeaveApprovel
	
	$('td .permissionLeaveApprovel').change(function(){
		var cnt = 0;
		$('input.permissionLeaveApprovel:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionLeaveApprovel:checkbox').length;
		if(cnt==cn){
			$('#LeaveApprovelAll').prop('checked',true);
		}
		else{
			$('#LeaveApprovelAll').prop('checked',false);
		}
	});
	$('#LeaveApprovelAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionLeaveApprovel').prop('checked',true);
			$('input.permissionLeaveApprovel').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionLeaveApprovel').prop('checked',false);
			$('input.permissionLeaveApprovel').parent().css({'color':''});
		}
	});
	
//LeaveModule
	
	$('td .permissionLeaveModule').change(function(){
		var cnt = 0;
		$('input.permissionLeaveModule:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionLeaveModule:checkbox').length;
		if(cnt==cn){
			$('#LeaveModuleAll').prop('checked',true);
		}
		else{
			$('#LeaveModuleAll').prop('checked',false);
		}
	});
	$('#LeaveModuleAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionLeaveModule').prop('checked',true);
			$('input.permissionLeaveModule').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionLeaveModule').prop('checked',false);
			$('input.permissionLeaveModule').parent().css({'color':''});
		}
	});
	
//ReportModule
	
	$('td .permissionReportModule').change(function(){
		var cnt = 0;
		$('input.permissionReportModule:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionReportModule:checkbox').length;
		if(cnt==cn){
			$('#ReportModuleAll').prop('checked',true);
		}
		else{
			$('#ReportModuleAll').prop('checked',false);
		}
	});
	$('#ReportModuleAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionReportModule').prop('checked',true);
			$('input.permissionReportModule').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionReportModule').prop('checked',false);
			$('input.permissionReportModule').parent().css({'color':''});
		}
	});
	
	
//CommunicationSettings
	
	$('td .permissionCommunicationSettings').change(function(){
		var cnt = 0;
		$('input.permissionCommunicationSettings:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionCommunicationSettings:checkbox').length;
		if(cnt==cn){
			$('#CommunicationSettingsAll').prop('checked',true);
		}
		else{
			$('#CommunicationSettingsAll').prop('checked',false);
		}
	});
	$('#CommunicationSettingsAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionCommunicationSettings').prop('checked',true);
			$('input.permissionCommunicationSettings').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionCommunicationSettings').prop('checked',false);
			$('input.permissionCommunicationSettings').parent().css({'color':''});
		}
	});
	
//InternalJobPosting
	
	$('td .permissionInternalJobPosting').change(function(){
		var cnt = 0;
		$('input.permissionInternalJobPosting:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionInternalJobPosting:checkbox').length;
		if(cnt==cn){
			$('#InternalJobPostingAll').prop('checked',true);
		}
		else{
			$('#InternalJobPostingAll').prop('checked',false);
		}
	});
	$('#InternalJobPostingAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionInternalJobPosting').prop('checked',true);
			$('input.permissionInternalJobPosting').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionInternalJobPosting').prop('checked',false);
			$('input.permissionInternalJobPosting').parent().css({'color':''});
		}
	});
	
	
//AccademicYearPlan
	
	$('td .permissionAccademicYearPlan').change(function(){
		var cnt = 0;
		$('input.permissionAccademicYearPlan:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionAccademicYearPlan:checkbox').length;
		if(cnt==cn){
			$('#AccademicYearPlanAll').prop('checked',true);
		}
		else{
			$('#AccademicYearPlanAll').prop('checked',false);
		}
	});
	$('#AccademicYearPlanAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionAccademicYearPlan').prop('checked',true);
			$('input.permissionAccademicYearPlan').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionAccademicYearPlan').prop('checked',false);
			$('input.permissionAccademicYearPlan').parent().css({'color':''});
		}
	});
	
	
//PasswordMaintenance
	
	$('td .permissionPasswordMaintenance').change(function(){
		var cnt = 0;
		$('input.permissionPasswordMaintenance:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionPasswordMaintenance:checkbox').length;
		if(cnt==cn){
			$('#PasswordMaintenanceAll').prop('checked',true);
		}
		else{
			$('#PasswordMaintenanceAll').prop('checked',false);
		}
	});
	$('#PasswordMaintenanceAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionPasswordMaintenance').prop('checked',true);
			$('input.permissionPasswordMaintenance').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionPasswordMaintenance').prop('checked',false);
			$('input.permissionPasswordMaintenance').parent().css({'color':''});
		}
	});
	
//SetupModule
	
	$('td .permissionSetupModule').change(function(){
		var cnt = 0;
		$('input.permissionSetupModule:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionSetupModule:checkbox').length;
		if(cnt==cn){
			$('#SetupModuleAll').prop('checked',true);
		}
		else{
			$('#SetupModuleAll').prop('checked',false);
		}
	});
	$('#SetupModuleAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionSetupModule').prop('checked',true);
			$('input.permissionSetupModule').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionSetupModule').prop('checked',false);
			$('input.permissionSetupModule').parent().css({'color':''});
		}
	});
	
//ClassTimeTable
	
	$('td .permissionClassTimeTable').change(function(){
		var cnt = 0;
		$('input.permissionClassTimeTable:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionClassTimeTable:checkbox').length;
		if(cnt==cn){
			$('#ClassTimeTableAll').prop('checked',true);
		}
		else{
			$('#ClassTimeTableAll').prop('checked',false);
		}
	});
	$('#ClassTimeTableAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionClassTimeTable').prop('checked',true);
			$('input.permissionClassTimeTable').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionClassTimeTable').prop('checked',false);
			$('input.permissionClassTimeTable').parent().css({'color':''});
		}
	});
	
	
//StaffAttendance
	
	$('td .permissionStaffAttendance').change(function(){
		var cnt = 0;
		$('input.permissionStaffAttendance:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionStaffAttendance:checkbox').length;
		if(cnt==cn){
			$('#StaffAttendanceAll').prop('checked',true);
		}
		else{
			$('#StaffAttendanceAll').prop('checked',false);
		}
	});
	$('#StaffAttendanceAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionStaffAttendance').prop('checked',true);
			$('input.permissionStaffAttendance').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionStaffAttendance').prop('checked',false);
			$('input.permissionStaffAttendance').parent().css({'color':''});
		}
	});
	
	
//StaffModule
	
	$('td .permissionStaffModule').change(function(){
		var cnt = 0;
		$('input.permissionStaffModule:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionStaffModule:checkbox').length;
		if(cnt==cn){
			$('#StaffModuleAll').prop('checked',true);
		}
		else{
			$('#StaffModuleAll').prop('checked',false);
		}
	});
	$('#StaffModuleAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionStaffModule').prop('checked',true);
			$('input.permissionStaffModule').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionStaffModule').prop('checked',false);
			$('input.permissionStaffModule').parent().css({'color':''});
		}
	});
	
	
//StaffRegistration
	
	$('td .permissionStaffRegistration').change(function(){
		var cnt = 0;
		$('input.permissionStaffRegistration:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionStaffRegistration:checkbox').length;
		if(cnt==cn){
			$('#StaffRegistrationAll').prop('checked',true);
		}
		else{
			$('#StaffRegistrationAll').prop('checked',false);
		}
	});
	$('#StaffRegistrationAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionStaffRegistration').prop('checked',true);
			$('input.permissionStaffRegistration').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionStaffRegistration').prop('checked',false);
			$('input.permissionStaffRegistration').parent().css({'color':''});
		}
	});
	
	
//StudentRegistration
	
	$('td .permissionStudentRegistration').change(function(){
		var cnt = 0;
		$('input.permissionStudentRegistration:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionStudentRegistration:checkbox').length;
		if(cnt==cn){
			$('#StudentRegistrationAll').prop('checked',true);
		}
		else{
			$('#StudentRegistrationAll').prop('checked',false);
		}
	});
	$('#StudentRegistrationAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionStudentRegistration').prop('checked',true);
			$('input.permissionStudentRegistration').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionStudentRegistration').prop('checked',false);
			$('input.permissionStudentRegistration').parent().css({'color':''});
		}
	});
	
//UploadAssignment
	
	$('td .permissionUploadAssignment').change(function(){
		var cnt = 0;
		$('input.permissionUploadAssignment:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionUploadAssignment:checkbox').length;
		if(cnt==cn){
			$('#UploadAssignmentAll').prop('checked',true);
		}
		else{
			$('#UploadAssignmentAll').prop('checked',false);
		}
	});
	$('#UploadAssignmentAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionUploadAssignment').prop('checked',true);
			$('input.permissionUploadAssignment').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionUploadAssignment').prop('checked',false);
			$('input.permissionUploadAssignment').parent().css({'color':''});
		}
	});
	
//StudentMarks
	
	$('td .permissionStudentMarks').change(function(){
		var cnt = 0;
		$('input.permissionStudentMarks:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionStudentMarks:checkbox').length;
		if(cnt==cn){
			$('#StudentMarksAll').prop('checked',true);
		}
		else{
			$('#StudentMarksAll').prop('checked',false);
		}
	});
	$('#StudentMarksAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionStudentMarks').prop('checked',true);
			$('input.permissionStudentMarks').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionStudentMarks').prop('checked',false);
			$('input.permissionStudentMarks').parent().css({'color':''});
		}
	});
	
	
//StudentInformation
	
	$('td .permissionStudentInformation').change(function(){
		var cnt = 0;
		$('input.permissionStudentInformation:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionStudentInformation:checkbox').length;
		if(cnt==cn){
			$('#StudentInformationAll').prop('checked',true);
		}
		else{
			$('#StudentInformationAll').prop('checked',false);
		}
	});
	$('#StudentInformationAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionStudentInformation').prop('checked',true);
			$('input.permissionStudentInformation').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionStudentInformation').prop('checked',false);
			$('input.permissionStudentInformation').parent().css({'color':''});
		}
	});
	
//AttendanceDetails
	
	$('td .permissionAttendanceDetails').change(function(){
		var cnt = 0;
		$('input.permissionAttendanceDetails:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionAttendanceDetails:checkbox').length;
		if(cnt==cn){
			$('#AttendanceDetailsAll').prop('checked',true);
		}
		else{
			$('#AttendanceDetailsAll').prop('checked',false);
		}
	});
	$('#AttendanceDetailsAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionAttendanceDetails').prop('checked',true);
			$('input.permissionAttendanceDetails').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionAttendanceDetails').prop('checked',false);
			$('input.permissionStudentInformation').parent().css({'color':''});
		}
	});
	
//AttendanceDetails
	
	$('td .permissionAttendanceDetails').change(function(){
		var cnt = 0;
		$('input.permissionAttendanceDetails:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionAttendanceDetails:checkbox').length;
		if(cnt==cn){
			$('#AttendanceDetailsAll').prop('checked',true);
		}
		else{
			$('#AttendanceDetailsAll').prop('checked',false);
		}
	});
	$('#AttendanceDetailsAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionAttendanceDetails').prop('checked',true);
			$('input.permissionAttendanceDetails').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionAttendanceDetails').prop('checked',false);
			$('input.permissionAttendanceDetails').parent().css({'color':''});
		}
	});
	
//AssignmentDetails
	
	$('td .permissionAssignmentDetails').change(function(){
		var cnt = 0;
		$('input.permissionAssignmentDetails:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionAssignmentDetails:checkbox').length;
		if(cnt==cn){
			$('#AssignmentDetailsAll').prop('checked',true);
		}
		else{
			$('#AssignmentDetailsAll').prop('checked',false);
		}
	});
	$('#AssignmentDetailsAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionAssignmentDetails').prop('checked',true);
			$('input.permissionAssignmentDetails').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionAssignmentDetails').prop('checked',false);
			$('input.permissionAssignmentDetails').parent().css({'color':''});
		}
	});
	
	
//SyllubusDetails
	
	$('td .permissionSyllubusDetails').change(function(){
		var cnt = 0;
		$('input.permissionSyllubusDetails:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionSyllubusDetails:checkbox').length;
		if(cnt==cn){
			$('#SyllubusDetailsAll').prop('checked',true);
		}
		else{
			$('#SyllubusDetailsAll').prop('checked',false);
		}
	});
	$('#SyllubusDetailsAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionSyllubusDetails').prop('checked',true);
			$('input.permissionSyllubusDetails').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionSyllubusDetails').prop('checked',false);
			$('input.permissionSyllubusDetails').parent().css({'color':''});
		}
	});
	
//FeedBack
	
	$('td .permissionFeedBack').change(function(){
		var cnt = 0;
		$('input.permissionFeedBack:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionFeedBack:checkbox').length;
		if(cnt==cn){
			$('#FeedBackAll').prop('checked',true);
		}
		else{
			$('#FeedBackAll').prop('checked',false);
		}
	});
	$('#FeedBackAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionFeedBack').prop('checked',true);
			$('input.permissionFeedBack').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionFeedBack').prop('checked',false);
			$('input.permissionFeedBack').parent().css({'color':''});
		}
	});
	
//StudentModule
	
	$('td .permissionStudentModule').change(function(){
		var cnt = 0;
		$('input.permissionStudentModule:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionStudentModule:checkbox').length;
		if(cnt==cn){
			$('#StudentModuleAll').prop('checked',true);
		}
		else{
			$('#StudentModuleAll').prop('checked',false);
		}
	});
	$('#StudentModuleAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionStudentModule').prop('checked',true);
			$('input.permissionStudentModule').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionStudentModule').prop('checked',false);
			$('input.permissionStudentModule').parent().css({'color':''});
		}
	});
	
	
//StudentAttendance
	
	$('td .permissionStudentAttendance').change(function(){
		var cnt = 0;
		$('input.permissionStudentAttendance:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionStudentAttendance:checkbox').length;
		if(cnt==cn){
			$('#StudentAttendanceAll').prop('checked',true);
		}
		else{
			$('#StudentAttendanceAll').prop('checked',false);
		}
	});
	$('#StudentAttendanceAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionStudentAttendance').prop('checked',true);
			$('input.permissionStudentAttendance').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionStudentAttendance').prop('checked',false);
			$('input.permissionStudentAttendance').parent().css({'color':''});
		}
	});
	
	
//TransportModule
	
	$('td .permissionTransportModule').change(function(){
		var cnt = 0;
		$('input.permissionTransportModule:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionTransportModule:checkbox').length;
		if(cnt==cn){
			$('#TransportModuleAll').prop('checked',true);
		}
		else{
			$('#TransportModuleAll').prop('checked',false);
		}
	});
	$('#TransportModuleAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionTransportModule').prop('checked',true);
			$('input.permissionTransportModule').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionTransportModule').prop('checked',false);
			$('input.permissionTransportModule').parent().css({'color':''});
		}
	});
	
	

	
	
	//
	
	$('td .permissionRolePermissionMapping').change(function(){
		var cnt = 0;
		$('input.permissionRolePermissionMapping:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionRolePermissionMapping:checkbox').length;
		if(cnt==cn){
			$('#RolePermissionMappingAll').prop('checked',true);
		}
		else{
			$('#RolePermissionMappingAll').prop('checked',false);
		}
	});
	$('#RolePermissionMappingAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionRolePermissionMapping').prop('checked',true);
			$('input.permissionRolePermissionMapping').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionRolePermissionMapping').prop('checked',false);
			$('input.permissionRolePermissionMapping').parent().css({'color':''});
		}
	});
	
	
	


	
	$('#leaveall').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionleave').prop('checked',true);
			$('input.permissionleave').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionleave').prop('checked',false);
			$('input.permissionleave').parent().css({'color':''});
		}
	});
	

	
	$('input.permission').change(function(){
		if($(this).is(':checked')){
			$(this).parent().css({'color':'blue'});
		}
		else{
			$(this).parent().css({'color':''});
		}
	});
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Library Book Master
	
	$('td .permissionBookMaster').change(function(){
		var cnt = 0;
		$('input.permissionBookMaster:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionBookMaster:checkbox').length;
		if(cnt==cn){
			$('#BookMasterAll').prop('checked',true);
		}
		else{
			$('#BookMasterAll').prop('checked',false);
		}
	});
	$('#BookMasterAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionBookMaster').prop('checked',true);
			$('input.permissionBookMaster').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionBookMaster').prop('checked',false);
			$('input.permissionBookMaster').parent().css({'color':''});
		}
	});
	
	
//Library Issue Details	
	
	$('td .permissionBookIssueDetails').change(function(){
		var cnt = 0;
		$('input.permissionBookIssueDetails:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionBookIssueDetails:checkbox').length;
		if(cnt==cn){
			$('#BookIssueDetailsAll').prop('checked',true);
		}
		else{
			$('#BookIssueDetailsAll').prop('checked',false);
		}
	});
	$('#BookIssueDetailsAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionBookIssueDetails').prop('checked',true);
			$('input.permissionBookIssueDetails').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionBookIssueDetails').prop('checked',false);
			$('input.permissionBookIssueDetails').parent().css({'color':''});
		}
	});
	
	//Library Fee Collection
	
	$('td .permissionBookFeeCollection').change(function(){
		var cnt = 0;
		$('input.permissionBookFeeCollection:checkbox:checked').map(function() {
			++cnt;
		});
		var cn = $('input.permissionBookFeeCollection:checkbox').length;
		if(cnt==cn){
			$('#BookFeeCollectionAll').prop('checked',true);
		}
		else{
			$('#BookFeeCollectionAll').prop('checked',false);
		}
	});
	$('#BookFeeCollectionAll').change(function(){
		var bCheck = $(this).is(':checked');
		if(bCheck){
			$('input.permissionBookFeeCollection').prop('checked',true);
			$('input.permissionBookFeeCollection').parent().css({'color':'blue'});
		}
		else{
			$('input.permissionBookFeeCollection').prop('checked',false);
			$('input.permissionBookFeeCollection').parent().css({'color':''});
		}
	});
	
	
	
	
	$("#closepre").click(function(){
		  location.reload();
	});
	
	$("#closePreview").click(function(){
		
		$(".modal").hide();
	
	});
	
	$("#PreviewPermission").click(function(){
		
		var roleCode=$('#roleId').val();
		if(roleCode==""){
			
				$(".errormessagediv").show();
				$(".validateTips")
				.text(
						"Please Select Role");
				/*$(".error").text("Please Select Role");*/
				$("#roleId").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
			    setTimeout(function() {$('.errormessagediv').fadeOut();}, 2000);
				return false;
				
				
				
				
				
		}
		else{
		$(".errormessagediv").hide();
		$("#roleId").css({'backgroundColor' : 'transparent','border':'1px solid #ccc'});
		
		
		
		$(".modal").show();
		$(".modal #allstudent tbody tr").hide();
		$(".modal #allstudent tbody tr:first").show();
		$("#allstudents tbody tr td").each(function(){
		var visibleRow=$(this).find("input[type='checkbox']:checked").attr("id")+"_base";
		if(visibleRow!=undefined){
			$(".modal #allstudent #"+visibleRow.split("_")[0]).parent("td").parent("tr").show();
			$(".modal #allstudent #"+visibleRow.split("_")[0]).prop("checked",true);	
		}
		});
		$(".modal").css({
			'height':($(".modal #allstudent").height()+150)+'px'
			
		});
		if($(".modal #allstudent").height()>=500){
			$(".modal").css({
				'overflow-y':'scroll'
			});
		}
		}
	});
	$('#savePermission').click(function(){
		/*var roleCode=$('#roleId').val();
		if(roleCode==""){
			
				$("#errormessagediv").show();
				$(".error").text("Please Select Role");
				$("#roleId").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
			    setTimeout(function() {$('#errorhover').fadeOut();}, 3000);
				return false;
		}
	
		$("#errormessagediv").hide();
		$("#roleId").css({'backgroundColor' : 'transparent','border':'1px solid #ccc'});*/
		
		
	var permissionCode=[];
	var permissionShortName=[];
	var isPermissionAllowed=[];
	
	$('.modal .permission').each(function(){
		
		permissionCode.push($(this).prop('id'));
		permissionShortName.push($(this).prop('name'));
		var isAllowed = false;
		if($(this).is(':checked')){
		
			isAllowed=true;
		}
		isPermissionAllowed.push(isAllowed);
		
		
	});
	
	
	//sending selected data to database
	//
	
	var dataArray={"roleCode":$('#roleId').val(),"permissionCode":permissionCode.join(),"permissionShortName":permissionShortName.join(),"isPermissionAllowed":isPermissionAllowed.join()};
	
	
	$.ajax({
			type:"POST",
			url:"userPermission.html?method=insertRolePermission",
			data:dataArray,
			async:false,
			success:function(response){
				var result =$.parseJSON(response);
				
				$(".successmessagediv").show();
				$(".validateTips").text(result.status);
				
				setTimeout(function(){
					
					 window.location.href="adminMenu.html?method=getUserRolePermission";
				 
				 },2000);
				
			}
		});
	
	});
	
	
	//Getting permissions respect to the role
	
	
	$('#roleId').change(function(){

		$('.permission').prop('checked',false);
		$('.permission').parent().css({'color':''});
		$('#StudentAll').prop('checked',false);
		$('#StudentPromotionAll').prop('checked',false);
		$('#StudentEnquiryAll').prop('checked',false);
		$('#StreamAll').prop('checked',false);
		$('#RoleAll').prop('checked',false);
		$('#DepartmentAll').prop('checked',false);
		$('#ClassAll').prop('checked',false);
		$('#DesignationAll').prop('checked',false);
		$('#SectionAll').prop('checked',false);
		$('#leaveall').prop('checked',false);
		$('#QuotaAll').prop('checked',false);
		$('#DriverAll').prop('checked',false);
		$('#RouteAll').prop('checked',false);
		$('#VehicleAll').prop('checked',false);
		$('#SubjectAll').prop('checked',false);
		$('#AcademicYearAll').prop('checked',false);
		$('#RegistrationAll').prop('checked',false);
		$('#EmployementAll').prop('checked',false);
		$('#AttendanceAll').prop('checked',false);
		$('#PayrollGenerationAll').prop('checked',false);
		$('#FeeMasterAll').prop('checked',false);
		$('#TermMasterAll').prop('checked',false);
		$('#ClassFeeSetupAll').prop('checked',false);
		$('#StageFeeSetupAll').prop('checked',false);
		$('#FeeCollectionAll').prop('checked',false);
		$('#ExamDetailsAll').prop('checked',false);
		$('#ExamTimeTableAll').prop('checked',false);
		$('#FuelMaintenanceAll').prop('checked',false);
		$('#ViewEaxmDetailsAll').prop('checked',false);
		$('#ConcessionDetailsAll').prop('checked',false);
		$('#TransportTypeAll').prop('checked',false);
		$('#RolePermissionMappingAll').prop('checked',false);
		$('#ExamModuleAll').prop('checked',false);
		$('#FeeModuleAll').prop('checked',false);
		$('#LeaveRequestAll').prop('checked',false);
		$('#LeaveApprovelAll').prop('checked',false);
		$('#LeaveModuleAll').prop('checked',false);
		$('#ReportModuleAll').prop('checked',false);
		$('#CommunicationSettingsAll').prop('checked',false);
		$('#InternalJobPostingAll').prop('checked',false);
		$('#AccademicYearPlanAll').prop('checked',false);
		$('#PasswordMaintenanceAll').prop('checked',false);
		$('#SetupModuleAll').prop('checked',false);
		$('#ClassTimeTableAll').prop('checked',false);
		$('#StaffAttendanceAll').prop('checked',false);
		$('#StaffModuleAll').prop('checked',false);
		$('#StaffRegistrationAll').prop('checked',false);
		$('#StudentRegistrationAll').prop('checked',false);
		$('#UploadAssignmentAll').prop('checked',false);
		$('#StudentMarksAll').prop('checked',false);
		$('#StudentInformation').prop('checked',false);
		$('AttendanceDetailsnAll').prop('checked',false);
		$('AssignmentDetailsnAll').prop('checked',false);
		$('SyllubusDetailsnAll').prop('checked',false);
		$('FeedBacknAll').prop('checked',false);
		$('StudentModulenAll').prop('checked',false);
		$('StudentAttendancenAll').prop('checked',false);
		$('TransportModulenAll').prop('checked',false);
		$('#BookMasterAll').prop('checked',false);
		$('#BookIssueDetailsAll').prop('checked',false);
		$('#BookFeeCollectionAll').prop('checked',false);



		$('#StageAll').prop('checked',false);
		$.ajax({
			url:"userPermission.html?method=getPermissionByRole",
			data:{"roleCode":$(this).val()},
			async:false,
			success:function(data){
				var result = JSON.parse(data);
				
				
				for(var i=0;i<result.permission.length;i++){
					var id=result.permission[i].permissionId;
					
					
					var isApplicable = result.permission[i].permissionIsApplicable;
				
					if(isApplicable=="true"){
					
						$('.col-md-12 #'+id+"_base").prop('checked',true);
						$('.col-md-12 #'+id+"_base").parent().css({'color':'blue'});
					}
					else{
						$('#'+id).prop('checked',false);
						$('#'+id).parent().css({'color':''});
					}
				}
			}
		});
	});
	
	
	//Check all checkboxes if we select select all checkbox
	
	
	$('#selectAll').change(function(){
		
		if($(this).is(':checked')){
			
			$("#allstudents input[type='checkbox']").prop('checked',true);

		}
		else{
			$("#allstudents input[type='checkbox']").prop('checked',false);
		}
	});
	$(".submoduleCheck").change(function(){
			if($(this).is(':checked')){
			
				$(this).closest("tr").find("input[type='checkbox']").prop('checked',true);

		}
		else{
			$(this).closest("tr").find("input[type='checkbox']").prop('checked',false);
		}
	});
	$("input[type='checkbox']").change(function(){
		if($("#allstudents input[type='checkbox']").length==$('#allstudents input:checked').length){
			$('#selectAll').prop("checked",true);
		}
		else{
			$('#selectAll').prop("checked",false);
		}
		
	});
	
		$(".submoduleCheck").closest("tr").find("input[type='checkbox']").change(function(){
			
		if($(this).closest("tr").find("input[type='checkbox']").not(".submoduleCheck").length==$(this).closest("tr").find('input:checked').not(".submoduleCheck").length){
			$(this).closest("tr").find(".submoduleCheck").prop("checked",true);
			if($("#allstudents input[type='checkbox']").length==$('#allstudents input:checked').length){
				$('#selectAll').prop("checked",true);
			}
			else{
				$('#selectAll').prop("checked",false);
			}
		}
		else{
			$(this).closest("tr").find(".submoduleCheck").prop("checked",false);
		}
	});
	
	
	$('.removePermission').click(function(){
		var roleCode=$('#roleId').val();
		if(roleCode=="All"){
			$('#errordiv').text("Please Select Role");
			$('#errordiv').css({'visibility': 'visible'});
			return false;
		}
		
		$.ajax({
			url:"userPermission.do?method=removePermission",
			data:{"roleCode":roleCode},
			async:false,
			success:function(data){
				location.reload();
			}
		});
	
	});
	
});









