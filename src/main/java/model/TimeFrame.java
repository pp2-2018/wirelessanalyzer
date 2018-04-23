package model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TimeFrame {
	
	private LocalDateTime timeFrameStart;
	private LocalDateTime timeFrameEnd;
	
	public TimeFrame(LocalDateTime inicio, LocalDateTime fin){
		
		this.timeFrameStart = inicio;
		this.timeFrameEnd = fin;
	}
	

	public LocalDateTime getStart() {
		return timeFrameStart;
	}

	public LocalDateTime getEnd() {
		return timeFrameEnd;
		
	}
	
	public boolean contains(LocalDateTime dateTime){
		
		if ( (dateTime.isBefore(timeFrameEnd) || dateTime.equals(timeFrameEnd) )&&
				(dateTime.isAfter(timeFrameStart) || dateTime.equals(timeFrameStart))){
			return true;
		}
		
		return false;
	}
	public boolean contains(TimeStamp timeStamp){
		Instant instant= Instant.ofEpochMilli(timeStamp.getUnixTime());
		System.out.println(instant);
		LocalDateTime dateTime = LocalDateTime.ofInstant(instant,ZoneId.systemDefault());

		return contains(dateTime);
	}
}
