

$(function () {
$('.login-button').click(function () {
	let popFlag = document.getElementById('flag').value;
	let mail = document.getElementById('login_mail').value;
	let password = document.getElementById('login_pass').value;

if (mail=='' || password=='') {
	popFlag='1';
}else{
	popFlag='0';
}

console.log(popFlag);

if(popFlag=='0'){
	  $('.login-form').submit();
} else if(popFlag=='1'){
    $('.login_msg').html('入力されていない<br>項目があるよ');
	$('.error-popup').fadeIn();
	popFlag='0';
} else if(popFlag=='2'){
	$('.error-popup').fadeIn();
	popFlag='0';
}


});

$('.close-popup').click(function () {
    $('.confirm-popup').fadeOut();
    $('.error-popup').fadeOut();
    $('.back-popup').fadeOut();
    let password = document.getElementById('login_pass');
	password.value='';
  });



});
