$(document)
		.ready(
				function() {
					// *****************Highlight The Error
					// Massage**************************//
					function updateTips(t) {

						tips.text(t).addClass("ui-state-highlight");
						setTimeout(function() {
							tips.removeClass("ui-state-highlight", 500);
						}, 500);
					}

					// *****************Validation Of All TextBox
					// Input**************************//
					function checkLengthText(o, n, min, max) {

						if (o.val() == 0) {

							o.addClass("ui-state-error");
							updateTips("Enter " + n);
							return false;
						} else {
							if (o.val().length > max || o.val().length < min) {
								o.addClass("ui-state-error");

								updateTips(n + " " + "should be between " + min
										+ " and " + max + ".");
								return false;
							} else {
								o.removeClass("ui-state-error");
								return true;
							}
						}
					}
					function checkRegexpText(o, regexp, n) {
						
						alert(regexp.test(o.val()));
						
						if (!(regexp.test(o.val()))) {
							o.addClass("ui-state-error");
							updateTips(n);
							return false;
						} else {
							o.removeClass("ui-state-error");
							return true;
						}
					}
				/*	function checkRegexpTextVal(o, regexp, n) {

						if (o) {
							// o.addClass( "ui-state-error" );

							updateTips(n);
							// o.removeClass("ui-state-error",5000);
							return false;
						}

						return true;

					}*/
					// *****************Validation Of
					// SelectBox**************************//
					function selectBoxElement(selectVal, errorMessage) {

						var selectId = selectVal.val().length;

						if (selectId == 0) {
							selectVal.addClass("ui-state-error");
							updateTips(errorMessage);
							return false;
						} else {
							selectVal.removeClass("ui-state-error");
							return true;
						}
					}
					// *******************Validation Of
					// CheckBox***********************//
					function checkBoxElement(checkbox, errorMessage1) {

						var checkbox = document.getElementById("checkbox");
						if (checkbox.checked != true) {
							updateTips(errorMessage1);
							return false;
						} else {
							$("#checkbox").prop("checked", false);
							return true;
						}
					}
					// *******************Validation Of Radio
					// button***********************//
					function radioButtonElement(gender, errorMessage1) {

						if ($('input[name=gender]:checked').length <= 0) {

							gender.addClass("ui-state-error");
							updateTips(errorMessage1);
							return false;
						} else {
							gender.removeClass("ui-state-error");
							return true;
						}
					}

					function radioButtonElementFeeType(gender, nameValue,
							errorMessage1) {

						if ($('input[name=' + nameValue + ']:checked').length <= 0) {

							gender.addClass("ui-state-error");
							updateTips(errorMessage1);
							return false;
						} else {
							gender.removeClass("ui-state-error");
							return true;
						}
					}
					// *****************Validation Of ChekBox And Radio
					// Button**************************//
					function countSelected(formElement, inputType, inputName) {
						if (inputType == null)
							inputType = 'checkbox';
						var returnValue = 0;
						for ( var loopCounter = 0; loopCounter < formElement.length; loopCounter++) {
							var element = formElement.elements[loopCounter];
							if (element.type == inputType
									&& element.checked == true) {
								if (inputName.length > 0)
									if (element.name == inputName)
										returnValue++;
									else
										returnValue++;
							}
						}
						return returnValue;
					}

					// *****************Validation For Image
					// Upload**************************//

					function validateFileExtension(obj, fileName, errorMessage1) {

						var exp = /^(jpg|jpeg|JPEG|gif|JPG|png|PNG)$/;

						if (!exp.test(fileName)) {

							obj.addClass("ui-state-error");
							updateTips(errorMessage1);
							return false;

						} else {
							obj.removeClass("ui-state-error");
							return true;

						}

					}

					function countSelectedOptions(selectElement) {
						var returnValue = 0;
						for ( var loopCounter = 0; loopCounter < selectElement.options.length; loopCounter++)
							if (selectElement.options[loopCounter].selected == true)
								returnValue++;
						return returnValue;
					}


					window.checkLengthText = checkLengthText;
					window.checkRegexpText = checkRegexpText;
					window.selectBoxElement = selectBoxElement;
/*					window.checkRegexpTextVal = checkRegexpTextVal;
*/					window.radioButtonElement = radioButtonElement;
					window.radioButtonElementFeeType = radioButtonElementFeeType;
					window.validateFileExtension = validateFileExtension;
				});

// ****************************************CALL VALIDATION FUNCION RULES OF TEXT
// FIELD**************************************************//
// checkLengthText( RoleId , "RoleId ", 5, 16 );/*********length for all text
// field****************/
// checkRegexpText( RoleId, /^([0-9])+$/, "RoleId field only allow Integer :0-9"
// );/********number***********/
// checkRegexpText( RoleName, /^[a-z]([0-9a-z_])+$/i, "RoleName may consist of
// a-z, 0-9, underscores, begin with a letter." );/***********combination of num
// and alphabate***/
// checkRegexpText( Description, /^[a-z]([a-z_])+$/i, "Description may consist
// only alphabate a-z." );/********combination of only alphabate************/
// checkRegexpText( EmailId, \b[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}\b, "please
// enter the valid mail ending with .com." );/********Emailid************/
