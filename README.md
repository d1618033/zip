general
=======
- Generates instances of a simple ZIP model
- Estimates the parameters of a simple ZIP model

usage
-----

first compile the source files

    $ javac GenerateZIP.java
    $ javac EstimateZIP.java

to generate instances of the zip model:

    $ java GenerateZIP <p> <t> <n>

- p - probability that rv will be drawn from poisson (as oppose to 0)
- t - parameter of poisson distribution
- n - number of rvs to generate

to estimate the parameters of the zip model:

    $ java EstimateZIP <filename>

the first number in the file must be the number of rvs to read from the file (see example)

example
----------


first 
    $ echo "1000" > data1000.txt 
    $ java GenerateZIP 0.5 3 1000 >> data1000.txt
    $ head data1000.txt 
    1000
    0
    7
    1
    0
    3
    0
    0
    2
    0

estimating paramters:    

    $ java EstimateZIP data1000.txt 
p = 0.5056836064401727
t = 2.9049784910790795


