package rt.resources;

import java.util.logging.ConsoleHandler;
import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This class uses CDI to alias Java EE resources, such as the persistence context, to CDI beans
 * 
 * <p>
 * Example injection on a managed bean field:
 * </p>
 * 
 * <pre>
 * &#064;Inject
 * private EntityManager em;
 * </pre>
 */
public class Resources {
   // use @SuppressWarnings to tell IDE to ignore warnings about field not being referenced directly
   @SuppressWarnings("unused")
   @Produces
   @PersistenceContext
   private EntityManager em;
   
   @Produces
   public Logger produceLog(InjectionPoint injectionPoint) {
	   String class_name = injectionPoint.getMember().getDeclaringClass().getName();
       Logger logger = Logger.getLogger(class_name);
       logger.setLevel(Level.INFO);
       //logger.setFilter(new RtFilter());
       //logger.setUseParentHandlers(false);
       //logger.addHandler(new ConsoleHandler());
       return logger;
   }
   
   @Produces
   @RequestScoped
   public FacesContext produceFacesContext() {
      return FacesContext.getCurrentInstance();
   }
   
//   class RtFilter implements Filter{
//	   public boolean isLoggable(LogRecord record){
//		   String title = record.getLoggerName();
//		   System.out.println(title);
//		   if (title.contains("Rt.")) return true;		   
//		   return false;
//	   }
//   }
   
}
