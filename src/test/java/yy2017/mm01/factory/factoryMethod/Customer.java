package yy2017.mm01.factory.factoryMethod;

import yy2017.mm01.factory.BMW;

public class Customer {  
    public static void main(String[] args) {  
        FactoryBMW320 factoryBMW320 = new FactoryBMW320();  
        BMW bmw320 = factoryBMW320.createBMW();
        
        FactoryBMW523 factoryBMW523 = new FactoryBMW523();  
        BMW bmw523 = factoryBMW523.createBMW();
        
        bmw320.create();
		bmw523.create();
    }  
}  