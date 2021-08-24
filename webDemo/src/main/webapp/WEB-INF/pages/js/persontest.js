//<![CDATA[
'use strict'

let WDXC0002 = (function ($) {
    return {

        populatePersonHeaderDatatable: async function (formData) {
            $.LoadingOverlay('show');
            const responseData = null;
            // datatable  
            let tablePersonHeaderResult = $("#tablePersonHeaderResult").DataTable({
                "data": responseData,
                "destroy": true,
                "lengthChange": false,
                "buttons": [{
                    text: '<i class="green file excel outline icon">',
                    titleAttr: 'Export to Excel',
                    extend: 'excelHtml5',
                    title: 'Person Header Table',
                    exportOptions: {
                        columns: [1, 2, 3, 4, 5, 6, 7, 8, 9]
                    }
                }, 'pageLength'],
                "order": [
                    [1, "asc"]
                ],
                "columns": [{
                    "data": "gimType",
                    "searchable": false,
                    "orderable": false,
                    "className": "dt-body-center",
                    "render": function (data, type, row) {
                        return ('<input type="checkbox" name="chkPersoneader" value="' + data + '"/>');
                    },
                    "title": '<a href="#" onclick="return WDXC0001.clearHeaderCheckBox()"><i class="large square outline icon"/></a>'
                },
                {
                    "data": "citizenId",
                    "orderable": true,
                    "searchable": true,
                    "className": "dt-body-left"
                },
                {
                    "data": "firstName",
                    "orderable": true,
                    "searchable": true
                },
                {
                    "data": "lastName",
                    "orderable": true,
                    "className": "dt-body-right"
                },
                {
                    "data": "birthDate",
                    "orderable": true,
                    "searchable": true
                },
                {
                    "data": "address",
                    "orderable": true,
                    "searchable": true
                },
                {
                    "data": "field3Label",
                    "orderable": true,
                    "searchable": true
                },
                {
                    "data": "picture",
                    "orderable": true,
                    "className": "dt-body-center"
                },
                {
                    "data": "modifiedBy",
                    "orderable": true
                },
                {
                    "data": "modifiedDt",
                    "orderable": true,
                    "render": function (data, type, row) {
                        return DXCUtils.formatDate(data, "DD/MM/YYYY HH:mm:ss");
                    }
                }
                ],
                "initComplete": function (settings, json) {
                    $.LoadingOverlay('hide');
                }
            });
            tablePersonHeaderResult.buttons().container().appendTo($('div.eight.column:eq(0)', tablePersonHeaderResult.table().container()));
        },


    }
}(jQuery));






$(document).ready(async function () {
    $.fn.dataTable.moment('DD/MM/YYYY HH:mm:ss');
    $("#WDXC0002Edit").hide();
    $("#WDXC0002Delete").hide();

    $('#WDXC0002Search').on('click', _.debounce(function (event) {
        $("#WDXC0002Edit").show();
        $("#WDXC0002Delete").show();
        $("#searchResultSection").show();

        return false;
    }, 300, true));


    $('#WDXC0002Clear').on('click', _.debounce(function (event) {
        $("#WDXC0002Edit").hide();
        $("#WDXC0002Delete").hide();
        $("#searchResultSection").hide();

        return false;
    }, 300, true));

    $('#WDXC0002Add').on('click', _.debounce(function (event) {
        $("#personSearchSection").hide();
        $("#searchResultSection").hide();
        $("#pesonenEditSection").show();

        return false;
    }, 300, true));

    $('#WDXC0002Save').on('click', _.debounce(function (event) {
        $("#WDXC0002Search").show();
        $("#WDXC0002Clear").show();
        $("#WDXC0002Add").show();
        $("#WDXC0002Edit").show();
        $("#WDXC0002Delete").show();
        $("#personSearchSection").show();
        $("#searchResultSection").show();
        $("#pesonenEditSection").hide();

        return false;
    }, 300, true));


    $('#WDXC0002Cancel').on('click', _.debounce(function (event) {
        $("#WDXC0002Search").show();
        $("#WDXC0002Clear").show();
        $("#WDXC0002Add").show();
        $("#WDXC0002Edit").show();
        $("#WDXC0002Delete").show();
        $("#personSearchSection").show();
        $("#searchResultSection").show();
        $("#pesonenEditSection").hide();

        return false;
    }, 200, true));


});



//]]'>