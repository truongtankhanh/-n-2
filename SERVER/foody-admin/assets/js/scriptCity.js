$(document).ready(function() {

    // Start event button save info city
    $('#btnSaveCity').click(function() {
        var cityCode = $('#cityCode').val();
        var cityName = $('#cityName').val();

        if (cityCode == "" || cityName == "") {
            alert("Please enter full information.");
            return false;
        }
    });
    $('#btnUpdateCity').click(function() {
        var cityCode = $('#cityCode').val();
        var cityName = $('#cityName').val();

        if (cityCode == "" || cityName == "") {
            alert("The information is not complete.");
            return false;
        }
    });
    // End event button save info city


});