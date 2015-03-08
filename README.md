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

    $ java EstimateZIP <filename> <number of lines in file>


example
----------


first generate the parameters.

    $ java GenerateZIP 0.5 3 1000 > data1000.txt
    $ head data1000.txt
    0
    0
    2
    0
    6
    0
    0
    3
    0
    0


estimating paramters:

    $ java EstimateZIP data1000.txt 1000
    p = 0.4901073639468373
    t = 3.054432783757116
