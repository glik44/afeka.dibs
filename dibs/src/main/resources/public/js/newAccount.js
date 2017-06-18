$(document).ready(function() {
    $.ajax({
        url: "http://rest-service.guides.spring.io/greeting"
    }).then(function(data) {
       $('.greeting-id').append(data.id);
       $('.greeting-content').append(data.content);
    });
});


function newAccount() {
    $.ajax({
        url: "http://localhost:8080/account/new",
        type: "POST",
        datatype: "json",
        data: {id: $('#Id').val(), name: $('#name').val(), email: $('#email').val(), password: $('#password').val(), birthdate: $('#birthdate').val()}
    }).then(function(data) {
       $('.new-account-result').append(data);
    });
});