$(document).ready(function() {
    $('#btnSaveFood').click(function() {
        var foodID = $('#foodID').val();
        var foodCode = $('#foodCode').val();
        var foodName = $('#foodName').val();
        var foodAddress = $('#foodAddress').val();
        var foodPrice = $('#foodPrice').val();
        var foodCreate = $('#foodCreate').val();
        var foodUpdate = $('#foodUpdate').val();

        if (foodID == "" || foodCode == "" || foodName == "" || foodAddress == "" || foodPrice == "" || foodCreate == "" || foodUpdate == "") {
            alert("Please enter full information.");
            return false;
        }
    });
    $('#btnUpdateFood').click(function() {
        var foodID = $('#foodID').val();
        var foodCode = $('#foodCode').val();
        var foodName = $('#foodName').val();
        var foodAddress = $('#foodAddress').val();
        var foodPrice = $('#foodPrice').val();
        var foodCreate = $('#foodCreate').val();
        var foodUpdate = $('#foodUpdate').val();

        if (foodID == "" || foodCode == "" || foodName == "" || foodAddress == "" || foodPrice == "" || foodCreate == "" || foodUpdate == "") {
            alert("The information is not complete.");
            return false;
        }
    });
});