*** Settings ***
Library    SeleniumLibrary
Library    OperatingSystem
Library    ExcelLibrary
Suite Teardown    Close Browser

Resource    ../resources/common-keywords.robot

** Variables **
${URL}    http://159.89.210.222:38080/demo/gimmaster

*** Test Case ***
Dowloaded file should be GIM Header Table
    ${file}    Download Excel File
    Excel Cell Should Be Equal As Strings    ${file}    1    1    GIM Header Table    # check excel header

*** Keywords ***
Download Excel File
    ${download directory} =    common-keywords.DXC Open Chrome Browser Headless with Download Ability     ${URL}
    Click Element    id:WDXC0001Search
    Wait Until Element Is Not Visible    class:loadingoverlay
    Wait Until Element Is Enabled    id:tableGimTypeHeaderResult
    Click Element    //*[@id="tableGimTypeHeaderResult_wrapper"]/div/div[1]/div[1]/div/button[1]    # downloads a file
    # wait for download to finish
    ${file}    Wait Until Keyword Succeeds    1 min    2 sec    Download Should Be Done    ${download directory}
    [Return]    ${file}

Download Should Be Done
    [Arguments]    ${directory}
    [Documentation]    Verifies that the directory has only one folder and it is not a temp file.
    ...
    ...    Returns path to the file
    ${files}    List Files In Directory    ${directory}
    Length Should Be    ${files}    1
    Should Not Match Regexp    ${files[0]}    (?i).*\\.tmp    Chrome is still downloading a file
    ${file}    Join Path    ${directory}    ${files[0]}
    Log    File was successfully downloaded to ${file}
    [Return]    ${file}

Excel Cell Should Be Equal As Strings
    [Arguments]    ${directory}    ${row_num}    ${col_num}    ${text}
    Open Excel Document    filename=${directory}    doc_id=doc1	
    ${a1}=    Read Excel Cell    row_num=${row_num}    col_num=${col_num}
    Log    Data of cell is ${a1}
    Should Be Equal As Strings    ${a1}    ${text}
    Close All Excel Documents