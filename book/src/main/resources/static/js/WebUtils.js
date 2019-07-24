
/*
 * 숫자 3개마다 [,] 
 * x 가 숫자가 아닐 시, 0보다 작을 시 "정보없음"
 */
function getMoneyPoint(val) {
	
	if (isNaN(val) || val < 0) {
		return "정보없음";
	}
	
    return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

function getDateYMD(str) {
	var date = new Date(str);
	
	return date.getFullYear() + "년 " + (date.getMonth()+1) + "월 " + date.getDate() + "일";
}

function getDateYMDHm(str) {
	var date = new Date(str);
	return getDateYMD(str) + " " + date.getHours() + "시 " + date.getMinutes() + "분";
}

function exceptionAlert(data) {
	var msg = "[알림]\n";
	msg += data.message;
	alert(msg);
}