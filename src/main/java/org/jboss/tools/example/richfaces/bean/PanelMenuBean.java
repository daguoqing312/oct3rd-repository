package org.jboss.tools.example.richfaces.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.richfaces.event.ItemChangeEvent;

@ManagedBean
@RequestScoped
public class PanelMenuBean {
	
	
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
        	this.current = current;        
    }

    public void updateCurrent(ItemChangeEvent event) {
        setCurrent(event.getNewItemName());
    }
}