*** Settings ***
Documentation  This is About Database keyword
Library    DatabaseLibrary

*** keywords ***
Run Databse Sql Script
    [Arguments]    ${sqlscript}
    Connect To Database    pymysql    demo   demo    demo    localhost    3306
    Execute Sql Script    ${sqlscript}
    Disconnect From Database