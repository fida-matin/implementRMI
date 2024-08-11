# University of Adelaide - Distributed Systems 2024
# Fida Matin - a1798239
# 11 Aug 2024
JFLAGS = -g
JC = javac
.SUFFIXES: .java .class

.java.class:
		$(JC) $(JFLAGS) $*.java

CLASSES = \
        Calculator.java \
        CalculatorClient.java \
        CalculatorImplementation.java \
        CalculatorServer.java \

default: classes
	@echo " "
	@echo "KILLING PROCESS FOR RMI:"
	@echo "To close rmi use 'ps' to get process id and use 'kill -9 PID' to remove process"
	@echo " "

classes: $(CLASSES:.java=.class)

setup: 			CalculatorServer.java
	rmiregistry &
	sleep 2
	java CalculatorServer

server: 		CalculatorServer.java
	java CalculatorServer

test: 			CalculatorClient.java
	java -ea CalculatorClient

clean: 
	$(RM) *.class