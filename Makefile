all: xlsxunpass.class

poi-3.15/:
	wget http://apache.mesi.com.ar/poi/release/bin/poi-bin-3.15-20160924.tar.gz
	tar -xzvf poi-bin-3.15-20160924.tar.gz

xlsxunpass.class: poi-3.15 xlsxunpass.java
	# assume poi-3.15 is in working directory, tar extract of poi-bin-3.15-20160924.tar.gz
	javac -cp poi-3.15/poi-ooxml-3.15.jar:poi-3.15/poi-3.15.jar xlsxunpass.java

# this is just here for reference
run:
	java -cp "poi-3.15/ooxml-lib/*:poi-3.15/*:./" xlsxunpass
