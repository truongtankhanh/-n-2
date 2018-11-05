$(document).ready(function() {
    // <!-- Start event search restaurant -->
    $("#searchMember").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("#tableMember tr").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
    // <!-- End event search restaurant -->
});