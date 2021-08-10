*** Settings ***
Documentation  This is Comon keyword
Library    SeleniumLibrary
Library    DateTime
Library    String
Library    OperatingSystem

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
    Click Element    id:modalButtonOK
    Wait Until Keyword Succeeds    500x    20ms    Check Alert Modal Is Complete Invisible
    Wait Until Page Does Not Contain Element    class:loadingoverlay    10
    Wait Until Element Is Not Visible    class:loadingoverlay    10
Click Yes on Confirm
    Click Element    xpath://*[@id="confirmingModal"]/div[3]/div[1]
    Wait Until Keyword Succeeds    500x    20ms    Check Confirm Modal Is Complete Invisible
    Wait Until Page Does Not Contain Element    class:loadingoverlay    10
    Wait Until Element Is Not Visible    class:loadingoverlay    10
Click No on Confirm
    Click Element    xpath://*[@id="confirmingModal"]/div[3]/div[2]
    Wait Until Keyword Succeeds    500x    20ms    Check Confirm Modal Is Complete Invisible
    Wait Until Page Does Not Contain Element    jquery:.loadingoverlay    10
    Wait Until Element Is Not Visible    jquery:.loadingoverlay    10
    
DXC Open Chrome Browser with Download Ability
    [Arguments]    ${URL}
    ${now}    Get Time    epoch
    ${download directory}    Join Path    ${OUTPUT DIR}    downloads_${now}
    Create Directory    ${download directory}
    ${chrome options}=    Evaluate    sys.modules['selenium.webdriver'].ChromeOptions()    sys, selenium.webdriver
    # list of plugins to disable. disabling PDF Viewer is necessary so that PDFs are saved rather than displayed
    ${disabled}    Create List    Chrome PDF Viewer
    ${prefs}    Create Dictionary    download.default_directory=${download directory}    plugins.plugins_disabled=${disabled}
    Log Many    ${prefs}
    Call Method    ${chrome options}    add_experimental_option    prefs    ${prefs}
    Call Method    ${chrome options}    add_argument    --incognito
    Create Webdriver    Chrome    chrome_options=${chrome options}
    Goto    ${URL}
    [Return]    ${download directory}

DXC Open Chrome Browser Headless with Download Ability
    [Arguments]    ${URL}
    ${now}    Get Time    epoch
    ${download directory}    Join Path    ${OUTPUT DIR}    downloads_${now}
    Create Directory    ${download directory}
    ${chrome options}=    Evaluate    sys.modules['selenium.webdriver'].ChromeOptions()    sys, selenium.webdriver
    # list of plugins to disable. disabling PDF Viewer is necessary so that PDFs are saved rather than displayed
    ${disabled}    Create List    Chrome PDF Viewer
    ${prefs}    Create Dictionary    download.default_directory=${download directory}    plugins.plugins_disabled=${disabled}
    Log Many    ${prefs}
    Call Method    ${chrome options}    add_experimental_option    prefs    ${prefs}
    Call Method    ${chrome options}    add_argument    --headless
    Call Method    ${chrome options}    add_argument    --no-sandbox
    Call Method    ${chrome options}    add_argument    --disable-dev-shm-usage
    Call Method    ${chrome options}    add_argument    --ignore-certificate-errors
    Call Method    ${chrome options}    add_argument    --incognito
    Call Method    ${chrome options}    add_argument    --disable-gpu
    Create Webdriver    Chrome    chrome_options=${chrome options}
    Goto    ${URL}
    [Return]    ${download directory}