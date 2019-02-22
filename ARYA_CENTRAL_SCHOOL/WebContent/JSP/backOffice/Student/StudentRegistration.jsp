<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<script type="text/javascript" src="JS/common.js"></script>

<script type="text/javascript" src="JS/backOffice/Student/StudentRegistration.js"></script>



<style>
.form-group {
	margin-bottom: 5px;
}

.save:hover {
	cursor: pointer;
}

fieldset {
	width: 512px;
	display: block;
	/*  margin-left: auto;
    margin-right: 2px; */
	margin-bottom: 5px;
	padding-bottom: 0.625em;
	padding: 7px;
	padding-right: 0px;
	border: 0.5px solid #ccc;
}

legend {
	display: inline-block;
	width: auto;
	padding: 0;
	margin-bottom: 0px;
	font-size: 16px;
	line-height: inherit;
	color: #333;
	border: 0;
}
</style>
<style>
.buttons {
	vertical-align: -5px;
}

.navbar-right {
	top: -3px;
}

.panel-primary>.panel-heading {
	margin-bottom: 0px;
}

form .panel.panel-primary.panel-list {
	padding-bottom: 0px;
}

#admissionstudent thead {
	border: 1px solid #dedede;
}

@media ( min-width :320px) and (max-width:767px) {
	br {
		display: none;
	}
}
</style>
</head>

