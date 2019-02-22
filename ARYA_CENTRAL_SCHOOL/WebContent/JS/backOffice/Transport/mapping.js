function addIndivudualtext() {
 		
		var a = document.getElementById('original');
		var b = document.getElementById('copy');

		var concatValues;
		if (a != null) {

			for ( var count = 0; count < a.length; count++) {
				if (a[count].selected) {
					var option = document.createElement("option");
					option.setAttribute("value", a.value);
					//option.innerHTML = a.value;
					option.innerHTML = a[count].text;
					document.getElementById('copy').appendChild(option);

					concatValues = a[count].value;
					a[count].remove();
					break;
				}
			}

		}
	}
function deselectIndivivdualData() {
  		var a = document.getElementById('copy');
		var b = document.getElementById('original');
		var concatValues;
		if (a != null) {
			for ( var count = 0; count < a.length; count++) {
				if (a[count].selected) {
					var option = document.createElement("option");
					option.setAttribute("value", a.value);
					//option.innerHTML = a.value;
					option.innerHTML=a[count].text;
					document.getElementById('original').appendChild(option);
					concatValues = a[count].value;
					a[count].remove();
					break;
				}
			}

		}

	}
	
	
	 
	function addtext() {
		
		var a = document.getElementById('original');
		var b = document.getElementById('copy');
		var selectedValues = new Array();
		var seelctedTexts = new Array();
		var concatValues = "";
		var concattext="";
		var v = a.length;
		for ( var count = 0; count < a.length; count++) {

			if (a[count].selected) {
				concatValues = a[count].value + "@" + concatValues;
				concattext= a[count].text + "@" + concattext;
				//a[count].remove();
			}
		}

		var splitValues = concatValues.split("@");
        var splitsText=concattext.split("@");
		for ( var i = 0; i < splitValues.length; i++) {
			if (splitValues[i] != "") {
				selectedValues[i] = splitValues[i];
			}
		}
		for ( var i = 0; i < splitsText.length; i++) {
			if (splitsText[i] != "") {
				seelctedTexts[i] = splitsText[i];
			}
		}
		for ( var i = 0; i < selectedValues.length; i++) {
			var option = document.createElement("option");
			option.setAttribute("value", selectedValues[i]);
			option.innerHTML =seelctedTexts[i];
			//option.innerHTML = selectedValues[i];
			document.getElementById('copy').appendChild(option);
		}

		remove(concatValues, a);
	}
	 function remove(concatValues, a) {
		var splitValues = concatValues.split("@");
		var selectedValues = new Array();
		for ( var i = 0; i < splitValues.length; i++) {
			if (splitValues[i] != "") {
				selectedValues[i] = splitValues[i];
			}
		}
		for ( var i = 0; i < selectedValues.length; i++) {
			for ( var j = 0; j < a.length; j++) {
				if (a[j].value == selectedValues[i]) {
					a[j].remove();
				}
			}
		}

	}
	 
	 function deSelectData() {
			
			var a = document.getElementById('copy');
			var b = document.getElementById('original');
			var selectedValues = new Array();
			var seelctedTexts = new Array();
			var concatValues = "";
			var concattext="";
			for ( var count = 0; count < a.length; count++) {
				if (a[count].selected) {
					concatValues = a[count].value + "@" + concatValues;
					concattext= a[count].text + "@" + concattext;
					// a[count].remove();
				}
			}

			var splitValues = concatValues.split("@");
			var splitsText=concattext.split("@");
			for ( var i = 0; i < splitValues.length; i++) {
				if (splitValues[i] != "") {
					selectedValues[i] = splitValues[i];
				}
			}
			for ( var i = 0; i < splitsText.length; i++) {
				if (splitsText[i] != "") {
					seelctedTexts[i] = splitsText[i];
				}
			}
			for ( var i = 0; i < selectedValues.length; i++) {
				var option = document.createElement("option");
				option.setAttribute("value", selectedValues[i]);
				option.innerHTML =seelctedTexts[i];
				document.getElementById('original').appendChild(option);
			}

			remove(concatValues, a);
		}
	 
	 function selectData() {
			document.getElementById('original').innerHTML = "";
			document.getElementById('original').value = "";
			var data = document.getElementById('role').value;
			var mods;
			if (data == 'programmer') {
				mods = [ 'Admin', 'Transaction', 'Reports' ];
			} else if (data == 'trainee') {
				mods = [ 'Reports' ];
			} else if (data == 'tl') {
				mods = [ 'Admin', 'Transaction', 'Reports', 'Status' ];
			} else {
				mods = [ 'Admin', 'Transaction', 'Reports', 'Status',
						'OverAllStatus' ];
			}
			for ( var i = 0; i < mods.length; i++) {
				var option = document.createElement("option");
				option.setAttribute("value", mods[i]);
				option.innerHTML = mods[i];

				document.getElementById('original').appendChild(option);
			}
		}
		
		