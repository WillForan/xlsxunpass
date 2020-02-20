.PHONY: fat all
all: fat
fat: xlsxunpass.jar
xlsxunpass.jar: xlsxunpass.java build.gradle
	gradle shadowJar
	cp ./build/libs/xlsxunpass-all.jar xlsxunpass.jar

# below only useful for quick testing?
POIVER = 4.1.2
POIDATE = 20200217
PROVIDER = http://apache-mirror.8birdsvideo.com/poi/release/bin/

poi-$(POIVER)/:
	wget ${PROVIDER}/poi-bin-$(POIVER)-$(POIDATE).tar.gz
	tar -xzvf poi-bin-$(POIVER)-$(POIDATE).tar.gz

xlsxunpass.class: poi-$(POIVER)/ xlsxunpass.java
	javac -cp poi-$(POIVER)/poi-ooxml-$(POIVER).jar:poi-$(POIVER)/poi-$(POIVER).jar xlsxunpass.java

# this is just here for reference
run:
	java -cp "poi-$(POIVER)/ooxml-lib/*:poi-$(POIVER)/*:./" xlsxunpass
