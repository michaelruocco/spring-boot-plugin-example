# Todo

1. Don't pass channel id from controller, pull from current request to reduce arguments passed around
2. Don't return plugin api type alias on API - plugin types need to be decoupled from front end API types
3. Handle errors correctly, copy example for returning custom error responses
4. Figure out how we are going to handle loading properties

Example calls

```
curl -X POST -H "channel-id:BIDV_DEMO" -H "content-type:application/json" http://localhost:8080/aliases -d '{ "type": "UKC_CARDHOLDER_ID", "value": "12345678" }'
curl -X POST -H "channel-id:AS3" -H "content-type:application/json" http://localhost:8080/aliases -d '{ "type": "UKC_CARDHOLDER_ID", "value": "12345678" }'
curl -X POST -H "channel-id:BBOS" -H "content-type:application/json" http://localhost:8080/aliases -d '{ "type": "UKC_CARDHOLDER_ID", "value": "12345678" }'
```