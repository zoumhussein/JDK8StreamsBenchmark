# JDK8StreamsBenchmark

# Quesaco ?

Small Benchmark that uses 3 different methods to test speed for sorting and max retrieval.
The 3 methods are old school JDK7 sort and JDK8 stream and parallel stream

# How

An Integer list is populated with 10 Millions or Random integer from MIN to MAX Integer, then we go over this same list for each of the 3 methods.

The same list is also use to look for the MAX with the old school get last from the sorted list JDK7 way, max() for JDK8 stream and parallelStream fashions

# Results (in Millis) with Intel(R) Core(TM) i3-3217U CPU @ 1.80GHz, 2 Cores (4vCPU), 8 Gb RAM on Ubuntu 14.04.4 LTS running Oracle JDK 1.8.0_91

******** sort OLD School JDK 1.7 *******
duration :13920

******** sort STREAM NOT collected JDK 1.8 *******
duration :5

******** sort STREAM collected JDK 1.8 *******
duration :534

******** sort PARALLEL STREAM NOT collected JDK 1.8 *******
duration :0

******** sort PARALLEL STREAM collected JDK 1.8 *******
duration :1186

******** MAX OLD School JDK 1.7 *******
duration : already computed
2147483425

******** MAX PARALLEL STREAM collected JDK 1.8 *******
duration :335
2147483425

******** MAX STREAM collected JDK 1.8 *******
duration :534
2147483425
