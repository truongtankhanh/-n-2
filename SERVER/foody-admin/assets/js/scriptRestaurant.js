$(document).ready(function() {
    // <!-- Start event search restaurant -->
    $("#searchRes").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("#tableRes tr").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
    // <!-- End event search restaurant -->
});