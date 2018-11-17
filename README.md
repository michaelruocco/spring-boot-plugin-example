# Todo

1. Don't pass channel id from controller, pull from current request to reduce arguments passed around
2. Don't return plugin api type alias on API - plugin types need to be decoupled from front end API types
3. Handle errors correctly, copy example for returning custom error responses
4. Change endpoints to show environment properties more obviously than just in logging

## Starting the service locally

```
make run
```

## Starting the service locally using docker

```
make runDocker
```

## Example calls

```
curl -X POST -H "channel-id:BIDV" -H "content-type:application/json" http://localhost:8080/aliases -d '{ "type": "UKC_CARDHOLDER_ID", "value": "12345678" }'
curl -X POST -H "channel-id:AS3" -H "content-type:application/json" http://localhost:8080/aliases -d '{ "type": "UKC_CARDHOLDER_ID", "value": "12345678" }'
curl -X POST -H "channel-id:BBOS" -H "content-type:application/json" http://localhost:8080/aliases -d '{ "type": "UKC_CARDHOLDER_ID", "value": "12345678" }'
```

## Limitations

* Plugin loader doesn't support beans with arguments, which means constructor autowiring
cannot be used in plugins
* Certificates would need to be configured in main service, plugin builders would need to request
that they are added to application keystore