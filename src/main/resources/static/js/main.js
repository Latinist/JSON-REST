$(document).ready(function () {

    $("#search-form").submit(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();
        fire_ajax_submit();

    });

});

function date_validation() {

    fDate = new Date(); // min Date
    cDate = new Date($("#birthday").val()); // date from form
    lDate = new Date();
    lDate.setYear(lDate.getFullYear() - 120);
    fDate.setYear(fDate.getFullYear() - 18);

    if (Date.parse(cDate) > Date.parse(lDate) && Date.parse(cDate) < Date.parse(fDate)) {
        return true;
    }
    return false;

}

function showWindow() {
    dialog.showModal();
}

function fire_ajax_submit() {

    if (date_validation()) {


        var search = {
            firstName: $("#firstname").val(),
            middleName: $("#middlename").val(),
            lastName: $("#lastname").val(),
            birthday: $("#birthday").val(),
            email: $("#email").val()
        }
        $("#btn-search").prop("disabled", true);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/rest/save",
            data: JSON.stringify(search),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (data) {

                var json = "<h4>Ajax Response</h4><pre>"
                    + JSON.stringify(data, null, 4) + "</pre>";
                $('#feedback').html(json);

                console.log("SUCCESS : ", data);
                $("#btn-search").prop("disabled", false);

            },
            error: function (e) {

                var json = "<h4>Ajax Response</h4><pre>"
                    + e.responseText + "</pre>";
                $('#feedback').html(json);

                console.log("ERROR : ", e);
                $("#btn-search").prop("disabled", false);

            }
        });

    } else {

        var $accountDeleteDialog = $('#dialog');

        event.preventDefault();
        jQuery.noConflict();
        $('#myModal').modal('show');
    }
}