# xlsx unpass
create a passwordless version of a password protected xlsx file.

`unoconv` is a more robust utility. This has been written to work around "Failed to connect to... / Connector : couldn't connect to socket" errors

## use

```
curl -L -o xlsxunpass.jar 'https://github.com/WillForan/xlsxunpass/blob/master/xlsxunpass.jar?raw=true'
java -jar xlsxunpass.jar input.xslx nopassout.xlsx infile_password
```


## build
Using gradle and the shadow plugin
```
gradle shadowJar
java -jar build/libs/xlsxunpass-all.jar input.xlsx output.xlsx password
```

There is also a `Makefile` but `commons-collection` is not in the classpath for the `run` ref section.


# Notes
need poi **and** poi-ooxml [so ref](http://stackoverflow.com/questions/5878341/cannot-import-xssf-in-apache-poi)
```
# test compile
wget http://svn.apache.org/repos/asf/poi/trunk/src/examples/src/org/apache/poi/ss/examples/BusinessPlan.java
javac -cp poi-3.15/poi-ooxml-3.15.jar:poi-3.15/poi-3.15.jar BusinessPlan.java
```

