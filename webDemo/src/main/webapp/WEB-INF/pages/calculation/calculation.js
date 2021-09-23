//<![CDATA[
'use strict'
$(document).ready(async function () {
    $.fn.dataTable.moment('DD/MM/YYYY HH:mm');
    $("#pendingProjectTable").DataTable({
        "order": [
            [1, "desc"]
        ]
    });
    $("#calPendingProjectTable").DataTable({
        "order": [
            [1, "desc"],
            [0, "asc"]
        ]
    });
    $("#tableVerificationAndResult").DataTable({
        "order": [
            [4, "desc"],
            [5, "desc"]
        ]
    });
    $('#requestFormSection').find(':input').prop('disabled', true);
    $('#requestFormSection').find('.button').addClass('disabled');

    $('#step20Section').find(':input').prop('disabled', true);
    $('#step20Section').find('.button').addClass('disabled');

    $('#step30Section').find(':input').prop('disabled', false);
    $('#step30Section').find('.button').removeClass('disabled');

    // $('#step33Section').find(':input').prop('disabled', false);
    // $('#step33Section').find('.button').removeClass('disabled');

    $('#step40Section').find('.button').removeClass('disabled');
    // $('#WDR01131IgnoreSimulation').removeClass('disabled');

    $('#calculationSection').hide();
    $('#initialSection').show();
});
//]]'>