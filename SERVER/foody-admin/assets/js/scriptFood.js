$(document).ready(function() {
    // <!-- Start event search food -->
    $("#searchFood").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("#tableFood tr").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
    // <!-- End event search restaurant -->
});