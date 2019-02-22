$(document).ready(
		function() {
			
			
			
		var	hidden_academicyear_id = $("#hidden_academicyear_id").val();
		
		if(hidden_academicyear_id!=null)
			{
		
		$("#accyear option[value="+hidden_academicyear_id+"]").attr("selected",'true');		
		$("#accyear").attr("disabled",'true');
			}
			
			var monthlist = [];

			$("#payment_date_id").datepicker({

				dateFormat : "dd-mm-yy",
				changeMonth : "true",
				changeYear : "true",
				buttonImage : "images/calendar.GIF",
				buttonImageOnly : true

			});

			/*
			 * if(s1=="JUN") {
			 * 
			 * $("#JUN").prop( "checked", true ); }
			 */

			$("#accyear").append(function() {

				/*var s1 = $("#jan").val();

				var s2 = $("#FEB").val();

				if (s1 == "JAN")

				{
					$("#jan").prop("checked", true);

				}

				if (s2 == "FEB")

				{
					$("#FEB").prop("checked", true);

				}*/

				var studentid = $("#hstudentid").val();
				var admissionnum = $("#admissionNum").val();
				var classid = $("#hclassid").val();
				var hfmscode = $("#hfmscode").val();

				var datalist = {

					"studentid" : studentid,
					"admissionnum" : admissionnum,
					"classid" : classid,
					"hfmscode" : hfmscode
				};

				$.ajax({

					type : "GET",
					url : "feecollection.html?method=getmodeofpaymentAction",
					data : datalist,
					async : false,

					success : function(data) {

						var result = $.parseJSON(data);

						if ((result.feeList[0].january) == "JAN") {

						//	$("#jan").prop("checked", true);
							$("#JAN").hide();
							monthlist.push("JAN");


							//document.getElementById("JAN").disabled = true;

						}

						if ((result.feeList[0].february) == "FEB") {

							//$("#FEB").prop("checked", true);
							$("#FEB").hide();
							monthlist.push("FEB");

						//	document.getElementById("FEB").disabled = true;

						}
						if ((result.feeList[0].march) == "MAR") {

							//$("#MAR").prop("checked", true);
							$("#MAR").hide();
							monthlist.push("MAR");

							//document.getElementById("MAR").disabled = true;


						}
						if ((result.feeList[0].april) == "APR") {

							//$("#APR").prop("checked", true);
							$("#APR").hide();
							monthlist.push("APR");

							//document.getElementById("APR").disabled = true;


						}
						if ((result.feeList[0].may) == "MAY") {

							//$("#MAY").prop("checked", true);
							$("#MAY").hide();
							monthlist.push("MAY");

						//	document.getElementById("MAY").disabled = true;


						}
						if ((result.feeList[0].june) == "JUN") {

							
							$("#JUN").hide();
							
							monthlist.push("JUN");
							
							//document.getElementById("JUN").disabled = true;

						}
						if ((result.feeList[0].july) == "JUL") {

							//$("#JUL").prop("checked", true);
							$("#JUL").hide();
							
							monthlist.push("JUL");

						//	document.getElementById("JUL").disabled = true;

						}
						if ((result.feeList[0].august) == "AUG") {

							//$("#AUG").prop("checked", true);
							$("#AUG").hide();
							monthlist.push("AUG");
						//	document.getElementById("AUG").disabled = true;

						}
						if ((result.feeList[0].september) == "SEP") {

							//$("#SEP").prop("checked", true);
							$("#SEP").hide();
							monthlist.push("SEP");

							//document.getElementById("SEP").disabled = true;


						}
						if ((result.feeList[0].october) == "OCT") {

							//$("#OCT").prop("checked", true);
							$("#OCT").hide();
							monthlist.push("OCT");

							//document.getElementById("OCT").disabled = true;


						}
						if ((result.feeList[0].november) == "NOV") {

							//$("#NOV").prop("checked", true);
							$("#NOV").hide();
							monthlist.push("NOV");

							//document.getElementById("NOV").disabled = true;


						}
						if ((result.feeList[0].december) == "DEC") {

							$("#DEC").hide();
							monthlist.push("DEC");

							//$("#DEC").prop("checked", true);
						//	document.getElementById("DEC").disabled = true;


						}

						$("#paidamountid").val(result.feeList[0].paidamount);

					}

				});

				var totalamount = $("#totalfeeamtid").val();
				var paidamount = $("#paidamountid").val();

				var balanceamount = null;

				balanceamount = totalamount - paidamount

				$("#balanceamountid").val(balanceamount);

			});

			// Save Function

			$(".paymentMonth").click(function() {
				var count = 0;
				var totalfullamount = $("#totalfeeamtid").val();

				$('input[name="paymentMonth"]:checked').each(function() {
					count = count + 1;
					var totalamount = $("#total_fee").val();
					$("#payingamount").val(totalamount * count);
					var dueamount = null;
					var balance = $("#balanceamountid").val();
					dueamount = balance - (totalamount * count);
					$("#dueamountid").val(dueamount);

				});

			});

			$('#save').click(
					function() {
						
						
						var month = $('#paymentTypeId').val();
						var academicyear = $('#accyear').val();
						var paymentdate=$('#payment_date_id').val();
						var paymentmode=$('#payment_mode').val();

						var count = 0;
						var feecode = null;
						$('input[name="paymentMonth"]:checked').each(
								function() {
									count = count + 1;
									feecode = this.value;
								});

						if (academicyear == null || academicyear == "") {

							$('.errormessagediv').show();
							$('.validateTips').text(
									"Please Select Academic Year");

						}

						else if (month == null || month == "") {

							$('.errormessagediv').show();
							$('.validateTips').text(
									"Please Select Payment Type");

						}

						else if ((count > 1 || count == 0)
								&& month == "monthly") {

							$('.errormessagediv').show();
							$('.validateTips').text("Select any one month");

						}

						else if ((count < 3 || count == 0 || count > 3)
								&& month == "quarterly") {

							$('.errormessagediv').show();
							$('.validateTips').text("Select  3 months");

						} else if ((count < 6 || count == 0 || count > 6)
								&& month == "halfyearly") {

							$('.errormessagediv').show();
							$('.validateTips').text("Select  6 months");

						}

						else if ((count < 12 || count == 0 || count > 12)
								&& month == "annually") {

							$('.errormessagediv').show();
							$('.validateTips').text("Select 12 months");

						}	else if (paymentdate == null || paymentdate == "") {

							$('.errormessagediv').show();
							$('.validateTips').text(
									"Please Select Payment Date");

						}	else if (paymentmode == null || paymentmode == "") {

							$('.errormessagediv').show();
							$('.validateTips').text(
									"Please Select Payment Mode");

						}
						
						
						

						else {


							var count = 0;
							

							$('input[name="paymentMonth"]:checked').each(
									function(i, selected) {
										count = count + 1;
										//monthlist[i] = $(selected).val();
										
										monthlist.push($(selected).val());
										
										//monthlist[i] = $("paymentMonth").attr("disabled", true);
									});
							

							var months = monthlist.join(",");


							
							
							$("#monthlist").val(months);
							
							document.getElementById("formfeecollection").submit();
						}

						return true;

					});

		});
