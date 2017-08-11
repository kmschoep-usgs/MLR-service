package gov.usgs.wma.data.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import gov.usgs.wma.model.MonitoringLocation;
import gov.usgs.wma.dao.IMonitoringLocationDAO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dpatterm
 */
public class DataService {
	
	private static final Logger log = LoggerFactory.getLogger(DataService.class);
	private IMonitoringLocationDAO monitoringLocationDao;
	
	public DataService(IMonitoringLocationDAO monitoringLocationDao) {
		this.monitoringLocationDao = monitoringLocationDao;
	}
		
	public MonitoringLocation getMonitoringLocationByLocationNumber(String locationNumber){
		return monitoringLocationDao.getMonitoringLocationByLocationNumber(locationNumber);
	}
	
	public List<MonitoringLocation> getMonitoringLocations(Map<String, Object> params){
		return monitoringLocationDao.getMonitoringLocations(params);
	}
}
