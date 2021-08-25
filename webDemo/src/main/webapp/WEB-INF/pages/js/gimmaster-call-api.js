//<![CDATA[
'use strict'
let WDXC0001_API = (function ($) {
    return {
        getGimTypeCombo: () => DXCUtils.callAPI("/demo/combo/gimtypecombo","GET"),
        getActiveFlagCombo: () => DXCUtils.callAPI("/demo/combo/activeflagcombo","GET"),
        searchGimHeader: formData => DXCUtils.callAPI("/demo/gimmaster/gimheader","POST",formData),
        saveGimHeader: formData => DXCUtils.callAPI("/demo/gimmaster/gimheader", "PATCH",formData),
        searchGimDetail: formData =>  DXCUtils.callAPI("/demo/gimmaster/gimdetail", "POST",formData),
        saveGimDetail: formData =>  DXCUtils.callAPI("/demo/gimmaster/gimdetail", "PUT",formData)
    }
}(jQuery));
//]]'>