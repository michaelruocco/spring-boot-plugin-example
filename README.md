# Todo

1. Try to set up use of MongoDB on Heroku
2. Cucumber and unit tests

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

Successful examples:

```
curl -X POST -H "channel-id:BIDV" -H "content-type:application/json" http://localhost:8080/identities -d '{ "data": [ { "type": "identities", "attributes": { "aliases": [ { "type": "UKC_CARDHOLDER_ID", "value": "12345678" } ] } } ] }'
curl -X POST -H "channel-id:AS3" -H "content-type:application/json" http://localhost:8080/identities -d '{ "data": [ { "type": "identities", "attributes": { "aliases": [ { "type": "UKC_CARDHOLDER_ID", "value": "12345678" } ] } } ] }'
curl -X POST -H "channel-id:BBOS" -H "content-type:application/json" http://localhost:8080/identities -d '{ "data": [ { "type": "identities", "attributes": { "aliases": [ { "type": "UKC_CARDHOLDER_ID", "value": "12345678" } ] } } ] }'
```

Example error:

```
curl -X POST -H "channel-id:BIDV" -H "content-type:application/json" http://localhost:8080/identities -d '{ "data": [ { "type": "identities", "attributes": { "aliases": [ { "type": "UKC_CARDHOLDER_ID", "value": "123456789" } ] } } ] }'
```

## Limitations

* Plugin loader doesn't support beans with constructor arguments, which means constructor autowiring
cannot be used in plugins
* Certificates would need to be configured in main service, plugin builders would need to request
that they are added to application keystore