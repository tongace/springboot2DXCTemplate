//<![CDATA[
'use strict'
let WDXC0001_API = (function ($) {
    return {
        getGimTypeCombo: () => DXCUtils.callAPI("/demo/combo/gimtypecombo", "GET"),
        getActiveFlagCombo: () => DXCUtils.callAPI("/demo/combo/activeflagcombo", "GET"),
        searchGimHeader: formData => DXCUtils.callAPI("/demo/gimmaster/gimheader", "POST", formData),
        saveGimHeader: formData => DXCUtils.callAPI("/demo/gimmaster/gimheader", "PUT", formData),
        updateGimHeader: formData => DXCUtils.callAPI("/demo/gimmaster/gimheader", "PATCH", formData),
        searchGimDetailByType: gimType => DXCUtils.callAPI("/demo/gimmaster/gimdetail/" + gimType, "GET"),
        saveGimDetail: formData => DXCUtils.callAPI("/demo/gimmaster/gimdetail", "PUT", formData),
        updateGimDetail: formData => DXCUtils.callAPI("/demo/gimmaster/gimdetail", "PATCH", formData),
        deleteGimDetail: formData => DXCUtils.callAPI("/demo/gimmaster/gimdetail", "DELETE", formData)
    }
}(jQuery));
//]]'>