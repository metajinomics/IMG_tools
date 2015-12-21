#
rm(list=ls())

#read all file
final = data.frame(matrix(ncol=4,nrow=0))
listFile = Sys.glob("~/globus/Hof3ISUMetaT*/*assembled.faa.phylodist.out.txt")
who <- read.table("~/globus/meta.txt",sep="\t",header=T)
fileNumbers <- seq(listFile)
for (fileNumber in 1:length(listFile)){
	data <- as.matrix(read.table(listFile[fileNumber],sep="\t"))
	#count
	map <- new.env(hash=T, parent=emptyenv())
	for (i in 1:dim(data)[1]){
		key <- data[i,3]
		key = as.character(key)
		if(exists(key,envir = map)){
			if(data[i,5]=="null"){next}
			tempnum = map[[key]] 
			num = tempnum + as.numeric(data[i,5])
			map[[key]] <- num
		}else{
			if(data[i,5]=="null"){next}
			num = as.numeric(data[i,5])
			map[[key]] <- num
		}
	}
	ec <- (ls(map))
	count <- array(0)
	for (i in 1:4){
		count[i] <- map[[ls(map)[i]]]
	}
	which = strsplit(data[1,1],"_")
	time <- (c("nuknown"))
	timex <- (c("nuknown"))
	for (i in 1:dim(who)[1]){
		if(which$V1[1]==who$id[i]){
			time <- paste0(who$hour[i],"_",who$replicate[i])
			timex <- as.numeric(paste0(who$hour[i],".",who$replicate[i]))
		}
	}
	
	tempframe= data.frame(time,ec,count,timex)
	final = rbind(final,tempframe)
}

#average


#plot
library(ggplot2)
ordered <- reorder(final$time,final$timex)
ggplot(final)+geom_bar(aes(x=ordered,y=count,fill=ec),stat="identity")





#not using
 
  
data_time_0_1 <- read.table("~/globus/Hof3ISUMetaT/68483.assembled.faa.phylodist.out.txt", sep="\t")
tempData <- as.matrix(data_time_0_1)

data_time_0_2 <- read.table("~/globus/Hof3ISUMetaT_16/68482.assembled.faa.phylodist.out.txt", sep="\t")
tempData <- data_time_0_2


data_time_24_1 <- read.table("~/globus/Hof3ISUMetaT_17/68490.assembled.faa.phylodist.out.txt", sep="\t")
tempData <- as.matrix(data_time_24_1)
time <- (c("24 hour"))

data_time_36_1 <- read.table("~/globus/Hof3ISUMetaT_18/68489.assembled.faa.phylodist.out.txt", sep="\t")
tempData <- as.matrix(data_time_36_1)
time <- (c("36 hour"))
data_time_36_2 <- read.table("~/globus/Hof3ISUMetaT_19/68486.assembled.faa.phylodist.out.txt", sep="\t")
tempData <- as.matrix(data_time_36_2)

data_time_144_1 <- read.table("~/globus/Hof3ISUMetaT_10/68487.assembled.faa.phylodist.out.txt", sep="\t")
tempData <- as.matrix(data_time_144_1)
time <- (c("144 hour"))

data_time_240_1 <- read.table("~/globus/Hof3ISUMetaT_15/68481.assembled.faa.phylodist.out.txt", sep="\t")
tempData <- as.matrix(data_time_240_1)
time <- (c("240 hour"))


#average
for (i in 1:dim(final)[1]){
	final$count[i]  <- (final$count[i] + count[i] )/2
}


mycount <- function(){
	
	
	
}


