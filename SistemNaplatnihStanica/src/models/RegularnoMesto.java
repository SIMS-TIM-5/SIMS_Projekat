package models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class RegularnoMesto extends NaplatnoMesto {

	public RegularnoMesto() {
	}

	public RegularnoMesto(boolean aktivnost, String id, int idStanice) {
		super(aktivnost, id, idStanice);
	}

	@Override
	public String toString() {
		return "RegularnoMesto [aktivnost=" + aktivnost + ", id=" + id + ", idStanice=" + idStanice + "]";
	}

	@Override
	public void naplatiProlaz() {
		
	}
	
	@Override
	public void podigniRampu() {
		
	}
}
