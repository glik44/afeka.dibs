
function newAccountDetails() {
	// TODO: input validation
	return {
		'id': $('#reg-id').val(),
		'name': $('#reg-name').val(),
		'email': $('#reg-email').val(),
		'birthdate': $('#reg-birthdate').val(),
		'password': $('#reg-password').val()
	};
}

function newAccount() {
	console.log('newAccount()  = ', newAccountDetails());	
    $.ajax({
        url: "http://localhost:8080/account/new",
        type: "POST",
        contentType: "application/json",
        dataType: "text",
        data: JSON.stringify(newAccountDetails()),
        success: function(data, status, x) {
        	console.log('success ', data);
        	createPortfolio();
        	$('#reg-account-result').text(data);
        	$('#reg-form').addClass('hidden');
            
         },
        error: function(err, status, err2) { console.log('err: ', err, status, err2); }
    });
};

function createPortfolio() {	
    $.ajax({
        url: "http://localhost:8080/portfolio/create/"+ $('#reg-id').val(),
        type: "GET",
        dataType: "text",
        success: function(data, status, x) {
        	console.log('success ', data);
        	$('#reg-portfolio-result').text(data);
        	$('#reg-form').addClass('hidden');
            
         },
        error: function(err, status, err2) { console.log('err: ', err, status, err2); }
    });
};