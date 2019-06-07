
function isLeapYear(year) {
	var yearNum = parseInt(year);
	if(isNaN(yearNum)) {
		throw("Not a valid number !");
	}
	if((year % 4) !== 0) {
		return false;
	}
	if( ((year % 100) === 0) && ((year % 400) !== 0) ) {
		return false;
	}
	return true;
}

function changeOptions(inputList, selectTagName, limit) {
	var selectOld = document.getElementById(selectTagName);
	selectOld.options.remove(0);
	var i;
	var len = selectOld.options.length;
	for (i = 0; i < length + 1; i++) {
		selectOld.options.remove(0);
	}
	len = inputList.length;
	for (i = 0; i < len; i++) {
		if(i > limit) {
			break;
		}
		selectOld.options[i] = new Option(inputList[i].value, inputList[i].name);
	}
}

