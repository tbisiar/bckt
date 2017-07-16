/**
 * Customized javascript for bckt operations
 * Created by tbis163 on 6/02/17.
 */

var url_base = 'http://localhost:8080';
var user_id = 'tbisiar';
var CREATENEW = 'create-new';
// Load the bucket once the page has loaded
$(load_all_buckets_for_user());

// Create bucket when create demo bucket button clicked
$(function () {
    // Initialize bucket fields
    // $(load_buckets(url_base, user_id));

    $('#bucketSelect').change(function () {
        load_bucket(this.value);
        enable_save_delete_bucket(this.value);
    });

    // Initialize buttons to create & delete buckets
    $('#createDemoBucket').click(function () {
        create_demo_bucket();
    });
    $('#deleteAllBuckets').click(function () {
        delete_all_buckets();
    });

    // Initialize buttons to save or delete
    $('#saveBucket').click(function (e) {
        e.preventDefault();
        save_bucket();
    });
    $('#deleteBucket').click(function (e) {
        e.preventDefault();
        delete_bucket();
    });

    // Reload bucket when clicking button
    $('#search-form').submit(function (event) {
        event.preventDefault();
        $('#document-container').html('');
        $('#error-alert').addClass('hidden');
        load_all_buckets_for_user();
    });

    // Re-enable bucket save button when a title is input
    $('#bucketTitle').keyup(function () {
        enable_save_delete_bucket($('bucketSelect').value);
    });

});

function load_all_buckets_for_user() {
    var url = url_base + '/buckets?userId=' + user_id;
    $.ajax({
               url: url,
               type: 'GET',
               dataType: 'json',
               success: function (data) {
                   show_bucket(data);
                   populate_dropdown(data);
               },
               error: show_error('Path ' + url + ' could not be loaded')
           });
}

function load_bucket(bucket_id) {
    if (bucket_id === CREATENEW) {
        update_bucket_form([]);
    } else {
        var url = url_base + '/buckets?userId=' + user_id + '&bucketId=' + bucket_id;
        $.ajax({
                   url: url,
                   type: 'GET',
                   dataType: 'json',
                   success: function (data) {
                       if (data.length === 1) {
                           update_bucket_form(data[0]);
                       } else if (data.length === 0) {
                           show_error("No Data Returned for id " + bucket_id);
                       } else {
                           show_error("More than one bucket returned for id " + bucket_id);
                       }
                   },
                   error: show_error('Path ' + url + ' could not be loaded')
               });
    }
}

function show_bucket(data) {
    $.each(data, function (key, bucket) {
        $.each(bucket.drops, function (key, drop) {
            var div_start = '<div class="col-md-4">';
            var thumbnail = '<img src="' + drop.photo[0].source + '" height="150" width="200"/>';
            var title = '<h2>' + drop.title + '</h2>';
            var description = '<p>' + drop.description + '</p>';
            var type = '<h3>' + drop.dropType.type + '</h3>';
            var div_end = '</div>';

            var overview_card = div_start + title + thumbnail + description + type + div_end;

            $('#bucket-container').append(overview_card);
        })
    });
}

function save_bucket() {
    var data = {};
    data['id'] = $('#bucketId').val();
    data['title'] = $('#bucketTitle').val();
    data['description'] = $('#bucketDescription').val();

    $('#saveBucket').prop('disabled', true);

    var url = url_base + '/buckets/saveBucket?userId=' + user_id;
    $.ajax({
               url: url,
               type: 'POST',
               dataType: 'json',
               data: JSON.stringify(data),
               timeout: 600000,
               contentType: 'application/json',
               success: function (data) {
                   show_bucket(data);
                   populate_dropdown(data);
                   $('#saveBucket').prop('disabled', false);
               },
               error: function () {
                   show_error('The bucket could not be saved at path ' + url);
                   $('#saveBucket').prop('disabled', false);
               }
           });
}

function enable_save_delete_bucket(selected_bucket_id) {
    if (selected_bucket_id === 'create-new' || selected_bucket_id === undefined) {
        $('#saveBucket').prop('disabled', !$('#bucketTitle').value);
        $('#deleteBucket').prop('disabled', true);
    } else {
        $('#saveBucket').prop('disabled', false);
        $('#deleteBucket').prop('disabled', false);
    }
}

function show_error(error_msg) {
    $('#error-alert').removeClass('hidden').find('p').text(error_msg);
}

function create_demo_bucket() {
    var url = url_base + '/buckets/createDemoBucket';
    $.ajax({
               url: url,
               type: 'GET',
               dataType: 'json',
               success: function (data) {
                   show_bucket(data);
                   populate_dropdown(data);
               },
               error: show_error('Path ' + url + ' could not be loaded')
           });
}

function delete_all_buckets() {
    var url = url_base + '/buckets/format?userId=' + user_id;
    $.ajax({
               url: url,
               type: 'GET',
               dataType: 'json',
               success: function (data) {
                   show_bucket(data);
                   populate_dropdown(data);
               },
               error: show_error('Path ' + url + ' could not be reached for delete')
           });
}

function delete_bucket() {
    var url = url_base + '/buckets/format/' + $('#bucketId').val() + '?userId=' + user_id;
    $.ajax({
               url: url,
               type: 'DELETE',
               success: function (data) {
                   populate_dropdown(data);
               },
               error: show_error('Path ' + url + ' could not be reached for delete')
           })
}

/* Populate select dropdown options */
function populate_dropdown(data, selected_bucket_id) {
    var dropdown = $('#bucketSelect');
    dropdown.empty().append('<option value="create-new">Create New</option>');
    $.each(data, function () {
        dropdown.append('<option value="' + this.id + '">' + this.title + '</option>');
    });
    if (selected_bucket_id !== undefined) {
        dropdown.val(selected_bucket_id);
    }
}

/* Populate bucket form */
function update_bucket_form(bucket_data) {
    $('#bucketId').val(bucket_data.id);
    $('#bucketTitle').val(bucket_data.title);
    $('#bucketDescription').val(bucket_data.description);
}