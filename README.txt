general
=======
Generates instances of a simple ZIP model
Estimates the parameters of a simple ZIP model

usage
=====
to generate:
$ javac GenerateZIP.java
$ java GenerateZIP <p> <t> <n>
p - probability that rv will be drawn from poisson (as oppose to 0)
t - parameter of poisson distribution
n - number of rvs to generate

to estimate:
$ javac EstimateZIP.java
$ java EstimateZIP <filename>

example
=======
$ java GenerateZIP 0.5 3 1000 > data1000.txt
$ head data1000.txt 
6
1
2
4
0
0
0
3
2
3
$ java EstimateZIP data1000.txt 
p = 0.5759694518993623
t = 2.0255703888797827



