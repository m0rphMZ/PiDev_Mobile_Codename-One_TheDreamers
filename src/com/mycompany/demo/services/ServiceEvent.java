/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.demo.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.demo.entities.Event;
import com.mycompany.demo.utils.Statics;
import java.util.ArrayList;
import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Aymen
 */
public class ServiceEvent {
    public ArrayList<Event> events;

    public static ServiceEvent instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceEvent() {
        req = new ConnectionRequest();
    }

    public static ServiceEvent getInstance() {
        if (instance == null) {
            instance = new ServiceEvent();
        }
        return instance;
    }

    public ArrayList<Event> parseEvents(String jsonText) {
        try {
            events = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> eventsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) eventsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Event e = new Event();
                Object idObj = obj.get("eventId");
                if (idObj == null) {
                    e.setEvent_id(0);
                } else {
                    float event_id = Float.parseFloat(idObj.toString());
                    e.setEvent_id((int) event_id);
                }
                if (obj.get("title") == null) {
                    e.setTitle("error");
                } else {
                    e.setTitle(obj.get("title").toString());
                }
                if (obj.get("type") == null) {
                    e.setType("error");
                } else {
                    e.setType(obj.get("type").toString());
                }
                if (obj.get("description") == null) {
                    e.setDescription("error");
                } else {
                    e.setDescription(obj.get("description").toString());
                }
                if (obj.get("startDate") == null) {
                    e.setStartDate(new Date());
                } else {
                    String dateString = obj.get("startdate").toString();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date date = dateFormat.parse(dateString);
                        e.setStartDate(date);
                    } catch (ParseException ex) {
                        System.err.println("Error parsing date: " + dateString);
                        e.setStartDate(new Date());
                    }
                }
                if (obj.get("enddate") == null) {
                    e.setEndDate(new Date());
                } else {
                    String dateString = obj.get("enddate").toString();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date date = dateFormat.parse(dateString);
                        e.setEndDate(date);
                    } catch (ParseException ex) {
                        System.err.println("Error parsing date: " + dateString);
                        e.setEndDate(new Date());
                    }
                }
                if (obj.get("ticketcount") == null) {
                    e.setTicketCount(0);
                } else {
                    float ticketCount = Float.parseFloat(obj.get("ticketcount").toString());
                    e.setTicketCount((int) ticketCount);
                }
                if (obj.get("affiche") == null) {
                    e.setAffiche("error");
                } else {
                    e.setAffiche(obj.get("affiche").toString());
                }
                if (obj.get("ticketprice") == null) {
                    e.setTicketPrice(0);
                } else {
                    float ticketprice = Float.parseFloat(obj.get("ticketprice").toString());
                    e.setTicketPrice(ticketprice);
                }
                
                
                events.add(e);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return events;
    }

    public ArrayList<Event> getAllEvents() {
        String url = Statics.BASE_URL + "json/index/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                events = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }
}
