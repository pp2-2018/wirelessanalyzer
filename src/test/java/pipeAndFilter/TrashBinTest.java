package pipeAndFilter;

import org.junit.Assert;
import org.junit.Test;

import pipeAndFilter.impl.QueuePipe;
import pipeAndFilter.impl.TrashBin;

public class TrashBinTest {

	@Test
	public void test() {

		Pipe<Object> inputPipe = new QueuePipe<>();

		TrashBin<Object> bin = new TrashBin<>(inputPipe);

		inputPipe.accept(new Object());
		inputPipe.accept(new Object());
		inputPipe.accept(new Object());
		inputPipe.closeForWritting();

		bin.process();
		bin.process();
		bin.process();

		Assert.assertTrue(inputPipe.isEmpty());
		Assert.assertFalse(inputPipe.canRetrieve());

	}

}
