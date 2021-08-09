*** Settings ***
Documentation  This is Comon keyword
Library    SeleniumLibrary
Library    DateTime
Library    String

*** keywords ***
Check Alert Modal Is Complete Visible
    ${class}=    Get Element Attribute   xpath://*[@id="alertModal"]    class
    Should Contain    ${class}    modal transition visible active
Check Confirm Modal Is Complete Visible
    ${class}=    Get Element Attribute   xpath://*[@id="confirmingModal"]    class
    Should Contain    ${class}    modal transition visible active
Check Alert Modal Is Complete Invisible
    ${class}=    Get Element Attribute   xpath://*[@id="alertModal"]    class
    Run Keyword If    '${class}'=='ui mini modal'    Should Be Equal    ${class}    ui mini modal    ELSE    Should Contain    ${class}    modal transition hidden
Check Confirm Modal Is Complete Invisible
    ${class}=    Get Element Attribute   xpath://*[@id="confirmingModal"]    class
    Should Contain    ${class}    modal transition hidden
Check Confirm Message
    [Arguments]    ${code}    ${message}
    Wait Until Keyword Succeeds    500x    20ms    Check Confirm Modal Is Complete Visible
    Element Text Should Be    xpath://*[@id="confirmingModal"]/div[1]    ${code}
    Element Text Should Be    xpath://*[@id="confirmingModal"]/div[2]    ${message}
Check Message Result Or Alert
    [Arguments]    ${code}    ${message}
    Wait Until Keyword Succeeds    500x    20ms    Check Alert Modal Is Complete Visible
    Element Text Should Be    xpath://*[@id="alertModal"]/div[1]    ${code}
    Element Text Should Be    xpath://*[@id="alertModal"]/div[2]    ${message}
Click OK on Message Result Or Alert
    Click Element    jquery:#modalButtonOK
    Wait Until Keyword Succeeds    500x    20ms    Check Alert Modal Is Complete Invisible
    Wait Until Page Does Not Contain Element    jquery:.loadingoverlay    10
    Wait Until Element Is Not Visible    jquery:.loadingoverlay    10
Click Yes on Confirm
    Click Element    xpath://*[@id="confirmingModal"]/div[3]/div[1]
    Wait Until Keyword Succeeds    500x    20ms    Check Confirm Modal Is Complete Invisible
    Wait Until Page Does Not Contain Element    jquery:.loadingoverlay    10
    Wait Until Element Is Not Visible    jquery:.loadingoverlay    10
Click No on Confirm
    Click Element    xpath://*[@id="confirmingModal"]/div[3]/div[2]
    Wait Until Keyword Succeeds    500x    20ms    Check Confirm Modal Is Complete Invisible
    Wait Until Page Does Not Contain Element    jquery:.loadingoverlay    10
    Wait Until Element Is Not Visible    jquery:.loadingoverlay    10