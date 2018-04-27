package pipeAndFilter;

import org.junit.Assert;
import org.junit.Test;

import evaluator.Expression;
import model.Package;
import model.RawPackage;
import model.TimeStamp;
import packageBuilder.PackageBuilder;
import pipeAndFilter.filters.PackageBuilderFilter.PackageBuilderNormalizer;
import pipeAndFilter.impl.QueuePipe;

import static org.mockito.Mockito.*;

public class PackageBuilderFilterTest {

	@Test
	public void simpleNormalizerTest() {
		
		Pipe<RawPackage> input = new QueuePipe<>();
		Pipe<Package> output = new QueuePipe<>();
		
		RawPackage rp1 = new RawPackage(new byte[] {0x01});
		Package mockBuilt = new Package(new TimeStamp(0));
		
		Expression<RawPackage> expression1 = mock(Expression.class);
		when(expression1.interpret(rp1)).thenReturn(true);
		
		PackageBuilder builder = mock(PackageBuilder.class);
		when(builder.build(rp1)).thenReturn(mockBuilt);
		
		PackageBuilderNormalizer filter = new PackageBuilderNormalizer(input, output);
		
		filter.addRoute(expression1, builder);
		
		input.accept(rp1);
		
		filter.process();
		
		Assert.assertEquals(output.retireve(), mockBuilt);
		
	}
	
	@Test
	public void twoRoutesNormalizerTest() {
		
		// Setup
		Pipe<RawPackage> input = new QueuePipe<>();
		Pipe<Package> output = new QueuePipe<>();
		
		RawPackage rp1 = new RawPackage(new byte[] {0x01});
		RawPackage rp2 = new RawPackage(new byte[] {0x02});
		Package mockBuilt1 = new Package(new TimeStamp(0));
		Package mockBuilt2 = new Package(new TimeStamp(1));
		
		Expression<RawPackage> expression1 = mock(Expression.class);
		Expression<RawPackage> expression2 = mock(Expression.class);
		when(expression1.interpret(rp1)).thenReturn(true);
		when(expression1.interpret(rp2)).thenReturn(false);
		when(expression2.interpret(rp2)).thenReturn(true);
		when(expression2.interpret(rp1)).thenReturn(false);
		
		PackageBuilder builder1 = mock(PackageBuilder.class);
		PackageBuilder builder2 = mock(PackageBuilder.class);
		when(builder1.build(rp1)).thenReturn(mockBuilt1);
		when(builder2.build(rp2)).thenReturn(mockBuilt2);
		
		PackageBuilderNormalizer filter = new PackageBuilderNormalizer(input, output);
		
		filter.addRoute(expression1, builder1);
		filter.addRoute(expression2, builder2);
		
		//Test
		input.accept(rp1);
		input.accept(rp2);
		
		while(!input.isEmpty()) {
			filter.process();
		}
		
		Assert.assertEquals(output.retireve(), mockBuilt1);
		Assert.assertEquals(output.retireve(), mockBuilt2);
		
	}
	
}
