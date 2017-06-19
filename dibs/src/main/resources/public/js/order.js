function orderDetails() {
	// TODO: input validation
	return {
		'portfolioId': $('#portfolioId').val(),
		'stockId': $('#stockId').val(),
		'type': $('#order-type').val(),
		'paymentMethod': $('#payment-method').val(),
		'amount': $('#amount').val(),
		'maxPrice': $('#min-price').val(),
		'minPrice': $('#max-price').val()
	};
}

function placeOrder() {
	console.log('orderDetails()  = ', orderDetails());	
    $.ajax({
        url: "http://localhost:8080/order/placeorder",
        type: "POST",
        contentType: "application/json",
        dataType: "text",
        data: JSON.stringify(orderDetails()),
        success: function(data, status, x) {
        	console.log('success ', data);
        	$('#order-result').text(data);
        	$('#order-form').addClass('hidden');
            
         },
        error: function(err, status, err2) { console.log('err: ', err, status, err2); }
    });
};