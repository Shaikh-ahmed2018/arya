$(document).ready(function(){
	
	
	
	
	var cnt = 5;
	
	$("#addrow")
	.click(
			function() {
				alert("addrow");	
					
				cnt++;
				
				var tr1 = document.createElement("tr");
				var td1 = document.createElement("td");
				
				
				var check = document
				.createElement("span");
		check.id = "sno" + cnt;
		check.name = "sno";
		check.setAttribute("class", "snoClass");
		td1.appendChild(check);
		tr1.appendChild(td1);
				
				
				
		
		
		var bioid_input = document
		.createElement("input");

bioid_input.name = "bookname";
bioid_input.id = "bookname" + cnt;

bioid_input.setAttribute("class",
		"bookclass");

bioid_input.style.background = "#F4F6F7";
bioid_input.style.border = "1px solid #999999 ";
bioid_input.style.fontSize = "12px";
bioid_input.style.padding = "2px";
bioid_input.style.width = "100%";
bioid_input.style.height = "26px";
bioid_input.style.borderRadius = "4px";
		
		
		
		
		
var td2 = document.createElement("td");
td2.appendChild(bioid_input);
tr1.appendChild(td2);

var empname_input = document
		.createElement("input");

empname_input.type = "text";
empname_input.name = "authorname";
empname_input.id = "authorname" + cnt;

empname_input.setAttribute("class",
		"authorclass");

empname_input.style.background = "#F4F6F7";
empname_input.style.border = "1px solid #999999";

empname_input.style.fontSize = "12px";
empname_input.style.padding = "2px";
empname_input.style.width = "100%";
empname_input.style.height = "26px";	
		
		
	

var td3 = document.createElement("td");
td3.appendChild(empname_input);
tr1.appendChild(td3);

var hdfcacno_input = document
		.createElement("input");

hdfcacno_input.type = "text";
hdfcacno_input.name = "edition";
hdfcacno_input.id = "edition"
		+ cnt;

hdfcacno_input.setAttribute("class",
		"editionclass");

hdfcacno_input.style.background = "#F4F6F7";
hdfcacno_input.style.border = "1px solid #999999";
hdfcacno_input.style.fontSize = "12px";
hdfcacno_input.style.padding = "2px";
hdfcacno_input.style.width = "100%";
hdfcacno_input.style.height = "25px";





var td4 = document.createElement("td");
td4.appendChild(hdfcacno_input);
tr1.appendChild(td4);

var amount_input = document
		.createElement("input");

amount_input.type = "text";
amount_input.name = "barcode";
amount_input.id = "barcode" + cnt;

amount_input.setAttribute("class",
		"barcodeclass");

amount_input.style.background = "#F4F6F7";
amount_input.style.border = "1px solid #999999";
amount_input.style.fontSize = "12px";
amount_input.style.padding = "2px";
amount_input.style.width = "100%";
amount_input.style.height = "25px";



document.getElementById(
"allstudent")
.appendChild(tr1);

$("#" + check.id).text("" + cnt);
		
		
				
			});
	
	
});