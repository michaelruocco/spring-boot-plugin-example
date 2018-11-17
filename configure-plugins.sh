#!/bin/bash

env=${1:-local}
plugins=${2:-as3,bbos,bidv}
echo 'configuring plugins' ${plugins} 'for environment' ${env}

rm -rf build
mkdir -p build/plugins
mkdir -p build/plugin-config

for plugin in $(echo $plugins | tr "," "\n")
do
  echo 'configuring plugin' ${plugin}
  cp plugins/${plugin}/build/libs/*.jar build/plugins
  if [ -d "plugins/${plugin}-config" ]; then
    echo 'found config for plugin' ${plugin}
    cp plugins/${plugin}-config/build/distributions/${plugin}-config-*-${env}.zip build/plugin-config/${plugin}-config.zip
  else
    echo 'no config found for plugin' ${plugin}
  fi
done