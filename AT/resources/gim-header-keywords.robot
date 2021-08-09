*** Settings ***
Documentation  This is GIM Master Header Maintenance keyword
Library    SeleniumLibrary
Library    DateTime
Library    String
Library    DatabaseLibrary
Resource    ../resources/global-variable.robot

*** keywords ***
Open Gim Master Screen with Google Chrome
    Open Browser    http://localhost:8080/demo/gimmaster    chrome
    Set Window Size    1920    1080
    Set Window Position    0    0
Open Gim Master Screen with Google Chrome Headless
    Open Browser    http://localhost:8080/demo/gimmaster    headlesschrome    options=add_argument("--no-sandbox"); add_argument("--disable-dev-shm-usage"); add_argument("--ignore-certificate-errors"); add_argument("--incognito"); add_argument("--lang=en"); add_argument("--disable-gpu")
    Set Window Size    1920    1080
    Set Window Position    0    0
Check Gim Header Initial Correctly 
    Wait Until Page Contains    WDXC0001: GIM Master
    Element Attribute Value Should Be    jquery:#gimHeaderForm [name="searchGimTypes"]    value    ${EMPTY}
    Element Attribute Value Should Be    jquery:#gimHeaderForm [name="searchGimDesc"]    value    ${EMPTY}
    Element Attribute Value Should Be    jquery:#gimHeaderForm [name="searchActiveFlag"]    value    All
    Element Should Be Visible    jquery:#WDXC0001Search
    Element Should Be Visible    jquery:#WDXC0001Clear
    Element Should Be Visible    jquery:#WDXC0001Add
    Element Should Not Be Visible    jquery:#WDXC0001Edit
Search Gim Header Data With No Criteria
    Search Gim Header Data With Criteria    ${EMPTY}    ${EMPTY}    ${DROPDOWN_ALL}
Search Gim Header Data With Criteria
    [Arguments]    ${gimType}    ${gimDesc}    ${activeFlag}
    Execute JavaScript    $('#gimHeaderForm [name="searchGimTypes"]').dropdown('set selected','${gimType}')
    Input Text    jquery:#gimHeaderForm [name="searchGimDesc"]    ${gimDesc}
    Execute JavaScript    $('#gimHeaderForm [name="searchActiveFlag"]').dropdown('set selected','${activeFlag}')
Click Search Button
    Click Element    id:WDXC0001Search
Check Search Result Row count is
    [Arguments]    ${expected rowcount}
    Wait Until Page Does Not Contain Element    jquery:.loadingoverlay
    Wait Until Element Is Visible    xpath://*[@id="tableGimTypeHeaderResult_info"]
    Element Should Contain    xpath://*[@id="tableGimTypeHeaderResult_info"]    of ${expected rowcount} entries
Check Search Result Gim Type is
    [Arguments]    ${expected gimtype}
    Wait Until Page Does Not Contain Element    jquery:.loadingoverlay
    Wait Until Element Is Visible    xpath://*[@id="tableGimTypeHeaderResult_info"]
    Element Text Should Be    xpath://*[@id="tableGimTypeHeaderResult"]/tbody/tr/td[2]    ${expected gimtype}
Click Checkbox of Row Number
    [Arguments]    ${row}
    Click Element    xpath://*[@id="tableGimTypeHeaderResult"]/tbody/tr[1]/td[1]/input
Click Edit Button
    Click Element    id:WDXC0001Edit
Check Data Add or Edit Page are
    [Arguments]    ${gimType}    ${gimDesc}    ${cdLength}    ${field1Label}    ${field2Label}
            ...    ${field3Label}    ${activeFlag}
    Wait Until Element Is Visible    id:WDXC0001Save
    Element Attribute Value Should Be    jquery:#editGimHeaderForm [name="gimType"]    value    ${gimType}
    Element Attribute Value Should Be    jquery:#editGimHeaderForm [name="gimDesc"]    value    ${gimDesc}
    Element Attribute Value Should Be    jquery:#editGimHeaderForm [name="cdLength"]    value    ${cdLength}
    Element Attribute Value Should Be    jquery:#editGimHeaderForm [name="field1Label"]    value    ${field1Label}
    Element Attribute Value Should Be    jquery:#editGimHeaderForm [name="field2Label"]    value    ${field2Label}
    Element Attribute Value Should Be    jquery:#editGimHeaderForm [name="field3Label"]    value    ${field3Label}
    Element Attribute Value Should Be    jquery:#editGimHeaderForm [name="activeFlag"]    value    ${activeFlag}
