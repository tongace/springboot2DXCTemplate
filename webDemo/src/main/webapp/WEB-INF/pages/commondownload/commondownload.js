//<![CDATA[
'use strict'
$(document).ready(async function () {
    $.fn.dataTable.moment('DD/MM/YYYY HH:mm');
    $('#reportName').dropdown({
        forceSelection: false
    });
    $("#tableDownloadResult").DataTable({
        "order": [
            [3, "desc"],
            [4, "desc"]
        ]
    });
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
        monthFirst: false,
        endCalendar: $('#prrEffiveDateToCal'),
        closable: false
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
        monthFirst: false,
        startCalendar: $('#prrEffiveDateFromCal'),
        closable: false
    });
    $('#prrEffiveDateToCal').calendar('set date', moment().startOf('day').toDate(), true, true);
    $('#prrEffiveDateFromCal').calendar('set date', moment().subtract(1, 'years').toDate(), true, true);

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