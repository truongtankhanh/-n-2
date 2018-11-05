$(document).ready(function() {
    $('#btnSaveMember').click(function() {
        var memberID = $('#memberID').val();
        var memberName = $('#memberName').val();
        var memberBirthday = $('#memberBirthday').val();
        var Gender = $('#Gender').val();
        var memberEmail = $('#memberEmail').val();
        var memberPassword = $('#memberPassword').val();
        var memberCreate = $('#memberCreate').val();
        var memberUpdate = $('#memberUpdate').val();

        if (memberID == "" || memberName == "" || memberBirthday == "" || Gender == "" || memberEmail == "" || memberPassword == "" || memberCreate == "" || memberUpdate == "") {
            alert("Please enter full information.");
            return false;
        }
    });
    $('#btnUpdateMember').click(function() {
        var memberID = $('#memberID').val();
        var memberName = $('#memberName').val();
        var memberBirthday = $('#memberBirthday').val();
        var Gender = $('#Gender').val();
        var memberEmail = $('#memberEmail').val();
        var memberPassword = $('#memberPassword').val();
        var memberCreate = $('#memberCreate').val();
        var memberUpdate = $('#memberUpdate').val();

        if (memberID == "" || memberName == "" || memberBirthday == "" || Gender == "" || memberEmail == "" || memberPassword == "" || memberCreate == "" || memberUpdate == "") {
            alert("The information is not complete.");
            return false;
        }
    });
});