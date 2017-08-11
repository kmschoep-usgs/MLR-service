package gov.usgs.wma.dao;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import gov.usgs.wma.model.MonitoringLocation;
/**
 *
 * @author zmoore
 */
public interface IMonitoringLocationDAO {

	//Abstract Methods
	public List<MonitoringLocation> getMonitoringLocations(Map<String, Object> params);
	public MonitoringLocation createMonitoringLocation(MonitoringLocation newMonitoringLocation);
	
	//TODO - Implement Updates
	//public MonitoringLocation updateMonitoringLocation(MonitoringLocation updatedMonitoringLocation);
	
	//TODO - Implement Deletes
	//public void deleteMonitoringLocation(MonitoringLocation monitoringLocationToDelete);
	
	//Default Methods -- READ
	public default List<MonitoringLocation> getMonitoringLocations() {return getMonitoringLocations(new HashMap<String,Object>());}
		public default MonitoringLocation getMonitoringLocationById(Long id) {
		return getSingleMonitoringLocationByParam("id", id);
	};
	
	public default MonitoringLocation getMonitoringLocationByLocationNumber(String locationNumber) {
		return getSingleMonitoringLocationByParam("locationNumber", locationNumber);
	};
	
	public default MonitoringLocation getSingleMonitoringLocationByParam(String key, Object value) {
		HashMap<String,Object> params = new HashMap<>();
		MonitoringLocation returnLocation = null;
		params.put(key, value);
		
		List<MonitoringLocation> retrievedLocations = getMonitoringLocations(params);
		
		if(retrievedLocations != null && retrievedLocations.size() > 0){
			returnLocation = retrievedLocations.get(0);
		}
		
		return returnLocation;
	};
	
	//Default Methods -- Delete	
	//public default void deleteMonitoringLocationById(Long locationNumber)
	
	//public default void deleteMontiroingLocationByLocationNumber(Long locationNumber)
	
	
}
