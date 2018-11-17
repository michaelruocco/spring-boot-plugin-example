env=local
plugins=as3,bbos,bidv

clean:
	./gradlew clean

build:
	make clean
	./gradlew build

dockerBuild:
	make build
	docker build -t michaelruocco/spring-boot-plugin-example .

run:
	make build
	. ./configure-plugins.sh $(env) $(plugins)
	./gradlew bootRun

dockerRun:
	make dockerBuild
	. ./configure-plugins.sh $(env) $(plugins)
	docker run -v $(shell pwd)/build/plugins:/opt/plugins -v $(shell pwd)/build/plugin-config:/opt/plugin-config -p 8080:8080 --name plugin-example --rm michaelruocco/spring-boot-plugin-example
