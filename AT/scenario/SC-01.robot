*** Settings ***
Documentation  This is GIM Master Maintenance scenario
Library    SeleniumLibrary
Library    DateTime
Library    String
Library    DatabaseLibrary

Resource    ../resources/gim-header-keywords.robot
Resource    ../resources/database-keywords.robot
Resource    ../resources/common-keywords.robot
Resource    ../resources/global-variable.robot
Suite Teardown    Close Browser

*** Test Cases ***
Step 0 Prepare Data for GIM Master Maintenance scenario 01
    database-keywords.Run Databse Sql Script    D:/Development/SourceCode/springboot2DXCTemplate/AT/data/preparedata.sql
Step 1 Open GIM Master Screen
    gim-header-keywords.Open Gim Master Screen with Google Chrome
    gim-header-keywords.Check Gim Header Initial Correctly
    Capture Page Screenshot

Step 2 Search without Criteria
    gim-header-keywords.Search Gim Header Data With No Criteria
    gim-header-keywords.Click Search Button
    gim-header-keywords.Check Search Result Row count is    1
    gim-header-keywords.Check Search Result Gim Type is    ACTIVE_FLAG
    Capture Page Screenshot

Step 3 Click "Add" button
    gim-header-keywords.Click Add Button
    gim-header-keywords.Check Data Add or Edit Page are    ${EMPTY}    ${EMPTY}    ${EMPTY}
                                         ...    Not Used  Not Used  Not Used  Select
    Capture Page Screenshot

Step 4 Input Information and click "Save" button
    gim-header-keywords.Input Data Add Page    STATUS    Production Status Puay    3
                                                ...    Not Used    Not Used    Not Used  ${Y:Active}
    Capture Page Screenshot
    gim-header-keywords.Click Save Button
    common-keywords.Check Confirm Message    MSTD0006ACFM    MSTD0006ACFM: Do you want to save changed data?
    Capture Page Screenshot

Step 5 Click "Yes" on Confirmation Message to save Added Data
    common-keywords.Click Yes on Confirm
    common-keywords.Check Message Result Or Alert    MBX00005AINF    MBX00005AINF: Save data is completed successfully.
    Capture Page Screenshot

Step 6 Click "OK" on Result Message and check Added Data
    common-keywords.Click OK on Message Result Or Alert
    gim-header-keywords.Check Search Result Row count is    2
    gim-header-keywords.Check Seach Result of Row    2    STATUS    Production Status Puay    3
                                              ...    Not Used  Not Used  Not Used  Active  csamphao

Step 7 Click Search button with criteria
    gim-header-keywords.Search Gim Header Data With Criteria    STATUS    ${EMPTY}    ${DROPDOWN_ALL}
    gim-header-keywords.Click Search Button
    gim-header-keywords.Check Search Result Row count is    1
    gim-header-keywords.Check Search Result Gim Type is    STATUS
    Capture Page Screenshot

Step 8 Select Checkbox GIM Type = "STATUS" then click "Edit" button
    gim-header-keywords.Click Checkbox of Row Number    1
    gim-header-keywords.Click Edit Button
    gim-header-keywords.Check Data Add or Edit Page are    STATUS    Production Status Puay    3  
                                         ...    Not Used  Not Used  Not Used  ${Y:Active}
    Capture Page Screenshot

Step 9 Edit with below information and click "Save" button
    gim-header-keywords.Input Data Edit Page    Production Release Status Puay    3
                                                ...    Priority Order    Not Used    Not Used  ${Y:Active}
    Capture Page Screenshot
    gim-header-keywords.Click Save Button
    common-keywords.Check Confirm Message    MSTD0006ACFM    MSTD0006ACFM: Do you want to save changed data?
    Capture Page Screenshot

Step 10 Click "Yes" on Confirmation Message to save Edited Data
    common-keywords.Click Yes on Confirm
    common-keywords.Check Message Result Or Alert    MBX00005AINF    MBX00005AINF: Save data is completed successfully.
    Capture Page Screenshot

Step 11 Click "OK" on Result Message and check Edited Data
    common-keywords.Click OK on Message Result Or Alert
    gim-header-keywords.Check Search Result Row count is  1
    gim-header-keywords.Check Seach Result of Row    1    STATUS    Production Release Status Puay    3
                                              ...    Priority Order  Not Used  Not Used  Active  csamphao