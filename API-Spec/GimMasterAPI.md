## Search Gim Header API
```
POST /gimmaster/gimheader
```
### Request body
```json
{
    "searchGimTypes":["ACTIVE_FLAG","TEST"], 
    "searchGimDesc":"Any String (can use wildcard as *)",
    "searchActiveFlag" : "ALL"
}
```


### Response
#### Status
```
200 success
```
#### Body
##### Success (Data Found)
```json
{
  "message":null,
  "rowCount": 0,
   "data" :[
        {
            "gimType": "ACTIVE_FLAG",
            "gimDesc": "Active Flag Please don't delete",
            "cdLength": 1,
            "field1Label": "Not used",
            "field2Label": "Not used",
            "field3Label": "Not used",
            "activeFlag": "Y",
            "createdBy": "SYSTEM",
            "createdDt": 1628048262000,
            "modifiedBy": "SYSTEM",
            "modifiedDt": 1638329862000,
            "displayActiveFlag": "Active",
            "mode": null,
            "searchGimTypes": null,
            "searchGimDesc": null,
            "searchActiveFlag": null
        },
        {
            "gimType": "STATUS",
            "gimDesc": "Production Release Status Puay",
            "cdLength": 3,
            "field1Label": "Priority Order",
            "field2Label": "Not Used",
            "field3Label": "Not Used",
            "activeFlag": "Y",
            "createdBy": "csamphao",
            "createdDt": 1628586097000,
            "modifiedBy": "csamphao",
            "modifiedDt": 1628586102000,
            "displayActiveFlag": "Active",
            "mode": null,
            "searchGimTypes": null,
            "searchGimDesc": null,
            "searchActiveFlag": null
        },
        {
            "gimType": "TEST_GIM",
            "gimDesc": "ddddd",
            "cdLength": 5,
            "field1Label": "Not Used",
            "field2Label": "Not Used",
            "field3Label": "Not Used",
            "activeFlag": "Y",
            "createdBy": "csamphao",
            "createdDt": 1628826257000,
            "modifiedBy": "csamphao",
            "modifiedDt": 1628826257000,
            "displayActiveFlag": "Active",
            "mode": null,
            "searchGimTypes": null,
            "searchGimDesc": null,
            "searchActiveFlag": null
        }
    ]
}
```
##### Success (Data Not Found)
```json
{
  "message":"MAPP0006AERR: No data found",
  "rowCount": 0,
   "data" : null
}