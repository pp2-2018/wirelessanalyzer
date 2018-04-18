package packageBuilder;

import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import evaluator.ByteInOffsetExpression;
import evaluator.Expression;
import model.Package;
import model.RawPackage;
import model.TimeStamp;

public class PackageHandlerTest {
	
	
	@Test
	public void test() {
		
		RawPackage packageType1 = new RawPackage(new byte[] {0x00, 0x00, 0x00, (byte) 0xDC, 0x6B, 0x74, 0x77, (byte) 0x80, 0x00, 0x00, (byte) 0xff, (byte) 0xff, (byte) 0xfa});
		RawPackage packageType2 = new RawPackage(new byte[] {0x00, 0x00, (byte) 0xff, 0x00, 0x00, 0x00, (byte) 0xDC, 0x6B, 0x74, 0x77, (byte) 0x80,(byte) 0xff, (byte) 0xfa});

		PackageBuilder builder1 = mock(PackageBuilder.class);
		when(builder1.build(packageType1)).thenReturn(new Package(new TimeStamp(0)));
		PackageBuilder builder2 = mock(PackageBuilder.class);
		when(builder2.build(packageType2)).thenReturn(new Package(new TimeStamp(1)));
		
		Expression<RawPackage> expression1 = mock(ByteInOffsetExpression.class);
		when(expression1.interpret(packageType1)).thenReturn(true);
		when(expression1.interpret(packageType2)).thenReturn(false);
		Expression<RawPackage> expression2 = mock(ByteInOffsetExpression.class);
		when(expression2.interpret(packageType2)).thenReturn(true);
		when(expression2.interpret(packageType1)).thenReturn(false);
		
		PackageHandler handler1 = new PackageHandler(expression1, builder1);
		PackageHandler handler2 = new PackageHandler(expression2, builder2);
		
		handler1.setNext(handler2);
		
		Package package1 = handler1.handlePackage(packageType1);
		Package package2 = handler1.handlePackage(packageType2);
		
		
		Assert.assertEquals(package1.getTimeStamp().getUnixTime(), 0);
		Assert.assertEquals(package2.getTimeStamp().getUnixTime(), 1);
		
		
	}

}
