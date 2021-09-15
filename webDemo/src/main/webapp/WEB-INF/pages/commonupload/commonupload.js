//<![CDATA[
'use strict'
$(document).ready(async function () {
    $.fn.dataTable.moment('DD/MM/YYYY HH:mm');
    $("#tableVerificationResult").DataTable({
        "order": [
            [0, "desc"]
        ]
    });
});
//]]'>