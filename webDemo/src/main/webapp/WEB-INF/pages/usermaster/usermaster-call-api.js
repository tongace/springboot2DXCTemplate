//<![CDATA[
'use strict'
let WDXC0002_API = (function ($) {
    return {
        searchUser: formData => DXCUtils.callAPI("/demo/usermaster/user","POST",formData),
        addUser: formData => DXCUtils.callAPIWithUploadFile("/demo/usermaster/user", "PUT",formData),
        updateUser: formData => DXCUtils.callAPIWithUploadFile("/demo/usermaster/user", "PATCH",formData),
        deleteUser: formData => DXCUtils.callAPI("/demo/usermaster/user", "DELETE",formData),
        searchUserPicture: formData => DXCUtils.callAPIWithDownloadFile("/demo/common/file/"+formData, "GET")
    }
}(jQuery));
//]]'>