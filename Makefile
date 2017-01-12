decrypt.class:
	# assume poi-3.15 is in working directory, tar extract of poi-bin-3.15-20160924.tar.gz
	javac -cp poi-3.15/poi-ooxml-3.15.jar:poi-3.15/poi-3.15.jar decrypt.java

all: decrypt.class
