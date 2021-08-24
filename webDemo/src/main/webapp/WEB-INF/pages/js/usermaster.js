//<![CDATA[
    'use strict'
    let WDXC0002 = (function ($) {
        return {
        }
    }(jQuery));

$(document).ready(async function () {
    $.fn.dataTable.moment('DD/MM/YYYY HH:mm:ss');
    $("#tableUserMasterHeaderResult").DataTable();
    $('#birthDateCal').calendar({
        type: 'date',
        formatInput: true
    });
    //button
    let WDXC0002SearchClick;
    $('#WDXC0002Search').on('click', _.debounce(function (event) {
        let userMasterTable = $("#tableUserMasterHeaderResult").DataTable();
        userMasterTable.state.clear();
        userMasterTable.destroy();
        if ($('#userMasterForm').form('validate form')) {
            let formData = $('#userMasterForm').form('get values');
            //WDXC0002.populateUserMasterDatatable(formData);
        }

        return false;
    }, 300, true));

    let WDXC0002CancelClick;
    $('#WDXC0002Cancel').on('click', _.debounce(function (event) {
        event.preventDefault();
        let modal = DXCUtils.comfirmModal('[[#{MBX0000MACFM}]]', null,
            function () {
                $('#userMasterEditSection').fadeOut(600, function () {
                    $('#userMasterSearchSection').fadeIn(600);
                });
            });
        modal.modal('show');
    }, 300, true));

    let WDXC0002AddClick;
    $('#WDXC0002Add').on('click', _.debounce(function (event) {
        event.preventDefault();
        $('#userMasterSearchSection').fadeOut(600, function () {
            $('#userMasterEditSection').fadeIn(600);
        });
        $('#editUserMasterForm').find('input[name="citizenID"]').parent().removeClass("readonly");
        $('#editUserMasterForm').find('input[name="citizenID"]').prop("readonly", false);
        $('#editUserMasterForm').form('reset');
        // set not used for field1,2,3 label and mode
        $('#editUserMasterForm').form('set values', {
            mode: DXCUtils.MODE_ADD
        });

        return false;
    }, 300, true));
});

const Counter = {
    data() {
        return {
        counter: 0
        }
    },
    mounted() {
        setInterval(() => {
        this.counter++
        }, 1000)
    }
}
  
Vue.createApp(Counter).mount('#counter')
//]]'>