<body>
<div class="container" id='container' style="display: none;">
	<div class='row'>
		<div class='col-md-12'>
			<div class='tabledetails'>
				<div class='row'>
					<div class='col-md-12'>
						<div class='firstpage'>
							<div class="row">
								<div class='col-md-12'>
									<div class='headings'>
										<h2 class='forkg' style="text-align: center; margin: 0; margin-top: 5px; font-family: Algerian;">ARYA
												SMART KIDS</h2>
												
											<h2 class='notForKg' style="text-align: center; margin: 0; margin-top: 5px; font-family: Algerian;">ARYA CENTRAL SCHOOL
											</h2>
										<p class='schoolheading forkg'>KINDERGARTEN</p>
										<p style="text-align: center; font-weight: 600">Aryakumar
												Ashram Trust Pattom, Thiruvananthapuram 4</p>
									</div>
									<div class='subheadings'>
										<p>APPLICATION FOR ADMISSION</p>
									</div>
								</div>
							</div>
							<div class='row'>
								<div class='col-md-12'>
									<div class='tablecontent'>
										<div class='row'>
											<div class="col-md-12">
												<div style="margin-top: 5px;">
													<div class='col-md-3'>
														<table class='toptables'>
															<tr>
																<th style="width: 150px; text-align: center;">Admission
																		No</th>
															</tr>
															<tr>
																<td style="text-align: center;width:150px;height: 25px;"></td>
															</tr>
														</table>
													</div>
													<div class='col-md-6'>
														<div>
															<table class='toptables'>
																<tr style="height: 45px;">
																	<th style="width: 165px; text-align: center;">Class
																			to which Admission is sought</th>
																	<td style="width: 75px;left;padding:5px;font-size:13px;" class="className"><logic:present name='studentinformation'><bean:write name='studentinformation' property="className"/></logic:present></td>
																</tr>
															</table>
														</div>
													</div>
													<div class='col-md-3'>
														<div class='photostyle'>
														<img height="90px" width="90px" class="stuimg" src=''>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class='row'>
											<div class='col-md-12'>
												<div class='studetails'>
													<div class='col-md-12'>
														<table class="toptables" width="100%">
															<tr style="height: 25px">
																<th style="text-align: center; width: 30px;font-size:13px;">1.</th>
																<th style="text-align: center; width: 170px;font-size:13px;">Name
																		of Pupil</th>
																<td style="width: 375px; left;padding:5px;font-size:13px;"><span class='stuName'></span></td>
															</tr>
														</table>
													</div>
													<div class='col-md-12' style="margin-top: 5px;">
														<table class="toptables" width="100%">
															<tr style="height: 25px">
																<th rowspan="2"
																		style="text-align: center; width: 34px;font-size:13px;">2.</th>
																<th rowspan="2"
																		style="text-align: center; width: 120px;font-size:13px;">Date of
																		Birth</th>
																<th style="width: 100px; text-align: center;font-size:13px;">In
																		figure</th>
																<th style="width: 280px; text-align: center;font-size:13px;">In
																		Words</th>
															</tr>
															<tr style="height: 25px">
																<td style="width: 120px; left;padding:5px;font-size:13px;" class="dobf"></td>
																<td style="width: 280px; left;padding:5px;font-size:13px;" class="dobw"></td>
															</tr>
														</table>
													</div>
													<div class='col-md-12' style="margin-top: 5px;">
														<table width="100%">
															<tr>
																<th rowspan="2"
																		style="width: 39px; text-align: center;font-size:13px;">3.</th>
																<th rowspan="2" style="width: 70px;text-align: center;font-size:13px;">Sex</th>
																<td rowspan="2"
																		style="width: 126px; left;padding:5px;font-size:13px;" class="gender"></td>
																<th rowspan="2"
																		style="width: 40px;font-size:13px; text-align: center;font-size:13px;">4.</th>
																<th rowspan="2"
																		style="width: 315px; text-align: center;font-size:13px;">Age as
																		on 1<sup>st</sup> June of Academic Session
																</th>
																
															</tr>
															<tr>
																<td style="left;padding:5px;font-size:13px;" class="ageason"></td>
															</tr>
														</table>
													</div>
													<div class='col-md-12' style="margin-top: 5px;">
														<table width="100%">
															<tr style="height: 25px;">
																<th style="text-align: center; width: 39px;font-size:13px;">5.</th>
																<th style="text-align: center;font-size:13px;">Religion</th>
																<td style="width: 158px; left;padding:5px;font-size:13px;" class="religion"></td>
																<th style="text-align: center; width: 40px;font-size:13px;">6.</th>
																<th style="text-align: center;font-size:13px;">Caste</th>
																<td style="width: 75px; left;padding:5px;font-size:13px;" class="casteName"></td>
																<th style="text-align: center; width: 40px;font-size:13px;">7.</th>
																<th style="text-align: center;font-size:13px;">Nationality</th>
																<td style="left;padding:5px;font-size:13px;" class="nationality"></td>
															</tr>
														</table>
													</div>
													<div class="col-md-12" style="margin-top: 5px;">
														<table width="100%">
															<tr style="height: 25px;">
																<th style="text-align: center; width: 39px;font-size:13px;">8.</th>
																<th style="text-align: center; width: 250px;font-size:13px;">Whether
																		Belonging to SC/ST/OBC</th>
																<td style="left;padding:5px;font-size:13px;width: 75px;" class="isscst"></td>
																<th style="text-align: center; width: 40px;font-size:13px;">9.</th>
																<th style="text-align: center; width: 110px;font-size:13px;">Mother
																		Tongue</th>
																<td style="left;padding:5px;font-size:13px;" class="mottongue"></td>
															</tr>
														</table>
													</div>
													<div class='col-md-12'>
														<div class="parentsdetails">
															<p>DETAILS OF PARENTS</p>
														</div>
													</div>
													<div class="col-md-12" style="margin-top: 5px;">
														<table width="100%">
															<tr style="height: 25px;">
																<th style="width: 250px;"></th>
																<th style="text-align: center; width: 242px;font-size:13px;">Father</th>
																<th style="text-align: center;font-size:13px;">Mother</th>
															</tr>
														</table>
													</div>
													<div class="col-md-12" style="margin-top: 5px;">
														<table width="100%">
															<tr style="height: 25px;">
																<th style="text-align: center; width: 40px;font-size:13px;">10.</th>
																<th style="text-align: left;padding:5px;font-size:13px; width: 210px;">Name</th>
																<td style="left;padding:5px;font-size:13px; width: 242px;" class="fatname"></td>
																<td style="left;padding:5px;font-size:13px;" class="motName"></td>
															</tr>
														</table>
													</div>
													<div class="col-md-12" style="margin-top: 5px;">
														<table width="100%">
															<tr style="height: 40px;">
																<th style="text-align: center; width: 40px;font-size:13px;">11.</th>
																<th style="text-align: left;padding:5px;font-size:13px; width: 210px;">Occupation
																		& Designation</th>
																<td style="left;padding:5px;font-size:13px; width: 242px;"><span class="fatOccu"></span><br /><span class="fatdes"></span></td>
																<td style="left;padding:5px;font-size:13px;"><span class="motOccu"></span><br /><span class="motdes"></span></td>
															</tr>
														</table>
													</div>
													<div class="col-md-12" style="margin-top: 5px;">
														<table width="100%">
															<tr style="height: 80px;">
																<th style="text-align: center; width: 40px;font-size:13px;">12.</th>
																<th style="text-align: left;padding:5px;font-size:13px; width: 210px;">Official
																		Address</th>
																<td style="left;padding:5px;font-size:13px; width: 242px;" class="fathoffiaddr"></td>
																<td style="left;padding:5px;font-size:13px;" class="motoffiaddr"></td>
															</tr>
														</table>
													</div>
													<div class="col-md-12" style="margin-top: 5px;">
														<table width="100%">
															<tr style="height: 25px;">
																<th style="text-align: center; width: 40px;font-size:13px;">13.</th>
																<th style="text-align: left;padding:5px;font-size:13px; width: 210px;">Land
																		Line No.</th>
																<td style="left;padding:5px;font-size:13px; width: 242px;" class="father_office_landline"></td>
																<td style="left;padding:5px;font-size:13px;" class="mother_office_landline"></td>
															</tr>
														</table>
													</div>
													<div class="col-md-12" style="margin-top: 5px;">
														<table width="100%">
															<tr style="height: 25px;">
																<th style="text-align: center; width: 40px;font-size:13px;">14.</th>
																<th style="text-align: left;padding:5px;font-size:13px; width: 210px;">Monthly
																		Income</th>
																<td style="left;padding:5px;font-size:13px; width: 242px;" class="fatincome"></td>
																<td style="left;padding:5px;font-size:13px;" class="motincome"></td>
															</tr>
														</table>
													</div>
													<div class="col-md-12" style="margin-top: 5px;">
														<table width="100%">
															<tr style="height: 100px;">
																<th style="text-align: center; width: 40px;font-size:13px;">15.</th>
																<th style="text-align: left;padding:5px;font-size:13px; width: 210px;"><span>Permanant
																			Residental</span><br />Address</th>
																<td style="left;padding:5px;font-size:13px; width: 242px;" class="peraddr"></td>
																<td style="left;padding:5px;font-size:13px;" class="peraddr"></td>
															</tr>
														</table>
													</div>
													<div class="col-md-12" style="margin-top: 5px;">
														<table width="100%">
															<tr style="height: 25px;">
																<th style="text-align: center; width: 40px;font-size:13px;">16.</th>
																<th style="text-align: left;padding:5px;font-size:13px; width: 210px;">Land
																		Line No.</th>
																<td style="left;padding:5px;font-size:13px; width: 242px;" class="landlineNo"></td>
																<td style="left;padding:5px;font-size:13px;"></td>
															</tr>
														</table>
													</div>
													<div class="col-md-12" style="margin-top: 5px;">
														<table width="100%">
															<tr style="height: 40px;">
																<th style="text-align: center; width: 40px;font-size:13px;">17.</th>
																<th style="text-align: left;padding:5px;font-size:13px; width: 210px;">Mobile
																		No.</th>
																<td style="left;padding:5px;font-size:13px; width: 242px;" class="fathphone"></td>
																<td style="left;padding:5px;font-size:13px;" class="motphone"></td>
															</tr>
														</table>
													</div>
													<div class="col-md-12" style="margin-top: 5px;">
														<table width="100%">
															<tr style="height: 25px;">
																<th style="text-align: center; width: 40px;font-size:13px;">18.</th>
																<th style="text-align: left;padding:5px;font-size:13px; width: 210px;">SMS
																		No.</th>
																<td style="left;padding:5px;font-size:13px; width: 242px;" class="smsNo"></td>
																<td style="left;padding:5px;font-size:13px;" class="smsNo"></td>
															</tr>
														</table>
													</div>
													<div class="col-md-12" style="margin-top: 5px;">
														<table width="100%">
															<tr style="height: 25px;">
																<th style="text-align: center; width: 40px;font-size:13px;">19.</th>
																<th style="text-align: left;padding:5px;font-size:13px; width: 210px;">Emergency
																		Mobile No.</th>
																<td style="left;padding:5px;font-size:13px; width: 242px;" class="emergencyNo"></td>
																<td style="left;padding:5px;font-size:13px;" class="emergencyNo"></td>
															</tr>
														</table>
													</div>
													<div class="col-md-12" style="margin-top: 5px;">
														<table width="100%">
															<tr style="height: 100px;">
																<th style="text-align: center; width: 40px;font-size:13px;">20.</th>
																<th style="text-align: left;padding:5px;font-size:13px; width: 210px;">Present
																		Address<br /> [At the time of Admission]
																</th>
																<td style="padding-left:5px;font-size:13px; width: 242px;" class="preaddr"></td>
																<td style="left;padding:5px;font-size:13px;font-size:13px;" class="preaddr"></td>
															</tr>
														</table>
													</div>
													<div class="col-md-12"
															style="margin-top: 5px; margin-bottom: 10px;">
														<table width="100%">
															<tr style="height: 25px;">
																<th style="text-align: center; width: 40px;font-size:13px;">21.</th>
																<th style="text-align: left;padding:5px;font-size:13px; width: 210px;">Land
																		Line No.</th>
																<td style="left;padding:5px;font-size:13px; width: 242px;margin-bottom:20px;" class="fatPreAddLandLiNo"></td>
																<td style="left;padding:5px;font-size:13px;" class="mothPreAddLandLiNo"></td>
															</tr>
														</table>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class='row notForSenior'>
					<div class='col-md-12'>
						<div class="declaration">
							<p>I have read the entire date typed above(typed by me) and
									verified it.I here by declare that the details furnished
									above are true to the best of my knowledge.</p>
							<p style="text-align: right; padding-top: 20px;">Signature
									of Parent or Guardian</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div
								style="width: 760px; height: 60px; margin: auto; margin-top: 5px; border: 1px solid #000000; border-radius: 5px;">
							<div class="col-md-12" style="margin-top: 8px;">
								<table width="100%">
									<tr style="height: 25px;">
										<th style="text-align: left;padding:5px;font-size:13px; width: 40px;font-size:13px;">22.</th>
										<th class="forkg" style="text-align: center; width: 210px;">Name of
												the School/<br />Kindergarten last studied
										</th>
										<th class="notForKg" style="text-align: center; width: 210px;">Name of
												the School last studied
										</th>
										<td style="left;padding:5px;font-size:13px; width: 242px;" class="lastKindergartenName"></td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
		<div class="row">
					<div class="col-md-12">
						<div
								style="width: 760px; height: 60px; margin: auto; margin-top: 5px; border: 1px solid #000000; border-radius: 5px;">
							<div class="col-md-12" style="margin-top: 8px;">
								<table width="100%">
									<tr style="height: 25px;">
										<th style="text-align: center; width: 40px;font-size:13px;">23.</th>
										<th style="text-align: left;padding:5px; width: 210px;font-size:13px;">Adhar No.
										</th>
										<td class="aadharNo" style="padding-left: 5px; width: 242px;font-size:13px;"></td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
					
			<div class="row forschool">
				<div class="col-md-12">
						<div
								style="width: 760px; height: 60px; margin: auto; margin-top: 5px; border: 1px solid #000000; border-radius: 5px;">
							<div class="col-md-12" style="margin-top: 8px;">
								<table width="100%">
									<tr style="height: 25px;">
										<th style="text-align: center; width: 40px;font-size:13px;">24.</th>
										<th style="text-align: left;padding:5px; width: 210px;font-size:13px;">Second Langauge
										</th>
										<td class="secondLanguage" style="padding-left: 5px; width: 242px;font-size:13px;"></td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
					
				<div class="row forschool">
				<div class="col-md-12">
						<div
								style="width: 760px; height: 60px; margin: auto; margin-top: 5px; border: 1px solid #000000; border-radius: 5px;">
							<div class="col-md-12" style="margin-top: 8px;">
								<table width="100%">
									<tr style="height: 25px;">
										<th style="text-align: center; width: 40px;font-size:13px;">25.</th>
										<th style="text-align: left;padding:5px; width: 210px;font-size:13px;">Third Langauge
										</th>
										<td class="thirdLanguage" style="padding-left: 5px; width: 242px;font-size:13px;" ></td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
					
				<div class="row forSenior">
					<div class="col-md-12">
						<div
								style="width: 760px; height: 60px; margin: auto; margin-top: 5px; border: 1px solid #000000; border-radius: 5px;">
							<div class="col-md-12" style="margin-top: 8px;">
								<table width="100%">
									<tr style="height: 25px;">
										<th style="text-align: center; width: 40px;font-size:13px;">24.</th>
										<th style="text-align: left;padding:5px; width: 210px;font-size:13px;">Scheme of Study: (SSLC/faces/OSSE/Any Other)
										</th>
										<td class="schemeofstudy" style="padding-left: 5px; width: 242px;font-size:13px;"></td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
				<div class="row forSenior">
					<div class="col-md-12">
						<div
								style="width: 760px; height: 60px; margin: auto; margin-top: 5px; border: 1px solid #000000; border-radius: 5px;">
							<div class="col-md-12" style="margin-top: 8px;">
								<table width="100%">
									<tr style="height: 25px;">
										<th style="text-align: center; width: 40px;font-size:13px;">25.</th>
										<th style="text-align: left;padding:5px; width: 210px;font-size:13px;">No. sad date of Transfer Certificate attached
										</th>
										<td class="istc" style="padding-left: 5px; width: 242px;font-size:13px;"></td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
					
				<div class="row forSenior">
					<div class="col-md-12">
						<div
								style="width: 760px; height: 60px; margin: auto; margin-top: 5px; border: 1px solid #000000; border-radius: 5px;">
							<div class="col-md-12" style="margin-top: 8px;">
								<table width="100%">
									<tr style="height: 25px;">
										<th style="text-align: center; width: 40px;font-size:13px;">26.</th>
										<th style="text-align: left;padding:5px; width: 210px;font-size:13px;">No. sad date of Migration Certificate attached
										</th>
										<td class="isMigration" style="padding-left: 5px; width: 242px;font-size:13px;"></td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
					
					
				<div class="row forSenior">
					<div class="col-md-12">
						<div
								style="width: 760px; height: 60px; margin: auto; margin-top: 5px; border: 1px solid #000000; border-radius: 5px;">
							<div class="col-md-12" style="margin-top: 8px;">
								<table width="100%">
									<tr style="height: 25px;">
										<th style="text-align: center; width: 40px;font-size:13px;">27.</th>
										<th style="text-align: left;padding:5px; width: 210px;font-size:13px;">Group/ Subjects Preferred
										</th>
										<td class="opt_subjects" style="padding-left: 5px; width: 242px;font-size:13px;"></td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
					
				<div class="row forSenior">
					<div class="col-md-12">
						<div
								style="width: 760px; height: 60px; margin: auto; margin-top: 5px; border: 1px solid #000000; border-radius: 5px;">
							<div class="col-md-12">
								<table width="100%">
									<tr style="height: 25px;">
										<th style="text-align: center; width: 40px;font-size:13px;">28.</th>
										<th style="text-align: left;padding:5px; width: 210px;font-size:13px;">Percentage of Marks obtained in Qualifying Exam 
										</th>
										<td class="percentageobtained" style="padding-left: 5px; width: 242px;font-size:13px;">
										<br/>(Aggregate of English, 2nd Language, Science, Math & Social Studies only)
										</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
					
				<div class='row'>
					<div class='col-md-12'>
					<div class="studentdeclaration"
								style="width: 760px; margin: auto; margin-top: 5px;">
							<div class='declarationheading'>
								<p
										style="text-align: center; font-weight: 600; font-size: 16px; margin: 3px;">Declaration of the candidate</p>
							</div>
							<div>
								<p
										style="left;padding:5px;font-size:13px;margin:0; margin-top: 5px; padding-right: 2px;">1)&nbsp;I declare that all the foregoing statements made in this application are true.
										I accept that any statement made in this application if found incorrect on scrutiny, will render the application liable for rejection and admission, if granted on basis of such incorrect information, will stand cancelled.
