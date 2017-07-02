/**
 * Customized javascript for bckt operations
 * Created by tbis163 on 6/02/17.
 */
var url_base = 'http://localhost:8080';
var user_id = 'tbisiar;';
// Load the bucket once the page has loaded
// $(load_bucket(url_base));

// Create bucket when create demo bucket button clicked
$(function () {
    // Initialize bucket fields
    $(load_bucket(url_base));

    $("#bucketSelect").change(function() {
        $("#bucketTitle").val(this.value);
        $("#bucketDescription").val(this.value);
    });

    // Initialize buttons to create & delete buckets
    $("#createDemoBucket").click(function () {
        create_demo_bucket(url_base, user_id);
    });
    $("#deleteAllBuckets").click(function () {
        delete_buckets(url_base, user_id);
    });

    // Reload bucket when clicking button
    $("#search-form").submit(function (event) {
        event.preventDefault();
        $("#document-container").html("");
        $("#error-alert").addClass("hidden");
        load_buckets(url_base, user_id);
    });

});

function load_buckets(path, user_id) {
    $.ajax({
               url: path + "/buckets?userId=" + user_id,
               type: 'GET',
               dataType: 'json',
               success: function (data) {
                   show_bucket(data);
                   populate_dropdown(data);
               },
               error: show_error("Path " + path + " could not be loaded")
           });
}

function show_bucket(data) {
    $.each(data, function (key, bucket) {
        $.each(bucket.drops, function (key, drop) {
            var div_start = "<div class='col-md-4'>";
            var thumbnail = "<img src='" + drop.photo[0].source + "' height='150' width='200'/>";
            var title = "<h2>" + drop.title + "</h2>";
            var description = "<p>" + drop.description + "</p>";
            var type = "<h3>" + drop.dropType.type + "</h3>";
            var div_end = "</div>";

            var overview_card = div_start + title + thumbnail + description + type + div_end;

            $("#bucket-container").append(overview_card);
        })
    });
}

function show_error(error_msg) {
    $("#error-alert").removeClass("hidden").find("p").text(error_msg);
}

function create_demo_bucket(path) {
    $.ajax({
               url: path + "/buckets/createDemoBucket?userId=tbisiar",
               type: 'GET',
               dataType: 'json',
               success: function (data) {
                   show_bucket(data);
                   populate_dropdown(data);
               },
               error: show_error("Path " + path + " could not be loaded")
           });
}

function delete_buckets(path, user_id) {
    $.ajax({
               url: path + "/buckets/format?userId=" + user_id,
               type: 'GET',
               dataType: 'json',
               success: function (data) {
                   show_bucket(data);
                   populate_dropdown(data);
               },
               error: show_error("Path " + path + " could not be reached for delete")
           });
}

/* Populate select dropdown options */
function populate_dropdown(data) {
    $("#editBucket").empty().append('<option value="null">Create New</option>');
    $.each(data, function () {
        $("#editBucket").append('<option value="' + this.id + '">' + this.title + '</option>');
    })
}