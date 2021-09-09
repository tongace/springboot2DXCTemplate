*** Settings ***
Documentation  This is Excel Reader scenario

Library    ExcelLibrary
Library    OperatingSystem
Library    Collections

*** Test Cases ***
test1
    Open Excel Document    filename=D:/Customer.xlsx    doc_id=testdoc1
#    ${a1}=    Read Excel Cell    row_num=3    col_num=1
#    Log To Console    ${a1}
#    ${a2}=    Read Excel Cell    row_num=3    col_num=2
#    Log To Console    ${a2}
	${rd}=    Read Excel Row    row_num=16    max_num=6    sheet_name=Sheet1
	Log To Console    ${rd}
    Close All Excel Documents
# *** Test Cases ***
# Check created excel doc
#     ${document}=    Create Excel Document    doc_name
#     Should Be Equal As Strings    doc_name    ${document}