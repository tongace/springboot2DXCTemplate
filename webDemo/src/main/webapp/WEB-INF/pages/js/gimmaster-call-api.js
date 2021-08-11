//<![CDATA[
let WDXC0001_API = (function ($) {
    return {
        getGimTypeCombo: function () {
            return DXCUtils.callAPI("/demo/combo/gimtypecombo","GET")
        },
        getActiveFlagCombo: function () {
            return $.ajax({
                "async": true,
                "url": "/demo/combo/activeflagcombo",
                "method": "GET",
                "cache": false
            });
        },
        searchGimHeader: function (formData) {
            return $.ajax({
                "async": true,
                "url": "/demo/gimmaster/gimheader",
                "type": "POST",
                "contentType": "application/json; charset=utf-8",
                "data": JSON.stringify(formData),
                "cache": false
            });
        },
        saveGimHeader: function (formData) {
            return $.ajax({
                "async": true,
                "url": "/demo/gimmaster/gimheader",
                "type": "PUT",
                "contentType": "application/json; charset=utf-8",
                "data": JSON.stringify(formData),
                "cache": false
            });
        },
        searchGimDetail: function (formData) {
            return $.ajax({
                "async": true,
                "url": "/demo/gimmaster/gimdetail",
                "type": "POST",
                "contentType": "application/json; charset=utf-8",
                "data": JSON.stringify(formData),
                "cache": false
            });
        },
        saveGimDetail: function(formData) {
            return $.ajax({
                "async": true,
                "url": "/demo/gimmaster/gimdetail",
                "type": "PUT",
                "contentType": "application/json; charset=utf-8",
                "data": JSON.stringify(formData),
                "cache": false
            })
        }
    }
}(jQuery));
//]]'>