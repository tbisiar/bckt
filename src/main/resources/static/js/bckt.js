/**
 * Created by tbis163 on 6/02/17.
 */

// Load the bucket once the page has loaded
$(load_bucket("http://localhost:8080"));

// Reload bucket when clicking button
$("#search-form").submit(function (event) {
    event.preventDefault();
    $("#document-container").html("");
    $("#error-alert").addClass("hidden");
    load_bucket("http://localhost:8080");
});

function load_bucket(path) {
    $.ajax({
               url: path + "/bucket",
               type: 'GET',
               dataType: 'json',
               success: function(data) {
                   show_bucket(data)
               },
               error: show_error("Path " + path + " could not be loaded")
           });
}

function show_bucket(data) {
    $.each(data.drops, function (key, drop) {
        var div_start = "<div class='col-md-4'>";
        var thumbnail = "<img src='" + drop.photo[0].source + "' height='150' width='200'/>";
        var title = "<h2>" + drop.title + "</h2>";
        var description = "<p>" + drop.description + "</p>";
        var type = "<h3>" + drop.dropType.type + "</h3>";
        var div_end = "</div>";

        var overview_card = div_start + title + thumbnail + description + type + div_end;

        $("#bucket-container").append(overview_card);
    });
}

function show_error(error_msg) {
    $("#error-alert").removeClass("hidden");
    $("#error-alert p").text(error_msg);
}