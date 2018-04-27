package pipeAndFilter;

import org.junit.Assert;
import org.mockito.MockingDetails;

import pipeAndFilter.sink.Test.TestSink;

public class TestObserver implements negocio.iface.TestObserver{

	@Override
	public void update(TestSink<?> observable) {

		Assert.assertTrue(observable.isResult());
		
	}

}
