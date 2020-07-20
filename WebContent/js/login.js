let popFlag ='0';
let mail = document.getElementById('mail').value;
let password = document.getElementById('pass').value;

$(function () {
$('.login-button').click(function () {

if (document.getElementById('mail').value=='' || password=='') {
	popFlag='1';
}

if(popFlag=='1'){
/*	$('.error1').text('エラー1')*/
	$('.error-popup').fadeIn();
}

if(popFlag=='2'){
	$('.error-popup').fadeIn();
}


});
});
