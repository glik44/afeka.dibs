$(document).ready(function() {
    $.ajax({
        url: "http://rest-service.guides.spring.io/greeting"
    }).then(function(data) {
       $('.greeting-id').append(data.id);
       $('.greeting-content').append(data.content);
    });
});


function newAccountDetails() {
	// TODO: input validation
	return {
		'id': $('#reg-id').val(),
		'name': $('#reg-name').val(),
		'email': $('#reg-email').val(),
		'birthday': $('#reg-birthdate').val(),
		'password': $('#reg-password').val()
	};
}

function newAccount() {
	console.log('newAccount()  = ', newAccountDetails());	
    $.ajax({
        url: "http://localhost:8080/account/new",
        type: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(newAccountDetails()),
        success: function(data, status, x) {
        	console.log('success ', data);
        	$('#reg-result').text(data.status);
        	$('#reg-form').addClass('hidden');
            
         },
        error: function(err, status, err2) { console.log('err: ', err, status, err2); }
    });
};
