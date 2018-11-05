$(document).ready(function() {
    // Start event button save info city
    $('#btnSaveKind').click(function() {
        var kindCode = $('#kindCode').val();
        var kindName = $('#kindName').val();
        var kindCreate = $('#kindCreate').val();
        var kindUpdate = $('#kindUpdate').val();

        if (kindCode == "" || kindName == "" || kindCreate == "" || kindUpdate == "") {
            alert("Please enter full information.");
            return false;
        }
    });
    $('#btnUpdateKind').click(function() {
        var kindCode = $('#kindCode').val();
        var kindName = $('#kindName').val();
        var kindCreate = $('#kindCreate').val();
        var kindUpdate = $('#kindUpdate').val();

        if (kindCode == "" || kindName == "" || kindCreate == "" || kindUpdate == "") {
            alert("The information is not complete.");
            return false;
        }
    });
    // End event button save info city
});