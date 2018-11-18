
//
// Created by koppe on 2018-11-13.

#include "RecycleSearch.h"
#include<string>

RecycleItem::RecycleItem(std::string name, std::string binType, unsigned long long int upc, std::string description) {
    this->name = name;
    this->binType = binType;
    this->upc = upc;
    this->description = description;
}

std::string RecycleItem::getName(){
    return name;
}

std::string RecycleItem::getBinType(){
    return binType;
}
unsigned long long int RecycleItem::getUpc(){
    return upc;
}
std::string RecycleItem::getDescription(){
    return description;
}

Search::Search(unsigned long long int UPCcode) {
    this->UPCcode = UPCcode;
}

std::string Search::searchByUPC() {
    std::string message;
    if (this->UPCcode == 5557742075) {
        message = "This is a gatorade bottle. It goes in the blue bin, leave the cap on!";
        return message;
    }
}

//int main(){
//    Search test1(102424);
//    test1.searchByUPC();
//   return 0;
//}






