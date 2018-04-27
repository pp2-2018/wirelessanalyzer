package model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class TimeStamp {

	private long unixTime;
	
	public TimeStamp(long unixTime) {
		this.unixTime = unixTime;
	}

	public TimeStamp(LocalDateTime localDateTime) {
		Instant instant = Instant.now(); //can be LocalDateTime
		ZoneId systemZone = ZoneId.systemDefault(); // my timezone
		ZoneOffset currentOffsetForMyZone = systemZone.getRules().getOffset(instant);
		this.unixTime = localDateTime.toInstant(currentOffsetForMyZone).toEpochMilli();
	}

	public TimeStamp(Instant unixTime) {
		this.unixTime = unixTime.toEpochMilli();
	}

	public long getUnixTime() {
		return unixTime;
	}

	public Instant toInstant(){
		return Instant.ofEpochMilli(unixTime);

	}
	
	
	
	
}
