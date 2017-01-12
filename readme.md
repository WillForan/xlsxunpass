# decrypt sheets
## use
```
java -jar xlsxunpass.jar input.xslx nopassout.xlsx infile_password
```

## build
build using gradle and the shadow plugin
```
gradle build
java -jar build/libs/xlsxunpass-all.jar input.xlsx output.xlsx password
```

There is also a `Makefile` but `commons-collection` is not in the classpath for the `run` section.


# Notes
need poi **and** poi-ooxml [so ref](http://stackoverflow.com/questions/5878341/cannot-import-xssf-in-apache-poi)
```
# test compile
wget http://svn.apache.org/repos/asf/poi/trunk/src/examples/src/org/apache/poi/ss/examples/BusinessPlan.java
javac -cp poi-3.15/poi-ooxml-3.15.jar:poi-3.15/poi-3.15.jar BusinessPlan.java
```

