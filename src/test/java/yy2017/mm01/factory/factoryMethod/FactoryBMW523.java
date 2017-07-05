package yy2017.mm01.factory.factoryMethod;

import yy2017.mm01.factory.BMW;
import yy2017.mm01.factory.BMW523;

public class FactoryBMW523 implements Factory{

	@Override
	public BMW createBMW() {
		return new BMW523();
	}

}