</p>
								<p style="left;padding:5px;font-size:13px;margin:0;margin-top: 5px;">2)&nbsp;I sincerely assure that, if admitted, I will strictly adhere to the rule and regulations that may be adopted by the school from time to time and will abide by the rules of discipline of the school.</p>
								<p style="left;padding:5px;font-size:13px;margin:0;margin-top: 5px; padding-right: 2px;">3)&nbsp;I agree to abide by the decisions of the Principal of the school for any misconduct or misbehavior or breach of rules by me during the entire period of my study.</p>
									
								<p
										style="left;padding:5px;font-size:13px; padding-right: 2px;margin:0;margin-top: 5px; font-family: Times New Roman; font-size: 15px; font-weight: 600;">I
										have read the entire data typed and verified
										it. I hereby declare that the details furnished above are
										true.</p>
								<p
										style="left;padding:5px;font-size:13px;margin:0;margin-top: 5px; font-weight: 600; font-size: 15px;">
										Date : ...................</p>
								<p
										style="left;padding:5px;font-size:13px;margin:0;margin-top: 5px; font-weight: 600; font-size: 15px;">
									<span>Place :</span> <span
											style="float: right; padding-right: 5px;">Signature of
											Canditate</span>
								</p>
							</div>
						</div>
						
						
						
						<div class="parentdeclaration"
								style="width: 760px; margin: auto; margin-top: 5px;">
							<div class='declarationheading'>
								<p
										style="text-align: center; font-weight: 600; font-size: 16px; margin: 3px;">Declaration
										of the parent/guardian</p>
							</div>
							<div>
								<p
										style="left;padding:5px;font-size:13px;margin:0; margin-top: 5px; padding-right: 2px;">1)&nbsp;I
										hereby declare that the 'Date of Birth' in respect of my
										Son/Daughter : <span style="width: 375px; left;padding:5px;font-size:13px;text-decoration:underline;" class="stuName"></span>furnished by me in
										item No. 2 is correct and that I would not demand any change
										in it at any date. I accept that any statement made in the
										application, if found incorrect on scrutiny, will render the
										application of my son/daughter/ward liable for rejection and
										the admission, if granted on the basis of such incorrect
										information will stand cancelled.</p>
								<p style="left;padding:5px;font-size:13px;margin:0; margin-top: 5px;">2)&nbsp;I shall be
										responsible for his/her conduct,good behaviour and compliance
										with the rules in force from time to time during the entire
										period of his/her study.</p>
								<p style="left;padding:5px;font-size:13px; padding-right: 2px;margin:0; margin-top: 5px;">3)&nbspI
										promise to abide by any decision taken by the Principal for
										any misconduct or misbehaviour or breach of rules by my
										son/daughter/ward during the entire period of his/her course.
										I shall also hold myself responsible and compensate for any
										damages caused by my son/daughter/ward in the school.</p>
								<p style="left;padding:5px;font-size:13px; padding-right: 2px;margin:0; margin-top: 5px;" class="notForSenior">PS: The
										school provides transport facilities but offers no guarantee
										that a seat in the school bus will be available when the buses
										are full to capacity/do not ply in the area of your residence.
										It will be the responsibility of the parents/guardian to
										drop/collect the child from the school or from the specified bus stop as applicable.</p>
								<p
										style="left;padding:5px;font-size:13px;margin:0; margin-top: 5px; padding-right: 2px; font-family: Times New Roman; font-size: 15px; font-weight: 600;">I
										have read the entire data typed and verified
										it. I hereby declare that the details furnished above are
										true.</p>
								<p
										style="left;padding:5px;font-size:13px;margin:0; margin-top: 5px; font-weight: 600; font-size: 15px;">
										Date : ...................</p>
								<p
										style="left;padding:5px;font-size:13px; font-weight: 600; font-size: 15px;margin:0; margin-top: 5px;">
									<span>Place :</span> <span
											style="float: right; padding-right: 5px;">Signature of
											Parent/Guardian</span>
								</p>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class='col-md-12'>
						<div class="office"
								style="width: 760px; margin: auto; margin-top: 5px;">
							<div class='officeuse'>
								<p
										style="text-align: center; font-weight: 600; font-size: 16px; margin: 3px;">FOR
										OFFICE USE</p>
							</div>
							<div class='officewraP' style="width: 746px; margin-left: 5px;">
								<div
										style="border: 1px solid black; height: 75px; margin-top: 10px; border-radius: 5px;">
									<div class="col-md-6" style="margin-top: 15px;">
										<p>Verified the data furnished</p>
									</div>
									<div class="col-md-6"
											style="margin-top: 10px; text-align: left;">
										<p style="margin-left: 15px;">Name :</p>
										<p style="margin-left: 15px;">Signature :</p>
									</div>
								</div>
								<div
										style="border: 1px solid black; height: 100px; margin-top: 10px; border-radius: 5px;">
									<div class="col-md-6" style="margin-top: 10px;">
										<p>Admit to class</p>
									</div>
									<div class="col-md-6" style="margin-top: 25px;">
										<p style="text-align: right;">Principal</p>
										<p style="text-align: right; padding-top: 10px;">Date :
												........................</p>
									</div>
								</div>
								<div
										style="border: 1px solid black; height: 125px; margin-top: 10px; border-radius: 5px;">
									<p style="margin-top: 20px; padding-left: 15px;">Admitted
											to class : ...................... Sec ....................
											Fee Receipt No .................... Date ...................
											issued.</p>
									<p style="margin-top: 10px; padding-left: 15px;">Name has
											been entered in the Admission Register.</p>
									<p style="margin-top: 20px; padding-left: 15px;">
										<span>Date : ...................</span> <span
												style="float: right; padding-right: 10px;">Office
												Clerk.</span>
									</p>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
</div>
	
<textarea id="css_for-kg" style="display:none ">
.forschool,.forSenior,.studentdeclaration{display:none;}
.notForKg{display: none;}
.col-md-6{
	width:49%;
	float:left;
	}
	.col-md-3{
	width:24%;
	float:left;
	}
	.row{margin-left:-15px;margin-right:-15px}
	.row::after{
	content:"";
	clear:both;
	display:block;
	overflow:hidden;
	}
	body{-webkit-print-color-adjust: exact;margin:0;padding:0;}
</textarea>
<textarea id="css_for-school" style="display:none ">
.forSenior,.studentdeclaration{display:none;}
	.forkg{
display: none;
}


	.col-md-6{
	width:49%;
	float:left;
	}
	.col-md-3{
	width:24%;
	float:left;
	}
	.row{margin-left:-15px;margin-right:-15px}
	.row::after{
	content:"";
	clear:both;
	display:block;
	overflow:hidden;
	}
.parentdeclaration{
height: 425px;
}
	body{-webkit-print-color-adjust: exact;margin:0;padding:0;}
</textarea>
<textarea id="css_for-senior" style="display:none ">
	.forschool,.notForSenior,.forkg{
display: none;
}
.headings{
height:auto !important;
}
.studentdeclaration p,
.parentdeclaration p{
margin:0px;
padding 0px 5px !important;
}
.parentdeclaration{
height: 375px;
}
.office{
height: auto !important;
}
.forSenior{
display:block;
}
.col-md-6{
	width:49%;
	float:left;
	}
	.col-md-3{
	width:24%;
	float:left;
	}
	.row{margin-left:-15px;margin-right:-15px}
	.row::after{
	content:"";
	clear:both;
	display:block;
	overflow:hidden;
	}
	body{-webkit-print-color-adjust: exact;margin:0;padding:0;}
</textarea>
		

