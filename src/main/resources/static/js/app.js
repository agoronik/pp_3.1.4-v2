var get_user = function(id) {
    return fetch('/api/user/' + id, {method: 'GET'});
}

var clean_froms = function(o) {
    $('input[name^="d_"],input[name^="e_"]').each(function(){
        $(this).val("");
    })
    $('select[name^="d_"],select[name^="e_"]').find('option').each(function() {
        $(this).prop('selected', false);
    })
}

var initial_table = function() {
    clean_froms();
    window.location.reload();
}

var obj_id = 0;
$(document).on('click', 'button.modal-delete', function(){
    obj_id = $(this).attr('data-id');

    get_user(obj_id)
        .then(function (response) {
            response.json().then(function(user) {
                console.log(user);
                $('[name="d_firstName"]').val(user.firstName);
                $('[name="d_lastName"]').val(user.lastName);
                $('[name="d_email"]').val(user.email);
                $('[name="d_age"]').val(user.age);

                $.each(user.roles, function(k,v){
                    $('[name="d_roles"]').find('option[value="'+v.id+'"]').prop('selected', 'selected');
                })

                $('#modal-delete').modal('show');
            })
        })
})

var edit_obj_id = 0;

$(document).on('click', 'button.modal-edit', function(){
    edit_obj_id = $(this).attr('data-id');

    get_user(edit_obj_id)
        .then(function (response) {
            response.json().then(function(user) {
                console.log(user);
                $('[name="e_firstName"]').val(user.firstName);
                $('[name="e_lastName"]').val(user.lastName);
                $('[name="e_email"]').val(user.email);
                $('[name="e_age"]').val(user.age);
                $('[name="e_password"]').val(user.password);

                $.each(user.roles, function(k,v){
                    $('[name="e_roles"]').find('option[value="'+v.id+'"]').prop('selected', 'selected');
                })

                $('#modal-edit').modal('show');
            })
        })
})
$(document).on('click', 'button.delete-user', function(){
    $('.modal').modal('hide');
    fetch('/api/user/del/' + obj_id, {method: 'GET'})
        .then(function(response){
            console.log(response.json());
        })
    obj_id = 0;
    initial_table();
    return false;
})

$(document).on('click', 'button.edit-user', function(){
    var data = {};
        data.roles = [];
    $('[name^="e_"]').each(function(){
        var n = $(this).prop('name').split('_');
        if(n[1] != 'roles') {
            data[n[1]] = $(this).val();
        } else {
            $(this).find('option:selected').each(function () {
                data.roles.push(parseInt($(this).attr('value')));
            })
        }
    })

    var headers = new Headers();
    headers.append('Content-Type', 'application/json; charset=utf-8');

    var request = new Request('/api/user/' + edit_obj_id, {
        method: 'POST',
        headers: headers,
        body: JSON.stringify(data)
    });

    console.log(data);

    fetch(request);

    edit_obj_id = 0;
    $('.modal').modal('hide');
    initial_table();
    return false;
})
