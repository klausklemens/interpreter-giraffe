SRC := $(wildcard *.java)
CLASS-FILES := $(patsubst %.java, %.class, $(SRC))

.PHONY: jar clean-all clean-classes

build: compile jar clean-classes

compile: $(CLASS-FILES)

jar:
	jar cfe Evaluator.jar MathClient $(CLASS-FILES)
	chmod +x Evaluator.jar

%.class: %.java
	javac $<


run:
	java -jar Evaluator.jar ${ARGS}


clean-all: clean-classes
	rm Evaluator.jar

clean-classes:
	rm $(CLASS-FILES)

