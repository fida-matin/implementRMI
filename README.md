# implementRMI
Fida Matin - a1798239 - Distributed Systems
University of Adelaide - 2024

Introduction to Java RMI by building a calculator

Makefile covers all required testing and compilation requirements.

For compilation:
- run "make"

For executables:
- if RMI is not already setup, run "make setup" to run RMI and server in one command
- otherwise run "make server" which runs only the server

For testing:
- integration testing is automated into the CalculatorClient.java file
- run "make test" to run through all testing

Breakdown of all the test, 
Test 1-3 tests functions for an individual client, Tests 4-6 tests functions for multiple clients:
- TEST 1: Examines integration of pushing, popping with the stack from server, as well as checking if stack is empty
- TEST 2: Examines all pushOperations() functionality ("min", "max", "lcm", "gcd")
- TEST 3: Tests delayPop() functionality

- TEST 4: Create 4 clients connected to the same server
- TEST 5: Test whether all clients are sharing the same stack by calling different operations with different clients
- TEST 6: Additional testing to verify multiple client functionality

