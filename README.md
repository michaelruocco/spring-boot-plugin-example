# Todo

1. Don't pass channel id from controller, pull from current request to reduce arguments passed around
2. Don't return plugin api type alias on API - plugin types need to be decoupled from front end API types
3. Handle errors correctly, copy example for returning custom error responses
4. Change endpoints to show environment properties more obviously than just in logging
5. Cucumber and unit tests

## Starting the service locally

```
make -B run
```

If you want to specify which environment config is used or which plugins to configure
that can also be specified, by default local environment will be used with all
three plugins (bidv,as3,bbos) to change this you can specify arguments to the make task.

```
make -B run env=dev plugins=as3,bbos
```

## Starting the service locally using docker

```
make -B dockerRun
```

The same environment and plugin overrides can be applied to the runDocker task as the
run examples shown previously.

```
make -B dockerRun run env=dev plugins=as3,bbos
```

## Example calls

```
curl -X POST -H "channel-id:BIDV" -H "content-type:application/json" http://localhost:8080/aliases -d '{ "type": "UKC_CARDHOLDER_ID", "value": "12345678" }'
curl -X POST -H "channel-id:AS3" -H "content-type:application/json" http://localhost:8080/aliases -d '{ "type": "UKC_CARDHOLDER_ID", "value": "12345678" }'
curl -X POST -H "channel-id:BBOS" -H "content-type:application/json" http://localhost:8080/aliases -d '{ "type": "UKC_CARDHOLDER_ID", "value": "12345678" }'
```

## Limitations

* Plugin loader doesn't support beans with constructor arguments, which means constructor autowiring
cannot be used in plugins
* Certificates would need to be configured in main service, plugin builders would need to request
that they are added to application keystore