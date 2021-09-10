//<![CDATA[
'use strict'
$(document).ready(async function () {
    $.fn.dataTable.moment('DD/MM/YYYY HH:mm');
    $('#reportName').dropdown({
        forceSelection: false
    });
    $("#tableDownloadResult").DataTable({});
    $('#pprExporter').dropdown({
        forceSelection: false
    });
    $('#pprImporter').dropdown({
        forceSelection: false
    });
    $('#pprModel').dropdown({
        forceSelection: false
    });
    $('#prrEffiveDateFromCal').calendar({
        type: 'date',
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
        maxDate: moment().startOf('day').toDate(),
        today: true,
        monthFirst: false
    });
    $('#prrEffiveDateToCal').calendar({
        type: 'date',
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
        maxDate: moment().startOf('day').toDate(),
        today: true,
        monthFirst: false
    });
    $('#historyProjectId').dropdown({
        preserveHTML: false,
        apiSettings: {
            cache: false,
            url: '/demo/combo/dropdown?messageCode={query}',
            onResponse: function (response) {
                let comboResult = {};
                comboResult.success = true;
                comboResult.results = response.data;
                return comboResult;
            }
        },
        clearable: true,
        minCharacters: 1,
        ignoreCase: true,
        saveRemoteData: false,
        forceSelection: false
    });
});
//]]'>