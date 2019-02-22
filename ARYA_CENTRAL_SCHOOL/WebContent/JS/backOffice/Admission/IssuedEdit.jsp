<div id="add" class="tab-pane">
     <div class="col-md-10"
		style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">
				Registration</span>
		</p>
		


		<form
			action="parentrequiresappointment.html?method=InsertTemporaryStudentRegistration"
			enctype="multipart/form-data" id="formstudent" method="post">

			


			<div class="panel-group" id="accordion">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne"
								style="color: #767676; vertical-align: middle;"> <i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Registration
							</a>
						</h4>



						<div class="navbar-right">
		              <span class="buttons" id="save2"
						data-toggle="tooltip" data-placement="bottom" title="save">Save</span>
			
						

						</div>

					</div>



					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

							
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">First
										Name <span style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<input type="text" name="parentfirstName"
											onkeypress="HideError()" id="parentfirstName" maxlength="25"
											class="form-control" value='' />




									</div>
								</div>
								<br />



								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Parents
										Name <span style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="parents_name"
											id="parents_name" onkeypress="HideError()" maxlength="10"
											value='' />
									</div>
								</div>
								<br />



								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Email Id <span
										style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="parentEmailId"
											onkeypress="HideError()" id="parentEmailId" maxlength="100"
											value='' />
									</div>
								</div>

								<br />






								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Address
										<span style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<textarea name="address" id="address" onkeypress="HideError()"
											class="form-control"></textarea>



									</div>
								</div>

								<br /> <br />


							</div>

							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">


								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Last
										Name <span style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" maxlength="50"
											name="parent_LastName" id="parent_LastName"
											onkeypress="HideError()" value='' />
									</div>
								</div>

								<br />





								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Relationship <span
										style="color: red;">*</span></label>
									<div class="col-xs-7">
									<select name="stu_parrelation" id="stu_parrelation"
											class="form-control" onkeypress="HideError()">
											<option value=" "></option>
										    <option value="father">Father</option>
											<option value="mother">Mother</option>
											<option value="guardian">Guardian</option>
										</select>



									</div>
								</div>
								<br />

								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Mobile
										No <span style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="mobile_number"
											id="mobile_number" onkeypress="HideError()" maxlength="10"
											value='' />



									</div>
								</div>
								<br />


								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;" id="inputnames">Stream
										<span style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<select id="stream" name="stream" class="form-control"
											required>
											<option value=""></option>
											<logic:present name="StreamList">

												<logic:iterate id="StreamRec" name="StreamList">

													<option
														value="<bean:write name="StreamRec" property="streamId"/>">
														<bean:write name="StreamRec" property="streamname" />
													</option>

												</logic:iterate>

											</logic:present>
										</select>

									</div>
								</div>

								<br />




								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;" id="inputnames">Class
										<span style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<select id="class" name="classname" class="form-control"
											required>
											<option value=""></option>

										</select>
									</div>
								</div>

								<br />


							</div>
						</div>
						
					</div>
					
				</div>
				

	</form>
	</div>
	</div>