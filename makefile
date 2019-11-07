SRC := $(wildcard *.java)
CLASS-FILES := $(patsubst %.java, %.class, $(SRC))

.PHONY: clean-all clean-classes

build: $(CLASS-FILES) Evaluator.jar clean-classes

%.jar:
	jar cfe $@ MathClient $(CLASS-FILES)
	chmod +x $@

%.class: %.java
	javac $<


run:
	java -jar Evaluator.jar


clean-all: clean-classes
	rm Evaluator.jar

clean-classes:
	rm $(CLASS-FILES)

