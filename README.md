# IMG_tools
IMG_tools

## Calculate RPKM
```
g++ Get_RPKM_for_IMG.cpp -o Get_RPKM_for_IMG
./Get_RPKM_for_IMG rnaseq_expression.txt rnaseq_expression.RPKM.txt
```

## get EC count with targeted EC number
```
javac MergeIMG_RPKM.java
java MergeIMG_RPKM rnaseq_expression.txt.RPKM.txt 68483.assembled.faa.product.names 68483.assembled.faa.phylodist 68483.assembled.faa.ec
```

## get EC count for all sample
```
javac get_ec_count.java
java get_ec_count 68483.assembled.faa.ec rnaseq_expression.txt.RPKM.txt
```

To automate,
```
for x in ~/globus/*/*.assembled.faa.EC ;do java get_ec_count $x ${x%/*}/rnaseq_expression.txt.RPKM.txt;done
```

After this, let's merge counts for all samples

```
javac merge_ec_counts.java
javac merge_ec_counts ~/globus/meta.txt
```


