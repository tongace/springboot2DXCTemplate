//<![CDATA[
'use strict'
let WDXC0002 = (function ($) {
    return {
        saveUserData: async  function(formData,mode) {
            let saveResp = {};
            if(mode=='add'){
                saveResp = await WDXC0002_API.addUser(formData);
            }else{
                saveResp = await WDXC0002_API.updateUser(formData);
            }
            console.log('saveResp:'+saveResp);
            if (saveResp.message == null || saveResp.message == '') {
                $('#userEditSection').fadeOut(600, function () {
                    $('#userSearchSection').fadeIn(600);
                });
                // set save message
                if (saveResp.rowCount > 0) {
                    let modal = DXCUtils.alertModal('[[#{MBX00005AINF}]]', null);
                    modal.modal('show');
                } else {
                    let modal = DXCUtils.alertModal('[[#{MBX00009AERR}]]', null);
                    modal.modal('show');
                }
                let formData = $('#userSearchForm').form('get values');
                WDXC0002.populateUserDatatable(formData);

            } else {
                let modal = DXCUtils.alertModal(saveResp.message, null);
                modal.modal('show');
            }
        },
        deleteUserData: async  function(formData) {
            let saveResp =  await WDXC0002_API.deleteUser(formData);
            console.log('saveResp:'+saveResp);
            if (saveResp.message == null || saveResp.message == '') {
                // set save message
                if (saveResp.rowCount > 0) {
                    let modal = DXCUtils.alertModal('[[#{MBX00004AINF}]]', null);
                    modal.modal('show');
                } else {
                    let modal = DXCUtils.alertModal('[[#{MBX00009AERR}]]', null);
                    modal.modal('show');
                }
                let formData = $('#userSearchForm').form('get values');
                WDXC0002.populateUserDatatable(formData);

            } else {
                let modal = DXCUtils.alertModal(saveResp.message, null);
                modal.modal('show');
            }
        },
        populateUserDatatable: async function (formData) {
            $.LoadingOverlay('show');
//            const responseData = {"message":null,"rowCount":null,
//                                 "data":[{"citizenId":1547581522,"firstName":"udom","lastName":"show one","dateOfBirth":1629151357000
//                                 ,"address":"bangkok","picture":"xxx","createdBy":"csamphao","createdDt":1629751357000,"modifiedBy":"csamphao","modifiedDt":1629751357000}]};
            const responseData = await WDXC0002_API.searchUser(formData);
            if ($.isEmptyObject(responseData.data) == false) {
                $("#WDXC0002Edit").show();
                $("#WDXC0002Delete").show();
            } else {
                $("#WDXC0002Edit").hide();
                $("#WDXC0002Delete").hide();
            }
            if (S(responseData.message).isEmpty() == false) {
                let modal = DXCUtils.alertModal(responseData.message, null);
                modal.modal('show');
                $('#searchResultSection').hide();
            } else {
                $('#searchResultSection').show();
            }
            console.log(JSON.stringify(responseData.data));
            // datatable
            let userTable = $("#tableUserResult").DataTable({
                "data": responseData.data,
                "destroy": true,
                "lengthChange": false,
                "buttons": [{
                    text: '<i class="green file excel outline icon">',
                    titleAttr: 'Export to Excel',
                    extend: 'excelHtml5',
                    title: 'User Table',
                    exportOptions: {
                        columns: [1, 2, 3, 4, 5, 6, 7, 8,9]
                    }
                }, 'pageLength'],
                "order": [
                    [1, "asc"]
                ],
                "columns": [{
                        "data": "citizenId",
                        "searchable": false,
                        "orderable": false,
                        "className": "dt-body-center",
                        "render": function (data, type, row) {
                            return ('<input type="checkbox" name="chkCitizenId" value="' + data + '"/>');
                        },
                        "title": '<a href="#" onclick="return WDXC0002.clearHeaderCheckBox()"><i class="large square outline icon"/></a>'
                    },
                    {
                        "data": "citizenId",
                        "orderable": true,
                        "searchable": true,
                    },
                    {
                        "data": "firstName",
                        "orderable": true,
                        "searchable": true
                    },
                    {
                        "data": "lastName",
                        "orderable": true,
                        "searchable": true
                    },
                    {
                        "data": "dateOfBirth",
                        "orderable": true,
                        "render": function (data, type, row) {
                            return DXCUtils.formatDate(data, "DD/MM/YYYY");
                        }
                    },
                    {
                        "data": "address",
                        "orderable": true,
                        "searchable": true
                    },
                    {
                        "data": "pictureId",
                        "orderable": true,
                        "searchable": true,
                        "render": function (data, type, row,meta) {
                            return '<a href="#" onclick="return WDXC0002.displayUserPicture('+data+');">' + row.firstName + '</a>';
                       }
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
                "initComplete": function(settings, json) {
                    $.LoadingOverlay('hide');
                }
            });
            userTable.buttons().container().appendTo($('div.eight.column:eq(0)', userTable.table().container()));
        },
        displayUserPicture: async  function(pictureId) {
            const responseData =  await WDXC0002_API.searchUserPicture(pictureId);
            console.log('responseData:'+responseData);
            const data =  await DXCUtils.blobToBase64Url(responseData);
            console.log('data:'+data);

            $('#userPic').attr('src', data);
            $('#userLink').attr('href', data);
            //$('#userLink').attr('download', file.name);
            $('#imageModal').modal({
                selector: {
                    close: '#modalUploadOK'
                },
                duration: 0
            }).modal('show');;

        },
        displayUserEditPicture: async  function(pictureId) {
            const responseData =  await WDXC0002_API.searchUserPicture(pictureId);
            console.log('responseData:'+responseData);
            const data =  await DXCUtils.blobToBase64Url(responseData);
            console.log('data:'+data);

            $('#uploadPic').attr('src',data);
            $('#uploadLink').attr('href',data);
            //$('#uploadLink').attr('download',file.name);
            $('#uploadCard').show();

        },
        clearHeaderCheckBox: function () {
            let userTable = $("#tableUserResult").DataTable();
            userTable.$('[name="chkGimHeader"]').prop('checked', false);
        },
        blobToData : function (blob) {
            return new Promise((resolve) => {
              const reader = new FileReader()
              reader.onloadend = () => resolve(reader.result)
              reader.readAsDataURL(blob)
            });
        }
    }
}(jQuery));

$(document).ready(async function () {
    //initial button and search criteria
    let mode = '';
    $.fn.dataTable.moment('DD/MM/YYYY HH:mm:ss');
    $("#WDXC0002Edit").hide();
    $("#WDXC0002Delete").hide();
    // end initial button and search criteria

    // button action
    let WDXC0002SearchClick;
    $('#WDXC0002Search').on('click', _.debounce(function (event) {
        let userTable = $("#tableUserResult").DataTable();
        userTable.state.clear();
        userTable.destroy();
        if ($('#userSearchForm').form('validate form')) {
            let formData = $('#userSearchForm').form('get values');
            WDXC0002.populateUserDatatable(formData);
        }

        return false;
    }, true));

    let WDXC0002SaveClick;
    $('#WDXC0002Save').on('click', _.debounce(function (event) {
        event.preventDefault();
        console.log('mode:'+mode);
        function saveCallback() {
            let userFormData = $('#editUserForm').form('get values');
            console.log('formData:'+userFormData);
            console.log(JSON.stringify(userFormData));
            const file = $('#uploadFile').prop("files")[0];
            let formData = new FormData();

            let userBlob = new Blob([JSON.stringify(userFormData)], {
                type: "application/json"
            });

            formData.append("userDTO", userBlob);
            if(file){
                 formData.append("userPic", file, file.name);
            }else{
                formData.append("userPic", new Blob());
            }

            console.log(formData);
            console.log(JSON.stringify(formData));

            WDXC0002.saveUserData(formData,mode);
        }

        if ($('#editUserForm').form('validate form')) {
            let modal = DXCUtils.comfirmModal('[[#{MSTD0006ACFM}]]', null, saveCallback);
            modal.modal('show');
        }
        return false;
    }, 300, true));

    let WDXC0002CancelClick;
    $('#WDXC0002Cancel').on('click', _.debounce(function (event) {
        event.preventDefault();
        let modal = DXCUtils.comfirmModal('[[#{MBX0000MACFM}]]', null,
            function () {
                $('#userEditSection').fadeOut(600, function () {
                    $('#userSearchSection').fadeIn(600);
                });
            });
        modal.modal('show');
    }, 0, true));

    let WDXC0002AddClick;
    $('#WDXC0002Add').on('click', _.debounce(function (event) {
        event.preventDefault();
        $('#userSearchSection').fadeOut(600, function () {
            $('#userEditSection').fadeIn(600);
        });

         $('#dateOfBirthCalendar').calendar({type: 'date',startMode: 'year',
            formatter: {
                date: function (date, settting) {
                    return DXCUtils.formatDate(date, 'DD/MM/YYYY');
                }
            },
            parser: {
                date: function (text, settings) {
                    return DXCUtils.parseDate(text, 'DD/MM/YYYY');
                }
            },
            maxDate: moment().toDate(),
            today: true,
         });

        $('#editUserForm').find('input[name="citizenId"]').parent().removeClass("readonly");
        $('#editUserForm').find('input[name="citizenId"]').prop("readonly", false);
        $('#uploadPic').removeAttr('src');
        $('#uploadLink').removeAttr('href');
        $('#uploadLink').removeAttr('download');
        $('#uploadCard').hide();
        $('#editUserForm').form('reset');
        mode = 'add';
        return false;
    }, 300, true));

    let uploadFileClick;
    $('#uploadFile').on('change', async (e) => {
        const file = e.target.files[0];
        console.log(file);
        if(file.size> (100*1024)){
            alert('file more than 100kB');
            return;
        }
        let data = await WDXC0002.blobToData(file);
        console.log(data);
        $('#uploadPic').attr('src',data);
        $('#uploadLink').attr('href',data);
        $('#uploadLink').attr('download',file.name);
        $('#uploadCard').show();
    });

    let uploadDeleteClick;
	$('#uploadDelete').on('click', _.debounce(async (e) => {
		$('#uploadPic').removeAttr('src');
		$('#uploadLink').removeAttr('href');
		$('#uploadLink').removeAttr('download');
		$('#uploadCard').hide();
	}, 300, true));

    let WDXC0002EditClick;
    $('#WDXC0002Edit').on('click', _.debounce(function (event) {
        event.preventDefault();
        mode = 'edit';
        console.log('come edit');
        // check only one GIM Header data row to be edited
        let userTable = $('#tableUserResult').DataTable();
        let countChecked = userTable.rows().nodes().to$().find(':checked[name="chkCitizenId"]').length;

        console.log('countChecked:'+countChecked);

        if (countChecked != 1) {
            let modal = DXCUtils.alertModal('[[#{MSTD1017AERR}]]', null);
            modal.modal('show');
        } else {
            let editHeaderDataSelection = {};
            $.each(userTable.rows().nodes(), function (index, value) {
                if ($(value).find('[name="chkCitizenId"]').prop('checked') == true) {
                    editHeaderDataSelection = userTable.row(value).data();
                    return;
                }
            });

            console.log(JSON.stringify(editHeaderDataSelection));

             $('#dateOfBirthCalendar').calendar({type: 'date',startMode: 'year',
                formatter: {
                    date: function (date, settting) {
                        return DXCUtils.formatDate(date, 'DD/MM/YYYY');
                    }
                },
                parser: {
                    date: function (text, settings) {
                        return DXCUtils.parseDate(text, 'DD/MM/YYYY');
                    }
                },
                maxDate: moment().toDate(),
                today: true,
             });

            $('#editUserForm').find('input[name="citizenId"]').parent().addClass("readonly");
            $('#editUserForm').find('input[name="citizenId"]').prop("readonly", true);
            $('#uploadPic').removeAttr('src');
            $('#uploadLink').removeAttr('href');
            $('#uploadLink').removeAttr('download');
            $('#uploadCard').hide();
            $('#editUserForm').form('reset');

            $('#editUserForm').form('set values', editHeaderDataSelection);
            $('#dateOfBirthCalendar').calendar('set date', DXCUtils.formatDate(editHeaderDataSelection.dateOfBirth,'DD/MM/YYYY'), true, false);

            WDXC0002.displayUserEditPicture(editHeaderDataSelection.pictureId);

            $('#userSearchSection').fadeOut(600, function () {
                $('#userEditSection').fadeIn(600);
            });
        }

        return false;
    }, 300, true));

  let WDXC0002DeleteClick;
    $('#WDXC0002Delete').on('click', _.debounce(function (event) {
        event.preventDefault();

        console.log('come delete');
        // check only one User data row to be deleted
        let userTable = $('#tableUserResult').DataTable();
        let countChecked = userTable.rows().nodes().to$().find(':checked[name="chkCitizenId"]').length;

        console.log('countChecked:'+countChecked);

        if (countChecked == 0) {
            let modal = DXCUtils.alertModal('[[#{MSTD1017AERR}]]', null);
            modal.modal('show');
        } else {

            function deleteCallback() {
                let editHeaderDataSelection = [];
                $.each(userTable.rows().nodes(), function (index, value) {
                    if ($(value).find('[name="chkCitizenId"]').prop('checked') == true) {
                        editHeaderDataSelection.push(userTable.row(value).data());
                    }
                });

                console.log(JSON.stringify(editHeaderDataSelection));
                WDXC0002.deleteUserData(editHeaderDataSelection);
            }

            if ($('#editUserForm').form('validate form')) {
                let modal = DXCUtils.comfirmModal('[[#{MSTD0007ACFM}]]', null, deleteCallback);
                modal.modal('show');
            }
        }

        return false;
    }, 300, true));

    let WDXC0002ClearClick;
    $('#WDXC0002Clear').on('click', _.debounce(function (event) {
        event.preventDefault();
        $('#userSearchForm').form('reset');
        let datatable = $("#tableUserResult").DataTable();
        datatable.clear().draw();
        $("#WDXC0002Edit").hide();
        $("#WDXC0002Delete").hide();
        $("#searchResultSection").hide();

        return false;
    }, 300, true));
    // end button

    // form validation
    $('#userSearchForm').form({
        fields: {
            searchCitizenId: {
                identifier: 'searchCitizenId',
                rules: [{
                        type: 'maxLength[13]'
                    },
                    {
                        type: 'regExp[/^$|^[^\s]+(\s+[^\s]+)*$/]'
                    }
                ]
            },
            firstName: {
                identifier: 'firstName',
                rules: [{
                        type: 'maxLength[100]'
                    },
                    {
                        type: 'regExp[/^$|^[^\s]+(\s+[^\s]+)*$/]'
                    }
                ]
            }
        },
        inline: true,
        on: 'blur'
    });
    // modal validation
    $('#editUserForm').form({
        fields: {
            citizenId: {
                identifier: 'citizenId',
                rules: [{
                        type: 'empty'
                    },
                    {
                        type: 'space[this.value]',
                        prompt: '{name} should not be only white space or start or end with white space'
                    },
                    {
                        type: 'maxLength[13]'
                    }
                ]
            },
            firstName: {
                identifier: 'firstName',
                rules: [{
                        type: 'empty',
                    },
                    {
                        type: 'space[this.value]',
                        prompt: '{name} should not be only white space or start or end with white space'
                    },
                    {
                        type: 'maxLength[100]'
                    }
                ]
            },
            gimDesc: {
                identifier: 'lastName',
                rules: [{
                        type: 'empty',
                    },
                    {
                        type: 'space[this.value]',
                        prompt: '{name} should not be only white space or start or end with white space'
                    },
                    {
                        type: 'maxLength[100]'
                    }
                ]
            },
            dateOfBirth: {
                identifier: 'dateOfBirth',
                rules: [{
                        type: 'empty',
                    },
                    {
                        type: 'space[this.value]',
                        prompt: '{name} should not be only white space or start or end with white space'
                    }
                ]
            },
            uploadFile: {
                identifier: 'uploadFile',
                rules: [{
                        type: 'empty',
                    },
                    {
                        type: 'space[this.value]',
                        prompt: '{name} should not be only white space or start or end with white space'
                    },
                    {
                        type: 'maxLength[200]'
                    }
                ]
            }

        },
        inline: true,
        on: 'blur'
    });

    $.fn.form.settings.rules.validationGimCodeLenth = function (value) {
        let gimHeaderObj = JSON.parse($('#selectedGimHeaderDiv').text());
        return value.length <= gimHeaderObj.cdLength;
    };
    $.fn.form.settings.rules.validationField1 = function (value) {
        let gimHeaderObj = JSON.parse($('#selectedGimHeaderDiv').text());
        return (gimHeaderObj.field1Label == 'Not Used' || (gimHeaderObj.field1Label != 'Not Used' && value != ''));
    };
    $.fn.form.settings.rules.validationField2 = function (value) {
        let gimHeaderObj = JSON.parse($('#selectedGimHeaderDiv').text());
        return (gimHeaderObj.field2Label == 'Not Used' || (gimHeaderObj.field2Label != 'Not Used' && value != ''));
    };
    $.fn.form.settings.rules.validationField3 = function (value) {
        let gimHeaderObj = JSON.parse($('#selectedGimHeaderDiv').text());
        return (gimHeaderObj.field3Label == 'Not Used' || (gimHeaderObj.field3Label != 'Not Used' && value != ''));
    };
    $('#editGimDetailForm').form({
        fields: {
            gimType: {
                identifier: 'gimType',
                rules: [{
                        type: 'empty'
                    },
                    {
                        type: 'regExp[/\s*$/]'
                    },
                    {
                        type: 'regExp[/^\s*/]'
                    },
                    {
                        type: 'maxLength[50]'
                    }
                ]
            },
            gimCd: {
                identifier: 'gimCd',
                rules: [{
                        type: 'empty'
                    },
                    {
                        type: 'regExp[/^$|^[^\s]+(\s+[^\s]+)*$/]'
                    },
                    {
                        type: 'validationGimCodeLenth',
                        prompt: '[[#{MBX01005AERR}]]'
                    }
                ]
            },
            gimValue: {
                identifier: 'gimValue',
                rules: [{
                        type: 'empty'
                    },
                    {
                        type: 'regExp[/^$|^[^\s]+(\s+[^\s]+)*$/]'
                    },
                    {
                        type: 'maxLength[2000]'
                    }
                ]
            },
            field1: {
                identifier: 'field1',
                rules: [{
                        type: 'validationField1'
                    },
                    {
                        type: 'regExp[/^$|^[^\s]+(\s+[^\s]+)*$/]'
                    },
                    {
                        type: 'maxLength[4000]'
                    }
                ]
            },
            field2: {
                identifier: 'field2',
                rules: [{
                        type: 'validationField2',
                        prompt: '[[#{MBX01002AERR}]]'
                    },
                    {
                        type: 'regExp[/^$|^[^\s]+(\s+[^\s]+)*$/]'
                    },
                    {
                        type: 'maxLength[4000]'
                    }
                ]
            },
            field3: {
                identifier: 'field3',
                rules: [{
                        type: 'validationField3',
                        prompt: '[[#{MBX01002AERR}]]'
                    },
                    {
                        type: 'space[this.value]',
                        prompt: '{name} should not be only white space or start or end with white space'
                    },
                    {
                        type: 'maxLength[4000]'
                    }
                ]
            },
            activeFlag: {
                identifier: 'activeFlag',
                rules: [{
                    type: 'not[Select]',
                    prompt: '[[#{MBX01001AERR}]]'
                }]
            }
        },
        inline: true,
        on: 'blur'
    });
});
//]]'>