//
// Created by koppe on 2018-11-13.
#include "RecycleSearch.h"
#include<string>


Search::Search(unsigned long long int UPCcode){
    this->UPCcode = UPCcode;
}

std::string Search::searchByUPC(){
    std::string message;
    if(this->UPCcode == 102424){
        message = "Recycle this in the blue bin!";
        return message;

    }
}

//int main(){
//    Search test1(102424);
//    test1.searchByUPC();
//   return 0;
//}