<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
	<p>
		<img src="images/addstu.png" /><span id="pageHeading">Student
				Registration</span>
	</p>

	<div id="admissionDialog" style="display: none">
		<div id="admissionclose" class="accordion-body collapse in">
			<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

				<div class="col-md-6"
						style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-5"
								style="text-align: right; line-height: 35px;">Student
								Name</label>
						<div class="col-xs-7">
							<input type="text" class="form-control" onchange="readyOnly()"
									name="SearchAdmissionStudent" id="temp_studentName" value="" />
						</div>
					</div>

					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-5"
								style="text-align: right; line-height: 35px;">Parent's
								Name</label>
						<div class="col-xs-7">
							<input type="text" class="form-control" onchange="readyOnly()"
									name="SearchAdmissionStudent" id="temp_parentName" value="" />
						</div>
					</div>
				</div>

				<div class="col-md-6"
						style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-5"
								style="text-align: right; line-height: 35px;">Mobile No</label>
						<div class="col-xs-7">
							<input type="text" class="form-control" onchange="readyOnly()"
									name="SearchAdmissionStudent" id="temp_mobileNo" value="" />
						</div>
					</div>


					<div class="form-group clearfix">
						<div class="col-xs-11">
							<div class="navbar-right">
								<span class="buttons" id="Print">Print</span>
							</div>
						</div>
					</div>


				</div>

				<table class="table allstudent" id="admissionstudent">
					<thead>
					<tr>
						<th></th>
						<th>Temp Admission No.</th>
						<th>Student Name</th>
						<th>Parent's Name</th>
						<th>Mobile Number</th>
						<th>Date Of Birth</th>
							
						<th>Created Date</th>
						<th>Delete</th>
					<tr>
					</thead>
					<tbody>
					</tbody>
				</table>


			</div>
			<br />
		</div>
	</div>

	<logic:present name="successMessage" scope="request">

		<div class="successmessagediv1"
				style="width: auto !important; display: none" align="center">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successMessage" scope="request" /></span></a>
			</div>
		</div>

	</logic:present>

	<logic:present name="errorMessage" scope="request">

		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span> <bean:write
								name="errorMessage" scope="request" /></span></a>
			</div>
		</div>

	</logic:present>

	<logic:present name="duplicateMessage">

		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span> <bean:write
								name="duplicateMessage" scope="request" />
				</span></a>
			</div>

		</div>

	</logic:present>
	<div class="errormessagediv1" style="display: none;" align="center">
		<div class="message-item1">
			<!-- Warning -->
			<a href="#" class="msg-warning1 bg-msg-warning1" style="width: 30%;"><span
					class="validateTips1"></span></a>
		</div>
	</div>

	<div class="errormessagediv" style="display: none;" align="center">
		<div class="message-item">
			<!-- Warning -->
			<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
		</div>
	</div>
	<form action="studentRegistration.html?method=saveStudentRegistration"
			enctype="multipart/form-data" name="StudentRegistrationForm"
			id="formstudent" method="post">

		<input type="hidden" id="studentid" name="studentId"
				value="<logic:present name="studentSearchList" property="studentId"><bean:write name="studentSearchList" property="studentId" /></logic:present>" />

		<input type="hidden" id="hiddentempregid" name="tempRegId" value="" />

		<input type="hidden" id="hiddenenquiryid" name="enquiryId" value="" />

		<input type="hidden" id="parenthiddenId" name="parenthiddenId"
				value="<logic:present name="studentSearchList" property="parentId"><bean:write name="studentSearchList" property="parentId" /></logic:present>">


		<input type="hidden" id="acchiddenId"
				value="<logic:present name="studentSearchList" property="academicYearId"><bean:write name="studentSearchList" property="academicYearId" /></logic:present>">

		<input type="hidden" id="streamhiddenId"
				value="<logic:present name="studentSearchList" property="category"><bean:write name="studentSearchList" property="category" /></logic:present>">

		<input type="hidden" id="classhiddenid"
				value="<logic:present name="studentSearchList" property="studClassId"><bean:write name="studentSearchList" property="studClassId" /></logic:present>">
		<input type="hidden" id="sectionhiddenid"
				value="<logic:present name="studentSearchList" property="studSectionId"><bean:write name="studentSearchList" property="studSectionId" /></logic:present>">
		<input type="hidden" id="househiddenid"
				value="<logic:present name="studentSearchList" property="houseId"><bean:write name="studentSearchList" property="houseId" /></logic:present>">


		<input type="hidden" id="rtehiddenid"
				value="<logic:present name="studentSearchList" property="rte"><bean:write name="studentSearchList" property="rte" /></logic:present>">
		<input type="hidden" id="hostelhiddenid"
				value="<logic:present name="studentSearchList" property="hostel"><bean:write name="studentSearchList" property="hostel" /></logic:present>">
		<input type="hidden" id="concessionaplicablehidden"
				value="<logic:present name="studentSearchList" property="isconcession"><bean:write name="studentSearchList" property="isconcession" /></logic:present>">
		<input type="hidden" id="concessionhiddenid"
				value="<logic:present name="studentSearchList" property="scholarShip"><bean:write name="studentSearchList" property="scholarShip" /></logic:present>">
		<input type="hidden" id="transporthiddenid"
				value="<logic:present name="studentSearchList" property="transport"><bean:write name="studentSearchList" property="transport" /></logic:present>">

		<input type="hidden" id="transportcategoryhiddenid"
				value="<logic:present name="studentSearchList" property="transporttypeId"><bean:write name="studentSearchList" property="transporttypeId" /></logic:present>">
		<input type="hidden" id="transportlocationhiddenid"
				value="<logic:present name="studentSearchList" property="transportlocationId"><bean:write name="studentSearchList" property="transportlocationId" /></logic:present>">


		<input type="hidden" id="transportroutehiddenid"
				value="<logic:present name="studentSearchList" property="route"><bean:write name="studentSearchList" property="route" /></logic:present>">

		<input type="hidden" id="schoollocationhiddenid"
				value="<logic:present name="studentSearchList" property="schoolLocation"><bean:write name="studentSearchList" property="schoolLocation" /></logic:present>">

		<input type="hidden" id="typecollectfeehidden"
				value="<logic:present name="studentSearchList" property="transportcollectType"><bean:write name="studentSearchList" property="transportcollectType" /></logic:present>">

		<input type="hidden" id="photohiddenid" name="previousImage"
				value="<logic:present name="studentSearchList" property="studentPhoto"><bean:write name="studentSearchList" property="studentPhoto" /></logic:present>">

		<input type="hidden" id="tempphotohiddenid"
				name="previousTempRegImage" value=""> <input type="hidden"
				id="genderhiddenid"
				value="<logic:present name="studentSearchList" property="gender"><bean:write name="studentSearchList" property="gender" /></logic:present>">

		<input type="hidden" id="bloodhiddenid"
				value="<logic:present name="studentSearchList" property="bloodGroup"><bean:write name="studentSearchList" property="bloodGroup" /></logic:present>">

		<input type="hidden" id="nationalityhiddenid"
				value="<logic:present name="studentSearchList" property="nationality"><bean:write name="studentSearchList" property="nationality" /></logic:present>">

		<input type="hidden" id="mothertongehiddenid"
				value="<logic:present name="studentSearchList" property="mothertongue"><bean:write name="studentSearchList" property="mothertongue" /></logic:present>">

		<input type="hidden" id="religionhiddenid"
				value="<logic:present name="studentSearchList" property="religion"><bean:write name="studentSearchList" property="religion" /></logic:present>">

		<input type="hidden" id="castehiddenid"
				value="<logic:present name="studentSearchList" property="studentcastename"><bean:write name="studentSearchList" property="studentcastename" /></logic:present>">

		<input type="hidden" id="castecategoryhiddenid"
				value="<logic:present name="studentSearchList" property="casteCategory"><bean:write name="studentSearchList" property="casteCategory" /></logic:present>">

		<input type="hidden" id="statushiddenid"
				value="<logic:present name="studentSearchList" property="studentStatus"><bean:write name="studentSearchList" property="studentStatus" /></logic:present>">

		<input type="hidden" id="physicallychallengedhiddenid"
				value="<logic:present name="studentSearchList" property="physicallyChallenged"><bean:write name="studentSearchList" property="physicallyChallenged" /></logic:present>">

		<input type="hidden" id="physicallychallengeddescriptionhiddenid"
				value="<logic:present name="studentSearchList" property="physicalchalreason"><bean:write name="studentSearchList" property="physicalchalreason" /></logic:present>">

		<input type="hidden" id="addresshiddenid"
				value="<logic:present name="studentSearchList" property="address"><bean:write name="studentSearchList" property="address" /></logic:present>">

		<input type="hidden" id="presentaddresshiddenid"
				value="<logic:present name="studentSearchList" property="presentaddress"><bean:write name="studentSearchList" property="presentaddress" /></logic:present>">

		<input type="hidden" id="studentStatushiddentid"
				value="<logic:present name="studentSearchList" property="studentStatus"><bean:write name="studentSearchList" property="studentStatus" /></logic:present>">

		<input type="hidden" id="studentMediumhiddentid"
				value="<logic:present name="studentSearchList" property="medium"><bean:write name="studentSearchList" property="medium" /></logic:present>">


		<input type="hidden" id="selected_Primary_hiddenId"
				value="<logic:present name="studentSearchList" property="primaryPerson"><bean:write name="studentSearchList" property="primaryPerson" /></logic:present>" />

		<input type="hidden" id="birthcertificatehiddenid"
				name="previousBirthCer"
				value="<logic:present name="studentSearchList" property="birthcertificate"><bean:write name="studentSearchList" property="birthcertificate" /></logic:present>">

		<input type="hidden" id="transfercertificatehiddenid"
				name="previousTransferCer"
				value="<logic:present name="studentSearchList" property="transferfile"><bean:write name="studentSearchList" property="transferfile" /></logic:present>">

		<input type="hidden" id="certificate1hiddenid"
				name="previousBirthCer"
				value="<logic:present name="studentSearchList" property="certificate1"><bean:write name="studentSearchList" property="certificate1" /></logic:present>">

		<input type="hidden" id="certificate2hiddenid"
				name="previousBirthCer"
				value="<logic:present name="studentSearchList" property="certificate2"><bean:write name="studentSearchList" property="certificate2" /></logic:present>">

		<input type="hidden" id="certificate3hiddenid"
				name="previousBirthCer"
				value="<logic:present name="studentSearchList" property="certificate3"><bean:write name="studentSearchList" property="certificate3" /></logic:present>">

		<input type="hidden" id="certificate4hiddenid"
				name="previousBirthCer"
				value="<logic:present name="studentSearchList" property="certificate4"><bean:write name="studentSearchList" property="certificate4" /></logic:present>">

		<input type="hidden" id="certificate5hiddenid"
				name="previousBirthCer"
				value="<logic:present name="studentSearchList" property="certificate5"><bean:write name="studentSearchList" property="certificate5" /></logic:present>">

		<input type="hidden" id="parentsguardianhiddenid"
				name="previousTransferCer"
				value="<logic:present name="studentSearchList" property="isWorkingTeacherGuardian"><bean:write name="studentSearchList" property="isWorkingTeacherGuardian" /></logic:present>">
		<input type="hidden" id="mediumhiddenid" name="previousTransferCer"
				value="<logic:present name="studentSearchList" property="medium"><bean:write name="studentSearchList" property="medium" /></logic:present>">

		<input type="hidden" id="specilizationhiddenid"
				name="previousTransferCer"
				value="<logic:present name="studentSearchList" property="specilization"><bean:write name="studentSearchList" property="specilization" /></logic:present>">

		<input type="hidden" id="studentrollnohiddenid"
				name="previousTransferCer"
				value="<logic:present name="studentSearchList" property="studentAdmissionNo"><bean:write name="studentSearchList" property="studentAdmissionNo" /></logic:present>">

		<input type="hidden" id="fatheroccupationhiddenid"
				name="previousTransferCer"
				value="<logic:present name="studentSearchList" property="fatheroccupation"><bean:write name="studentSearchList" property="fatheroccupation" /></logic:present>">

		<input type="hidden" id="fatherdepartmenthiddenid"
				name="previousTransferCer"
				value="<logic:present name="studentSearchList" property="fatherDepartment"><bean:write name="studentSearchList" property="fatherDepartment" /></logic:present>">

		<input type="hidden" id="fatherdesignationhiddenid"
				name="previousTransferCer"
				value="<logic:present name="studentSearchList" property="fatherDesignation"><bean:write name="studentSearchList" property="fatherDesignation" /></logic:present>">

		<input type="hidden" id="motheroccupationhiddenid"
				name="previousTransferCer"
				value="<logic:present name="studentSearchList" property="motheroccupation"><bean:write name="studentSearchList" property="motheroccupation" /></logic:present>">

		<input type="hidden" id="motherdepartmenthiddenid"
				name="previousTransferCer"
				value="<logic:present name="studentSearchList" property="motherDepartment"><bean:write name="studentSearchList" property="motherDepartment" /></logic:present>">

		<input type="hidden" id="motherdesignationhiddenid"
				name="previousTransferCer"
				value="<logic:present name="studentSearchList" property="motherDesignation"><bean:write name="studentSearchList" property="motherDesignation" /></logic:present>">



		<input type="hidden" id="fatherphotohiddenid"
				name="previousFatherImage"
				value="<logic:present name="studentSearchList" property="fatherPhoto"><bean:write name="studentSearchList" property="fatherPhoto" /></logic:present>">

		<input type="hidden" id="motherphotohiddenid"
				name="previousMotherImage"
				value="<logic:present name="studentSearchList" property="motherPhoto"><bean:write name="studentSearchList" property="motherPhoto" /></logic:present>">

		<input type="hidden" id="guardphotohiddenid"
				name="previousGuardImage"
				value="<logic:present name="studentSearchList" property="guardianPhoto"><bean:write name="studentSearchList" property="guardianPhoto" /></logic:present>">

		<input type="hidden" id="guardoccupationhiddenid"
				name="previousTransferCer"
				value="<logic:present name="studentSearchList" property="guardianOccupation"><bean:write name="studentSearchList" property="guardianOccupation" /></logic:present>">


		<input type="hidden" id="admissionhiddenid"
				value="<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="dateofJoin"></bean:write></logic:present>">

		<input type="hidden" id="firstlanghiddenid"
				value="<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="firstlang"></bean:write></logic:present>">

		<input type="hidden" id="secondlanghiddenid"
				value="<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="secondlang"></bean:write></logic:present>">

		<input type="hidden" id="thirdlanghiddenid"
				value="<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="thirdlang"></bean:write></logic:present>">
				
				
		<input type="hidden" id="fatherOfficeAddresshiddenid"
				value="<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="fatherOfficeAddress"></bean:write></logic:present>">
				
				
			<input type="hidden" id="motherOfficeAddresshiddenid"
				value="<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="motherOfficeAddress"></bean:write></logic:present>">
				
			<input type="hidden" id="guardianOfficeAddresshiddenid"
				value="<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="guardianOfficeAddress"></bean:write></logic:present>">
		<!-- chiru -->

		<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
			<div class="panel panel-primary panel-list">
				<div class="panel-heading" role="tab" id="headingOne">

					<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseOne"
							style="color: #767676; vertical-align: text-top; margin-left: 10px;">
						<h4 class="panel-title">
							<i class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Student Registration
						</h4>
					</a>


					<div class="navbar-right">
						<span class="buttons" id="admissionform">Admission Form</span> <span
								class="buttons" id="save" class="save">Save</span> <span
								id="back" class="buttons">Back</a></span>
					</div>
				</div>

				<div id="collapseOne" class="panel-collapse collapse in "
						role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body own-panel">
						<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">
							<div class="form-group clearfix ">
								<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">First
										Name <font color="red">*</font>
								</label>
								<div class="col-xs-7">
									<input type="text" name="studentFirstName" tabindex="1"
											onkeypress="HideError()" id="studentFirstNameId"
											maxlength="25" class="form-control"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentFirstName"/></logic:present>' />
								</div>
							</div>


							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Last
										Name</label>
								<div class="col-xs-7">
									<input type="text" class="form-control" maxlength="20"
											tabindex="2" name="studentLastName" id="studentLastNameId"
											onkeypress="HideError()"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentLastName"></bean:write></logic:present>' />
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School
										Name<font color="red">*</font>
								</label>
								<div class="col-xs-7">
									<select class="form-control" onkeypress="HideError()"
											tabindex="3" name="schoolLocation" id="schoolLocationId">
										<option value=""></option>
									</select>
								</div>
							</div>


							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Admission
										Date <font color="red">*</font>
								</label>
								<div class="col-xs-7">
									<input type="text" readonly="readonly" name="dateofJoin"
											onkeypress="HideError()" id="dateofJoinId"
											class="form-control" value='' />
								</div>
							</div>


							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Admission
										No <font color="red">*</font>
								</label>
								<div class="col-xs-7">
									<input type="text" class="form-control" name="studentrollno"
											tabindex="4" onkeypress="HideError()" id="studentrollno"
											onchange="checkRollnumber()" maxlength="25"
											
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentAdmissionNo"></bean:write></logic:present>' />
								</div>
							</div>


							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Application
										Number </label>
								<div class="col-xs-7">
									<input type="text" class="form-control" name="applicationNo"
											tabindex="5" id="applicationNoId" onkeypress="HideError()"
											maxlength="25"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="applicationNo"></bean:write></logic:present>' />
								</div>
							</div>


							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic
										Year <font color="red">*</font>
								</label>
								<div class="col-xs-7">
									<select name="academicYear" onkeypress="HideError()"
											id="academicYear" class="form-control"></select>
								</div>
							</div>


							<div class="form-group clearfix" id="studentstatuslable">
								<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Medium</label>
								<div class="col-xs-7">
									<select name="medium" id="studentMediumId"
											onkeypress="HideError()" class="form-control">
										<option value="English">English</option>
										<option value="Malayalam">Malayalam</option>
										<option value="Tamil">Tamil</option>
										<option value="Telugu">Telugu</option>
									</select>
								</div>
							</div>

							<div class="form-group clearfix" id="">
								<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">First
										Language</label>
								<div class="col-xs-7">
									<select name="firstlang" id="firstlang" class="form-control">
										<option value=""></option>
									</select>
								</div>
							</div>

							<div class="form-group clearfix" id="studentstatuslable">
								<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Second
										Language</label>
								<div class="col-xs-7">
									<select name="secondlang" id="secondlang" class="form-control">
										<option value=""></option>
									</select>
								</div>
							</div>

							<div class="form-group clearfix" id="studentstatuslable">
								<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Third
										Language</label>
								<div class="col-xs-7">
									<select name="thirdlang" id="thirdlang" class="form-control">
										<option value=""></option>
									</select>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Transport<font
										color="red">*</font></label>

								<div class="col-xs-7" id="radiostyle" style="margin-top: 0px">
									<select name="transport" id="transportId"
											onkeypress="HideError()" class="form-control">
										<option value="Y">Yes</option>
										<option value="N">No</option>
									</select>
									<!-- <label>
										<input type="radio" class="radio-inline" name="transport" class="transport" id="transportIdY" value="Y" />&nbsp;Yes&nbsp;&nbsp;&nbsp;
									</label>
									<label> 
										<input type="radio" class="radio-inline" name="transport" id="transportIdN" class="transport" value="N" checked="checked"/>&nbsp;No
									</label> -->
								</div>
							</div>


							<div class="form-group clearfix" id="transportcategorylabel">
								<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Transport
										Category<font color="red">*</font>
								</label>
								<div class="col-xs-7">
									<select name="transcategory" id="transcategory"
											class="form-control">
										<option value=""></option>
									</select>
								</div>
							</div>


							<div class="form-group clearfix" id="transportlocationlabel">
								<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">
										Transport Location<font color="red">*</font>
								</label>
								<div class="col-xs-7">
									<select name="translocation" id="translocation"
											class="form-control">
										<option value=""></option>
									</select>
								</div>
							</div>


							<div class="form-group clearfix" id="routelabel">
								<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Route </label>
								<div class="col-xs-7">
									<select name="route" id="route" class="form-control">
										<option value=""></option>
									</select>
								</div>
							</div>
							
							<div class="form-group clearfix" id="routelabel">
								<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Prev. School </label>
								<div class="col-xs-7">
									<input type="text" name="previousSchool" id="previousSchool" class="form-control" value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="previousSchool"></bean:write></logic:present>' />
										
								</div>
							</div>
						</div>

						<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">
							<div class="form-group clearfix" style="height: 87px;">
								<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"></label>
								<div class="col-xs-7">
									<div
											style="border: 1px solid #B3B0B0; margin-bottom: 10px; width: 172px; height: 172px;">
										<img id="imagePreview" class="setImage" alt="image" src="#"
												style="height: 45mm; width: 45mm;">
										<div style="position: absolute; top: 0; height: 160px;">
											<input type="file" id="studentImageId1" name="studentImage"
													class="form-control"
													style="height: 170px !important; width: 170px; opacity: 0; z-index: 99999999;">
										</div>
										<span id="removeSpanId" class="close"
												style="position: absolute; top: 0px; right: 100px; color: red;">X</span>
									</div>
								</div>
							</div>


							<div class="form-group clearfix"
									style="padding-top: 0px; margin-top: -12px;">
								<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Stream<font
										color="red">*</font></label>
								<div class="col-xs-7">
									<select class="form-control" name="category" tabindex="6"
											onkeypress="HideError()" id="category">
										<option value=""></option>
									</select>
								</div>
							</div>


							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Class<font
										color="red">*</font></label>
								<div class="col-xs-7">
									<select class="form-control" onkeypress="HideError()"
											tabindex="7" name="studClassId" id="studClassId">
										<option value=""></option>
									</select>
								</div>
							</div>


							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Division<font
										color="red">*</font></label>
								<div class="col-xs-7">
									<select class="form-control" onkeypress="HideError()"
											tabindex="8" name="studSectionId" id="studSectionId">
										<option value=""></option>
									</select>
								</div>
							</div>


							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Specialization</label>
								<div class="col-xs-7">
									<select class="form-control" onkeypress="HideError()"
											tabindex="9" name="specilization" id="specilization">
										<option value=""></option>
									</select>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">House</label>
								<div class="col-xs-7">
									<select class="form-control" onkeypress="HideError()"
											tabindex="8" name="studHouseId" id="studHouseId">
										<option value="">------Select-------</option>
									</select>
								</div>
							</div>
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Transfer
										Certificate No. </label>
								<div class="col-xs-7">
									<input type="text" class="form-control"
											name="transferCertificateNo" onkeypress="HideError()"
											id="transferCertificateNo" onblur="checkRollnumber()"
											maxlength="25"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="transferCertificateNo"></bean:write></logic:present>' />
								</div>
							</div>


							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 18px;">Parents/Guardian
										Working In School? </label>
								<div class="col-xs-7">
									<select name="isWorkingTeacherGuardian" id="isWorking"
											onkeypress="HideError()" class="form-control">
										<option value="Yes">Yes</option>
										<option value="No">No</option>
									</select>
								</div>
							</div>

							<div class="form-group clearfix">

								<label id="workingTeacherIdLabel" for="inputPassword"
										class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Staff ID<font
										color="red">*</font>
								</label>
								<div class="col-xs-7">
									<input type="text" class="form-control"
											name="workingTeacherGuardianId" id="workingTeacherId"
											onkeypress="HideError()" maxlength="25"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="workingTeacherGuardianId"></bean:write></logic:present>' />
								</div>
							</div>

							<div class="form-group clearfix">

								<label id="workingTeacherNameLabel" for="inputPassword"
										class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Staff
										Name </label>
								<div class="col-xs-7">
									<input type="text" class="form-control"
											name="workingTeacherName" id="workingTeacherName"
											onkeypress="HideError()" maxlength="25" readonly="readonly"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="workingTeacherName"></bean:write></logic:present>' />
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="panel panel-primary">
				<div class="panel-heading" role="tab" id="headingTwo">

					<a class="collapsed" role="button" data-toggle="collapse"
							data-parent="#accordion" href="#collapseTwo"
							style="color: #767676" aria-expanded="false"
							aria-controls="collapseTwo"><h4 class="panel-title">
							<i class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Personal Information
						</h4></a>

				</div>
				<div id="collapseTwo" class="panel-collapse collapse"
						role="tabpanel" aria-labelledby="headingTwo">
					<div class="panel-body own-panel">
						<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">

							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Date Of
										Birth <font color="red">*</font>
								</label>
								<div class="col-xs-7">
									<input type="text" placeholder="" onkeypress="HideError()"
											tabindex="10" readonly="readonly" name="dateofBirth"
											id="dateofBirthId" onchange="ageCalculate();"
											class="form-control"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="dateofBirth"></bean:write></logic:present>' />
								</div>
							</div>

							<div class="form-group clearfix clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Age</label>
								<div class="col-xs-7">
									<input type="text" name="age" id="ageId" readonly="readonly"
											class="form-control"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="age"></bean:write></logic:present>' />
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Gender<font
										color="red">*</font></label>
								<div class="col-xs-7" id="radiostyle" style="margin-top: 0px">
									<select name="gender" id=genderId class="form-control"
											tabindex="11">
										<option value="">----------Select----------</option>
										<option value="Male">Male</option>
										<option value="Female">Female</option>
									</select>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Blood
										Group </label>
								<div class="col-xs-7">
									<select name="bloodGroup" id="bloodGroupId" tabindex="12"
											onkeypress="HideError()" class="form-control">
										<option value="">----------Select----------</option>
										<option value="A+ve">A+ve</option>
										<option value="A-ve">A-ve</option>
										<option value="A1+ve">A1+ve</option>
										<option value="A1-ve">A1-ve</option>
										<option value="A2+ve">A2+ve</option>
										<option value="A2-ve">A2-ve</option>
										<option value="A1B+ve">A1B+ve</option>
										<option value="A1B-ve">A1B-ve</option>
										<option value="AB+ve">AB+ve</option>
										<option value="AB-ve">AB-ve</option>
										<option value="AB1+ve">AB1+ve</option>
										<option value="AB1-ve">AB1-ve</option>
										<option value="A+B+ve">A+B+ve</option>
										<option value="B+ve">B+ve</option>
										<option value="B-ve">B-ve</option>
										<option value="O+ve">O+ve</option>
										<option value="O-ve">O-ve</option>
									</select>
								</div>
							</div>


							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Nationality</label>
								<div class="col-xs-7">
									<select name="nationality" id="nationalityId"
											class="form-control">
										<option value="">----------Select----------</option>
										<option value="Afghan">Afghan</option>
										<option value="American">American</option>
										<option value="Argentine">Argentine</option>
										<option value="Argentinian">Argentinian</option>
										<option value="Australian">Australian</option>
										<option value="Belgian">Belgian</option>
										<option value="Bolivian">Bolivian</option>
										<option value="Brazilian">Brazilian</option>
										<option value="British">British</option>
										<option value="Cambodian">Cambodian</option>
										<option value="Cameroonian">Cameroonian</option>
										<option value="Canadian">Canadian</option>
										<option value="Chilean">Chilean</option>
										<option value="Chinese">Chinese</option>
										<option value="Colombian">Colombian</option>
										<option value="Costa Rican">Costa Rican</option>
										<option value="Cuban">Cuban</option>
										<option value="Danish">Danish</option>
										<option value="Dominican">Dominican</option>
										<option value="Ecuadorian">Ecuadorian</option>
										<option value="Egyptian">Egyptian</option>
										<option value="Estonian">Estonian</option>
										<option value="Ethiopian">Ethiopian</option>
										<option value="Finnish">Finnish</option>
										<option value="French">French</option>
										<option value="German">German</option>
										<option value="Ghanaian">Ghanaian</option>
										<option value="Greek">Greek</option>
										<option value="Guatemalan">Guatemalan</option>
										<option value="Haitian">Haitian</option>
										<option value="Honduran">Honduran</option>
										<option value="Indonesian">Indonesian</option>
										<option value="Iranian">Iranian</option>
										<option value="Irish">Irish</option>
										<option value="Israeli">Israeli</option>
										<option value="Italian">Italian</option>
										<option value="Indian" selected="selected">Indian</option>
										<option value="Japanese">Japanese</option>
										<option value="Jordanian">Jordanian</option>
										<option value="Kenyan">Kenyan</option>
										<option value="Laotian">Laotian</option>
										<option value="Latvian">Latvian</option>
										<option value="Lithuanian">Lithuanian</option>
										<option value="Malaysian">Malaysian</option>
										<option value="Mexican">Mexican</option>
										<option value="Moroccan">Moroccan</option>
										<option value="Dutch">Dutch</option>
										<option value="New Zealander">New Zealander</option>
										<option value="Nicaraguan">Nicaraguan</option>
										<option value="Norwegian">Norwegian</option>
										<option value="Panamanian">Panamanian</option>
										<option value="Paraguayan">Paraguayan</option>
										<option value="Peruvian">Peruvian</option>
										<option value="Filipino">Filipino</option>
										<option value="Polish">Polish</option>
										<option value="Portuguese">Portuguese</option>
										<option value="Puerto Rican">Puerto Rican</option>
										<option value="Romanian">Romanian</option>
										<option value="Russian">Russian</option>
										<option value="Saudi">Saudi</option>
										<option value="Scottish">Scottish</option>
										<option value="Spanish">Spanish</option>
										<option value="Swedish">Swedish</option>
										<option value="Swiss">Swiss</option>
										<option value="Taiwanese">Taiwanese</option>
										<option value="Tajik">Tajik</option>
										<option value="Thai">Thai</option>
										<option value="Turkish">Turkish</option>
										<option value="Ukrainian">Ukrainian</option>
										<option value="Uruguayan">Uruguayan</option>
										<option value="Venezuelan">Venezuelan</option>
										<option value="Vietnamese">Vietnamese</option>
										<option value="Welsh">Welsh</option>
									</select>
									<%-- <input type="text" name="nationality" id="nationalityId"
											onkeypress="HideError()" maxlength="20" class="form-control"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="nationality"></bean:write></logic:present>' /> --%>
								</div>
							</div>


							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Mother
										Tongue<font color="red">*</font>
								</label>
								<div class="col-xs-7">
									<select name="mothertongue" id="mothertongueId"
											class="form-control" tabindex="13">
										<option value="">----------Select----------</option>
											<option value="Bengali">Bengali</option>
											<option value="Telugu">Telugu</option>
											<option value="Marathi">Marathi</option>
											<option value="Tamil">Tamil</option>
											<option value="Urdu">Urdu</option>
											<option value="Gujarati">Gujarati</option>
											<option value="Kannada">Kannada</option>
											<option value="Bhojpuri">Bhojpuri</option>
											<option value="Malayalam">Malayalam</option>
											<option value="Odia">Odia</option>
											<option value="Punjabi">Punjabi</option>
											<option value="Rajasthani">Rajasthani</option>
											<option value="Magadhi/Magahi">Magadhi/Magahi</option>
											<option value="Chhattisgarhi">Chhattisgarhi</option>
											<option value="Assamese">Assamese</option>
											<option value="Maithili">Maithili</option>
											<option value="Haryanvi">Haryanvi</option>
											<option value="Marwari">Marwari</option>
											<option value="Santali">Santali</option>
											<option value="Malvi">Malvi</option>
											<option value="Kashmiri">Kashmiri</option>
											<option value="Mewari">Mewari</option>
											<option value="Khortha/Khotta">Khortha/Khotta</option>
											<option value="Bhili/Bhilodi">Bhili/Bhilodi</option>
											<option value="Bundeli/Bundelkhan">Bundeli/Bundelkhan</option>
											<option value="Nepali">Nepali</option>
											<option value="Bagheli">Bagheli</option>
											<option value="Pahari[c]">Pahari[c]</option>
											<option value="Lamani/Lambadi">Lamani/Lambadi</option>
											<option value="Awadhi">Awadhi</option>
											<option value="Wagdi">Wagdi</option>
											<option value="Gondi">Gondi</option>
											<option value="Harauti">Harauti</option>
											<option value="Konkani">Konkani</option>
											<option value="Dogri">Dogri</option>
											<option value="Garhwali">Garhwali</option>
											<option value="Nimadi">Nimadi</option>
											<option value="Sadan/Sadri">Sadan/Sadri</option>
											<option value="Kumaoni">Kumaoni</option>
											<option value="Dhundhari">Dhundhari</option>
											<option value="Ahirani">Ahirani</option>
											<option value="Kurukh/Oraon">Kurukh/Oraon</option>
											<option value="Tulu">Tulu</option>
											<option value="Sindhi">Sindhi</option>
											<option value="Meitei">Meitei</option>
											<option value="Surgujia">Surgujia</option>
											<option value="Bagri Rajasthani">Bagri Rajasthani</option>
											<option value="Bodo/Boro">Bodo/Boro</option>
											<option value="Banjari">Banjari</option>
											<option value="Nagpuria">Nagpuria</option>
											<option value="Surjapuri">Surjapuri</option>
											<option value="Kangri">Kangri</option>
											<option value="Mundari">Mundari</option>
											<option value="Ho">Ho</option>
											<option value="Other">Other</option>
											
											
									</select>
									<%-- <input type="text" name="mothertongue" id="remarksId"
											maxlength="20" class="form-control"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="mothertongue"></bean:write></logic:present>' /> --%>
								</div>
							</div>


							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Aadhar
										No</label>
								<div class="col-xs-7">
									<input type="text" name="aadharno" id="aadharId"
											maxlength="12" class="form-control"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="aadharno"></bean:write></logic:present>' />
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Sms No.</label>
								<div class="col-xs-7">
									<input type="text" name="smsnumber" id="smsnumber"
											maxlength="10" class="form-control"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="smsnumber"></bean:write></logic:present>' />
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Emergency
										No.</label>
								<div class="col-xs-7">
									<input type="text" name="emergencynumber" id="emergencynumber"
											maxlength="10" class="form-control"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="emergencynumber"></bean:write></logic:present>' />
								</div>
							</div>
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 25px;">Physically
										Challenged <font color="red">*</font>
								</label>
								<div class="col-xs-7" id="radiostyle" style="margin-top: 0px">
									<select name="physicallyChallenged"
											id="physicallyChallengedId" class="form-control">
										<option value="YES">YES</option>
										<option value="NO" selected="selected">NO</option>
									</select>
									<!-- <label><input type="radio" class="radio-inline"
											name="physicallyChallenged" id="physicallyChallengedIdY"
											value="YES" />&nbsp;Yes&nbsp;&nbsp;&nbsp;</label><label> <input
											type="radio" name="physicallyChallenged" class="radio-inline"
											id="physicallyChallengedIdN" value="NO" />&nbsp;No
									</label> -->
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
										id="physicalchlngres"
										style="text-align: right; line-height: 25px;">Reason<font
										color="red">*</font></label>
								<div class="col-xs-7" id="reasonDivId">
									<textarea name="physicalchalreason" id="physicalchalreason"
											class="form-control"></textarea>
								</div>
							</div>

						</div>

						<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">

							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Religion<font
										color="red">*</font></label>

								<div class="col-xs-7">
									<select name="religion" id="religion" tabindex="14"
											class="form-control">
										<option value=""></option>

									</select>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Caste<font
										color="red">*</font></label>
								<div class="col-xs-7">
									<select name="caste" id="casteId" tabindex="15"
											class="form-control">
										<option value="">----------Select----------</option>

									</select>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Caste
										Category<font color="red">*</font>
								</label>
								<div class="col-xs-7">
									<select name="casteCategory" id="casteCategoryId"
											tabindex="16" class="form-control">
										<option value="">----------Select----------</option>

									</select>
								</div>
							</div>

							<div class="form-group clearfix" id="studentstatuslable">
								<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Status</label>
								<div class="col-xs-7">
									<select name="studentstatus" id="studentStatusId"
											class="form-control">
										<option value="active">Active</option>
										<!-- <option value="inactive">InActive</option> -->
									</select>
								</div>
							</div>
							
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 45px;">State</label>
								<div class="col-xs-7">
									<input type="text" name="state"
											id="state"  class="form-control" value="<logic:present name="studentSearchList"><bean:write name="studentSearchList"
													property="state"></bean:write></logic:present>" />

								</div>
							</div>

							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 45px;">Identification
										Marks 1</label>
								<div class="col-xs-7">
									<textarea name="identificationMarks"
											id="identificationMarksId" tabindex="17" class="form-control"><logic:present
												name="studentSearchList">
											<bean:write name="studentSearchList"
													property="identificationMarks"></bean:write>
										</logic:present></textarea>

								</div>
							</div>

							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 45px;">Identification
										Marks 2</label>
								<div class="col-xs-7">
									<textarea name="identificationMarks1"
											id="identificationMarksId1" class="form-control"><logic:present
												name="studentSearchList">
											<bean:write name="studentSearchList"
													property="identificationMarks1"></bean:write>
										</logic:present></textarea>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 24px;">Medical
										History <font size="2px;" style="padding-left: 25px;">(Including
											Alergy)</font>
								</label>
								<div class="col-xs-7">
									<textarea name="previousHistory" id="previousHistoryId"
											class="form-control"><logic:present
												name="studentSearchList">
											<bean:write name="studentSearchList"
													property="previousHistory"></bean:write>
										</logic:present></textarea>
								</div>

							</div>

							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Remarks</label>
								<div class="col-xs-7">
									<textarea name="remarks" id="remarksId" class="form-control"><logic:present
												name="studentSearchList">
											<bean:write name="studentSearchList" property="remarks"></bean:write>
										</logic:present></textarea>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
			<div class="panel panel-primary">
				<div class="panel-heading" role="tab" id="headingThree">

					<a class="collapsed" role="button" data-toggle="collapse"
							data-parent="#accordion" href="#collapseThree"
							style="color: #767676" aria-expanded="false"
							aria-controls="collapseThree"><h4 class="panel-title">
							<i class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Parent Information
						</h4></a>

				</div>
				<div id="collapseThree" class="panel-collapse collapse"
						role="tabpanel" aria-labelledby="headingThree">
					<div class="panel-body own-panel">
						<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Sibling
										Name</label>
								<div class="col-xs-7">
									<input type="text" class="form-control" onchange="readyOnly()"
											name="searchTerm" id="SearchStudent"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentSibilingName"></bean:write></logic:present>' />
									<input type="hidden" name="studentSibilingIdInt"
											id="studentSibilingIdIntId"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentSibilingIdInt"></bean:write></logic:present>' />
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Sibling
										class</label>
								<div class="col-xs-7">
									<input type="text" name="sibilingClass" id="sibilingClassId"
											readonly="readonly" class="form-control"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="sibilingClassId"></bean:write></logic:present>' />
								</div>
							</div>

							<fieldset>
								<legend
										style="font-family: Helvetica Neue, Helvetica, Arial, sans-serif; font-size: 16px; color: #767676;">Father's
										Details:</legend>
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Father
											Name <font color="red">*</font>
									</label>
									<div class="col-xs-7">

										<input type="hidden" name="parentId" id="parentId" /> <input
												type="text" name="fatherName" id="fatherNameId"
												onkeypress="HideError()" maxlength="30" class="form-control"
												value='<logic:present name="studentSearchList" scope="request"><bean:write name="studentSearchList" property="fatherName"></bean:write></logic:present>' />
									</div>
								</div>



								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Mobile
											Number <font color="red">*</font>
									</label>
									<div class="col-xs-7">
										<input type="text" name="fatherMobileNo"
												id="fatherMobileNoId" onkeypress="HideError()"
												maxlength="10" class="form-control"
												value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="fatherMobileNo"></bean:write></logic:present>' />
									</div>
								</div>


								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Occupation</label>
									<div class="col-xs-7">
										<select class="form-control" name="fatherOccupation"
												id="fatherOccupation">
											<option value=""></option>
										</select>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Department</label>
									<div class="col-xs-7">
										<input type="text" class="form-control"
												name="fatherDepartment" id="fatherDepartment"
												value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="fatherDepartment"></bean:write></logic:present>' />

									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Designation</label>
									<div class="col-xs-7">
										<input type="text" class="form-control"
												name="fatherDesignation" id="fatherDesignation"
												value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="fatherDesignation"></bean:write></logic:present>' />

									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Office
											Address</label>
									<div class="col-xs-7">
										<textarea name="fatherOfficeAddress" id="fatherofficeaddress"
												onkeypress="HideError()" class="form-control"
												style="height: 110px;"></textarea>
									</div>
								</div>


								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">PAN No.
									</label>
									<div class="col-xs-7">
										<input type="text" name="fatherPanNo" id="fatherPanNo"
												class="form-control"
												value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="fatherPanNo"></bean:write></logic:present>' />
									</div>
								</div>


								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Annual
											Income </label>
									<div class="col-xs-7">
										<input type="text" name="fatherAnnualIncome"
												id="fatherAnnualIncome" maxlength="10" class="form-control"
												value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="fatherAnnualIncome"></bean:write></logic:present>' />
									</div>
								</div>


								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Email
											Id</label>
									<div class="col-xs-7">
										<input type="text" maxlength="45" name="fatherEmail"
												id="fatherEmail" onblur="gaurdianvalidateEmail()"
												class="form-control"
												value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="fatheremailId"></bean:write></logic:present>' />
									</div>
								</div>


								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Qualification</label>
									<div class="col-xs-7">
										<input type="text" name="fatherQualification"
												id="fatherQualification" maxlength="30" class="form-control"
												value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="fatherQualification"></bean:write></logic:present>' />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Photo</label>
									<div class="col-xs-7">
										<input type="file" id="fatherImageId" name="fatherPhoto"
												class="form-control" />
									</div>
								</div>

								<div class="form-group clearfix">

									<div class="col-xs-7">
										<img id="imageFatherPreview" src="#" alt="image" width="80px"
												height="70px" style="margin-left: 80%;" />
									</div>
								</div>
							</fieldset>

							<fieldset>
								<legend
										style="font-family: Helvetica Neue, Helvetica, Arial, sans-serif; font-size: 16px; color: #767676;">Guardian's
										Details:</legend>
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Guardian
											Name
									</label>
									<div class="col-xs-7">
										<input type="text" name="guardianName" id="gaurdianNameId"
												onkeypress="HideError()" maxlength="30" class="form-control"
												value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="guardianName"></bean:write></logic:present>' />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Mobile
											Number 
									</label>
									<div class="col-xs-7">
										<input type="text" name="guardianMobileNo"
												id="guardianMobileNoId" onkeypress="HideError()"
												maxlength="10" class="form-control"
												value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="guardianMobileNo"></bean:write></logic:present>' />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Occupation</label>
									<div class="col-xs-7">
										<select class="form-control" name="guardianOccupation"
												id="guardianOccupation">
											<option value=""></option>
										</select>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Department</label>
									<div class="col-xs-7">
										<input type="text" class="form-control"
												name="guardianDepartment" id="guardianDepartment"
												value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="guardianDepartment"></bean:write></logic:present>' />

									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Designation</label>
									<div class="col-xs-7">
										<input type="text" class="form-control"
												name="guardianDesignation" id="guardianDesignation"
												value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="guardianDesignation"></bean:write></logic:present>' />

									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Office
											Address</label>
									<div class="col-xs-7">
										<textarea name="guardianOfficeAddress"
												id="guardianofficeaddress" onkeypress="HideError()"
												class="form-control" style="height: 110px;"></textarea>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">PAN No.
									</label>
									<div class="col-xs-7">
										<input type="text" name="guardianPanNo" id="guardianPanNo"
												class="form-control"
												value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="guardianPanNo"></bean:write></logic:present>' />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Annual
											Income </label>
									<div class="col-xs-7">
										<input type="text" name="guardianAnnualIncome"
												id="guardianAnnualIncome" maxlength="10"
												class="form-control"
												value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="guardianAnnualIncome"></bean:write></logic:present>' />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Email
											Id</label>
									<div class="col-xs-7">
										<input type="text" maxlength="45" name="guardianEmail"
												id="guardianEmail" onblur="gaurdianvalidateEmail()"
												class="form-control"
												value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="guardianemailId"></bean:write></logic:present>' />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Qualification</label>
									<div class="col-xs-7">
										<input type="text" name="guardianQualification"
												id="guardianQualificationId" maxlength="30"
												class="form-control"
												value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="guardianQualification"></bean:write></logic:present>' />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Photo</label>
									<div class="col-xs-7">
										<input type="file" id="guardianImageId" name="guardianPhoto"
												class="form-control" />
									</div>
								</div>

								<div class="form-group clearfix">

									<div class="col-xs-7">
										<img id="imageGuardianPreview" src="#" alt="image"
												width="80px" height="70px" style="margin-left: 80%;" />
									</div>
								</div>

							</fieldset>




						</div>


						<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px; margin-bottom: 10px;">Admission
										No</label>
								<div class="col-xs-7">
									<input type="text" name="studentRegNo" id="sibilingadminnoId"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentSibilingRegNo"></bean:write></logic:present>'
											readonly="readonly" class="form-control" />
								</div>
							</div>

							<br /> <br />
							<!-- <div class="form-group clearfix"></div> -->

							<fieldset>
								<legend
										style="font-family: Helvetica Neue, Helvetica, Arial, sans-serif; font-size: 16px; color: #767676;">Mother's
										Details:</legend>
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Mother
											Name <font color="red">*</font>
									</label>
									<div class="col-xs-7">
										<input type="text" name="motherName" id="motherNameId"
												onkeypress="HideError()" maxlength="30" class="form-control"
												value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="motherName"></bean:write></logic:present>' />
									</div>
								</div>


								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Mobile
											Number <font color="red">*</font>
									</label>
									<div class="col-xs-7">
										<input type="text" name="motherMobileNo"
												id="motherMobileNoId" onkeypress="HideError()"
												maxlength="10" class="form-control"
												value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="motherMobileNo"></bean:write></logic:present>' />
									</div>
								</div>



								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Occupation</label>
									<div class="col-xs-7">
										<select class="form-control" name="motherOccupation"
												id="motherOccupation">
											<option value=""></option>
										</select>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Department</label>
									<div class="col-xs-7">
										<input type="text" class="form-control"
												name="motherDepartment" id="motherDepartment"
												value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="motherDepartment"></bean:write></logic:present>' />

									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Designation</label>
									<div class="col-xs-7">
										<input type="text" class="form-control"
												name="motherDesignation" id="motherDesignation"
												value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="motherDesignation"></bean:write></logic:present>' />

									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Office
											Address</label>
									<div class="col-xs-7">
										<textarea name="motherOfficeAddress" id="motherofficeaddress"
												onkeypress="HideError()" class="form-control"
												style="height: 110px;"></textarea>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">PAN No.
									</label>
									<div class="col-xs-7">
										<input type="text" name="motherPanNo" id="motherPanNo"
												class="form-control"
												value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="motherPanNo"></bean:write></logic:present>' />
									</div>
								</div>


								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Annual
											Income </label>
									<div class="col-xs-7">
										<input type="text" name="motherAnnualIncome"
												id="motherAnnualIncome" maxlength="10" class="form-control"
												value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="motherAnnualIncome"></bean:write></logic:present>' />
									</div>
								</div>


								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Email
											Id</label>
									<div class="col-xs-7">
										<input type="text" maxlength="45" name="motherEmail"
												id="motherEmail" onblur="gaurdianvalidateEmail()"
												class="form-control"
												value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="motheremailId"></bean:write></logic:present>' />
									</div>
								</div>



								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Qualification</label>
									<div class="col-xs-7">
										<input type="text" name="motherQualification"
												id="motherQualificationId" maxlength="30"
												class="form-control"
												value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="motherQualification"></bean:write></logic:present>' />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Photo</label>
									<div class="col-xs-7">
										<input type="file" id="motherImageId" name="motherPhoto"
												class="form-control" />
									</div>
								</div>

								<div class="form-group clearfix">

									<div class="col-xs-7">
										<img id="imageMotherPreview" src="#" alt="image" width="80px"
												height="70px" style="margin-left: 80%;" />
									</div>
								</div>

							</fieldset>


							<fieldset>
								<legend
										style="font-family: Helvetica Neue, Helvetica, Arial, sans-serif; font-size: 16px; color: #767676;">Primary
										Contact Details:</legend>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Primary
											Person <font color="red">*</font>
									</label>
									<div class="col-xs-7">
										<select name="primaryPersondisables" id="primarypersonId"
												class="form-control">
											<option value="">----------Select----------</option>
											<option value="father">Father</option>
											<option value="mother">Mother</option>
											<option value="guardian">Guardian</option>
										</select>
									</div>
									<input type="hidden" id="hprymarycntperson"
											name="primaryPerson" value="<logic:present name="studentSearchList" property="primaryPerson"><bean:write name="studentSearchList" property="primaryPerson" /></logic:present>" />
								</div>
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Land Line No.
											 </label>
									<div class="col-xs-7">
										<input type="text" name="landLine" id="landLine" 
												class="form-control" value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="landLine"></bean:write></logic:present>' />
									</div>
								</div>
								

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px; margin-top: 40px;">Permanent
											Address </label>
									<div class="col-xs-7">
										<textarea name="address" id="paddrs" onkeypress="HideError()"
												class="form-control" style="height: 110px;"></textarea>
									</div>
								</div>

								<div>
									<div style="margin-left: 136px; margin-bottom: 5px;"
											align="center">
										<input type="checkbox" id="checkAddressId">Same as a
											permanent address
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px; margin-top: 40px;">Present
											Address </label>
									<div class="col-xs-7">
										<textarea name="presentaddress" id="presentadd"
												onkeypress="HideError()" class="form-control"
												style="height: 110px;"></textarea>
									</div>
								</div>

							</fieldset>


						</div>
					</div>
				</div>
			</div>

			<div class="panel panel-primary">
				<div class="panel-heading" role="tab" id="headingfour">

					<a class="collapsed" role="button" data-toggle="collapse"
							data-parent="#accordion" href="#collapsefour"
							style="color: #767676" aria-expanded="false"
							aria-controls="collapsefour"><h4 class="panel-title">
							<i class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Certificates Uploads
						</h4></a>

				</div>
				<div id="collapsefour" class="panel-collapse collapse"
						role="tabpanel" aria-labelledby="headingfour">
					<div class="panel-body own-panel">
						<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Birth
										Certificate</label>
								<div class="col-xs-7">

									<input type="file" id="uploadBirth" name="birthFile"
											class="form-control" style="height: 20%;" /> <input
											type="button" id="document1btn" name="profile"
											class="downloadDoc" value="Download" /> <span
											style="font-size: 20px; color: red; cursor: pointer;"
											id="deleteProfile"> x</span>

								</div>
							</div>

							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Transfer
										Certificate</label>
								<div class="col-xs-7">

									<input type="file" id="uploadTransfer" name="transferFile"
											class="form-control" style="height: 20%;" /> <input
											type="button" id="document2btn" name="idproof"
											class="downloadDoc" value="Download" /> <span
											style="font-size: 20px; color: red; cursor: pointer;"
											id="deleteIDProof">x</span>

								</div>

								<span id="fileUploaddynmic"
										style="margin-left: 65%; text-decoration: underline; color: #2f91c1; cursor: pointer; font-family: Roboto Light; font-weight: bold;">Add
										More Certificates </span>

							</div>

							<div class="form-group clearfix" id="fileattachment1Div"
									style="display: none;">

								<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right;" id="fileattachment1label">File
										Attachment :</label>
								<div class="col-xs-7">
									<input type="file" class="form-control" id="filaattachment1"
											name="certificate1"
											style="height: 35px !important; display: none;"> <span
											style="font-size: 20px; color: red; cursor: pointer; display: none; position: absolute; right: 0; top: 1px;"
											id="deleteFile1"> x</span> <input type="button"
											id="document4btn" name="profile" class="downloadDoc"
											value="Download" style="display: none;" /> <span
											id="downloadFileAttachment1Title" style="display: none;">
											downloadFileAttachment1 </span>
								</div>
								<!-- <div>
									<span style="font-size: 20px; color: red;position: absolute; "id="removefileattach1"> X</span>
								</div> -->

							</div>

							<div class="form-group clearfix" id="fileattachment2Div"
									style="display: none;">
								<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right;" id="fileattachment2label">File
										Attachment :</label>
								<div class="col-xs-7">
									<input type="file" class="form-control" id="filaattachment2"
											name="certificate2"
											style="height: 35px !important; display: none;"> <span
											style="font-size: 20px; color: red; cursor: pointer; display: none; position: absolute; right: 0; top: 1px;"
											id="deleteFile2"> x</span> <input type="button"
											id="document5btn" name="filaattachment2" class="downloadDoc"
											value="Download" style="display: none;" /> <span
											id="downloadFileAttachment2Title" style="display: none;">
											downloadFileAttachment2 </span>

								</div>
								<!-- <div>
									<span style="font-size: 20px; color: red;position: absolute; "id="removefileattach2"> X</span>
								</div>
 -->
							</div>

							<div class="form-group clearfix" id="fileattachment3Div"
									style="display: none;">
								<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right;" id="fileattachment3label">File
										Attachment :</label>
								<div class="col-xs-7">
									<input type="file" class="form-control" id="filaattachment3"
											name="certificate3"
											style="height: 35px !important; display: none;"> <span
											style="font-size: 20px; color: red; cursor: pointer; display: none; position: absolute; right: 0; top: 1px;"
											id="deleteFile3"> x</span> <input type="button"
											id="document6btn" name="filaattachment2" class="downloadDoc"
											value="Download" style="display: none;" /> <span
											id="downloadFileAttachment3Title" style="display: none;">
											downloadFileAttachment3 </span>
								</div>
								<!-- <div>
									<span style="font-size: 20px; color: red;position: absolute; "id="removefileattach3"> X</span>
								</div> -->
							</div>

							<div class="form-group clearfix" id="fileattachment4Div"
									style="display: none;">
								<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right;" id="fileattachment4label">File
										Attachment :</label>
								<div class="col-xs-7">
									<input type="file" class="form-control" id="filaattachment4"
											name="certificate4"
											style="height: 35px !important; display: none;"> <span
											style="font-size: 20px; color: red; cursor: pointer; display: none; position: absolute; right: 0; top: 1px;"
											id="deleteFile4"> x</span> <input type="button"
											id="document7btn" name="filaattachment4" class="downloadDoc"
											value="Download" style="display: none;" /> <span
											id="downloadFileAttachment4Title" style="display: none;">
											downloadFileAttachment4 </span>
									<!-- <div>
									<span style="font-size: 20px; color: red;position: absolute; "id="removefileattach4"> X</span>
									</div> -->
								</div>

							</div>

							<div class="form-group clearfix" id="fileattachment5Div"
									style="display: none;">
								<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right;" id="fileattachment5label">File
										Attachment :</label>
								<div class="col-xs-7">
									<input type="file" class="form-control" id="filaattachment5"
											name="certificate5"
											style="height: 35px !important; display: none;"> <span
											style="font-size: 20px; color: red; cursor: pointer; display: none; position: absolute; right: 0; top: 1px;"
											id="deleteFile5"> x</span> <input type="button"
											id="document8btn" name="filaattachment5" class="downloadDoc"
											value="Download" style="display: none;" /> <span
											id="downloadFileAttachment5Title" style="display: none;">
											downloadFileAttachment5 </span>
								</div>
								<!-- <div>
									<span style="font-size: 20px; color: red;position: absolute; "id="removefileattach5"> X</span>
								</div> -->

							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>
</html>
