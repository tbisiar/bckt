/**
 * Customized javascript for bckt operations
 * Created by tbis163 on 6/02/17.
 */

var url_base = 'http://localhost:8080';
var user_id = 'tbisiar';
// Load the bucket once the page has loaded
$(load_all_mongo_objects_for_user('bucket'));

// Create bucket when create demo bucket button clicked
$(function () {

    $('#bucketSelect').on('change', function () {
        load_mongo_object('bucket', this.value);
        enable_save_delete('bucket', this.value);
    });

    $('#bucketDropSelect').on('change', function () {
        add_bucket_drop(this.id, this.value);
    });

    $('#dropSelect').on('change', function () {
        load_mongo_object('drop', this.value);
        enable_save_delete('drop', this.value);
    });

    // Initialize buttons to create & delete buckets
    $('#createDemoBucket').on('click', function () {
        create_demo_bucket();
    });
    $('#deleteAllBuckets').on('click', function () {
        delete_all_buckets();
    });

    // Initialize buttons to save or delete
    $('#bucketSave').on('click', function (e) {
        e.preventDefault();
        save_mongo_object('bucket');
    });
    $('#bucketDelete').on('click', function (e) {
        e.preventDefault();
        delete_mongo_object('bucket');
    });
    $('#dropSave').on('click', function(e) {
        e.preventDefault();
        save_mongo_object('drop');
    });
    $('#dropDelete').on('click', function(e) {
        e.preventDefault();
        delete_mongo_object('drop');
    });

    // Re-enable bucket save button when a title is input
    $('#bucketTitle').on('keyup', function () {
        enable_save_delete('bucket', $('#bucketSelect').value);
    });

    $('#dropTitle').on('keyup', function() {
        enable_save_delete('drop', $('#dropSelect').value);
    })

});

function load_all_mongo_objects_for_user(type) {
    var url = url_base + '/'+type+'s?userId=' + user_id;
    $.ajax({
               url: url,
               type: 'GET',
               dataType: 'json',
               success: function (data) {
                   populate_select(type, data);
                   if(type==='drop') {
                       populate_select('bucketDrop',data);
                   }
               },
               error: show_error('Path ' + url + ' could not be loaded')
           });
}

function load_mongo_object(type, id) {
    if (id === undefined || id === "") {
        update_form(type, []);
    } else {
        var url = url_base + '/' + type + 's?userId=' + user_id + '&' + type + 'Id=' + id;
        $.ajax({
                   url: url,
                   type: 'GET',
                   dataType: 'json',
                   success: function (data) {
                       if (data.length === 1) {
                           update_form(type, data[0]);
                       } else if (data.length === 0) {
                           show_error("No Data Returned for id " + id);
                       } else {
                           show_error("More than one item returned for id " + id);
                       }
                   },
                   error: show_error('Path ' + url + ' could not be loaded')
               });
    }
}

function show_bucket(data) {
    var bucket_container = $('#bucket-container');
    $.each(data, function (key, bucket) {
        $.each(bucket.drops, function (key, drop) {
            var div_start = '<div class="col-md-4">';
            var thumbnail = '<img src="' + drop.photo[0].source + '" height="150" width="200"/>';
            var title = '<h2>' + drop.title + '</h2>';
            var description = '<p>' + drop.description + '</p>';
            var type = '<h3>' + drop.dropType.type + '</h3>';
            var div_end = '</div>';

            var overview_card = div_start + title + thumbnail + description + type + div_end;

            bucket_container.append(overview_card);
        })
    });
}

function save_mongo_object(type) {
    var data = {};
    var id = $('#'+type+'Id').val();
    data['id'] = id;
    data['title'] = $('#'+type+'Title').val();
    data['description'] = $('#'+type+'Description').val();

    $('#'+type+'Save').prop('disabled', true);

    var url = url_base + '/'+type+'s/save?userId=' + user_id;
    $.ajax({
               url: url,
               type: 'POST',
               dataType: 'json',
               data: JSON.stringify(data),
               timeout: 600000,
               contentType: 'application/json',
               success: function (data) {
                   if(id===undefined) {
                       id = data.id;
                   }
                   populate_select(type, data, id); // TODO: provide id of saved bucket
                   $('#'+type+'Save').prop('disabled', false);
               },
               error: function () {
                   show_error('The '+type+' could not be saved at path ' + url);
                   $('#'+type+'Save').prop('disabled', false);
               }
           });
}

function create_demo_bucket() {
    var url = url_base + '/buckets/createDemoBucket';
    $.ajax({
               url: url,
               type: 'GET',
               dataType: 'json',
               success: function (data) {
                   show_bucket(data);
                   populate_select('bucket', data);
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
                   populate_select('bucket', data);
               },
               error: show_error('Path ' + url + ' could not be reached for delete')
           });
}

function delete_mongo_object(type) {
    var url = url_base + '/'+type+'s/format/' + $('#'+type+'Id').val() + '?userId=' + user_id;
    $.ajax({
               url: url,
               type: 'DELETE',
               success: function (data) {
                   populate_select(type, data);
               },
               error: show_error('Path ' + url + ' could not be reached for delete')
           })
}

function populate_select(type, data, selected_id) {
    var dropdown = $('#'+type+'Select');
    var dropdownDefault = 'Create New';
    if(type==='bucket') {
        load_all_mongo_objects_for_user('drop');
    } else if(type==='bucketDrop') {
        dropdownDefault = 'Select Drop to add';
    }
    dropdown.empty().append($('<option/>').val(undefined).text(dropdownDefault));
    if(data!==undefined) {
        $.each(data, function () {
            dropdown.append($('<option/>').val(this.id).text(this.title));
        });
    }
    if (selected_id !== undefined) {
        dropdown.val(selected_id);
    }
}

function enable_save_delete(type, selected_id) {
    if (selected_id === undefined) {
        $('#' + type + 'Save').prop('disabled', !$('#' + type + 'Title').val());
        $('#' + type + 'Delete').prop('disabled', true);
    } else {
        $('#' + type + 'Save').prop('disabled', false);
        $('#' + type + 'Delete').prop('disabled', false);
    }
}

function show_error(error_msg) {
    $('#error-alert').removeClass('hidden').find('p').text(error_msg);
}

/* Populate bucket form */
function update_form(type, data) {
    $('#' + type + 'Id').val(data.id);
    $('#' + type + 'Title').val(data.title);
    $('#' + type + 'Description').val(data.description);
}

function add_bucket_drop(drop_id, drop_name) {
    $('#bucketDropList').append(
        $('<li>').attr('id', drop_id).append(drop_name)
    );
}