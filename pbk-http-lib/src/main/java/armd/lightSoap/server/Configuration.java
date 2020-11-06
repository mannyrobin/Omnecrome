package armd.lightSoap.server;


import java.util.List;

public class Configuration {
   private List<ServiceConfiguration> services;

   public List<ServiceConfiguration> getServices() {
	  return services;
   }

   public void setServices(List<ServiceConfiguration> services) {
	  this.services = services;
   }
}
