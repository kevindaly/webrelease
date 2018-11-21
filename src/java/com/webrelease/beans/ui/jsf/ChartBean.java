/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webrelease.beans.ui.jsf;

import java.io.Serializable;  
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;  
import org.primefaces.model.chart.PieChartModel;  



@Named("chartBean")
@SessionScoped
public class ChartBean implements Serializable {  
  
    private PieChartModel livePieModel;  
  
    public ChartBean() {  
        createLivePieModel();  
    }  
  
    public PieChartModel getLivePieModel() {  
        int random1 = (int)(Math.random() * 1000);  
        int random2 = (int)(Math.random() * 1000);  
  
        livePieModel.getData().put("Activity", random1);  
        livePieModel.getData().put("Release", random2);  
  
        return livePieModel;  
    }  
  
    private void createLivePieModel() {  
        livePieModel = new PieChartModel();  
  
        livePieModel.set("Activity", 540);  
        livePieModel.set("Release", 325);  
    }  
}  