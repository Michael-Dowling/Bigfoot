//
// Created by koppe on 2018-11-13.
//

#include <string>

#ifndef ALBERTREPO_RECYCLESEARCH_H
#define ALBERTREPO_RECYCLESEARCH_H

#endif //ALBERTREPO_RECYCLESEARCH_H

class Search{
public:
    Search(unsigned long long int);
    std::string searchByUPC();
private:
    unsigned long long int UPCcode;
};
