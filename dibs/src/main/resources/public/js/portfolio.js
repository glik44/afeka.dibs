function showPortfolio() {	
    $.ajax({
        url: "http://localhost:8080/portfolio/show",
        type: "GET",
        data: {accountId: $('#port-accountId').val()},
        dataType: "json",
        success: function(data, status, x) {
        	console.log('success ', data);
        	$('#portfolio-result').text(data[0].value);
        	$('#portfolio-form').addClass('hidden');
            
         },
        error: function(err, status, err2) { console.log('err: ', err, status, err2); }
    });
};