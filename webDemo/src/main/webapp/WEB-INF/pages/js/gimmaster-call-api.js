//<![CDATA[
'use strict'
let WDXC0001_API = (function ($) {
    return {
        getGimTypeCombo: function () {
            return DXCUtils.callAPI("/demo/combo/gimtypecombo","GET");
        },
        getActiveFlagCombo: function () {
            return DXCUtils.callAPI("/demo/combo/activeflagcombo","GET");
        },
        searchGimHeader: function (formData) {
            return DXCUtils.callAPI("/demo/gimmaster/gimheader","POST",formData);
        },
        saveGimHeader: function (formData) {
            return DXCUtils.callAPI("/demo/gimmaster/gimheader", "PUT",formData);
        },
        searchGimDetail: function (formData) {
            return DXCUtils.callAPI("/demo/gimmaster/gimdetail", "POST",formData);
        },
        saveGimDetail: function(formData) {
            return DXCUtils.callAPI("/demo/gimmaster/gimdetail", "PUT",formData);
        }
    }
}(jQuery));
//]]'>