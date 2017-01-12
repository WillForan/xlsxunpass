# decrypt sheets
```
gradle build
# OR
make
```

## Notes
need poi **and** poi-ooxml [so ref](http://stackoverflow.com/questions/5878341/cannot-import-xssf-in-apache-poi)
```
# test compile
wget http://svn.apache.org/repos/asf/poi/trunk/src/examples/src/org/apache/poi/ss/examples/BusinessPlan.java
javac -cp poi-3.15/poi-ooxml-3.15.jar:poi-3.15/poi-3.15.jar BusinessPlan.java
```
