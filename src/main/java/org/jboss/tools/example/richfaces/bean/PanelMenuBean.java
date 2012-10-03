package org.jboss.tools.example.richfaces.bean;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.richfaces.event.ItemChangeEvent;

@ManagedBean
@RequestScoped
public class PanelMenuBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Logger log;
    private String current;
    private boolean singleMode;

    public boolean isSingleMode() {
        return singleMode;
    }

    public void setSingleMode(boolean singleMode) {
        this.singleMode = singleMode;
    }

    public String getCurrent() {
        return this.current;
    }

    public void setCurrent(String current) {
    		//if (current == null || current.equals("")) return;
    		log.info("\n");
    		log.info("current Value:" + current);
        	this.current = current; 
        	
    }

    public void updateCurrent(ItemChangeEvent event) {
        setCurrent(event.getNewItemName());
        
    }
}