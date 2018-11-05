$(document).ready(function() {
    $('#btnSaveRes').click(function() {
        var resCodeNew = $('#resCodeNew').val();
        var resNameNew = $('#resNameNew').val();
        var numberOfBranches = $('#numberOfBranches').val();

        if (resCodeNew == "" || resNameNew == "" || numberOfBranches == "") {
            alert("Please enter full information.");
            return false;
        }
    });
    $('#btnUpdateRes').click(function() {
        var resCodeNew = $('#resCodeNew').val();
        var resNameNew = $('#resNameNew').val();
        var numberOfBranches = $('#numberOfBranches').val();

        if (resCodeNew == "" || resNameNew == "" || numberOfBranches == "") {
            alert("The information is not complete.");
            return false;
        }
    });
});