Click Add Button
    Click Element    id:WDXC0001Add
Input Data Add Page
    [Arguments]    ${gimType}    ${gimDesc}    ${cdLength}    ${field1Label}    ${field2Label}
            ...    ${field3Label}    ${activeFlag}
    Input Text    jquery:#editGimHeaderForm [name="gimType"]    ${gimType}
    Input Text    jquery:#editGimHeaderForm [name="gimDesc"]    ${gimDesc}
    Input Text    jquery:#editGimHeaderForm [name="cdLength"]    ${cdLength}
    Input Text    jquery:#editGimHeaderForm [name="field1Label"]    ${field1Label}
    Input Text    jquery:#editGimHeaderForm [name="field2Label"]    ${field2Label}
    Input Text    jquery:#editGimHeaderForm [name="field3Label"]    ${field3Label}
    Execute JavaScript    $('#editGimHeaderForm [name="activeFlag"]').dropdown('set selected','${activeFlag}')
Input Data Edit Page
    [Arguments]    ${gimDesc}    ${cdLength}    ${field1Label}    ${field2Label}
            ...    ${field3Label}    ${activeFlag}
    Input Text    jquery:#editGimHeaderForm [name="gimDesc"]    ${gimDesc}
    Input Text    jquery:#editGimHeaderForm [name="cdLength"]    ${cdLength}
    Input Text    jquery:#editGimHeaderForm [name="field1Label"]    ${field1Label}
    Input Text    jquery:#editGimHeaderForm [name="field2Label"]    ${field2Label}
    Input Text    jquery:#editGimHeaderForm [name="field3Label"]    ${field3Label}
    Execute JavaScript    $('#editGimHeaderForm [name="activeFlag"]').dropdown('set selected','${activeFlag}')
Click Save Button
    Click Element    id:WDXC0001Save
Check Seach Result of Row
    [Arguments]    ${rowNumber}    ${gimType}    ${gimDesc}    ${cdLength}    ${field1Label}    ${field2Label}
            ...    ${field3Label}    ${activeFlag}    @{modifiedBy}
    Element Text Should Be    xpath://*[@id="tableGimTypeHeaderResult"]/tbody/tr[${rowNumber}]/td[2]    ${gimType}
    Element Text Should Be    xpath://*[@id="tableGimTypeHeaderResult"]/tbody/tr[${rowNumber}]/td[3]    ${gimDesc}
    Element Text Should Be    xpath://*[@id="tableGimTypeHeaderResult"]/tbody/tr[${rowNumber}]/td[4]    ${cdLength}
    Element Text Should Be    xpath://*[@id="tableGimTypeHeaderResult"]/tbody/tr[${rowNumber}]/td[5]    ${field1Label}
    Element Text Should Be    xpath://*[@id="tableGimTypeHeaderResult"]/tbody/tr[${rowNumber}]/td[6]    ${field2Label}
    Element Text Should Be    xpath://*[@id="tableGimTypeHeaderResult"]/tbody/tr[${rowNumber}]/td[7]    ${field3Label}
    Element Text Should Be    xpath://*[@id="tableGimTypeHeaderResult"]/tbody/tr[${rowNumber}]/td[8]    ${activeFlag}
    Element Text Should Be    xpath://*[@id="tableGimTypeHeaderResult"]/tbody/tr[${rowNumber}]/td[9]    @{modifiedBy}
    ${CurrentDate}    Get Current Date    result_format=%d/%m/%Y
    Element Should Contain    xpath://*[@id="tableGimTypeHeaderResult"]/tbody/tr[${rowNumber}]/td[10]    ${CurrentDate}