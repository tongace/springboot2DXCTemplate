//<![CDATA[
    'use strict'
    let WDXC0002 = (function ($) {
        return {
        }
    }(jQuery));

$(document).ready(async function () {
    $.fn.dataTable.moment('DD/MM/YYYY HH:mm:ss');
    $("#tablePersonalInfoHeaderResult").DataTable();
    $('#birthDate').calendar({
        type: 'date'
    });
    //button
    let WDXC0002SearchClick;
    $('#WDXC0002Search').on('click', _.debounce(function (event) {
        let personalInfoTable = $("#tablePersonalInfoHeaderResult").DataTable();
        personalInfoTable.state.clear();
        personalInfoTable.destroy();
        if ($('#personalInfoForm').form('validate form')) {
            let formData = $('#personalInfoForm').form('get values');
            //WDXC0002.populatePersonalInfoDatatable(formData);
        }

        return false;
    }, 300, true));

    let WDXC0002CancelClick;
    $('#WDXC0002Cancel').on('click', _.debounce(function (event) {
        event.preventDefault();
        let modal = DXCUtils.comfirmModal('[[#{MBX0000MACFM}]]', null,
            function () {
                $('#personalInfoEditSection').fadeOut(600, function () {
                    $('#personalInfoSearchSection').fadeIn(600);
                });
            });
        modal.modal('show');
    }, 300, true));

    let WDXC0002AddClick;
    $('#WDXC0002Add').on('click', _.debounce(function (event) {
        event.preventDefault();
        $('#personalInfoSearchSection').fadeOut(600, function () {
            $('#personalInfoEditSection').fadeIn(600);
        });
        $('#editPersonalInfoForm').find('input[name="citizenID"]').parent().removeClass("readonly");
        $('#editPersonalInfoForm').find('input[name="citizenID"]').prop("readonly", false);
        $('#editPersonalInfoForm').form('reset');
        // set not used for field1,2,3 label and mode
        $('#editPersonalInfoForm').form('set values', {
            mode: DXCUtils.MODE_ADD
        });

        return false;
    }, 300, true));
});
//]]'>