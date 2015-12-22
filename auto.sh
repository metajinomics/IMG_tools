for x in ~/globus/Hof3ISUMetaT*/*.assembled.faa.EC;
do java MergeIMG ${x%/*.assembled.faa.EC}/rnaseq_expression.txt ${x%.assembled.faa.EC}.assembled.faa.product.names ${x%.assembled.faa.EC}.assembled.faa.phylodist $x;
done