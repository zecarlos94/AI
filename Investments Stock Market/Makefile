
LIBRARIES = jade.jar
JAVA = src/*.java
JFLAGS = -cp $(LIBRARIES)
OUTPUT = -d bin
JC = javac
.SUFFIXES: .java .class
comp:
	$(JC) $(JFLAGS) $(JAVA) $(OUTPUT)

classes: $(CLASSES:.java=.class)

default: classes


clean:
	rm -rf bin/*
