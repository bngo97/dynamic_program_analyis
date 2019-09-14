#!/bin/bash

echo -----Running tests that should pass-----
for filename in tst/pass/*.txt; do
    java -cp src/ MyParser -f $filename 0
done

echo

echo -----Running tests that should fail-----
for filename in tst/fail/*.txt; do
    java -cp src/ MyParser -f $filename 1
done
