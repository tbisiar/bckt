/**
 * Created by tbis163 on 6/02/17.
 */

// Load the bucket once the page has loaded
$(load_bucket("http://localhost:8080"));

function load_bucket(path) {
    $.ajax({
               url: path + "/bucket",
               type: 'GET',
               dataType: 'json',
               success: function (data) {
                   $.each(data.drops, function (key, drop) {
                       var thumbnail = "<img src='" + drop.photo[0].source + "'/>";
                       var title = "<h2>" + drop.title + "</h2>";
                       var description = "<p>" + drop.description + "</p>";
                       var type = "<h3>" + drop.dropType.type + "</h3>";

                       var overview_card = title + thumbnail + description + type;

                       $("#bucket-container").append(overview_card);
                   });
               },
               error: function (error) {
                   show_error("Path " + path + " could not be loaded");
               }
           });


    function show_error(error_msg) {
        $("#error-alert").removeClass("hidden");
        $("#error-alert p").text(error_msg);
    }

}