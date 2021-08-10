*** Settings ***
Library    SeleniumLibrary
Library    OperatingSystem
Library    ExcelLibrary
Suite Teardown    Close Browser

*** Test Case ***
Open Gim Master Screen with Google Chrome
    ${now}    Get Time    epoch
    ${download directory}    Join Path    ${OUTPUT DIR}    downloads_${now}
    ${prefs}    Create Dictionary    download.default_directory=${download directory}
    Open Browser    http://localhost:8080/demo/gimmaster    chrome    options=add_argument(${prefs})
    Set Window Size    1920    1080
    Set Window Position    0    0