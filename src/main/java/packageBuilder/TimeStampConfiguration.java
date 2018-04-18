package packageBuilder;

import java.security.Timestamp;

import model.TimeStamp;

public class TimeStampConfiguration implements PackageBuilderConfiguration{

	private TimeStamp timestamp;

	protected TimeStampConfiguration(TimeStamp timestamp) {
		super();
		this.timestamp = timestamp;
	}

	protected TimeStamp getTimestamp() {
		return timestamp;
	}

	@Override
	public void configure(PackageBuilder builer) {
		builer.configure(this);
		
	}
	
}
